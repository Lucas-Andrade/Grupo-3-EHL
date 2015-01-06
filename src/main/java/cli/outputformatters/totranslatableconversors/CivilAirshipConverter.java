package main.java.cli.outputformatters.totranslatableconversors;

import java.util.Map;
import main.java.cli.model.airships.CivilAirship;
import main.java.cli.outputformatters.Translatable;

/**TODO
 * 
 * 
 *
 *@author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
class CivilAirshipConverter extends AirshipConverter
{
	@Override
	public Translatable convert( Object civilAirship )
	{
		CivilAirship ca = (CivilAirship) civilAirship;
		
		Map< String, Object > propertiesBag = createAirshipPropertiesBag( ca );

		propertiesBag.put( "Number of Passengers", String.valueOf( ((CivilAirship)ca).getPassengers() ) );
		
		return new Translatable( "CivilAirship", null, null, null, propertiesBag, ca.toString() );
	}
}
