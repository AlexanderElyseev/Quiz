import java.util.Iterator;

public class Deque<T> {
    private class Wrapper {
        T item;
        Wrapper next;
        Wrapper previous;

        @Override
        public String toString() {
            return item.toString();
        }
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
            throw new UnsupportedOperationException();

        T result = head.item;
        head = head.previous;

        size--;

        if (size == 0)
            tail = null;

        return result;
    }

    public T removeLast() {
        if (size == 0)
            throw new UnsupportedOperationException();

        T result = tail.item;
        tail = tail.next;

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
