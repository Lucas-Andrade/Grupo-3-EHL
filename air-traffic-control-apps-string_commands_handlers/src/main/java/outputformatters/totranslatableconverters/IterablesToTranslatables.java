package outputformatters.totranslatableconverters;


import java.util.LinkedHashMap;
import java.util.Map;

import elements.Airship;
import elements.User;
import outputformatters.Translatable;
import utils.exceptions.formattersexceptions.UnknownTypeException;


/**
 * Class that contains all {@link Converter}s that convert {@code Iterable}s into
 * {@link Translatable}s representing .
 * <p>
 * <b>The following conventions are advised:</b> translatables that represent {@link Iterable}s have
 * {@code null keyTag, null valueTag}, a non- {@code null tag}, a non-{@code null entryTag} (which
 * shall be the singular of {@code tag}) and the entries in the properties bag have
 * {@link Translatables} as values (their keys are useless).
 * </p>
 *
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
class IterablesToTranslatables {
    
    
    /**
     * Class whose instances convert instances of {@code Iterable<User>} into {@link Translatables}.
     *
     * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
     */
    static class IterableUserConverter extends Converter {
        
        /**
         * @see outputformatters.totranslatableconverters.Converter#convert(java.lang.Object)
         */
        @SuppressWarnings( "unchecked" )
        @Override
        Translatable convert( Object iterableOfUsers ) throws UnknownTypeException {
        
            Iterable< User > it;
            try {
                it = (Iterable< User >)iterableOfUsers;
            }
            catch( ClassCastException e ) {
                throw new UnknownTypeException( iterableOfUsers
                                                + " could not be converted into a translatable.", e );
            }
            
            Map< String, Object > propertiesBag = new LinkedHashMap< String, Object >();
            for( User user : it ) {
                propertiesBag.put( user.getIdentification(), ToTranslatableConverter.convert( user ) );
            }
            
            return new Translatable( "users", "user", null, null, propertiesBag, null );
        }
    }
    
    
    /**
     * Class whose instances convert instances of {@code Iterable<Airship>} into
     * {@link Translatables}.
     *
     * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
     */
    static class IterableAirshipConverter extends Converter {
        
        /**
         * @see outputformatters.totranslatableconverters.Converter#convert(java.lang.Object)
         */
        @SuppressWarnings( "unchecked" )
        @Override
        Translatable convert( Object iterableOfAirships ) throws UnknownTypeException {
        
            Iterable< Airship > it;
            try {
                it = (Iterable< Airship >)iterableOfAirships;
            }
            catch( ClassCastException e ) {
                throw new UnknownTypeException( iterableOfAirships
                                                + " could not be converted into a translatable.", e );
            }
            
            Map< String, Object > propertiesBag = new LinkedHashMap< String, Object >();
            for( Airship user : it ) {
                propertiesBag.put( user.getIdentification(), ToTranslatableConverter.convert( user ) );
            }
            
            return new Translatable( "airships", "airship", null, null, propertiesBag, null );
        }
    }
    
    
    /**
     * Unused private constructor
     */
    private IterablesToTranslatables() {
    
    }
    
}
