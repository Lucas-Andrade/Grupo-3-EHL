package main.java.cli.utils.exceptions.conversorsexceptions;


import main.java.cli.outputformatters.totranslatableconversors.ToTranslatableConversor;


/**
 * Thrown when {@link ToTranslatableConversor} cannot convert the instance given
 * as argument in the method {@link ToTranslatableConversor#convert(Object)}
 * for its type is not one of the specified in the documentation.
 *
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 * @see ToTranslatableConversor
 */
@SuppressWarnings( "serial" )
public class UnknownTypeException extends Exception
{
	
	/**
	 * Constructs an {@link UnknownTypeException} with the specified detail
	 * message.
	 * 
	 * @param message
	 *            The detail message.
	 */
	public UnknownTypeException( String message ) {
		super( message );
	}
	
}
