package parsingtools_tests.commandfactories.getfactories;


import static org.junit.Assert.assertEquals;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import org.junit.Before;
import org.junit.Test;
import parsingtools.commandfactories.CommandFactory;
import parsingtools.commandfactories.getfactories.GetAirshipsCloserToCommandsFactory;
import utils.StringCommandsDictionary;
import utils.Optional;
import databases.InMemoryAirshipsDatabase;
import elements.Airship;
import elements.User;
import elements.airships.CivilAirship;
import elements.airships.MilitaryAirship;
import exceptions.InvalidArgumentException;


public class GetAirshipsCloserToCommandsFactory_Test {
    
    InMemoryAirshipsDatabase airshipsDatabase;
    User user;
    Airship airship;
    Airship airship2;
    Airship airship3;    
    CommandFactory< Optional< Iterable<Airship>>> getTheNearestAirship;
    Map< String, String > parametersMap;    
    String airshipsNumberKey;
    String latitudeKey;
    String longitudeKey;
    String latitude;
    String longitude;
    String airshipNumber;
    
    @Before
    public void createTheCommandParserAndRegisterTheCommands() throws InvalidArgumentException{
        
        user = new User("pantunes","pantunespassword","pantunes@gmail.com");
        airshipsDatabase = new InMemoryAirshipsDatabase( "first airship database" );
        airship = new CivilAirship( 38, 171, 15000, 12000, 10000, 100 );  
        airship2 = new CivilAirship( 10, 120, 15000, 12000, 10000, 100 );  
        airship3 = new MilitaryAirship( -60, 40, 15000, 12000, 10000, true );  
        airshipsDatabase.add(airship,user);
        airshipsDatabase.add(airship2,user);
        airshipsDatabase.add(airship3,user);
        getTheNearestAirship = new GetAirshipsCloserToCommandsFactory( airshipsDatabase );  
        airshipsNumberKey = StringCommandsDictionary.NUMBEROFAIRSHIPSTOFIND;
        latitudeKey = StringCommandsDictionary.LATITUDE;
        longitudeKey = StringCommandsDictionary.LONGITUDE;
        airshipNumber="2";
        latitude = "20";
        longitude = "150";
        parametersMap = new HashMap<>();   
        parametersMap.put(latitudeKey , latitude );
        parametersMap.put(longitudeKey, longitude);
        parametersMap.put( airshipsNumberKey, airshipNumber );
                
    }
    
    
    @Test
    public void shouldGetCorrectCommandsDescription(){
        
        assertEquals("Get the nearest aircrafts of Geographic coordinates",getTheNearestAirship.getCommandsDescription());
        
    }
    
    @Test
    public void shouldGetTheNearestAirshipToGeographicalPosition() throws Exception{
        
        Collection<Airship> expectedResult = new ArrayList<>();
        expectedResult.add( airship );
        expectedResult.add( airship2 );
        assertEquals(expectedResult.toString(),getTheNearestAirship.newCommand( parametersMap ).call().get().toString());
        
        
    }
    
    @Test( expected = InvalidArgumentException.class )
    public void
            shouldThrowInvalidArgumentExceptionWhenTryingToGiveANullAirShipDatabaseInTheFactory()
                throws Exception {
        
        new GetAirshipsCloserToCommandsFactory( null );
    }
}
