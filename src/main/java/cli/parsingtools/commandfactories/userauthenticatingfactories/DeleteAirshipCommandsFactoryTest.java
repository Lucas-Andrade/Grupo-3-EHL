package main.java.cli.parsingtools.commandfactories.userauthenticatingfactories;

import static org.junit.Assert.*;
import java.util.HashMap;
import main.java.cli.parsingtools.commandfactories.CommandFactory;
import main.java.cli.parsingtools.commandfactories.HelpCommandsFactory;
import main.java.domain.commands.DeleteAirshipCommand;
import main.java.domain.commands.HelpCommand;
import main.java.domain.model.Database;
import main.java.domain.model.airships.InMemoryAirshipsDatabase;
import main.java.domain.model.users.InMemoryUsersDatabase;
import main.java.domain.model.users.User;
import main.java.utils.OptionsList;
import main.java.utils.exceptions.InvalidArgumentException;
import org.junit.BeforeClass;
import org.junit.Test;


public class DeleteAirshipCommandsFactoryTest {
    
    @Test
    public void shouldSuccessfullyCreatTheCorrectCommand() throws Exception {

        CommandFactory< String > a = new DeleteAirshipCommandsFactory( new InMemoryUsersDatabase( "" ), new InMemoryAirshipsDatabase( "" ) );

        
        assertTrue( a.newCommand( new HashMap< String, String >() ) instanceof DeleteAirshipCommand );
    }
    
    @Test( expected = InvalidArgumentException.class )
    public void shouldBeThrownInvalidArgumentException() throws InvalidArgumentException {
        new HelpCommandsFactory( null );
    
}
