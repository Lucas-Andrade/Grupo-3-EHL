package deprecated;

import utils.FlightPlan;
import utils.GeographicalPosition;
import utils.MilitaryAirplane;

public class ReconnaissanceAircraft extends MilitaryAirplane{
	
	@Deprecated
	public ReconnaissanceAircraft(String flightID,
			GeographicalPosition statingPosition, FlightPlan flightPlan, boolean armament) {
		super(flightID, statingPosition, flightPlan, armament);
		
	}

	
}
