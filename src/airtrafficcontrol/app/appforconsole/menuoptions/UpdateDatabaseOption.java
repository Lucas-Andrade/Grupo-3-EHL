package airtrafficcontrol.app.appforconsole.menuoptions;


import java.io.IOException;
import airtrafficcontrol.app.appforconsole.AirTrafficControlAppForConsole;
import airtrafficcontrol.app.exceptions.DatabaseNotFoundException;
import airtrafficcontrol.app.exceptions.InvalidArgumentException;
import airtrafficcontrol.app.exceptions.InvalidFlightIDException;
import airtrafficcontrol.app.utils.towerControl.Database;
import airtrafficcontrol.app.utils.towerControl.ReadAirplanesCoordinates;
import airtrafficcontrol.deprecated.ConsoleDataToolbox;


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
public class UpdateDatabaseOption extends Option
{
	
	// INSTANCE FIELDS
	
	/**
	 * An instance of type ActualizeDatabaseOption.
	 */
	private static UpdateDatabaseOption instance = new UpdateDatabaseOption();
	
	/**
	 * The database whose flights are to be updated.
	 */
	private Database flightsDB = null;
	
	/**
	 * The text file name where to read the coordinates from.
	 */
	private String fileName = null;
	
	
	
	// CONSTRUCTOR AND getInstance()
	
	/**
	 * Creates a new instance of type ActualizeDatabaseOption and sets up the
	 * final values of the fields {@code title} and {@code description}.
	 */
	public UpdateDatabaseOption() {
		super( "Update the database's flights' coordinates." );
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
	public static UpdateDatabaseOption getInstance() {
		return instance;
	}
	
	
	
	// EXECUTE METHODS
	
	/**
	 * Creates the internal database of flights of the app from the flights
	 * listed in the "src\ListOfFlights.txt".
	 * 
	 * @param app
	 *            The app's {@link ConsoleDataToolbox} field.
	 * @throws InvalidArgumentException
	 *             If {@code app} is {@code null}.
	 */
	public void executeToConsole( AirTrafficControlAppForConsole app )
			throws InvalidArgumentException {
		
		if( app == null )
			throw new InvalidArgumentException( "INVALID NULL APP!" );
		
		// save app's database as a field so execute can access it
		flightsDB = app.flightsDB;
		// set the file where to look for the information
		fileName = "ActualizedCoordinates.txt";
		
		try
		{
			System.out.println( execute() );
		}
		catch( IOException e )
		{
			System.out.println( fileName + " FILE NOT FOUND!" );
		}
		catch( InvalidFlightIDException | DatabaseNotFoundException e )
		{
			System.out.println( e.getMessage() );
		}
	}
	
	/**
	 * Creates a database of flights from the flights listed in the the file
	 * {@code fileName}.
	 * 
	 * @return A message on the operation being successfully concluded.
	 * @throws DatabaseNotFoundException
	 * @throws IOException
	 * @throws InvalidArgumentException
	 * @throws InvalidFlightIDException
	 */
	public String execute() throws DatabaseNotFoundException,
			InvalidFlightIDException, InvalidArgumentException, IOException {
		
		if( flightsDB == null )
			throw new DatabaseNotFoundException( "INVALID NULL DATABASE!" );
		
		new ReadAirplanesCoordinates().readFromFile(
				"ActualizedCoordinates.txt", flightsDB );
		return "DONE! New coordinates updated from \n" + fileName
				+ " successfully.";
	};
	
}
