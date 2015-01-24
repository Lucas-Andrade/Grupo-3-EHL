package main.java.gui;

import java.awt.EventQueue;

import main.java.domain.model.airships.InMemoryAirshipsDatabase;
import main.java.domain.model.users.InMemoryUsersDatabase;
import main.java.utils.exceptions.InvalidArgumentException;
import ByGD.windows.PostAirshipsWindow;

public class AppForUI {
	
	private static InMemoryUsersDatabase usersDatabase;
	@SuppressWarnings ("unused")
	private static InMemoryAirshipsDatabase airshipsDatabase;
	
	public static void main(String[] args) throws InvalidArgumentException {
	
		usersDatabase = new InMemoryUsersDatabase("Users Database");
		airshipsDatabase = new InMemoryAirshipsDatabase("Airships Database");
		
		EventQueue.invokeLater(new Runnable() {
			
			@Override
			public void run() {
			
//				new FunctionalLoginWindow(new LogInWindow(400, 500), usersDatabase);
//				new SuccessfulActionWindow("We are the champions!!!", Color.GREEN);
//				new FailedActionWindow("You Moron!!!", Color.RED);
//				new FunctionalPatchUserWindow(new PatchUserWindow(400, 600), usersDatabase);
				new PostAirshipsWindow(500, 700);
			}
		});
	}
}