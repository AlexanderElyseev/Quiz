public class Percolation {
    /**
     * Grid with sites.
     */
    private final boolean[][] sites;

    /**
     * UnionFind data structure.
     */
    private final WeightedQuickUnionUF UF;

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
        UF = new WeightedQuickUnionUF(n * n + 2);

        // Connect top and bottom sides with imagine elements.
        TOP_IMAGINE_ELEMENT_INDEX = n * n;
        for (int j = 0; j < n; j++) {
            UF.union(j, TOP_IMAGINE_ELEMENT_INDEX);
        }

        BOTTOM_IMAGINE_ELEMENT_INDEX = TOP_IMAGINE_ELEMENT_INDEX + 1;
        for (int j = 0; j < n; j++) {
            UF.union((n - 1) * n + j, BOTTOM_IMAGINE_ELEMENT_INDEX);
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
        int index = realI * N + realJ;
        if (realI != 0 && sites[realI - 1][realJ])
            UF.union((realI - 1) * N + realJ, index);

        if (realI + 1 != N && sites[realI + 1][realJ])
            UF.union((realI + 1) * N + realJ, index);

        if (realJ != 0 && sites[realI][realJ - 1])
            UF.union(index - 1, index);

        if (realJ + 1 != N && sites[realI][realJ + 1])
            UF.union(index + 1, index);

        // Connect with top side or bottom side.
        if (realI == 0)
            UF.union(TOP_IMAGINE_ELEMENT_INDEX, index);

        if (realI == N - 1)
            UF.union(BOTTOM_IMAGINE_ELEMENT_INDEX, index);
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
     * Is site (row i, column j) full (open and connected with top side)?
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

        return sites[realI][realJ] && UF.connected(TOP_IMAGINE_ELEMENT_INDEX, N * realI + realJ);
    }

    /**
     * Does the system percolate?
     *
     * @return True if the system percolates.
     */
    public boolean percolates() {
        return UF.connected(TOP_IMAGINE_ELEMENT_INDEX, BOTTOM_IMAGINE_ELEMENT_INDEX);
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