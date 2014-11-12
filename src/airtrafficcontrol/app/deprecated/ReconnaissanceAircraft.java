package airtrafficcontrol.app.deprecated;

import airtrafficcontrol.app.utils.FlightPlan;
import airtrafficcontrol.app.utils.GeographicalPosition;
import airtrafficcontrol.app.utils.MilitaryAirplane;

public class ReconnaissanceAircraft extends MilitaryAirplane{
	
	@Deprecated
	public ReconnaissanceAircraft(String flightID,
			GeographicalPosition statingPosition, FlightPlan flightPlan, boolean armament) {
		super(flightID, statingPosition, flightPlan, armament);
		
	}

	
}
