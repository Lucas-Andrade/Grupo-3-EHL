package main.java.commandlineuserinterface;


import java.util.Scanner;
import java.util.concurrent.Callable;
import main.java.commandlineuserinterface.commandfactories.getairshipscommands.GetAirshipByIdCommand;
import main.java.commandlineuserinterface.commands.getairshipscommands.GetAirshipsByOwnerCommand;
import main.java.commandlineuserinterface.commands.getairshipscommands.GetAirshipsWithMinimumPassengersCommand;
import main.java.commandlineuserinterface.commands.getairshipscommands.GetAllAirshipsCommand;
import main.java.commandlineuserinterface.commands.getairshipscommands.GetReportOfTransgressionByIdCommand;
import main.java.commandlineuserinterface.commands.getairshipscommands.GetTransgressingAirshipsCommand;
import main.java.commandlineuserinterface.commands.getuserscommands.GetAllUsersCommand;
import main.java.commandlineuserinterface.commands.postcommands.PostAirshipCommand;
import main.java.commandlineuserinterface.commands.postcommands.PostUserCommand;
import main.java.commandlineuserinterface.exceptions.commandparserexceptions.DuplicateParametersException;
import main.java.commandlineuserinterface.exceptions.commandparserexceptions.InvalidCommandParametersSyntaxException;
import main.java.commandlineuserinterface.exceptions.commandparserexceptions.InvalidRegisterException;
import main.java.commandlineuserinterface.exceptions.commandparserexceptions.UnknownCommandException;
import main.java.commandlineuserinterface.exceptions.databaseexceptions.NoSuchElementInDatabaseException;
import main.java.commandlineuserinterface.model.airships.InMemoryAirshipDatabase;
import main.java.commandlineuserinterface.model.users.InMemoryUserDatabase;


public class App
{
	
	private static final CommandParser parser = new CommandParser();
	private static final Scanner scanner = new Scanner( System.in );
	private static final InMemoryUserDatabase usersDatabase = new InMemoryUserDatabase(
			"internal users database" );
	private static final InMemoryAirshipDatabase airshipsDatabase = new InMemoryAirshipDatabase(
			"internal airships database" );
	
	public static void main( String[] args ) {
		
		registerCommands();
		
		if( args.length != 0 )
			execute( args );
		else
		{// prompt,execution,prompt,execution,prompt....,exit
		
			System.out.println( ">>" );
			String input = scanner.nextLine();
			
			while( !input.equals( "EXIT" ) )
			{
				execute( input.split( " " ) );
				System.out.print( "\n> " );
				input = scanner.nextLine();
			}
			
		}
	}
	
	private static void registerCommands() {
		
		try
		{
			
			// Register Get Users
			parser.registerCommand( "GET", "/users",
					new GetAllUsersCommand.Factory( usersDatabase ) );
			parser.registerCommand( "GET", "/users/{username}",
					new GetUserByUsernameCommand.Factory( usersDatabase ) );
			
			// Register Get Airships
			parser.registerCommand( "GET", "/airships",
					new GetAllAirshipsCommand.Factory( airshipsDatabase ) );
			parser.registerCommand( "GET", "/airships/{flightId}",
					new GetAirshipByIdCommand.Factory( airshipsDatabase ) );
			parser.registerCommand( "GET", "/airships/owner/{owner}",
					new GetAirshipsByOwnerCommand.Factory( airshipsDatabase ) );
			parser.registerCommand( "GET",
					"/airships/nbPassengers/{nbP}/bellow",
					new GetAirshipsWithMinimumPassengersCommand.Factory(
							airshipsDatabase ) );
			parser.registerCommand( "GET", "/airships/reports",
					new GetTransgressingAirshipsCommand.Factory(
							airshipsDatabase ) );
			parser.registerCommand( "GET", "/airships/reports/{flightId}",
					new GetReportOfTransgressionByIdCommand.Factory(
							airshipsDatabase ) );
			
			// Register Posts For Users
			parser.registerCommand( "POST", "/users",
					new PostUserCommand.Factory( usersDatabase, usersDatabase ) );
			
			// Register Posts For Airships
			parser.registerCommand( "POST", "/airships/{type}",
					new PostAirshipCommand.Factory( usersDatabase,
							airshipsDatabase ) );
			
		}
		catch( InvalidRegisterException e )
		{
			
			System.out.println( e.getMessage() );
		}
	}
	
	private static void execute( String[] args ) {
		
		try
		{
			Callable< ? > command = parser.getCommand( args );
			command.call();
			System.out.println( command.getResult() );
			
		}
		catch( UnknownCommandException | DuplicateParametersException
				| InvalidCommandParametersSyntaxException
				| NoSuchElementInDatabaseException | CommandException e )
		{
			
			System.out.println( e.getMessage() );
		}
	}
}
