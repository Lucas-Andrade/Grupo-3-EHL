package test.java.commands.postcommands;

import main.java.cli.commands.postcommands.PostCivilAirshipCommand;
import main.java.cli.commands.postcommands.PostMilitaryAirshipCommand;
import main.java.cli.exceptions.InvalidArgumentException;
import main.java.cli.model.airships.InMemoryAirshipsDatabase;
import main.java.cli.model.users.User;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class PostAirshipCommand_Tests {

	private InMemoryAirshipsDatabase airshipsDatabase;
	private User user1;
	private PostMilitaryAirshipCommand postMilitaryAirship;
	private PostCivilAirshipCommand postCivilAirship;

	// Before

	@Before
	public void createUserAndAirshipDatabaseWhereToPostTheCreatedAirships() throws InvalidArgumentException {

		// Arrange
		airshipsDatabase = new InMemoryAirshipsDatabase("Airships Database");

		user1 = new User("Daniel", "pass", "@daniel");
	}

	// Test Normal Dinamic And Prerequisites

	@Test
	public void shouldPostACivilAirshipWithTheCorrectParametersInTheGivenDatabase()
			throws Exception {

		// Act
		postCivilAirship = new PostCivilAirshipCommand(0, 0, 0, 100, 50, 100, airshipsDatabase, user1);
		String testedInformation = postCivilAirship.call();

		// Assert
		Assert.assertEquals(testedInformation, "Flight Id: id2" );
	}
	
	@Test
	public void shouldPostAMilitaryAirshipWithTheCorrectParametersInTheGivenDatabase()
			throws Exception {

		// Act
		postMilitaryAirship = new PostMilitaryAirshipCommand(0, 0, 0, 100, 50, false, airshipsDatabase, user1);
		String testedInformation = postMilitaryAirship.call();

		// Assert
		Assert.assertEquals(testedInformation, "Flight Id: id1" );
	}

	// Test Exceptions

	@Test (expected = InvalidArgumentException.class)
	public void shouldThrowInvalidArgumentExceptionWhenTryingToCreateThePostCivilAirshipCommandGivenANullDatabase()
			throws InvalidArgumentException {

		postCivilAirship = new PostCivilAirshipCommand(0, 0, 0, 100, 50, 100, null, user1);
	}
	
	@Test (expected = InvalidArgumentException.class)
	public void shouldThrowInvalidArgumentExceptionWhenTryingToCreateThePostMilitaryAirshipCommandGivenANullDatabase()
			throws InvalidArgumentException {

		postMilitaryAirship = new PostMilitaryAirshipCommand(0, 0, 0, 100, 50, false, null, user1);
	}
}