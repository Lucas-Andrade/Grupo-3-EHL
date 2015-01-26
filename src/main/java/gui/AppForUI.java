package main.java.gui;

import java.awt.EventQueue;

import main.java.domain.model.Database;
import main.java.domain.model.airships.Airship;
import main.java.domain.model.airships.InMemoryAirshipsDatabase;
import main.java.domain.model.users.InMemoryUsersDatabase;
import main.java.domain.model.users.User;
import main.java.gui.designWindows.windows.LogInWindow;
import main.java.gui.functionalWindows.FunctionalLoginWindow;
import main.java.utils.exceptions.InvalidArgumentException;

public class AppForUI {
	
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
