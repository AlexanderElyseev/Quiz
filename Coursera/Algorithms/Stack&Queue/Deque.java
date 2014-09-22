import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<T> {
    private class Wrapper {
        T item;
        Wrapper next;
        Wrapper previous;
    }

    private Wrapper head;

    private Wrapper tail;

    private int size = 0;

    boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void addFirst(T item) {
        if (item == null)
            throw new NullPointerException();

        Wrapper oldHead = head;

        Wrapper newHead = new Wrapper();
        newHead.item = item;
        newHead.next = null;
        newHead.previous = oldHead;
        head = newHead;

        if (oldHead != null)
            oldHead.next = newHead;

        // 1 item -> tail === head.
        if (size == 0)
            tail = head;

        size++;
    }

    public void addLast(T item) {
        if (item == null)
            throw new NullPointerException();

        Wrapper oldTail = tail;

        Wrapper newTail = new Wrapper();
        newTail.item = item;
        newTail.next = oldTail;
        newTail.previous = null;

        tail = newTail;

        if (oldTail != null)
            oldTail.previous = newTail;

        // 1 item -> tail === head.
        if (size == 0)
            head = tail;

        size++;
    }

    public T removeFirst() {
        if (size == 0)
            throw new NoSuchElementException();

        T result = head.item;

        // Previous item from head become head.
        head = head.previous;
        if (head != null)
            head.next = null;

        // If it was the last item, than tail === head === null.
        size--;
        if (size == 0)
            tail = null;

        return result;
    }

    public T removeLast() {
        if (size == 0)
            throw new NoSuchElementException();

        T result = tail.item;

        // Next item from tail become new tail.
        tail = tail.next;
        if (tail != null)
            tail.previous = null;

        // If it was the last item, than tail === head === null.
        size--;
        if (size == 0)
            head = null;

        return result;
    }

    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private Wrapper current = tail;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public T next() {
                if (current == null)
                    throw new NoSuchElementException();

                T val = current.item;
                current = current.next;
                return val;
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }
}
