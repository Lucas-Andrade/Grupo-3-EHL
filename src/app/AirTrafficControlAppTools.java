package app;


import utils.Database;
import utils.ReportGenerator;


/**
 * Class whose subclasses's instances provide tools for apps of air traffic
 * control. Instances must have three public final fields of types
 * {@link OptionsMenu}, {@link Database} and {@link ReportGenerator}.
 * 
 *
 * @author Eva Gomes
 * @author Hugo Leal
 * @author Lucas Andrade
 */
public abstract class AirTrafficControlAppTools
{
	
	// CAMPOS DA CLASSE
	
	/**
	 * A menu for the app.
	 */
	public final OptionsMenu mainMenu;
	
	
	/**
	 * A flights' data base of the app.
	 */
	public final Database flightsDB;
	
	
	/**
	 * A app's report generator.
	 */
	public final ReportGenerator reporter;
	
	
	// CONSTRUTOR
	
	
	
	/**
	 * Creates a new instance of type {@link AirTrafficControlAppTools}.
	 * <p>
	 * This instance provides:
	 * <ul>
	 * <li>a {@link AirTrafficControlAppTools#mainMenu MAINMENU} with the
	 * {@link OptionsMenu#title title} {@code menuTitle} and the options
	 * {@code options} and</li>
	 * <li>an empty {@link utils.Database flights' database}</li>
	 * </ul>
	 * for the app.
	 * </p>
	 * 
	 * @param menuTitle
	 *            The title of this app's menu.
	 * @param options
	 *            The options list of this app's menu.
	 */
	public AirTrafficControlAppTools( String menuTitle, Option... options ) {
		mainMenu = new OptionsMenu( menuTitle, options );
		flightsDB = new Database();
		reporter = new ReportGenerator();
	}
	
}
