package test.java.domain.commands;


import static org.junit.Assert.assertEquals;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;
import main.java.domain.commands.HelpCommand;
import main.java.utils.OptionsList;
import main.java.utils.exceptions.InvalidArgumentException;
import org.junit.Test;


/**
 * This Test class tests the following classes:
 * 
 * <pre>
 * 
 * {@link HelpCommand}
 * 
 * </pre>
 *
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
public class HelpCommand_Tests {
    
    @Test
    public void shouldGetAllCommandsDescription() throws Exception {
    
        // Arrange
        Map< String, String > commandsDescription = new HashMap< String, String >();
        commandsDescription.put( "GET /airships", "Gets the list of all airships." );
        commandsDescription.put( "PATCH /users/{username}", "Change An User Password" );
        
        Callable< OptionsList > helpCommand = new HelpCommand( commandsDescription );
        
        // Act
        Map< String, String > descriptions = helpCommand.call().getOptions();
        
        // Assert
        assertEquals( descriptions.get( "GET /airships" ), "Gets the list of all airships." );
        assertEquals( descriptions.get( "PATCH /users/{username}" ), "Change An User Password" );
    }
    
    
    @Test( expected = InvalidArgumentException.class )
    public void shouldThrowInvalidArgumentExceptionGivingANullCommandParserInHelpCommand()
        throws Exception {
    
        new HelpCommand( null );
    }
}
