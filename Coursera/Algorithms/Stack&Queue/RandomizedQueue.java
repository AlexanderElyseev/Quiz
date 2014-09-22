import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<T> {
    private class Wrapper {
        T item;
        Wrapper next;
        Wrapper previous;
    }

    private Wrapper head;
    private Wrapper tail;
    private int size;

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void enqueue(T item) {
        if (item == null)
            throw new NullPointerException();

        if (size == 0) {
            // 1st item -> head === tail.
            Wrapper first = new Wrapper();
            first.item = item;
            first.next = null;
            first.previous = null;

            head = tail = first;
        } else {
            // New item before (doesn't matter) random item.
            // TODO: needless random here?
            int index = StdRandom.uniform(size);

            Wrapper current = tail;
            while (--index >= 0) {
                current = current.next;
            }

            Wrapper random = new Wrapper();
            random.item = item;
            random.next = current;
            random.previous = current.previous;

            if (current.previous != null)
                current.previous.next = random;

            current.previous = random;

            if (current == tail)
                tail = random;
        }

        size++;
    }

    public T dequeue() {
        if (size == 0)
            throw new NoSuchElementException();

        int index = StdRandom.uniform(size);

        Wrapper current = tail;
        while (--index >= 0) {
            current = current.next;
        }

        size--;

        if (size == 0) {
            tail = null;
            head = null;
        } else {
            if (current == tail) {
                tail = tail.next;
                tail.previous = null;
            } else if (current == head) {
                head = head.previous;
                head.next = null;
            } else {
                current.previous.next = current.next;
                current.next.previous = current.previous;
            }
        }

        return current.item;
    }

    public T sample() {
        if (size == 0)
            throw new NoSuchElementException();

        int index = StdRandom.uniform(size);

        Wrapper current = tail;
        while (--index >= 0) {
            current = current.next;
        }

        return current.item;
    }

    private T[] shuffle() {
        @SuppressWarnings("unchecked")
        T[] data = (T[])new Object[size];

        int index = 0;
        Wrapper current = tail;
        while (current != null) {
            data[index++] = current.item;
            current = current.next;
        }

        for (int i = 1; i < size; i++) {
            int rnd = StdRandom.uniform(i);
            T tmp = data[rnd];
            data[rnd] = data[i];
            data[i] = tmp;
        }

        return data;
    }

    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private T[] data = shuffle();
            private int index = 0;

            @Override
            public boolean hasNext() {
                return index < data.length;
            }

            @Override
            public T next() {
                if (index == data.length)
                    throw new NoSuchElementException();

                return data[index++];
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }
}
