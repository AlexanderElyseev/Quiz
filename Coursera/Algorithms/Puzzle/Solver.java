import java.util.ArrayList;
import java.util.List;

public class Solver {

    private class GameNode implements Comparable<GameNode> {
        public Board board;
        public int step;
        public int hamming;
        public int manhattan;

        public GameNode(Board board, int step, int hamming, int manhattan) {
            this.board = board;
            this.step = step;
            this.hamming = hamming;
            this.manhattan = manhattan;
        }

        @Override
        public int compareTo(GameNode o) {
            return Integer.compare(
                step + hamming + manhattan,
                o.step + o.hamming + o.manhattan
            );
        }
    }

    private final MinPQ<GameNode> initialQueue = new MinPQ<GameNode>();
    private final MinPQ<GameNode> twinQueue = new MinPQ<GameNode>();

    private List<Board> solution;

    private boolean solvable = true;

    public Solver(Board initial) {
        initialQueue.insert(new GameNode(initial, 0, initial.hamming(), initial.manhattan()));

        Board twin = initial.twin();
        twinQueue.insert(new GameNode(twin, 0, twin.hamming(), twin.manhattan()));
    }

    public boolean isSolvable() {
        solve();
        return solvable;
    }

    public int moves() {
        solve();
        return solution.size() - 1;
    }

    public Iterable<Board> solution() {
        solve();
        return solvable ? solution : null;
    }

    private void solve() {
        if (solution != null) {
            return;
        }

        solution = new ArrayList<Board>();

        Board previousInitialBoard = null;
        Board previousTwinBoard = null;

        while (true) {
            GameNode minInitialNode = initialQueue.delMin();
            Board minInitialBoard = minInitialNode.board;

            GameNode minTwinNode = twinQueue.delMin();
            Board minTwinBoard = minTwinNode.board;

            if (minTwinBoard.isGoal()) {
                solution.clear();
                solvable = false;
                return;
            }

            solution.add(minInitialBoard);

            if (minInitialBoard.isGoal()) {
                break;
            }

            int nextInitialStep = minInitialNode.step + 1;
            for (Board neighbor : minInitialBoard.neighbors()) {
                if (neighbor.equals(previousInitialBoard)) {
                    continue;
                }

                GameNode neighborNode = new GameNode(neighbor, nextInitialStep, neighbor.hamming(), neighbor.manhattan());
                initialQueue.insert(neighborNode);
            }

            previousInitialBoard = minInitialBoard;

            int nextTwinStep = minTwinNode.step + 1;
            for (Board neighbor : minTwinBoard.neighbors()) {
                if (neighbor.equals(previousTwinBoard)) {
                    continue;
                }

                GameNode neighborNode = new GameNode(neighbor, nextTwinStep, neighbor.hamming(), neighbor.manhattan());
                twinQueue.insert(neighborNode);
            }

            previousTwinBoard = minTwinBoard;
        }
    }

    public static void main(String[] args) {
        In in = new In(args[0]);
        int N = in.readInt();
        int[][] blocks = new int[N][N];
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                blocks[i][j] = in.readInt();

        Board initial = new Board(blocks);

        Solver solver = new Solver(initial);

        if (!solver.isSolvable())
            StdOut.println("No solution possible");
        else {
            StdOut.println("Minimum number of moves = " + solver.moves());
            for (Board board : solver.solution())
                StdOut.println(board);
        }
    }
}
