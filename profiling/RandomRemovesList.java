import java.util.concurrent.ThreadLocalRandom;
import org.python.types.Int;
import org.python.types.List;

public class RandomRemovesList {
    private static long LIST_SIZE = 200000;
    private static final long AMOUNT_OF_REMOVES = 50000;

    public static final void main(String[] args) {

        List l = new List();
        for (long i = 0; i < LIST_SIZE; i++) {
            l.append(Int.getInt(ThreadLocalRandom.current().nextLong(Long.MIN_VALUE, Long.MAX_VALUE)));
        }

        long start = System.nanoTime();

        for (long j = 0; j < AMOUNT_OF_REMOVES; j++) {
            long index = ThreadLocalRandom.current().nextLong(0, LIST_SIZE);
            l.pop(Int.getInt(index));
            LIST_SIZE -= 1;
        }

        long end = System.nanoTime();

        System.out.println(String.format("Time taken: %f seconds", (double) (end - start) / 1e9));
    }
}