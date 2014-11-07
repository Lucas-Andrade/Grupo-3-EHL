package app;


import java.util.InputMismatchException;
import java.util.Scanner;


/**
 * Air Traffic Control app for console.
 * <p style="font-size:16">
 * <b>Description</b>
 * </p>
 * <p>
 * Runnable class to perform actions of air traffic control. </br>This app
 * allows the user to manage a set of flights, inserting them into the app
 * either from a file or manually, with the purpose of monitoring the geographic
 * coordinates of the airships at each moment in time and producing reports of
 * the airships transgressing the pre-established air corridors.</p>
 * 
 * <p style="font-size:16">
 * <b>Usage</b>
 * </p>
 * <p>
 * When launching this app, the user is given access to a first menu:
 * 
 * <ol style="font-family:Consolas">
 * OPTION MENU
 * <li>Add a list of flights from a text file.</li>
 * <li>Add a flight manually.</li>
 * <li>Monitor Air Traffic.</li>
 * <li>Print a report of transgressions to a text file.</li>
 * <li>Consult a flight's details.</li>
 * <li>Remove all Passenger-Flights with zero passengers.</li>
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
 * </ol> </p> ...TODO
 *
 * @author Eva Gomes
 * @author Hugo Leal
 * @author Lucas Andrade
 */
public class AppForConsole
{
	
	// CAMPOS DA CLASSE
	
	/**
	 * The scanner that receives input from the console.
	 */
	private static Scanner in = new Scanner( System.in );
	
	
	
	// O MÉTODO main
	
	public static void main( String[] args ) {
		
		boolean runApp = true;
		do
		{
			
			// apresentaçao do menu
			printSectionDelimiter();
			printSectionTitle( MainMenu.menuTitle );
			System.out.print( MainMenu.inAString() );
			AppForConsole.printSectionDelimiter();
			int selectedOption = askTheUserForAValidOption();
			printSectionDelimiter();
			
			
			printSpaceBetweenSections();
			
			
			// execuçao de uma opçao
			printSectionDelimiter();
			printSectionTitle( "aqi vem a execuçao da opçao" );// provisoria
			runApp = executeOption( selectedOption );
			printSectionDelimiter();
			
			
			printSpaceBetweenSections();
			
		}
		while( runApp );
		
		// mensagem "END"
		AppForConsole.printSectionTitle( "end" );
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
				in.nextLine(); // limpeza
			}
			catch( InvalidOptionNumberException e )
			{
				System.out.println( "INVALID OPTION!\n" );
				in.nextLine(); // limpeza
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
		
		int selectedOption = in.nextInt();
		
		if( selectedOption < 1 || selectedOption > MainMenu.numberOfOptions )
			throw new InvalidOptionNumberException();
		
		in.nextLine(); // limpeza
		return selectedOption;
		
	}
	
	
	/**
	 * Performs the action corresponding to the option from the
	 * {@code Main Menu} chosen by the user.
	 * 
	 * @param option
	 *            The option chosen by the user.
	 * @return {@code true} if the app should return to the {@code Option Menu}
	 *         after the execution of this option; {@code false} if the app is
	 *         to be ended after this option executes.
	 */
	private static boolean executeOption( int option ) {
		
		switch( option )
		{
			case 1:
				break;
			case 2:
				break;
			case 3:
				break;
			case 4:
				break;
			case 5:
				break;
			case 6:
				break;
			case 7:
				break;
			case 8:
				break;
			case 9:
				break;
			case 10:
				System.out.print( "Thanks for using an EHL app! Bye!" );
				return false;
		}
		return true;
		
	}
	
	
	
	// MÉTODOS RELACIONADOS COM A APRESENTAÇÃO DO OUTPUT
	
	
	
	/**
	 * Returns the string that will delimit the sections presented in the
	 * output. This section delimiter consists in a new line filled with
	 * symbols. Its presence in the output contributes to the output's
	 * readability.
	 * 
	 * @return The section delimiter.
	 */
	public static String sectionDelimiter() {
		return "\n-----------------------------------------------------\n";
	}
	
	
	/**
	 * Prints the section delimiter to the console (see the method
	 * {@code sectionDelimiter()}).
	 */
	public static void printSectionDelimiter() {
		System.out.print( sectionDelimiter() );
	}
	
	
	/**
	 * Formats a section title.
	 * 
	 * <p>
	 * The {@code title} will be returned in a single line which is constructed
	 * from the section delimiter of this app (see the method
	 * {@code sectionDelimiter()}) by removing the middle symbols and inserting
	 * {@code title} in its place; the formatted title string has the same
	 * length as the section delimiter string. </br>For example, if the section
	 * delimiter was "......................" and {@code title} was "My Title",
	 * the formatted section title would be the line "...... MY TITLE ......".
	 * <p>
	 * In case the length of {@code title} equals or exceeds the length of the
	 * section delimiter, the title will simply be printed in upper case. Also,
	 * if the string {@code title} has line changing characters, the title will
	 * simply be printed in upper case using has many lines as the string
	 * {@code title} contains.
	 * </p>
	 * 
	 * @param title
	 *            The section title to be formatted.
	 * @return The string containing the formatted section title.
	 */
	public static String formatSectionTitle( String title ) {
		
		// the length of the app's section delimiter, which will also be the
		// length of the string which is the formatted version of title:
		int theLength = sectionDelimiter().length();
		
		
		// cases in which the format will be kept more simple:
		if( title.length() + 3 >= theLength || title.contains( "\n" ) )
			return "\n" + title.toUpperCase() + "\n";
		
		
		// construction of a formatted title:
		
		int numOfCharsToEachSideOfTheTitle = (theLength - title.length() - 2) / 2;
		String charsToTheLeft = sectionDelimiter().substring( 0,
				numOfCharsToEachSideOfTheTitle );
		String charsToTheRight = sectionDelimiter().substring(
				theLength - numOfCharsToEachSideOfTheTitle, theLength );
		
		StringBuilder formattedSectionTitle = new StringBuilder( charsToTheLeft )
				.append( " " ).append( title.toUpperCase() ).append( " " );
		
		// if the parity of the title length is different from the parity of
		// theLength, an extra char is printed after the space after the title
		if( (title.length() & 1) != (theLength & 1) )
			formattedSectionTitle.append( sectionDelimiter().charAt(
					theLength - numOfCharsToEachSideOfTheTitle - 1 ) );
		
		formattedSectionTitle.append( charsToTheRight );
		
		// return
		return formattedSectionTitle.toString();
		
	}
	
	
	/**
	 * Prints a formatted section title to the console (see the method
	 * {@code formatSectionTitle(String title)}).
	 * 
	 * @param title
	 *            The title to be formatted and printed.
	 */
	public static void printSectionTitle( String title ) {
		System.out.print( formatSectionTitle( title ) );
	}
	
	
	/**
	 * Returns the string with the established blank lines to appear between the
	 * sections in the output. Its presence in the output contributes to the
	 * output's readability.
	 * 
	 * @return The space between sections.
	 */
	public static String spaceBetweenSections() {
		return "\n\n";
	}
	
	
	/**
	 * Prints the sapce between sections to the console (see the method
	 * {@code spaceBetweenSections()}).
	 * 
	 */
	public static void printSpaceBetweenSections() {
		System.out.println( spaceBetweenSections() );
	}
	
	
}