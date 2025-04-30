public class Customer implements Runnable {

    private TicketPool ticketPool;
    private volatile boolean running = true;

    public Customer(TicketPool ticketPool) {
        this.ticketPool = ticketPool;
    }

    public void stop() {
        running = false;
    }

    @Override
    public void run() {
        while (running) {
            ticketPool.purchaseTicket();
            try {
                Thread.sleep(1200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(Thread.currentThread().getName() + " stopped!");
    }
}
