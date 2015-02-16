import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
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
    
    // Before Class
    
    @BeforeClass
    public static void starRequestProcess() throws IOException {
    
        process =
                Runtime.getRuntime()
                       .exec( "cd .. .. .. target classes AirTrafficControlServer.class" );
    }
    
    // Test Gets
    
    @Test
    public void testGetUsers() throws MalformedURLException, IOException {
    
        String url = "http://localhost:8081/users";
        
        HttpURLConnection connection = (HttpURLConnection)new URL( url ).openConnection();
        
        Assert.assertEquals( HttpServletResponse.SC_OK, connection.getResponseCode() );
    }
    
    @Test
    public void testGetUserByUsername() throws MalformedURLException, IOException {
    
        String url = "http://localhost:8081/users/MASTER";
        
        HttpURLConnection connection = (HttpURLConnection)new URL( url ).openConnection();
        
        Assert.assertEquals( HttpServletResponse.SC_OK, connection.getResponseCode() );
    }
    
    @Test
    public void testAuthenticateUser() throws MalformedURLException, IOException {
    
        String url = "http://localhost:8081/users/authenticate";
        
        HttpURLConnection connection = (HttpURLConnection)new URL( url ).openConnection();
        
        Assert.assertEquals( HttpServletResponse.SC_OK, connection.getResponseCode() );
    }
    
    @Test
    public void testGetAirships() throws MalformedURLException, IOException {
    
        String url = "http://localhost:8081/airships";
        
        HttpURLConnection connection = (HttpURLConnection)new URL( url ).openConnection();
        
        Assert.assertEquals( HttpServletResponse.SC_OK, connection.getResponseCode() );
    }
    
    @Test
    public void testGetAirshipByID() throws MalformedURLException, IOException {
    
        String url = "http://localhost:8081/airships/id1";
        
        HttpURLConnection connection = (HttpURLConnection)new URL( url ).openConnection();
        
        Assert.assertEquals( HttpServletResponse.SC_OK, connection.getResponseCode() );
    }
    
    @Test
    public void testGetAirshipOfUser() throws MalformedURLException, IOException {
    
        String url = "http://localhost:8081/airships/owner/MASTER";
        
        HttpURLConnection connection = (HttpURLConnection)new URL( url ).openConnection();
        
        Assert.assertEquals( HttpServletResponse.SC_OK, connection.getResponseCode() );
    }
    
    @Test
    public void testGetAirshipWithLessThan() throws MalformedURLException, IOException {
    
        String url = "http://localhost:8081/airships/nbPassengers/10/bellow";
        
        HttpURLConnection connection = (HttpURLConnection)new URL( url ).openConnection();
        
        Assert.assertEquals( HttpServletResponse.SC_OK, connection.getResponseCode() );
    }
    
    @Test
    public void testGetTransgressingAirships() throws MalformedURLException, IOException {
    
        String url = "http://localhost:8081/airships/reports";
        
        HttpURLConnection connection = (HttpURLConnection)new URL( url ).openConnection();
        
        Assert.assertEquals( HttpServletResponse.SC_OK, connection.getResponseCode() );
    }
    
    @Test
    public void testCheckIfAirshipIsTransgressing() throws MalformedURLException, IOException {
    
        String url = "http://localhost:8081/airships/reports/id1";
        
        HttpURLConnection connection = (HttpURLConnection)new URL( url ).openConnection();
        
        Assert.assertEquals( HttpServletResponse.SC_OK, connection.getResponseCode() );
    }
    
    @Test
    public void testGetAirshipsCloserTo() throws MalformedURLException, IOException {
    
        String url = "http://localhost:8081/airships/find";
        
        HttpURLConnection connection = (HttpURLConnection)new URL( url ).openConnection();
        
        Assert.assertEquals( HttpServletResponse.SC_OK, connection.getResponseCode() );
    }
    
    // Test Posts
    
    @Test
    public void testPostUsers() throws MalformedURLException, IOException {
    
        String url = "http://localhost:8081/users";
        
        HttpURLConnection connection = (HttpURLConnection)new URL( url ).openConnection();
        
        connection.setRequestMethod( "POST" );
        connection.setDoOutput( true );
        connection.setRequestProperty( "Accept", "text/html" );
        
        BufferedWriter br =
                new BufferedWriter( new OutputStreamWriter( connection.getOutputStream() ) );
        
        br.write( "loginName=MASTER&loginPassword=master&username=Daniel&password=daniel&email=d@d&fullname=danielacgomes" );
        br.close();
        
        
        Assert.assertEquals( HttpServletResponse.SC_OK, connection.getResponseCode() );
    }
    
    // Test Errors
    
    
    
    // After Class
    
    @AfterClass
    public static void destroyProcess() {
    
        process.destroy();
    }
}
