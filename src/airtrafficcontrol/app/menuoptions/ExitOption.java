package airtrafficcontrol.app.menuoptions;


import airtrafficcontrol.app.OptionsMenu;
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
 * <li>This subclass of Option was strictly created for the EHL's AIR TRAFFIC
 * CONTROL app for console.</li>
 * </ul>
 *
 * @author Eva Gomes
 * @author Hugo Leal
 * @author Lucas Andrade
 */
public class ExitOption extends Option
{
	
	
	// CAMPO DA CLASE
	
	/**
	 * An instance of type ExitOption.
	 */
	private static ExitOption instance = new ExitOption();
	
	/**
	 * The title of the app being exited.
	 */
	private String appTitle = null;
	
	/**
	 * The developer of the app being exited.
	 */
	private String appDeveloper = null;
	
	
	// M�TODO CONSTRUTOR e M�TODO getInstance()
	
	
	/**
	 * Creates a new instance of type ExitOption and sets up the final values of
	 * the fields {@code title} and {@code description}.
	 */
	public ExitOption() {
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
	public static ExitOption getInstance() {
		return instance;
	}
	
	
	
	// EXECUCAO
	
	
	
	/**
	 * Prints a goodbye message to console.
	 * <p>
	 * This implementation of the method
	 * {@link Option#executeToConsole(AirTrafficControlAppForConsole)
	 * executeToConsole()} does not use the argument {@code app} because this
	 * subclass of Option was strictly created for the EHL's AIR TRAFFIC CONTROL
	 * app for console.
	 * </p>
	 * 
	 * @param app
	 *            An instance of {@link AirTrafficControlAppForConsole}.
	 */
	public void executeToConsole( AirTrafficControlAppForConsole app ) {
		appTitle = app.appTitle;
		appDeveloper = app.appDeveloper;
		System.out.println( execute() );
	};
	
	
	
	/**
	 * Produces a goodbye message.
	 * 
	 * @return The goodbye message.
	 */
	public String execute() {
		return "Exiting the " + appTitle + "...\nThanks for using an "
				+ appDeveloper + "'s app! Bye!";
	}
	
	
}