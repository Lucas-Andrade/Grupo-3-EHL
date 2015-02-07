package patchcommands;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import commands.patchcommands.PatchUserPasswordCommand;

import databases.InMemoryUsersDatabase;
import elements.User;
import exceptions.InvalidArgumentException;


/**
 * Test class that targets the class {@link PatchUserPasswordCommand}.
 * 
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
public class PatchUserPasswordCommand_Tests {
    
    private InMemoryUsersDatabase usersDatabase;
    private User user1, user2;
    private PatchUserPasswordCommand patchUserPassword, patchUserPassword2;
    
    // Before
    
    @Before
    public void createUsersTheirDatabase() throws InvalidArgumentException {
    
        // Arrange
        user1 = new User( "Daniel", "pass", "@daniel" );
        user2 = new User( "Pedro", "pass2", "@pedro" );
        
        usersDatabase = new InMemoryUsersDatabase( "Users Database" );
        
        usersDatabase.add( user1, user1 );
        usersDatabase.add( user2, user1 );
    }
    
    // Test Normal Dinamic And Prerequisites
    
    @Test
    public void shouldPatchAUserWithTheCorrectPasswordInTheGivenDatabase() throws Exception {
    
        // Act
        patchUserPassword = new PatchUserPasswordCommand( usersDatabase, "Daniel", "pass", "dany" );
        
        // Assert
        Assert.assertEquals( "User password successfully changed.", patchUserPassword.call()
                                                                                     .getMessage() );
    }
    
    @Test
    public void shouldNotPatchAnUserThatDoesNotExistInTheDatabaseOrTheMasterUser() throws Exception {
    
        // Act
        patchUserPassword = new PatchUserPasswordCommand( usersDatabase, "Gomes", "pass", "dany" );
        patchUserPassword2 =
                new PatchUserPasswordCommand( usersDatabase, "MASTER", "master", "dany" );
        
        // Assert
        Assert.assertEquals( "Failure. Gomes not found in Users Database.",
                             patchUserPassword.call().getMessage() );
        Assert.assertEquals( "Failure. Cannot change MASTER's password.",
                             patchUserPassword2.call().getMessage() );
    }
    
    @Test
    public void shouldNotPatchAnExistingUserIfAWrongAuthenticationPasswordIsGiven()
        throws Exception {
    
        Assert.assertEquals( "Failure. Old password is incorrect.",
                             new PatchUserPasswordCommand( usersDatabase, "Daniel", "dany", "dany" ).call()
                                                                                                    .getMessage() );
    }
    
    // Test Exceptions
    
    @Test( expected = InvalidArgumentException.class )
    public void shouldThrowInvalidArgumentExceptionIfCreatingInstantiatingWithNullDatabase()
        throws InvalidArgumentException {
    
        new PatchUserPasswordCommand( null, "Daniel", "pass", "dany" );
    }
    
    @Test( expected = InvalidArgumentException.class )
    public void shouldThrowInvalidArgumentExceptionIfCreatingInstantiatingWithNullUsername()
        throws InvalidArgumentException {
    
        new PatchUserPasswordCommand( usersDatabase, null, "pass", "dany" );
    }
    
    @Test( expected = InvalidArgumentException.class )
    public void shouldThrowInvalidArgumentExceptionIfCreatingInstantiatingWithNullOldPassword()
        throws InvalidArgumentException {
    
        new PatchUserPasswordCommand( usersDatabase, "Daniel", null, "dany" );
    }
    
    @Test( expected = InvalidArgumentException.class )
    public void shouldThrowInvalidArgumentExceptionIfCreatingInstantiatingWithNullNewPassword()
        throws InvalidArgumentException {
    
        new PatchUserPasswordCommand( usersDatabase, "Daniel", "pass", null );
    }
}
