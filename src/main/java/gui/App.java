package main.java.gui;

import java.awt.EventQueue;

import main.java.domain.model.Database;
import main.java.domain.model.airships.Airship;
import main.java.domain.model.airships.InMemoryAirshipsDatabase;
import main.java.domain.model.users.InMemoryUsersDatabase;
import main.java.domain.model.users.User;
import main.java.gui.design.windows.userwindows.LogInWindow;
import main.java.gui.functionalcomponents.functionaluserwindows.FunctionalLoginWindow;
import main.java.utils.exceptions.InvalidArgumentException;

/**
 * User Interface for AirTrafficControl - Swing
 * 
 * How to use, first login user:
 * <ul>
 * <li>Username: MASTER
 * <li>Password: master
 * </ul>
 * 
 * It will open the main window. At the top right corner, you see all the
 * {@code User} functionalities. At the bottom you see all the {@code Airships}
 * functionalities.
 * 
 * User Buttons:
 * <ul>
 * <li>POST a new user
 * <li>GET all users: it will open a new window, with a list of all posted user.
 * Click in one to see it info.
 * <li>PATCH a user: to change your password. You can not change the MASTER
 * password.
 * <li>Logout: close the main window and show the login window
 * <li>Turn off: close the application
 * <li>DELETE a user: still under construction
 * </ul>
 * 
 * Airship Buttons:
 * <ul>
 * <li>POST a new airship
 * <li>GET all airships: not working, we don't know why :(, to see all posted airships logout and login again.
 * It will appear the posted airships in the world image, and a list at the right. To see an Airship info click on one. 
 * <li>Under Construction:
 * <ul>
 * <li>Airships Closest to Coordinates
 * <li>Get Transgressing Airships
 * <li>Get Airships with less Passengers
 * <li>Change Airship
 * <li>Delete Airship
 * </ul>
 * 
 * In the next version you can choose your favorite Chuck Norris picture.
 * 
 * 
 *
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
public class App
{
	public static void main( String[] args ) throws InvalidArgumentException
	{
		Database< User > usersDatabase = new InMemoryUsersDatabase( "Users Database" );
		Database< Airship > airshipsDatabase = new InMemoryAirshipsDatabase( "Airships Database" );

		EventQueue.invokeLater( new Runnable()
		{
			@Override
			public void run()
			{
				new FunctionalLoginWindow( new LogInWindow(), usersDatabase, airshipsDatabase );
			}
		} );
	}
}
