package main.java.cli.parsingtools.commandfactories.userauthenticatingfactories;


import static org.junit.Assert.assertTrue;
import java.util.HashMap;
import main.java.cli.parsingtools.commandfactories.CommandFactory;
import main.java.cli.parsingtools.commandfactories.HelpCommandsFactory;
import main.java.domain.commands.CompletionStatus;
import main.java.domain.commands.DeleteAirshipCommand;
import main.java.domain.model.airships.InMemoryAirshipsDatabase;
import main.java.domain.model.users.InMemoryUsersDatabase;
import main.java.utils.exceptions.InvalidArgumentException;
import org.junit.Test;


public class TODO_DeleteAirshipCommandsFactoryTest {
    
    @Test
    public void shouldSuccessfullyCreatTheCorrectCommand() throws Exception {
    
        CommandFactory< CompletionStatus > a =
                new DeleteAirshipCommandsFactory( new InMemoryUsersDatabase( "" ),
                                                  new InMemoryAirshipsDatabase( "" ) );
        
        
        assertTrue( a.newCommand( new HashMap< String, String >() ) instanceof DeleteAirshipCommand );
    }
    
    @Test( expected = InvalidArgumentException.class )
    public void shouldBeThrownInvalidArgumentException() throws InvalidArgumentException {
    
        new HelpCommandsFactory( null );
        
    }
}
