package main.java.cli.outputformatters.totranslatableconversors;

import java.util.Map;
import main.java.cli.model.airships.MilitaryAirship;
import main.java.cli.outputformatters.Translatable;

class MilitaryAirshipConverter extends AirshipConverter
{
	@Override
	public Translatable convert( Object militaryAirship )
	{
		MilitaryAirship ma = (MilitaryAirship)militaryAirship;
		Map< String, Object > propertiesBag = createAirshipPropertiesBag( ma );

		propertiesBag.put( "Carries Weapons", String.valueOf( ((MilitaryAirship)ma).hasWeapons() ) );
		return new Translatable( "MilitaryAirship", null, null, null, propertiesBag, ma.toString() );
	}
}
