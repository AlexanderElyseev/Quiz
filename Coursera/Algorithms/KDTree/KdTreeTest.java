import junit.framework.Assert;
import org.junit.Test;

import java.util.Random;

public class KdTreeTest {
    @Test
    public void TestIsEmpty() {
        KdTree set = new KdTree();
        Assert.assertTrue(set.isEmpty());

        set.insert(new Point2D(0, 0));
        Assert.assertFalse(set.isEmpty());
    }

    @Test
    public void TestSize() {
        KdTree set = new KdTree();
        Assert.assertEquals(0, set.size());

        set.insert(new Point2D(0, 0));
        Assert.assertEquals(1, set.size());

        set.insert(new Point2D(0, 0));
        Assert.assertEquals(1, set.size());

        set.insert(new Point2D(1, 1));
        Assert.assertEquals(2, set.size());

        set.insert(new Point2D(1, 1));
        Assert.assertEquals(2, set.size());

        set.insert(new Point2D(0, 0));
        Assert.assertEquals(2, set.size());
    }

    @Test
    public void TestSizeForRandomSet() {
        KdTree set = new KdTree();
        Random random = new Random();
        for (int i = 0; i < 1000; i++) {
            Assert.assertEquals(i, set.size());
            set.insert(new Point2D(random.nextDouble(), random.nextDouble()));
        }
    }

    @Test
    public void TestContains() {
        KdTree set = new KdTree();
        Assert.assertFalse(set.contains(new Point2D(0, 0)));

        set.insert(new Point2D(0, 0));
        Assert.assertTrue(set.contains(new Point2D(0, 0)));

        Random random = new Random();
        for (int i = 0; i < 100; i++) {
            Point2D point = new Point2D(random.nextDouble(), random.nextDouble());
            set.insert(point);
            Assert.assertTrue(set.contains(point));
        }
    }

    @Test
    public void TestEmptyRange() {
        KdTree set = new KdTree();

        int itemsCount = 0;
        for (Point2D ignored : set.range(new RectHV(0, 0, 100, 100))) {
            itemsCount++;
        }

        Assert.assertEquals(0, itemsCount);
    }

    @Test
    public void TestRange() {
        KdTree set = new KdTree();
        set.insert(new Point2D(0, 0));
        set.insert(new Point2D(1, 1));
        set.insert(new Point2D(2, 2));
        set.insert(new Point2D(101, 101));

        int itemsCount = 0;
        for (Point2D ignored : set.range(new RectHV(0, 0, 100, 100))) {
            itemsCount++;
        }

        Assert.assertEquals(3, itemsCount);
    }

    @Test
    public void TestNullNearest() {
        KdTree set = new KdTree();
        Point2D nearest = set.nearest(new Point2D(0, 0));
        Assert.assertNull(nearest);
    }

    @Test
    public void TestNearest() {
        KdTree set = new KdTree();

        Random random = new Random();

        Point2D target = new Point2D(random.nextDouble(), random.nextDouble());

        double nearestDistance = Double.POSITIVE_INFINITY;
        Point2D nearestPoint = null;

        for (int i = 0; i < 1000; i++) {
            Point2D point = new Point2D(random.nextDouble(), random.nextDouble());
            double distance = target.distanceTo(point);
            if (distance < nearestDistance) {
                nearestDistance = distance;
                nearestPoint = point;
            }

            set.insert(point);
        }

        Assert.assertEquals(nearestPoint, set.nearest(target));
    }

    @Test
    public void TestNearest1() {
        KdTree set = new KdTree();
        set.insert(new Point2D(0.40, 0.67));
        set.insert(new Point2D(0.74, 0.02));
        set.insert(new Point2D(0.01, 0.74));
        set.insert(new Point2D(0.01, 0.95));
        set.insert(new Point2D(0.14, 0.20));
        set.insert(new Point2D(0.53, 0.43));
        set.insert(new Point2D(0.97, 0.05));
        set.insert(new Point2D(0.90, 0.67));
        set.insert(new Point2D(0.87, 0.95));
        set.insert(new Point2D(0.37, 0.16));

        Assert.assertEquals(new Point2D(0.37, 0.16), set.nearest(new Point2D(0.45, 0.2)));
    }

    @Test
    public void TestNearest2() {
        KdTree set = new KdTree();
        set.insert(new Point2D(0.7, 0.1));
        set.insert(new Point2D(0.2, 0.3));
        set.insert(new Point2D(0.8, 0.8));

        Assert.assertEquals(new Point2D(0.8, 0.8), set.nearest(new Point2D(0.6, 0.8)));
    }
}