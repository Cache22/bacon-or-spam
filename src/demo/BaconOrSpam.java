package demo;

import validation.Validator;
import java.util.Scanner;

/**
 * {@code demo.BaconOrSpam} is a basic sample demo application showing how to validate char input and then
 * use it in a switch statement.
 *
 * <p>
 * @see Validator
 * @see Validator#getChar(java.util.Scanner, String, String, String)
 */
public class BaconOrSpam {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); // Standard Scanner object
        boolean askForBacon = true;

        // Loop until a valid choice is made
        while(askForBacon) {

            // Prompts user for input, and validates that it is a valid char with a value of B, S, or E.
            char choice = Validator.getChar(scanner, "Would you like [B]acon or [S]pam, or [E]xit? ", "BSE", "Invalid input. Please enter 'B', 'S', or 'E'.");

            switch (choice) {
                case 'B': // DELICIOUS!
                    System.out.println("You chose bacon, excellent choice!");
                    break;
                case 'S': // YUCK!
                    System.out.println("You chose the infamous potted meat product. I would have gone with the bacon.");
                    break;
                case 'E': // Exit
                    askForBacon = false;
                    System.out.println("I hope you enjoyed your breakfast!");
                    break;
            }
        }
    }
}
