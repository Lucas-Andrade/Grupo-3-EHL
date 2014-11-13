package airtrafficcontrol.app.menuoptions;


import airtrafficcontrol.app.appforconsole.AirTrafficControlAppForConsole;
import airtrafficcontrol.app.appforconsole.ConsoleDataToolbox;


/**
 * This class represents the option with the title {@code Help!} of the EHL's
 * AIR TRAFFIC CONTROL app for console.
 * 
 * <p style="font-size:16">
 * <b>Description</b>
 * </p>
 * <p>
 * The purpose of this class is to perform the action of producing a message
 * with instructions to find the USER'S GUIDE txt file for the
 * {@link AirTrafficControlAppForConsole EHL's AIR TRAFFIC CONTROL app for
 * console}.
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
public class HelpOption_for_EHLsATCAppForConsole extends Option
{
	
	
	// CAMPOS DA CLASE
	
	/**
	 * An instance of type HelpOption.
	 */
	private static HelpOption_for_EHLsATCAppForConsole instance = new HelpOption_for_EHLsATCAppForConsole();
	
	
	
	// M�TODO CONSTRUTOR e M�TODO getInstance()
	
	
	
	/**
	 * Creates a new instance of type HelpOption and sets up the final values of
	 * the fields {@code title} and {@code description}.
	 */
	public HelpOption_for_EHLsATCAppForConsole() {
		super( "Help!",
				"Produces a message with instructions to find the USER'S GUIDE txt file." );
	};
	
	/**
	 * Returns an instance of type HelpOption, without creating a new one.
	 * 
	 * <p>
	 * Note that the instances of this type have no differentiating properties:
	 * the only instance fields are {@code title} and {@code description} and
	 * their values are the same for all instances. This method lets you reuse
	 * an already created instance instead of always creating new ones with the
	 * {@link #HelpOption() constructor}.
	 * </p>
	 * 
	 * @return A instance of type HelpOption.
	 */
	public static HelpOption_for_EHLsATCAppForConsole getInstance() {
		return instance;
	}
	
	
	
	// EXECUCAO
	
	
	
	/**
	 * Prints the message with instructions to find the USER'S GUIDE txt file.
	 * 
	 * <p>
	 * This method doesn't use the argument {@code app}.
	 * </p>
	 * 
	 * @param app
	 *            An {@link ConsoleDataToolbox} instance.
	 */
	public void executeToConsole( AirTrafficControlAppForConsole app ) {
		System.out.print( execute() );
	}
	
	
	
	/**
	 * Produces a message with instructions to find the USER'S GUIDE txt file.
	 */
	public String execute() {
		return new StringBuilder( "Open the airtrafficcontrol directory." )
				.append( "\nThere you will find a txt file named" )
				.append( "\n   USER'S GUIDE.txt" )
				.append( "\nwhich provides a detailed description" )
				.append( "\nabout each option in the menu." ).toString();
	};
	
	
}