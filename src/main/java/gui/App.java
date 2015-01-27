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
 * <li> Username: MASTER
 * <li> Password: master
 * <ul>
 * 
 * It will open the main window. At the top right corner, you see all the {@code User} functionalities.
 * At the bottom you see all the {@code Airships} functionalities.
 * 
 * User Buttons:
 * <ul>
 * <li> POST a new user
 * <li> DELETE a user - still under construction
 * <li> GET all users: it will open a new window, with a list of all posted user. Click in one to see it info.
 * <li> PATCH a user: to change your password. You can not change the MASTER password.
 * <li> Logout: close the main window and show the login window
 * <li> Turn off: close the application
 * <ul>
 * 
 * Airship
 *  
 * 
 * 
 *
 *@author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
public class App {
	
	private static Database< User > usersDatabase;
	private static Database< Airship > airshipsDatabase;
	
	
	public static void main(String[] args) throws InvalidArgumentException {
	
		usersDatabase = new InMemoryUsersDatabase("Users Database");
		airshipsDatabase = new InMemoryAirshipsDatabase("Airships Database");
		
		EventQueue.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				new FunctionalLoginWindow(new LogInWindow(), usersDatabase, airshipsDatabase);

			}
		});
	}
}
