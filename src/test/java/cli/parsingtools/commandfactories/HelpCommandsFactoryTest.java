package test.java.cli.parsingtools.commandfactories;


import static org.junit.Assert.assertTrue;
import java.util.HashMap;
import main.java.cli.parsingtools.commandfactories.CommandFactory;
import main.java.cli.parsingtools.commandfactories.HelpCommandsFactory;
import main.java.domain.commands.HelpCommand;
import main.java.utils.OptionsList;
import main.java.utils.exceptions.InvalidArgumentException;
import org.junit.Test;


public class HelpCommandsFactoryTest {
    
    @Test
    public void shouldSuccessfullyCreatTheCorrectCommand() throws Exception {

        CommandFactory< OptionsList > a = new HelpCommandsFactory( new HashMap< String, String >() );
        
        assertTrue( a.newCommand( new HashMap< String, String >() ) instanceof HelpCommand );
    }
    
    @Test( expected = InvalidArgumentException.class )
    public void shouldBeThrownInvalidArgumentException() throws InvalidArgumentException {
        new HelpCommandsFactory( null );
    }
}
