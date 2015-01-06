package main.java.cli.NewsByG;


import java.util.HashMap;
import java.util.Map;
import main.java.cli.ResultsTypesStringsDictionary;
import main.java.cli.translations.translatables.Translatable;


public class ToTranslatableConversor
{
	
	/**
	 * The mapping between strings that represent instance types and the
	 * {@link Converter} instance needed to convert instances of that type into
	 * {@link Translatables} (uses the {@link ResultTypesStringsDictionary}).
	 */
	public static final Map< String, Converter > RESULTS_TYPES_MAP = new HashMap< String, Converter >();
	static
	{
		RESULTS_TYPES_MAP.put( ResultsTypesStringsDictionary.USER,
				new UserConverter() );
		RESULTS_TYPES_MAP.put( ResultsTypesStringsDictionary.CIVIL,
				new CivilAirshipConverter() );
		RESULTS_TYPES_MAP.put( ResultsTypesStringsDictionary.MILITARY,
				new MilitaryAirshipConverter() );
	}
	
	/**
	 * Converts a specific object into a {@link Translatable}.
	 * 
	 * @param object
	 *            The object to be converted.
	 * @param objectConcreteType
	 *            The concrete type of {@code object}.
	 * @return
	 */
	public static Translatable convert( Object object, String objectConcreteType ) {
		//TODO: tratar null
		return RESULTS_TYPES_MAP.get( objectConcreteType ).convert( object );
		
	}
	
}
