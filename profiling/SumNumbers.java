
import org.python.types.Int;
import org.python.types.Iterator;
import org.python.types.List;

public class SumNumbers {
    private static final long LIST_SIZE = 2000000;

    public static final void main(String[] args) {

        List l = new List();
        for (long i = 0; i < LIST_SIZE; i++) {
            l.append(Int.getInt(i));
        }

        long start = System.nanoTime();

        long sum = 0;
        try {
            Iterator iter = (Iterator) l.__iter__();
            while (true) {
                Int pythonValue = (Int) iter.__next__().__int__();
                sum += pythonValue.value;
            }

        } catch (org.python.exceptions.StopIteration e) {
        }

        long end = System.nanoTime();

        System.out.println(String.format("Calculating the sum took %f seconds", (double) (end - start) / 1e9));
    }
}