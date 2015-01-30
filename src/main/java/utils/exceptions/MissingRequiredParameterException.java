package main.java.utils.exceptions;


import java.text.MessageFormat;


/**
 * Thrown when the parameters container received does not have one of the parameters needed to
 * perform a certain operation.
 *
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
@SuppressWarnings( "serial" )
public class MissingRequiredParameterException extends Exception {
    
    /**
     * Constructs a {@link MissingRequiredParameterException} with the message <i>«Required
     * parameter with name {@code parameterName} missing.»</i>.
     * 
     * @param parameterName
     *            The missing parameter's name.
     */
    public MissingRequiredParameterException( String parameterName ) {
        
        super( MessageFormat.format( "Required parameter with name {0} missing.", parameterName ) );
    }
    
    
    /**
     * Constructs an {@link MissingRequiredParameterException} with the message <i>«Required
     * parameter with name {@code parameterName} missing.»</i> and the specified cause.
     * 
     * @param parameterName
     *            The missing parameter's name.
     * @param cause
     *            The cause (saved for later retrieval by the {@link #getCause()} method).
     *            {@code null} values are allowed and indicate that the cause is nonexistent or
     *            unknown.
     */
    public MissingRequiredParameterException( String parameterName, Throwable cause ) {
        super( parameterName, cause );
    }
    
}