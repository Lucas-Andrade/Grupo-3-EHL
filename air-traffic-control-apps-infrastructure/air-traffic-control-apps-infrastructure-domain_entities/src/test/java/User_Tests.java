

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import elements.Airship;
import elements.User;
import elements.airships.MilitaryAirship;
import exceptions.InvalidArgumentException;


/**
 * Tests class that targets the class {@link User}.
 * 
 *
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
public class User_Tests {
    
    User user1, user2;
    
    // Before
    
    @Before
    public void createUsers() throws InvalidArgumentException {
        
        user1 = new User( "Pedro", "pass", "pedro@gmail.com", "Pedro Antunes" );
        user2 = new User( "Gonçalo", "pass2", "Gonçalo@gmail.com" );
    }
    
    // Test Normal Dinamic And Prerequisites
    
    @Test
    public void shouldReturnUserIdentification() {
        
        Assert.assertTrue( user1.getIdentification().equals( "Pedro" ) );
        Assert.assertTrue( user2.getIdentification().equals( "Gonçalo" ) );
    }
    
    @Test
    public void shouldReturnAStringWithAllTheInformation() {
        
        Assert.assertEquals( "username: Pedro,\r\npassword: pass,\r\nemail: pedro@gmail.com,\r\nfullName: Pedro Antunes\r\n",
                             user1.toString() );
    }
    
    @Test
    public void shouldReturnAStringWithAllTheInformationExceptFullname() {
        
        Assert.assertEquals( "username: Gonçalo,\r\npassword: pass2,\r\nemail: Gonçalo@gmail.com\r\n",
                             user2.toString() );
    }
    
    @Test
    public void shouldReturnUsername() {
        
        Assert.assertTrue( user1.getIdentification().equals( "Pedro" ) );
        Assert.assertTrue( user2.getIdentification().equals( "Gonçalo" ) );
    }
    
    @Test
    public void shouldReturnTheUserFullname() {
        
        Assert.assertEquals( "Pedro Antunes", user1.getFullName() );
    }
    
    @Test
    public void shouldReturnAnUserEmail() {
        
        Assert.assertEquals( "pedro@gmail.com", user1.getEmail() );
    }
    
    @Test
    public void shouldAnUserBeInstanceOfUserClass() {
        
        Assert.assertTrue( user1.getClass().equals( User.class ) );
        Assert.assertTrue( user2.getClass().equals( User.class ) );
    }
    
    @Test
    public void verifyIfUsersAreEquals() throws InvalidArgumentException {
        
        Assert.assertTrue( user1.equals( new User( "Pedro", "pass", "pedro@gmail.com",
                                                   "Pedro Antunes" ) ) );
        Assert.assertFalse( user1.equals( user2 ) );
    }
    
    @Test
    public void verifyIfUsersHasDiferentsUsernames() throws InvalidArgumentException {
        
        Assert.assertFalse( user1.equals( new User( "Gonçalo", "pass", "pedro@gmail.com",
                                                    "Pedro Antunes" ) ) );
    }
    
    @Test
    public void verifyIfUsersHasDiferentsEmails() throws InvalidArgumentException {
        
        Assert.assertFalse( user1.equals( new User( "Pedro", "pass", "Gonçalo@gmail.com",
                                                    "Pedro Antunes" ) ) );
    }
    
    @Test
    public void shouldReturnAStringWithoutPasswordButWithAnFullname() {
        
        Assert.assertEquals( "username: Pedro,\r\nemail: pedro@gmail.com,\r\nfullName: Pedro Antunes\r\n",
                             user1.toStringWithoutPassword() );
    }
    
    @Test
    public void shouldReturnAStringWithoutPasswordButWithoutAnFullname() {
        
        Assert.assertEquals( "username: Gonçalo,\r\nemail: Gonçalo@gmail.com\r\n",
                             user2.toStringWithoutPassword() );
    }
    
    @Test
    public void shouldAuthenticatePassword() {
        
        Assert.assertTrue( user1.authenticatePassword( "pass" ) );
    }
    
    @Test
    public void shouldNotAuthenticatePassword() {
        
        Assert.assertFalse( user1.authenticatePassword( "password" ) );
    }
    
    @Test
    public void onlyBecauseIWant100PercentCoverage() throws InvalidArgumentException {
        
        Airship airship = new MilitaryAirship( 0, 0, 0, 10, 0, false );
        Assert.assertFalse( user1.equals( null ) );
        Assert.assertTrue( user1.equals( user1 ) );
        Assert.assertFalse( user1.equals( airship ) );
    }
    
    // Test Exceptions
    
    @Test( expected = InvalidArgumentException.class )
    public void shouldThrowInvalidArgumentExceptionWhenUsernameIsNull()
        throws InvalidArgumentException {
        
        new User( null, "pass", "pantunes@gmail.com", "Pantunes da Silva Pantunes" );
    }
    
    @Test( expected = InvalidArgumentException.class )
    public void shouldThrowInvalidArgumentExceptionWhenPasswordIsNull()
        throws InvalidArgumentException {
        
        new User( "Pantunes", null, "pantunes@gmail.com", "Pantunes da Silva Pantunes" );
    }
    
    @Test( expected = InvalidArgumentException.class )
    public void shouldThrowInvalidArgumentExceptionWhenEmailIsNull()
        throws InvalidArgumentException {
        
        new User( "Pantunes", "pass", null, "Pantunes da Silva Pantunes" );
    }
    
    @Test( expected = InvalidArgumentException.class )
    public void shouldThrowInvalidArgumentExceptionWhenEmailDoesntHaveAt()
        throws InvalidArgumentException {
        
        new User( "Pantunes", "pass", "pantunesgmail.com", "Pantunes da Silva Pantunes" );
    }
    
    @Test( expected = InvalidArgumentException.class )
    public void shouldThrowInvalidArgumentExceptionWhenEmailDoesntHaveOnlyOneAt()
        throws InvalidArgumentException {
        
        new User( "Pantunes", "pass", "pant@unes@gmail.com", "Pantunes da Silva Pantunes" );
    }
    
    @Test( expected = InvalidArgumentException.class )
    public void shouldThrowInvalidArgumentExceptionWhenFullnameIsNull()
        throws InvalidArgumentException {
        
        new User( "pantunes", "pass", "pantunes@gmail.com", null );
    }
    
    @Test( expected = InvalidArgumentException.class )
    public void shouldThrowInvalidArgumentExceptionWhenUsernameIsAnEmptyString()
        throws InvalidArgumentException {
        
        new User( "", "pass", "pantunes@gmail.com", "Pantunes da Silva Pantunes" );
    }
    
    @Test( expected = InvalidArgumentException.class )
    public void shouldThrowInvalidArgumentExceptionWhenPasswordIsAnEmptyString()
        throws InvalidArgumentException {
        
        new User( "pantunes", "", "pantunes@gmail.com", "Pantunes da Silva Pantunes" );
    }
    
    @Test( expected = InvalidArgumentException.class )
    public void shouldThrowInvalidArgumentExceptionWhenEmailIsAnEmptyString()
        throws InvalidArgumentException {
        
        new User( "pantunes", "password", "", "Pantunes da Silva Pantunes" );
    }
}
