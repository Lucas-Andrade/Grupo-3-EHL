package airtrafficcontrol.app.appforconsole.utils.hangar;

import airtrafficcontrol.app.appforconsole.exceptions.InvalidArgumentException;
import airtrafficcontrol.app.appforconsole.utils.aircraftcoordinates.GeographicalPosition;
import airtrafficcontrol.app.appforconsole.utils.airshipplan.FlightPlan;

/**
 * Abstract class that represents an Aisplane with a position, an ID and a corridor
 *
 * @author Filipa Estiveira, Filipa Gonçalves, Gonçalo Carvalho, José Oliveira
 */
public abstract class AirPlane extends Airship
{
	
	/**
	 * This construtor identifies the features if the airplane and throws an IO exception if
	 * these features aren't verified 
	 */
	public AirPlane(String flightID, GeographicalPosition statingPosition, FlightPlan flightPlan )
			throws InvalidArgumentException
	{
		super(flightID, statingPosition, flightPlan);
	}
	
	
	
}
