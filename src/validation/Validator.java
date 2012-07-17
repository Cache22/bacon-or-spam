package validation;

import javax.swing.text.JTextComponent;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import static validation.Constants.*;

/**
 * This variation of the Validator class prompts for input from the console and uses the Scanner object to
 * retrieve input. The input is then validated based on the method called. Integers and doubles can pass
 * optional minimum & maximum values for an inclusive range check. String input can pass just the scanner
 * and input prompt, as well as optionally add a regular expression pattern to match and custom error message.
 *
 * <p><b>For Swing applications:</b> JTextComponent objects can be validated as well. The methods throw
 * {@link InvalidInputException}, the calling code can choose how to handle highlighting the error
 * by changing the background color as well as displaying the error message dialog.
 *
 * <p>The statically imported Constants class contains constants for commonly used patterns as well as input
 * validation error message strings.
 *
 * <p><b>Examples:</b></p>
 * <p>A compilable getChar example is available in the {@link demo} package:</p>
 * {@link demo.BaconOrSpam BaconOrSpam demo application}
 *
 * <blockquote><pre>
 * // Prompts user to <em>Enter Last name: </em>, verifies input is not empty
 * String lastName = Validator.getString(scanner, "Enter last name: ", {@link Constants#MATCH_NOT_EMPTY MATCH_NOT_EMPTY});
 *
 * // Prompts user to <em>Enter email address: </em>, matches against {@link Constants#MATCH_EMAIL} regular expression
 * String emailAddress = Validator.getString(scanner, "Enter email address: ", {@link Constants#MATCH_EMAIL MATCH_EMAIL});
 *
 * // Uses user-defined regular expression, this example matches a customer number in the form M12345 (1 character followed by 5 numbers)
 * customerNumber = Validator.getString(scanner, "Customer number: ", "[A-Za-z]{1}[0-9]{5}", "Please enter a valid customer number (ex: M12345).");
 *
 * // This example is from the CircleApp, it uses two of the Validator methods. It loops until the user chooses
 * // to exit by getting input and using the predefined {@link Constants#MATCH_CHOICE_YN} regular expression.
 * // Inside the loop, a Circle object is instantiated, the Validator.getDouble method is used in the constructor.
 * //    This will first retrieve and validate that the user supplied a valid double, and then pass it to the Circle constructor.
 * while(continueCircleApp.equalsIgnoreCase("Y")){
 *      Circle circle = new Circle(Validator.getDouble(scanner, "\nEnter radius: "));
 *      System.out.println("Circumference: " + circle.getFormattedCircumference());
 *      System.out.println("Area:          " + circle.getFormattedArea());
 *      continueCircleApp = Validator.getString(scanner, "\nContinue? (y/n): ", {@link Constants#MATCH_CHOICE_YN MATCH_CHOICE_YN});
 * }
 * </pre></blockquote>
 *
 * <p>@author Ben Murray (validation@my.stlcc.edu)<br />
 * IS:251-650 Introduction to Java Programming
 *
 * <p>@version 2012.07.10
 */
public class Validator {

    /**
     * Use static HashMap to track fields with errors, currently used to track background color so it can be
     * reliably reset when the error state has cleared.
     */
    private static Map<JTextComponent, Color> errorFieldMap = new HashMap<JTextComponent, Color>();

    /**
     * Private method used to retrieve user input from console, used by all the console input methods
     * in the Validator class.
     *
     * @param scanner        Scanner object
     * @param inputPrompt    String for user input prompt
     * @return               Returns user input to caller as a String
     * @throws InvalidInputException
     */
    private static String getConsoleInput(Scanner scanner, String inputPrompt) throws InvalidInputException {
        System.out.print(inputPrompt);
        String consoleInputLine = scanner.nextLine();
        if(consoleInputLine == null) {
            consoleInputLine = "";
        }
        return consoleInputLine;
    }

