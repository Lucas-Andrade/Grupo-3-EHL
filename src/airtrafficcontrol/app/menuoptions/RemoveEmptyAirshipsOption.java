package airtrafficcontrol.app.menuoptions;


import java.util.Scanner;
import airtrafficcontrol.app.AirTrafficControlAppToolbox;
import airtrafficcontrol.app.appforconsole.ConsoleDataToolbox;
import airtrafficcontrol.app.exceptions.DatabaseNotFoundException;


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
 * {@link AirTrafficControlAppToolbox app}'s flight's internal database. For more
 * information, read the documentation of method {@link #executeToConsole()
 * executeToConsole}.
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
	
	
	// MÉTODO CONSTRUTOR e MÉTODO getInstance()
	
	
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
	
	
	
	// ACÇÃO
	
	
	
	/**
	 * Removes all the passenger-flights that have zero passengers from the
	 * app's flight's internal database. Before removing them, asks the user if
	 * he really wants to remove the flights or if he wants to abort this
	 * operation.
	 * <p>
	 * Uses the {@link airtrafficcontrol.app.AirTrafficControlAppToolbox#flightsDB FLIGHTSDB} of
	 * {@code app} (the app's {@link ConsoleDataToolbox}
	 * field).
	 * </p>
	 * 
	 * @param app
	 *            The app's {@link ConsoleDataToolbox} field.
	 */
	public void executeToConsole( ConsoleDataToolbox app ) {
		
		System.out
				.print( " Are you sure you want to remove\n all passenger-flights with zero passengers?" );
		System.out
				.print( "\n Type YES if so or type any other\n key otherwhise and press Enter." );
		System.out.print( "\n\nRemove? " );
		
		Scanner in = new Scanner( System.in );
		if( in.nextLine().equals( "YES" ) )
			try
			{
				if( app.getFlightsDatabase() == null )
					throw new DatabaseNotFoundException();
				
				app.getFlightsDatabase().removeAirplanesWithZeroPassengers();
				
				System.out
						.print( "DONE! Passenger-flights with zero passengers removed successfully!" );
			}
			catch( DatabaseNotFoundException e )
			{
				System.out.println( "DATABASE NOT FOUND!" );
			}
		else System.out.println( "ABORTED OPERATION!" );
		
	}
	
	
	
	/**
	 * Performs no action.
	 * 
	 * @return {@code null}
	 */
	public String execute() throws DatabaseNotFoundException {
		
		return null;
	};
	
}