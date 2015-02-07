package outputformatters.translators;


import java.util.Map.Entry;

import outputformatters.Translatable;
import utils.exceptions.formattersexceptions.UnknownTranslatableException;


/**
 * Class whose instances have the task of translating {@link Translatable} instances into formatted
 * strings.
 * <p>
 * <b>It is advised to only try to encode {@link Translatable} instances that follow the conventions
 * on the {@link Translatable} documentation.</b>
 * </p>
 *
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
public class ToPlainTextTranslator implements Translator {
    
    // PUBLIC METHOD
    
    /**
     * Returns a string representation of {@code translatable}. <b>It is advised to only try to
     * encode {@link Translatable} instances that follow the conventions on the {@link Translatable}
     * documentation.</b>
     * 
     * @param translatable
     *            The {@link Translatable} to be translated.
     * @return A string representation of {@code translatable}.
     * @throws UnknownTranslatableException
     *             If {@code translatable} does not follow the conventions specified in the
     *             {@link Translatable} documentation.
     */
    @Override
    public String encode( Translatable translatable ) throws UnknownTranslatableException {
    
        // ATTENTION: the order of the if statements on this method must not be
        // changed unless you thought really well about what you're about to do!
        
        // if translatable represents a String or a simple instance
        if( translatable.getEntryTag() == null )
            return translatable.toString();
        
        // if translatable represents an iterable
        if( translatable.getKeyTag() == null )
            return encodeIterable( translatable );
        
        // if translatable represents a Map<String,String>
        return encodeMap( translatable );
    }
    
    
    
    // PRIVATE METHODS
    
    /**
     * Returns a string representation of {@code translatable}, which is assumed to represent an
     * {@code Map<String,String>}.
     * 
     * @param translatable
     *            The {@link Translatable} to be translated.
     * @return A string representation of {@code translatable}.
     */
    private String encodeMap( Translatable translatable ) {
    
        String entryDelimiter = "\r\n\r\n";
        
        StringBuilder sb =
                new StringBuilder( "__/" ).append( translatable.getTag() ).append( "\\__" )
                                          .append( entryDelimiter );
        
        for( Entry< String, Object > entry : translatable.getPropertiesBag().entrySet() )
            sb.append( "  " ).append( entry.getKey() ).append( "\r\n" ).append( entry.getValue() )
              .append( entryDelimiter );
        
        
        return sb.append( "\\__" ).append( translatable.getTag() ).append( "__/" ).toString();
    }
    
    /**
     * Returns a string representation of {@code translatable}, which is assumed to represent an
     * {@link Iterable}.
     * 
     * @param translatable
     *            The {@link Translatable} to be translated.
     * @return A string representation of {@code translatable}.
     * @throws UnknownTranslatableException
     *             If {@code translatable} does not follow the conventions specified in the
     *             {@link Translatable} documentation.
     */
    private String encodeIterable( Translatable translatable ) throws UnknownTranslatableException {
    
        String entryDelimiter = "\r\n\r\n";
        
        StringBuilder sb =
                new StringBuilder( "__/" ).append( translatable.getTag() ).append( "\\__" )
                                          .append( entryDelimiter );
        
        try {
            for( Object element : translatable.getPropertiesBag().values() )
                sb.append( encode( (Translatable)element ) ).append( entryDelimiter );
        }
        catch( ClassCastException e ) {
            throw new UnknownTranslatableException(
                                                    "Translatable representing iterable has non-translatable values in the properties bag.",
                                                    e );
        }
        
        return sb.append( "\\__" ).append( translatable.getTag() ).append( "__/" ).toString();
    }
    
}
