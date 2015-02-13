import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import javax.servlet.http.HttpServletResponse;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;


/**
 * This Test class tests the following classes:
 * 
 * <pre>
 * {@link AirTrafficControlServer};
 * {@link AirTrafficControlServelet};
 * </pre>
 * 
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
public class ServerAndServlet_Tests {
    
    private static Process process;
    
    @BeforeClass
    public static void starRequestProcess() throws IOException {
    
        process = Runtime.getRuntime().exec( "cd .. .. .. target classes AirTrafficControlServer.class" );
    }
    
    @Test
    public void testGetUsers() throws MalformedURLException, IOException {
    
        String url = "http://localhost:8081/users";
        
        HttpURLConnection connection = (HttpURLConnection) new URL( url ).openConnection();
        
        Assert.assertEquals( HttpServletResponse.SC_OK, connection.getResponseCode() );
        
//        BufferedReader in =
//                new BufferedReader( new InputStreamReader( connection.getInputStream() ) );
//        String inputLine;
//        String html = new String();
//        
//        // TODO
//        while( (inputLine = in.readLine()) != null ) {
//            html += inputLine;
//        }
//        in.close();
    }
    
    @AfterClass
    public static void destroyProcess() {
    
        process.destroy();
    }
}
