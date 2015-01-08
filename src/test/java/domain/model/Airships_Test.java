package test.java.domain.model;


import main.java.domain.model.airships.Airship;
import main.java.domain.model.airships.CivilAirship;
import main.java.domain.model.airships.MilitaryAirship;
import main.java.utils.exceptions.InvalidArgumentException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


/**
 * Test class that targets the classes: {@link Airship}, its subclasses
 * {@link CivilAirship} and {@link MilitaryAirship},
 * {@link GeographicalPosition}, {@link GeographicalCoordinate} and
 * {@link AirCorridor}. 
 *
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
public class Airships_Test
{
	
	private Airship airship1, airship2, airship3;
	
	@Before
	public void createAirships() {
		
		try
		{
			airship1 = new CivilAirship( 30, 40, 6000, 3000, 300, 6 );
			airship2 = new MilitaryAirship( 30, 40, 5000, 4000, 200, false );
			airship3 = new MilitaryAirship( 30, 40, 2000, 3000, 200, true );
		}
		catch( InvalidArgumentException e )
		{
			// never happens cause the given values for initialization are valid
		}
	}
	
	@Test( expected = InvalidArgumentException.class )
	public void shouldThrowInvalidArgumentExceptionIfCivilAirshipHasLessThatZeroPassengers()
			throws InvalidArgumentException {
		
		new CivilAirship( 30, 40, 2000, 3000, 300, -3 );
	}
	
	@Test( expected = InvalidArgumentException.class )
	public void shouldThrowInvalidArgumentExceptionIfLatitudeIsNotWhitinTheValidLimits()
			throws InvalidArgumentException {
		
		new CivilAirship( 100, 40, 2000, 3000, 300, 20 );
	}
	
	@Test( expected = InvalidArgumentException.class )
	public void shouldThrowInvalidArgumentExceptionIfLongitudeIsNotWhitinTheValidLimits()
			throws InvalidArgumentException {
		
		new CivilAirship( 10, -10, 2000, 3000, 300, 20 );
	}
	
	@Test( expected = InvalidArgumentException.class )
	public void shouldThrowInvalidArgumentExceptionIfAltitudeIsNotWhitinTheValidLimits()
			throws InvalidArgumentException {
		
		new CivilAirship( 10, 60, 300000, 3000, 300, 20 );
	}
	
	@Test( expected = InvalidArgumentException.class )
	public void shouldThrowInvalidArgumentExceptionIfTheMaximumAltitudeIsLessThanTheMinimumAltitude()
			throws InvalidArgumentException {
		
		new CivilAirship( 10, 60, 3000, 200, 3000, 20 );
	}
	
	@Test( expected = InvalidArgumentException.class )
	public void shouldThrowInvalidArgumentExceptionIfTheMinimumAltitudeIsLessThanZero()
			throws InvalidArgumentException {
		
		new CivilAirship( 10, 60, 3000, 200, -10, 20 );
	}
	
	@Test
	public void shouldVerifyIfAnAirshipIsNotWhitinItsAirCorridor() {
		// Assert
		Assert.assertTrue( airship1.isTransgressing() );
		Assert.assertTrue( airship2.isTransgressing() );
		Assert.assertFalse( airship3.isTransgressing() );
	}
}