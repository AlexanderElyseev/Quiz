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

        Point p9 = new Point(64, 11);
        Point p10 = new Point(371, 70);
        Assert.assertEquals(0.19218241042345277, p9.slopeTo(p10));
    }

    @Test
    public void testSlopeOrderComparator() {
        Point p = new Point(2, 230);
        Point q = new Point(356, 341);
        Point r = new Point(251, 375);
        Assert.assertEquals(-1, p.SLOPE_ORDER.compare(q, r));

        p = new Point(26054, 29224);
        q = new Point(5599, 3145);
        r = new Point(1032, 3871);
        Assert.assertEquals(1, p.SLOPE_ORDER.compare(q, r));

        p = new Point(7, 9);
        q = new Point(1, 7);
        r = new Point(1, 6);
        Assert.assertEquals(-1, p.SLOPE_ORDER.compare(q, r));
    }
}