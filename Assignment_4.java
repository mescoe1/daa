import java.util.Scanner;

public class KnapsackProblem {
    public static int knapsack(int[] values, int[] weights, int capacity) {
        int n = values.length;  // Number of items
        int[][] dp = new int[n + 1][capacity + 1];  // Dynamic programming table

        for (int i = 0; i <= n; i++) {
            for (int w = 0; w <= capacity; w++) {
                if (i == 0 || w == 0) {
                    dp[i][w] = 0;  // Initialize the table's first row and first column to zero
                } else if (weights[i - 1] <= w) {
                    // If the weight of the current item can fit in the knapsack
                    dp[i][w] = Math.max(values[i - 1] + dp[i - 1][w - weights[i - 1]], dp[i - 1][w]);
                    // Calculate the maximum value by choosing or not choosing the current item
                } else {
                    dp[i][w] = dp[i - 1][w];  // If the weight of the item is too large, skip it
                }
            }
        }
        return dp[n][capacity];  // Return the maximum value that can be obtained
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of items: ");
        int n = scanner.nextInt();  // Read the number of items
        int[] values = new int[n];  // Arrays to store values and weights for each item
        int[] weights = new int[n];

        System.out.println("Enter the values and weights for each item:");
        for (int i = 0; i < n; i++) {
            System.out.print("Value for item " + (i + 1) + ": ");
            values[i] = scanner.nextInt();  // Read the value for each item
            System.out.print("Weight for item " + (i + 1) + ": ");
            weights[i] = scanner.nextInt();  // Read the weight for each item
        }

        System.out.print("Enter the knapsack's weight capacity: ");
        int capacity = scanner.nextInt();  // Read the knapsack's weight capacity

        int maxValue = knapsack(values, weights, capacity);  // Call the knapsack function to compute the maximum value
        System.out.println("The maximum value in the knapsack is: " + maxValue);  // Print the result
    }
}
