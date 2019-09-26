
import java.util.concurrent.ThreadLocalRandom;
import org.python.types.Int;
import org.python.types.List;

public class RandomReadsList {
    private static final int LIST_SIZE = 1000000;
    private static final int NR_READS = 1000000;
    private static final long MAX_VALUE = Long.MAX_VALUE;
    private static final long MIN_VALUE = Long.MIN_VALUE;

    public static void main(String[] args) {
        List l = new List();
        for (int i = 0; i < LIST_SIZE; ++i) {
            long rand = ThreadLocalRandom.current().nextLong(MIN_VALUE, MAX_VALUE);
            l.append(Int.getInt(rand));
        }

        long start = System.nanoTime();

        for (int i = 0; i < NR_READS; ++i) {
            ThreadLocalRandom.current().nextLong(MIN_VALUE, MAX_VALUE);
            org.python.Object tmp = l.__getitem__(Int.getInt((int) (Math.random() * LIST_SIZE)));
        }

        System.out.println("Time taken: " + (System.nanoTime() - start) / 1000000000.);
    }

}