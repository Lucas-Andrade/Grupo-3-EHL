package tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import app.ConsoleOutputFormatter;

public class ConsoleOutputFormatterTest {
	
	ConsoleOutputFormatter formatter;
	ConsoleOutputFormatter formatterA;
	
	@Before
	public void constructsOutputFormatter() 
	{
		//Arrange
		formatter = new ConsoleOutputFormatter();
		formatterA = new ConsoleOutputFormatter('!', 45, 4);
	}
		
	@Test
	public void shouldFormatSectionTitle()
	{
		//Arrange
		String title = "slb";
		String expectedFormatedTitle = "---------------------- SLB -----------------------";
		String expectedFormatedTitleA = "!!!!!!!!!!!!!!!!!!!! SLB !!!!!!!!!!!!!!!!!!!!";
		
		//Act
		String formatedTitle = formatter.formatSectionTitle(title);
		String formatedTitleA = formatterA.formatSectionTitle(title);
		
		//Assert
		assertTrue(expectedFormatedTitle.equals(formatedTitle));
		assertTrue(expectedFormatedTitleA.equals(formatedTitleA));
	}

}
