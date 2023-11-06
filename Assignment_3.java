import java.util.*;

class Item {
    String name;
    double weight;
    double value;

    public Item(String name, double weight, double value) {
        this.name = name;
        this.weight = weight;
        this.value = value;
    }
}

public class aman {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the number of items: ");
        int n = scanner.nextInt();  // Read the number of items

        Item[] items = new Item[n];  // Create an array to store item information

        for (int i = 0; i < n; i++) {
            System.out.println("Enter the name of item " + (i + 1) + ": ");
            String name = scanner.next();  // Read the name of each item

            System.out.println("Enter the weight of item " + (i + 1) + ": ");
            double weight = scanner.nextDouble();  // Read the weight of each item

            System.out.println("Enter the value of item " + (i + 1) + ": ");
            double value = scanner.nextDouble();  // Read the value of each item

            items[i] = new Item(name, weight, value);  // Create Item objects and store them in the array
        }

        System.out.println("Enter the maximum capacity of the knapsack: ");
        double capacity = scanner.nextDouble();  // Read the maximum capacity of the knapsack

        scanner.close();  // Close the scanner

        // Sort the items in descending order of value-to-weight ratio using a lambda comparator
        Arrays.sort(items, (item1, item2) -> Double.compare(item2.value / item2.weight, item1.value / item1.weight));

        double totalValue = 0;
        double remainingCapacity = capacity;

        // Iterate through the items, selecting them for the knapsack
        for (Item item : items) {
            if (remainingCapacity >= item.weight) {
                System.out.println("Selected " + item.name + " - Weight: " + item.weight + ", Value: " + item.value);
                totalValue += item.value;
                remainingCapacity -= item.weight;
            } else {
                double fraction = remainingCapacity / item.weight;
                System.out.println("Selected " + item.name + " - Weight: " + item.weight * fraction + ", Value: " + item.value * fraction);
                totalValue += item.value * fraction;
                break;  // Stop selecting items once the knapsack is full
            }
        }

        System.out.println("Total Value: " + totalValue);  // Print the total value obtained
    }
}