    /**
     * Prompts the user for input and returns a valid integer.
     *
     * @param scanner        Scanner object
     * @param inputPrompt    String for user input prompt
     * @return               Returns valid integer
     * @see #getInt(java.util.Scanner, String, int, int)
     */
    public static int getInt(Scanner scanner, String inputPrompt) {
        return getInt(scanner, inputPrompt, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    /**
     * Verifies a valid int, then performs an <b>inclusive</b> range check
     *
     * @param scanner         Scanner object
     * @param inputPrompt     String for user input prompt
     * @param minimumValue    Minimum allowed integer in range (inclusive)
     * @param maximumValue    Maximum allowed integer in range (inclusive)
     * @return                Returns valid integer within specified range
     */
    public static int getInt(Scanner scanner, String inputPrompt, int minimumValue, int maximumValue) {
        int validInt;
        while (true) {
            try {
                String userInput = getConsoleInput(scanner, inputPrompt);
                validInt = Integer.parseInt(userInput);
                if((validInt < minimumValue) || validInt > maximumValue) {
                    System.out.println("\nThe value entered must be within the range " + minimumValue + " through " + maximumValue + ". Please try again.");
                } else {
                    break;
                }
            } catch (InvalidInputException e) {
                System.out.println("\n" + MSG_INVALID_INT);
            } catch (NumberFormatException e){
                System.out.println("\n" + MSG_INVALID_INT);
            }
        }
        return validInt;
    }

    /**
     * Prompts the user for input and returns a valid double. Calls getDouble and passes
     * static constants for minimum and maximum possible double values.
     *
     * <p>NOTE: The default minimum value is determined by making MAX_VALUE negative, this is because MIN_VALUE on
     * a decimal refers to the smallest decimal and not the "most negative" value
     *
     * @param scanner        Scanner object
     * @param inputPrompt    String for user input prompt
     * @return               Returns valid double
     * @see #getDouble(java.util.Scanner, String, double, double)
     */
    public static double getDouble(Scanner scanner, String inputPrompt) {
        return getDouble(scanner, inputPrompt, -Double.MAX_VALUE, Double.MAX_VALUE);
    }

   /**
     * Verifies a valid double, then performs an <b>inclusive</b> range check
     *
     * @param scanner         Scanner object
     * @param inputPrompt     String for user input prompt
     * @param minimumValue    Minimum allowed integer in range (inclusive)
     * @param maximumValue    Maximum allowed integer in range (inclusive)
     * @return                Returns valid integer within specified range
     */
    public static double getDouble(Scanner scanner, String inputPrompt, double minimumValue, double maximumValue) {
        double validDouble;
        while (true) {
            try {
                String userInput = getConsoleInput(scanner, inputPrompt);
                validDouble = Double.parseDouble(userInput);
                if((validDouble < minimumValue) || validDouble > maximumValue) {
                    System.out.println("\nThe value entered must be within the range " + minimumValue + " through " + maximumValue + ". Please try again.");
                } else {
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("\n" + MSG_INVALID_DECIMAL);
            } catch (InvalidInputException e) {
                e.toString(MSG_INVALID_DECIMAL);
                System.out.println("\n" + MSG_INVALID_DECIMAL);
            }
        }
        return validDouble;
    }

    /**
     * Prompts the user for input and validates it as a valid {@code char}
     *
     * @param scanner              Scanner object
     * @param inputPrompt          String for user input prompt
     * @return {@code char} with the validated input
     * @see #getChar(java.util.Scanner, String, String, String)
     */
    public static char getChar(Scanner scanner, String inputPrompt) {
        return getChar(scanner, inputPrompt, MATCH_CHAR, MSG_INVALID_CHAR);
    }

    /**
     * Prompts the user for input and validates it as a valid {@code char}
     *
     * @param scanner              Scanner object
     * @param inputPrompt          String for user input prompt
     * @param validChars           String containing list of valid characters
     * @return {@code char} with the validated input
     * @see #getChar(java.util.Scanner, String, String, String)
     */
    public static char getChar(Scanner scanner, String inputPrompt, String validChars) {
        return getChar(scanner, inputPrompt, validChars, MSG_INVALID_CHAR);
    }

    /**
     * Prompts the user for input and validates it as a valid {@code char}
     *
     * @param scanner              Scanner object
     * @param inputPrompt          String for user input prompt
     * @param validChars           String containing list of valid characters
     * @param inputErrorMessage    Error message to display if validation fails
     * @return {@code char} with the validated input
     */
    public static char getChar(Scanner scanner, String inputPrompt, String validChars, String inputErrorMessage) {
        String userInput;
        while(true) {
            if(inputErrorMessage.equals(MSG_INVALID_CHAR)) {
                // Append accepted characters to error message
                inputErrorMessage += " [" + validChars.toUpperCase() + validChars.toLowerCase() + "]";
            }
            // Validate, use common getString, pass RegEx to verify only a single character has been input
            userInput = getString(scanner, inputPrompt, MATCH_CHAR, inputErrorMessage);
            // If provided, validated against a string of valid characters passed to the function
            if(!validChars.equals(MATCH_CHAR)) {
                if(validChars.toLowerCase().contains(userInput.toLowerCase())) {
                    break;
                } else {
                    System.out.println("\n" + inputErrorMessage);
                }
            } else {
                break;
            }
        }
        return userInput.toUpperCase().charAt(0);
    }

    /**
     * Prompts the user for input and returns a valid String. Allows any string including an empty
     * string. Calls overloaded getString() method passing constants MATCH_ANY and MSG_INVALID_STRING
     *
     * @param scanner           Scanner object
     * @param inputPrompt       String for user input prompt
     * @return                  Returns valid String
     * @see #getString(java.util.Scanner, String, String, String)
     */
    public static String getString(Scanner scanner, String inputPrompt) {
        return getString(scanner, inputPrompt, MATCH_ANY, MSG_INVALID_STRING);
    }

    /**
     * Prompts the user for input and validates it against a pattern. Calls overloaded getString
     * method passing constant MSG_INVALID_STRING
     *
     * @param scanner        Scanner object
     * @param inputPrompt    String for user input prompt
     * @param pattern        Pattern to evaluate input against
     * @return               Returns validated String
     * @see #getString(java.util.Scanner, String, String, String)
     */
    public static String getString(Scanner scanner, String inputPrompt, String pattern) {
        return getString(scanner, inputPrompt, pattern, MSG_INVALID_STRING);
    }

    /**
     * Prompts the user for input and validates it against a pattern. When valid
     * input is found it returns the valid input as a String. Invalid input throws exception
     * that displays error message and asks the user to try again.
     *
     * @param scanner        Scanner object
     * @param inputPrompt    String for user input prompt
     * @param pattern        Pattern to evaluate input against
     * @param inputErrorMessage    Message to display on error
     * @return               Returns validated String
     */
    public static String getString(Scanner scanner, String inputPrompt, String pattern, String inputErrorMessage) {
        String userInput;
        while (true) {
            try {
                userInput = getConsoleInput(scanner, inputPrompt);
                if(!userInput.matches(pattern)) {
                    System.out.println("\n" + inputErrorMessage);
                } else {
                    break;
                }
            } catch (InvalidInputException e) {
                e.toString(inputErrorMessage);
            }
        }
        return userInput;
    }

    /**
     * Validates text as double from JTextComponent object
     *
     * @param jTextComponent    JTextComponent object being validated
     * @param inputErrorMessage Error message to display to the user
     * @return valid {@code double}
     * @exception InvalidJTextComponentInputException thrown if an input validation error occurs
     * @see #getDouble(javax.swing.text.JTextComponent, String, double, double)
     */
    public static double getDouble(JTextComponent jTextComponent, String inputErrorMessage) throws InvalidJTextComponentInputException{
        return getDouble(jTextComponent, inputErrorMessage, -Double.MAX_VALUE, Double.MAX_VALUE);
    }

    /**
     * Validates text as double from JTextComponent object
     *
     * @param jTextComponent    JTextComponent object being validated
     * @param inputErrorMessage Error message to display to the user
     * @param minimumValue      Minimum value for double
     * @param maximumValue      Maximum value for double
     * @return valid {@code double}
     * @exception InvalidJTextComponentInputException thrown if an input validation error occurs
     */
    public static double getDouble(JTextComponent jTextComponent, String inputErrorMessage, double minimumValue, double maximumValue) throws InvalidJTextComponentInputException {
        String userInput;
        double validDouble;

        userInput = getInputFromJTextComponent(jTextComponent, inputErrorMessage, Constants.MATCH_NOT_EMPTY);

        try {
            validDouble = Double.parseDouble(userInput);
        } catch (NumberFormatException e) {
            throw new InvalidJTextComponentInputException();
        }
        if(!rangeCheck(validDouble, minimumValue, maximumValue)) {
            throw new InvalidJTextComponentInputException();
        }
        return validDouble;
    }


    /**
     * Validates text as int from JTextComponent object
     *
     * @param jTextComponent    JTextComponent object being validated
     * @param inputErrorMessage Error message to display to the user
     * @return valid {@code Integer}
     * @exception InvalidJTextComponentInputException thrown if an input validation error occurs
     * @see #getInt(javax.swing.text.JTextComponent, String, int, int)
     */
    public static int getInt(JTextComponent jTextComponent, String inputErrorMessage) throws InvalidJTextComponentInputException {
        return getInt(jTextComponent, inputErrorMessage, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    /**
     * Validates text as int from JTextComponent object with a minimum value specified
     *
     * @param jTextComponent    JTextComponent object being validated
     * @param inputErrorMessage Error message to display to the user
     * @param minimumValue      Minimum value for int
     * @return valid {@code Integer}
     * @exception InvalidJTextComponentInputException thrown if an input validation error occurs
     * @see #getInt(javax.swing.text.JTextComponent, String, int, int)
     */
    public static int getInt(JTextComponent jTextComponent, String inputErrorMessage, int minimumValue) throws InvalidJTextComponentInputException {
        return getInt(jTextComponent, inputErrorMessage, minimumValue, Integer.MAX_VALUE);
    }

    /**
     * Validates text from JTextComponent object with <strong>inclusive</strong> range check.
     *
     * @param jTextComponent    JTextComponent object being validated
     * @param inputErrorMessage Error message to display to the user
     * @param minimumValue      Minimum value if a range is required
     * @param maximumValue      Maximum value if a range is required
     * @return valid {@code Integer}
     * @exception InvalidJTextComponentInputException thrown if an input validation error occurs
     */
    public static int getInt(JTextComponent jTextComponent, String inputErrorMessage, int minimumValue, int maximumValue) throws InvalidJTextComponentInputException {
        String userInput;
        int validInt;

        userInput = getInputFromJTextComponent(jTextComponent, inputErrorMessage, Constants.MATCH_NOT_EMPTY);

        try {
            validInt = Integer.parseInt(userInput);
        } catch (NumberFormatException e) {
            throw new InvalidJTextComponentInputException();
        }
        if(!rangeCheck(validInt, minimumValue, maximumValue)) {
            throw new InvalidJTextComponentInputException();
        }
        return validInt;
    }

    /**
     * Accepts a JTextComponent and validates it against a pattern. If valid input is found
     * it returns the valid input as a string. Invalid input throws an InvalidJTextComponentInputException
     * exception to be handled by the calling code. See docs for getInputFromJTextComponent for an example.
     *
     * @param jTextComponent       JTextComponent object being validated
     * @param inputErrorMessage    Error message to display to the user
     * @param pattern              Pattern to match.
     * @return valid {@code String}
     * @exception InvalidJTextComponentInputException thrown if an input validation error occurs
     */
    public static String getString(JTextComponent jTextComponent, String inputErrorMessage, String pattern) {
        return getInputFromJTextComponent(jTextComponent, inputErrorMessage, pattern);
    }

    /**
     * For GUI validation, works with anything that inherits from JTextComponent. This method is called
     * from other overloaded methods for retrieving int, double, and String input.
     *
     * @see #getInt(javax.swing.text.JTextComponent, String)
     * @see #getInt(javax.swing.text.JTextComponent, String, int, int)
     * @see #getDouble(javax.swing.text.JTextComponent, String)
     * @see #getDouble(javax.swing.text.JTextComponent, String, double, double)
     * @see #getString(javax.swing.text.JTextComponent, String, String)
     *
     * <p>An exception of type InvalidJTextComponentInputException is thrown if the input is not
     * valid. The exception needs to be handled by the original calling code.
     *
     * <p><strong>Example:</strong> This example uses one the {@link #getInt(javax.swing.text.JTextComponent, String, int, int)} method.
     * <blockquote><pre>
     * public void actionPerformed(ActionEvent e) {
     *     int testScore = 0;
     *     try {
     *         // Pass JTextField "testScore" to Validator.getInt with error message and valid range
     *         testScore = Validator.getInt(testScoreTextField, "A Valid Test Score between 0 and 100 is Required.", 0, 100);
     *         // If valid continue with code, if not exception thrown to catch block below
     *         testScores.enterScore(testScore);
     *      } catch (InvalidJTextComponentInputException e) {
     *         // Change the JTextField background color to highlight the error
     *         e.setInvalidFieldBackground(testScoreTextField);
     *         // Display the error message dialog box
     *         e.showErrorMessageDialog(testScoreTextField, "A Valid Test Score between 0 and 100 is Required.");
     *      }
     * }
     * </blockquote></pre>
     *
     * @param jTextComponent     JTextComponent object to use for validation
     * @param inputErrorMessage  Error message to display to user
     * @param pattern            Pattern to match.
     * @return                   Returns the string acquired from the JTextComponent
     * @exception InvalidJTextComponentInputException thrown if an input validation error occurs
     */
    public static String getInputFromJTextComponent(JTextComponent jTextComponent, String inputErrorMessage, String pattern) throws InvalidJTextComponentInputException {
        String userInput;

        userInput = jTextComponent.getText();
        if(!userInput.matches(pattern)) {
            throw new InvalidJTextComponentInputException();
        }
        // If there is a previous error, set the background back to original state
        clearErrorField(jTextComponent);

        return userInput;
    }

    /**
     * <b>Inclusive</b> range check for double
     *
     * @param userInput       User supplied input
     * @param minimumValue    Minimum allowed value
     * @param maximumValue    Maximum allowed value
     * @return {@code boolean} for success/failure
     * @see #getInt(java.util.Scanner, String, int, int)
     * @see #getInt(javax.swing.text.JTextComponent, String, int, int)
     */
    private static boolean rangeCheck(double userInput, double minimumValue, double maximumValue) {
        return !((userInput < minimumValue) || userInput > maximumValue);
    }

    /**
     * <b>Inclusive</b> range check for int
     *
     * @param userInput       User supplied input
     * @param minimumValue    Minimum allowed value
     * @param maximumValue    Maximum allowed value
     * @return {@code boolean} for success/failure
     * @see #getDouble(java.util.Scanner, String, double, double)
     * @see #getDouble(javax.swing.text.JTextComponent, String, double, double)
     */
    private static boolean rangeCheck(int userInput, int minimumValue, int maximumValue) {
        return !((userInput < minimumValue) || userInput > maximumValue);
    }

    /**
     * Check if the passed JTextComponent has been flagged with a validation error
     *
     * @param jTextComponent    The JTextComponent to Test
     * @return boolean          Returns true if this JTextComponent has been flagged with an error
     * @see #errorFieldMap
     */
    public static boolean isErrorField(JTextComponent jTextComponent) {
        return errorFieldMap.containsKey(jTextComponent);
    }

    /**
     * Resets the background color and clears the error flag for a JTextComponent
     *
     * @param jTextComponent    The JTextComponent to modify
     * @see #isErrorField(javax.swing.text.JTextComponent)
     */
    public static void clearErrorField(JTextComponent jTextComponent) {
        if(isErrorField(jTextComponent)) {
            jTextComponent.setBackground(errorFieldMap.get(jTextComponent));
            errorFieldMap.remove(jTextComponent);
        }
    }

    /**
     * Stores the original background color of a JTextComponent into a HashMap, and then changes the current background
     * color to reflect a validation error for the field.
     *
     * @param jTextComponent    The JTextComponent to modify
     * @see #errorFieldMap
     */
    public static void setErrorField(JTextComponent jTextComponent) {
        errorFieldMap.put(jTextComponent, jTextComponent.getBackground());
        jTextComponent.setBackground(COLOR_ERROR_BACKGROUND);
    }

    /**
     * Called on form "Clear" action.
     *
     * <p>Iterates through errorFieldMap, sets all properties back to original and then clears the HashMap
     * so it is in an initial state again for fresh use.
     *
     * @see #errorFieldMap
     * @see #clearErrorField(javax.swing.text.JTextComponent)
     */
    public static void resetErrorFields() {
        for (Map.Entry<JTextComponent, Color> errorField : errorFieldMap.entrySet()) {
            clearErrorField(errorField.getKey());
        }
        errorFieldMap.clear();
    }
}
