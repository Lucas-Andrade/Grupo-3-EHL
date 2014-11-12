package airtrafficcontrol.app;


import airtrafficcontrol.app.menuoptions.Option;


/**
 * Class whose subclasses' instances represent Air Traffic Control apps.
 * <p>
 * Air Traffic Control apps have a name, have a {@link OptionsMenu options menu}
 * , have a {@link AirTrafficControlAppToolbox a database and a reports
 * generator} and they run.
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
	 * <li>an {@link airtrafficcontrolapp.app.OptionsMenu options menu} with the
	 * title {@code menuTitle} and the options {@code options},</li>
	 * <li>a flights' database and</li>
	 * <li>a reports generator.</li>
	 * </ul>
	 * 
	 * @param appTitle
	 *            This app's name.
	 * @param menuTitle
	 *            This app's options menu title.
	 * @param options
	 *            This app's menu's options.
	 */
	public AirTrafficControlApp( String appTitle, String menuTitle,
			Option... options ) {
		
		this.appTitle = appTitle;
		mainMenu = new OptionsMenu( menuTitle, options );
		tools = new AirTrafficControlAppToolbox();
	}
	
	
	// RUN
	
	/**
	 * Runs the app.
	 */
	public abstract void run();
}
