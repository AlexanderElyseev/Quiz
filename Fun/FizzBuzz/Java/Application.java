public class Application {
    public static void main(String args[]) {
        int max = 100;

        System.out.println("Variant 1:");
        run1(max);
        System.out.println();

        System.out.println("Variant 2:");
        run2(max);
        System.out.println();
    }

    private static void run1(int maxInt) {
        for (int i = 1; i <= maxInt; i++) {
            boolean fizz = i % 3 == 0;
            boolean buzz = i % 5 == 0;

            if (!fizz && !buzz) {
                System.out.print(i);
            } else {
                if (fizz)
                    System.out.print("Fizz");

                if (buzz)
                    System.out.print("Buzz");
            }

            System.out.print(i == maxInt ? "" : ", ");
        }
    }

    private static void run2(int maxInt) {
        for (int i = 1; i <= maxInt; i++) {
            String s = "";
            if (i % 3 == 0) {
                s += "Fizz";
            }

            if (i % 5 == 0) {
                s += "Buzz";
            }

            if (s.length() == 0) {
                s = String.valueOf(i);
            }

            System.out.print(s + (i == maxInt ? "" : ", "));
        }
    }
}
