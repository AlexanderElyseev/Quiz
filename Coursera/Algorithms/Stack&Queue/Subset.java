import java.util.Iterator;

public class Subset {
    public static void main(String[] args) {
        // Reading input.
        int count = Integer.parseInt(args[0]);

        // Initializing queue.
        RandomizedQueue<String> queue = new RandomizedQueue<String>();
        while (!StdIn.isEmpty())
            queue.enqueue(StdIn.readString());

        // Printing output.
        Iterator<String> it = queue.iterator();
        int index = 0;
        while (index++ < count)
            StdOut.println(it.next());
    }
}
