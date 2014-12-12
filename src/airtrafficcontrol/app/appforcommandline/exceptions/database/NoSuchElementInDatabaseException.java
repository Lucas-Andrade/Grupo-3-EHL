package airtrafficcontrol.app.appforcommandline.exceptions.database;




@SuppressWarnings( "serial" )
public class NoSuchElementInDatabaseException extends DatabaseException
{
	
	public NoSuchElementInDatabaseException( String message ) {
		super( message );
	}
}
