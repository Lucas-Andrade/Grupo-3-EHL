package airtrafficcontrol.app.appforcommandline.exceptions.databaseexceptions;


/**
 * Base class for all {@link Database} exceptions.
 *
 */
@SuppressWarnings( "serial" )
public class DatabaseException extends Exception
{
	
	public DatabaseException() {
		super();
	}
	
	public DatabaseException( String message ) {
		super( message );
	}
	
	public DatabaseException( String message, Throwable cause ) {
		super( message, cause );
	}
}
