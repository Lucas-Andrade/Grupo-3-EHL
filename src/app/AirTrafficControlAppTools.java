package app;


import utils.Database;


/**
 * Class whose subclasses's instances provide tools for apps of air traffic
 * control. Instances must have a public final field of type {@link OptionsMenu}
 * and a public final field of type {@link Database}.
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
	public final OptionsMenu MAINMENU;
	
	
	/**
	 * A flights' data base of the app.
	 */
	public final Database FLIGHTSDB;
	
	
	
	// CONSTRUTOR
	
	/**
	 * Creates a new instance of type {@link AirTrafficControlAppTools}.
	 * <p>
	 * This instance provides:
	 * <ul>
	 * <li>a {@link AirTrafficControlAppTools#MAINMENU MAINMENU} with the
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
		MAINMENU = new OptionsMenu( menuTitle, options );
		FLIGHTSDB = new Database();
	}
	
}
