package main.java.cli.outputformatters.totranslatableconversors;

import java.util.HashMap;
import java.util.Map;

import main.java.cli.outputformatters.Translatable;

/**
 * 
 * 
 *
 *@author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
class StringConversor extends Conversor
{
	@Override
	Translatable convert( Object string )
	{
		// TODO cast exceptions
		String str = ( String )string;

		Map< String, Object > propertiesBag = new HashMap< String, Object >();
		propertiesBag.put( "message", str );

		return new Translatable( null, null, null, null, propertiesBag, str );
	}
}
