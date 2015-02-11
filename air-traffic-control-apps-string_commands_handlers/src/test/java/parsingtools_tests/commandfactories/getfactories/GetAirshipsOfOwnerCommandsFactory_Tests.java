package parsingtools_tests.commandfactories.getfactories;



import static org.junit.Assert.assertEquals;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import org.junit.Before;
import org.junit.Test;
import parsingtools.commandfactories.CommandFactory;
import parsingtools.commandfactories.getfactories.GetAirshipsOfOwnerCommandsFactory;
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
 * {@link GetAirshipsOfOwnerCommandsFactory}
 * 
 * </pre>
 *
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
public class GetAirshipsOfOwnerCommandsFactory_Tests {
    
    InMemoryAirshipsDatabase airshipsDatabase;
    InMemoryUsersDatabase userDatabase;
    User user;
    Airship airship;
    CommandFactory< Optional< Iterable< Airship >> > getAirshipOfOwner;
    Map< String, String > parametersMap;    
    String owner;
    
    
    // Before Class
    
    @Before
    public void createUserAndAirshipAndTheirDatabases() throws InvalidArgumentException{
        
        userDatabase = new InMemoryUsersDatabase( "first user database" );        
        user = new User("pantunes","pantunespassword","pantunes@gmail.com");
        userDatabase.add( user, user );
        
        airshipsDatabase = new InMemoryAirshipsDatabase( "first airship database" );
        airship = new CivilAirship( 38, 171, 15000, 20000, 10000, 100 );  
        airshipsDatabase.add(airship,user);
        getAirshipOfOwner = new GetAirshipsOfOwnerCommandsFactory( airshipsDatabase );
        parametersMap = new HashMap<>();        
        owner = StringCommandsDictionary.OWNER;
        
        parametersMap.put( owner, user.getIdentification() );
               
    }
           
    // Test Normal Dinamic And Prerequisites
    
    @Test
    public void shouldGetCorrectCommandsDescription() throws Exception {
        
        assertEquals("Gets all airships added by a certain user.",getAirshipOfOwner.getCommandsDescription());

    }
    
    @Test
    public void shouldSuccessfullyGetAirshipsOfOwner() throws Exception {
           
            Collection<Airship> expectedAirshipContainer = new ArrayList<>(); 
            expectedAirshipContainer.add(airship);
            assertEquals(expectedAirshipContainer.toString(),getAirshipOfOwner.newCommand( parametersMap ).call().get().toString());
        

    }
    
    // Test Exceptions
    
    @Test( expected = InvalidArgumentException.class )
    public void
            shouldThrowInvalidArgumentExceptionWhenTryingToCreateAGetAirshipsOfOwnerCommandsFactoryGivenANullDatabase()
                throws InvalidArgumentException {
        
        new GetAirshipsOfOwnerCommandsFactory( null );
    }
}
