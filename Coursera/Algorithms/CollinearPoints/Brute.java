import java.util.Arrays;

public class Brute {
    public static void main(String[] args) {
        int count = StdIn.readInt();
        Point[] points = new Point[count];

        while (--count >= 0) {
            int x = Integer.parseInt(StdIn.readString());
            int y = Integer.parseInt(StdIn.readString());

            points[count] = new Point(x, y);
        }

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

    static void printIfCollinear(Point[] points) {
        int n = points.length;

        for (int i = 0; i < n - 2; i++) {
            if (points[i].slopeTo(points[i + 1]) != points[i + 1].slopeTo(points[i + 2])) {
                return;
            }
        }

        if (points[n - 2].slopeTo(points[n - 1]) != points[n - 1].slopeTo(points[0])) {
            return;
        }

        Arrays.sort(points);
        for (int i = 0; i < n; i++) {
            if (i != n - 1) {
                StdOut.print(points[i] + " -> ");
            } else {
                StdOut.println(points[i]);
            }
        }
    }
}
