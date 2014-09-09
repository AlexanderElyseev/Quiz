/**
 * Interface of data structure for Union-Find problem.
 */
interface IUnionFind {
    /**
     * Connects two elements.
     *
     * @param p The first element.
     * @param q The second element.
     */
    public void union(int p, int q);

    /**
     * Checks if two elements are connected.
     *
     * @param p The first element.
     * @param q The second element.
     *
     * @return True if elements are connected.
     */
    public boolean isConnected(int p, int q);
}