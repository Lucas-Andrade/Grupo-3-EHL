package airtrafficcontrol.deprecated;


import airtrafficcontrol.app.appforconsole.AirTrafficControlAppForConsole;
import airtrafficcontrol.app.appforconsole.exceptions.InvalidArgumentException;
import airtrafficcontrol.app.appforconsole.menuoptions.Option;


/**
 * This class represents the option with the title {@code Configurations.} of an
 * Air Traffic Control app.
 * 
 * <p style="font-size:16">
 * <b>Description</b>
 * </p>
 * <p>
 * The purpose of this class is to perform the action of allowing the user of
 * the EHL's Air Traffic Control app to change some settings, read the
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
public class ConfigurationsOption extends Option
{
	
	// CLASS INSTANCE
	
	/**
	 * An instance of type ConfigurationsOption.
	 */
	private static ConfigurationsOption instance = new ConfigurationsOption();
	
	
	
	// CONSTRUCTOR AND getInstance()
	
	/**
	 * Creates a new instance of type ConfigurationsOption and sets up the final
	 * values of the fields {@code title} and {@code description}.
	 */
	public ConfigurationsOption() {
		super( "Configurations." );
	};
	
	/**
	 * Returns an instance of type ConfigurationsOption, without creating a new
	 * one.
	 * 
	 * <p>
	 * Note that the instances of this type have no differentiating properties:
	 * the only instance fields are {@code title} and {@code description} and
	 * their values are the same for all instances. This method lets you reuse
	 * an already created instance instead of always creating new ones with the
	 * {@link #ConfigurationsOption() constructor}.
	 * </p>
	 * 
	 * @return A instance of type ConfigurationsOption.
	 */
	public static ConfigurationsOption getInstance() {
		return instance;
	}
	
	
	
	// EXECUTE
	
	/**
	 * Allows the user of the EHL's Air Traffic Control app to change some
	 * settings
	 * 
	 * <p>
	 * DESCRIPTION TODO
	 * </p>
	 */
	@Override
	public void executeToConsole( AirTrafficControlAppForConsole app )
			throws InvalidArgumentException {
		// TODO Auto-generated method stub
		
	};
	
	
	/**
	 * 
	 */
	public String execute() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
}