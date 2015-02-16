package exceptions;


/**
 * Exception thrown when there is no logged-in user set and someone tries to reach it or use it.
 *
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
@SuppressWarnings( "serial" )
public class LoggedInUserMissingException extends Exception {
    
    
    /**
     * Constructs an {@link LoggedInUserMissingException} with the message <i>«No logged-in user
     * set.»</i>.
     */
    public LoggedInUserMissingException() {
    
        super( "No logged-in user set." );
    }
    
    
    /**
     * Constructs an {@link LoggedInUserMissingException} with the message <i>«No logged-in user
     * set.»</i> and the specified cause.
     * 
     * @param cause
     *            The cause (saved for later retrieval by the {@link #getCause()} method).
     *            {@code null} values are allowed and indicate that the cause is nonexistent or
     *            unknown.
     */
    public LoggedInUserMissingException( Throwable cause ) {
    
        super( "No logged-in user set.", cause );
    }
    
}
