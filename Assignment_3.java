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

public class FractionalKnapsackUserInput {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the number of items: ");
        int n = scanner.nextInt();

        Item[] items = new Item[n];

        for (int i = 0; i < n; i++) {
            System.out.println("Enter the name of item " + (i + 1) + ": ");
            String name = scanner.next();

            System.out.println("Enter the weight of item " + (i + 1) + ": ");
            double weight = scanner.nextDouble();

            System.out.println("Enter the value of item " + (i + 1) + ": ");
            double value = scanner.nextDouble();

            items[i] = new Item(name, weight, value);
        }

        System.out.println("Enter the maximum capacity of the knapsack: ");
        double capacity = scanner.nextDouble();

        scanner.close();

        Arrays.sort(items, (item1, item2) -> Double.compare(item2.value / item2.weight, item1.value / item1.weight));

        double totalValue = 0;
        double remainingCapacity = capacity;

        for (Item item : items) {
            if (remainingCapacity >= item.weight) {
                System.out.println("Selected " + item.name + " - Weight: " + item.weight + ", Value: " + item.value);
                totalValue += item.value;
                remainingCapacity -= item.weight;
            } else {
                double fraction = remainingCapacity / item.weight;
                System.out.println("Selected " + item.name + " - Weight: " + item.weight * fraction + ", Value: " + item.value * fraction);
                totalValue += item.value * fraction;
                break;
            }
        }

        System.out.println("Total Value: " + totalValue);
    }
}
