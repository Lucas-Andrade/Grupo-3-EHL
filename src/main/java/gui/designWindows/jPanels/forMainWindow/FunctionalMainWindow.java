package main.java.gui.designWindows.jPanels.forMainWindow;

import main.java.domain.model.Database;
import main.java.domain.model.airships.Airship;
import main.java.domain.model.users.User;
import main.java.gui.designWindows.windows.MainWindow;

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
	
		functionalMainWindow.setFooterPanel((new FunctionalFooterPanel(
			functionalMainWindow.getFooterPanel(), airshipsDatabase, user)).getFooterPanel());
	}
	
	// Public Get Methods
	
	public MainWindow getFunctionalMainWindow() {
	
		return functionalMainWindow;
	}
}