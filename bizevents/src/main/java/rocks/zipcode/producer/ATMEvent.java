package rocks.zipcode.producer;

import java.sql.Timestamp;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import com.google.gson.Gson;

public class ATMEvent extends BizEvent {
    private static final Random randy = new Random();
    private static final Gson gson = new Gson();

    enum ATMEvType {
        DEPOSIT,
        WITHDRAW; //,
        //BALANCE;

        private static final List<ATMEvType> VALUES = Collections.unmodifiableList(Arrays.asList(values()));
        private static final int SIZE = VALUES.size();
        private static final Random RANDOM = new Random();

        public static ATMEvType randomEVType() {
            return VALUES.get(RANDOM.nextInt(SIZE));
        }
    }

    private String origin = ZonedDateTime.now().toString();
    private ATMEvType evtype = ATMEvType.randomEVType();
    private Double amount = 0.0;
    private String account = "";

    public static ATMEvent produceEvent() {
        ATMEvent t = new ATMEvent();
        t.amount = getRandomAmount();
        t.account = getRandomAccount();
        return t;
    }

    private static final int NUMACCTS = 10;
    public static Double getRandomAmount() {
        return Math.floor(randy.nextDouble() * 10000.0) / 100.0;
    }
    public static String getRandomAccount() {
        int n = (int) Math.floor((randy.nextDouble() * NUMACCTS) + 2000.0);
        return String.valueOf(n);
    }

    @Override
    public String toString() {
        return String.format("%s | %s | acct-%s | %8.2f ",
            evtype.toString(),
            origin,
            account, amount);
    }
    public String toJSON() {
        return gson.toJson(this);
    }
}
