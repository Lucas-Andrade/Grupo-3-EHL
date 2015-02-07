package exceptions;



/**
 * Superclass for all {@link Database} exceptions.
 *
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
@SuppressWarnings( "serial" )
public class DatabaseException extends Exception {
    
    /**
     * Constructs a {@link DatabaseException} with no detail message.
     */
    public DatabaseException() {
        
    }
    
    /**
     * Constructs a {@link DatabaseException} with the specified detail message.
     * 
     * @param message
     *            The detail message.
     */
    public DatabaseException( String message ) {
        
        super( message );
    }
    
}
