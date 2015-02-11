package parsingtools_tests.commandfactories;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import java.util.HashMap;
import java.util.Map;
import org.junit.Test;
import parsingtools.commandfactories.CommandFactory;
import parsingtools.commandfactories.HelpCommandsFactory;
import utils.OptionsList;
import commands.HelpCommand;
import exceptions.InvalidArgumentException;


public class HelpCommandsFactoryTest {
    
    
    @Test 
    public void shouldGetCorrectCommandsDescription() throws InvalidArgumentException{
               
        Map< String, String > commandsDescription = new HashMap<>();
        
        commandsDescription.put("description","description" );
        assertEquals( "Returns the descriptions of known commands.", 
                      new HelpCommandsFactory( commandsDescription ).getCommandsDescription());    
    } 
    
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
