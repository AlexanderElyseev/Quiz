/**
 * Description: We need to find a single element from array of ints, where
 * all elements except one exist two times.
 * Memory: O(1)
 * Operations: O(n)
 */

public class Program {
    public static void main(String[] args) {
        int result = 0; // O(1)
        int[] data = { 1, 3, 5, 3, 1, 2, 2 };   // 5

        // O(n)
        for (int i : data) {
            /* A XOR A = 0
             * A XOR 0 = A
             * (A XOR B) XOR C = A XOR (B XOR C)
             */
            result = result ^ i;
        }

        System.out.println(result);
    }
}