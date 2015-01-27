package main.java.cli.outputformatters.totranslatableconverters;


import java.util.LinkedHashMap;
import java.util.Map;
import main.java.cli.outputformatters.Translatable;
import main.java.domain.model.airships.Airship;
import main.java.domain.model.users.User;
import main.java.utils.exceptions.formattersexceptions.UnknownTypeException;


/**
 * Class that contains all classes whose instances convert instances of {@code Iterable}s into
 * {@link Translatable}s.
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
    
    
}
