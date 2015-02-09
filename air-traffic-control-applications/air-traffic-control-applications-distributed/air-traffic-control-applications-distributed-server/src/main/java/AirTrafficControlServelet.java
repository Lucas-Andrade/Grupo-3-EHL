import java.io.IOException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.Gson;
import exceptions.InvalidArgumentException;
import parsingtools.CommandParser;
import utils.Executor;
import utils.exceptions.parsingexceptions.InvalidCommandSyntaxException;
import utils.exceptions.parsingexceptions.parserexceptions.DuplicateParametersException;
import utils.exceptions.parsingexceptions.parserexceptions.InvalidCommandParametersSyntaxException;



@SuppressWarnings( "serial" )
public class AirTrafficControlServelet extends HttpServlet {
    
    public AirTrafficControlServelet() {
    
    }
    
    @Override
    public void doGet( HttpServletRequest req, HttpServletResponse resp ) throws IOException {
    
        String[] args = getCommandArgumentsFromRequest( req );
        
        Executor executor = null;
        
        try {
            
            executor = new ServerExecutor( null, args );
        }
        catch( InvalidCommandParametersSyntaxException | DuplicateParametersException
               | InvalidCommandSyntaxException | InvalidArgumentException e ) {
            
            new Gson().toJson( e );
        }
        
        try {
            executor.getOutput();
        }
        catch( Exception e ) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    /**
     * returns a string with the command line request needed for the command parser. This string is
     * created based on {@link HttpServletRequest} data.
     * 
     * @param req
     *            the {@link HttpServletRequest} corresponding to the current HTTP request
     * @return the string to send to {@link CommandParser}
     */
    private String[] getCommandArgumentsFromRequest( HttpServletRequest req ) {
    
        String[] args =
                { req.getMethod(), req.getRequestURI(),
                 req.getQueryString() + "&accept=" + req.getHeader( "Accept" ) };
        
        return args;
    }
}
