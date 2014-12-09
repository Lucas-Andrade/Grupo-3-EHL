package airtrafficcontrol.deprecated;


import airtrafficcontrol.app.appforconsole.exceptions.InvalidOptionNumberException;
import airtrafficcontrol.app.appforconsole.utils.towerControl.Database;


/**
 * 
 * 
 *
 * @author Eva Gomes
 * @author Hugo Leal
 * @author Lucas Andrade
 */
public class ClassToTestSmallThingsThatWillBeRemoved
{
	
	private static Database flightsDatabase = new Database();
	
	@SuppressWarnings( "unused" )
	private static ClassToTestSmallThingsThatWillBeRemoved c;
	
	static
	{
		try
		{
			c = new ClassToTestSmallThingsThatWillBeRemoved( 3 );
		}
		catch( InvalidOptionNumberException e )
		{
			c = null;
		}
	}
	
	public void set( Database db ) {
		flightsDatabase = db;
	}
	
	public static void main( String[] args ) throws InvalidOptionNumberException {
		Database antes = flightsDatabase;
		new ClassToTestSmallThingsThatWillBeRemoved( 3 ).set( new Database() );
		System.out.println( antes == flightsDatabase );
	}
	
	public ClassToTestSmallThingsThatWillBeRemoved( int i )
			throws InvalidOptionNumberException {
		if( i < 0 )
			throw new InvalidOptionNumberException();
	}
	
}
