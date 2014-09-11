import java.util.Arrays;

/**
 * Class of data structure for Union-Find problem
 * that implement naive quick-union algorithm.
 *
 * Init complexity: O(N).
 * Union complexity: O(N) (worst).
 * Find complexity: O(N) (worst).
 */
public class WeighedQuickUnion implements IUnionFind {
    /**
     * Compact tree path.
     */
    private final boolean _compact;

    /**
     * Connected elements.
     * Value of element is the index of root element.
     */
    private int[] _data;

    /**
     * Count of elements in trees.
     */
    private int[] _sizes;

    /**
     * Class constructor.
     * Initializes data structures.
     *
     * @param n       The number of elements in set.
     * @param compact Compact tree path.
     */
    public WeighedQuickUnion(int n, boolean compact) {
        _compact = compact;
        _data = new int[n];
        for (int i = 0; i < n; i++) {
            _data[i] = i;
        }

        _sizes = new int[n];
        Arrays.fill(_sizes, 1); // Without initial value sizes will be always 0.
    }

    /**
     * Connects two elements.
     *
     * @param p The first element.
     * @param q The second element.
     */
    @Override
    public void union(int p, int q) {
        // Connecting roots (smaller to bigger).
        // Increasing size of bigger tree.
        int rootQ = getRoot(q);
        int rootP = getRoot(p);
        if (_sizes[p] >= _sizes[q]) {
            _data[rootQ] = rootP;
            _sizes[rootP] += _sizes[rootQ];
        } else {
            _data[rootP] = rootQ;
            _sizes[rootQ] += _sizes[rootP];
        }
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

    @Override
    public String toString() {
        return "WeighedQuickUnion { Data=" + Arrays.toString(_data) + ", Sizes=" + Arrays.toString(_sizes) + " }";
    }

    /**
     * Loads the index of the root element for specified element.
     *
     * @param i The index of the element.
     *
     * @return The index of root element.
     */
    private int getRoot(int i) {
        while (_data[i] != i) {

            if (_compact)
                _data[i] = _data[_data[i]]; // Moving element up to the root.

            i = _data[i];
        }

        return i;
    }
}
