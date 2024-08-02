package ru.ideaplatform;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneOffset;
import java.util.concurrent.TimeUnit;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class Ticket {
    private String origin;
    private String originName;
    private String destination;
    private String destinationName;

    @JsonFormat(pattern = "dd.MM.yy")
    private LocalDate departureDate;

    @JsonFormat(pattern = "H:mm")
    private LocalTime departureTime;

    @JsonFormat(pattern = "dd.MM.yy")
    private LocalDate arrivalDate;

    @JsonFormat(pattern = "H:mm")
    private LocalTime arrivalTime;

    private String carrier;
    private int stops;
    private int price;

    public Ticket() {
    }

    public Ticket(String origin,
                  String originName,
                  String destination,
                  String destinationName,
                  LocalDate departureDate,
                  LocalTime departureTime,
                  LocalDate arrivalDate,
                  LocalTime arrivalTime,
                  String carrier,
                  int stops,
                  int price) {
        this.origin = origin;
        this.originName = originName;
        this.destination = destination;
        this.destinationName = destinationName;
        this.departureDate = departureDate;
        this.departureTime = departureTime;
        this.arrivalDate = arrivalDate;
        this.arrivalTime = arrivalTime;
        this.carrier = carrier;
        this.stops = stops;
        this.price = price;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getOriginName() {
        return originName;
    }

    public void setOriginName(String originName) {
        this.originName = originName;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getDestinationName() {
        return destinationName;
    }

    public void setDestinationName(String destinationName) {
        this.destinationName = destinationName;
    }

    public LocalDate getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(LocalDate departureDate) {
        this.departureDate = departureDate;
    }

    public LocalTime getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(LocalTime departureTime) {
        this.departureTime = departureTime;
    }

    public LocalDate getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(LocalDate arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public LocalTime getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(LocalTime arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public String getCarrier() {
        return carrier;
    }

    public void setCarrier(String carrier) {
        this.carrier = carrier;
    }

    public int getStops() {
        return stops;
    }

    public void setStops(int stops) {
        this.stops = stops;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getFlightTimeMinutes() {
        long arrivalSeconds = computeArrivalSeconds();
        long departureSeconds = computeDepartureSeconds();
        return (int) TimeUnit.SECONDS.toMinutes(arrivalSeconds - departureSeconds);
    }

    private long computeDepartureSeconds() {
        return LocalDateTime.of(departureDate, departureTime).toEpochSecond(ZoneOffset.UTC);
    }

    private long computeArrivalSeconds() {
        return LocalDateTime.of(arrivalDate, arrivalTime).toEpochSecond(ZoneOffset.UTC);
    }

    @Override
    public String toString() {
        return "ru.ideaplatform.Ticket{" +
                "origin='" + origin + '\'' +
                ", originName='" + originName + '\'' +
                ", destination='" + destination + '\'' +
                ", destinationName='" + destinationName + '\'' +
                ", departureDate=" + departureDate +
                ", departureTime=" + departureTime +
                ", arrivalDate=" + arrivalDate +
                ", arrivalTime=" + arrivalTime +
                ", carrier='" + carrier + '\'' +
                ", stops=" + stops +
                ", price=" + price +
                '}';
    }
}
