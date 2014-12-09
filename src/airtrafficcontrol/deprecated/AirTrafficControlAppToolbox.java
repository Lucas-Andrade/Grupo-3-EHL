package airtrafficcontrol.deprecated;


import airtrafficcontrol.app.AirTrafficControlApp;
import airtrafficcontrol.app.appforconsole.utils.towerControl.Database;
import airtrafficcontrol.app.appforconsole.utils.towerControl.ReportGenerator;


/**
 * Class whose subclasses's instances provide tools for apps of air traffic
 * control. Instances have a {@link Database flights database} and a
 * {@link ReportGenerator reports generator}.
 * 
 * <p style="font-size:16">
 * <b>Implementation notes</b>
 * </p>
 * <ul>
 * <li>Fields are final and public.</li>
 * </ul>
 *
 * @author Eva Gomes, Hugo Leal, Lucas Andrade
 * @author (deprecated by) Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro
 *         Antunes
 */
public class AirTrafficControlAppToolbox
{
	
	// CAMPOS DA CLASSE
	
	/**
	 * The app's flights database.
	 */
	public final Database flightsDB;
	
	/**
	 * The app's reports generator. This reporter emits reports about the
	 * {@link Airship airships} in the database
	 * {@link AirTrafficControlApp#flightsDB flightsDB}.
	 */
	public final ReportGenerator reporter;
	
	
	// CONSTRUTOR
	
	/**
	 * Creates a new instance of type {@link AirTrafficControlAppToolbox}.
	 * <p>
	 * This instance provides:
	 * <ul>
	 * <li>an empty {@link airtrafficcontrol.app.appforconsole.utils.towerControl.Database
	 * flights' database}</li>
	 * <li>a {@link airtrafficcontrol.app.appforconsole.utils.towerControl.ReportGenerator
	 * reports generator}.
	 * </ul>
	 * for the app.
	 * </p>
	 */
	public AirTrafficControlAppToolbox() {
		flightsDB = new Database();
		reporter = new ReportGenerator();
	}
	
}
