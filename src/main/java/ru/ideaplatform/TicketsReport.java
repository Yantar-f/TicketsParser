package ru.ideaplatform;

import java.util.List;

public class TicketsReport {
    private List<Ticket> tickets;

    public TicketsReport() {
    }

    public TicketsReport(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }
}
