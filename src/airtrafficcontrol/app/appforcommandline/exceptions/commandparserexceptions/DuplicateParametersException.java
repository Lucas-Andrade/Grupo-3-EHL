package airtrafficcontrol.app.appforcommandline.exceptions.commandparserexceptions;


import java.text.MessageFormat;


/**
 * Thrown when a parsing error occurs, result of having been received several
 * values for the same parameter.
 *
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
@SuppressWarnings( "serial" )
public class DuplicateParametersException extends CommandParserException
{
	
	/**
	 * Constructs a {@link DuplicateParametersException} with the message
	 * <i>«Required parameter with name {@code parameterName} has multiple
	 * values.»</i>.
	 * 
	 * @param parameterName
	 *            The parameter's name.
	 */
	public DuplicateParametersException( String parameterName ) {
		super( MessageFormat.format(
				"Parameter with name {0} has multiple values.", parameterName ) );
	}
}
