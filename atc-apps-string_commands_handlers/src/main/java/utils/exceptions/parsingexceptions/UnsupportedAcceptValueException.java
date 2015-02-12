package utils.exceptions.parsingexceptions;


/**
 * Thrown when a parsing error occurs due to receiving an invalid {@code Accept} parameter value
 * from the string-command.
 *
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
@SuppressWarnings( "serial" )
public class UnsupportedAcceptValueException extends ParsingException {
    
    /**
     * Constructs a {@link UnsupportedAcceptValueException} with the specified detail message.
     * 
     * @param message
     *            The detail message.
     */
    public UnsupportedAcceptValueException( String message ) {
    
        super( message );
    }
}
