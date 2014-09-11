import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Program {
    /**
     * Program main entry point.
     *
     * @param args Command line arguments.
     */
    public static void main(String[] args) throws Exception {
        if (args.length == 0) {
            System.out.println("Data file is not specified.");
            return;
        }

        String fileName = args[0];
        System.out.println("Reading file \"" + fileName + "\"...");

        IUnionFind uf = readInputFile(fileName);

        System.out.println("Data is loaded.");

        if (uf.isConnected(1, 2))
            System.out.println("(1, 2) : connected.");

        if (uf.isConnected(2, 3))
            System.out.println("(2, 3) : connected.");

        if (uf.isConnected(5, 13))
            System.out.println("(5, 13) : connected.");

        IUnionFind uf1 = new WeighedQuickUnion(10, true);
        System.out.println(uf1.toString());
        uf1.union(4, 3);
        System.out.println(uf1.toString());
        uf1.union(3, 8);
        System.out.println(uf1.toString());
        uf1.union(6, 5);
        System.out.println(uf1.toString());
        uf1.union(9, 4);
        System.out.println(uf1.toString());
        uf1.union(2, 1);
        System.out.println(uf1.toString());
        uf1.union(5, 0);
        System.out.println(uf1.toString());
        uf1.union(7, 2);
        System.out.println(uf1.toString());
        uf1.union(6, 1);
        System.out.println(uf1.toString());
        uf1.union(7, 3);
        System.out.println(uf1.toString());
    }

    /**
     * Reads the file from specified path and build UnionFind structure.
     *
     * If line contains two numbers ("10 20"), that elements are connected.
     * Other lines are skipped.
     *
     * @param fileName The name of file with data.
     *
     * @return UnionFind structure with data from the file.
     */
    private static IUnionFind readInputFile(String fileName) throws IOException {
        IUnionFind uf;
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        try {
            String line = br.readLine();
            if (line == null)
                throw new UnsupportedOperationException("First line of data file have to contain size of set.");

            int itemsCount = Integer.parseInt(line);
            uf = new WeighedQuickUnion(itemsCount, true);

            Pattern pattern = Pattern.compile("-?\\d+");
            int[] numbers = new int[2];
            while (line != null) {
                int index = 0;
                Matcher matcher = pattern.matcher(line);
                while (matcher.find()) {
                    numbers[index++] = Integer.parseInt(matcher.group());
                }

                line = br.readLine();

                if (index != 2)
                    continue;

                uf.union(numbers[0], numbers[1]);
            }
        } finally {
            br.close();
        }
        return uf;
    }
}