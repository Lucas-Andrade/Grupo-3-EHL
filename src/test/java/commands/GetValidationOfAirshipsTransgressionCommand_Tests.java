package test.java.commands;

import main.java.CommandParser;
import main.java.commands.getAirshipsCommands.GetValidationOfAirshipsTransgressionCommand;
import main.java.exceptions.commandExecptions.CommandException;
import main.java.exceptions.commandParserExceptions.DuplicateParametersException;
import main.java.exceptions.commandParserExceptions.InvalidCommandParametersSyntaxException;
import main.java.exceptions.commandParserExceptions.InvalidRegisterException;
import main.java.exceptions.commandParserExceptions.UnknownCommandException;
import main.java.exceptions.dataBaseExceptions.NoSuchElementInDatabaseException;
import main.java.model.airships.CivilAirship;
import main.java.model.airships.InMemoryAirshipDatabase;
import main.java.model.airships.MilitaryAirship;
import main.java.model.users.User;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * 
 * Those Tests were created to test the {@link GetValidationOfAirshipsTransgressionCommand} Class who's a
 * Command of the Air Traffic Project
 *
 */
public class GetValidationOfAirshipsTransgressionCommand_Tests {

	private CommandParser commandParser = new CommandParser();
	private InMemoryAirshipDatabase airshipsDatabaseWhereToSearch = new InMemoryAirshipDatabase();

	private User pantunes = new User("pantunes", "pass", "pantunes@gmail.com", "Pantunes");

	@Before
	public void BeforeTests() throws InvalidRegisterException {

		commandParser.registerCommand("GET", "/airships/reports",
				new GetValidationOfAirshipsTransgressionCommand.Factory(airshipsDatabaseWhereToSearch));

	}

	@Test
	public void ShouldNotHaveAnyTransgressionRecords() {

		MilitaryAirship f16 = new MilitaryAirship(50, 100, 10000, 15000, 5000, true);
		MilitaryAirship bombardeiro = new MilitaryAirship(-60, 120, 12000, 20000, 10000, true);
		CivilAirship boing777 = new CivilAirship(60, 220, 10000, 10000, 8000, 300);

		airshipsDatabaseWhereToSearch.add(f16, pantunes);
		airshipsDatabaseWhereToSearch.add(bombardeiro, pantunes);
		airshipsDatabaseWhereToSearch.add(boing777, pantunes);
		
		try {		

			GetValidationOfAirshipsTransgressionCommand getReport = (GetValidationOfAirshipsTransgressionCommand) commandParser
					.getCommand("GET", "/airships/reports");	
			getReport.execute();
			Assert.assertEquals("There are no transgressions recorded", getReport.getResult());

		} catch (NoSuchElementInDatabaseException | CommandException | UnknownCommandException | DuplicateParametersException | InvalidCommandParametersSyntaxException e) {

		}

	}

	@Test
	public void ShouldHaveAnyTransgressionRecords() throws Exception {

		CivilAirship boing747 = new CivilAirship(-70, 145, 9000, 20000, 10000, 350);
		MilitaryAirship alouette = new MilitaryAirship(45, 200, 12000, 20000, 10000, false);
		MilitaryAirship superblanik = new MilitaryAirship(45, 300, 18000, 15000, 10000, false);

		airshipsDatabaseWhereToSearch.add(boing747, pantunes);
		airshipsDatabaseWhereToSearch.add(alouette, pantunes);
		airshipsDatabaseWhereToSearch.add(superblanik, pantunes);
		
		try {
		GetValidationOfAirshipsTransgressionCommand getReport = (GetValidationOfAirshipsTransgressionCommand) commandParser
				.getCommand("GET", "/airships/reports");
		getReport.execute();
		Assert.assertEquals("\n Airship flightID: "+boing747.getIdentification() + "\n Airship flightID: " + superblanik.getIdentification(),
			getReport.getResult());
		} catch(NoSuchElementInDatabaseException | CommandException | UnknownCommandException | DuplicateParametersException | InvalidCommandParametersSyntaxException e){
			
		}
	}
}