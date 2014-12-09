package airtrafficcontrol.app.appforconsole.menuoptions;


import java.io.IOException;
import java.util.Scanner;
import airtrafficcontrol.app.appforconsole.AirTrafficControlAppForConsole;
import airtrafficcontrol.app.exceptions.DatabaseNotFoundException;
import airtrafficcontrol.app.exceptions.InvalidArgumentException;
import airtrafficcontrol.app.exceptions.InvalidFlightIDException;
import airtrafficcontrol.app.utils.towerControl.Database;
import airtrafficcontrol.app.utils.towerControl.ReportGenerator;


/**
 * This class represents the option with the title {@code Monitor Air Traffic.}
 * of an Air Traffic Control app.
 * 
 * <p style="font-size:16">
 * <b>Description</b>
 * </p>
 * <p>
 * The purpose of this class is to perform the action of monitoring the flights
 * in the list of scheduled flights that already took-off. For more information,
 * read the documentation of method {@link #execute() execute}.
 * </p>
 * 
 * <p style="font-size:16">
 * <b> Implementation notes</b>
 * </p>
 * <ul>
 * <li>Instances of this class are immutable.</li>
 * <li>All instances of this class share the same {@link #title} and
 * {@link #description}.
 * </ul>
 *
 * @author Eva Gomes
 * @author Hugo Leal
 * @author Lucas Andrade
 */
public class MonitorAirTrafficOption extends Option
{
	
	// CLASS FIELDS
	
	/**
	 * An instance of type MonitorAirTrafficOption.
	 */
	private static MonitorAirTrafficOption instance = new MonitorAirTrafficOption();
	
	/**
	 * The database where to search for the transgressing flights.
	 */
	private Database flightsDB = null;
	
	/**
	 * It is true if the execute() method must generate a report in .txt about
	 * the transgressing airships; and it is false if the report is only to be
	 * returned as a string.
	 */
	private boolean printTrangressionsToTxt = false;
	
	/**
	 * It is true if the execute() method must generate a report in .txt in the
	 * transgressing airships; and it is false if the report is only to be
	 * returned as a string.
	 */
	private boolean printOVNIsToTxt = false;
	
	
	
	// CONSTRUCTOR AND getInstance()
		
	/**
	 * Creates a new instance of type MonitorAirTrafficOption and sets up the
	 * final values of the fields {@code title} and {@code description}.
	 */
	public MonitorAirTrafficOption() {
		super( "Monitor Air Traffic." );
	};
	
	/**
	 * Returns an instance of type MonitorAirTrafficOption, without creating a
	 * new one.
	 * 
	 * <p>
	 * Note that the instances of this type have no differentiating properties:
	 * the only instance fields are {@code title} and {@code description} and
	 * their values are the same for all instances. This method lets you reuse
	 * an already created instance instead of always creating new ones with the
	 * {@link #MonitorAirTrafficOption() constructor}.
	 * </p>
	 * 
	 * @return A instance of type MonitorAirTrafficOption.
	 */
	public static MonitorAirTrafficOption getInstance() {
		return instance;
	}
	
	
	
	// EXECUTE METHODS
	
	/**
	 * Monitors the flights in the list of scheduled flights that already
	 * took-off, printing the informations in the console.
	 * 
	 * @throws InvalidArgumentException
	 */
	public void executeToConsole( AirTrafficControlAppForConsole app )
			throws InvalidArgumentException {
		
		if( app == null )
			throw new InvalidArgumentException( "INVALID NULL APP!" );
		
		// save app's database as a field so execute can access it
		flightsDB = app.flightsDB;
		
		@SuppressWarnings( "resource" )
		Scanner in = new Scanner( System.in );
		
		// ask for confirmation
		System.out.print( new StringBuffer(
				" Do you want to get the trangressions report" )
				.append( "\n on a .txt file as well?" )
				.append( "\n\n Type YES if so or type any other" )
				.append( "\n key otherwhise and press Enter." )
				.append( "\n\n Print trangressions to .txt? " ).toString() );
		String transgressionsToTxt = in.nextLine();
		if( transgressionsToTxt.equals( "ABORT" ) )
		{
			System.out.println( "ABORTED OPERATION!" );
			return;
		}
		if( transgressionsToTxt.equals( "YES" ) )
			printTrangressionsToTxt = true;
		else printTrangressionsToTxt = false;
		
		System.out.print( new StringBuffer(
				" Do you want to get the unknown airships" )
				.append( "\n report on a .txt file as well?" )
				.append( "\n\n Type YES if so or type any other" )
				.append( "\n key otherwhise and press Enter." )
				.append( "\n\n Print unknown airships to .txt? " ).toString() );
		String OVNIsToTxt = in.nextLine();
		if( OVNIsToTxt.equals( "ABORT" ) )
		{
			System.out.println( "ABORTED OPERATION!" );
			return;
		}
		if( OVNIsToTxt.equals( "YES" ) )
			printOVNIsToTxt = true;
		else printOVNIsToTxt = false;
		
		
		try
		{
			System.out.println( execute() );
		}
		catch( DatabaseNotFoundException | IOException
				| InvalidFlightIDException e )
		{
			e.printStackTrace();
		}
	}
		
	/**
	 * Monitors the flights in the list of scheduled flights that already
	 * took-off, printing the informations in the console.
	 * 
	 * @return A message on the operation being successfully concluded.
	 * @throws InvalidArgumentException
	 * @throws IOException
	 * @throws InvalidFlightIDException
	 */
	public String execute() throws DatabaseNotFoundException, IOException,
			InvalidArgumentException, InvalidFlightIDException {
		
		if( flightsDB == null )
			throw new DatabaseNotFoundException( "DATABASE NOT FOUND!" );
		
		ReportGenerator reporter = new ReportGenerator();
		
		if( printTrangressionsToTxt )
			reporter.reportAirplanesOutOfCorridorToTxt( flightsDB );
		if( printOVNIsToTxt )
			reporter.reportAirplanesWithUnknownFlightIDToTxt();
		
		
		String[] all = reporter.reportAll( flightsDB, "ActualizedCoordinates.txt" );
		String[] transgressions = reporter
				.reportAirplanesOutOfCorridor( flightsDB );
		String unread = reporter.reportemptyFields();
		String unknown = reporter.reportAirplanesWithUnknownFlightID();
		
		
		StringBuilder toReturn = new StringBuilder( "\n\nFLIGHTS ON AIR info\n" );
		for( String line : all )
			toReturn.append( "\n" ).append( line );
		
		toReturn.append( "\n\nFLIGHTS TRANGRESSING THE AIR CORRIDORS\n" );
		for( String line : transgressions )
			toReturn.append( "\n" ).append( line );
		
		toReturn.append(
				"\n\n Couldn't actualize the following flights' coordinates:\n" )
				.append( unread );
		
		toReturn.append( "\n\n Found the following unknow airships:\n" )
				.append( unknown );
		
		return toReturn.toString();
	}
	
	
}