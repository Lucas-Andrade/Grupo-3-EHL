package parsingtools_tests.commandfactories.getfactories;


import java.util.concurrent.Callable;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import parsingtools.CommandParser;
import parsingtools.Parser;
import parsingtools.commandfactories.getfactories.getallfactories.GetAllAirshipsInADatabaseCommandsFactory;
import parsingtools.commandfactories.getfactories.getallfactories.GetAllElementsInADatabaseCommandsFactory;
import parsingtools.commandfactories.getfactories.getallfactories.GetAllUsersInADatabaseCommandsFactory;
import utils.exceptions.parsingexceptions.InvalidCommandSyntaxException;
import utils.exceptions.parsingexceptions.commandparserexceptions.InvalidRegisterException;
import utils.exceptions.parsingexceptions.commandparserexceptions.UnknownCommandException;

import commands.getcommands.GetAllElementsInADatabaseCommand;

import databases.InMemoryAirshipsDatabase;
import databases.InMemoryUsersDatabase;
import exceptions.InternalErrorException;
import exceptions.InvalidArgumentException;
import exceptions.InvalidParameterValueException;
import exceptions.MissingRequiredParameterException;
import exceptions.NoSuchElementInDatabaseException;
import exceptions.WrongLoginPasswordException;


/**
 * This Test class tests the following classes:
 * 
 * <pre>
 * 
 * {@link GetAllElementsInADatabaseCommandsFactory}
 * {@link GetAllUsersInADatabaseCommandsFactory}
 * {@link GetAllAirshipsInADatabaseCommandsFactory}
 * 
 * </pre>
 *
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
public class GetAllCommandsFactories_Tests {
    
    private static CommandParser cmdparser = new CommandParser();
    private static InMemoryUsersDatabase userDatabase;
    private static InMemoryAirshipsDatabase airshipsDatabase;
    
    // Before Class
    
    @BeforeClass
    public static void createTheCommandParserAndRegisterTheCommands()
        throws InvalidRegisterException, InvalidArgumentException {
    
        cmdparser = new CommandParser();
        
        userDatabase = new InMemoryUsersDatabase( "Users Database" );
        airshipsDatabase = new InMemoryAirshipsDatabase( "Airships Database" );
        
        cmdparser.registerCommand( "GET", "/airships",
                                   new GetAllAirshipsInADatabaseCommandsFactory( airshipsDatabase ) );
        
        cmdparser.registerCommand( "GET", "/users",
                                   new GetAllUsersInADatabaseCommandsFactory( userDatabase ) );
    }
    
    // Test Normal Dinamic And Prerequisites
    
    @Test
    public void shouldSuccessfullyCreateTheCorrectCommands()
        throws WrongLoginPasswordException, MissingRequiredParameterException,
        InvalidCommandSyntaxException, UnknownCommandException, NoSuchElementInDatabaseException,
        InvalidParameterValueException, InvalidArgumentException, InternalErrorException, Exception {
    
        Callable< ? > getAllUsersCommand = (new Parser( cmdparser, "GET", "/users" )).getCommand();
        
        Callable< ? > getAllAirshipsCommand =
                (new Parser( cmdparser, "GET", "/airships" )).getCommand();
        
        Assert.assertTrue( getAllUsersCommand instanceof GetAllElementsInADatabaseCommand );
        Assert.assertTrue( getAllAirshipsCommand instanceof GetAllElementsInADatabaseCommand );
    }
    
    // Test Exceptions
    
    @Test( expected = InvalidArgumentException.class )
    public
            void
            shouldThrowInvalidArgumentExceptionWhenTryingToCreateAGetAllAirshipsInADatabaseCommandsFactoryGivenANullDatabase()
                throws InvalidArgumentException {
    
        new GetAllAirshipsInADatabaseCommandsFactory( null );
    }
    
    @Test( expected = InvalidArgumentException.class )
    public
            void
            shouldThrowInvalidArgumentExceptionWhenTryingToCreateAGetAllUsersInADatabaseCommandsFactoryGivenANullDatabase()
                throws InvalidArgumentException {
    
        new GetAllUsersInADatabaseCommandsFactory( null );
    }
}
