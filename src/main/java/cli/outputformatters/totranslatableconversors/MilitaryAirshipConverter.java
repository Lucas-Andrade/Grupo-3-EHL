package main.java.cli.outputformatters.totranslatableconversors;

import java.util.Map;
import main.java.cli.model.airships.MilitaryAirship;
import main.java.cli.outputformatters.Translatable;

/**
 * Class whose instances convert instances of {@link MilitaryAirships} into
 * {@link Translatables}.
 *
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
class MilitaryAirshipConverter extends AirshipConverter
{
	@Override
	Translatable convert( Object militaryAirship )
	{
		MilitaryAirship ma = (MilitaryAirship)militaryAirship;
		Map< String, Object > propertiesBag = createAirshipPropertiesBag( ma );

		propertiesBag.put( "Carries Weapons", String.valueOf( ((MilitaryAirship)ma).hasWeapons() ) );
		return new Translatable( null, "Military Airship", null, null, propertiesBag, ma.toString() );
	}
}
