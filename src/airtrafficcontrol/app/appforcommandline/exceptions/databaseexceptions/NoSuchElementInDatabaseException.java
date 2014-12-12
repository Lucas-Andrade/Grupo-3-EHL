package airtrafficcontrol.app.appforcommandline.exceptions.databaseexceptions;




@SuppressWarnings( "serial" )
public class NoSuchElementInDatabaseException extends DatabaseException
{
	
	public NoSuchElementInDatabaseException( String message ) {
		super( message );
	}
}
