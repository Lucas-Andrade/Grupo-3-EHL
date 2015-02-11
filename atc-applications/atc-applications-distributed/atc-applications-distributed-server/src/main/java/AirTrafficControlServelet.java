import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import utils.Executor;
import utils.StringCommandsExecutor;
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
    
    // Private Constructor
    
    /**
     * Unused private constructor
     */
    private AirTrafficControlServelet() {
    
    }
    
    // Public Methods
    
    /**
     * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest,
     *      javax.servlet.http.HttpServletResponse)
     */
    @Override
    public void doGet( HttpServletRequest req, HttpServletResponse resp ) throws IOException {
    
        String[] args = getCommandArgumentsFromGetRequest( req );
        
        ServletOutputStream outStream = resp.getOutputStream();
        
        createExecutorAndSendResponse( args, resp, outStream );
    }
    
    /**
     * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest,
     *      javax.servlet.http.HttpServletResponse)
     */
    @Override
    public void doPost( HttpServletRequest req, HttpServletResponse resp ) throws IOException {
    
        String[] args = getCommandArgumentsFromPostRequest( req, resp );
        
        ServletOutputStream outStream = resp.getOutputStream();
        
        createExecutorAndSendResponse( args, resp, outStream );
    }
    
    // Private Auxiliar Methods
    
    /**
     * Returns a string-command received by an HTTP server needed for the {@link ServerExecutor} to
     * call the <b>GET</b> command requested by the client and return its result. This string is
     * created based on {@link HttpServletRequest} data.
     * 
     * @param req
     *            - The {@link HttpServletRequest} corresponding to the current HTTP request
     * @return The string to send to the {@link ServerExecutor}.
     */
    private String[] getCommandArgumentsFromGetRequest( HttpServletRequest req ) {
    
        String[] args =
                { req.getMethod(), req.getRequestURI(),
                 req.getQueryString() + "&accept=" + req.getHeader( "Accept" ) };
        
        return args;
    }
    
    /**
     * Returns a string-command received by an HTTP server needed for the {@link ServerExecutor} to
     * call the <b>POST</b> command requested by the client and return its result. This string is
     * created based on {@link HttpServletRequest} data.
     * 
     * @param req
     *            - The {@link HttpServletRequest} corresponding to the current HTTP request
     * @return The string to send to the {@link ServerExecutor}.
     */
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
    
    /**
     * Auxiliar method that will create the {@link ServerExecutor} that will create the requested
     * command, execute it and obtain its result as a String via the method
     * {@link StringCommandsExecutor#getOutput() getOutput()}. This result will be sent to the
     * client via the {@link HttpServletResponse#getOutputStream() outputStream}.
     * 
     * This method will also catch all the exceptions that can be throw by the creation of the
     * {@code ServerExecutor} and the call of the {@code getOutput()} method, sending the correct
     * error to the client.
     * 
     * @param args
     *            - The String array containing the method, the path and the parameters for the
     *            requested command.
     * @param resp
     *            - The HttpServletResponse.
     * @param outputStream
     *            - The HttpServletResponse's outputStream.
     * 
     * @throws IOException
     *             - If the println() method made to the {@code outputStream} throws an IOException.
     */
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
