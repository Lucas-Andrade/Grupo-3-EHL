package airtrafficcontrol.app;


import airtrafficcontrol.app.appforconsole.exceptions.InvalidArgumentException;
import airtrafficcontrol.app.appforconsole.utils.towerControl.Database;
import airtrafficcontrol.app.appforconsole.utils.towerControl.ReportGenerator;


/**
 * Class whose subclasses' instances represent apps that perform actions of air
 * traffic control.
 * <p>
 * {@link AirTrafficControlApp Air Traffic Control apps} have a
 * {@link #appTitle name} and a
 * {@link #appDeveloper developer}. They also are associated
 * with a {@link Database flights database} and a {@link ReportGenerator reports
 * generator}.
 * </p>
 * <p style="font-size:16">
 * <b>Implementation notes</b>
 * </p>
 * <ul>
 * <li>{@link AirTrafficControlApp} instances are immutable.</li>
 * <li>Fields are final and public.</li>
 * </ul>
 *
 * @author (original) Eva Gomes, Hugo Leal, Lucas Andrade
 * @author (2nd version) Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro
 *         Antunes
 */
public abstract class AirTrafficControlApp implements Runnable
{
	
	// INSTANCE FIELDS
	
	/**
	 * The app's title.
	 */
	public final String appTitle;
	
	/**
	 * The app's developer.
	 */
	public final String appDeveloper;
	
	/**
	 * The app's flights database.
	 */
	public final Database flightsDB;
	
	/**
	 * The app's reports generator. This reporter emits reports about the
	 * {@link airtrafficcontrol.app.appforconsole.utils.hangar.Airship airships} in the
	 * database {@link AirTrafficControlApp#flightsDB flightsDB}.
	 */
	public final ReportGenerator reporter;
	
	
	
	// CONSTRUCTOR
	
	/**
	 * Creates a new Air Traffic Control app with
	 * <ul>
	 * <li>the {@link #appTitle name} {@code appTitle},</li>
	 * <li>the {@link #appDeveloper developer}
	 * {@code appDeveloper},</li>
	 * <li>a {@link Database flights database} and</li>
	 * <li>a {@link ReportGenerator reports generator}.</li>
	 * </ul>
	 * 
	 * @param appTitle
	 *            This app's name.
	 * @param appDeveloper
	 *            This app's developer.
	 * @throws InvalidArgumentException
	 *             If either {@code appTitle} or {@code appDeveloper} are
	 *             {@code null} or the empty {@link String} ({@code ""}).
	 */
	public AirTrafficControlApp( String appTitle, String appDeveloper )
			throws InvalidArgumentException {
		
		if( appTitle == null || appTitle.equals( "" ) )
			throw new InvalidArgumentException( "INVALID APP TITLE!" );
		if( appDeveloper == null || appDeveloper.equals( "" ) )
			throw new InvalidArgumentException( "INVALID APP DEVELOPER!" );
		
		this.appTitle = appTitle;
		this.appDeveloper = appDeveloper;
		flightsDB = new Database();
		reporter = new ReportGenerator();
	}
	
	
	// RUN
	
	/**
	 * Runs the app.
	 */
	public abstract void run();
}
