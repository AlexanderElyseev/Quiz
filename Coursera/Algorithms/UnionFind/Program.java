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

        UnionFind uf = readInputFile(fileName);

        System.out.println("Data is loaded.");
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
    private static UnionFind readInputFile(String fileName) throws IOException {
        UnionFind uf = new UnionFind();
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        try {
            String line = br.readLine();
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