package swingworkers;


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
 * 
 * 
 *
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
public abstract class ClientRequest {
    
    private final String HTTP_LOCALHOST = "http://localhost:8081/";
    private HttpURLConnection connection;
    private String method;
    private StringBuilder url;
    private StringBuilder parameters;
    
    /**
     * 
     */
    public ClientRequest( String method, String path ) throws MalformedURLException, IOException {
    
        this.method = method;
        url = new StringBuilder( HTTP_LOCALHOST ).append( path ).append( "?" );
    }
    
    /**
     * 
     */
    public abstract void createParameters();
    
    /**
     * Add authenticate parameters
     * 
     * @param loginName
     * @param loginPassword
     */
    public void addAuthenticateParameters( String loginName, String loginPassword ) {
    
        parameters.append( StringCommandsDictionary.LOGINNAME ).append( loginName )
           .append( StringCommandsDictionary.LOGINPASSWORD ).append( loginPassword );
    }
    
    /**
     * Add a new parameter to the {@code Request}
     *  
     * @param key 
     * @param value
     */
    public void newParameter( String key, String value ) {
    
        parameters.append( key ).append( value );
    }
    
    /**
     * @return the strResponse
     * @throws IOException
     */
    public String getResponse() throws IOException {
    
        BufferedReader reader =
                new BufferedReader( new InputStreamReader( connection.getInputStream() ) );
        
        String response = reader.readLine();
        reader.close();
        
        return response;
    }
    
    /**
     * TODO create a connection
     * 
     * @throws MalformedURLException
     * @throws IOException
     */
    public boolean createConnection() throws MalformedURLException, IOException {
    
        writeParameters();
        
        connection = (HttpURLConnection)new URL( url.toString() ).openConnection();
        connection.setRequestMethod( method );
        
        return connection.getResponseCode() == 200;
    }

    
    /**
     * 
     * @throws IOException
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
    
//    public void addPostUserParameters( String username, String password, String email ) {
//        
//        url.append( StringCommandsDictionary.USERNAME ).append( username )
//           .append( StringCommandsDictionary.PASSWORD ).append( password )
//           .append( StringCommandsDictionary.EMAIL ).append( email );
//    }
    

    
    
}
