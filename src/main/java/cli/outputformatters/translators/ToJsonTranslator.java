package main.java.cli.outputformatters.translators;

import java.util.Map.Entry;
import java.util.Set;
import java.util.StringJoiner;
import main.java.cli.outputformatters.Translatable;
import main.java.utils.exceptions.formattersexceptions.UnknownTranslatableException;


/**
 * Class that have the point to translate {@link Translatable}s into
 * {@code Json-format}, i.e., strings with the {@code Json} representation.
 * <p>
 * 
 * <b>It is advised to only try to encode {@link Translatable} instances that
 * follow the conventions on the {@link Translatable} documentation.</b>
 *
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
public class ToJsonTranslator
	implements Translator
{
	private final static String INDENTATION = "{\n   ";
	
	/**
	 * Translate a {@link Translatable} to a string with the {@code Json}
	 * representation.
	 * 
	 * @param translatable
	 *        The {@link Translatable} to be translated.
	 * @return A string with the {@code Json} representation of
	 *         {@code translatable}.
	 * @throws UnknownTranslatableException
	 *         If {@code translatable} does not follow the conventions specified
	 *         in the {@link Translatable} documentation.
	 */
	@Override
	public String encode( Translatable translatable ) throws UnknownTranslatableException
	{
		if( translatable.getTag() == null )
			return stringEnconde( translatable );

		if( translatable.getEntryTag() == null )
			return simpleEncode( translatable );

		if( translatable.getKeyTag() == null )
			return iterableEnconde( translatable );

		return mapEncode( translatable );
	}

	/**
	 * 
	 * @param translatable
	 * @return
	 */
	private String mapEncode( Translatable translatable )
	{
		StringJoiner strJoinerList = new StringJoiner( ", ", "[ ", " ]" );
		for( Entry< String, Object > entry : translatable.getPropertiesBag().entrySet() )
		{
			strJoinerList.add( createSimple( entry, translatable.getEntryTag(), translatable.getKeyTag(),
					translatable.getValueTag() ) );
		}

		return new StringBuilder( "{ \"" ).append( translatable.getTag() )
											.append( "\" : " )
											.append( strJoinerList.toString() )
											.append( " }" )
											.toString();
	}

	/**
	 * 
	 * @param translatable
	 * @return
	 * @throws UnknownTranslatableException
	 */
	private String iterableEnconde( Translatable translatable ) throws UnknownTranslatableException
	{
		StringJoiner strJoinerList = new StringJoiner( ", ", "[ ", " ]" );
		try
		{
			for( Object object : translatable.getPropertiesBag().values() )
			{
				strJoinerList.add( encode( ( Translatable )object ) );
			}
		}
		catch( ClassCastException e )
		{
			throw new UnknownTranslatableException(
					"Translatable representing iterable has non-translatable values in the properties bag." );
		}

		return new StringBuilder( "{ \"" ).append( translatable.getTag() )
											.append( "\" : " )
											.append( strJoinerList.toString() )
											.append( " }" )
											.toString();

	}

	/**
	 * 
	 * @param translatable
	 * @return
	 */
	private String simpleEncode( Translatable translatable )
	{
		return createSimple( translatable.getPropertiesBag().entrySet(), translatable.getTag() );
	}

	/**
	 * 
	 * @param entries
	 * @param tag
	 * @return
	 */
	private String createSimple( Set< Entry< String, Object > > entries, String tag )
	{
		StringJoiner strJoinerList = new StringJoiner( ", " );
		for( Entry< String, Object > entry : entries )
		{
			strJoinerList.merge( getSimpleJoiner( entry.getKey(), entry.getValue() ) );
		}

		return new StringBuilder( "{\n \"" ).append( tag )
											.append( "\" : " )
											.append( strJoinerList.toString() )
											.append( " }" )
											.toString();
	}

	/**
	 * 
	 * @param entry
	 * @param tag
	 * @param keyTag
	 * @param valueTag
	 * @return
	 */
	private String createSimple( Entry< String, Object > entry, String tag, String keyTag, String valueTag )
	{
		StringBuilder strB = new StringBuilder( INDENTATION ).append( "\"" ).append( tag )
				.append( "\" : " )
				.append( getSimpleJoiner( keyTag, entry.getKey() ) )
				.append( ", " )
				.append( getSimpleJoiner( valueTag, entry.getValue() ) )
				.append( " }" );
		return strB.toString();
	}

	/**
	 * 
	 * @param translatable
	 * @return
	 */
	private String stringEnconde( Translatable translatable )
	{
		return getSimpleJoiner( "message", translatable.toString() ).toString();
	}

	/**
	 * 
	 * @param obj1
	 * @param obj2
	 * @return
	 */
	private StringJoiner getSimpleJoiner( Object obj1, Object obj2 )
	{
		StringBuilder str1 = new StringBuilder( "\"" ).append( obj1 ).append( "\"" );
		StringBuilder str2 = new StringBuilder();

		if( obj2 instanceof String )
			str2.append( "\"" ).append( obj2 ).append( "\"" );
		else
			str2.append( obj2 );

		return new StringJoiner( " : ", INDENTATION, " }" ).add( str1 ).add( str2 );
	}
}
