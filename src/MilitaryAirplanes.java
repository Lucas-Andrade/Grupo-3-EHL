
public abstract class MilitaryAirplanes extends Airplane{
	
	private boolean carriesArmament;
	
	public MilitaryAirplanes(String flightID, AltitudeCorridor airCorridor,
			int currentPassengers, GeographicalPosition geographicalPosition, boolean armament) {
		super(flightID, airCorridor, currentPassengers, geographicalPosition);
		carriesArmament = armament;
		// TODO Auto-generated constructor stub
	}
	
}
