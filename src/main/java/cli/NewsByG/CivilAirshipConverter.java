package main.java.cli.NewsByG;

import java.util.Map;
import main.java.cli.model.airships.CivilAirship;
import main.java.cli.translations.translatables.Translatable;
import main.java.cli.CommandLineStringsDictionary;

/**TODO
 * 
 * 
 *
 *@author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
public class CivilAirshipConverter extends AirshipConverter
{
	@Override
	Translatable convert( Object civilAirship )
	{
		CivilAirship ca = (CivilAirship) civilAirship;
		
		Map< String, Object > propertiesBag = createAirshipPropertiesBag( ca );

		propertiesBag.put( "Number of Passengers", String.valueOf( ((CivilAirship)ca).getPassengers() ) );
		
		return new Translatable( null, "Civil Airship", null, null, propertiesBag, ca.toString() );
	}
}
