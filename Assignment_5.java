import java.util.Scanner;

public class sup {
    static int N;           // Number of queens and size of the chessboard
    static int[][] board;   // Chessboard represented as a 2D array
    static int solutions = 0;  // Counter for the number of solutions

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the value of N for the N-Queens problem: ");
        N = scanner.nextInt();   // User input for the number of queens
        scanner.close();

        board = new int[N][N];  // Initialize the chessboard

        findNQueensSolutions(0);  // Start finding N-Queens solutions from the first column (column 0)

        System.out.println("Number of solutions: " + solutions);  // Print the number of solutions
    }

    static void findNQueensSolutions(int col) {
        if (col >= N) {  // If all queens are placed successfully (base case)
            printBoard();  // Print the solution
            solutions++;   // Increment the solutions counter
        } else {
            for (int i = 0; i < N; i++) {
                if (isSafe(i, col)) {  // Check if it's safe to place a queen at row i, column col
                    board[i][col] = 1;  // Place a queen at (i, col)
                    findNQueensSolutions(col + 1);  // Recursively solve for the next column
                    board[i][col] = 0;  // Backtrack by removing the queen (0 indicates no queen)
                }
            }
        }
    }

    static boolean isSafe(int row, int col) {
        int i, j;

        for (i = 0; i < col; i++) {
            if (board[row][i] == 1) {
                return false;  // Check if there is a queen in the same row to the left
            }
        }

        for (i = row, j = col; i >= 0 && j >= 0; i--, j--) {
            if (board[i][j] == 1) {
                return false;  // Check if there is a queen in the upper-left diagonal
            }
        }

        for (i = row, j = col; j >= 0 && i < N; i++, j--) {
            if (board[i][j] == 1) {
                return false;  // Check if there is a queen in the lower-left diagonal
            }
        }

        return true;  // If no conflicts are found, it's safe to place a queen at (row, col)
    }

    static void printBoard() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(board[i][j] + " ");  // Print the chessboard with 1s (queens) and 0s
            }
            System.out.println();
        }
        System.out.println();  // Empty line to separate solutions
    }
}
