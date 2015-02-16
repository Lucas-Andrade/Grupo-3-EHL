package utils;


import elements.Airship;
import elements.Element;
import elements.User;
import entities.Entity;
import entities.SimpleAirship;
import entities.SimpleUser;


/**
 * Class whose instances are used to convert {@link Element} to {@link Entity}
 * 
 *
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
public class EntitiesConversor {
    
    /**
     * Convert a {@link User} to {@link SimpleUser}.
     * 
     * @param user
     *            - The {@code User} to be converted.
     * @return a {@link SimpleUser}
     */
    public SimpleUser toSimpleUser( User user ) {
    
        return new SimpleUser( user.getIdentification(), user.toStringWithoutPassword() );
    }
    
    
    /**
     * Convert a {@link Airship} to {@link SimpleAirship}.
     * 
     * @param user
     *            - The {@code Airship} to be converted.
     * @return a {@link SimpleAirship}
     */
    public SimpleAirship toSimpleAirship( Airship airship ) {
    
        return new SimpleAirship( airship.getIdentification(), airship.getCoordinates()
                                                                      .getLatitude().getValue(),
                                  airship.getCoordinates().getLongitude().getValue(),
                                  airship.toString() );
        
    }
}
