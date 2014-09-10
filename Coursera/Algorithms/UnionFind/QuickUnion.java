/**
 * Class of data structure for Union-Find problem
 * that implement naive quick-union algorithm.
 *
 * Init complexity: O(N).
 * Union complexity: O(N) (worst).
 * Find complexity: O(N) (worst).
 */
public class QuickUnion implements IUnionFind {
    /**
     * Connected elements.
     * Value of element is the index of root element.
     */
    private int[] _data;

    /**
     * Class constructor.
     * Initializes data structures.
     *
     * @param n The number of elements in set.
     */
    public QuickUnion(int n) {
        _data = new int[n];
        for (int i = 0; i < n; i++) {
            _data[i] = i;
        }
    }

    /**
     * Connects two elements.
     *
     * @param p The first element.
     * @param q The second element.
     */
    @Override
    public void union(int p, int q) {
        // Connecting roots.
        _data[getRoot(q)] = getRoot(p);
    }

    /**
     * Checks if two elements are connected.
     *
     * @param p The first element.
     * @param q The second element.
     *
     * @return True if elements are connected.
     */
    @Override
    public boolean isConnected(int p, int q) {
        return getRoot(p) == getRoot(q);
    }

    /**
     * Loads the index of the root element for specified element.
     *
     * @param i The index of the element.
     * @return The index of root element.
     */
    private int getRoot(int i) {
        while (_data[i] != i)
            i = _data[i];

        return i;
    }
}
