package airtrafficcontrol.tests;


import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import airtrafficcontrol.app.appforconsole.exceptions.InvalidArgumentException;
import airtrafficcontrol.app.appforconsole.utils.AltitudeCorridor;


/**
 * Test class that targets the class {@link AltitudeCorridor}.
 * 
 * <p style="font-size:14">
 * <b>Implementation notes</b>
 * </p>
 * <ul>
 * <li>Needs input from console to test.</li>
 * </ul>
 *
 * @author Eva Gomes
 * @author Hugo Leal
 * @author Lucas Andrade
 */
public class AltitudeCorridorTest
{
	
	AltitudeCorridor corridor;
	
	@Before
	public void initializeTheCorridor() throws InvalidArgumentException {
		corridor = new AltitudeCorridor( 1, 2 );
	}
	
	@Test
	public void shouldSwapUpperAndLowerLimits() throws InvalidArgumentException {
		AltitudeCorridor ac = new AltitudeCorridor( 6000, 8000 );
		assertEquals( 6000, ac.getLowerLimit(), 0 );
		assertEquals( 8000, ac.getUpperLimit(), 0 );
	}
	
	@Test( expected = InvalidArgumentException.class )
	public void shouldNotReceiveUpperValueBelowMinimumAltitudeAllowed()
			throws InvalidArgumentException {
		new AltitudeCorridor( -3, 8000 );
	}
	
	@Test( expected = InvalidArgumentException.class )
	public void shouldNotReceiveLowerValueBelowMinimumAltitudeAllowed()
			throws InvalidArgumentException {
		new AltitudeCorridor( 8000, -340 );
	}
	
	@Test
	public void shouldReturnTheHigherLimit() {
		assertEquals( corridor.getUpperLimit(), 2, 0 );
	}
	
	@Test
	public void shouldReturnTheLowerLimit() {
		assertEquals( corridor.getLowerLimit(), 1, 0 );
	}
	
	@Test
	public void despiteBeingIntroducedInTheWrongOrderTheHigherValueShouldAlwaysBeTheHigherAndTheSameForTheLower()
			throws InvalidArgumentException {
		AltitudeCorridor corridor = new AltitudeCorridor( 10, 2 );
		assertEquals( 10, corridor.getUpperLimit(), 0 );
		assertEquals( 2, corridor.getLowerLimit(), 0 );
	}
}
