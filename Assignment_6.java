import java.util.Scanner;
import java.util.Random;

public class QuickSortAnalysis {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the size of the array: ");
        int size = scanner.nextInt();

        int[] array = new int[size];
        int[] randomizedArray = new int[size];

        Random random = new Random();

        for (int i = 0; i < size; i++) {
            System.out.print("Enter element " + (i + 1) + ": ");
            array[i] = randomizedArray[i] = scanner.nextInt();
        }

        int deterministicComparisons = quickSort(array, 0, size - 1);
        int randomizedComparisons = randomizedQuickSort(randomizedArray, 0, size - 1, random);

        System.out.println("Sorted Array (Deterministic Quick Sort): " + arrayToString(array));
        System.out.println("Comparisons (Deterministic Quick Sort): " + deterministicComparisons);

        System.out.println("Sorted Array (Randomized Quick Sort): " + arrayToString(randomizedArray));
        System.out.println("Comparisons (Randomized Quick Sort): " + randomizedComparisons);

        scanner.close();
    }

    static int quickSort(int[] arr, int low, int high) {
        int comparisons = 0;
        if (low < high) {
            int pivotIndex = partition(arr, low, high);
            comparisons += (high - low);
            comparisons += quickSort(arr, low, pivotIndex - 1);
            comparisons += quickSort(arr, pivotIndex + 1, high);
        }
        return comparisons;
    }

    static int randomizedQuickSort(int[] arr, int low, int high, Random random) {
        int comparisons = 0;
        if (low < high) {
            int pivotIndex = randomizedPartition(arr, low, high, random);
            comparisons += (high - low);
            comparisons += randomizedQuickSort(arr, low, pivotIndex - 1, random);
            comparisons += randomizedQuickSort(arr, pivotIndex + 1, high, random);
        }
        return comparisons;
    }

    static int partition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (arr[j] <= pivot) {
                i++;
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;

        return i + 1;
    }

    static int randomizedPartition(int[] arr, int low, int high, Random random) {
        int randomIndex = random.nextInt(high - low + 1) + low;
        int temp = arr[randomIndex];
        arr[randomIndex] = arr[high];
        arr[high] = temp;
        return partition(arr, low, high);
    }

    static String arrayToString(int[] arr) {
        StringBuilder builder = new StringBuilder();
        builder.append("[");
        for (int i = 0; i < arr.length; i++) {
            builder.append(arr[i]);
            if (i < arr.length - 1) {
                builder.append(", ");
            }
        }
        builder.append("]");
        return builder.toString();
    }
}
