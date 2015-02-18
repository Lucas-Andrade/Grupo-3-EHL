package utils;


import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import exceptions.MissingRequiredParameterException;


public abstract class ClientNonGETRequest extends ClientRequest {
    
    public enum NonGetMethods {
        POST( "POST" ), DELETE( "DELETE" ), PATCH( "PATCH" );
        
        private String name;
        
        private NonGetMethods( String name ) {
        
            this.name = name;
        }
        
        public String toString() {
        
            return name;
        }
    }
    
    public ClientNonGETRequest( NonGetMethods method, String path )
        throws MissingRequiredParameterException, MalformedURLException, IOException {
    
        super( method.toString(), path );
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
    
        connection = (HttpURLConnection)new URL( url.toString() ).openConnection();
        super.setConnection();
        writeBodyMessage();
        
    }
    
    
    private void writeBodyMessage() throws IOException {
    
        if( parameters.length() != 0 ) {
            parameters.deleteCharAt( 0 );
            BufferedWriter writer;
            connection.setDoOutput( true );
            writer = new BufferedWriter( new OutputStreamWriter( connection.getOutputStream() ) );
            
            writer.write( parameters.toString() );
            writer.close();
        }
    }
    
}
