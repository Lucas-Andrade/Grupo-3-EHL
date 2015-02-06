package main.java.utils.exceptions.formattersexceptions;


import main.java.cli.outputformatters.totranslatableconverters.ToTranslatableConverter;


/**
 * Thrown when {@link ToTranslatableConverter} cannot convert the instance given as argument in the
 * method {@link ToTranslatableConverter#convert(Object)} for its type is not one of the specified
 * in the documentation.
 *
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 * @see ToTranslatableConverter
 */
@SuppressWarnings( "serial" )
public class UnknownTypeException extends Exception {
    
    
    /**
     * Constructs an {@link UnknownTypeException} with the specified detail message.
     * 
     * @param message
     *            The detail message.
     */
    public UnknownTypeException( String message ) {
    
        super( message );
    }
    
    /**
     * Constructs an {@link UnknownTypeException} with the specified detail message and cause.
     * 
     * @param message
     *            The detail message.
     * @param cause
     *            The {@link Throwable} that caused this exception.
     */
    public UnknownTypeException( String message, Throwable cause ) {
    
        super( message, cause );
    }
    
    
    
}
