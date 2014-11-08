package app;


import java.util.InputMismatchException;
import java.util.Scanner;


/**
 * EHL's AIR TRAFFIC CONTROL app for console.
 * <p style="font-size:16">
 * <b>Description</b>
 * </p>
 * <p>
 * Runnable class to perform actions of air traffic control. </br>This app
 * allows the user to manage a set of flights, inserting them into the app
 * either from a file or manually, with the purpose of monitoring the geographic
 * coordinates of the airships at each moment in time and producing reports of
 * the airships transgressing the pre-established air corridors.
 * </p>
 * 
 * <p style="font-size:16">
 * <b>Usage</b>
 * </p>
 * <p>
 * When launching this app, the user is given access to a first menu:
 * 
 * <ol style="font-family:Consolas">
 * OPTION MENU
 * <li>Add a list of flights from txt file.</li>
 * <li>Add a flight manually.</li>
 * <li>Monitor Air Traffic.</li>
 * <li>Report altitude transgressions.</li>
 * <li>Consult a flight's details.</li>
 * <li>Remove zero-passenger-flights.</li>
 * <li>Remove a flight manually.</li>
 * <li>Configurations.</li>
 * <li>Help!</li>
 * <li>Exit app.</li>
 * </ol>
 * </p>
 * <p>
 * The user must write the number of the option in the console and press Enter
 * to proceed.
 * <p>
 * <b> Detailed description of the options: </b>
 * </p>
 * <p style="font-family:Consolas">
 * <b> 1. Add a list of flights from a text file.</b>
 * </p>
 * <p>
 * To add a list of flights from a text file, the user is asked to introduce the
 * path and filename of the text file and its expected that the list is
 * organized in the following manner:
 * <ul>
 * <li>Each and every line has information about one flight only. There must be
 * no empty lines and the first line is already interpreted has containing a
 * flight's information.</li>
 * <li>Information on a line must be written attending to the fact that each
 * word is interpreted as a specific information and words are separated by the
 * character space. Words in a line should be presented in the following manner:
 * <ol>
 * <li>First word: the flight ID number or code (it must not contain the space
 * character);</li>
 * <li>Second word: the type of airship used in this flight, it must necessarily
 * be the word "military" or the word "civil"</li>
 * <li>...</li> TODO
 * </ol>
 * </li>
 * </ul>
 * Each flight in the list will be immediately inserted in the app's internal
 * data base of flights and every flight's detailed information is available to
 * be consulted using the {@code Option5} of the {@code Option Menu} until the
 * airship's landing time. After the hour of take-off, the airship's geographic
 * coordinates will also be available for monitoring and tracking transgressions
 * of the established airway corridors.
 * </p>
 * <p style="font-family:Consolas">
 * <b> 2. Add a flight manually.</b>
 * </p>
 * </ol> </p>
 * <p>
 * ...TODO
 * </p>
 *
 * @author Eva Gomes
 * @author Hugo Leal
 * @author Lucas Andrade
 * 
 *         <p>
 *         Copyright (c) 2014, EHL. All rights reserved. DO NOT ALTER OR REMOVE
 *         COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 *         This code is distributed in the hope to get us good grades, but
 *         WITHOUT ANY WARRANTY; without even the implied warranty of
 *         MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 *         </p>
 */
public class AppForConsole
{
	
	// CAMPOS DA CLASSE
	
	/**
	 * The scanner that receives input from the console.
	 */
	private static final Scanner IN = new Scanner( System.in );
	
	/**
	 * This app's menu.
	 */
	private static final OptionsMenu MAINMENU = new OptionsMenu(
			"Options Menu", AddAListOfFlightsOption.getInstance(),
			AddAFlightOption.getInstance(),
			MonitorAirTrafficOption.getInstance(),
			ReportTransgressionsOption.getInstance(),
			ConsultFlightDetailsOption.getInstance(),
			RemoveEmptyAirshipsOption.getInstance(),
			RemoveAFlightOption.getInstance(),
			ConfigurationsOption.getInstance(), HelpOption.getInstance(),
			ExitOption.getInstance() );
	
	
	/**
	 * The style formatting set up for this app's output.
	 */
	private static final ConsoleOutputFormatter STYLIZER = new ConsoleOutputFormatter(
			'-', 45, 4 );
	
	
	
