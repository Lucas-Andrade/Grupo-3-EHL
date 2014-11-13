package airtrafficcontrol.app.menuoptions;


import airtrafficcontrol.app.appforconsole.AirTrafficControlAppForConsole;
import airtrafficcontrol.app.appforconsole.ConsoleDataToolbox;
import airtrafficcontrol.app.appforconsole.ConsoleInputHandler;
import airtrafficcontrol.app.exceptions.DatabaseNotFoundException;
import airtrafficcontrol.app.exceptions.FlightNotFoundInDatabaseException;
import airtrafficcontrol.app.exceptions.InvalidArgumentException;
import airtrafficcontrol.app.exceptions.InvalidFlightIDException;
import airtrafficcontrol.app.utils.Database;


/**
 * This class represents the option with the title
 * {@code Remove a flight manually.} of an Air Traffic Control app.
 * 
 * <p style="font-size:16">
 * <b>Description</b>
 * </p>
 * <p>
 * The purpose of this class is to perform the action of removing an airship
 * manually from the app's internal database of flights. For more information,
 * read the documentation of method {@link #execute() execute} .
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
public class RemoveAFlightOption extends Option
{
	
	
	// CAMPO DA CLASE
	
	/**
	 * An instance of type RemoveAFlightOption.
	 */
	private static RemoveAFlightOption instance = new RemoveAFlightOption();
	
	/**
	 * The flightID of the flight to be removed.
	 */
	private String flightID = null;
	
	/**
	 * The database where to search for the flight.
	 */
	private Database flightsDB = null;
	
	
	// METODO CONSTRUTOR e METODO getInstance()
	
	
	/**
	 * Creates a new instance of type RemoveAFlightOption and sets up the final
	 * values of the fields {@code title} and {@code description}.
	 */
	public RemoveAFlightOption() {
		super(
				"Remove a flight manually.",
				"Asks the user to choose the flight from the flights' database of this app which he wants to remove from the database." );
	};
	
	/**
	 * Returns an instance of type RemoveAFlightOption, without creating a new
	 * one.
	 * 
	 * <p>
	 * Note that the instances of this type have no differentiating properties:
	 * the only instance fields are {@code title} and {@code description} and
	 * their values are the same for all instances. This method lets you reuse
	 * an already created instance instead of always creating new ones with the
	 * {@link #RemoveAFlightOption() constructor}.
	 * </p>
	 * 
	 * @return A instance of type RemoveAFlightOption.
	 */
	public static RemoveAFlightOption getInstance() {
		return instance;
	}
	
	
	
	// AC��O
	
	
	
	/**
	 * Removes a plane manually from the app's internal database of flights.
	 * Asks the user to choose the flight from the flights' database of this app
	 * which he wants to remove from the database.
	 * <p>
	 * Uses the
	 * {@link airtrafficcontrol.app.AirTrafficControlAppToolbox#flightsDB
	 * FLIGHTSDB} of {@code app}, the app's {@link ConsoleDataToolbox} field.
	 * </p>
	 * 
	 * @param app
	 *            The app's {@link ConsoleDataToolbox} field.
	 * @throws InvalidArgumentException
	 *             If the {@code app} is {@code null}.
	 */
	public void executeToConsole( AirTrafficControlAppForConsole app )
			throws InvalidArgumentException {
		
		if( app == null )
			throw new InvalidArgumentException( "INVALID APP FOR CONSOLE!" );
		
		// save app's database as a field so execute can access it
		flightsDB = app.tools.flightsDB;
		
		
		
		// ask the user for a flightID
		String instruction = new StringBuilder(
				" Type the flightID of the flight you want" )
				.append( "\n to remove and press Enter." )
				.append( "\n\nRemove flight with flightID: " ).toString();
		try
		{
			flightID = ConsoleInputHandler
					.get_AFlightIDExistentInACertainDatabase_FromUser(
							flightsDB, instruction );
		}
		catch( DatabaseNotFoundException e )
		{
			flightsDB = null;
			// this catch block will never run as, by ensuring app!=null, we
			// assured flightsDB was assigned with a app's for console database
			// which is always null by construction
		}
		
		
		// either abort execution
		if( flightID.equals( "ABORT" ) )
			System.out.print( "ABORTED OPERATION!" );
		
		
		// or remove the flight from database
		else
		{
			try
			{
				System.out.println( execute() );
			}
			catch( FlightNotFoundInDatabaseException
					| DatabaseNotFoundException | InvalidFlightIDException e )
			{
				System.out.print( e.getMessage() );
				// this catch block will never happen as
				// get_AFlightIDExistentInACertainDatabase_FromUser obtained a
				// valid flightID that certainly is in flightsDB, which is also
				// not null by the app's construction
			}
		}
		
		
	}
	
	/**
	 * Removes a flight from a flights' database and returns a message on the
	 * operation being successfully concluded.
	 * 
	 * @return A message on the operation being successfully concluded.
	 * @throws DatabaseNotFoundException
	 *             If {@link RemoveAFlightOption#flightsDB flightsDB} is
	 *             {@code null}.
	 * @throws FlightNotFoundInDatabaseException
	 *             If {@link RemoveAFlightOption#flightID flightID} does not
	 *             correspond to any flight stored in
	 *             {@link RemoveAFlightOption#flightsDB flightsDB}.
	 * @throws InvalidFlightIDException
	 *             If {@link RemoveAFlightOption#flightID flightID} is
	 *             {@code null}.
	 */
	public String execute() throws FlightNotFoundInDatabaseException,
			DatabaseNotFoundException, InvalidFlightIDException {
		
		if( flightsDB == null )
			throw new DatabaseNotFoundException( "INVALID NULL DATABASE!" );
		if( flightID == null )
			throw new FlightNotFoundInDatabaseException(
					"INVALID NULL FlightID!" );
		
		
		if( flightsDB.removeAirplane( flightID ) )
			return "DONE! Flight with identifier " + flightID
					+ "\nremoved successfully!";
		else throw new FlightNotFoundInDatabaseException(
				"FLIGHT NOT FOUND IN DATABASE!" );
	};
	
}