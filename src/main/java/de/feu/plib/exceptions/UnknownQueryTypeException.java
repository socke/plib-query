package de.feu.plib.exceptions;

/**
 * TODO document file
 */
public class UnknownQueryTypeException extends RuntimeException  {

    public static final String REASON = "Type of query is unknown or not supported. ";

    public UnknownQueryTypeException() {
        super(REASON);
    }

    public UnknownQueryTypeException(String s) {
        super(REASON + s);
    }

    public UnknownQueryTypeException(String s, Throwable throwable) {
        super(REASON + s, throwable);
    }
}
