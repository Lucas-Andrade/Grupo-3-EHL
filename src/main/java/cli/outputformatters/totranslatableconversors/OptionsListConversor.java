package main.java.cli.outputformatters.totranslatableconversors;


import java.util.Map;
import java.util.TreeMap;
import main.java.cli.outputformatters.Translatable;
import main.java.cli.utils.OptionsList;
import main.java.cli.utils.exceptions.conversorsexceptions.UnknownTypeException;


/**
 * Class whose instances convert instances of {@link OptionsList} into
 * {@link Translatables}.
 *
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
public class OptionsListConversor extends Converter
{
	
	@Override
	Translatable convert( Object optionsList ) throws UnknownTypeException {
		
		OptionsList optList;
		try
		{
			optList = (OptionsList)optionsList;
		}
		catch( ClassCastException e )
		{
			throw new UnknownTypeException( "Could not convert " + optionsList
					+ " into a translatable." );
		}
		
		Map< String, Object > options = new TreeMap<>();
		for( Map.Entry< String, String > option : optList.options.entrySet() )
			options.put( option.getKey(), option.getValue() );
		return new Translatable( "options", "option", "command", "description",
				options, optList.options.toString() );
	}
	
}
