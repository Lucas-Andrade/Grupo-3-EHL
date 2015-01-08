package main.java.utils.exceptions.formattersexceptions;


import main.java.cli.outputformatters.Translatable;
import main.java.cli.outputformatters.translators.Translator;


/**
 * Thrown when a {@link Translator} cannot encode the {@link Translatable}
 * instance given as argument in the method
 * {@link Translator#encode(Translatable)} for it does not follow the conventions
 * specified in the documentation.
 *
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 * @see Translatable
 */
@SuppressWarnings( "serial" )
public class UnknownTranslatableException extends Exception
{
	
	/**
	 * Constructs an {@link UnknownTranslatableException} with the specified
	 * detail message.
	 * 
	 * @param message
	 *            The detail message.
	 */
	public UnknownTranslatableException( String message ) {
		super( message );
	}
	
}
