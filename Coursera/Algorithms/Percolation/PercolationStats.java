public class PercolationStats {
    /**
     * Probability of percolation of each experiment.
     */
    private double[] experiments;

    /**
     * Cached value of standard deviation of percolation threshold.
     */
    private double stddev = -1;

    /**
     * Cached value of mean of percolation threshold.
     */
    private double mean = -1;

    /**
     * perform T independent computational experiments on an N-by-N grid
     *
     * @param sitesOnOneSide   Count of sites on one side of the grid.
     * @param experimentsCount Count of launches of experiment.
     */
    public PercolationStats(int sitesOnOneSide, int experimentsCount) {
        if (sitesOnOneSide <= 0)
            throw new IllegalArgumentException();

        if (experimentsCount <= 0)
            throw new IllegalArgumentException();

        experiments = new double[experimentsCount];
        for (int experimentIndex = 0; experimentIndex < experimentsCount; experimentIndex++) {
            Percolation percolation = new Percolation(sitesOnOneSide);
            int openedSites = 0;
            while (!percolation.percolates()) {
                // Open random site.
                int x, y;
                while (true) {
                    x = StdRandom.uniform(1, sitesOnOneSide + 1);
                    y = StdRandom.uniform(1, sitesOnOneSide + 1);

                    if (percolation.isOpen(x, y))
                        continue;

                    percolation.open(x, y);
                    break;
                }

                openedSites++;
            }

            experiments[experimentIndex] = (double) openedSites / (sitesOnOneSide * sitesOnOneSide);
        }
    }

    /**
     * Returns mean of percolation threshold.
     *
     * @return Mean of percolation threshold.
     */
    public double mean() {
        if (mean < 0)
            mean = StdStats.mean(experiments);

        return mean;
    }

    /**
     * Returns standard deviation of percolation threshold.
     *
     * @return Standard deviation of percolation threshold.
     */
    public double stddev() {
        if (stddev < 0)
            stddev = StdStats.stddev(experiments);

        return stddev;
    }

    /**
     * Returns lower bound of the 95% confidence interval.
     *
     * @return Lower bound of the 95% confidence interval.
     */
    public double confidenceLo() {
        return mean() - 1.96 * stddev() / Math.sqrt(experiments.length);
    }

    /**
     * Returns upper bound of the 95% confidence interval.
     *
     * @return Upper bound of the 95% confidence interval
     */
    public double confidenceHi() {
        return mean() + 1.96 * stddev() / Math.sqrt(experiments.length);
    }

    /**
     * Program entry point.
     *
     * Arguments:
     * [0]: count of sites on one side of the grid;
     * [1]: count of launches;
     *
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        int sitesOnOneSide = Integer.parseInt(args[0]);
        int experimentsCount = Integer.parseInt(args[1]);

        PercolationStats stats = new PercolationStats(sitesOnOneSide, experimentsCount);

        System.out.println("mean                    = " + stats.mean());
        System.out.println("stddev                  = " + stats.stddev());
        System.out.println("95% confidence interval = " + stats.confidenceLo() + ", " + stats.confidenceHi());
    }
}
