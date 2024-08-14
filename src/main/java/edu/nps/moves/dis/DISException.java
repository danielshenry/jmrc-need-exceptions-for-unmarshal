package edu.nps.moves.dis;

/**
 * DISException encapsulates exceptions that occur within the open-dis library.
 * @author babrown
 *
 */
public class DISException extends Exception {

    private static final long serialVersionUID = 1L;

    public DISException() {
        super();
    }

    public DISException(String message) {
        super( message );
    }

    public DISException(Throwable cause) {
        super( cause );
    }

    public DISException(String message, Throwable cause) {
        super( message, cause );
    }

    public DISException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super( message, cause, enableSuppression, writableStackTrace );
    }

}
