import java.util.Arrays;

public class Board {
    private final int[][] blocks;

    private final int n;

    public Board(int[][] blocks) {
        n = blocks.length;
        this.blocks = new int[n][n];
        for (int i = 0; i < n; i++) {
            System.arraycopy(blocks[i], 0, this.blocks[i], 0, n);
        }
    }

    public int dimension() {
        return this.n;
    }

    public int hamming() {
        int sum = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                // Without the last (empty) place.
                if (i == n - 1 && j == n - 1) {
                    break;
                }

                if (blocks[i][j] != i * n + j + 1) {
                    sum++;
                }
            }
        }
        return sum;
    }

    public int manhattan() {
        int sum = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                // Without the last (empty) place.
                if (blocks[i][j] == 0) {
                    continue;
                }

                int ti = (blocks[i][j] - 1) / n;
                int tj = blocks[i][j] - ti * n - 1;
                sum += Math.abs(i - ti) + Math.abs(j - tj);
            }
        }
        return sum;
    }

    public boolean isGoal() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == n - 1 && j == n - 1)
                    return blocks[i][j] == 0;

                if (blocks[i][j] != i * n + j + 1)
                    return false;
            }
        }
        return true;
    }

    public Board twin() {
        int ti = 0, tj = 0;
        boolean stop = false;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (blocks[i][j] == 0) {
                    ti = i;
                    tj = j;
                    stop = true;
                    break;
                }
            }

            if (stop) {
                break;
            }
        }

        if (n == 2) {
            if (ti == 0) {
                return clone(1, 0, 1, 1);
            } else {
                return clone(0, 0, 0, 1);
            }
        } else {
            if (ti == 0) {
                if (tj == 0) {
                    return clone(0, 1, 0, 2);
                } else if (tj == 1) {
                    return clone(1, 0, 1, 1);
                } else {
                    return clone(0, 0, 0, 1);
                }
            } else {
                return clone(0, 0, 0, 1);
            }
        }
    }

    public Iterable<Board> neighbors() {
        int ti = 0, tj = 0;
        boolean stop = false;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (blocks[i][j] == 0) {
                    ti = i;
                    tj = j;
                    stop = true;
                    break;
                }
            }

            if (stop) {
                break;
            }
        }

        Board[] neighbors;
        if (ti == 0 && tj == 0) {
            // top-left
            neighbors = new Board[] {
                clone(ti, tj, ti, tj + 1),  // right
                clone(ti, tj, ti + 1, tj)   // bottom
            };
        } else if (ti == 0 && tj == n - 1) {
            // top-right
            neighbors = new Board[] {
                clone(ti, tj, ti, tj - 1),  // left
                clone(ti, tj, ti + 1, tj)   // bottom
            };
        } else if (ti == n - 1 && tj == 0) {
            // bottom-left
            neighbors = new Board[] {
                clone(ti, tj, ti, tj + 1),  // right
                clone(ti, tj, ti - 1, tj)   // top
            };
        } else if (ti == n - 1 && tj == n - 1) {
            // bottom-right
            neighbors = new Board[] {
                clone(ti, tj, ti, tj - 1),  // left
                clone(ti, tj, ti - 1, tj)   // top
            };
        } else if (ti == 0) {
            // top
            neighbors = new Board[] {
                clone(ti, tj, ti + 1, tj),  // bottom
                clone(ti, tj, ti, tj + 1),  // right
                clone(ti, tj, ti, tj - 1)   // left
            };
        } else if (tj == 0) {
            // left
            neighbors = new Board[] {
                clone(ti, tj, ti, tj + 1),  // right
                clone(ti, tj, ti - 1, tj),  // top
                clone(ti, tj, ti + 1, tj)   // bottom
            };
        } else if (ti == n - 1) {
            // bottom
            neighbors = new Board[] {
                clone(ti, tj, ti - 1, tj),  // top
                clone(ti, tj, ti, tj + 1),  // right
                clone(ti, tj, ti, tj - 1)   // left
            };
        } else if (tj == n - 1) {
            // right
            neighbors = new Board[] {
                clone(ti, tj, ti, tj - 1),  // left
                clone(ti, tj, ti - 1, tj),  // top
                clone(ti, tj, ti + 1, tj)   // bottom
            };
        } else {
            neighbors = new Board[] {
                clone(ti, tj, ti - 1, tj),  // top
                clone(ti, tj, ti + 1, tj),  // bottom
                clone(ti, tj, ti, tj - 1),  // left
                clone(ti, tj, ti, tj + 1)   // right
            };
        }

        return Arrays.asList(neighbors);
    }

    private Board clone(int si, int sj, int ti, int tj) {
        int[][] data = new int[n][n];
        for (int i = 0; i < n; i++) {
            System.arraycopy(blocks[i], 0, data[i], 0, n);
        }

        int tmp = data[si][sj];
        data[si][sj] = data[ti][tj];
        data[ti][tj] = tmp;

        return new Board(data);
    }

    @Override
    public boolean equals(Object y) {
        if (y == null)
            return false;

        if (!(y instanceof Board))
            return false;

        Board other = (Board)y;
        if (n != other.n)
            return false;

        return Arrays.deepEquals(blocks, other.blocks);
    }

    @Override
    public String toString() {
        String result = dimension() + "\n";
        for (int i = 0; i < n; i++) {
            result += " ";
            for (int j = 0; j < n; j++) {
                result += blocks[i][j];
                if (j != n - 1)
                    result += "  ";
            }
            result += "\n";
        }
        return result;
    }
}
