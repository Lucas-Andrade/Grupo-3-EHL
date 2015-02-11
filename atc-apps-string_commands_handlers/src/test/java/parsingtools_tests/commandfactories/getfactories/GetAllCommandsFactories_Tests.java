package parsingtools_tests.commandfactories.getfactories;


import static org.junit.Assert.assertEquals;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import org.junit.Before;
import org.junit.Test;
import parsingtools.commandfactories.CommandFactory;
import parsingtools.commandfactories.getfactories.getallfactories.GetAllAirshipsInADatabaseCommandsFactory;
import parsingtools.commandfactories.getfactories.getallfactories.GetAllElementsInADatabaseCommandsFactory;
import parsingtools.commandfactories.getfactories.getallfactories.GetAllUsersInADatabaseCommandsFactory;
import utils.Optional;
import utils.exceptions.parsingexceptions.commandparserexceptions.InvalidRegisterException;
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
 * {@link GetAllElementsInADatabaseCommandsFactory}
 * {@link GetAllUsersInADatabaseCommandsFactory}
 * {@link GetAllAirshipsInADatabaseCommandsFactory}
 * 
 * </pre>
 *
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
public class GetAllCommandsFactories_Tests {
    
    InMemoryAirshipsDatabase airshipsDatabase;
    InMemoryUsersDatabase usersDatabase;
    User user;
    Airship airship;
    CommandFactory< Optional< Iterable< Airship >>> getAllAirship;
    CommandFactory< Optional< Iterable< User >>> getAllUsers;
    Map< String, String > parametersMap;    
    String owner;
    
    
    // Before Class
    
    @Before
    public void createTheCommandParserAndRegisterTheCommands()
        throws InvalidRegisterException, InvalidArgumentException {
       
        usersDatabase = new InMemoryUsersDatabase( "first user Database" );
        user = new User("pantunes","pantunespassword","pantunes@gmail.com");
        usersDatabase.add(user,user);
        
        airshipsDatabase = new InMemoryAirshipsDatabase( "first airship database" );
        airship = new CivilAirship( 38, 171, 15000, 20000, 10000, 100 );  
        airshipsDatabase.add(airship,user);
        parametersMap = new HashMap<>();
        getAllAirship = new GetAllAirshipsInADatabaseCommandsFactory( airshipsDatabase );
        getAllUsers = new GetAllUsersInADatabaseCommandsFactory( usersDatabase );
                    
    }
    
    // Test Normal Dinamic And Prerequisites
    @Test
    public void shouldGetCorrectCommandsDescriptionForGetAllAirshipsInADatabaseCommandsFactory(){
        
        assertEquals("Gets the list of all airships.",getAllAirship.getCommandsDescription());
        
    }
    
    @Test
    public void shouldGetCorrectCommandsDescriptionForGetAllUsersInADatabaseCommandsFactory(){
        

        assertEquals("Gets the list of all users.",getAllUsers.getCommandsDescription());
        
    }
    @Test
    public void shouldGetAllAirshipsFromDatabase()
        throws Exception {

        Collection<Airship> expectedAirshipContainer = new ArrayList<>(); 
        expectedAirshipContainer.add(airship);        
        assertEquals(expectedAirshipContainer.toString(),getAllAirship.newCommand( parametersMap ).call().get().toString());
    }
    
    @Test
    public void shouldGetAllUsersFromDatabase()
        throws Exception {
        User Master = new User("MASTER","master","master@master");
        Collection<User> expectedAirshipContainer = new ArrayList<>(); 
        expectedAirshipContainer.add(user);    
        expectedAirshipContainer.add(Master);
        assertEquals(expectedAirshipContainer.toString(),getAllUsers.newCommand( parametersMap ).call().get().toString());
    }
    // Test Exceptions
    
    @Test( expected = InvalidArgumentException.class )
    public
            void
            shouldThrowInvalidArgumentExceptionWhenTryingToCreateAGetAllAirshipsInADatabaseCommandsFactoryGivenANullDatabase()
                throws InvalidArgumentException {
        
        new GetAllAirshipsInADatabaseCommandsFactory( null );
    }
    
    @Test( expected = InvalidArgumentException.class )
    public
            void
            shouldThrowInvalidArgumentExceptionWhenTryingToCreateAGetAllUsersInADatabaseCommandsFactoryGivenANullDatabase()
                throws InvalidArgumentException {
        
        new GetAllUsersInADatabaseCommandsFactory( null );
    }
}
