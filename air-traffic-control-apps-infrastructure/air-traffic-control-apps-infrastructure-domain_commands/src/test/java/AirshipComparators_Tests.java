


import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import utils.AirshipComparators;
import databases.InMemoryAirshipsDatabase;
import elements.Airship;
import elements.User;
import elements.airships.CivilAirship;
import elements.airships.GeographicPosition;
import elements.airships.MilitaryAirship;


/**
 * This test class tests the class {@link ComparatorByDistance}.
 * 
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
public class AirshipComparators_Tests {
    
    private InMemoryAirshipsDatabase database;
    
    @Test
    public void shouldGetTheAirshipsClosserToASpecificGeographicPosition() throws Exception {
    
        // Arrange
        List< Airship > expectedAirshipList = new ArrayList< Airship >();
        
        User user = new User( "username", "password", "email@" );
        database = new InMemoryAirshipsDatabase( "airships database" );
        
        Airship air1 = new CivilAirship( 1, 0, 10000, 20000, 0, 100 );
        Airship air2 = new MilitaryAirship( 2, 0, 15000, 20000, 0, true );
        Airship air3 = new CivilAirship( 3, 0, 12000, 20000, 0, 50 );
        Airship air4 = new MilitaryAirship( 7, 0, 15000, 20000, 0, false );
        Airship air5 = new CivilAirship( 5, 0, 12000, 20000, 0, 50 );
        Airship air6 = new CivilAirship( 6, 0, 12000, 20000, 0, 50 );
        Airship air7 = new CivilAirship( 4, 0, 12000, 20000, 0, 50 );
        Airship air8 = new MilitaryAirship( 8, 0, 15000, 20000, 0, false );
        
        // Act
        database.add( air1, user );
        database.add( air2, user );
        database.add( air3, user );
        database.add( air4, user );
        database.add( air5, user );
        database.add( air6, user );
        database.add( air7, user );
        database.add( air8, user );
        
        expectedAirshipList.add( air1 );
        expectedAirshipList.add( air2 );
        expectedAirshipList.add( air3 );
        expectedAirshipList.add( air7 );
        expectedAirshipList.add( air5 );
        expectedAirshipList.add( air6 );
        expectedAirshipList.add( air4 );
        expectedAirshipList.add( air8 );
        
        // Assert
        assertEquals( expectedAirshipList,
                      database.sortBy( new AirshipComparators.ComparatorByDistance(
                                                                                    new GeographicPosition(
                                                                                                            0,
                                                                                                            0,
                                                                                                            0 ) ) )
                              .get() );
    }
    
}
