package airtrafficcontrol.app.utils.hangar;

import airtrafficcontrol.app.exceptions.InvalidArgumentException;
import airtrafficcontrol.app.utils.aircraftcoordinates.GeographicalPosition;


/**
 * This class verifies a UFO position, as an exception
 * @author (Revisão) Filipa Estiveira, Filipa Gonçalves, Gonçalo Carvalho, José Oliveira
 */
public class OVNI extends AirCraft
{
	
	/**
	 * This constructor identifies the ufo position
	 */
	public OVNI( GeographicalPosition statingPosition)
			throws InvalidArgumentException
	{
		super("UFO", statingPosition);
	}
}
