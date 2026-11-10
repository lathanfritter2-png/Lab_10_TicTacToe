
import java.util.Scanner;

public class TicTacToe {

    private static final int ROWS = 3;
    private static final int COLS = 3;
    private static String[][] board = new String[ROWS][COLS];

    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);
        boolean playAgain = true;

        // Keep playing until the players choose to stop
        while (playAgain) {
            clearBoard();
            String player = "X";
            int moveCount = 0;
            boolean gameOver = false;

            // Take turns until someone wins or the game ties
            while (!gameOver) {
                display();
                System.out.println("Player " + player + "'s turn");

                int row;
                int col;

                // Ask for a move until an empty cell is chosen
                do {
                    row = SafeInput.getRangedInt(console, "Enter row", 1, ROWS) - 1;
                    col = SafeInput.getRangedInt(console, "Enter column", 1, COLS) - 1;

                    if (!isValidMove(row, col)) {
                        System.out.println("That spot is taken. Try again.");
                    }
                } while (!isValidMove(row, col));

                // Record the move
                board[row][col] = player;
                moveCount++;

                // Check for a win or tie
                if (moveCount >= 5 && isWin(player)) {
                    display();
                    System.out.println("Player " + player + " wins!");
                    gameOver = true;
                } else if (moveCount >= 9 && isTie()) {
                    display();
                    System.out.println("It's a tie!");
                    gameOver = true;
                } else {
                    // Switch players
                    player = player.equals("X") ? "O" : "X";
                }
            }

            playAgain = SafeInput.getYNConfirm(console, "Play again?");
        }

        System.out.println("Thanks for playing!");
        console.close();
    }

    // Clear all spaces on the board
    private static void clearBoard() {
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                board[row][col] = " ";
            }
        }
    }

    // Display the current board
    private static void display() {
        System.out.println();
        System.out.println("    1   2   3");

        for (int row = 0; row < ROWS; row++) {
            System.out.print((row + 1) + "   ");

            for (int col = 0; col < COLS; col++) {
                System.out.print(board[row][col]);

                if (col < COLS - 1) {
                    System.out.print(" | ");
                }
            }

            System.out.println();

            if (row < ROWS - 1) {
                System.out.println("   ---+---+---");
            }
        }

        System.out.println();
    }

    // Check if the chosen space is empty
    private static boolean isValidMove(int row, int col) {
        return row >= 0 && row < ROWS
                && col >= 0 && col < COLS
                && board[row][col].equals(" ");
    }

    // Check all possible ways to win
    private static boolean isWin(String player) {
        return isRowWin(player)
                || isColWin(player)
                || isDiagnalWin(player);
    }

    // Check for a winning row
    private static boolean isRowWin(String player) {
        for (int row = 0; row < ROWS; row++) {
            boolean win = true;

            for (int col = 0; col < COLS; col++) {
                if (!board[row][col].equals(player)) {
                    win = false;
                }
            }

            if (win) {
                return true;
            }
        }

        return false;
    }

    // Check for a winning column
    private static boolean isColWin(String player) {
        for (int col = 0; col < COLS; col++) {
            boolean win = true;

            for (int row = 0; row < ROWS; row++) {
                if (!board[row][col].equals(player)) {
                    win = false;
                }
            }

            if (win) {
                return true;
            }
        }

        return false;
    }

    // Check both diagonals
    private static boolean isDiagnalWin(String player) {
        boolean leftDiagonal = true;
        boolean rightDiagonal = true;

        for (int i = 0; i < ROWS; i++) {
            if (!board[i][i].equals(player)) {
                leftDiagonal = false;
            }

            if (!board[i][COLS - 1 - i].equals(player)) {
                rightDiagonal = false;
            }
        }

        return leftDiagonal || rightDiagonal;
    }

    // Check if all spaces are filled
    private static boolean isTie() {
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                if (board[row][col].equals(" ")) {
                    return false;
                }
            }
        }

        return true;
    }
}

