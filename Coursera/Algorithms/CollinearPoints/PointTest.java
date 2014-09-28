import junit.framework.Assert;
import org.junit.Test;

public class PointTest {
    @Test
    public void testNaturalCompareTo() {
        Point p1 = new Point(0, 0);
        Point p2 = new Point(0, 0);
        Assert.assertEquals(0, p1.compareTo(p2));
        Assert.assertEquals(0, p2.compareTo(p1));

        Point p3 = new Point(1, 1);
        Assert.assertTrue(p1.compareTo(p3) < 0);
        Assert.assertTrue(p3.compareTo(p1) > 0);

        Point p4 = new Point(-1, -1);
        Assert.assertTrue(p1.compareTo(p4) > 0);
        Assert.assertTrue(p4.compareTo(p1) < 0);

        Point p5 = new Point(0, 1);
        Assert.assertTrue(p1.compareTo(p5) < 0);
        Assert.assertTrue(p5.compareTo(p1) > 0);

        Point p6 = new Point(0, -1);
        Assert.assertTrue(p1.compareTo(p6) > 0);
        Assert.assertTrue(p6.compareTo(p1) < 0);
    }

    @Test
    public void testSlopeTo() {
        Point p1 = new Point(0, 0);
        Point p2 = new Point(0, 0);
        Assert.assertEquals(Double.NEGATIVE_INFINITY, p1.slopeTo(p2));
        Assert.assertEquals(Double.NEGATIVE_INFINITY, p2.slopeTo(p1));

        Point p3 = new Point(0, 1);
        Point p4 = new Point(0, 2);
        Assert.assertEquals(Double.POSITIVE_INFINITY, p3.slopeTo(p4));
        Assert.assertEquals(Double.POSITIVE_INFINITY, p4.slopeTo(p3));

        Point p5 = new Point(1, 0);
        Point p6 = new Point(2, 0);
        Assert.assertEquals(0.0, p5.slopeTo(p6));
        Assert.assertEquals(0.0, p6.slopeTo(p5));

        Point p7 = new Point(1, 1);
        Point p8 = new Point(2, 2);
        Assert.assertEquals(p8.slopeTo(p7), p7.slopeTo(p8));
        Assert.assertEquals(1.0, p7.slopeTo(p8));
    }
}