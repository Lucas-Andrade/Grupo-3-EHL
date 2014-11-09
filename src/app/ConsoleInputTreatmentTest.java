package app;


import static org.junit.Assert.*;
import java.util.Scanner;
import org.junit.Test;


public class ConsoleInputTreatmentTest
{
	
	@Test
	public void nullStringsShouldNotBeAlphaNumericStrings() {
		assertTrue( ConsoleInputTreatment.isNotAnAlphanumericString( null ) );
	}
	
	@Test
	public void emptyStringsShouldNotBeAlphaNumericStrings() {
		assertTrue( ConsoleInputTreatment.isNotAnAlphanumericString( "" ) );
	}
	
	@Test
	public void stringsWithParagraphsShouldNotBeAlphaNumericStrings() {
		assertTrue( ConsoleInputTreatment
				.isNotAnAlphanumericString( "sakj\nlsekrg" ) );
	}
	
	@Test
	public void stringsWithSpacesShouldNotBeAlphaNumericStrings() {
		assertTrue( ConsoleInputTreatment
				.isNotAnAlphanumericString( "afgsf asdgsd" ) );
	}
	
	@Test
	public void stringsWithSymbolsShouldNotBeAlphaNumericStrings() {
		assertTrue( ConsoleInputTreatment
				.isNotAnAlphanumericString( "afgs.asd/gsd" ) );
		assertTrue( ConsoleInputTreatment
				.isNotAnAlphanumericString( "af_asdg:sd" ) );
		assertTrue( ConsoleInputTreatment
				.isNotAnAlphanumericString( "afgs#dgs@d" ) );
		assertTrue( ConsoleInputTreatment
				.isNotAnAlphanumericString( "afgsf?g`sd" ) );
		assertTrue( ConsoleInputTreatment
				.isNotAnAlphanumericString( "afg[gsd{" ) );
		assertTrue( ConsoleInputTreatment
				.isNotAnAlphanumericString( "afgs}dgs,d" ) );
	}
	
	@Test
	public void shouldBeAlphaNumericStrings() {
		assertFalse( ConsoleInputTreatment
				.isNotAnAlphanumericString( "afgasdgzsd" ) );
		assertFalse( ConsoleInputTreatment
				.isNotAnAlphanumericString( "345789rnv1fis9d" ) );
		assertFalse( ConsoleInputTreatment
				.isNotAnAlphanumericString( "afgAEFsZgfdiFDSGF3sd" ) );
		assertFalse( ConsoleInputTreatment.isNotAnAlphanumericString( "ABORT" ) );
	}
	
}
