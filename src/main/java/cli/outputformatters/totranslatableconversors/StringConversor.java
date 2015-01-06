package main.java.cli.outputformatters.totranslatableconversors;

import java.util.HashMap;
import java.util.Map;
import main.java.cli.outputformatters.Translatable;
import main.java.cli.utils.exceptions.conversorsexceptions.UnknownTypeException;

/**
 * Class whose instances convert instances of {@link String} into
 * {@link Translatables}.
 *
 *@author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
class StringConversor extends Conversor
{
	@Override
	Translatable convert( Object string ) throws UnknownTypeException
	{
		String str;
		try
		{
			str = (String)string;
		}
		catch( ClassCastException e )
		{
			throw new UnknownTypeException( "Could not convert "
					+ string + " into a translatable." );
		}

		Map< String, Object > propertiesBag = new HashMap< String, Object >();
		propertiesBag.put( "message", str );

		return new Translatable( null, null, null, null, propertiesBag, str );

	}
}
