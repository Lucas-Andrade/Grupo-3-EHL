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
 * the airships transgressing the pre-established air corridors.
 * </p>
 * 
 * <p >
 * For more informations read the «USER'S GUIDE.txt» available in the
 * «airtrafficcontrol» directory.
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
	 * <li>Update the database's flights' coordinates.</li>
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
	 * a line filled with a sequence of {@code 50} repetitions of the symbol '
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
				'-', 50, 3, AddAListOfFlightsOption.getInstance(),
				UpdateDatabaseOption.getInstance(),
				MonitorAirTrafficOption.getInstance(),
				ReportTransgressionsOption.getInstance(),
				ConsultFlightDetailsOption.getInstance(),
				RemoveEmptyAirshipsOption.getInstance(),
				RemoveAFlightOption.getInstance(),
				HelpOption_for_EHLsATCAppForConsole.getInstance(),
				ExitOption.getInstance() ).run();
	}
	
}