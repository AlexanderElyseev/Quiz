import junit.framework.Assert;
import org.junit.Test;

import java.util.Iterator;

public class SolverTest {
    @Test
    public void testSolution() {
        Board initial = new Board(new int[][] { { 0, 1, 3 }, { 4, 2, 5 }, { 7, 8, 6 } });
        Solver solver = new Solver(initial);

        Assert.assertTrue(solver.isSolvable());

        Iterator<Board> iterator = solver.solution().iterator();

        Board board1 = iterator.next();
        Assert.assertEquals(initial, board1);

        Board board2 = iterator.next();
        Assert.assertEquals(new Board(new int[][] { { 1, 0, 3 }, { 4, 2, 5 }, { 7, 8, 6 } }), board2);

        Board board3 = iterator.next();
        Assert.assertEquals(new Board(new int[][] { { 1, 2, 3 }, { 4, 0, 5 }, { 7, 8, 6 } }), board3);

        Board board4 = iterator.next();
        Assert.assertEquals(new Board(new int[][] { { 1, 2, 3 }, { 4, 5, 0 }, { 7, 8, 6 } }), board4);

        Board board5 = iterator.next();
        Assert.assertEquals(new Board(new int[][] { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 0 } }), board5);

        Assert.assertEquals(4, solver.moves());
    }

    @Test
    public void testUnsolvable2x2() {
        Board initial = new Board(new int[][] { { 2, 1 }, { 3, 0 } });
        Solver solver = new Solver(initial);

        Assert.assertFalse(solver.isSolvable());
    }

    @Test
    public void testUnsolvable3x3() {
        Board initial = new Board(new int[][] { { 0, 1, 3 }, { 2, 4, 6 }, { 7, 5, 8 } });
        Solver solver = new Solver(initial);

        Assert.assertFalse(solver.isSolvable());
    }

    @Test
    public void testUnsolvable4x4() {
        Board initial = new Board(new int[][] { { 1, 0, 3, 4 }, { 5, 2, 6, 8 }, { 9, 10, 7, 11 }, { 13, 15, 14, 12 } });
        Solver solver = new Solver(initial);

        Assert.assertFalse(solver.isSolvable());
    }
}