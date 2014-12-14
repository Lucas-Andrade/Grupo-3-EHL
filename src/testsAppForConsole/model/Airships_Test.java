package testsAppForConsole.model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import appForConsole.model.airships.Airship;
import appForConsole.model.airships.CivilAirship;
import appForConsole.model.airships.MilitaryAirship;

public class Airships_Test {

	private Airship airship1, airship2, airship3, airship4;

	@Before
	public void createAirships() {

		airship1 = new CivilAirship(30, 40, 6000, 3000, 300, 6);
		airship2 = new MilitaryAirship(30, 40, 5000, 4000, 200, false);
		airship3 = new MilitaryAirship(30, 40, 2000, 3000, 200, true);
	}

	@Test (expected = IllegalArgumentException.class)
	public void shouldThrowIllegalArgumentExceptionIfCivilAirshipHasLessThatZeroPassengers() {

		airship4 = new CivilAirship(30, 40, 2000, 3000, 300, -3);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void shouldThrowIllegalArgumentExceptionIfLatitudeIsNotWhitinTheValidLimits() {

		airship4 = new CivilAirship(100, 40, 2000, 3000, 300, 20);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void shouldThrowIllegalArgumentExceptionIfLongitudeIsNotWhitinTheValidLimits() {

		airship4 = new CivilAirship(10, -10, 2000, 3000, 300, 20);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void shouldThrowIllegalArgumentExceptionIfAltitudeIsNotWhitinTheValidLimits() {

		airship4 = new CivilAirship(10, 60, 300000, 3000, 300, 20);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void shouldThrowIllegalArgumentExceptionIfTheMaximumAltitudeIsLessThanTheMinimumAltitude() {

		airship4 = new CivilAirship(10, 60, 3000, 200, 3000, 20);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void shouldThrowIllegalArgumentExceptionIfTheMinimumAltitudeIsLessThanZero() {

		airship4 = new CivilAirship(10, 60, 3000, 200, -10, 20);
	}
	
	@Test
	public void shouldVerifyIfAnAirshipIsNotWhitinItsAirCorridor() {
		// Assert
		Assert.assertTrue(airship1.isTransgressing());
		Assert.assertTrue(airship2.isTransgressing());
		Assert.assertFalse(airship3.isTransgressing());
	}
}