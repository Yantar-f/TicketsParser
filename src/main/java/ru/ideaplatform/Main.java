package ru.ideaplatform;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Main {
    private static final int FILE_PATH_ARGUMENT_INDEX = 0;

    public static void main(String[] args) {
        if (args.length < 1)
            throw new MissingProgramArgumentException();

        String filePath = args[FILE_PATH_ARGUMENT_INDEX];
        File ticketsFile = new File(filePath);
        List<Ticket> tickets = parseTicketsFrom(ticketsFile);
        Set<String> carriers = extractCarriersFrom(tickets);
        List<Ticket> vladivostokTelAvivFlights = extractVladivostokTelAvivFlightsFrom(tickets);

        Map<String, Integer> carriersVladivostokTelAvivMinFlightTime =
                computeCarriersMinFlightTime(carriers, vladivostokTelAvivFlights);

        double diffBetweenAvgAndMedianPrice = computeDiffBetweenAvgAndMedianPrice(vladivostokTelAvivFlights);

        System.out.println("Carriers Vladivostok to Tel-Aviv min flight time (minutes): " + carriersVladivostokTelAvivMinFlightTime);
        System.out.println("Difference between average and median price: " + diffBetweenAvgAndMedianPrice);
    }

    private static double computeDiffBetweenAvgAndMedianPrice(List<Ticket> tickets) {
        double avg = computeAvgPrice(tickets);
        double median = computeMedianPrice(tickets);
        return Math.abs(avg - median);
    }

    private static int computeMedianPrice(List<Ticket> tickets) {
        List<Integer> sortedPrices = tickets.stream()
                .map(Ticket::getPrice)
                .sorted()
                .toList();

        if (tickets.size()%2 == 0) {
            int left = sortedPrices.get(sortedPrices.size()/2 - 1);
            int right = sortedPrices.get(sortedPrices.size()/2);

            return (left + right)/2;
        } else {
            return sortedPrices.get(sortedPrices.size()/2);
        }
    }

    private static double computeAvgPrice(List<Ticket> tickets) {
        return (double) tickets.stream()
                .map(Ticket::getPrice)
                .reduce(Integer::sum)
                .orElse(0) /tickets.size();
    }

    private static Map<String, Integer> computeCarriersMinFlightTime(Set<String> carriers,
                                                                    List<Ticket> tickets) {
        Map<String, Integer> carriersMinFlightTime = new HashMap<>();

        carriers.forEach(carrier -> tickets.stream()
                .filter(ticket -> ticket.getCarrier().equals(carrier))
                .min(new TicketFlightTimeComparator())
                .map(ticket -> carriersMinFlightTime.put(carrier, ticket.getFlightTimeMinutes())));

        return carriersMinFlightTime;
    }

    private static List<Ticket> parseTicketsFrom(File ticketsJSON) {
        ObjectMapper objectMapper = new ObjectMapper().findAndRegisterModules();

        try {
            TicketsReport ticketsReport = objectMapper.readValue(ticketsJSON, new TypeReference<>(){});
            return ticketsReport.getTickets();
        } catch (IOException exception) {
            throw new FileParsingException(ticketsJSON, exception);
        }
    }

    private static List<Ticket> extractVladivostokTelAvivFlightsFrom(List<Ticket> tickets) {
        return tickets.stream()
                .filter(t -> (t.getOrigin().equals("TLV") || t.getOrigin().equals("VVO")) &&
                        (t.getDestination().equals("TLV") || t.getDestination().equals("VVO")))
                .toList();
    }

    private static Set<String> extractCarriersFrom(List<Ticket> tickets) {
        return tickets.stream()
                .map(Ticket::getCarrier)
                .collect(Collectors.toSet());
    }
}
