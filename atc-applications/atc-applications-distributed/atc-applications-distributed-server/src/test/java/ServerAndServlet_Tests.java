import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import javax.servlet.http.HttpServletResponse;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import app.AirTrafficControlServelet;
import app.AirTrafficControlServer;


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
    
////    private static Process process;
//    private HttpURLConnection connection;
//    private BufferedWriter br;
//    
//    // Before Class - Not Implemented!
//    
//    @BeforeClass
//    public static void startRequestProcess() throws IOException {
//    
////        process =
////                Runtime.getRuntime()
////                       .exec( "cd .. .. .. target classes AirTrafficControlServer.class" );
//    }
//    
//    // Test Gets
//    
//    @Test
//    public void testGetUsers() throws MalformedURLException, IOException {
//    
//        createNewConnection( "http://localhost:8081/users" );
//        
//        assertOkResponse();
//    }
//    
//    @Test
//    public void testGetUserByUsername() throws MalformedURLException, IOException {
//    
//        createNewConnection( "http://localhost:8081/users/MASTER" );
//        
//        assertOkResponse();
//    }
//    
//    @Test
//    public void testAuthenticateUser() throws MalformedURLException, IOException {
//    
//        createNewConnection( "http://localhost:8081/users/authenticate?loginName=MASTER&loginPassword=master" );
//        
//        assertOkResponse();
//    }
//    
//    @Test
//    public void testGetAirships() throws MalformedURLException, IOException {
//    
//        createNewConnection( "http://localhost:8081/airships" );
//        
//        assertOkResponse();
//    }
//    
//    @Test
//    public void testGetAirshipByID() throws MalformedURLException, IOException {
//    
//        createNewConnection( "http://localhost:8081/airships/id1" );
//        
//        assertOkResponse();
//    }
//    
//    @Test
//    public void testGetAirshipOfUser() throws MalformedURLException, IOException {
//    
//        createNewConnection( "http://localhost:8081/airships/owner/MASTER" );
//        
//        assertOkResponse();
//    }
//    
//    @Test
//    public void testGetAirshipWithLessThan() throws MalformedURLException, IOException {
//    
//        createNewConnection( "http://localhost:8081/airships/nbPassengers/10/bellow" );
//        
//        assertOkResponse();
//    }
//    
//    @Test
//    public void testGetTransgressingAirships() throws MalformedURLException, IOException {
//    
//        createNewConnection( "http://localhost:8081/airships/reports" );
//        
//        assertOkResponse();
//    }
//    
//    @Test
//    public void testCheckIfAirshipIsTransgressing() throws MalformedURLException, IOException {
//    
//        // An Airship Needs To Be Added First!!!
//        createNewConnection( "http://localhost:8081/airships/Military" );
//        
//        br = setPostMethod();
//        br.write( "loginName=MASTER&loginPassword=master&latitude=0&longitude=0&altitude=0&minAltitude=0&maxAltitude=10&hasArmour=true" );
//        br.close();
//        
//        // Airship Already Added!
//        
//        createNewConnection( "http://localhost:8081/airships/reports/id1" );
//        
//        assertOkResponse();
//    }
//    
//    @Test
//    public void testGetAirshipsCloserTo() throws MalformedURLException, IOException {
//    
//        createNewConnection( "http://localhost:8081/airships/find?nbAirships=2&latitude=0&longitude=0" );
//        
//        assertOkResponse();
//    }
//    
//    // Test Posts
//    
//    @Test
//    public void testPostUsers() throws MalformedURLException, IOException {
//    
//        createNewConnection( "http://localhost:8081/users" );
//        
//        br = setPostMethod();
//        br.write( "loginName=MASTER&loginPassword=master&username=Manuel&password=manuel&email=d@d&fullname=danielacgomes" );
//        br.close();
//        
//        assertOkResponse();
//    }
//    
//    @Test
//    public void testPostAirship() throws MalformedURLException, IOException {
//    
//        createNewConnection( "http://localhost:8081/airships/Military" );
//        
//        br = setPostMethod();
//        br.write( "loginName=MASTER&loginPassword=master&latitude=0&longitude=0&altitude=0&minAltitude=0&maxAltitude=10&hasArmour=true" );
//        br.close();
//        
//        assertOkResponse();
//    }
//    
//    // Test Errors
//    
//    @Test
//    public void anErrorShouldBeSentEveryTimeAnExceptionOccursInTheServer()
//        throws MalformedURLException, IOException {
//    
//        // Test InvalidCommandParametersSyntaxException
//        createNewConnection( "http://localhost:8081/airships/find?nbAirships=2&latitude==0&longitude=0" );
//        Assert.assertEquals( HttpServletResponse.SC_BAD_REQUEST, connection.getResponseCode() );
//        Assert.assertEquals( "Invalid syntax in parameters-list!", connection.getResponseMessage() );
//        
//        // Test InvalidParameterException
//        createNewConnection( "http://localhost:8081/airships/find?nbAirships=2&latitude=300&longitude=0" );
//        Assert.assertEquals( HttpServletResponse.SC_BAD_REQUEST, connection.getResponseCode() );
//        Assert.assertEquals( "Invalid value 300 is greater than maximum value allowed (90) for latitude.",
//                             connection.getResponseMessage() );
//        
//        // Test MissingRequiredParameterException
//        createNewConnection( "http://localhost:8081/airships/find?airships=2&latitude=0&longitude=0" );
//        Assert.assertEquals( HttpServletResponse.SC_BAD_REQUEST, connection.getResponseCode() );
//        Assert.assertEquals( "Required parameter with name nbAirships missing.",
//                             connection.getResponseMessage() );
//        
//        // Test DuplicateParametersException
//        createNewConnection( "http://localhost:8081/airships/find?nbAirships=2&latitude=0&latitude=3&longitude=0" );
//        Assert.assertEquals( HttpServletResponse.SC_BAD_REQUEST, connection.getResponseCode() );
//        Assert.assertEquals( "Parameter with name latitude was received more than once.",
//                             connection.getResponseMessage() );
//        
//        // Test NoSuchElementInDatabaseException
//        createNewConnection( "http://localhost:8081/users/authenticate?loginName=Daniel&loginPassword=master" );
//        Assert.assertEquals( HttpServletResponse.SC_NOT_FOUND, connection.getResponseCode() );
//        Assert.assertEquals( "Daniel not found in Users Database.", connection.getResponseMessage() );
//        
//        // Test WrongLoginPasswordException
//        createNewConnection( "http://localhost:8081/users/authenticate?loginName=MASTER&loginPassword=erro" );
//        Assert.assertEquals( HttpServletResponse.SC_UNAUTHORIZED, connection.getResponseCode() );
//        Assert.assertEquals( "Wrong password: MASTER's password is not erro.",
//                             connection.getResponseMessage() );
//        
//        // Test UnsupportedAcceptValueException
//        createNewConnection( "http://localhost:8081/users/authenticate?loginName=MASTER&loginPassword=master" );
//        connection.setRequestProperty( "Accept", "text/erro" );
//        Assert.assertEquals( HttpServletResponse.SC_NOT_ACCEPTABLE, connection.getResponseCode() );
//        Assert.assertEquals( "Unsupported Accept Values!", connection.getResponseMessage() );
//    }
//    
//    // After Class - Not Implemented!
//    
//    @AfterClass
//    public static void destroyProcess() {
//    
////        process.destroy();
//    }
//    
//    // Private Auxiliar Methods
//    
//    private void createNewConnection( String url ) throws MalformedURLException, IOException {
//    
//        connection = (HttpURLConnection)new URL( url ).openConnection();
//    }
//    
//    private void assertOkResponse() throws IOException {
//    
//        Assert.assertEquals( HttpServletResponse.SC_OK, connection.getResponseCode() );
//    }
//    
//    private BufferedWriter setPostMethod() throws ProtocolException, IOException {
//    
//        connection.setRequestMethod( "POST" );
//        connection.setDoOutput( true );
//        connection.setRequestProperty( "Accept", "text/html" );
//        
//        return new BufferedWriter( new OutputStreamWriter( connection.getOutputStream() ) );
//    }

}
