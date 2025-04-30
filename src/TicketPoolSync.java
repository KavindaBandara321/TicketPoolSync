import java.util.LinkedList;
import java.util.Queue;

public class TicketPoolSync implements TicketPool {

    private Queue<Ticket> ticketQueue = new LinkedList<Ticket>();
    private int maxSize;
    private int noOfTicketsOffered = 0;
    private int noOfTicketsBought = 0;

    public TicketPoolSync(int maxSize) {

        this.maxSize = maxSize;
    }

    @Override
    public synchronized void addTicket(Ticket ticket) {
        while (ticketQueue.size() == this.maxSize) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        ticketQueue.offer(ticket);
        noOfTicketsOffered++;
        System.out.println(Thread.currentThread().getName() + " added TicketNumber: " + ticket.getTicketId() + ", Vendor: " + ticket.getVendor() + ", Event: " + ticket.getEvent());
        notifyAll();
    }

    @Override
    public synchronized Ticket purchaseTicket() {
        while (ticketQueue.isEmpty()) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Ticket ticket = ticketQueue.poll();
        noOfTicketsBought++;
        System.out.println(Thread.currentThread().getName() + " purchased TicketNumber: " + ticket.getTicketId() + ", Vendor: " + ticket.getVendor() + ", Event: " + ticket.getEvent());
        notifyAll();
        return ticket;
    }

    @Override
    public synchronized void printTicketPoolStatus() {
        System.out.println("No of tickets added to queue by Vendor: " + noOfTicketsOffered);
        System.out.println("No of tickets bought from queue by Customer: " + noOfTicketsBought);
        System.out.println("No of tickets currently available in the queue: " + ticketQueue.size());
    }
}

