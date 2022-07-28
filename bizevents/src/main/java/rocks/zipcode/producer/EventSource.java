package rocks.zipcode.producer;

import java.util.concurrent.ArrayBlockingQueue;

public class EventSource extends Thread {
    private ArrayBlockingQueue<BizEvent> events = new ArrayBlockingQueue<>(6);
    // why 6? it's a random number!

    @Override
    public void run() {
        super.run();

        while (true) {
            try {
                events.put(ATMEvent.produceEvent()); // this will block on full queue
                Thread.sleep(325); // yes, busy-wait wastes CPU cycles... who cares?
            } catch (InterruptedException e) {
                System.err.println(e);
                break;
            }
        }
    }

    public BizEvent fetch() {
        BizEvent ev = null;

        try {
            ev = events.take(); // this will block on empty queue
            return ev;
        } catch (InterruptedException e) {
            System.err.println(e);
        }
        return ev;
    }
}
