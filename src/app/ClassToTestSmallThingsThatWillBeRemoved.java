package app;


import java.util.Calendar;
import utils.*;


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
	
	public static void main( String[] args ) {
		flightsDatabase.addAirplane( new Airship("", new GeographicalPosition(0,0,0), new FlightPlan(new Calendar(), new Calendar()) ));
	}
}
