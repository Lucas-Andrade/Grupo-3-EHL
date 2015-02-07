package postcommands;


import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import commands.postcommands.PostUserCommand;

import databases.InMemoryUsersDatabase;
import elements.User;
import exceptions.InvalidArgumentException;


/**
 * This Test class tests the following classes:
 * 
 * <pre>
 * 
 * {@link PostUserCommand}
 * 
 * </pre>
 *
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
public class PostUserCommand_Tests {
    
    private InMemoryUsersDatabase usersDatabase;
    private User user1;
    private PostUserCommand postUser;
    
    // Before
    
    @Before
    public void createUserAndUserDatabaseWhereToPostTheNewUsers() throws InvalidArgumentException {
    
        // Arrange
        usersDatabase = new InMemoryUsersDatabase( "Users Database" );
        
        user1 = new User( "Daniel", "pass", "@daniel" );
    }
    
    // Test Normal Dinamic And Prerequisites
    
    @Test
    public void shouldPostAUserWithTheCorrectParametersIncludingFullNameInTheGivenDatabase()
        throws Exception {
    
        // Act
        postUser =
                new PostUserCommand( "Pedro", "pass2", "@pedro", "Pedro Antunes", usersDatabase,
                                     user1 );
        String testedInformation = postUser.call().getMessage();
        
        // Assert
        assertEquals( "New user successfully added: "
                              + usersDatabase.getElementByIdentification( "Pedro" ).toString(),
                      testedInformation );
    }
    
    @Test
    public void shouldPostAUserWithTheCorrectParametersGivenANullFullNameInTheGivenDatabase()
        throws Exception {
    
        // Act
        postUser = new PostUserCommand( "Pedro", "pass2", "@pedro", null, usersDatabase, user1 );
        String testedInformation = postUser.call().getMessage();
        
        // Assert
        assertEquals( "New user successfully added: "
                              + usersDatabase.getElementByIdentification( "Pedro" ).toString(),
                      testedInformation );
    }
    
    @Test
    public void shouldNotPostAUserWithTheSameEmailHasAnotherExistingUserInTheDatabase()
        throws Exception {
    
        // Act
        postUser = new PostUserCommand( "Pedro", "pass2", "@pedro", null, usersDatabase, user1 );
        postUser.call();
        
        postUser = new PostUserCommand( "Daniel", "pass", "@pedro", null, usersDatabase, user1 );
        String testedInformation = postUser.call().getMessage();
        
        // Assert
        assertEquals( "User not added. Either the username «Daniel» or\nthe email «@pedro» already exist in Users Database",
                      testedInformation );
    }
    
    // Test Exceptions
    
    @Test( expected = InvalidArgumentException.class )
    public void shouldThrowInvalidArgumentExceptionWhenTryingToCreateTheCommandGivenANullDatabase()
        throws InvalidArgumentException {
    
        postUser = new PostUserCommand( "Pedro", "pass2", "@pedro", "Pedro Antunes", null, user1 );
    }
}
