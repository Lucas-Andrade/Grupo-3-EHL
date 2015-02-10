import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import parsingtools.CommandParser;
import utils.Executor;
import utils.exceptions.parsingexceptions.InvalidCommandSyntaxException;
import utils.exceptions.parsingexceptions.parserexceptions.DuplicateParametersException;
import utils.exceptions.parsingexceptions.parserexceptions.InvalidCommandParametersSyntaxException;
import exceptions.DatabaseException;
import exceptions.InternalErrorException;
import exceptions.InvalidArgumentException;
import exceptions.NoSuchElementInDatabaseException;
import exceptions.WrongLoginPasswordException;


/**
 * Class that represents and HTTP servlet suitable to for the {@link AirTrafficControlServer}.
 * 
 * Extends {@link HttpServlet}.
 *
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
@SuppressWarnings( "serial" )
public class AirTrafficControlServelet extends HttpServlet {
    
    public AirTrafficControlServelet() {
    
    }
    
    @Override
    public void doGet( HttpServletRequest req, HttpServletResponse resp ) throws IOException {
    
        String[] args = getCommandArgumentsFromGetRequest( req );
        
        ServletOutputStream outStream = resp.getOutputStream();
        
        createExecutorAndSendResponse( args, resp, outStream );
    }
    
    @Override
    public void doPost( HttpServletRequest req, HttpServletResponse resp ) throws IOException {
    
        String[] args = getCommandArgumentsFromPostRequest( req, resp );
        
        ServletOutputStream outStream = resp.getOutputStream();
        
        createExecutorAndSendResponse( args, resp, outStream );
    }
    
    /**
     * returns a string with the command line request needed for the command parser. This string is
     * created based on {@link HttpServletRequest} data.
     * 
     * @param req
     *            the {@link HttpServletRequest} corresponding to the current HTTP request
     * @return the string to send to {@link CommandParser}
     */
    private String[] getCommandArgumentsFromGetRequest( HttpServletRequest req ) {
    
        String[] args =
                { req.getMethod(), req.getRequestURI(),
                 req.getQueryString() + "&accept=" + req.getHeader( "Accept" ) };
        
        return args;
    }
    
    private String[] getCommandArgumentsFromPostRequest( HttpServletRequest req,
                                                         HttpServletResponse resp )
        throws IOException {
    
        
        String parameters =
                new BufferedReader( new InputStreamReader( req.getInputStream() ) ).readLine();
        
        String[] args =
                { req.getMethod(), req.getRequestURI(),
                 parameters + "&accept=" + req.getHeader( "Accept" ) };
        
        return args;
    }
    
    private void createExecutorAndSendResponse( String[] args, HttpServletResponse resp,
                                                ServletOutputStream outputStream )
        throws IOException {
    
        Executor executor = null;
        
        try {
            executor = new ServerExecutor( AirTrafficControlServer.CMD_PARSER, args );
            
            String result = executor.getOutput();
            
            outputStream.println( result );
            
        }
        catch( InvalidCommandParametersSyntaxException | DuplicateParametersException
               | InvalidCommandSyntaxException | InvalidArgumentException e ) {
            resp.sendError( HttpServletResponse.SC_BAD_REQUEST, e.getMessage() );
        }
        catch( NoSuchElementInDatabaseException e ) {
            resp.sendError( HttpServletResponse.SC_NOT_FOUND, e.getMessage() );
        }
        catch( DatabaseException | WrongLoginPasswordException e ) {
            resp.sendError( HttpServletResponse.SC_UNAUTHORIZED, e.getMessage() );
        }
        catch( IOException e ) {
            throw (IOException)e;
        }
        catch( InternalErrorException e ) {
            resp.sendError( HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage() );
        }
        catch( Exception e ) {
            resp.sendError( HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage() );
        }
    }
}
