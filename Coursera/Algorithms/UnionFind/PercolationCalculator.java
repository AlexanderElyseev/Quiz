import java.util.Random;

/**
 * Program for calculating likelihood of percolation in
 * two-dimensional area using Union-Find algorithms.
 */
public class PercolationCalculator {
    /**
     * The number of experiments.
     */
    public static final int ITERATIONS = 10;

    /**
     * The number of elements in each side.
     */
    public static final int N = 1000;

    /**
     * Program entry point.
     * Launches calculations.
     *
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();

        Random rand = new Random();
        double sum = 0;
        for (int i = 0; i < ITERATIONS; i++) {
            IUnionFind uf = buildDataStructure(args.length > 0 ? args[0] : null);

            // Open sites.
            boolean[][] sites = new boolean[N][N];

            // Connect top and bottom sides with imagine elements.
            int topImagineElementIndex = N * N;
            for (int j = 0; j < N; j++) {
                sites[0][j] = true;
                uf.union(j, topImagineElementIndex);
            }

            int bottomImagineElementIndex = topImagineElementIndex + 1;
            for (int j = 0; j < N; j++) {
                sites[N - 1][j] = true;
                uf.union((N - 1) * N + j, bottomImagineElementIndex);
            }

            int openSites = 0;
            while (!uf.isConnected(topImagineElementIndex, bottomImagineElementIndex)) {
                // Mark new open site.
                int x;
                int y;
                while (true) {
                    x = rand.nextInt(N);
                    y = rand.nextInt(N);

                    if (sites[x][y])
                        continue;

                    sites[x][y] = true;
                    break;
                }

                // Connect with nearest open sites.
                if (x != 0 && sites[x - 1][y])
                    uf.union((x - 1) * N + y, x * N + y);

                if (x + 1 != N && sites[x + 1][y])
                    uf.union((x + 1) * N + y, x * N + y);

                if (y != 0 && sites[x][y - 1])
                    uf.union(x * N + y - 1, x * N + y);

                if (y + 1 != N && sites[x][y + 1])
                    uf.union(x * N + y + 1, x * N + y);

                openSites++;

//                printSites(sites);
            }

            sum += (double)openSites / (N * N);
        }

        System.out.println("Estimate probability: " + sum / ITERATIONS);

        long endTime = System.currentTimeMillis();
        System.out.println("Total execution time: " + (endTime - startTime) / 1000.0 + " s.");
    }

    /**
     * Builds UnionFind data structure by the name.
     *
     * @param name The name of data structure.
     * @return Appropriate UnionFind data structure.
     */
    private static IUnionFind buildDataStructure(String name) {
        if ("WeighedQuickUnion".equals(name))
            return new WeighedQuickUnion(N * N + 2, false);

        if ("WeighedQuickUnionWithPathCompress".equals(name))
            return new WeighedQuickUnion(N * N + 2, true);

        if ("QuickUnion".equals(name))
            return new QuickUnion(N * N + 2);

        if ("QuickFind".equals(name))
            return new QuickFind(N * N + 2);

        throw new IllegalArgumentException("Undefined UnionFind data structure name.");
    }

    /**
     * Prints two-dimensional array of sites.
     * Helps visualize open sites.
     *
     * @param sites Array of sites.
     */
    private static void printSites(boolean[][] sites) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++)
                System.out.print(sites[i][j] ? 'x' : ' ');

            System.out.println();
        }

        System.out.println();
    }
}
