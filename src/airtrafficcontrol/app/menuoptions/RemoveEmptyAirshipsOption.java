package airtrafficcontrol.app.menuoptions;


import java.util.Scanner;
import airtrafficcontrol.app.AirTrafficControlAppToolbox;
import airtrafficcontrol.app.appforconsole.AirTrafficControlAppForConsole;
import airtrafficcontrol.app.appforconsole.ConsoleDataToolbox;
import airtrafficcontrol.app.exceptions.DatabaseNotFoundException;
import airtrafficcontrol.app.exceptions.InvalidArgumentException;
import airtrafficcontrol.app.utils.towerControl.Database;


/**
 * This class represents the option with the title
 * {@code Remove all zero-passenger-flights.} of an Air Traffic Control app for
 * console.
 * 
 * <p style="font-size:16">
 * <b>Description</b>
 * </p>
 * <p>
 * The purpose of this class is to perform the action of removing all the
 * passenger-flights that have zero passengers from the
 * {@link AirTrafficControlAppToolbox app}'s flight's internal database. For
 * more information, read the documentation of method
 * {@link #executeToConsole() executeToConsole}.
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
public class RemoveEmptyAirshipsOption extends Option
{
	
	
	// CAMPO DA CLASE
	
	/**
	 * An instance of type RemoveEmptyAirshipsOption.
	 */
	private static RemoveEmptyAirshipsOption instance = new RemoveEmptyAirshipsOption();
	
	/**
	 * The database where to search for the flight.
	 */
	private Database flightsDB = null;
	
	
	
	// METODO CONSTRUTOR e METODO getInstance()
	
	
	/**
	 * Creates a new instance of type RemoveEmptyAirshipsOption and sets up the
	 * final values of the fields {@code title} and {@code description}.
	 */
	public RemoveEmptyAirshipsOption() {
		super(
				"Remove all zero-passenger-flights.",
				"Removes all the passenger-flights that have zero passengers from the app's flights' database." );
	};
	
	/**
	 * Returns an instance of type RemoveEmptyAirshipsOption, without creating a
	 * new one.
	 * 
	 * <p>
	 * Note that the instances of this type have no differentiating properties:
	 * the only instance fields are {@code title} and {@code description} and
	 * their values are the same for all instances. This method lets you reuse
	 * an already created instance instead of always creating new ones with the
	 * {@link #RemoveEmptyAirshipsOption() constructor}.
	 * </p>
	 * 
	 * @return A instance of type RemoveEmptyAirshipsOption.
	 */
	public static RemoveEmptyAirshipsOption getInstance() {
		return instance;
	}
	
	
	
	// AC��O
	
	
	
	/**
	 * Removes all the passenger-flights that have zero passengers from the
	 * app's flight's internal database. Before removing them, asks the user if
	 * he really wants to remove the flights or if he wants to abort this
	 * operation.
	 * <p>
	 * Uses the
	 * {@link airtrafficcontrol.app.AirTrafficControlAppToolbox#flightsDB
	 * FLIGHTSDB} of {@code app} (the app's {@link ConsoleDataToolbox} field).
	 * </p>
	 * 
	 * @param app
	 *            The app's {@link ConsoleDataToolbox} field.
	 * @throws InvalidArgumentException
	 *             If {@code app} is{@code null}.
	 */
	public void executeToConsole( AirTrafficControlAppForConsole app )
			throws InvalidArgumentException {
		
		if( app == null )
			throw new InvalidArgumentException( "INVALID NULL APP!" );
		
		// save app's database as a field so execute can access it
		flightsDB = app.tools.flightsDB;
		
		
		// ask for confirmation
		System.out.print( new StringBuffer(
				" Are you sure you want to remove all" )
				.append( "\n passenger-flights with zero passengers?" )
				.append( "\n\n Type YES if so or type any other" )
				.append( "\n key otherwhise and press Enter." )
				.append( "\n\nRemove? " ).toString() );
		
		@SuppressWarnings( "resource" )
		Scanner in = new Scanner( System.in );
		
		
		// if user confirms remove all
		if( in.nextLine().equals( "YES" ) )
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
		
		// if user writes other than "YES" in console
		else System.out.println( "OPERATION ABORTED!" );
		
		// didn't close scanner on purpose
	}
	
	/**
	 * Removes all the passenger-flights that have zero passengers from
	 * {@code flightsDB}.
	 * 
	 * @return A message on the operation being successfully concluded.
	 * @throws DatabaseNotFoundException
	 */
	public String execute() throws DatabaseNotFoundException {
		
		if( flightsDB == null )
			throw new DatabaseNotFoundException( "DATABASE NOT FOUND!" );
		
		int numFlightsRemoved = flightsDB.removeAirplanesWithZeroPassengers();
		
		return new StringBuffer( "DONE! " ).append( numFlightsRemoved )
				.append( " passenger-flights with zero passengers" )
				.append( "\nsuccessfully removed!" ).toString();
	};
	
}