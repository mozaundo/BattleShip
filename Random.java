import java.util.concurrent.ThreadLocalRandom;

public class Random {
    public static int RandInt(int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max);
    }
}