package app;


import utils.Airliner;
import utils.Airship;
import utils.Database;
import utils.ReportGenerator;
import utils.Transport;


/**
 * This class represents the option with the title
 * {@code Consult a flight's details.} of an Air Traffic Control app.
 * 
 * <p style="font-size:16">
 * <b>Description</b>
 * </p>
 * <p>
 * The purpose of this class is to perform the action of consulting the details
 * of a specific flight from the app's flight's internal database and printing
 * them in the console. For more information, read the documentation of method
 * {@link #execute() execute} .
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
public class ConsultFlightDetailsOption extends Option
{
	
	
	// CAMPO DA CLASE
	
	/**
	 * An instance of type ConsultFlightDetailsOption.
	 */
	private static ConsultFlightDetailsOption instance = new ConsultFlightDetailsOption();
	
	/**
	 * The flightID of the flight to be consulted.
	 */
	private String flightID = null;
	
	/**
	 * The database where to search for the flight.
	 */
	private Database flightsDB = null;
	
	
	// MÉTODO CONSTRUTOR e MÉTODO getInstance()
	
	
	/**
	 * Creates a new instance of type ConsultFlightDetailsOption and sets up the
	 * final values of the fields {@code title} and {@code description}.
	 */
	public ConsultFlightDetailsOption() {
		super( "Consult a flight's details.", "d" );
	};
	
	/**
	 * Returns an instance of type ConsultFlightDetailsOption, without creating
	 * a new one.
	 * 
	 * <p>
	 * Note that the instances of this type have no differentiating properties:
	 * the only instance fields are {@code title} and {@code description} and
	 * their values are the same for all instances. This method lets you reuse
	 * an already created instance instead of always creating new ones with the
	 * {@link #ConsultFlightDetailsOption() constructor}.
	 * </p>
	 * 
	 * @return A instance of type ConsultFlightDetailsOption.
	 */
	public static ConsultFlightDetailsOption getInstance() {
		return instance;
	}
	
	
	
	// ACÇÃO
	
	
	
	/**
	 * Consults the details of a specific flight from the app's flight's
	 * internal database and prints them in the console. Asks the user to choose
	 * the flight from the flights' database of this app which he wants to read
	 * about.
	 * <p>
	 * Uses the {@link app.AirTrafficControlAppTools#flightsDB FLIGHTSDB} of
	 * {@code app}, the app's {@link AirTrafficControlAppForConsoleTools} field.
	 * </p>
	 * 
	 * @param app
	 *            The app's {@link AirTrafficControlAppForConsoleTools} field.
	 */
	public void executeToConsole( AirTrafficControlAppForConsoleTools app ) {
		
		flightsDB = app.getFlightsDB();
		
		// asks the user for a flightID
		String instruction = new StringBuilder(
				" Type the flightID of the flight whose details you" )
				.append( "\n want to consult and press Enter." )
				.append( "\n\nConsult details of flight with flightID: " )
				.toString();
		try
		{
			flightID = ConsoleInputTreatment
					.get_AFlightIDExistentInACertainDatabase_FromUser(
							flightsDB, instruction );
		}
		catch( DatabaseNotFoundException e )
		{
			System.out.println( "DATABASE NOT FOUND!" );
		}
		
		
		// either abort execution
		if( flightID.equals( "ABORT" ) )
			System.out.print( "ABORTED OPERATION!" );
		
		
		// consult the flight's details from database
		else
		{
			try
			{
				System.out.println( execute() );
			}
			catch( FlightNotFoundInDatabaseException e )
			{
				System.out.print( "FLIGHT NOT FOUND!" );
			}
			catch( DatabaseNotFoundException e )
			{
				System.out.println( "DATABASE NOT FOUND!" );
			}
		}
		
		
	}
	
	
	/**
	 * Consults the details of a specific flight from the app's flight's
	 * internal database and prints them in the console.
	 * 
	 * @return A message on the operation being successfully concluded.
	 * @throws DatabaseNotFoundException
	 * @throws FlightNotFoundInDatabaseException
	 */
	public String execute() throws FlightNotFoundInDatabaseException,
			DatabaseNotFoundException {
		
		if( flightsDB == null )
			throw new DatabaseNotFoundException();
		if( flightID == null )
			throw new FlightNotFoundInDatabaseException();
		
		String details;
		try
		{
			Airship airship = flightsDB.getAirplane( flightID );
			String[] info = airship.toStringArray();
			
			StringBuilder builder = new StringBuilder(
					"\n\n   FLIGHT DETAILS\n\n" ).append( info[0] ).append(
					info[1] );
			if( airship instanceof Airliner )
				builder.append( ((Airliner)airship).getPassengersNumber() );
			if( airship instanceof Transport )
				builder.append( ((Transport)airship).hasArmament() );
			details = builder.append( info[2] ).toString();
		}
		catch( NullPointerException e )
		{
			details = "INFORMATION NOT FOUND";
		}
		
		
		return flightID;
		
	};
	
}