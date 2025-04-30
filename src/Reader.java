public class Reader implements Runnable {

    private TicketPool ticketPool;
    private volatile boolean running = true;

    public Reader(TicketPool ticketPool) {
        this.ticketPool = ticketPool;
    }

    public void stop() {
        running = false;
    }

    @Override
    public void run() {
        while (running) {
            ticketPool.printTicketPoolStatus();
            try {
                Thread.sleep(2500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(Thread.currentThread().getName() + " stopped!");
    }
}
