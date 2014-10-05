import java.util.Arrays;
import java.util.Comparator;

public class Fast {
    private static class PointData {
        Point point;
        double slope;
        public PointData(Point point, double slope) {
            this.point = point;
            this.slope = slope;
        }
    }

    private static class PointDataByPointComparator implements Comparator<PointData> {
        @Override
        public int compare(PointData o1, PointData o2) {
            return o1.point.compareTo(o2.point);
        }
    }

    private static class PointDataBySlopeComparator implements Comparator<PointData> {
        @Override
        public int compare(PointData o1, PointData o2) {
            return Double.compare(o1.slope, o2.slope);
        }
    }

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

        for (int baseIndex = 0; baseIndex < count; baseIndex++) {
            Point basePoint = points[baseIndex];

            PointData[] data = new PointData[count];
            for (int r = 0; r < count; r++) {
                data[r] = new PointData(points[r], basePoint.slopeTo(points[r]));
            }

            Arrays.sort(data, new PointDataBySlopeComparator());
            int streak = 1;
            for (int r = 1; r < count; r++) {
                double previousSlope = data[r - 1].slope;
                double currentSlope = data[r].slope;

                if (previousSlope == currentSlope) {
                    streak++;
                    continue;
                }

                printStreak(data, basePoint, r, streak);
                streak = 1;
            }

            printStreak(data, basePoint, count, streak);
        }
    }

    private static void printStreak(PointData[] points, Point base, int end, int streak) {
        if (streak < 3)
            return;

        int index = 0;
        PointData[] print = new PointData[streak + 1];
        for (int i = end - streak; i < end; i++) {
            print[index++] = points[i];
        }
        print[streak] = new PointData(base, Double.NEGATIVE_INFINITY);

        // Print without duplicates (only streak from the base).
        Arrays.sort(print, new PointDataByPointComparator());
        if (print[0].slope != Double.NEGATIVE_INFINITY)
            return;

        // Printing.
        for (int i = 0; i <= streak; i++) {
            if (i != streak) {
                StdOut.print(print[i].point + " -> ");
            } else {
                StdOut.println(print[i].point);
            }
        }

        // Drawing output.
        print[0].point.drawTo(print[streak].point);
    }
}
