package main.java.cli.NewsByG;

import java.util.Map;

import main.java.cli.model.airships.Airship;
import main.java.cli.model.airships.MilitaryAirship;
import main.java.cli.translations.translatables.Translatable;

public class MilitaryAirshipConverter extends AirshipConverter
{
	@Override
	public Translatable convert( Airship militaryAirship )
	{
		Map< String, Object > propertiesBag = createAirshipPropertiesBag( militaryAirship );

		propertiesBag.put( "Carries Weapons", String.valueOf( ((MilitaryAirship)militaryAirship).hasWeapons() ) );
		return new Translatable( null, "Military Airship", null, null, propertiesBag, militaryAirship.toString() );
	}
}
