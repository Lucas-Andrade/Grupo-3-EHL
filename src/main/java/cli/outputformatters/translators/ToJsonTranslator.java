package main.java.cli.outputformatters.translators;


import java.util.Map.Entry;
import java.util.Set;
import java.util.StringJoiner;
import main.java.cli.outputformatters.Translatable;
import main.java.utils.exceptions.formattersexceptions.UnknownTranslatableException;


/**
 * Class that have the point to translate {@link Translatable}s into {@code Json-format}, i.e.,
 * strings with the {@code Json} representation.
 * <p>
 * 
 * <b>It is advised to only try to encode {@link Translatable} instances that follow the conventions
 * on the {@link Translatable} documentation.</b>
 *
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
public class ToJsonTranslator implements Translator {
    
    /**
     * Translate a {@link Translatable} to a string with the {@code Json} representation.
     * 
     * @param translatable
     *            The {@link Translatable} to be translated.
     * @return A string with the {@code Json} representation of {@code translatable}.
     * @throws UnknownTranslatableException
     *             If {@code translatable} does not follow the conventions specified in the
     *             {@link Translatable} documentation.
     */
    @Override
    public String encode( Translatable translatable ) throws UnknownTranslatableException {
        if( translatable.getTag() == null )
            return stringEnconde( translatable );
        
        if( translatable.getEntryTag() == null )
            return simpleEncode( translatable );
        
        if( translatable.getKeyTag() == null )
            return iterableEnconde( translatable );
        
        return mapEncode( translatable );
    }
    
    /**
     * Create a String in Json representation for {@code String-Translatable}s
     * 
     * @param translatable
     * @return A string with Json representation of {@code translatable}.
     */
    private String stringEnconde( Translatable translatable ) {
        Entry< String, Object > entry =
                translatable.getPropertiesBag().entrySet().iterator().next();
        return getSimpleJsonJoiner( entry.getKey(), entry.getValue() ).toString();
    }
    
    /**
     * Create a String in Json representation for {@code simple-Translatable}s
     * 
     * @param translatable
     * @return A string with Json representation of {@code translatable}.
     */
    private String simpleEncode( Translatable translatable ) {
        return getConposedJsonBuilder( translatable.getPropertiesBag().entrySet(),
                                       translatable.getTag() ).toString();
    }
    
    /**
     * Create a String in Json representation for {@code iterable-Translatable}s
     * 
     * @param translatable
     * @return A string with Json representation of {@code translatable}.
     * @throws UnknownTranslatableException
     */
    private String iterableEnconde( Translatable translatable ) throws UnknownTranslatableException {
        StringBuilder indentation = getIndentation( ++IndentationLength );
        StringJoiner strJoinerList = new StringJoiner( ", " + indentation, "[ ", " ]" );
        try {
            for( Object object : translatable.getPropertiesBag().values() ) {
                strJoinerList.add( encode( (Translatable)object ) );
            }
        }
        catch( ClassCastException e ) {
            throw new UnknownTranslatableException(
                                                    "Translatable representing iterable has non-translatable values in the properties bag." );
        }
        
        IndentationLength-- ;
        return new StringBuilder( "{ \"" ).append( translatable.getTag() ).append( "\" : " )
                                          .append( indentation ).append( strJoinerList.toString() )
                                          .append( " }" ).toString();
    }
    
    /**
     * Create a String in Json representation for {@code map-Translatable}s
     * 
     * @param translatable
     * @return A string with Json representation of {@code translatable}.
     * @throws UnknownTranslatableException
     */
    private String mapEncode( Translatable translatable ) {
        StringBuilder indentation = getIndentation( ++IndentationLength );
        StringJoiner strJoinerList = new StringJoiner( ", " + indentation, "[ ", " ]" );
        
        for( Entry< String, Object > entry : translatable.getPropertiesBag().entrySet() ) {
            strJoinerList.add( getUnitaryConposedJsonBuilder( entry, translatable.getEntryTag(),
                                                              translatable.getKeyTag(),
                                                              translatable.getValueTag() ) );
        }
        
        return new StringBuilder( "{ \"" ).append( translatable.getTag() ).append( "\" : " )
                                          .append( indentation ).append( strJoinerList.toString() )
                                          .append( " }" ).toString();
    }
    
    // Auxiliary methods
    /**
     * Create a {@link StringJoiner} for a simple {@code Object} with the required Json format,
     * i.e., <br>
     * <code>{ "obj1" : obj2 }</code><br>
     * 
     * If {@code obj2} is a {@code String} than it will also printed with quotation marks i.e., <br>
     * <code>{ "obj1" : "obj2" }</code>
     * 
     * @param obj1
     * @param obj2
     * @return a {@code StringJoiner} with the Json format
     */
    private StringJoiner getSimpleJsonJoiner( Object obj1, Object obj2 ) {
        StringBuilder str1 = new StringBuilder( "\"" ).append( obj1 ).append( "\"" );
        StringBuilder str2 = new StringBuilder();
        
        if( obj2 instanceof String )
            str2.append( "\"" ).append( obj2 ).append( "\"" );
        else str2.append( obj2 );
        
        return new StringJoiner( " : ", "{ ", " }" ).add( str1 ).add( str2 );
    }
    
    /**
     * Create a {@link StringBuilder} for a composed {@code Object}s with the required Json format,
     * i.e.,<br>
     * <code>{ "tag" : <br>&emsp;(*), <br>&emsp;(*), <br>&emsp;(*), <br>&emsp;... }</code> <br>
     * where (*) are created by the method {@link #getSimpleJsonJoiner}.
     * 
     * @param entries
     * @param tag
     * @return a StringBuiler with the Json format
     */
    private StringBuilder
            getConposedJsonBuilder( Set< Entry< String, Object > > entries, String tag ) {
        StringBuilder indentation = getIndentation( ++IndentationLength );
        
        StringJoiner strJoinerList = new StringJoiner( "," );
        
        for( Entry< String, Object > entry : entries ) {
            strJoinerList.add( indentation
                               + getSimpleJsonJoiner( entry.getKey(), entry.getValue() ).toString() );
        }
        
        IndentationLength-- ;
        return new StringBuilder( "{ \"" ).append( tag ).append( "\" :" )
                                          .append( strJoinerList.toString() ).append( " }" );
    }
    
    /**
     * Create a {@link StringBuilder} for an {@code Entry-Object} with the required Json format,
     * i.e.,<br>
     * <code>{ "tag" : <br>&emsp;(*key) }, <br>&emsp;(*value) }</code> <br>
     * where (*key) and (*value) are created by the method {@link #getSimpleJsonJoiner}.
     * 
     * @param entry
     * @param tag
     * @param keyTag
     * @param valueTag
     * @return a StringBuiler with the Json format
     */
    private StringBuilder getUnitaryConposedJsonBuilder( Entry< String, Object > entry, String tag,
                                                         String keyTag, String valueTag ) {
        StringBuilder indentation = getIndentation( ++IndentationLength );
        StringBuilder strB =
                new StringBuilder( "{ \"" ).append( tag )
                                           .append( "\" : " )
                                           .append( indentation )
                                           .append( getSimpleJsonJoiner( keyTag, entry.getKey() ) )
                                           .append( "," )
                                           .append( indentation )
                                           .append( getSimpleJsonJoiner( valueTag, entry.getValue() ) )
                                           .append( " }" );
        IndentationLength-- ;
        return strB;
    }
    
    // Indentation
    private static int IndentationLength = 0;
    
    /**
     * Create a StringBuilder for a indentation with the specified length
     * 
     * @param indentationLength
     * @return the indentation
     */
    private StringBuilder getIndentation( int indentationLength ) {
        StringBuilder indentation = new StringBuilder( "\r\n" );
        for( int i = 0; i < indentationLength; i++ ) {
            indentation.append( "   " );
        }
        return indentation;
    }
}
