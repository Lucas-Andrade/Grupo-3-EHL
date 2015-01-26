package main.java.utils.exceptions.parsingexceptions.parserexceptions;


import java.text.MessageFormat;
import main.java.utils.exceptions.parsingexceptions.ParsingException;


/**
 * Thrown when a parsing error occurs, result of having been received several values for the same
 * parameter.
 *
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
@SuppressWarnings( "serial" )
public class DuplicateParametersException extends ParsingException {
    
    /**
     * Constructs a {@link DuplicateParametersException} with the message <i>«Parameter with name
     * {@code parameterName} was received more than once.»</i>.
     * 
     * @param parameterName
     *            The parameter's name.
     */
    public DuplicateParametersException( String parameterName ) {
        
        super( MessageFormat.format( "Parameter with name {0} was received more than once.",
                                     parameterName ) );
    }
}
