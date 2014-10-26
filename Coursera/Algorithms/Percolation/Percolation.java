/**
 * Class for solving percolation problem.
 *
 * http://en.wikipedia.org/wiki/Percolation_theory
 */
public class Percolation {
    /**
     * Grid with sites.
     */
    private final boolean[][] sites;

    /**
     * UnionFind data structure with "backwash" problem.
     *
     * Contains n*n + 2*n + 2 elements:
     * -> n * n - sites grid;
     * -> 2n    - imaginary sides;
     * -> 2     - imaginary top and bottom;
     */
    private final WeightedQuickUnionUF unionFindDataWithBackWash;

    /**
     * UnionFind data structure without "backwash" problem.
     * Not connected to the bottom element.
     *
     * Contains n*n + 2*n + 2 elements:
     * -> n * n - sites grid;
     * -> 2n    - imaginary sides;
     * -> 1     - imaginary bottom;
     */
    private final WeightedQuickUnionUF unionFindDataWithoutBackWash;

    /**
     * Count of sites in one side.
     */
    private final int N;

    /**
     * Index of top virtual element.
     */
    private final int TOP_IMAGINE_ELEMENT_INDEX;

    /**
     * Index of bottom virtual element.
     */
    private final int BOTTOM_IMAGINE_ELEMENT_INDEX;

    /**
     * Class constructor.
     * Creates n-by-n grid, with all sites blocked.
     *
     * @param n Count of sites on one side (n > 0).
     */
    public Percolation(int n) {
        if (n <= 0)
            throw new IllegalArgumentException();

        N = n;
        sites = new boolean[n][n];
        unionFindDataWithBackWash = new WeightedQuickUnionUF(n * n + 2 * n + 2);
        unionFindDataWithoutBackWash = new WeightedQuickUnionUF(n * n + 2 * n + 2);

        // Connect top and bottom sides with imagine elements.
        TOP_IMAGINE_ELEMENT_INDEX = n * n + 2 * n;
        BOTTOM_IMAGINE_ELEMENT_INDEX = TOP_IMAGINE_ELEMENT_INDEX + 1;
        for (int j = 0; j < n; j++) {
            int imaginaryTopIndex = n * n + j;
            int imaginaryBottomIndex = n * n + n + j;

            unionFindDataWithBackWash.union(imaginaryTopIndex, TOP_IMAGINE_ELEMENT_INDEX);
            unionFindDataWithBackWash.union(imaginaryBottomIndex, BOTTOM_IMAGINE_ELEMENT_INDEX);

            unionFindDataWithoutBackWash.union(imaginaryTopIndex, TOP_IMAGINE_ELEMENT_INDEX);
        }
    }

    /**
     * Opens site (row i, column j) if it is not already.
     *
     * @param i Row index of the site (1 <= i <= N).
     * @param j Column index of the site (1 <= j <= N).
     */
    public void open(int i, int j) {
        checkIndexes(i, j);
        int realI = i - 1;
        int realJ = j - 1;

        if (sites[realI][realJ])
            return;

        sites[realI][realJ] = true;

        // Connect with neighbour open sites.
        int index = index(realI, realJ);
        if (realI != 0 && sites[realI - 1][realJ]) {
            unionFindDataWithBackWash.union(index(realI - 1, realJ), index);
            unionFindDataWithoutBackWash.union(index(realI - 1, realJ), index);
        }

        if (realI + 1 != N && sites[realI + 1][realJ]) {
            unionFindDataWithBackWash.union(index(realI + 1, realJ), index);
            unionFindDataWithoutBackWash.union(index(realI + 1, realJ), index);
        }

        if (realJ != 0 && sites[realI][realJ - 1]) {
            unionFindDataWithBackWash.union(index - 1, index);
            unionFindDataWithoutBackWash.union(index - 1, index);
        }

        if (realJ + 1 != N && sites[realI][realJ + 1]) {
            unionFindDataWithBackWash.union(index + 1, index);
            unionFindDataWithoutBackWash.union(index + 1, index);
        }

        // Connect with top side or bottom side.
        if (realI == 0) {
            unionFindDataWithBackWash.union(TOP_IMAGINE_ELEMENT_INDEX, index);
            unionFindDataWithoutBackWash.union(TOP_IMAGINE_ELEMENT_INDEX, index);
        }

        if (realI == N - 1) {
            unionFindDataWithBackWash.union(BOTTOM_IMAGINE_ELEMENT_INDEX, index);
        }
    }

    /**
     * Gets the index of the specified element in the union-find structure.
     *
     * @param realI Row index of the site (0 <= i < N).
     * @param realJ Column index of the site (0 <= j < N).
     *
     * @return The index of the element in the union-find structure.
     */
    private int index(int realI, int realJ) {
        return realI * N + realJ;
    }

    /**
     * Is site (row i, column j) open?
     *
     * @param i Row index of the site (1 <= i <= N).
     * @param j Column index of the site (1 <= j <= N).
     *
     * @return True if the site is open.
     */
    public boolean isOpen(int i, int j) {
        checkIndexes(i, j);

        return sites[i - 1][j - 1];
    }

    /**
     * Is site (row i, column j) full (open and connected with top side).
     *
     * @param i Row index of the site (1 <= i <= N).
     * @param j Column index of the site (1 <= j <= N).
     *
     * @return True if the site is full.
     */
    public boolean isFull(int i, int j) {
        checkIndexes(i, j);
        int realI = i - 1;
        int realJ = j - 1;

        return sites[realI][realJ] && unionFindDataWithoutBackWash.connected(TOP_IMAGINE_ELEMENT_INDEX, index(realI, realJ));
    }

    /**
     * Does the system percolate?
     *
     * @return True if the system percolates.
     */
    public boolean percolates() {
        return unionFindDataWithBackWash.connected(TOP_IMAGINE_ELEMENT_INDEX, BOTTOM_IMAGINE_ELEMENT_INDEX);
    }

    /**
     * Checks indexes of row and column.
     * Throw IndexOutOfBoundsException
     *
     * @param i Row index of the site (1 <= i <= N).
     * @param j Column index of the site (1 <= j <= N).
     *
     * @throws IndexOutOfBoundsException Exception is trowed if some index is incorrect.
     */
    private void checkIndexes(int i, int j) {
        if (i <= 0 || i > N || j <= 0 || j > N)
            throw new IndexOutOfBoundsException();
    }
}