package airtrafficcontrol.app.appforconsole.menuoptions;


import java.io.IOException;
import java.util.Scanner;
import airtrafficcontrol.app.appforconsole.AirTrafficControlAppForConsole;
import airtrafficcontrol.app.exceptions.DatabaseNotFoundException;
import airtrafficcontrol.app.exceptions.InvalidArgumentException;
import airtrafficcontrol.app.utils.towerControl.Database;
import airtrafficcontrol.app.utils.towerControl.ReportGenerator;


/**
 * This class represents the option with the title
 * {@code Report altitude transgressions.} of an Air Traffic Control app.
 * 
 * <p style="font-size:16">
 * <b>Description</b>
 * </p>
 * <p>
 * The purpose of this class is to perform the action of printing a list of the
 * flights whose airships are currently transgressing the established air
 * corridors. For more information, read the documentation of method
 * {@link #execute() execute}.
 * </p>
 * 
 * <p style="font-size:16">
 * <b> Implementation notes</b>
 * </p>
 * <ul>
 * <li>Instances of this class are immutable.</li>
 * <li>All instances of this class share the same {@code title} and
 * {@code description}.
 * </ul>
 *
 * @author Eva Gomes
 * @author Hugo Leal
 * @author Lucas Andrade
 */
public class ReportTransgressionsOption extends Option
{
	
	// INSTANCE FIELDS
	
	/**
	 * An instance of type PrintTransgressionsReportOption.
	 */
	private static ReportTransgressionsOption instance = new ReportTransgressionsOption();
	
	/**
	 * The database where to search for the transgressing flights.
	 */
	private Database flightsDB = null;
	
	/**
	 * It is true if the execute() method must generate a report in .txt about
	 * the transgressing airships; and it is false if the report is only to be
	 * returned as a string.
	 */
	private boolean printToTxt = false;
	
	
	
	// CONSTRUCTOR AND getInstance()
	
	/**
	 * Creates a new instance of type PrintTransgressionsReportOption and sets
	 * up the final values of the fields {@code title} and {@code description}.
	 */
	public ReportTransgressionsOption() {
		super( "Report altitude transgressions." );
	};
	
	/**
	 * Returns an instance of type PrintTransgressionsReportOption, without
	 * creating a new one.
	 * 
	 * <p>
	 * Note that the instances of this type have no differentiating properties:
	 * the only instance fields are {@code title} and {@code description} and
	 * their values are the same for all instances. This method lets you reuse
	 * an already created instance instead of always creating new ones with the
	 * {@link #PrintTransgressionsReportOption() constructor}.
	 * </p>
	 * 
	 * @return A instance of type PrintTransgressionsReportOption.
	 */
	public static ReportTransgressionsOption getInstance() {
		return instance;
	}
	
	
	
	// EXECUTE METHODS
	
	/**
	 * Prints a list of the flights whose airships are currently transgressing
	 * the established air corridors.
	 */
	public void executeToConsole( AirTrafficControlAppForConsole app )
			throws InvalidArgumentException {
		
		if( app == null )
			throw new InvalidArgumentException( "INVALID NULL APP!" );
		
		// save app's database as a field so execute can access it
		flightsDB = app.flightsDB;
		
		
		// ask for confirmation
		System.out.print( new StringBuffer(
				" Do you want to get the trangressions report" )
				.append( "\n on a .txt file as well?" )
				.append( "\n\n Type YES if so or type any other" )
				.append( "\n key otherwhise and press Enter." )
				.append( "\n\n Print to .txt?" ).toString() );
		
		@SuppressWarnings( "resource" )
		Scanner in = new Scanner( System.in );
		String answerFromUser = in.nextLine();
		
		// if user confirms remove all
		if( answerFromUser.equals( "ABORT" ) )
		{}
		else
		{
			// save answer as a field so execute can access it
			if( answerFromUser.equals( "YES" ) )
				printToTxt = true;
			else printToTxt = false;
			
			try
			{
				System.out.print( execute() );
			}
			catch( DatabaseNotFoundException e )
			{
				System.out.print( e.getMessage() );
				// this catch block will never happen as
				// get_AFlightIDExistentInACertainDatabase_FromUser obtained a
				// valid flightID that certainly is in flightsDB, which is also
				// not null by the app's construction
			}
			catch( IOException e )
			{
				e.printStackTrace();
			}
			
		}
		
		// didn't close scanner on purpose
	}
	
	public String execute() throws IOException, InvalidArgumentException,
			DatabaseNotFoundException {
		
		if( flightsDB == null )
			throw new DatabaseNotFoundException( "DATABASE NOT FOUND!" );
		
		ReportGenerator reporter = new ReportGenerator();
		
		if( printToTxt )
			reporter.reportAirplanesOutOfCorridorToTxt( flightsDB );
		
		String[] transgressingAirships = reporter
				.reportAirplanesOutOfCorridor( flightsDB );
		StringBuilder reportToReturn = new StringBuilder( "" );
		for( String airshipID : transgressingAirships )
			reportToReturn.append( "\n" ).append( airshipID );
		
		return reportToReturn.toString();
	};
	
}