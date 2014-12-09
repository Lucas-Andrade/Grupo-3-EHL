package airtrafficcontrol;


import airtrafficcontrol.app.appforconsole.AirTrafficControlAppForConsole;
import airtrafficcontrol.app.appforconsole.exceptions.InvalidArgumentException;
import airtrafficcontrol.app.appforconsole.menuoptions.AddAListOfFlightsOption;
import airtrafficcontrol.app.appforconsole.menuoptions.ConsultFlightDetailsOption;
import airtrafficcontrol.app.appforconsole.menuoptions.ExitOption;
import airtrafficcontrol.app.appforconsole.menuoptions.HelpOption_for_EHLsATCAppForConsole;
import airtrafficcontrol.app.appforconsole.menuoptions.MonitorAirTrafficOption;
import airtrafficcontrol.app.appforconsole.menuoptions.RemoveAFlightOption;
import airtrafficcontrol.app.appforconsole.menuoptions.RemoveEmptyAirshipsOption;
import airtrafficcontrol.app.appforconsole.menuoptions.ReportTransgressionsOption;
import airtrafficcontrol.app.appforconsole.menuoptions.UpdateDatabaseOption;


/**
 * EHL's AIR TRAFFIC CONTROL app for console.
 * <p style="font-size:16">
 * <b>Description</b>
 * </p>
 * <p>
 * Runnable class which contains the entry point of an app that performs actions
 * of air traffic control.
 * </p>
 * <p>
 * This app allows the user to manage a set of flights, inserting them into the
 * app's internal database either from a file or manually, with the purpose of
 * monitoring the geographic coordinates of the airships at each moment in time
 * and producing reports of the airships transgressing the pre-established air
 * corridors.
 * </p>
 * <p>
 * User-app interactions occur through a console: after launching this app, the
 * options menu is printed in the console and then the user must write the
 * number of the option to execute in the console and press Enter to proceed;
 * during execution of an option, the user may be instructed to give the app
 * more informations and feedback through the console; after an action is
 * performed, the app will print the options menu again and the user may choose
 * between performing another action or exiting the app.
 * </p>
 * <p >
 * For more informations read the «USER'S GUIDE of the EHL's AIR TRAFFIC CONTROL
 * app for console.txt» available in the «airtrafficcontrol» directory.
 * </p>
 * 
 *
 * @author Eva Gomes, Hugo Leal, Lucas Andrade
 * 
 *         <p>
 *         Copyright © 2014, EHL. All rights reserved. This code is distributed
 *         in the hope to get us good grades, but WITHOUT ANY WARRANTY.
 *         </p>
 */
public class RunEHLsATCAppForConsole
{
	
	/**
	 * Runs an {@link AirTrafficControlAppForConsole app for console} that has:
	 * <ul>
	 * <li>the {@link airtrafficcontrol.app.AirTrafficControlApp#appTitle name}
	 * {@code AIR TRAFFIC CONTROL app for console};</li>
	 * <li>the {@link airtrafficcontrol.app.AirTrafficControlApp#appDeveloper
	 * developer} {@code EHL};</li>
	 * <li>a {@link airtrafficcontrol.app.appforconsole.utils.towerControl.Database flights
	 * database},</li>
	 * <li>a {@link airtrafficcontrol.app.appforconsole.utils.towerControl.ReportGenerator
	 * reports generator},</li>
	 * <li>the
	 * {@link airtrafficcontrol.app.appforconsole.ConsoleOutputFormatter console
	 * output formatter} whose
	 * {@link airtrafficcontrol.app.appforconsole.ConsoleOutputFormatter#sectionDelimiter
	 * section delimiter} is a line with {@code 50} times the symbol '{@code -}'
	 * and whose
	 * {@link airtrafficcontrol.app.appforconsole.ConsoleOutputFormatter#spaceBetweenSections
	 * space between sections} is {@code 3} blank lines.,</li>
	 * <li>a {@link airtrafficcontrol.app.appforconsole.ConsoleInputHandler
	 * console input handler} and</li>
	 * <li>the {@link airtrafficcontrol.app.appforconsole.OptionsMenu options menu} with
	 * {@link airtrafficcontrol.app.appforconsole.OptionsMenu#menuTitle title}
	 * {@code Options Menu} and the following representation in a {@link String}:
	 * <ol>
	 * <code>
	 * OPTION MENU
	 * <li>Add a list of flights from txt file.</li>
	 * <li>Update the database's flights' coordinates.</li>
	 * <li>Monitor Air Traffic.</li>
	 * <li>Report altitude transgressions.</li>
	 * <li>Consult a flight's details.</li>
	 * <li>Remove zero-passenger-flights.</li>
	 * <li>Remove a flight manually.</li>
	 * <li>Help!</li>
	 * <li>Exit app.</li>
	 * </code></ol></li>
	 * </ul>
	 * </p>
	 */
	public static void main( String[] args ) throws InvalidArgumentException {
		
		try
		{
			new AirTrafficControlAppForConsole(
					"EHL's AIR TRAFFIC CONTROL app for console", "EHL", '-',
					50, 3, "Options Menu",
					AddAListOfFlightsOption.getInstance(),
					UpdateDatabaseOption.getInstance(),
					MonitorAirTrafficOption.getInstance(),
					ReportTransgressionsOption.getInstance(),
					ConsultFlightDetailsOption.getInstance(),
					RemoveEmptyAirshipsOption.getInstance(),
					RemoveAFlightOption.getInstance(),
					HelpOption_for_EHLsATCAppForConsole.getInstance(),
					ExitOption.getInstance() ).run();
		}
		catch( InvalidArgumentException e )
		{// never happens with these parameters
			System.out.println( e.getMessage() );
		}
	}
}