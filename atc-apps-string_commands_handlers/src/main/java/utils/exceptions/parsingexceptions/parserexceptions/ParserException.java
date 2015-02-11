package utils.exceptions.parsingexceptions.parserexceptions;


import utils.StringCommandsExecutor;
import utils.exceptions.parsingexceptions.ParsingException;


/**
 * Superclass for all {@link StringCommandsExecutor} exceptions.
 *
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
@SuppressWarnings( "serial" )
public class ParserException extends ParsingException {
    
    /**
     * Constructs a {@link ParserException} with no detail message.
     */
    public ParserException() {
        
    }
    
    /**
     * Constructs a {@link ParserException} with the specified detail message.
     * 
     * @param message
     *            The detail message.
     */
    public ParserException( String message ) {
        
        super( message );
    }
    
}
