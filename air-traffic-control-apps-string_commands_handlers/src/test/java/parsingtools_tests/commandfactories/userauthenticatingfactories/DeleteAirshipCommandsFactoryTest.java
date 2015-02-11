package parsingtools_tests.commandfactories.userauthenticatingfactories;


import java.util.HashMap;
import java.util.Map;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import parsingtools.commandfactories.CommandFactory;
import parsingtools.commandfactories.userauthenticatingfactories.DeleteAirshipCommandsFactory;
import utils.StringCommandsDictionary;
import utils.CompletionStatus;
import databases.InMemoryAirshipsDatabase;
import databases.InMemoryUsersDatabase;
import elements.Airship;
import elements.User;
import elements.airships.CivilAirship;
import exceptions.InvalidArgumentException;
import exceptions.InvalidParameterValueException;
import exceptions.MissingRequiredParameterException;
import exceptions.NoSuchElementInDatabaseException;
import exceptions.WrongLoginPasswordException;


/**
 * Test case that targets the class {@link DeleteAirshipCommandsFactory}.
 *
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
public class DeleteAirshipCommandsFactoryTest {
    
    
    InMemoryAirshipsDatabase airshipsDatabase;
    Airship air1;
    InMemoryUsersDatabase userDatabase;
    User user;
    CommandFactory< CompletionStatus > deleteFactory;
    
    Map< String, String > parametersMap;
    String flightId_ParameterName;
    String loginName_ParameterName;
    String loginPassword_ParameterName;
    
    
    @Before
    public void createUserAndAirshipAndTheirDatabases() throws InvalidArgumentException {
        
        userDatabase = new InMemoryUsersDatabase( "userDatabase" );
        user = new User( "pantunes", "pantunespassword", "pantunes@gmail.com" );
        userDatabase.add( user, user );
        
        airshipsDatabase = new InMemoryAirshipsDatabase( "airshipsDatabase" );
        air1 = new CivilAirship( 30, 230, 10000, 20000, 0, 199 );
        airshipsDatabase.add( air1, user );
        
        deleteFactory = new DeleteAirshipCommandsFactory( userDatabase, airshipsDatabase );
        
        parametersMap = new HashMap<>();
        flightId_ParameterName = StringCommandsDictionary.FLIGHTID;
        loginName_ParameterName = StringCommandsDictionary.LOGINNAME;
        loginPassword_ParameterName = StringCommandsDictionary.LOGINPASSWORD;
        
    }
    
    
    
    // Test Normal Dinamic And Prerequisites
    
    @Test
    public void shouldGetCorrectCommandsDescription() {
        Assert.assertEquals( "Deletes an airship.", deleteFactory.getCommandsDescription() );
    }
    
    @Test
    public void shouldDeleteAnAirhipsMemberOfInMemoryAirshipsDatabase() throws Exception {
        
        // Arrange
        parametersMap.put( flightId_ParameterName, air1.getIdentification() );
        parametersMap.put( loginName_ParameterName, "pantunes" );
        parametersMap.put( loginPassword_ParameterName, "pantunespassword" );
        
        // Act
        CompletionStatus status = deleteFactory.newCommand( parametersMap ).call();
        
        // Assert
        Assert.assertTrue( status.operationCompletedSuccessfully() );
        Assert.assertEquals( "Airship successfully removed", status.getMessage() );
    }
    
    @Test
    public void shouldFailToDeleteAnAirhipNotContainedInTheDatabase() throws Exception {
        
        // Arrange
        parametersMap.put( flightId_ParameterName, "inexistentID" );
        parametersMap.put( loginName_ParameterName, "pantunes" );
        parametersMap.put( loginPassword_ParameterName, "pantunespassword" );
        
        // Act
        CompletionStatus status = deleteFactory.newCommand( parametersMap ).call();
        
        // Assert
        Assert.assertFalse( status.operationCompletedSuccessfully() );
        Assert.assertEquals( "Airship doesn't exist in the database", status.getMessage() );
        
    }
    
    // Exceptional situations
    
    @Test
    public void shouldThrowExceptionIfFlightIdParameterIsMissing()
        throws InvalidArgumentException, InvalidParameterValueException,
        WrongLoginPasswordException, NoSuchElementInDatabaseException {
        
        // Arrange
        parametersMap.put( loginName_ParameterName, "pantunes" );
        parametersMap.put( loginPassword_ParameterName, "pantunespassword" );
        
        // Act & Assert
        try {
            deleteFactory.newCommand( parametersMap );
            Assert.assertTrue( false );
        }
        catch( MissingRequiredParameterException e ) {
            Assert.assertEquals( new MissingRequiredParameterException( flightId_ParameterName ).getMessage(),
                                 e.getMessage() );
        }
    }
    
    @Test
    public void shouldThrowExceptionIfLoginNameParameterIsMissing()
        throws InvalidArgumentException, InvalidParameterValueException,
        WrongLoginPasswordException, NoSuchElementInDatabaseException {
        
        // Arrange
        parametersMap.put( flightId_ParameterName, air1.getIdentification() );
        parametersMap.put( loginPassword_ParameterName, "pantunespassword" );
        
        // Act & Assert
        try {
            deleteFactory.newCommand( parametersMap );
            Assert.assertTrue( false );
        }
        catch( MissingRequiredParameterException e ) {
            Assert.assertEquals( new MissingRequiredParameterException( loginName_ParameterName ).getMessage(),
                                 e.getMessage() );
        }
    }
    
    @Test
    public void shouldThrowExceptionIfLoginPasswordParameterIsMissing()
        throws InvalidArgumentException, InvalidParameterValueException,
        WrongLoginPasswordException, NoSuchElementInDatabaseException {
        
        // Arrange
        parametersMap.put( flightId_ParameterName, air1.getIdentification() );
        parametersMap.put( loginName_ParameterName, "pantunes" );
        
        // Act & Assert
        try {
            deleteFactory.newCommand( parametersMap );
            Assert.assertTrue( false );
        }
        catch( MissingRequiredParameterException e ) {
            Assert.assertEquals( new MissingRequiredParameterException( loginPassword_ParameterName ).getMessage(),
                                 e.getMessage() );
        }
        
    }
    
    @Test
    public void shouldThrowExceptionIfLoginPasswordIsIncorrect()
        throws NoSuchElementInDatabaseException, InvalidArgumentException,
        MissingRequiredParameterException, InvalidParameterValueException {
        
        // Arrange
        parametersMap.put( flightId_ParameterName, air1.getIdentification() );
        parametersMap.put( loginName_ParameterName, "pantunes" );
        parametersMap.put( loginPassword_ParameterName, "wrongPassword" );
        
        // Act & Assert
        try {
            deleteFactory.newCommand( parametersMap );
            Assert.assertTrue( false );
        }
        catch( WrongLoginPasswordException e ) {
            Assert.assertEquals( new WrongLoginPasswordException( "pantunes", "wrongPassword" ).getMessage(),
                                 e.getMessage() );
        }
        
    }
}