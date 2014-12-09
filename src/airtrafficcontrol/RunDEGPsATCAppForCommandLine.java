package airtrafficcontrol;


import airtrafficcontrol.app.appforconsole.AirTrafficControlAppForConsole;
import airtrafficcontrol.app.appforconsole.ConsoleInputHandler;
import airtrafficcontrol.app.appforconsole.ConsoleOutputFormatter;


/**
 * DEGP's AIR TRAFFIC CONTROL app for command line.
 * 
 * <p style="font-size:16">
 * <b>Description</b>
 * </p>
 * <p>
 * Runnable class which contains the entry point of an app that performs actions
 * of air traffic control.
 * </p>
 * <p>
 * This app allows the user to manage a set of flights, inserting them into the
 * app's internal database, with the purpose of monitoring the geographic
 * coordinates of the airships and producing reports of the airships
 * transgressing the pre-established air corridors.
 * </p>
 * <p>
 * User-app interactions occur through a command line: when launching this app,
 * the user must write the commands for performing the desired operation.
 * Results of the operation will be printed.
 * </p>
 * <p >
 * For more informations read the «USER'S GUIDE of the DEGP's AIR TRAFFIC
 * CONTROL app for command line.txt» available in the «airtrafficcontrol»
 * directory.
 * </p>
 * 
 *
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 * 
 *         <p>
 *         Copyright © 2014, DEGP. All rights reserved. This code is distributed
 *         in the hope to get us good grades, but WITHOUT ANY WARRANTY.
 *         </p>
 */
public class RunDEGPsATCAppForCommandLine
{
	
	// TODO: Refazer isto:
	/**
	 * Runs an instance of {@link AirTrafficControlAppForConsole} that has:
	 * <ul>
	 * <li>the {@link AirTrafficControlAppForConsole#title title}
	 * "AIR TRAFFIC CONTROL app for console" ;
	 * <li>EHL as the developer;
	 * <li>a {@link airtrafficcontrol.app.appforconsole.OptionsMenu menu} with the
	 * {@link airtrafficcontrol.app.appforconsole.OptionsMenu#title title}
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
	 * <li>an initially empty
	 * {@link airtrafficcontrol.app.utils.towerControl.Database flights'
	 * database};</li>
	 * <li>a {@link airtrafficcontrol.app.utils.towerControl.ReportGenerator
	 * reports generator};</li>
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
	public static void main( String[] args ) {}
	
}
