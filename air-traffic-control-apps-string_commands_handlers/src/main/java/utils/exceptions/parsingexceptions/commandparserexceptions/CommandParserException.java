package utils.exceptions.parsingexceptions.commandparserexceptions;


import parsingtools.CommandParser;
import utils.exceptions.parsingexceptions.ParsingException;


/**
 * Superclass for all {@link CommandParser} exceptions.
 *
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
@SuppressWarnings( "serial" )
public class CommandParserException extends ParsingException {
    
    /**
     * Constructs a {@link CommandParserException} with no detail message.
     */
    public CommandParserException() {
    
    }
    
    /**
     * Constructs a {@link CommandParserException} with the specified detail message.
     * 
     * @param message
     *            The detail message.
     */
    public CommandParserException( String message ) {
    
        super( message );
    }
    
}
