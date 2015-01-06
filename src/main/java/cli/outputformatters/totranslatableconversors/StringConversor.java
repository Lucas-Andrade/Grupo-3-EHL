package main.java.cli.outputformatters.totranslatableconversors;

import java.util.HashMap;
import java.util.Map;

import main.java.cli.outputformatters.Translatable;

public class StringConversor
	implements Converter
{

	@Override
	public Translatable convert( Object string )
	{
		// TODO cast exceptions
		String str = ( String )string;

		Map< String, Object > propertiesBag = new HashMap< String, Object >();
		propertiesBag.put( "Message", str );

		return new Translatable( null, null, null, null, propertiesBag, str );
	}
}
