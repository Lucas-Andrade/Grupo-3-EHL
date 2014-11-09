package app;


/**
 * This class represents the option with the title {@code Monitor Air Traffic.}
 * of an Air Traffic Control app.
 * 
 * <p style="font-size:16">
 * <b>Description</b>
 * </p>
 * <p>
 * The purpose of this class is to perform the action of monitoring the flights
 * in the list of scheduled flights that already took-off. For more information,
 * read the documentation of method {@link #execute() execute}.
 * </p>
 * 
 * <p style="font-size:16">
 * <b> Implementation notes</b>
 * </p>
 * <ul>
 * <li>Instances of this class are immutable.</li>
 * <li>All instances of this class share the same {@link #title} and
 * {@link #description}.
 * </ul>
 *
 * @author Eva Gomes
 * @author Hugo Leal
 * @author Lucas Andrade
 */
public class MonitorAirTrafficOption extends Option
{
	
	
	// CAMPO DA CLASE
	
	/**
	 * An instance of type MonitorAirTrafficOption.
	 */
	private static MonitorAirTrafficOption instance = new MonitorAirTrafficOption();
	
	
	
	// MÉTODO CONSTRUTOR e MÉTODO getInstance()
	
	
	/**
	 * Creates a new instance of type MonitorAirTrafficOption and sets up the
	 * final values of the fields {@code title} and {@code description}.
	 */
	public MonitorAirTrafficOption() {
		super( "Monitor Air Traffic.", "d" );
	};
	
	/**
	 * Returns an instance of type MonitorAirTrafficOption, without creating a
	 * new one.
	 * 
	 * <p>
	 * Note that the instances of this type have no differentiating properties:
	 * the only instance fields are {@code title} and {@code description} and
	 * their values are the same for all instances. This method lets you reuse
	 * an already created instance instead of always creating new ones with the
	 * {@link #MonitorAirTrafficOption() constructor}.
	 * </p>
	 * 
	 * @return A instance of type MonitorAirTrafficOption.
	 */
	public static MonitorAirTrafficOption getInstance() {
		return instance;
	}
	
	
	
	// execute()
	
	/**
	 * Monitors the flights in the list of scheduled flights that already
	 * took-off
	 * 
	 * <p>
	 * DESCRIPTION TODO
	 * </p>
	 */
	public void executeToConsole( AirTrafficControlAppForConsoleTools app ) {
		System.out.println( title );
	};
	
}