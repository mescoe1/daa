// non recursive
import java.util.Scanner;
public class FibonacciIterative {
    public static int fibonacci(int n) {
        if (n <= 1) {
            return n;
        }
        int fib = 0, prev = 1, temp;
        for (int i = 2; i <= n; i++) {
            temp = fib;
            fib = fib + prev;
            prev = temp;
        }
        return fib;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the Number : ");
        int n = sc.nextInt();
        long startTime = System.nanoTime();
        System.out.println( startTime);
        int result = fibonacci(n);
        long endTime = System.nanoTime();

        System.out.println(endTime);


        long timeElapsed = endTime - startTime;
        System.out.println("Fibonacci(" + n + ") = " + result);
        System.out.println("\n Time taken: " + timeElapsed + " nanoseconds");
    }
}
// recursive
import java.util.Scanner;

public class FibonacciRecursive {
    public static int fibonacci(int n) {
        if (n <= 1) {
            return n;
        }
        return fibonacci(n - 1) + fibonacci(n - 2);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the Number : ");
        int n = sc.nextInt();
        long startTime = System.nanoTime();
        int result = fibonacci(n-1);
        
        long endTime = System.nanoTime();
        long timeElapsed = endTime - startTime;
        System.out.println("Fibonacci(" + n + ") = " + result);
        System.out.println("Time taken: " + timeElapsed + " nanoseconds");
    }
}
