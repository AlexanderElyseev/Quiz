/**
 * Class of data structure for Union-Find problem
 * that implement naive quick-find algorithm.
 *
 * Init complexity: O(N).
 * Union complexity: O(N).
 * Find complexity: O(1).
 */
public class QuickFind implements IUnionFind {
    /**
     * Connected elements.
     * Same value of elements means connection.
     */
    private int[] _data;

    /**
     * Class constructor.
     * Initializes data structures.
     *
     * @param n The number of elements in set.
     */
    public QuickFind(int n)
    {
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
        int ip = _data[p];
        int iq = _data[q];
        for (int i = 0; i < _data.length; i++) {
            if (_data[i] == ip)
                _data[i] = iq;
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
        return _data[p] == _data[q];
    }
}
