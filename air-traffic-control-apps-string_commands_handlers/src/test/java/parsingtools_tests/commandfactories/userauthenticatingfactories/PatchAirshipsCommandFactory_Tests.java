package parsingtools_tests.commandfactories.userauthenticatingfactories;


import static org.junit.Assert.assertEquals;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import parsingtools.commandfactories.userauthenticatingfactories.PatchAirshipCommandsFactory;
import utils.StringCommandsDictionary;
import utils.exceptions.parsingexceptions.commandparserexceptions.InvalidRegisterException;
import commands.patchcommands.PatchAirshipCommand;
import databases.InMemoryAirshipsDatabase;
import databases.InMemoryUsersDatabase;
import elements.Airship;
import elements.User;
import elements.airships.CivilAirship;
import elements.airships.MilitaryAirship;
import exceptions.InternalErrorException;
import exceptions.InvalidArgumentException;
import exceptions.InvalidParameterValueException;
import exceptions.MissingRequiredParameterException;
import exceptions.NoSuchElementInDatabaseException;
import exceptions.WrongLoginPasswordException;


/**
 * This Test class tests the following classes:
 * 
 * <pre>
 * 
 * {@link PatchAirshipCommandsFactory}
 * 
 * </pre>
 *
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
public class PatchAirshipsCommandFactory_Tests {
    
    private static InMemoryUsersDatabase usersDatabase;
    private static InMemoryAirshipsDatabase airshipsDatabase;
    private Map< String, String > parameters;
    private Map< String, String > onlyRequiredParameters;
    private static User user;
    private static Airship airship1, airship2;
    
    // Before Class
    
    @BeforeClass
    public static void createUsersAndAirshipsAndTheirDatabases()
        throws InvalidRegisterException, InvalidArgumentException {
        
        usersDatabase = new InMemoryUsersDatabase( "Users Database" );
        airshipsDatabase = new InMemoryAirshipsDatabase( "Airships Database" );
        
        user = new User( "Daniel", "pass", "@d" );
        airship1 = new CivilAirship( 10, 0, 20, 100, 50, 20 );
        airship2 = new MilitaryAirship( 10, 0, 20, 100, 50, true );
        
        usersDatabase.add( user, user );
        
        airshipsDatabase.add( airship1, user );
        airshipsDatabase.add( airship2, user );
    }
    
    // Before
    
    @Before
    public void createParametersMap() {
        
        parameters = new HashMap< String, String >();
        
        parameters.put( StringCommandsDictionary.LOGINNAME, "Daniel" );
        parameters.put( StringCommandsDictionary.LOGINPASSWORD, "pass" );
        
        parameters.put( StringCommandsDictionary.LATITUDE, "0" );
        parameters.put( StringCommandsDictionary.LONGITUDE, "0" );
        parameters.put( StringCommandsDictionary.ALTITUDE, "0" );
        parameters.put( StringCommandsDictionary.AIRCORRIDOR_MINALTITUDE, "10" );
        parameters.put( StringCommandsDictionary.AIRCORRIDOR_MAXALTITUDE, "0" );
        
        onlyRequiredParameters = new HashMap< String, String >();
        
        onlyRequiredParameters.put( StringCommandsDictionary.LOGINNAME, "Daniel" );
        onlyRequiredParameters.put( StringCommandsDictionary.LOGINPASSWORD, "pass" );
    }
    
    // Test Normal Dinamic And Prerequisites
    
    
    @Test 
    public void shouldGetCorrectCommandsDescription() throws InvalidArgumentException{
               
        
        assertEquals( "Change an Airship Coordinates and/or AirCorridor",
                      new PatchAirshipCommandsFactory( usersDatabase, airshipsDatabase ).getCommandsDescription());
        
    }
    
    @Test
    public void shouldSuccessfullyCreateTheCorrectCommandGivenAllOfTheOptionalParameters()
        throws NoSuchElementInDatabaseException, MissingRequiredParameterException,
        InvalidParameterValueException, WrongLoginPasswordException, InternalErrorException,
        InvalidArgumentException {
        
        parameters.put( StringCommandsDictionary.FLIGHTID, airship1.getIdentification() );
        
        Callable< ? > patchAirshipCommand =
                (new PatchAirshipCommandsFactory( usersDatabase, airshipsDatabase )).newCommand( parameters );
        
        Assert.assertTrue( patchAirshipCommand instanceof PatchAirshipCommand );
    }
    
    @Test
    public void shouldSuccessfullyCreateTheCorrectCommandGivenNoneOfTheOptionalParameters()
        throws NoSuchElementInDatabaseException, MissingRequiredParameterException,
        InvalidParameterValueException, WrongLoginPasswordException, InternalErrorException,
        InvalidArgumentException {
        
        onlyRequiredParameters.put( StringCommandsDictionary.FLIGHTID, airship1.getIdentification() );
        
        Callable< ? > patchAirshipCommand =
                (new PatchAirshipCommandsFactory( usersDatabase, airshipsDatabase )).newCommand( onlyRequiredParameters );
        
        Assert.assertTrue( patchAirshipCommand instanceof PatchAirshipCommand );
    }
    
}
