package main.java.cli.outputformatters.totranslatableconversors;


import java.util.HashMap;
import java.util.Map;
import main.java.cli.model.airships.Airship;
import main.java.cli.utils.CommandLineStringsDictionary;


/**
 * Class whose subclasses' instances convert instances of {@link Airship}'s
 * subclasses into {@link Translatables}.
 *
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
abstract class AirshipConversor
	extends Conversor
{

	/**
	 * TODO
	 * 
	 * @param airship
	 * @return
	 */
	Map< String, Object > createAirshipPropertiesBag( Airship airship )
	{

		Map< String, Object > propertiesBag = new HashMap< String, Object >();
		propertiesBag.put( CommandLineStringsDictionary.FLIGHTID, airship.getIdentification() );
		propertiesBag.put( CommandLineStringsDictionary.LATITUDE, airship.getCoordinates().getLatitude()
				.getValue() );
		propertiesBag.put( CommandLineStringsDictionary.LONGITUDE, airship.getCoordinates().getLongitude()
				.getValue() );
		propertiesBag.put( CommandLineStringsDictionary.ALTITUDE, airship.getCoordinates().getAltitude()
				.getValue() );
		propertiesBag.put( CommandLineStringsDictionary.AIRCORRIDOR_MINALTITUDE, airship.getAirCorridor()
				.getMinAltitude() );
		propertiesBag.put( CommandLineStringsDictionary.AIRCORRIDOR_MAXALTITUDE, airship.getAirCorridor()
				.getMaxAltitude() );

		return propertiesBag;
	}

}
