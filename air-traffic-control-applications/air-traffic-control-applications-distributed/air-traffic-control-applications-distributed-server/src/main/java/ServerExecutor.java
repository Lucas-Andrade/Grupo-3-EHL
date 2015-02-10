import parsingtools.CommandParser;
import utils.StringCommandsExecutor;
import utils.exceptions.parsingexceptions.InvalidCommandSyntaxException;
import utils.exceptions.parsingexceptions.parserexceptions.DuplicateParametersException;
import utils.exceptions.parsingexceptions.parserexceptions.InvalidCommandParametersSyntaxException;
import exceptions.InvalidArgumentException;


public class ServerExecutor extends StringCommandsExecutor {
    
    public ServerExecutor( CommandParser cmdParser, String[] args )
        throws InvalidCommandParametersSyntaxException, DuplicateParametersException,
        InvalidCommandSyntaxException, InvalidArgumentException {
    
        super( cmdParser, args );
    }
}
