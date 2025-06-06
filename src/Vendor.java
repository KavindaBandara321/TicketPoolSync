public class Vendor implements Runnable {

    private TicketPool ticketPool;
    private String vendorName;
    private volatile boolean running = true;

    public Vendor(TicketPool ticketPool, String vendorName) {
        this.ticketPool = ticketPool;
        this.vendorName = vendorName;
    }

    public void stop() {
        running = false;
    }

    @Override
    public void run() {
        int i = 1;
        while (running) {
            Ticket ticket = new Ticket(" " + i++, vendorName, " Concert Event");
            ticketPool.addTicket(ticket);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(Thread.currentThread().getName() + " stopped!");
    }
}
