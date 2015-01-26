package test.java.cli.parsingtools.commandfactories.userauthenticatingfactories;


import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;
import main.java.cli.CLIStringsDictionary;
import main.java.cli.parsingtools.commandfactories.userauthenticatingfactories.patchfactories.PatchAirshipCommandsFactory;
import main.java.domain.commands.patchcommands.PatchAirshipCommand;
import main.java.domain.model.airships.Airship;
import main.java.domain.model.airships.CivilAirship;
import main.java.domain.model.airships.InMemoryAirshipsDatabase;
import main.java.domain.model.airships.MilitaryAirship;
import main.java.domain.model.users.InMemoryUsersDatabase;
import main.java.domain.model.users.User;
import main.java.utils.exceptions.InternalErrorException;
import main.java.utils.exceptions.InvalidArgumentException;
import main.java.utils.exceptions.InvalidParameterValueException;
import main.java.utils.exceptions.MissingRequiredParameterException;
import main.java.utils.exceptions.WrongLoginPasswordException;
import main.java.utils.exceptions.databaseexceptions.NoSuchElementInDatabaseException;
import main.java.utils.exceptions.parsingexceptions.commandparserexceptions.InvalidRegisterException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


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
        
        parameters.put( CLIStringsDictionary.LOGINNAME, "Daniel" );
        parameters.put( CLIStringsDictionary.LOGINPASSWORD, "pass" );
        
        parameters.put( CLIStringsDictionary.LATITUDE, "0" );
        parameters.put( CLIStringsDictionary.LONGITUDE, "0" );
        parameters.put( CLIStringsDictionary.ALTITUDE, "0" );
        parameters.put( CLIStringsDictionary.AIRCORRIDOR_MINALTITUDE, "10" );
        parameters.put( CLIStringsDictionary.AIRCORRIDOR_MAXALTITUDE, "0" );
        
        onlyRequiredParameters = new HashMap< String, String >();
        
        onlyRequiredParameters.put( CLIStringsDictionary.LOGINNAME, "Daniel" );
        onlyRequiredParameters.put( CLIStringsDictionary.LOGINPASSWORD, "pass" );
    }
    
    // Test Normal Dinamic And Prerequisites
    
    @Test
    public void shouldSuccessfullyCreateTheCorrectCommandGivenAllOfTheOptionalParameters()
        throws NoSuchElementInDatabaseException, MissingRequiredParameterException,
        InvalidParameterValueException, WrongLoginPasswordException, InternalErrorException,
        InvalidArgumentException {
        
        parameters.put( CLIStringsDictionary.FLIGHTID, airship1.getIdentification() );
        
        Callable< ? > patchAirshipCommand =
                (new PatchAirshipCommandsFactory( usersDatabase, airshipsDatabase )).newInstance( parameters );
        
        Assert.assertTrue( patchAirshipCommand instanceof PatchAirshipCommand );
    }
    
    @Test
    public void shouldSuccessfullyCreateTheCorrectCommandGivenNoneOfTheOptionalParameters()
        throws NoSuchElementInDatabaseException, MissingRequiredParameterException,
        InvalidParameterValueException, WrongLoginPasswordException, InternalErrorException,
        InvalidArgumentException {
        
        onlyRequiredParameters.put( CLIStringsDictionary.FLIGHTID, airship1.getIdentification() );
        
        Callable< ? > patchAirshipCommand =
                (new PatchAirshipCommandsFactory( usersDatabase, airshipsDatabase )).newInstance( onlyRequiredParameters );
        
        Assert.assertTrue( patchAirshipCommand instanceof PatchAirshipCommand );
    }
    
    // Test Exceptions
}