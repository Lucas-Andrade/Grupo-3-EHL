package airtrafficcontrol.app.appforconsole.exceptions;

	/**
	 * Exception thrown when it was received an invalid argument. This class will
	 * substitute the unchecked exceptions of the type IllegalArgumentException and
	 * NullPointerException.
	 *
	 * @author Eva Gomes
	 * @author Hugo Leal
	 * @author Lucas Andrade
	 */
	@SuppressWarnings( "serial" )
	public class InvalidArgumentException extends Exception
	{
		
		/**
		 * Constructs a new {@code InvalidArgumentException} with {@code null} as
		 * its detail message.
		 */
		public InvalidArgumentException() {
			super();
		}
		
		/**
		 * Constructs a new {@code InvalidArgumentException} with the specified
		 * detail message.
		 * 
		 * @param message
		 *            The detail message.
		 */
		public InvalidArgumentException( String message ) {
			super( message );
		}
		
	}


