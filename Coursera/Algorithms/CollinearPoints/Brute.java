import java.util.Arrays;

public class Brute {
    public static void main(String[] args) {
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);

        String filename = args[0];
        In in = new In(filename);

        int count = in.readInt();
        Point[] points = new Point[count];

        int index = 0;
        while (index != count) {
            int x = in.readInt();
            int y = in.readInt();

            Point point = new Point(x, y);
            points[index++] = point;

            point.draw();
        }

        // Checking each 4 points.
        Arrays.sort(points);
        for (int i = 0; i < count; i++) {
            for (int j = i + 1; j < count; j++) {
                for (int k = j + 1; k < count; k++) {
                    for (int l = k + 1; l < count; l++) {
                        printIfCollinear(new Point[] { points[i], points[j], points[k], points[l] });
                    }
                }
            }
        }
    }

    private static void printIfCollinear(Point[] points) {
        int n = points.length;

        assert n >= 3;

        // Comparing slopes.
        Point base = points[0];
        Point target = points[1];
        double slope = base.slopeTo(target);

        int i = 2;
        do {
            target = points[i];
            if (base.slopeTo(target) != slope)
                return;

        } while (++i < n);

        // Printing output.
        Arrays.sort(points);
        for (i = 0; i < n; i++) {
            if (i != n - 1) {
                StdOut.print(points[i] + " -> ");
            } else {
                StdOut.println(points[i]);
            }
        }

        // Drawing output.
        points[0].drawTo(points[n - 1]);
    }
}
