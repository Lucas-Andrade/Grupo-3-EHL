package main.java.cli.outputformatters.totranslatableconverters;


import java.util.LinkedHashMap;
import java.util.Map;
import main.java.cli.outputformatters.Translatable;
import main.java.utils.exceptions.formattersexceptions.UnknownTypeException;


/**
 * Class whose instances convert instances of {@link String} into {@link Translatable}s.
 * Translatables that represent {@link String}s have
 * {@code null tag, null entryTag, null keyTag, null valueTag} and a properties bag containing only
 * one entry .
 *
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
class StringToTranslatableConverter extends Converter {
    
    
    @Override
    Translatable convert( Object string ) throws UnknownTypeException {
        String str;
        try {
            str = (String)string;
        }
        catch( ClassCastException e ) {
            throw new UnknownTypeException(
                                            string + " could not be converted into a translatable.",
                                            e );
        }
        
        Map< String, Object > propertiesBag = new LinkedHashMap< String, Object >();
        propertiesBag.put( "message", str );
        
        return new Translatable( null, null, null, null, propertiesBag, str );
        
    }
    
}
