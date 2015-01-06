package main.java.cli.outputformatters.totranslatableconversors;

import java.util.Map;
import main.java.cli.model.airships.CivilAirship;
import main.java.cli.outputformatters.Translatable;
import main.java.cli.utils.CommandLineStringsDictionary;
import main.java.cli.utils.exceptions.conversorsexceptions.UnknownTypeException;

/**
 * Class whose instances convert instances of {@link CivilAirship} into
 * {@link Translatables}.
 *
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
public class CivilAirshipConversor extends AirshipConversor
{
	@Override
	Translatable convert( Object civilAirship ) throws UnknownTypeException
	{
		CivilAirship ca;
		try
		{
			ca = (CivilAirship)civilAirship;
		}
		catch( ClassCastException e )
		{
			throw new UnknownTypeException( "Could not convert "
					+ civilAirship + " into a translatable." );
		}
		
		Map< String, Object > propertiesBag = createAirshipPropertiesBag( ca );
		propertiesBag.put( CommandLineStringsDictionary.NUMBEROFPASSENGERS, ca.getPassengers() );

		return new Translatable( "civilAirship", null, null, null,
				propertiesBag, ca.toString() );
		
	}
}
