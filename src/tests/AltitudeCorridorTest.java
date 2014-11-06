package tests;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import utils.AltitudeCorridor;


public class AltitudeCorridorTest {

	AltitudeCorridor corridor;
	
	@Before
	public void initializeTheCorridor()
	{
		corridor = new AltitudeCorridor(1, 2);
	}
	
	@Test
	public void shouldReturnTheHigherLimit() {
		assertEquals(corridor.getUpperLimit(), 2);
	}

	@Test
	public void shouldReturnTheLowerLimit() {
		assertEquals(corridor.getLowerLimit(), 1);
	}
	
	@Test
	public void despiteBeingIntroducedInTheWrongOrderTheHigherValueShouldAlwaysBeTheHigherAndTheSameForTheLower()
	{
		AltitudeCorridor corridor = new AltitudeCorridor(10, 2);
		assertEquals(corridor.getUpperLimit(), 10);
		assertEquals(corridor.getLowerLimit(), 2);
	}
}
