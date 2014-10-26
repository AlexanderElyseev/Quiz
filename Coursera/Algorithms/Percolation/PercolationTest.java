import org.junit.Assert;
import org.junit.Test;

/**
 * Class with unit-tests for Percolation class.
 */
public class PercolationTest {
    /**
     * Test method for constructor.
     */
    @Test
    public void testConstruct() {
        try {
            new Percolation(-1);
        } catch (IllegalArgumentException ignored) {
        } catch (Exception e) {
            Assert.fail();
        }

        try {
            new Percolation(0);
        } catch (IllegalArgumentException ignored) {
        } catch (Exception e) {
            Assert.fail();
        }
    }

    /**
     * Test for site opening action.
     */
    @Test
    public void testOpen() {
        Percolation percolation = new Percolation(1);
        percolation.open(1, 1);
        percolation.open(1, 1);
    }

    /**
     * Test for site open status.
     */
    @Test
    public void testIsOpen() {
        testIsOpenIndexException(1, 0, 0, true);
        testIsOpenIndexException(1, 1, 0, true);
        testIsOpenIndexException(1, 0, 1, true);
        testIsOpenIndexException(1, 1, 1, false);
        testIsOpenIndexException(1, 2, 0, true);
        testIsOpenIndexException(1, 0, 2, true);
        testIsOpenIndexException(1, 2, 2, true);

        Percolation percolation = new Percolation(2);
        Assert.assertFalse(percolation.isOpen(1, 1));
        Assert.assertFalse(percolation.isOpen(1, 2));
        Assert.assertFalse(percolation.isOpen(2, 1));
        Assert.assertFalse(percolation.isOpen(2, 2));
    }

    /**
     * Test for IndexOutOfBoundsException in checking site open status.
     *
     * @param n                    Count of sites on one side of the grid.
     * @param i                    Row index for test.
     * @param j                    Column index for test.
     * @param expectIndexException Is exception is expected.
     */
    private void testIsOpenIndexException(int n, int i, int j, boolean expectIndexException) {
        try {
            new Percolation(n).isOpen(i, j);
        } catch (IndexOutOfBoundsException ignored) {
            if (!expectIndexException)
                Assert.fail();
        } catch (Exception ignored) {
            Assert.fail();
        }
    }

    /**
     * Test for site full status.
     */
    @Test
    public void testIsFull() {
        Percolation percolation = new Percolation(2);
        Assert.assertFalse(percolation.isFull(1, 1));
        Assert.assertFalse(percolation.isFull(1, 2));
        Assert.assertFalse(percolation.isFull(2, 1));
        Assert.assertFalse(percolation.isFull(2, 2));

        percolation.open(1, 1);
        Assert.assertTrue(percolation.isFull(1, 1));

        percolation.open(2, 2);
        Assert.assertFalse(percolation.isFull(2, 2));

        percolation.open(1, 2);
        Assert.assertTrue(percolation.isFull(2, 2));
    }

    /**
     * Test for 1x1 grid.
     * Initial grid doesn't percolate.
     * Percolates after opening the first (single) element.
     */
    @Test
    public void testPercolates1() {
        Percolation percolation = new Percolation(1);
        Assert.assertFalse(percolation.percolates());

        percolation.open(1, 1);
        Assert.assertTrue(percolation.percolates());
    }

    /**
     * Test for 2x2 grid.
     * Initial grid doesn't percolate.
     */
    @Test
    public void testPercolates2() {
        Percolation percolation = new Percolation(2);
        Assert.assertFalse(percolation.percolates());

        percolation.open(1, 1);
        Assert.assertFalse(percolation.percolates());

        percolation.open(2, 2);
        Assert.assertFalse(percolation.percolates());

        percolation.open(1, 2);
        Assert.assertTrue(percolation.percolates());
    }

    /**
     * Test for 3x3 grid.
     */
    @Test
    public void testPercolates3() {
        Percolation percolation = new Percolation(3);
        Assert.assertFalse(percolation.percolates());

        percolation.open(1, 1);
        Assert.assertFalse(percolation.percolates());

        percolation.open(2, 2);
        Assert.assertFalse(percolation.percolates());

        percolation.open(3, 3);
        Assert.assertFalse(percolation.percolates());

        percolation.open(2, 1);
        Assert.assertFalse(percolation.percolates());

        percolation.open(2, 3);
        Assert.assertTrue(percolation.percolates());
    }

    /**
     * Test for 3x3 grid.
     */
    @Test
    public void testBackWash() {
        Percolation percolation = new Percolation(3);
        percolation.open(1, 3);
        percolation.open(2, 3);
        percolation.open(3, 3);
        Assert.assertTrue(percolation.percolates());
        Assert.assertFalse(percolation.isFull(3, 1));

        percolation.open(3, 1);
        Assert.assertFalse(percolation.isFull(3, 1));
    }
}