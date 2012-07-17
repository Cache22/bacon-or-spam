package validation;

import java.awt.*;

/**
 * Static constants for the Validator class. I created a separate Constants class to simplify
 * adding future common messages and regular expression patterns without cluttering up the main
 * Validation class.
 *
 * This will probably be refactored as an {@code enum} at some point
 *
 * <p>@author Ben Murray (validation@my.stlcc.edu)<br />
 * IS:251-650 Introduction to Java Programming
 * @see Validator
 */
public final class Constants {
    private Constants() {}

    // Pattern Matching Constants (Regular Expressions)

    /**
     * Allows only Yes/No in the form of <code>Y, y, N, or N</code>
     */
    public static final String MATCH_CHOICE_YN = "[yYnN]";

    /**
     * Verifies social security number. Will allow it to be entered with or without hyphens
     */
    public static final String MATCH_SSN = "[0-9]{3}-?[0-9]{2}-?[0-9]{4}";

    /**
     * Predefined regular expression<br />
     * The email regular expression attempts to handle most characters allowed per <a target="_blank" href="http://tools.ietf.org/html/rfc3696#section-3">RFC3696 Section 3}</a>, however it does
     * not support escaped or quoted strings in the email name.
     *
     * <p>non-escaped regex in comments for reference:
     * <blockquote><pre>
     *      ([\w\+!\$#%&'*\-/=?\^`{}|~]+\.{0,1})+@{1}(\w|-)+(\.{1}\w+)+
     * </pre></blockquote>
     */
    public static final String MATCH_EMAIL = "([\\w\\+!\\$#%&'*\\-/=?\\^`{}|~]+\\.{0,1})+@{1}(\\w|-)+(\\.{1}\\w+)+";

    /**
     * Specifies that an empty value <b>is not</b> allowed
     */
    public static final String MATCH_NOT_EMPTY = ".+";

    /**
     * Specifies that an empty value <b>is</b> allowed
     */
    public static final String MATCH_ANY = ".*";

    /**
     * Specifies that a single character is allowed
     */
    public static final String MATCH_CHAR = ".?";

    // Error message constants

    /**
     * Generic {@code char} error message
     */
    public static final String MSG_INVALID_CHAR = "Please enter a valid character.";
    /**
     * Generic {@code String} error message
     */
    public static final String MSG_INVALID_STRING = "Your input is not valid. Please try again.";
    /**
     * Generic {@code Integer} error message
     */
    public static final String MSG_INVALID_INT = "The value entered must be an integer. Please try again.";
    /**
     * Generic {@code Double} error message
     */
    public static final String MSG_INVALID_DECIMAL = "The value entered must be a decimal. Please try again.";

    /**
     * Defines JTextComponent background color for error highlighting. (Faded yellow)
     */
    public static final Color COLOR_ERROR_BACKGROUND = new Color(255, 255, 180);
}
