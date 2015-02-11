package parsingtools_tests.commandfactories.getfactories;


import static org.junit.Assert.assertEquals;
import java.util.HashMap;
import java.util.Map;
import org.junit.Before;
import org.junit.Test;
import parsingtools.commandfactories.CommandFactory;
import parsingtools.commandfactories.getfactories.getbyidfactories.GetAirshipByFlightIdCommandsFactory;
import parsingtools.commandfactories.getfactories.getbyidfactories.GetElementByIdentificationCommandsFactory;
import parsingtools.commandfactories.getfactories.getbyidfactories.GetUserByUsernameCommandsFactory;
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
 * {@link GetElementByIdentificationCommandsFactory}
 * {@link GetUserByUsernameCommandsFactory}
 * {@link GetAirshipByFlightIdCommandsFactory}
 * 
 * </pre>
 *
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
public class GetByIdCommandsFactories_Tests {
    
    InMemoryAirshipsDatabase airshipsDatabase;
    InMemoryUsersDatabase usersDatabase;
    User user;
    Airship airship;
    CommandFactory< Optional< Airship >> getAirship;
    CommandFactory< Optional< User >> getUsers;
    Map< String, String > parametersMap;
    String airshipFlightId;
    String userIdentification;
    
    @Before
    public void createTheCommandParserAndRegisterTheCommands() throws InvalidArgumentException {
    
        usersDatabase = new InMemoryUsersDatabase( "first user database" );
        user = new User( "pantunes", "pantunespassword", "pantunes@gmail.com" );
        usersDatabase.add( user, user );
        airshipsDatabase = new InMemoryAirshipsDatabase( "first airship database" );
        airship = new CivilAirship( 38, 171, 15000, 12000, 10000, 100 );
        airshipsDatabase.add( airship, user );
        airshipFlightId = StringCommandsDictionary.FLIGHTID;
        userIdentification = StringCommandsDictionary.USERNAME;
        getAirship = new GetAirshipByFlightIdCommandsFactory( airshipsDatabase );
        getUsers = new GetUserByUsernameCommandsFactory( usersDatabase );
        parametersMap = new HashMap<>();
        parametersMap.put( airshipFlightId, airship.getIdentification() );
        parametersMap.put( userIdentification, user.getIdentification() );
    }
    
    // Test Normal Dinamic And Prerequisites
    
    @Test
    public void shouldGetCorrectCommandsDescriptionForGetAirshipByFlightIdCommandsFactory() {
    
        assertEquals( "Gets an airship with a certain flightId.",
                      getAirship.getCommandsDescription() );
        
    }
    
    @Test
    public void shouldGetCorrectCommandsDescriptionForGetUserByFlightIdCommandsFactory() {
    
        assertEquals( "Gets a user with a certain username.", getUsers.getCommandsDescription() );
        
    }
    
    @Test
    public void shouldGetSpecificAirshipFromAirshipDatabase() throws Exception {
    
        assertEquals( airship.toString(), getAirship.newCommand( parametersMap ).call().get()
                                                    .toString() );
        
    }
    
    
    @Test
    public void shouldGetSpecificUserFromUserDatabase() throws Exception {
    
        assertEquals( user.toString(), getUsers.newCommand( parametersMap ).call().get().toString() );
        
    }
    
    
    // Test Exceptions
    
    @Test( expected = InvalidArgumentException.class )
    public
            void
            shouldThrowInvalidArgumentExceptionWhenTryingToCreateAGetAirshipByFlightIdCommandsFactoryGivenANullDatabase()
                throws InvalidArgumentException {
    
        new GetAirshipByFlightIdCommandsFactory( null );
    }
    
    @Test( expected = InvalidArgumentException.class )
    public
            void
            shouldThrowInvalidArgumentExceptionWhenTryingToCreateAGetUserByUsernameCommandsFactoryGivenANullDatabase()
                throws InvalidArgumentException {
    
        new GetUserByUsernameCommandsFactory( null );
    }
    
}
