package validation;

/**
 * Basic exception class for the validator ... this is a work in progress and likely to be completely redone.
 *
 * <p>@author Ben Murray (validation@my.stlcc.edu)<br />
 * IS:251-650 Introduction to Java Programming
 */
public class InvalidInputException extends Throwable {
    public InvalidInputException () { }

    /**
     * @param inputErrorMessage    Prints error message String to console
     */
    public void toString(String inputErrorMessage) {
        System.out.println("\n" + inputErrorMessage);
    }

}