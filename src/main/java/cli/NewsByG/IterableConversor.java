package main.java.cli.NewsByG;

import java.util.HashMap;
import java.util.Map;

import main.java.cli.model.Element;
import main.java.cli.translations.translatables.Translatable;

/**TODO
 * ?!?!?! Iterable generic -> <?> or <Element>
 * Como fazemos o iterable?!?!      p_q
 * 
 *
 *@author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
public class IterableConversor implements Converter<Iterable<Element>>
{
	@Override
	public Translatable convert( Iterable<Element> iterable )
	{
		Map< String, Object > propertiesBag =  new HashMap< String, Object >();

		for( Element element : iterable )
		{
			propertiesBag.put( element.getIdentification(), convert( element ) );
		}

		return new Translatable( tag, null, null, null, propertiesBag, null );
	}

}
