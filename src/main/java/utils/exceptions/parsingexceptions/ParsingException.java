package main.java.utils.exceptions.parsingexceptions;



/**
 * Superclass for all {@link CommandFactory} exceptions.
 *
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
@SuppressWarnings( "serial" )
public class ParsingException extends Exception {
    
    /**
     * Constructs a {@link ParsingException} with no detail message.
     */
    public ParsingException() {
        
    }
    
    /**
     * Constructs a {@link ParsingException} with the specified detail message.
     * 
     * @param message
     *            The detail message.
     */
    public ParsingException( String message ) {
        
        super( message );
    }    

    /**
     * Constructs a {@link ParsingException} with the specified detail message and cause.
     * 
     * @param message
     *            The detail message.
     */
    public ParsingException( String message, Throwable cause ) {
        
        super( message, cause );
    }
    
}