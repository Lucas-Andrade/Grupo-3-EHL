package main.java.gui.designWindows.jPanels.forMainWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import main.java.domain.model.Database;
import main.java.domain.model.airships.Airship;
import main.java.domain.model.users.User;
import main.java.gui.designWindows.windows.MainWindow;
import main.java.gui.designWindows.windows.userWindows.LogInWindow;
import main.java.gui.functionalWindows.functionalUserWindows.FunctionalLoginWindow;

public class FunctionalMainWindow {
	
	// Fields
	
	private MainWindow functionalMainWindow;
	
	private Database<User> usersDatabase;
	private Database<Airship> airshipsDatabase;
	
	private User user;
	
	// Contructors
	
	public FunctionalMainWindow(MainWindow nonFunctionalMainWindow, Database<User> usersDatabase,
		Database<Airship> airshipsDatabase, User user) {
	
		this.functionalMainWindow = nonFunctionalMainWindow;
		
		this.usersDatabase = usersDatabase;
		this.airshipsDatabase = airshipsDatabase;
		this.user = user;
		
		functionalHeaderPanel();
		functonalBodyPanel();
		functionalFooterPanel();
		functionalLogOutButton();
		functionalTurnOffButton();
	}
	
	// Private Auxiliar Methods
	
	private void functionalHeaderPanel() {
	
		functionalMainWindow.setHeaderPanel((new FunctionalHeaderPanel(functionalMainWindow
			.getHeaderPanel(), usersDatabase, user)).getHeaderPanel());
	}
	
	private void functonalBodyPanel() {
	
		// TODO Auto-generated method stub
	}
	
	private void functionalFooterPanel() {
	
		functionalMainWindow.setFooterPanel((new FunctionalFooterPanel(functionalMainWindow
			.getFooterPanel(), airshipsDatabase, user)).getFooterPanel());
	}
	
	private void functionalLogOutButton() {
	
		functionalMainWindow.getHeaderPanel().getUserPanel().getLogoutButton()
			.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
				
					new FunctionalLoginWindow(new LogInWindow(), usersDatabase, airshipsDatabase);
					functionalMainWindow.dispose();
				}
			});
	}
	
	private void functionalTurnOffButton() {
	
		functionalMainWindow.getHeaderPanel().getUserPanel().getTurnOffButton()
			.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
				
					functionalMainWindow.dispose();
				}
			});
	}
	
	// Public Get Methods
	
	public MainWindow getFunctionalMainWindow() {
	
		return functionalMainWindow;
	}
}