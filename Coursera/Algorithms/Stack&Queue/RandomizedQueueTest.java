import org.junit.Assert;
import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueueTest {
    @Test
    public void testSizeAndEmpty() {
        RandomizedQueue<Integer> queue = new RandomizedQueue<Integer>();
        Assert.assertEquals(0, queue.size());
        Assert.assertTrue(queue.isEmpty());

        queue.enqueue(1);
        Assert.assertEquals(1, queue.size());
        Assert.assertFalse(queue.isEmpty());

        queue.dequeue();
        Assert.assertEquals(0, queue.size());
        Assert.assertTrue(queue.isEmpty());
    }

    @Test
    public void testEnqueueNullException() {
        try {
            RandomizedQueue<Integer> queue = new RandomizedQueue<Integer>();
            queue.enqueue(null);
        } catch (NullPointerException ignored) {
        } catch (Exception e) {
            Assert.fail();
        }
    }

    @Test
    public void testDequeueEmptyException() {
        try {
            RandomizedQueue<Integer> queue = new RandomizedQueue<Integer>();
            queue.dequeue();
        } catch (NoSuchElementException ignored) {
        } catch (Exception e) {
            Assert.fail();
        }
    }

    @Test
    public void testDequeueSingle() {
        RandomizedQueue<Integer> queue = new RandomizedQueue<Integer>();
        queue.enqueue(1);
        Assert.assertEquals(1, queue.dequeue().intValue());
        Assert.assertTrue(queue.isEmpty());
    }

    @Test
    public void testDequeue() {
        testDequeue(1);
        testDequeue(2);
        testDequeue(3);
        testDequeue(4);
        testDequeue(10);
        testDequeue(20);
        testDequeue(100);
    }

    public void testDequeue(int n) {
        RandomizedQueue<Integer> queue = new RandomizedQueue<Integer>();
        for (int i = 0; i < n; i++)
            queue.enqueue(i);

        for (int i = 0; i < n; i++)
            queue.dequeue();

        Assert.assertTrue(queue.isEmpty());
    }

    @Test
    public void testSampleEmptyException() {
        try {
            RandomizedQueue<Integer> queue = new RandomizedQueue<Integer>();
            queue.sample();
        } catch (NoSuchElementException ignored) {
        } catch (Exception e) {
            Assert.fail();
        }
    }

    @Test
    public void testRemoveFromIteratorException() {
        try {
            new RandomizedQueue<Integer>().iterator().remove();
        } catch (UnsupportedOperationException ignored) {
        } catch (Exception e) {
            Assert.fail();
        }
    }

    @Test
    public void testNoNextIteratorException() {
        try {
            new RandomizedQueue<Integer>().iterator().next();
        } catch (NoSuchElementException ignored) {
        } catch (Exception e) {
            Assert.fail();
        }
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
        RandomizedQueue<Integer> queue = new RandomizedQueue<Integer>();
        boolean[] items = new boolean[n];
        for (int i = 0; i < n; i++)
            queue.enqueue(i);

        for (Integer item : queue)
            items[item] = true;

        for (int i = 0; i < n; i++)
            Assert.assertTrue(items[i]);
    }

    @Test
    public void testIteratorRandomness() {
        RandomizedQueue<Integer> queue = new RandomizedQueue<Integer>();

        int n = 4;
        for (int i = 0; i < n; i++)
            queue.enqueue(i);

        boolean random = false;
        for (int i = 0; i < 1000; i++) {
            Integer[] i1 = new Integer[n];
            Integer[] i2 = new Integer[n];
            Iterator<Integer> it1 = queue.iterator();
            Iterator<Integer> it2 = queue.iterator();

            for (int j = 0; j < n; j++)
            {
                Assert.assertTrue(it1.hasNext());
                Assert.assertTrue(it2.hasNext());

                i1[j] = it1.next();
                i2[j] = it2.next();
            }

            Assert.assertFalse(it1.hasNext());
            Assert.assertFalse(it2.hasNext());

            for (int j = 0; j < n; j++)
            {
                if (i1[j].intValue() != i2[j].intValue()) {
                    random = true;
                    break;
                }
            }

            if (random)
                break;
        }

        Assert.assertTrue(random);
    }
}