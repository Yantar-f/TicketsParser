package ru.ideaplatform;

import java.util.Comparator;

public class TicketFlightTimeComparator implements Comparator<Ticket> {
    @Override
    public int compare(Ticket o1, Ticket o2) {
        return Integer.compare(o1.getFlightTimeMinutes(), o2.getFlightTimeMinutes());
    }
}
