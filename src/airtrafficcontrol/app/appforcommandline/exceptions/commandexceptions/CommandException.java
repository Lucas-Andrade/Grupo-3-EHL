package airtrafficcontrol.app.appforcommandline.exceptions.commandexceptions;


/**
 * Base class for all {@link Command} exceptions.
 *
 */
@SuppressWarnings( "serial" )
public class CommandException extends Exception
{
	
	public CommandException() {
		super();
	}
	
	public CommandException( String message ) {
		super( message );
	}
	
	public CommandException( String message, Throwable cause ) {
		super( message, cause );
	}
	
}
