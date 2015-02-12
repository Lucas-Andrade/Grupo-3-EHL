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
import utils.exceptions.parsingexceptions.UnsupportedAcceptValueException;
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
    
    // Public Methods
    
    /**
     * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest,
     *      javax.servlet.http.HttpServletResponse)
     */
    @Override
    public void doGet( HttpServletRequest req, HttpServletResponse resp ) throws IOException {
    
        Execute( req, getCommandArgumentsFromGetRequest( req ), resp );
    }
    
    /**
     * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest,
     *      javax.servlet.http.HttpServletResponse)
     */
    @Override
    public void doPost( HttpServletRequest req, HttpServletResponse resp ) throws IOException {
    
        Execute( req, getCommandArgumentsFromPostRequest( req ), resp );
    }
    
    // Private Auxiliar Methods
    
    /**
     * Returns a string-command received by an HTTP server needed for the {@link ServerExecutor} to
     * call the <b>GET</b> command requested by the client and return its result. This string is
     * created based on {@link HttpServletRequest} data.
     * 
     * @param req
     *            - The {@link HttpServletRequest} corresponding to the current HTTP request.
     * @return The string to send to the {@link ServerExecutor}.
     */
    private String[] getCommandArgumentsFromGetRequest( HttpServletRequest req ) {
    
        String[] args =
                { req.getMethod(), req.getRequestURI(),
                 (req.getQueryString() == null ? "" : req.getQueryString()) };
        
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
    private String[] getCommandArgumentsFromPostRequest( HttpServletRequest req )
        throws IOException {
    
        String parameters =
                new BufferedReader( new InputStreamReader( req.getInputStream() ) ).readLine();
        
        String[] args = { req.getMethod(), req.getRequestURI(), parameters };
        
        return args;
    }
    
    /**
     * Auxiliar method that will execute the {@link HttpServletRequest Request} sent.
     * 
     * @param req
     *            - The {@link HttpServletRequest}.
     * @param args
     *            - The String array containing the method, the path and the parameters for the
     *            requested command.
     * @param resp
     *            - The {@link HttpServletResponse}.
     * 
     * @throws IOException
     *             - If the println() method made to the {@code outputStream} throws an IOException.
     */
    private void Execute( HttpServletRequest req, String[] args, HttpServletResponse resp )
        throws IOException {
    
        ServletOutputStream outputStream = resp.getOutputStream();
        
        String args2 = args[2];
        
        String[] acceptValuesList = req.getHeader( "Accept" ).split( "," );
        
        int index = 0;
        
        updateParameters( resp, args, args2, acceptValuesList, index );
        
        createExecutorAndSendResponse( args, args2, resp, outputStream, acceptValuesList, index );
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
     * If the creation of the {@code ServerExecutor} throws an
     * {@link UnsupportedAcceptValueException} this exception will be catched and call this method
     * again with the parameters string, stored to the {@code args} array, updated with a new accept
     * value existing in the {@code acceptValuesList}.
     * 
     * @param args
     *            - The String array containing the method, the path and the parameters for the
     *            requested command.
     * @param args3
     *            - The inicial value of the String parameters withouth the accept header.
     * @param acceptValuesList
     *            - The list of all the accept values supported by the entity that made the request.
     * @param index
     *            - The index of the {@code acceptValuesList} array that has the accept value we
     *            want to add to the parameters.
     * @param resp
     *            - The HttpServletResponse.
     * @param outputStream
     *            - The HttpServletResponse's outputStream.
     * 
     * @throws IOException
     *             - If the println() method made to the {@code outputStream} throws an IOException.
     */
    private void createExecutorAndSendResponse( String[] args, String args3,
                                                HttpServletResponse resp,
                                                ServletOutputStream outputStream,
                                                String[] acceptValuesList, int index )
        throws IOException {
    
        try {
            
            Executor executor = new ServerExecutor( AirTrafficControlServer.CMD_PARSER, args );
            
            String result = executor.getOutput();
                        
            resp.setContentType( acceptValuesList[index] );
            resp.setCharacterEncoding( "HTF-8" );
            
            outputStream.println( result );
            
        }
        catch( UnsupportedAcceptValueException e ) {
            
            updateParameters( resp, args, args3, acceptValuesList, index++ );
            
            createExecutorAndSendResponse( args, args3, resp, outputStream, acceptValuesList,
                                           index++ );
            
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
    
    /**
     * Auxilar method to update the parameters String with ith value of the accept header in the
     * form: <i>accept="value"</i>.
     * 
     * @param args
     *            - The String array containing the method, path and parameters of the
     *            {@code HttpServletRequest} to give to the {@link ServerExecutor}.
     * @param args2
     *            - The inicial value of the String parameters withouth the accept header.
     * @param acceptValuesList
     *            - The list of all the accept values supported by the entity that made the request.
     * @param i
     *            - The index of the {@code acceptValuesList} array that has the accept value we
     *            want to add to the parameters.
     * @throws IOException
     */
    private void updateParameters( HttpServletResponse resp, String[] args, String args2,
                                   String[] acceptValuesList, int i ) throws IOException {
    
        if( i >= acceptValuesList.length )
            resp.sendError( HttpServletResponse.SC_NOT_ACCEPTABLE, "Unsupported Accept Values" );
        
        if(acceptValuesList[i].contains( "=" ))
            updateParameters( resp, args, args2, acceptValuesList, i++ );
        
        if( args2 == "" ) {
            args[2] = "accept=" + acceptValuesList[i];
        }
        else {
            args[2] = args2 + "&accept=" + acceptValuesList[i];
        }
    }
}
