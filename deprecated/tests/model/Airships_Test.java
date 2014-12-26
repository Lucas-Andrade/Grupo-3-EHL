package test.java.commandlineuserinterface.model;


import main.java.commandlineuserinterface.exceptions.InvalidArgumentException;
import main.java.commandlineuserinterface.model.airships.Airship;
import main.java.commandlineuserinterface.model.airships.CivilAirship;
import main.java.commandlineuserinterface.model.airships.MilitaryAirship;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class Airships_Test
{	
	@SuppressWarnings( "unused" )
	private Airship airship1, airship2, airship3, airship4;
	
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
			// never happens with these values
		}
	}
	
	@Test( expected = InvalidArgumentException.class )
	public void shouldThrowIllegalArgumentExceptionIfCivilAirshipHasLessThatZeroPassengers() throws InvalidArgumentException {
		
		airship4 = new CivilAirship( 30, 40, 2000, 3000, 300, -3 );
	}
	
	@Test( expected = InvalidArgumentException.class )
	public void shouldThrowIllegalArgumentExceptionIfLatitudeIsNotWhitinTheValidLimits() throws InvalidArgumentException {
		
		airship4 = new CivilAirship( 100, 40, 2000, 3000, 300, 20 );
	}
 	
	@Test( expected = InvalidArgumentException.class )
	public void shouldThrowIllegalArgumentExceptionIfLongitudeIsNotWhitinTheValidLimits() throws InvalidArgumentException {
		
		airship4 = new CivilAirship( 10, -10, 2000, 3000, 300, 20 );
	}
	
	@Test( expected = InvalidArgumentException.class )
	public void shouldThrowIllegalArgumentExceptionIfAltitudeIsNotWhitinTheValidLimits() throws InvalidArgumentException {
		
		airship4 = new CivilAirship( 10, 60, 300000, 3000, 300, 20 );
	}
	
	@Test( expected = InvalidArgumentException.class )
	public void shouldThrowIllegalArgumentExceptionIfTheMaximumAltitudeIsLessThanTheMinimumAltitude() throws InvalidArgumentException {
		
		airship4 = new CivilAirship( 10, 60, 3000, 200, 3000, 20 );
	}
	
	@Test( expected = InvalidArgumentException.class )
	public void shouldThrowIllegalArgumentExceptionIfTheMinimumAltitudeIsLessThanZero() throws InvalidArgumentException {
		
		airship4 = new CivilAirship( 10, 60, 3000, 200, -10, 20 );
	}
	
	@Test
	public void shouldVerifyIfAnAirshipIsNotWhitinItsAirCorridor() {
		// Assert
		Assert.assertTrue( airship1.isTransgressing() );
		Assert.assertTrue( airship2.isTransgressing() );
		Assert.assertFalse( airship3.isTransgressing() );
	}
}