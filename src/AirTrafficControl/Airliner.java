package AirTrafficControl;

public class Airliner extends CivilAirplanes{

	public Airliner(String flightID, GeographicalPosition statingPosition,
			FlightPlan flightPlan) {
		super(flightID, statingPosition, flightPlan);
		// TODO Auto-generated constructor stub
	}

	private int passengersNum;

	/**
	 * @return the number of passengers in the airplane
	 */
	public int getPassengersNumber()
	{
		return passengersNum;
	}
	
	/**
	 * sets flying to false and the number of passengers to 0
	 */
	@Override
	protected void land()
	{
		passengersNum = 0;
		flying = false;
	}
	

}
