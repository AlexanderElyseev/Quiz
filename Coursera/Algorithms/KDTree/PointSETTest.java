import junit.framework.Assert;
import org.junit.Test;

import java.util.Random;

public class PointSETTest {
    @Test
    public void TestIsEmpty() {
        PointSET set = new PointSET();
        Assert.assertTrue(set.isEmpty());

        set.insert(new Point2D(0, 0));
        Assert.assertFalse(set.isEmpty());
    }

    @Test
    public void TestSize() {
        PointSET set = new PointSET();
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
        PointSET set = new PointSET();
        Random random = new Random();
        for (int i = 0; i < 1000; i++) {
            Assert.assertEquals(i, set.size());
            set.insert(new Point2D(random.nextDouble(), random.nextDouble()));
        }
    }

    @Test
    public void TestContains() {
        PointSET set = new PointSET();
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
        PointSET set = new PointSET();

        int itemsCount = 0;
        for (Point2D ignored : set.range(new RectHV(0, 0, 100, 100))) {
            itemsCount++;
        }

        Assert.assertEquals(0, itemsCount);
     }

    @Test
    public void TestRange() {
        PointSET set = new PointSET();
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
        PointSET set = new PointSET();
        Point2D nearest = set.nearest(new Point2D(0, 0));
        Assert.assertNull(nearest);
    }

    @Test
    public void TestNearest() {
        PointSET set = new PointSET();

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
        PointSET set = new PointSET();
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
}