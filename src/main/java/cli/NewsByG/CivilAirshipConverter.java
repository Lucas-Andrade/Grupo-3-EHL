package main.java.cli.NewsByG;

import java.util.Map;

import main.java.cli.model.airships.Airship;
import main.java.cli.model.airships.CivilAirship;
import main.java.cli.translations.translatables.Translatable;

/**TODO
 * 
 * 
 *
 *@author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
public class CivilAirshipConverter extends AirshipConverter
{
	@Override
	public Translatable convert( Airship civilAirship )
	{
		Map< String, Object > propertiesBag = createAirshipPropertiesBag( civilAirship );

		propertiesBag.put( "Number of Passengers", String.valueOf( ((CivilAirship)civilAirship).getPassengers() ) );
		
		return new Translatable( null, "Civil Airship", null, null, propertiesBag, civilAirship.toString() );
	}
}
