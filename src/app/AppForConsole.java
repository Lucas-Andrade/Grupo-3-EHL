package app;


import utils.Database;


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
public class AppForConsole extends App
{
	
	// CAMPOS DA CLASSE
	
	
	/**
	 * An instance of AppForConsole. The instance used in the entry point of
	 * this class.
	 */
	public static final App appC = new AppForConsole();
	
	
	/**
	 * The style formatting set up for this app's output.
	 */
	public static final ConsoleOutputFormatter STYLIZER = new ConsoleOutputFormatter(
			'-', 45, 4 );
	
	
	/**
	 * The flights data base of this app.
	 */
	public static final Database flightsDB = new Database();
	
	
	// CONSTRUTOR
	
	/**
	 * Creates an instance of AppForConsole whose {@link App#MAINMENU MAINMENU}
	 * has {@link OptionsMenu#title title} "Options Menu" and whose
	 * representation in a String is
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
	 */
	public AppForConsole() {
		super( "Options Menu", AddAListOfFlightsOption.getInstance(),
				AddAFlightOption.getInstance(), MonitorAirTrafficOption
						.getInstance(), ReportTransgressionsOption
						.getInstance(), ConsultFlightDetailsOption
						.getInstance(),
				RemoveEmptyAirshipsOption.getInstance(), RemoveAFlightOption
						.getInstance(), ConfigurationsOption.getInstance(),
				HelpOption.getInstance(), ExitOption.getInstance() );
	}
	
	
	
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
			
			printBeginningOfSection( appC.MAINMENU.menuTitle );
			System.out.print( appC.MAINMENU.inAString() );
			STYLIZER.printSectionDelimiter();
			String instruction = new StringBuilder(
					" Type the number of the option you want to" )
					.append( "\n perform and press Enter." )
					.append( "\n\nExecute option: " ).toString();
			int selectedOption = ConsoleInputTreatment.getAValidIntFromUser( 1,
					appC.MAINMENU.numberOfOptions, instruction );
			printEndOfSection();
			
			// EXECUÇAO DE UMA OPÇAO
			try
			{
				printBeginningOfSection( appC.MAINMENU
						.getOptionTitle( selectedOption ) );
				runApp = !appC.MAINMENU.executeOptionToConsole( selectedOption );
			}
			catch( InvalidOptionNumberException e )
			{
				System.out.println( "INVALID OPTION NUMBER!" );
			}
			// catch( InvalidOptionException e )
			// {System.out.println("INVALID OPTION!");}
			printEndOfSection();
			
		}
		while( runApp );
		
		
		// FINAL
		
		System.out.print( "\nCopyright (c) 2014, EHL. All rights reserved.\n" );
		STYLIZER.printSectionTitle( "end" );
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