import java.io.OutputStream;
import exceptions.InvalidArgumentException;
import parsingtools.CommandParser;
import utils.StringCommands_Executor;
import utils.exceptions.parsingexceptions.InvalidCommandSyntaxException;
import utils.exceptions.parsingexceptions.parserexceptions.DuplicateParametersException;
import utils.exceptions.parsingexceptions.parserexceptions.InvalidCommandParametersSyntaxException;


public class ServerExecutor extends StringCommands_Executor {
    
    public ServerExecutor( CommandParser cmdParser, String[] args )
        throws InvalidCommandParametersSyntaxException, DuplicateParametersException,
        InvalidCommandSyntaxException, InvalidArgumentException {
    
        super( cmdParser, args );
        // TODO Auto-generated method stub
    }
    
    @Override
    public String getOutput() {
    
        // TODO Auto-generated method stub
        return null;
    }
    
    @Override
    public OutputStream getStream() {
    
        // TODO Auto-generated method stub
        return null;
    }
}
