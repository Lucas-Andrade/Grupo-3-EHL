package main.java.utils.exceptions;


import java.text.MessageFormat;
import main.java.utils.exceptions.parsingexceptions.ParsingException;


/**
 * Thrown when the value received for a certain parameter is
 *
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
@SuppressWarnings( "serial" )
public class InvalidParameterValueException extends ParsingException {
    
    /**
     * Constructs a {@link InvalidParameterValueException} with the message <i>«Required parameter
     * with name {@code parameterName} has invalid value {@code parameterValue}.»</i>.
     * 
     * @param parameterName
     *            The parameter's name.
     * @param parameterValue
     *            The invalid parameter's value.
     */
    public InvalidParameterValueException( String parameterName, String parameterValue ) {
        
        super( MessageFormat.format( "Required parameter with name {0} has invalid value {1}.",
                                     parameterName, parameterValue ) );
    }
    
    /**
     * Constructs a {@link InvalidParameterValueException} with the message:
     * <p>
     * <i>Required parameter with name {@code parameterName} has invalid value
     * {@code parameterValue}. <br />
     * {@code extraInfo}.</i>
     * </p>
     * 
     * @param parameterName
     *            The parameter's name.
     * @param parameterValue
     *            The invalid parameter's value.
     * @param extraInfo
     *            A detail message about the invalidity of the parameter value.
     */
    public InvalidParameterValueException( String parameterName, String parameterValue,
                                           String extraInfo ) {
        
        super( MessageFormat.format( "Required parameter with name {0} has invalid value {1}."
                                     + "\n {2}", parameterName, parameterValue, extraInfo ) );
    }
    
    /**
     * Constructs an {@link InvalidParameterValueException} with the message <i>«Required parameter
     * with name {@code parameterName} has invalid value {@code parameterValue}.»</i> and the
     * specified cause.
     * 
     * @param parameterName
     *            The parameter's name.
     * @param parameterValue
     *            The invalid parameter's value.
     * @param cause
     *            The cause (saved for later retrieval by the {@link #getCause()} method).
     *            {@code null} values are allowed and indicate that the cause is nonexistent or
     *            unknown.
     */
    public InvalidParameterValueException( String parameterName, String parameterValue,
                                           Throwable cause ) {
        
        super( MessageFormat.format( "Required parameter with name {0} has invalid value {1}.",
                                     parameterName, parameterValue ), cause );
    }
    
    /**
     * Constructs a {@link InvalidParameterValueException} with the message:
     * <p>
     * <i>Required parameter with name {@code parameterName} has invalid value
     * {@code parameterValue}. <br />
     * {@code extraInfo}.</i>
     * </p>
     * and the given cause.
     * 
     * @param parameterName
     *            The parameter's name.
     * @param parameterValue
     *            The invalid parameter's value.
     * @param extraInfo
     *            A detail message about the invalidity of the parameter value.
     * @param cause
     *            The cause of this exception.
     */
    public InvalidParameterValueException( String parameterName, String parameterValue,
                                           String extraInfo, Throwable cause ) {
        
        super( MessageFormat.format( "Required parameter with name {0} has invalid value {1}."
                                     + "\n {2}", parameterName, parameterValue, extraInfo ), cause );
    }
    
    /**
     * Constructs an {@link InvalidParameterValueException} with the specified detail message and
     * the specified cause.
     * 
     * @param message
     *            The detail message.
     * @param cause
     *            The cause (saved for later retrieval by the {@link #getCause()} method).
     *            {@code null} values are allowed and indicate that the cause is nonexistent or
     *            unknown.
     */
    public InvalidParameterValueException( String message, Throwable cause ) {
        
        super( message, cause );
    }
    
}
