package airtrafficcontrol.app.tests;


import static org.junit.Assert.*;
import java.util.Scanner;
import org.junit.Test;
import airtrafficcontrol.app.appforconsole.ConsoleInputHandler;


public class ConsoleInputTreatmentTest
{
	
	@Test
	public void nullStringsShouldNotBeAlphaNumericStrings() {
		assertTrue( ConsoleInputHandler.isNotAnAlphanumericString( null ) );
	}
	
	@Test
	public void emptyStringsShouldNotBeAlphaNumericStrings() {
		assertTrue( ConsoleInputHandler.isNotAnAlphanumericString( "" ) );
	}
	
	@Test
	public void stringsWithParagraphsShouldNotBeAlphaNumericStrings() {
		assertTrue( ConsoleInputHandler
				.isNotAnAlphanumericString( "sakj\nlsekrg" ) );
	}
	
	@Test
	public void stringsWithSpacesShouldNotBeAlphaNumericStrings() {
		assertTrue( ConsoleInputHandler
				.isNotAnAlphanumericString( "afgsf asdgsd" ) );
	}
	
	@Test
	public void stringsWithSymbolsShouldNotBeAlphaNumericStrings() {
		assertTrue( ConsoleInputHandler
				.isNotAnAlphanumericString( "afgs.asd/gsd" ) );
		assertTrue( ConsoleInputHandler
				.isNotAnAlphanumericString( "af_asdg:sd" ) );
		assertTrue( ConsoleInputHandler
				.isNotAnAlphanumericString( "afgs#dgs@d" ) );
		assertTrue( ConsoleInputHandler
				.isNotAnAlphanumericString( "afgsf?g`sd" ) );
		assertTrue( ConsoleInputHandler
				.isNotAnAlphanumericString( "afg[gsd{" ) );
		assertTrue( ConsoleInputHandler
				.isNotAnAlphanumericString( "afgs}dgs,d" ) );
	}
	
	@Test
	public void shouldBeAlphaNumericStrings() {
		assertFalse( ConsoleInputHandler
				.isNotAnAlphanumericString( "afgasdgzsd" ) );
		assertFalse( ConsoleInputHandler
				.isNotAnAlphanumericString( "345789rnv1fis9d" ) );
		assertFalse( ConsoleInputHandler
				.isNotAnAlphanumericString( "afgAEFsZgfdiFDSGF3sd" ) );
		assertFalse( ConsoleInputHandler.isNotAnAlphanumericString( "ABORT" ) );
	}
	
}
