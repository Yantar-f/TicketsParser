import java.util.Comparator;

public class TicketPriceComparator implements Comparator<Ticket> {
    @Override
    public int compare(Ticket o1, Ticket o2) {
        return Integer.compare(o1.getPrice(), o2.getPrice());
    }
}
