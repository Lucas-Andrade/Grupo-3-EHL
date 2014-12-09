package airtrafficcontrol.app.appforconsole.utils.hangar;


import airtrafficcontrol.app.appforconsole.exceptions.InvalidArgumentException;
import airtrafficcontrol.app.appforconsole.utils.aircraftcoordinates.GeographicalPosition;


/**
 * This class verifies a UFO position, as an exception
 * 
 * @author (Revisão) Filipa Estiveira, Filipa Gonçalves, Gonçalo Carvalho, José
 *         Oliveira
 */
public class OVNI extends AirCraft
{
	
	/**
	 * This constructor identifies the ufo position
	 */
	public OVNI( GeographicalPosition statingPosition )
			throws InvalidArgumentException {
		super( "UFO", statingPosition );
	}
	
	
	/**
	 * Creates a list of the details of this airship in a string array.
	 * 
	 * @throws InvalidArgumentException
	 */
	public String[] toStringArray() {
		
		GeographicalPosition pos = getGeographicPosition();
		String[] details = new String[3];
		details[0] = new StringBuilder( "FlightID: unknown" ).toString();
		details[1] = new StringBuilder( "\n\nGeographic Position\nLatitude: " )
				.append( (new Double( pos.getLatitude() )).toString() )
				.append( "º\nLongitude: " )
				.append( (new Double( pos.getLongitude() )).toString() )
				.append( "º\nAltitude: " )
				.append( (new Double( pos.getAltitude() )).toString() )
				.append( "meters" ).toString();
		details[2] = "\n\nObservations: UFO";
		return details;
		
	}
}
