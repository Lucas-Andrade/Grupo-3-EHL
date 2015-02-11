package parsingtools_tests.commandfactories.getfactories;


import static org.junit.Assert.assertEquals;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import org.junit.Before;
import org.junit.Test;
import parsingtools.commandfactories.CommandFactory;
import parsingtools.commandfactories.getfactories.GetAirshipsWithLessPassengersThanCommandsFactory;
import utils.StringCommandsDictionary;
import utils.Optional;
import databases.InMemoryAirshipsDatabase;
import databases.InMemoryUsersDatabase;
import elements.Airship;
import elements.User;
import elements.airships.CivilAirship;
import exceptions.InvalidArgumentException;


/**
 * This Test class tests the following classes:
 * 
 * <pre>
 * 
 * {@link GetAirshipsWithLessPassengersThanCommandsFactory}
 * 
 * </pre>
 *
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
public class GetAirshipsWithLessPassengersThanCommandsFactory_Tests {
    
   
    InMemoryAirshipsDatabase airshipsDatabase;
    InMemoryUsersDatabase userDatabase;
    User user;
    Airship airship;
    CommandFactory< Optional< Iterable< Airship >> > getAirshipsLessPassengers;
    Map<String,String> parametersMap;
    String numberPassenger;
    String numberPassengerRequered;
   
    @Before
    public void createTheCommandParserAndRegisterTheCommands() throws InvalidArgumentException{
        
        userDatabase = new InMemoryUsersDatabase( "first user database" );        
        user = new User("pantunes","pantunespassword","pantunes@gmail.com");
        userDatabase.add( user, user );
        
        airshipsDatabase = new InMemoryAirshipsDatabase( "first airship database" );
        airship = new CivilAirship( 38, 171, 15000, 20000, 10000, 100 );  
        airshipsDatabase.add(airship,user);
        
        getAirshipsLessPassengers = new GetAirshipsWithLessPassengersThanCommandsFactory( airshipsDatabase );
        parametersMap = new HashMap<>();     
        numberPassengerRequered="200";
        numberPassenger = StringCommandsDictionary.NUMBEROFPASSENGERS_UPPERLIMIT;
        parametersMap.put( numberPassenger, numberPassengerRequered );
    }
    
    // Test Normal Dinamic And Prerequisites
    
    @Test
    public void shouldGetCorrectCommandsDescription(){
     
        

        assertEquals( "Gets all airships that are transgressing their air corridors."
                      ,getAirshipsLessPassengers.getCommandsDescription());        

        
    }
    
    @Test
    public void shouldGetAirshipsWitLessPassengerThan() throws Exception{
        
        Collection<Airship> expectedAirshipContainer = new ArrayList<>(); 
        expectedAirshipContainer.add(airship);
        assertEquals(expectedAirshipContainer.toString(),getAirshipsLessPassengers.newCommand( parametersMap ).call().get().toString());        
        
    }    
    
    
    // Test Exceptions
    
    @Test( expected = InvalidArgumentException.class )
    public void
            shouldThrowInvalidArgumentExceptionWhenTryingToCreateAGetAirshipsWithLessPassengersThanCommandsFactoryGivenANullDatabase()
                throws InvalidArgumentException {
        
        new GetAirshipsWithLessPassengersThanCommandsFactory( null );
    }
}
