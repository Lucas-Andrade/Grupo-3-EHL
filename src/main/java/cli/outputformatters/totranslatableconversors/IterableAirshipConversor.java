package main.java.cli.outputformatters.totranslatableconversors;

import java.util.HashMap;
import java.util.Map;
import main.java.cli.model.airships.Airship;
import main.java.cli.outputformatters.Translatable;
import main.java.cli.utils.exceptions.conversorsexceptions.UnknownTypeException;


/**
 * Class whose instances convert instances of {@code Iterable<Airship>} into
 * {@link Translatables}.
 *
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
class IterableAirshipConversor extends Conversor
{
	@SuppressWarnings( "unchecked" )
	@Override
	Translatable convert( Object iterableOfAirships ) throws UnknownTypeException {

		Iterable< Airship > it;
		try
		{
			it = (Iterable< Airship >)iterableOfAirships;
		}
		catch( ClassCastException e )
		{
			throw new UnknownTypeException( "Could not convert " + iterableOfAirships
					+ " into a translatable." );
		}
		
		Map< String, Object > propertiesBag = new HashMap< String, Object >();		
		for( Airship user : it )
		{
			propertiesBag.put( user.getIdentification(),
					ToTranslatableConversor.convert( user ) );
		}
		
		return new Translatable( "airships", "airship", null, null, propertiesBag, null );
	}
	
}
