package utils;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import exceptions.MissingRequiredParameterException;


public abstract class GetClientRequest extends ClientRequest{

    public GetClientRequest( String path ) throws MissingRequiredParameterException, MalformedURLException, IOException {
    
        super( "GET", path );
        
    }
    
    
 // Public method
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
    @Override
    protected void setConnection() throws MalformedURLException, IOException {
    
        writeQueryString();
        connection = (HttpURLConnection)new URL( url.toString() ).openConnection();
        super.setConnection();
        
    }
    
    
    /**
     * Write the {@code parameters} to the {@code QueryString} if {@code method} equals to GET,
     * otherwise write them to the {@code HTTP message body}.
     * 
     * @throws IOException
     *             If an I/O error occurs while creating the output stream.
     */
    private void writeQueryString() throws IOException {
    
        if( parameters.length() != 0 ) {
            parameters.deleteCharAt( 0 );
            
            if( method.equals( "GET" ) ) {
                url.append( "?" ).append( parameters );
            }
        }
    }
    
}
