package airtrafficcontrol.app.deprecated;


import java.util.Scanner;
import airtrafficcontrol.app.appforconsole.AirTrafficControlAppForConsole;
import airtrafficcontrol.app.menuoptions.Option;
import airtrafficcontrol.app.utils.Airliner;
import airtrafficcontrol.app.utils.Airship;
import airtrafficcontrol.app.utils.Database;
import airtrafficcontrol.app.utils.GeographicalPosition;


/**
 * This class represents the option with the title
 * {@code Add a flight manually.} of an Air Traffic Control app.
 * 
 * <p style="font-size:16">
 * <b>Description</b>
 * </p>
 * <p>
 * The purpose of this class is to perform the action of adding a plane manually
 * to an already existent list of scheduled flights. For more information, read
 * the documentation of method {@link #execute() execute}.
 * </p>
 * 
 * <p style="font-size:16">
 * <b> Implementation notes</b>
 * </p>
 * <ul>
 * <li>Instances of this class are immutable.</li>
 * <li>All instances of this class share the same {@code #title} and
 * {@code description}.
 * </ul>
 *
 * @author Eva Gomes
 * @author Hugo Leal
 * @author Lucas Andrade
 */
public class AddAFlightOption extends Option
{
	
	
	// CAMPO DA CLASE
	
	/**
	 * An instance of type AddAFlightOption.
	 */
	private static AddAFlightOption instance = new AddAFlightOption();
	
	/**
	 * The airship to be added to the {@code flightsDB}.
	 */
	private Airship airshipToBeAdded = null;
	
	/**
	 * The database where to add the airship.
	 */
	private Database flightsDB = null;
	
	
	// METODO CONSTRUTOR e METODO getInstance()
	
	
	/**
	 * Creates a new instance of type AddAFlightOption and sets up the final
	 * values of the fields {@code title} and {@code description}.
	 */
	public AddAFlightOption() {
		super( "Add a flight manually.", "d" );
	};
	
	/**
	 * Returns an instance of type AddAFlightOption, without creating a new one.
	 * 
	 * <p>
	 * Note that the instances of this type have no differentiating properties:
	 * the only instance fields are {@code title} and {@code description} and
	 * their values are the same for all instances. This method lets you reuse
	 * an already created instance instead of always creating new ones with the
	 * {@link #AddAFlightOption() constructor}.
	 * </p>
	 * 
	 * @return A instance of type AddAFlightOption.
	 */
	public static AddAFlightOption getInstance() {
		return instance;
	}
	
	
	
	// EXECUCAO
	
	
	/**
	 * Adds an airship with the details given by the user to the {@code app}'s
	 * internal database.
	 * 
	 * @param app
	 *            The app whose database is to receive the new airship.
	 */
	public void executeToConsole( AirTrafficControlAppForConsole app ) {
		
		
		
		Scanner in = new Scanner( System.in );
		System.out.println( "Insert the flight's details:" );
		
		String flightID = app.dataTools.inputHandler
				.getAValidFlightIDFromUser( "\n\nFlightID: " );
		
	//	GeographicalPosition takeOff =
		
		
		
	//	airshipToBeAdded = new Airliner(flightID,);
	//	flightsDB = app.tools.flightsDB;
		
	}
	
	/**
	 * 
	 * @return
	 */
	public String execute() {
		// new AirShip(
		return null;
	};
	
}