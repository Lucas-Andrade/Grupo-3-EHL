package main.java.cli.NewsByG;

import java.util.Map;
import main.java.cli.model.airships.MilitaryAirship;
import main.java.cli.translations.translatables.Translatable;

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
