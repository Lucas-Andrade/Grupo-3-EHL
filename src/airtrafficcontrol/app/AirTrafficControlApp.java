package airtrafficcontrol.app;


import airtrafficcontrol.app.menuoptions.Option;
import airtrafficcontrol.app.exceptions.InvalidArgumentException;
import airtrafficcontrol.app.OptionsMenu;


/**
 * Class whose subclasses' instances represent Air Traffic Control apps.
 * <p>
 * Air Traffic Control apps have a name, have a developer, have a
 * {@link OptionsMenu options menu} , have a {@link AirTrafficControlAppToolbox
 * a database and a reports generator} and they run.
 * 
 *
 * @author Eva Gomes
 * @author Hugo Leal
 * @author Lucas Andrade
 */
public abstract class AirTrafficControlApp implements Runnable
{
	
	// CAMPO DA CLASSE
	
	/**
	 * The app's title.
	 */
	public final String appTitle;
	
	/**
	 * The app's developer.
	 */
	public final String appDeveloper;
	
	/**
	 * A menu for the app.
	 */
	public final OptionsMenu mainMenu;
	
	/**
	 * The app's tools (creates the app's flights database and its reports
	 * generator).
	 */
	public final AirTrafficControlAppToolbox tools;
	
	
	
	// CONSTRUTOR
	
	/**
	 * Creates a new Air Traffic Control app with
	 * <ul>
	 * <li>the name {@code appTitle},</li>
	 * <li>the developer {@code appDeveloper},</li>
	 * <li>an {@link airtrafficcontrolapp.app.OptionsMenu options menu} with the
	 * title {@code menuTitle} and the options {@code options},</li>
	 * <li>a flights' database and</li>
	 * <li>a reports generator.</li>
	 * </ul>
	 * 
	 * @param appTitle
	 *            This app's name.
	 * @param appDeveloper
	 *            This app's developer.
	 * @param menuTitle
	 *            This app's options menu title.
	 * @param options
	 *            This app's menu's options.
	 * @throws InvalidArgumentException
	 *             If {@code null} arguments are inserted or if appTitle,
	 *             appDeveloper or menuTitle are given the empty string ("").
	 */
	public AirTrafficControlApp( String appTitle, String appDeveloper,
			String menuTitle, Option... options )
			throws InvalidArgumentException {
		
		if( appTitle == null || appTitle.equals( "" ) || appDeveloper == null
				|| appDeveloper.equals( "" ) || menuTitle == null
				|| menuTitle.equals( "" ) )
			throw new InvalidArgumentException();
		for( Option option : options )
			if( option == null )
				throw new InvalidArgumentException();
		
		this.appTitle = appTitle;
		this.appDeveloper = appDeveloper;
		
		mainMenu = new OptionsMenu( menuTitle, options );
		
		tools = new AirTrafficControlAppToolbox();
	}
	
	
	// RUN
	
	/**
	 * Runs the app.
	 */
	public abstract void run();
}
