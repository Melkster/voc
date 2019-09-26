import org.python.types.Int;
import org.python.types.List;

public class ConcatenateList {

    public static void main(String[] args) {
        int N_LISTS = 75;
        int N_ITEMS = 50000;

        List lists = new List();

        for (int i = 0; i < N_LISTS; i++) {
            List l = new List();

            for (int j = 0; j < N_ITEMS; j++) {
                l.append(Int.getInt(j));
            }

            lists.append(l);
        }

        List concatenatedLists = new List();

        long start = System.nanoTime();

        for (int i = 0; i < N_LISTS; i++) {
            concatenatedLists.__iadd__(lists.__getitem__(Int.getInt(i)));
        }

        long end = System.nanoTime();

        System.out.println(String.format("It took %f seconds to concatenate %d lists with %d items",
                (double) (end - start) / 1e9, N_LISTS, N_ITEMS));
    }
}
