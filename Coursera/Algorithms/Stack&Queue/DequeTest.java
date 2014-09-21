import org.junit.Assert;
import org.junit.Test;

import java.util.Iterator;

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
    public void testIterator() {
        Deque<Integer> deque = new Deque<Integer>();
        deque.addLast(1);
        deque.addFirst(2);
        deque.addFirst(3);
        deque.addLast(4);

        Iterator<Integer> iterator = deque.iterator();
        Assert.assertTrue(iterator.hasNext());
        Assert.assertEquals(4, iterator.next().intValue());
        Assert.assertTrue(iterator.hasNext());
        Assert.assertEquals(1, iterator.next().intValue());
        Assert.assertTrue(iterator.hasNext());
        Assert.assertEquals(2, iterator.next().intValue());
        Assert.assertTrue(iterator.hasNext());
        Assert.assertEquals(3, iterator.next().intValue());
        Assert.assertFalse(iterator.hasNext());
    }
}