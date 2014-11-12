package airtrafficcontrol.app.menuoptions;


import airtrafficcontrol.app.OptionsMenu;
import airtrafficcontrol.app.appforconsole.ConsoleDataToolbox;
import airtrafficcontrol.app.appforconsole.AirTrafficControlAppForConsole;


/**
 * This class represents the option with the title {@code Exit app.} of an Air
 * Traffic Control app.
 * 
 * <p style="font-size:16">
 * <b>Description</b>
 * </p>
 * <p>
 * The purpose of this class is to perform the action of exiting the app whose
 * {@link OptionsMenu} contains this option; as so it must be the last of
 * instance of type Option to be inserted in the parameters of the constructor
 * of the instance of {@link OptionsMenu} that the app uses. For more
 * information, read the documentation of method {@link #execute() execute}.
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
public class ExitOption_for_EHLsATCAppForConsole extends Option
{
	
	
	// CAMPO DA CLASE
	
	/**
	 * An instance of type ExitOption.
	 */
	private static ExitOption_for_EHLsATCAppForConsole instance = new ExitOption_for_EHLsATCAppForConsole();
	
	
	
	// MÉTODO CONSTRUTOR e MÉTODO getInstance()
	
	
	/**
	 * Creates a new instance of type ExitOption and sets up the final values of
	 * the fields {@code title} and {@code description}.
	 */
	public ExitOption_for_EHLsATCAppForConsole() {
		super( "Exit app.", "Produces a goodbye message and exits the app." );
	};
	
	/**
	 * Returns an instance of type ExitOption, without creating a new one.
	 * 
	 * <p>
	 * Note that the instances of this type have no differentiating properties:
	 * the only instance fields are {@code title} and {@code description} and
	 * their values are the same for all instances. This method lets you reuse
	 * an already created instance instead of always creating new ones with the
	 * {@link #ExitOption() constructor}.
	 * </p>
	 * 
	 * @return A instance of type ExitOption.
	 */
	public static ExitOption_for_EHLsATCAppForConsole getInstance() {
		return instance;
	}
	
	
	
	// ACÇÃO
	
	
	
	/**
	 * Prints a goodbye message to console.
	 * 
	 * <p>
	 * This method doesn't use the argument {@code app}.
	 * </p>
	 * 
	 * @param app
	 *            An {@link ConsoleDataToolbox} instance.
	 */
	public void executeToConsole( AirTrafficControlAppForConsole app ) {
		System.out.println( execute() );
	};
	
	
	
	/**
	 * Produces a goodbye message.
	 * 
	 * @return The goodbye message.
	 */
	public String execute() {
		return "Exiting the Air Traffic Control app..."
				+ "\nThanks for using an EHL app! Bye!";
	}
	
	
}