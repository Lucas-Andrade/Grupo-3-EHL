package app;


/**
 * This class represents the option with the title
 * {@code Consult a flight's details.} of an Air Traffic Control app.
 * 
 * <p style="font-size:16">
 * <b>Description</b>
 * </p>
 * <p>
 * The purpose of this class is to perform the action of consulting the details
 * of a specific flight from the list of scheduled flights in the console. For
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
public class ConsultFlightDetailsOption extends Option
{
	
	
	// CAMPO DA CLASE
	
	/**
	 * An instance of type ConsultFlightDetailsOption.
	 */
	private static ConsultFlightDetailsOption instance = new ConsultFlightDetailsOption();
	
	
	
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
	
	
	
	// execute()
	
	/**
	 * Consults the details of a specific flight.
	 * 
	 * <p>
	 * DESCRIPTION TODO
	 * </p>
	 */
	public void executeToConsole( AirTrafficControlAppForConsoleTools app ) {
		System.out.println( title );
	};
	
}