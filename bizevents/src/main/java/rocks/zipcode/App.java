package rocks.zipcode;

import rocks.zipcode.producer.ATMEvent;
import rocks.zipcode.producer.BizEvent;
import rocks.zipcode.producer.EventSource;

/**
 * Hello world!
 */
public final class App {
    private EventSource evs;
    // private BankService bankservice;
    // private LogService logservice;

    private App() {
        this.evs = new EventSource();
        evs.start();
    }

     public static void main(String[] args) {
        App app = new App();

        // you might consider putting this code into a EventConsumer class
         // and send app into it? or leave it here in App?
         BizEvent ev;

        while (true) {
            ev = app.evs.fetch();
            //
            System.out.println(ev.toString());
            //
            // No no, we don't just print it out...
            // write method that will

            // app.handleEvent(ev);
        }
    }

    // handleEvent....
    // look at README again, this is where you need to use the BankService and LogService classes.
}
