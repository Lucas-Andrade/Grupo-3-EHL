package airtrafficcontrol.deprecated;

import airtrafficcontrol.app.appforconsole.exceptions.InvalidArgumentException;
import airtrafficcontrol.app.appforconsole.utils.FlightPlan;
import airtrafficcontrol.app.appforconsole.utils.GeographicalPosition;
import airtrafficcontrol.app.appforconsole.utils.MilitaryAirplane;

public class ReconnaissanceAircraft extends MilitaryAirplane{
	
	@Deprecated
	public ReconnaissanceAircraft(String flightID,
			GeographicalPosition statingPosition, FlightPlan flightPlan, boolean armament) throws InvalidArgumentException {
		super(flightID, statingPosition, flightPlan, armament);
		
	}

	@Override
	public void setNumberOfMinutesToTakeOff(int newTime)
			throws InvalidArgumentException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setNumberOfMinutesToLand(int newTime)
			throws InvalidArgumentException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setNumberOfMinutesToSwitchCorridor(int newTime)
			throws InvalidArgumentException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getNumberOfMinutesToTakeOff() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getNumberOfMinutesToLand() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getNumberOfMinutesToSwitchCorridor() {
		// TODO Auto-generated method stub
		return 0;
	}

	
}
