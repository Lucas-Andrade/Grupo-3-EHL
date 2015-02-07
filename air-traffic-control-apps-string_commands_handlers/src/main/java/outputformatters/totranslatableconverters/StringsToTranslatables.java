package outputformatters.totranslatableconverters;


import java.util.LinkedHashMap;
import java.util.Map;

import outputformatters.Translatable;
import utils.CompletionStatus;
import utils.exceptions.formattersexceptions.UnknownTypeException;


/**
 * Class that contains all {@link Converter}s that convert instances into {@link Translatable}s
 * representing {@link String}s.
 * <p>
 * <b>The following conventions are advised:</b> translatables that represent {@link String}s have
 * {@code null tag, null entryTag, null keyTag, null valueTag} and a properties bag containing only
 * one entry .
 *
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
class StringsToTranslatables {
    
    
    /**
     * Class whose instances convert instances of {@link String} into {@link Translatable}s.
     * 
     * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
     */
    static class StringConverter extends Converter {
        
        /**
         * @see outputformatters.totranslatableconverters.Converter#convert(java.lang.Object)
         */
        @Override
        Translatable convert( Object string ) throws UnknownTypeException {
        
            String str;
            try {
                str = (String)string;
            }
            catch( ClassCastException e ) {
                throw new UnknownTypeException( string
                                                + " could not be converted into a translatable.", e );
            }
            
            Map< String, Object > propertiesBag = new LinkedHashMap< String, Object >();
            propertiesBag.put( "message", str );
            
            return new Translatable( null, null, null, null, propertiesBag, str );
            
        }
        
    }
    
    
    /**
     * Class whose instances convert instances of {@link CompletionStatus} into {@link Translatable}
     * s.
     *
     * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
     */
    static class CompletionStatusConverter extends Converter {
        
        /**
         * @see outputformatters.totranslatableconverters.Converter#convert(java.lang.Object)
         */
        @Override
        Translatable convert( Object completionStatus ) throws UnknownTypeException {
        
            CompletionStatus status;
            try {
                status = (CompletionStatus)completionStatus;
            }
            catch( ClassCastException e ) {
                throw new UnknownTypeException( completionStatus
                                                + " could not be converted into a translatable.", e );
            }
            
            return new StringsToTranslatables.StringConverter().convert( status.getMessage() );
            
        }
        
    }
    
    
    /**
     * Unused private constructor
     */
    private StringsToTranslatables() {
    
    }
    
}
