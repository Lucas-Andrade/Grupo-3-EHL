package main.java.cli.NewsByG;

import java.util.HashMap;
import java.util.Map;

import main.java.cli.CommandLineDictionary;
import main.java.cli.model.airships.Airship;

/**TODO
 * 
 * 
 *
 *@author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
public abstract class AirshipConverter implements Converter< Airship >
{
	/**TODO
	 * 
	 * @param airship
	 * @return
	 */
	protected Map< String, Object > createAirshipPropertiesBag( Airship airship )
	{
		Map< String, Object > propertiesBag = new HashMap< String, Object >();
		propertiesBag.put( CommandLineDictionary.FLIGHTID, airship.getIdentification() );
		propertiesBag.put( CommandLineDictionary.LATITUDE,
				String.valueOf( airship.getCoordinates().getLatitude().getValue() ) );
		propertiesBag.put( CommandLineDictionary.LONGITUDE,
				String.valueOf( airship.getCoordinates().getLongitude().getValue() ) );
		propertiesBag.put( CommandLineDictionary.ALTITUDE,
				String.valueOf( airship.getCoordinates().getAltitude().getValue() ) );
		propertiesBag.put( CommandLineDictionary.AIRCORRIDOR_MINALTITUDE,
				String.valueOf( airship.getAirCorridor().getMinAltitude() ) );
		propertiesBag.put( CommandLineDictionary.AIRCORRIDOR_MAXALTITUDE,
				String.valueOf( airship.getAirCorridor().getMaxAltitude() ) );
		propertiesBag.put( "Is Outside The Given Corridor", String.valueOf( airship.isTransgressing() ) );

		return propertiesBag;
	}
}