	// O MÉTODO main
	
	public static void main( String[] args ) {
		
		// WELCOME
		
		STYLIZER.printSectionDelimiter();
		System.out.print( "Welcome to the EHL's\n AIR TRAFFIC CONTROL app!" );
		STYLIZER.printSectionDelimiter();
		
		boolean runApp = true;
		
		do
		{
			
			// APRESENTAÇAO DO MENU
			
			printBeginningOfSection( MAINMENU.menuTitle );
			System.out.print( MAINMENU.inAString() );
			STYLIZER.printSectionDelimiter();
			int selectedOption = askTheUserForAValidOption();
			printEndOfSection();
			
			// EXECUÇAO DE UMA OPÇAO
			
			printBeginningOfSection( MAINMENU.getOptionTitle( selectedOption ) );
			runApp = !MAINMENU.executeOption( selectedOption );
			printEndOfSection();
			
		}
		while( runApp );
		
		
		// FINAL
		
		System.out.print( "\nCopyright (c) 2014, EHL. All rights reserved.\n" );
		STYLIZER.printSectionTitle( "end" );
	}
	
	
	
	// MÉTODOS RELACIONADOS COM AS OPÇÕES
	
	
	
	/**
	 * Returns the number of the option chosen by the user.
	 * 
	 * <p>
	 * Asks the user for a number, corresponding to the action he wants to
	 * perform from the {@code Option Menu}, until the number inserted by the
	 * user is valid (meaning, is between 1 and the number corresponding to the
	 * last option in the {@code Option Menu}) and returns the valid number
	 * chosen.
	 * </p>
	 * 
	 * @return The number of the option chosen by the user.
	 */
	private static int askTheUserForAValidOption() {
		
		int selectedOption = 0;
		while( selectedOption == 0 )
			try
			{
				selectedOption = askTheUserForAnOption();
			}
			catch( InputMismatchException e )
			{
				System.out.println( "INVALID OPTION!\n" );
				IN.nextLine(); // limpeza
			}
			catch( InvalidOptionNumberException e )
			{
				System.out.println( "INVALID OPTION!\n" );
				IN.nextLine(); // limpeza
			}
		return selectedOption;
	}
	
	
	/**
	 * Asks the user once for the number of the option from the
	 * {@code Option Menu} he wants the app to perform.
	 * 
	 * @return The number inserted by the user, if it is between 1 and the
	 *         maximum number of options available in the menu; or 0, if the
	 *         user inserted an invalid value.
	 * @throws InputMismatchException
	 *             If the user inserted something that is not a number in the
	 *             console.
	 * @throws InvalidOptionNumberException
	 *             If the user inserted an invalid number in the console.
	 */
	private static int askTheUserForAnOption() throws InputMismatchException,
			InvalidOptionNumberException {
		
		System.out.println( " Type the number of the option you want to" );
		System.out.println( " perform and press Enter." );
		System.out.print( "\nExecute option: " );
		
		int selectedOption = IN.nextInt(); // throws InputMismatchException if
											// non-number received
		
		if( selectedOption < 1 || selectedOption > MAINMENU.numberOfOptions )
			throw new InvalidOptionNumberException();
		
		IN.nextLine(); // limpeza
		return selectedOption;
		
	}
	
	
	
	// MÉTODOS RELACIONADOS COM IMPRESSAO DE SECÇOES
	
	
	
	/**
	 * Prints the space between sections, the section delimiter, the formatted
	 * section title and another section delimiter, according to the
	 * {@link #STYLIZER STYLIZER}.
	 * 
	 * @param sectionTitle
	 *            The title of the section.
	 */
	private static void printBeginningOfSection( String sectionTitle ) {
		
		if( sectionTitle == null )
		{
			STYLIZER.printSpaceBetweenSections();
			STYLIZER.printSectionDelimiter();
		}
		else
		{
			STYLIZER.printSpaceBetweenSections();
			STYLIZER.printSectionDelimiter();
			STYLIZER.printSectionTitle( sectionTitle );
			STYLIZER.printSectionDelimiter();
		}
	}
	
	/**
	 * Prints a section delimiter.
	 */
	private static void printEndOfSection() {
		STYLIZER.printSectionDelimiter();
	}
	
}