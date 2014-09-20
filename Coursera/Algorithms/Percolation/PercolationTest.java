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
    }

    /**
     * Test for percolate status.
     */
    @Test
    public void testPercolates() {
        Percolation percolation = new Percolation(2);
        Assert.assertFalse(percolation.percolates());

        percolation.open(1, 1);
        Assert.assertFalse(percolation.percolates());

        percolation.open(2, 2);
        Assert.assertFalse(percolation.percolates());

        percolation.open(1, 2);
        Assert.assertTrue(percolation.percolates());
    }
}