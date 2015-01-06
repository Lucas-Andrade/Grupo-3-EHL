package main.java.cli.utils.exceptions.factoryexceptions;


import java.text.MessageFormat;


/**
 * Thrown when the parameters container received does not have one of the
 * parameters needed to perform a certain operation.
 *
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
@SuppressWarnings( "serial" )
public class MissingRequiredParameterException extends CommandFactoryException
{
	
	/**
	 * Constructs a {@link MissingRequiredParameterException} with the message <i>«Required
	 * parameter with name {@code parameterName} missing.»</i>.
	 * 
	 * @param parameterName
	 *            The missing parameter's name.
	 */
	public MissingRequiredParameterException( String parameterName ) {
		
		super( MessageFormat.format(
				"Required parameter with name {0} missing.", parameterName ) );
	}
}