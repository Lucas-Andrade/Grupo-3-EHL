package tests;

import org.junit.Assert;
import org.junit.Test;

import utils.ReadAirplanesCoordinates;

public class ReadAirplanesCoordinatesTest {

	ReadAirplanesCoordinates read;
	@Test
	public void shouldGetEmptyFieldsFromFile()
	{
		// Arrange
		String sourceOfFlights = "src/FilesToRead/newCoordinates.txt";
		read = new ReadAirplanesCoordinates();
		String emptyFieldsExpected = "Empty Fields at Line: 5" + "\n";
		
		// Act
		read.readFromFile(sourceOfFlights);
		
		// Assert
		Assert.assertTrue(emptyFieldsExpected.equals(read.getEmptyFields()));
	}

}
