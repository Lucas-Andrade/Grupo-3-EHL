package main.java.cli.NewsByG;

import java.util.HashMap;
import java.util.Map;

import main.java.cli.CommandLineStringsDictionary;
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
		propertiesBag.put( CommandLineStringsDictionary.FLIGHTID, airship.getIdentification() );
		propertiesBag.put( CommandLineStringsDictionary.LATITUDE,
				String.valueOf( airship.getCoordinates().getLatitude().getValue() ) );
		propertiesBag.put( CommandLineStringsDictionary.LONGITUDE,
				String.valueOf( airship.getCoordinates().getLongitude().getValue() ) );
		propertiesBag.put( CommandLineStringsDictionary.ALTITUDE,
				String.valueOf( airship.getCoordinates().getAltitude().getValue() ) );
		propertiesBag.put( CommandLineStringsDictionary.AIRCORRIDOR_MINALTITUDE,
				String.valueOf( airship.getAirCorridor().getMinAltitude() ) );
		propertiesBag.put( CommandLineStringsDictionary.AIRCORRIDOR_MAXALTITUDE,
				String.valueOf( airship.getAirCorridor().getMaxAltitude() ) );
		propertiesBag.put( "Is Outside The Given Corridor", String.valueOf( airship.isTransgressing() ) );

		return propertiesBag;
	}
}
