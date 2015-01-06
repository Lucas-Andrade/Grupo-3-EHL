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
abstract class AirshipConverter extends Converter
{
	
	/**
	 * TODO
	 * 
	 * @param airship
	 * @return
	 */
	protected Map< String, Object > createAirshipPropertiesBag( Airship airship ) {
		Map< String, Object > propertiesBag = new HashMap< String, Object >();
		propertiesBag.put( CommandLineStringsDictionary.FLIGHTID,
				airship.getIdentification() );
		propertiesBag.put(
				CommandLineStringsDictionary.LATITUDE,
				String.valueOf( airship.getCoordinates().getLatitude()
						.getValue() ) );
		propertiesBag.put(
				CommandLineStringsDictionary.LONGITUDE,
				String.valueOf( airship.getCoordinates().getLongitude()
						.getValue() ) );
		propertiesBag.put(
				CommandLineStringsDictionary.ALTITUDE,
				String.valueOf( airship.getCoordinates().getAltitude()
						.getValue() ) );
		propertiesBag.put(
				CommandLineStringsDictionary.AIRCORRIDOR_MINALTITUDE,
				String.valueOf( airship.getAirCorridor().getMinAltitude() ) );
		propertiesBag.put(
				CommandLineStringsDictionary.AIRCORRIDOR_MAXALTITUDE,
				String.valueOf( airship.getAirCorridor().getMaxAltitude() ) );
		propertiesBag.put( "Is Outside The Given Corridor",
				String.valueOf( airship.isTransgressing() ) );
		
		return propertiesBag;
	}
	
}
