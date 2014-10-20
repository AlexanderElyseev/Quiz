import org.junit.Assert;
import org.junit.Test;

public class BoardTest {
    @Test
    public void testDimension() {
        Assert.assertEquals(new Board(new int[][] { {1,2}, {3,4} }).dimension(), 2);
    }

    @Test
    public void testHamming() {
        Assert.assertEquals(5, new Board(new int[][] { {8,1,3}, {4,0,2}, {7,6,5} }).hamming());
    }

    @Test
    public void testManhattan() {
        Assert.assertEquals(10, new Board(new int[][] { {8,1,3}, {4,0,2}, {7,6,5} }).manhattan());
        Assert.assertEquals(4, new Board(new int[][] { {0,1,3}, {4,2,5}, {7,8,6} }).manhattan());
    }

    @Test
    public void testIsGoal() {
        Assert.assertTrue(new Board(new int[][] { {1,2}, {3,0} }).isGoal());
        Assert.assertFalse(new Board(new int[][] { {2,0}, {1,3} }).isGoal());
        Assert.assertFalse(new Board(new int[][] { {1,2}, {3,4} }).isGoal());
    }

    @Test
    public void testEquals() {
        Board a = new Board(new int[][] { {1,2}, {3,4} });
        Board b = new Board(new int[][] { {1,2}, {3,4} });
        Board c = new Board(new int[][] { {2,1}, {3,4} });

        Assert.assertFalse(a.equals(null));
        Assert.assertTrue(a.equals(b));
        Assert.assertTrue(b.equals(a));
        Assert.assertFalse(a.equals(c));
        Assert.assertFalse(c.equals(a));
    }

    @Test
    public void testToString() {
        Assert.assertEquals(new Board(new int[][] { {1,2}, {3,4} }).toString(), "2\n 1  2\n 3  4\n");
    }
}