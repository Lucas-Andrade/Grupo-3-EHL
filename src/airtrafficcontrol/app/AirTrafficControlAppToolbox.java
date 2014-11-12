package airtrafficcontrol.app;


import airtrafficcontrol.app.utils.Database;
import airtrafficcontrol.app.utils.ReportGenerator;


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
 * @author Eva Gomes
 * @author Hugo Leal
 * @author Lucas Andrade
 */
public class AirTrafficControlAppToolbox
{
	
	// CAMPOS DA CLASSE
	
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
	 * Creates a new instance of type {@link AirTrafficControlAppToolbox}.
	 * <p>
	 * This instance provides:
	 * <ul>
	 * <li>an empty {@link airtrafficcontrol.app.utils.Database flights'
	 * database}</li>
	 * <li>a {@link airtrafficcontrol.app.utils.ReportGenerator reports
	 * generator}.
	 * </ul>
	 * for the app.
	 * </p>
	 */
	public AirTrafficControlAppToolbox() {
		
		flightsDB = new Database();
		reporter = new ReportGenerator();
	}
	
}
