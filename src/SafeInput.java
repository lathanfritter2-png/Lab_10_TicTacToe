
import java.util.Scanner;

public class SafeInput {

    public static int getRangedInt(Scanner console, String prompt, int low, int high) {
        int value;

        while (true) {
            System.out.print(prompt + " [" + low + "-" + high + "]: ");

            if (console.hasNextInt()) {
                value = console.nextInt();
                console.nextLine();

                if (value >= low && value <= high) {
                    return value;
                }
            } else {
                console.nextLine();
            }

            System.out.println("Invalid input. Please try again.");
        }
    }

    public static boolean getYNConfirm(Scanner console, String prompt) {
        while (true) {
            System.out.print(prompt + " [Y/N]: ");
            String answer = console.nextLine().trim();

            if (answer.equalsIgnoreCase("Y")) {
                return true;
            }

            if (answer.equalsIgnoreCase("N")) {
                return false;
            }

            System.out.println("Invalid input. Enter Y or N.");
        }
    }
}
