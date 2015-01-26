package main.java.utils.exceptions.parsingexceptions;


/**
 * Thrown when a parsing error occurs, result from trying to parse a string-command with illegal
 * syntax.
 *
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
@SuppressWarnings( "serial" )
public class InvalidCommandSyntaxException extends ParsingException {
    
    /**
     * Constructs a {@link InvalidCommandSyntaxException} with the specified detail message.
     * 
     * @param message
     *            The detail message.
     */
    public InvalidCommandSyntaxException( String message ) {
        
        super( message );
    }
}
