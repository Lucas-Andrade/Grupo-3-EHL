package parsingtools_tests.commandfactories;


import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import parsingtools.commandfactories.CommandFactory;
import parsingtools.commandfactories.HelpCommandsFactory;
import parsingtools.commandfactories.userauthenticatingfactories.DeleteAirshipCommandsFactory;
import utils.CLIStringsDictionary;
import utils.CompletionStatus;

import commands.DeleteAirshipCommand;

import databases.Database;
import databases.InMemoryAirshipsDatabase;
import databases.InMemoryUsersDatabase;
import elements.User;
import exceptions.InvalidArgumentException;


public class DeleteAirshipCommandsFactoryTest {
    
    @Test
    public void shouldSuccessfullyCreatTheCorrectCommand() throws Exception {
    
        Database< User > db = new InMemoryUsersDatabase( "" );
        User user = new User( "name", "pass", "@" );
        db.add( user, user );
        CommandFactory< CompletionStatus > a =
                new DeleteAirshipCommandsFactory( db, new InMemoryAirshipsDatabase( "" ) );
        
        Map< String, String > map = new HashMap< String, String >();
        map.put( CLIStringsDictionary.FLIGHTID, "id" );
        map.put( CLIStringsDictionary.LOGINNAME, "name" );
        map.put( CLIStringsDictionary.LOGINPASSWORD, "pass" );
        assertTrue( a.newCommand( map ) instanceof DeleteAirshipCommand );
    }
    
    @Test( expected = InvalidArgumentException.class )
    public void shouldBeThrownInvalidArgumentException() throws InvalidArgumentException {
    
        new HelpCommandsFactory( null );
        
    }
}
