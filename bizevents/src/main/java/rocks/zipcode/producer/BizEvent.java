package rocks.zipcode.producer;

import java.util.UUID;

public abstract class BizEvent {
    private String evid = UUID.randomUUID().toString();

    public String toJSON() {
        return String.format("%s", evid);
    }
}
