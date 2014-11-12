package airtrafficcontrol;


import airtrafficcontrol.app.appforconsole.AirTrafficControlAppForConsole;
import airtrafficcontrol.app.exceptions.*;
import airtrafficcontrol.app.menuoptions.*;


/**
 * EHL's AIR TRAFFIC CONTROL app for console. Executable file.
 * <p style="font-size:16">
 * <b>Description</b>
 * </p>
 * <p>
 * Runnable class to perform actions of air traffic control. </br>This app
 * allows the user to manage a set of flights, inserting them into the app
 * either from a file or manually, with the purpose of monitoring the geographic
 * coordinates of the airships at each moment in time and producing reports of
 * the airships transgressing the pre-established air corridors.//TODO
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
public class RunEHLsATCAppForConsole
{
	
	/**
	 * Runs an instance of {@link AirTrafficControlAppForConsole} that has:
	 * <ul>
	 * <li>the {@link AirTrafficControlAppForConsole#title title}
	 * "AIR TRAFFIC CONTROL app for console" ;
	 * <li>EHL as the developer;
	 * <li>a {@link airtrafficcontrol.app.OptionsMenu menu} with the
	 * {@link airtrafficcontrol.app.OptionsMenu#title title}
	 * {@code Options Menu} and the following representation in a String: //TODO
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
	 * <li>an initially empty {@link airtrafficcontrol.app.utils.Database
	 * flights' database};</li>
	 * <li>a {@link airtrafficcontrol.app.utils.ReportGenerator reports
	 * generator};</li>
	 * <li>a {@link ConsoleOutputFormatter console output formatter} that draws
	 * a line filled with a sequence of {@code 45} repetitions of the symbol '
	 * {@code -}' as a {@link ConsoleOutputFormatter#sectionDelimiter section
	 * delimiter} and prints {@code 3} blank lines of
	 * {@link ConsoleOutputFormatter#spaceBetweenSections space between
	 * sections}</li>
	 * <li>a {@link ConsoleInputHandler console input handler}.</li>
	 * </ul>
	 * </p>
	 */
	public static void main( String[] args ) throws InvalidArgumentException {
		
		new AirTrafficControlAppForConsole(
				"AIR TRAFFIC CONTROL app for console", "EHL", "Options Menu",
				'-', 45, 3, AddAListOfFlightsOption.getInstance(),
				UpdateDatabaseOption.getInstance(),
				MonitorAirTrafficOption.getInstance(),
				ReportTransgressionsOption.getInstance(),
				ConsultFlightDetailsOption.getInstance(),
				RemoveEmptyAirshipsOption.getInstance(),
				RemoveAFlightOption.getInstance(),
				ConfigurationsOption.getInstance(),
				HelpOption_for_EHLsATCAppForConsole.getInstance(),
				ExitOption_for_EHLsATCAppForConsole.getInstance() ).run();
	}
	
}