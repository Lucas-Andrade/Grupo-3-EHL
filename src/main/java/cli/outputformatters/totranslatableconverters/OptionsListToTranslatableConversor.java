package main.java.cli.outputformatters.totranslatableconverters;


import java.util.Map;
import java.util.TreeMap;
import main.java.cli.outputformatters.Translatable;
import main.java.utils.OptionsList;
import main.java.utils.exceptions.formattersexceptions.UnknownTypeException;


/**
 * Class whose instances convert instances of {@link OptionsList} into {@link Translatable}s.
 * <p>
 * <b>The following conventions are advised:</b> translatables that represent
 * {@code Map<String,String>} have all tags with non-{@code null} values and several entries in the
 * properties bag.
 * </p>
 *
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
class MapsToTranslatables {
    
    
    static class OptionsListConverter extends Converter {
        
        @Override
        Translatable convert( Object optionsList ) throws UnknownTypeException {
            
            OptionsList optList;
            try {
                optList = (OptionsList)optionsList;
            }
            catch( ClassCastException e ) {
                throw new UnknownTypeException( optionsList
                                                + " could not be converted into a translatable.", e );
            }
            
            Map< String, Object > options = new TreeMap<>();
            for( Map.Entry< String, String > option : optList.options.entrySet() )
                options.put( option.getKey(), option.getValue() );
            return new Translatable( "options", "option", "command", "description", options,
                                     optList.options.toString() );
        }
        
    }
    
    
}