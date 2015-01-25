package main.java.gui;

import java.awt.Color;
import java.awt.EventQueue;

import main.java.domain.model.airships.InMemoryAirshipsDatabase;
import main.java.domain.model.users.InMemoryUsersDatabase;
import main.java.gui.To_be_eliminated.windows.LogInWindow;
import main.java.gui.functionalWindows.FunctionalLoginWindow;
import main.java.utils.exceptions.InvalidArgumentException;

public class AppForUI {
	
	private static InMemoryUsersDatabase usersDatabase;
	private static InMemoryAirshipsDatabase airshipsDatabase;
	
	
	public static void main(String[] args) throws InvalidArgumentException {
	
		usersDatabase = new InMemoryUsersDatabase("Users Database");
		airshipsDatabase = new InMemoryAirshipsDatabase("Airships Database");
		
		EventQueue.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				new FunctionalLoginWindow(new LogInWindow(360, 370), usersDatabase, airshipsDatabase);

			}
		});
	}
}
