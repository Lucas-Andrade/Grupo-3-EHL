package airtrafficcontrol.app.appforconsole.menuoptions;


import airtrafficcontrol.app.appforconsole.AirTrafficControlAppForConsole;
import airtrafficcontrol.app.exceptions.InvalidArgumentException;


/**
 * Class whose instances exit air traffic control apps for console.
 * 
 * <p style="font-size:16">
 * <b>Description</b>
 * </p>
 * <p>
 * The purpose of this class's instances is to exit an
 * {@link AirTrafficControlAppForConsole air traffic control app for console}.
 * For more information, read the documentation of method
 * {@link #executeToConsole()} .
 * </p>
 * 
 * <p style="font-size:16">
 * <b> Implementation notes</b>
 * </p>
 * <ul>
 * <li>{@link ExitOption} instances are immutable and share the same
 * {@link Option#title title} (" {@code Exit app.}"). The method
 * {@link #getInstance()} allows to get an instance of this class without
 * creating a new one.
 * </ul>
 *
 * @author (original) Eva Gomes, Hugo Leal, Lucas Andrade
 * @author (2nd version) Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro
 *         Antunes
 */
public class ExitOption extends Option
{
	
	// CLASS FIELDS
	
	/**
	 * An instance of type {@link ExitOption}.
	 */
	private static ExitOption instance = new ExitOption();
	
	
	
	// CONSTRUCTOR AND getInstance()
	
	/**
	 * Creates a new instance of type ExitOption and sets up the final values of
	 * the fields {@code title} and {@code description}.
	 */
	public ExitOption() {
		super( "Exit app." );
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
	
	
	
	// EXECUTE TO CONSOLE
	
	/**
	 * Prints a goodbye message to console and calls the method
	 * {@link AirTrafficControlAppForConsole#exitApp() exitApp} through instance
	 * {@code app}.
	 * 
	 * @param app
	 *            An instance of {@link AirTrafficControlAppForConsole}.
	 * @throws InvalidArgumentException
	 *             If {@code app} is {@code null}.
	 */
	public void executeToConsole( AirTrafficControlAppForConsole app )
			throws InvalidArgumentException {
		
		if( app == null )
			throw new InvalidArgumentException( "INVALID NULL APP!" );
		
		System.out.println( "Exiting the " + app.appTitle
				+ "...\nThanks for using an " + app.appDeveloper
				+ "'s app! Bye!" );
		
		app.exitApp();
		
	}
	
	
}