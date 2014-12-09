package airtrafficcontrol.app.appforconsole.menuoptions;


import airtrafficcontrol.app.appforconsole.AirTrafficControlAppForConsole;


/**
 * Class whose instances return information on how to use the EHL's AIR TRAFFIC
 * CONTROL app for console.
 * 
 * <p style="font-size:16">
 * <b>Description</b>
 * </p>
 * <p>
 * The purpose of this class's instances is to produce a message with
 * instructions to find the «USER'S GUIDE of the EHL's AIR TRAFFIC CONTROL app
 * for console.txt» file. For more information, read the documentation of method
 * {@link #executeToConsole()} .
 * </p>
 * 
 * <p style="font-size:16">
 * <b> Implementation notes</b>
 * </p>
 * <ul>
 * <li>{@link HelpOption_for_EHLsATCAppForConsole} instances are immutable and
 * share the same {@link Option#title title} (" {@code Help!}"). The method
 * {@link #getInstance()} allows to get an instance of this class without
 * creating a new one.
 * <li>{@link HelpOption_for_EHLsATCAppForConsole} was strictly created for
 * EHL's AIR TRAFFIC CONTROL app for console.</li>
 * </ul>
 *
 * @author (original) Eva Gomes, Hugo Leal, Lucas Andrade
 * @author (2nd version) Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro
 *         Antunes
 */
public class HelpOption_for_EHLsATCAppForConsole extends Option
{
	
	// CLASS FIELDS
	
	/**
	 * An instance of type {@link HelpOption_for_EHLsATCAppForConsole}.
	 */
	private static HelpOption_for_EHLsATCAppForConsole instance = new HelpOption_for_EHLsATCAppForConsole();
	
	
	
	// CONSTRUCTOR AND getInstance()
	
	/**
	 * Creates a new instance of type HelpOption and sets up the final values of
	 * the fields {@code title} and {@code description}.
	 */
	public HelpOption_for_EHLsATCAppForConsole() {
		super( "Help!" );
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
	
	
	
	// EXECUTE TO CONSOLE
	
	/**
	 * Prints the message with instructions to find the «USER'S GUIDE of the
	 * EHL's AIR TRAFFIC CONTROL app for console.txt» file.
	 * <p>
	 * This method doesn't use the argument {@code app}.
	 * </p>
	 * 
	 * @param app
	 *            An {@link AirTrafficControlAppForConsole} instance.
	 */
	public void executeToConsole( AirTrafficControlAppForConsole app ) {
		System.out
				.print( new StringBuilder(
						"Open the airtrafficcontrol directory." )
						.append( "\nThere you will find a txt file named" )
						.append(
								"\n   USER'S GUIDE of the EHL's AIR TRAFFIC CONTROL app for console" )
						.append( "\nwhich provides a detailed description" )
						.append( "\nabout each option in the menu." ) );
	}
	
	
}