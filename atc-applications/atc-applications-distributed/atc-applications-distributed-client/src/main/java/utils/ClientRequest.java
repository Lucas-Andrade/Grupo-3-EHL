package utils;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import utils.StringCommandsDictionary;



/**
 * Abstract class to make a {@code connection} from the {@code Client App} and a {@code Server}.
 * Subclasses should implement the {@link ClientRequest#createParameters() createParameters()} with
 * the necessary {@code parameters} and it instances should be used to make a single request.
 * 
 *
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
public abstract class ClientRequest {
    
    
    private final String HTTP_LOCALHOST = "http://localhost:8081/";
    private final String method;
    private final StringBuilder url;
    private final StringBuilder parameters;
    
    private HttpURLConnection connection;
    
    /**
     * Create a {@code ClientRequest} with the given {@code method} and {@code path}. This
     * {@code ClientRequest} will {@link ClientRequest#createConnection() create a connection} to
     * the {@code HTTP_LOCALHOST}. Before calling this method it should be added all necessary
     * {@code parameters} within the {@link ClientRequest#createParameters() createParameters()}
     * method.
     * 
     * The {@code request response} can be get using the {@link ClientRequest#getResponse()
     * getResponse()}.
     * 
     * @param method
     *            - The HTTP method.
     * @param path
     *            - The URL path.
     */
    public ClientRequest( String method, String path ) {
    
        this.method = method;
        url = new StringBuilder( HTTP_LOCALHOST ).append( path );
        parameters = new StringBuilder( "?" );
        createParameters();
    }
    
    // Abstract Class
    /**
     * Abstract method that should be used to create a "list of parameters", e.g., creating the
     * {@code QueryString} for a GET {@code method}. If its not a GET {@code method} this
     * {@code parameters} will be added to the {@code HTTP message body}.
     * 
     * To create a new parameters use the {@link ClientRequest#newParameter(String, String)} or
     * {@link ClientRequest#addAuthenticateParameters} methods.
     */
    public abstract void createParameters();
    
    
    
    // Public Class
    /**
     * Create a connection to the {@code Sever}, adding first the {@code parameters} to the
     * {@code QueryString} or {@code HTTP message body}. Returns the status code from an HTTP
     * response message, e.g., if the status lines is {@code HTTP/1.0 200 OK} is return 200.
     * 
     * Returns -1 if no code can be discerned from the response (i.e., the response is not valid
     * HTTP).
     * 
     * 
     * @return the HTTP Status-Code, or -1
     * 
     * 
     * @throws MalformedURLException
     *             If no protocol is specified, or an unknown protocol is found, or {@code url} is
     *             null.
     * @throws IOException
     *             If an I/O error occurs or an error occurred connecting to the server.
     */
    public boolean createConnection() throws MalformedURLException, IOException {
    
        writeParameters();
        connection = (HttpURLConnection)new URL( url.toString() ).openConnection();
        connection.setRequestProperty( "Accept", "application/json" );
        connection.setRequestMethod( method );
        
        return connection.getResponseCode() == 200;
    }
    
    /**
     * Get the response from the {@code Server}.
     * 
     * @return A String-response
     * 
     * @throws IOException
     *             If an I/O error occurs; or if the protocol does not support input.
     */
    public String getResponse() throws IOException {
    
        BufferedReader reader =
                new BufferedReader( new InputStreamReader( connection.getInputStream() ) );
        
        String inputLine;
        StringBuilder response = new StringBuilder();
        
        while( (inputLine = reader.readLine()) != null ) {
            response.append( inputLine );
        }
        
        reader.close();
        
        return response.toString();
    }
    
    
    
    // Protected methods
    /**
     * Add the authenticate parameters to request {@code parameters} list, i.e., the
     * {@code loginName} and {@code loginPassword}.
     * 
     * @param loginName
     *            - The loginName value.
     * @param loginPassword
     *            - The loginPassword value.
     */
    protected void addAuthenticateParameters( String loginName, String loginPassword ) {
    
        
        parameters.append( StringCommandsDictionary.LOGINNAME ).append( "=" ).append( loginName )
                  .append( "&" ).append( StringCommandsDictionary.LOGINPASSWORD ).append( "=" )
                  .append( loginPassword ).append( "&" );
    }
    
    /**
     * Add a new parameter to the request {@code parameters} list.
     * 
     * @param parameterName
     *            - The parameter name
     * @param parameterValue
     *            - The parameter value
     */
    protected void newParameter( String parameterName, String parameterValue ) {
    
        parameters.append( parameterName ).append( "=" ).append( parameterValue ).append( "&" );
    }
    
    
    /**
     * Write the {@code parameters} to the {@code QueryString} if {@code method} equals to GET,
     * otherwise write them to the {@code HTTP message body}.
     * 
     * @throws IOException
     *             If an I/O error occurs while creating the output stream.
     */
    private void writeParameters() throws IOException {
    
        if( method.equals( "GET" ) )
            url.append( parameters );
        else {
            connection.setDoOutput( true );
            BufferedWriter reader =
                    new BufferedWriter( new OutputStreamWriter( connection.getOutputStream() ) );
            
            reader.write( parameters.toString() );
        }
    }
}
