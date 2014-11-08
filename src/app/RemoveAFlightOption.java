package app;


/**
 * This class represents the option with the title
 * {@code Remove a flight manually.}.
 * 
 * <p style="font-size:16">
 * <b>Description</b>
 * </p>
 * <p>
 * The purpose of this class is to perform the action of removing a plane
 * manually from the list of scheduled flights. For more information, read the
 * documentation of method {@link #execute() execute}.
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
	
	
	
	// M�TODO CONSTRUTOR e M�TODO getInstance()
	
	
	/**
	 * Creates a new instance of type RemoveAFlightOption and sets up the final
	 * values of the fields {@code title} and {@code description}.
	 */
	public RemoveAFlightOption() {
		super( "Remove a flight manually.", "d" );
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
	
	
	
	// execute()
	
	/**
	 * Removes a plane manually from the list of scheduled flights.
	 * 
	 * <p>
	 * DESCRIPTION TODO
	 * </p>
	 */
	public void execute() {
		System.out.println( title );
	};
	
}