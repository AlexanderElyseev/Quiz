import org.junit.Assert;
import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class DequeTest {
    @Test
    public void testIsEmpty() {
        Deque<Integer> deque = new Deque<Integer>();
        Assert.assertTrue(deque.isEmpty());

        deque.addFirst(1);
        Assert.assertFalse(deque.isEmpty());

        deque.removeFirst();
        Assert.assertTrue(deque.isEmpty());
    }

    @Test
    public void testSize() {
        Deque<Integer> deque = new Deque<Integer>();
        Assert.assertEquals(0, deque.size());

        deque.addFirst(1);
        Assert.assertEquals(1, deque.size());

        deque.removeFirst();
        Assert.assertEquals(0, deque.size());
    }

    @Test
    public void testAddNullException() {
        try {
            new Deque<Integer>().addFirst(null);
        } catch (NullPointerException ignored) {
        } catch (Exception e) {
            Assert.fail();
        }

        try {
            new Deque<Integer>().addLast(null);
        } catch (NullPointerException ignored) {
        } catch (Exception e) {
            Assert.fail();
        }
    }

    @Test
    public void testAddFirstRemoveFirst() {
        Deque<Integer> deque = new Deque<Integer>();
        deque.addFirst(1);
        deque.addFirst(2);

        Assert.assertEquals(2, deque.removeFirst().intValue());
        Assert.assertEquals(1, deque.removeFirst().intValue());
        Assert.assertTrue(deque.isEmpty());
    }

    @Test
    public void testAddLastRemoveLast() {
        Deque<Integer> deque = new Deque<Integer>();
        deque.addLast(1);
        deque.addLast(2);

        Assert.assertEquals(2, deque.removeLast().intValue());
        Assert.assertEquals(1, deque.removeLast().intValue());
        Assert.assertTrue(deque.isEmpty());
    }

    @Test
    public void testAddFirstRemoveLast() {
        Deque<Integer> deque = new Deque<Integer>();
        deque.addFirst(1);

        Assert.assertEquals(1, deque.removeLast().intValue());
        Assert.assertTrue(deque.isEmpty());
    }

    @Test
    public void testAddLastRemoveFirst() {
        Deque<Integer> deque = new Deque<Integer>();
        deque.addLast(1);

        Assert.assertEquals(1, deque.removeFirst().intValue());
        Assert.assertTrue(deque.isEmpty());
    }

    @Test
    public void testAddRemove() {
        Deque<Integer> deque = new Deque<Integer>();

        deque.addFirst(1);
        deque.addLast(2);
        deque.addFirst(3);
        deque.addLast(4);
        Assert.assertEquals(3, deque.removeFirst().intValue());
        Assert.assertEquals(1, deque.removeFirst().intValue());
        Assert.assertEquals(2, deque.removeFirst().intValue());
        Assert.assertEquals(4, deque.removeFirst().intValue());
        Assert.assertTrue(deque.isEmpty());

        deque.addLast(5);
        deque.addLast(6);
        deque.addLast(7);
        deque.addLast(8);
        Assert.assertEquals(5, deque.removeFirst().intValue());
        Assert.assertEquals(6, deque.removeFirst().intValue());
        Assert.assertEquals(7, deque.removeFirst().intValue());
        Assert.assertEquals(8, deque.removeFirst().intValue());
        Assert.assertTrue(deque.isEmpty());

        deque.addFirst(9);
        deque.addFirst(10);
        deque.addFirst(11);
        deque.addFirst(12);
        Assert.assertEquals(12, deque.removeFirst().intValue());
        Assert.assertEquals(11, deque.removeFirst().intValue());
        Assert.assertEquals(10, deque.removeFirst().intValue());
        Assert.assertEquals(9, deque.removeFirst().intValue());
        Assert.assertTrue(deque.isEmpty());

        deque.addLast(13);
        deque.addLast(14);
        deque.addLast(15);
        deque.addLast(16);
        Assert.assertEquals(16, deque.removeLast().intValue());
        Assert.assertEquals(15, deque.removeLast().intValue());
        Assert.assertEquals(14, deque.removeLast().intValue());
        Assert.assertEquals(13, deque.removeLast().intValue());
        Assert.assertTrue(deque.isEmpty());

        deque.addFirst(17);
        deque.addFirst(18);
        deque.addFirst(19);
        deque.addFirst(20);
        Assert.assertEquals(17, deque.removeLast().intValue());
        Assert.assertEquals(18, deque.removeLast().intValue());
        Assert.assertEquals(19, deque.removeLast().intValue());
        Assert.assertEquals(20, deque.removeLast().intValue());
        Assert.assertTrue(deque.isEmpty());
    }

    @Test
    public void testRemoveFromEmptyException() {
        try {
            new Deque<Integer>().removeFirst();
        } catch (NoSuchElementException ignored) {
        } catch (Exception e) {
            Assert.fail();
        }

        try {
            new Deque<Integer>().removeLast();
        } catch (NoSuchElementException ignored) {
        } catch (Exception e) {
            Assert.fail();
        }
    }

    @Test
    public void testRemoveFromIteratorException() {
        try {
            new Deque<Integer>().iterator().remove();
        } catch (UnsupportedOperationException ignored) {
        } catch (Exception e) {
            Assert.fail();
        }
    }

    @Test
    public void testNoNextIteratorException() {
        try {
            new Deque<Integer>().iterator().next();
        } catch (NoSuchElementException ignored) {
        } catch (Exception e) {
            Assert.fail();
        }
    }

    @Test
    public void testIterator() {
        Deque<Integer> deque = new Deque<Integer>();
        deque.addLast(1);
        deque.addFirst(2);
        deque.addFirst(3);
        deque.addLast(4);

        // from top to head
        Iterator<Integer> it = deque.iterator();
        Assert.assertTrue(it.hasNext());
        Assert.assertEquals(3, it.next().intValue());
        Assert.assertTrue(it.hasNext());
        Assert.assertEquals(2, it.next().intValue());
        Assert.assertTrue(it.hasNext());
        Assert.assertEquals(1, it.next().intValue());
        Assert.assertTrue(it.hasNext());
        Assert.assertEquals(4, it.next().intValue());
        Assert.assertFalse(it.hasNext());

        // first only
        deque = new Deque<Integer>();
        for (int i = 0; i < 1000; i++)
            deque.addFirst(i);

        it = deque.iterator();
        for (int i = 999; i >= 0; i--)
            Assert.assertEquals(i, it.next().intValue());

        Assert.assertFalse(it.hasNext());

        // last only
        deque = new Deque<Integer>();
        for (int i = 0; i < 1000; i++)
            deque.addLast(i);

        it = deque.iterator();
        for (int i = 0; i < 1000; i++)
            Assert.assertEquals(i, it.next().intValue());

        Assert.assertFalse(it.hasNext());
    }

    @Test
    public void testIteratorCompleteness() {
        testIteratorCompleteness(1);
        testIteratorCompleteness(2);
        testIteratorCompleteness(3);
        testIteratorCompleteness(4);
        testIteratorCompleteness(10);
        testIteratorCompleteness(20);
        testIteratorCompleteness(100);
    }

    private void testIteratorCompleteness(int n) {
        Deque<Integer> queue = new Deque<Integer>();
        boolean[] items = new boolean[n];
        for (int i = 0; i < n; i++)
            queue.addFirst(i);

        for (Integer item  : queue)
            items[item] = true;

        for (int i = 0; i < n; i++)
            Assert.assertTrue(items[i]);
    }
}