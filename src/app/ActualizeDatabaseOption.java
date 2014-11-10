package app;


import java.io.IOException;
import utils.ReadListOfFlights;


/**
 * This class represents the option with the title
 * {@code Update the database's flights' coordinates.} of an Air Traffic Control
 * app for console.
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
public class ActualizeDatabaseOption extends Option
{
	
	
	// CAMPO DA CLASE
	
	/**
	 * An instance of type ActualizeDatabaseOption.
	 */
	private static ActualizeDatabaseOption instance = new ActualizeDatabaseOption();
	
	
	
	// MÉTODO CONSTRUTOR e MÉTODO getInstance()
	
	
	/**
	 * Creates a new instance of type ActualizeDatabaseOption and sets up the
	 * final values of the fields {@code title} and {@code description}.
	 */
	public ActualizeDatabaseOption() {
		super(
				"Update the database's flights' coordinates.",
				"Creates an internal database of flights for the app from the flights listed in the «src\\ListOfFlights.txt»" );
	};
	
	
	/**
	 * Returns an instance of type ActualizeDatabaseOption, without creating a
	 * new one.
	 * 
	 * <p>
	 * Note that the instances of this type have no differentiating properties:
	 * the only instance fields are {@code title} and {@code description} and
	 * their values are the same for all instances. This method lets you reuse
	 * an already created instance instead of always creating new ones with the
	 * {@link #ActualizeDatabaseOption() constructor}.
	 * </p>
	 * 
	 * @return A instance of type ActualizeDatabaseOption.
	 */
	public static ActualizeDatabaseOption getInstance() {
		return instance;
	}
	
	
	
	// ACÇÃO
	
	
	
	/**
	 * Creates the internal database of flights of the app from the flights
	 * listed in the "src\ListOfFlights.txt".
	 * 
	 * @param app
	 *            The app's {@link AirTrafficControlAppForConsoleTools} field.
	 */
	public void executeToConsole( AirTrafficControlAppForConsoleTools app ) {
		try
		{
			app.flightsDB.addDataBase( new ReadListOfFlights()
					.readFlights( "ListOfFlights.txt" ) );
		}
		catch( IOException e )
		{
			System.out.println( "src/ListOfFlights.txt FILE NOT FOUND!" );
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
