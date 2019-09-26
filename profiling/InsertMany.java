
import org.python.types.Int;
import org.python.types.List;

public class InsertMany {
    private static final long N_ITEMS = 60000;

    public static final void main(String[] args) {

        long start = System.nanoTime();

        List l = new List();
        Int indexZero = Int.getInt(0);

        for (long i = 0; i < N_ITEMS; i++) {
            l.insert(indexZero, Int.getInt(i));
        }

        long end = System.nanoTime();

        System.out.println(String.format("It took %f seconds to insert %d items into the beginning of a list", (double) (end - start) / 1e9, N_ITEMS));
    }
}