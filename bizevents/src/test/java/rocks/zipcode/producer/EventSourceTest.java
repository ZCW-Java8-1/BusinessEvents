package rocks.zipcode.producer;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EventSourceTest {

    @Test
    void fetchTest1() {
        EventSource evs = new EventSource();
        BizEvent ev;

        evs.start();
        for (int i = 1; i <=10; i++) {
            ev = evs.fetch();
            System.out.println(ev.toString());
        }
    }
    @Test
    void fetchTest2() {
        EventSource evs = new EventSource();
        BizEvent ev;

        evs.start();
        for (int i = 1; i <=10; i++) {
            ev = evs.fetch();
            System.out.println(ev.toJSON());
        }
    }
}
