package parsingtools_tests.commandfactories.getfactories;


import static org.junit.Assert.assertEquals;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import org.junit.Before;
import org.junit.Test;
import parsingtools.commandfactories.CommandFactory;
import parsingtools.commandfactories.getfactories.GetAllTransgressingAirshipsCommandsFactory;
import utils.Optional;
import utils.exceptions.parsingexceptions.commandparserexceptions.InvalidRegisterException;
import databases.InMemoryAirshipsDatabase;
import databases.InMemoryUsersDatabase;
import elements.Airship;
import elements.User;
import elements.airships.CivilAirship;
import elements.airships.MilitaryAirship;
import exceptions.InvalidArgumentException;


/**
 * This Test class tests the following classes:
 * 
 * <pre>
 * 
 * {@link GetAllTransgressingAirshipsFactory}
 * 
 * </pre>
 *
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
public class GetAllTransgressingAirshipsFactory_Tests {
    
    InMemoryAirshipsDatabase airshipsDatabase;
    InMemoryUsersDatabase userDatabase;
    User user;
    Airship airship;
    Airship airship2;
    CommandFactory< Optional< Iterable< Airship >> > getAllTransgressingAirship;
    Map< String, String > parametersMap;    
    String owner;
    
    
    // Before Class
    
    @Before
    public void createTheCommandParserAndRegisterTheCommands()
        throws InvalidRegisterException, InvalidArgumentException {        
        
        userDatabase = new InMemoryUsersDatabase( "first user database" );        
        user = new User("pantunes","pantunespassword","pantunes@gmail.com");
        userDatabase.add( user, user );
        
        airshipsDatabase = new InMemoryAirshipsDatabase( "first airship database" );
        airship = new CivilAirship( 38, 171, 15000, 12000, 10000, 100 );  
        airship2 = new MilitaryAirship( 38, 171, 15000, 12000, 10000, true );  
        airshipsDatabase.add(airship,user);
        airshipsDatabase.add( airship2, user );
        getAllTransgressingAirship = new GetAllTransgressingAirshipsCommandsFactory( airshipsDatabase );
        parametersMap = new HashMap<>();        
        
        parametersMap.put( owner, user.getIdentification() );
               
    }
    
    // Test Normal Dinamic And Prerequisites
    
    @Test
    public void shouldGetCorrectCommandsDescription()
        throws  Exception {
        
      assertEquals("Gets all airships that are transgressing their air corridors.",
                   getAllTransgressingAirship.getCommandsDescription());        
    }
    
    @Test
    public void shouldGetAllTransgressorsAirships() throws  Exception{
        
        Collection<Airship> expectedAirshipContainer = new ArrayList<>();
        expectedAirshipContainer.add(airship2);
        expectedAirshipContainer.add(airship);
                
        assertEquals(expectedAirshipContainer.toString(),
                     getAllTransgressingAirship.newCommand( parametersMap ).call().get().toString());
    }  
    
    // Test Exceptions
    
    @Test( expected = InvalidArgumentException.class )
    public
            void
            shouldThrowInvalidArgumentExceptionWhenTryingToCreateAGetAllTransgressorAirshipsCommandsFactoryGivenANullDatabase()
                throws InvalidArgumentException {
        
        new GetAllTransgressingAirshipsCommandsFactory( null );
    }
}
