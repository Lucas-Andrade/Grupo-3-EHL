package test.java.cli.parsingtools.commandfactories;


import static org.junit.Assert.assertTrue;
import java.util.HashMap;
import java.util.Map;
import main.java.cli.CLIStringsDictionary;
import main.java.cli.parsingtools.commandfactories.CommandFactory;
import main.java.cli.parsingtools.commandfactories.HelpCommandsFactory;
import main.java.cli.parsingtools.commandfactories.userauthenticatingfactories.DeleteAirshipCommandsFactory;
import main.java.domain.commands.CompletionStatus;
import main.java.domain.commands.DeleteAirshipCommand;
import main.java.domain.model.Database;
import main.java.domain.model.airships.InMemoryAirshipsDatabase;
import main.java.domain.model.users.InMemoryUsersDatabase;
import main.java.domain.model.users.User;
import main.java.utils.exceptions.InvalidArgumentException;
import org.junit.Test;


public class DeleteAirshipCommandsFactoryTest {
    
    @Test
    public void shouldSuccessfullyCreatTheCorrectCommand() throws Exception {
    
        Database< User > db = new InMemoryUsersDatabase( "");
        User user = new User( "name", "pass", "@" );
        db.add( user, user );
        CommandFactory< CompletionStatus > a =
                new DeleteAirshipCommandsFactory( db ,
                                                  new InMemoryAirshipsDatabase( "" ) );
        
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
