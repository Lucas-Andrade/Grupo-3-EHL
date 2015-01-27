package main.java.cli.outputformatters.totranslatableconverters;


import java.util.LinkedHashMap;
import java.util.Map;
import main.java.cli.CLIStringsDictionary;
import main.java.cli.outputformatters.Translatable;
import main.java.domain.model.airships.Airship;
import main.java.domain.model.airships.CivilAirship;
import main.java.domain.model.airships.MilitaryAirship;
import main.java.domain.model.users.User;
import main.java.utils.exceptions.formattersexceptions.UnknownTypeException;


/**
 * Class that contains all classes whose instances convert instances of {@code simple} into
 * {@link Translatable}s.
 * <p>
 * <b>The following conventions are advised:</b> translatables that represent simple instances have
 * {@code null entryTag, null keyTag, null valueTag}, a non-{@code null tag} and several entries in
 * the properties bag.
 * <p>
 *
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
class SimpleInstancesToTranslatables {
    
    
    /**
     * Class whose instances convert instances of {@link User} into {@link Translatables}.
     *
     * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
     */
    static class UserConversor extends Converter {
        
        @Override
        Translatable convert( Object user ) throws UnknownTypeException {
            User u;
            try {
                u = (User)user;
            }
            catch( ClassCastException e ) {
                throw new UnknownTypeException( user
                                                + " could not be converted into a translatable.", e );
            }
            
            Map< String, Object > propertiesBag = new LinkedHashMap< String, Object >();
            propertiesBag.put( "username", u.getIdentification() );
            propertiesBag.put( "email", u.getEmail() );
            propertiesBag.put( "fullname", u.getFullName() );
            
            return new Translatable( "user", null, null, null, propertiesBag,
                                     u.toStringWithoutPassword() );
        }
    }
    
    
    /**
     * Abstract Class to convert whose subclasses instances convert instances of {@link Airship}'s
     * subclasses into {@link Translatables}.
     *
     * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
     */
    static abstract class AirshipConverter extends Converter {
        
        /**
         * Create the {@code propertiesBag} for all Airships
         * 
         * @param airship
         * @return the {@code propertiesBag}
         */
        Map< String, Object > createAirshipPropertiesBag( Airship airship ) {
            
            Map< String, Object > propertiesBag = new LinkedHashMap< String, Object >();
            propertiesBag.put( CLIStringsDictionary.FLIGHTID, airship.getIdentification() );
            propertiesBag.put( CLIStringsDictionary.LATITUDE, airship.getCoordinates()
                                                                     .getLatitude().getValue() );
            propertiesBag.put( CLIStringsDictionary.LONGITUDE, airship.getCoordinates()
                                                                      .getLongitude().getValue() );
            propertiesBag.put( CLIStringsDictionary.ALTITUDE, airship.getCoordinates()
                                                                     .getAltitude().getValue() );
            propertiesBag.put( CLIStringsDictionary.AIRCORRIDOR_MINALTITUDE,
                               airship.getAirCorridor().getMinAltitude() );
            propertiesBag.put( CLIStringsDictionary.AIRCORRIDOR_MAXALTITUDE,
                               airship.getAirCorridor().getMaxAltitude() );
            
            return propertiesBag;
        }
    }
    
    
    /**
     * Class whose instances convert instances of {@link CivilAirship} into {@link Translatables}.
     *
     * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
     */
    static class CivilAirshipConverter extends AirshipConverter {
        
        @Override
        Translatable convert( Object civilAirship ) throws UnknownTypeException {
            CivilAirship ca;
            try {
                ca = (CivilAirship)civilAirship;
            }
            catch( ClassCastException e ) {
                throw new UnknownTypeException( civilAirship
                                                + " could not be converted into a translatable.", e );
            }
            
            Map< String, Object > propertiesBag = createAirshipPropertiesBag( ca );
            propertiesBag.put( CLIStringsDictionary.NUMBEROFPASSENGERS, ca.getPassengers() );
            
            return new Translatable( "civilAirship", null, null, null, propertiesBag, ca.toString() );
            
        }
    }
    
    
    /**
     * Class whose instances convert instances of {@link MilitaryAirships} into
     * {@link Translatables}.
     *
     * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
     */
    static class MilitaryAirshipConverter extends AirshipConverter {
        
        @Override
        Translatable convert( Object militaryAirship ) throws UnknownTypeException {
            
            MilitaryAirship ma;
            try {
                ma = (MilitaryAirship)militaryAirship;
            }
            catch( ClassCastException e ) {
                throw new UnknownTypeException( militaryAirship
                                                + " could not be converted into a translatable.", e );
            }
            
            Map< String, Object > propertiesBag = createAirshipPropertiesBag( ma );
            propertiesBag.put( CLIStringsDictionary.HASARMOUR, ma.hasWeapons() );
            
            return new Translatable( "militaryAirship", null, null, null, propertiesBag,
                                     ma.toString() );
            
        }
    }
    
    
}