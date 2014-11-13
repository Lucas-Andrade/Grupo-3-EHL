package airtrafficcontrol.app.menuoptions;


import java.io.IOException;
import airtrafficcontrol.app.appforconsole.AirTrafficControlAppForConsole;
import airtrafficcontrol.app.appforconsole.ConsoleDataToolbox;
import airtrafficcontrol.app.exceptions.InvalidArgumentException;
import airtrafficcontrol.app.exceptions.InvalidFlightIDException;
import airtrafficcontrol.app.utils.ReadListOfFlights;


/**
 * This class represents the option with the title
 * {@code Add a list of flights from txt file.} of an Air Traffic Control app
 * for console.
 * 
 * <p style="font-size:16">
 * <b>Description</b>
 * </p>
 * <p>
 * The purpose of this class is to perform the action of creating an internal
 * database of flights for the app from the flights listed in the
 * "src\ListOfFlights.txt". For more information, read the documentation of
 * method {@link #execute() execute} .
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
public class AddAListOfFlightsOption extends Option
{
	
	
	// CAMPO DA CLASE
	
	/**
	 * An instance of type AddAListOfFlightsOption.
	 */
	private static AddAListOfFlightsOption instance = new AddAListOfFlightsOption();
	
	
	
	// M�TODO CONSTRUTOR e M�TODO getInstance()
	
	
	/**
	 * Creates a new instance of type AddAListOfFlightsOption and sets up the
	 * final values of the fields {@code title} and {@code description}.
	 */
	public AddAListOfFlightsOption() {
		super(
				"Add a list of flights from txt file.",
				"Creates an internal database of flights for the app from the flights listed in the �src\\ListOfFlights.txt�" );
	};
	
	
	/**
	 * Returns an instance of type AddAListOfFlightsOption, without creating a
	 * new one.
	 * 
	 * <p>
	 * Note that the instances of this type have no differentiating properties:
	 * the only instance fields are {@code title} and {@code description} and
	 * their values are the same for all instances. This method lets you reuse
	 * an already created instance instead of always creating new ones with the
	 * {@link #AddAListOfFlightsOption() constructor}.
	 * </p>
	 * 
	 * @return A instance of type AddAListOfFlightsOption.
	 */
	public static AddAListOfFlightsOption getInstance() {
		return instance;
	}
	
	
	
	// EXECUCAO
	
	
	
	/**
	 * Creates the internal database of flights of the app from the flights
	 * listed in the "src/airtrafficcontrol/filestoread/ListOfFlights.txt".
	 * 
	 * @param app
	 *            The app's {@link ConsoleDataToolbox} field.
	 */
	public void executeToConsole( AirTrafficControlAppForConsole app ) {
		try
		{
			System.out.print(app.tools.flightsDB.addDatabase( new ReadListOfFlights()
					.readFlights( "ListOfFlights.txt" ) ));
		}
		catch( IOException e )
		{
			System.out.println( "ListOfFlights.txt FILE NOT FOUND!" );
		}
		catch( InvalidFlightIDException e )
		{
			System.out.println(e.getMessage());
		}
		catch( InvalidArgumentException e )
		{
			System.out.println(e.getMessage());
		}
		System.out
				.println( "DONE! New internal database of flights \ncreated from ListOfFlights.txt" );
	}
	
	
	/**
	 * Performs no action.
	 * 
	 * @return {@code null}
	 */
	public String execute() {
		return null;
	};
	
}