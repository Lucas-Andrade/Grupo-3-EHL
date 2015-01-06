package main.java.cli.utils.exceptions;


/**
 * Thrown when a internal operation produces exceptions that interrupt
 * {@link FactoryOfParametrizedCallables#newInstance(Map) newInstance} of a
 * certain factory.
 * <p>
 * May happen
 * <ol>
 * <li>when it was tried to get and/or invoke a method by reflection and one of
 * the following exceptions occurred: NoSuchMethodException,
 * NullPointerException, SecurityException, IllegalAccessException,
 * IllegalArgumentException, InvocationTargetException and
 * ExceptionInInitializerError;</li>
 * <li>when an {@link Optional} instance is representing a {@code null} value
 * and it shouldn't be possible for that value to be {@code null}.</li>
 * </ol>
 * </p>
 *
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
@SuppressWarnings( "serial" )
public class InternalErrorException extends Exception
{
	
	/**
	 * Constructs an {@link InternalErrorException} with the message <i>«Sorry!
	 * An internal error that was supposed to never happen happened.»</i>.
	 * 
	 * @param message
	 *            The detail message.
	 */
	public InternalErrorException() {
		
		super(
				"Sorry! An internal error that was supposed to never happen happened." );
	}
	
	/**
	 * Constructs an {@link InternalErrorException} with the message:
	 * <p>
	 * <i>«Sorry! An internal error that was supposed to never happen happened.
	 * <br/>
	 * {@code message}»</i>
	 * </p>
	 * 
	 * @param message
	 *            The detail message.
	 */
	public InternalErrorException( String message ) {
		
		super( message );
	}
}
