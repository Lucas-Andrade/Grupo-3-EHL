package getcommands;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import commands.getcommands.GetElementFromADatabaseByIdCommand;
import databases.InMemoryAirshipsDatabase;
import databases.InMemoryUsersDatabase;
import elements.Airship;
import elements.User;
import elements.airships.CivilAirship;
import elements.airships.MilitaryAirship;
import exceptions.InvalidArgumentException;


/**
 * This Test class tests the following classes:
 * 
 * <pre>
 * 
 * {@link GetElementFromADatabaseByIdCommand}
 * 
 * </pre>
 *
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
public class GetElementFromADatabaseByIdCommand_Tests {
    
    private InMemoryAirshipsDatabase airshipsDatabase;
    private InMemoryUsersDatabase userDatabase;
    private User user1, user2;
    private Airship airship1, airship2, airship3;
    private GetElementFromADatabaseByIdCommand< ? > getElementFromADatabaseById;
    
    // Before
    
    @Before
    public void createAirshipsAndUsersAndTheirDatabases() throws InvalidArgumentException {
        
        // Arrange
        airshipsDatabase = new InMemoryAirshipsDatabase( "Airships Database" );
        userDatabase = new InMemoryUsersDatabase( "Users Database" );
        
        user1 = new User( "Daniel", "pass", "@daniel" );
        user2 = new User( "Pedro", "pass2", "@pedro", "Pedro Antunes" );
        
        airship1 = new CivilAirship( 0, 0, 0, 100, 50, 20 );
        airship2 = new CivilAirship( 0, 0, 0, 100, 50, 25 );
        airship3 = new MilitaryAirship( 0, 0, 0, 100, 50, false );
        
        userDatabase.add( user1, user1 );
        userDatabase.add( user2, user1 );
        
        airshipsDatabase.add( airship1, user1 );
        airshipsDatabase.add( airship2, user1 );
        airshipsDatabase.add( airship3, user2 );
    }
    
    // Test Normal Dinamic And Prerequisites
    
    @SuppressWarnings( { "rawtypes", "unchecked" } )
    @Test
    public void shouldSuccessfullyGetTheCorrectAirshipGivenItsIdentification() throws Exception {
        
        // Act
        getElementFromADatabaseById =
                new GetElementFromADatabaseByIdCommand( airshipsDatabase,
                                                        airship1.getIdentification() );
        Airship testedAirship = (Airship)getElementFromADatabaseById.call().get();
        
        // Assert
        Assert.assertEquals( testedAirship.toString(), airship1.toString() );
    }
    
    @SuppressWarnings( { "rawtypes", "unchecked" } )
    @Test
    public void shouldSuccessfullyGetTheCorrectUserGivenItsIdentification() throws Exception {
        
        // Act
        getElementFromADatabaseById =
                new GetElementFromADatabaseByIdCommand( userDatabase, "Daniel" );
        User testeduser = (User)getElementFromADatabaseById.call().get();
        
        // Assert
        Assert.assertEquals( testeduser.toString(), user1.toString() );
    }
    
    // Test Exceptions
    
    @SuppressWarnings( { "rawtypes", "unchecked" } )
    @Test( expected = InvalidArgumentException.class )
    public void shouldThrowInvalidArgumentExceptionWhenTryingToCreateTheCommandGivenANullDatabase()
        throws InvalidArgumentException {
        
        getElementFromADatabaseById = new GetElementFromADatabaseByIdCommand( null, "Daniel" );
    }
    
    @SuppressWarnings( { "rawtypes", "unchecked" } )
    @Test( expected = InvalidArgumentException.class )
    public
            void
            shouldThrowInvalidArgumentExceptionWhenTryingToCreateTheCommandGivenANullIdentification()
                throws InvalidArgumentException {
        
        getElementFromADatabaseById =
                new GetElementFromADatabaseByIdCommand( airshipsDatabase, null );
    }
    
    @SuppressWarnings( { "rawtypes", "unchecked" } )
    @Test( expected = InvalidArgumentException.class )
    public
            void
            shouldThrowInvalidArgumentExceptionWhenTryingToCreateTheCommandGivenAnEmptyStringAsIdentification()
                throws InvalidArgumentException {
        
        getElementFromADatabaseById = new GetElementFromADatabaseByIdCommand( airshipsDatabase, "" );
    }
}
