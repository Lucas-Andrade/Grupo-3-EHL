package app;


/**
 * This class represents the option with the title
 * {@code Add a list of flights from txt file.} of an Air Traffic Control app.
 * 
 * <p style="font-size:16">
 * <b>Description</b>
 * </p>
 * <p>
 * The purpose of this class is to perform the action of adding the flights
 * listed in a txt file to an already existent list of scheduled flights. For
 * more information, read the documentation of method {@link #execute() execute}
 * .
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
	
	
	
	// MÉTODO CONSTRUTOR e MÉTODO getInstance()
	
	
	/**
	 * Creates a new instance of type AddAListOfFlightsOption and sets up the
	 * final values of the fields {@code title} and {@code description}.
	 */
	public AddAListOfFlightsOption() {
		super( "Add a list of flights from txt file.", "d" );
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
	
	
	
	// execute()
	
	/**
	 * Adds the flights listed in a txt file to an already existent list of
	 * scheduled flights.
	 * 
	 * <p>
	 * DESCRIPTION TODO
	 * </p>
	 */
	public void executeToConsole(AirTrafficControlAppForConsoleTools app) {
		System.out.println( title );
	}

	@Override
	public String execute() {


		return null;
	};
	
}