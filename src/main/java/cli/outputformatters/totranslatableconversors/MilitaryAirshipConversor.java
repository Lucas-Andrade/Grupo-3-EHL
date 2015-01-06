package main.java.cli.outputformatters.totranslatableconversors;


import java.util.Map;
import main.java.cli.model.airships.MilitaryAirship;
import main.java.cli.outputformatters.Translatable;
import main.java.cli.utils.CommandLineStringsDictionary;
import main.java.cli.utils.exceptions.conversorsexceptions.UnknownTypeException;


/**
 * Class whose instances convert instances of {@link MilitaryAirships} into
 * {@link Translatables}.
 *
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
class MilitaryAirshipConversor extends AirshipConversor
{
	
	@Override
	Translatable convert( Object militaryAirship ) throws UnknownTypeException {
		
		MilitaryAirship ma;
		try
		{
			ma = (MilitaryAirship)militaryAirship;
		}
		catch( ClassCastException e )
		{
			throw new UnknownTypeException( "Could not convert "
					+ militaryAirship + " into a translatable." );
		}
		
		Map< String, Object > propertiesBag = createAirshipPropertiesBag( ma );
		propertiesBag.put( CommandLineStringsDictionary.HASARMOUR, ma.hasWeapons() );

		return new Translatable( "militaryAirship", null, null, null,
				propertiesBag, ma.toString() );
		
	}
}
