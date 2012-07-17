package validation;

import javax.swing.*;
import javax.swing.text.JTextComponent;

/**
 *
 * {@code InvalidJTextComponentInputException} handles invalid input in a JTextComponent
 *
 * <p>Exception should be handled from the calling code using the methods provided
 *
 * <p>@author Ben Murray (validation@my.stlcc.edu)<br />
 * IS:251-650 Introduction to Java Programming
 *
 * @see Validator
 */
public class InvalidJTextComponentInputException extends IllegalArgumentException  {

    public InvalidJTextComponentInputException () {
    }

    /**
     * Highlights (changes the background color) of the JTextComponent that threw the exception
     *
     * @param jTextComponent    JTextComponent to apply highlighted background to
     * @see Validator#getInputFromJTextComponent(javax.swing.text.JTextComponent, String, String)
     * @see Validator#setErrorField(javax.swing.text.JTextComponent)
     *
     */
    public void setInvalidFieldBackground(JTextComponent jTextComponent){
        Validator.setErrorField(jTextComponent);
    }

    /**
     * Shows an error message dialogue box for the JTextComponent that threw the exception
     *
     * @param jTextComponent    JTextComponent with the invalid input
     * @param errorMessage      Message to display to the user
     * @see Validator#getInputFromJTextComponent(javax.swing.text.JTextComponent, String, String)
     */
    public void showErrorMessageDialog(JTextComponent jTextComponent, String errorMessage) {
        System.out.println("\n" + errorMessage);
        JOptionPane.showMessageDialog(jTextComponent, errorMessage, "Invalid Input", JOptionPane.ERROR_MESSAGE);
    }
}
