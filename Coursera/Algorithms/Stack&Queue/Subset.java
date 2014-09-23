import java.util.Iterator;

public class Subset {
    public static void main(String[] args) {
        // Reading input.
        int count = Integer.parseInt(args[0]);
        String input = StdIn.readLine();

        // Initializing queue.
        RandomizedQueue<String> queue = new RandomizedQueue<String>();
        for (String item : input.split("\\s+"))
            queue.enqueue(item);

        // Printing output.
        Iterator<String> it = queue.iterator();
        int index = 0;
        while (index++ < count)
            StdOut.println(it.next());
    }
}
