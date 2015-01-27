package main.java.gui.functionalWindows.functionalMainWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import main.java.domain.commands.getcommands.GetAllElementsInADatabaseCommand;
import main.java.domain.model.Database;
import main.java.domain.model.airships.Airship;
import main.java.domain.model.users.User;
import main.java.gui.designWindows.jPanels.forMainWindow.JBodyPanelForMainWindow;
import main.java.gui.designWindows.windows.MainWindow;
import main.java.gui.designWindows.windows.userWindows.LogInWindow;
import main.java.gui.functionalWindows.functionalUserWindows.FunctionalLoginWindow;
import main.java.utils.exceptions.InternalErrorException;

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
		funtionalShowAllAirshipsButtons();
		addGetAllAirshipsButtonAction();
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
			.getFooterPanel(), functionalMainWindow.getBodyPanel(), airshipsDatabase, user))
			.getFooterPanel());
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
	
	
	
	private void addGetAllAirshipsButtonAction() {
		
		functionalMainWindow.getFooterPanel().getShowAllAirships().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent click) {
			
				try {
//					updateBodyPanel(airshipsDatabase, new GetAllElementsInADatabaseCommand<Airship>(
//						airshipsDatabase).call().get());
					updateBodyPanel(airshipsDatabase, airshipsDatabase.getAllElements().get());
					
				} catch (Exception exception) {
					throw new InternalErrorException(exception);
				}
			}
		});
	}
	
	
	private void updateBodyPanel(Database<Airship> airshipsDatabase, Iterable<Airship> airshipsFound) throws Exception {
		
//		functionalMainWindow.dispose();
//		new FunctionalMainWindow(new MainWindow(airshipsDatabase, airshipsDatabase.getAllElements()
//				.get()), usersDatabase, airshipsDatabase, user);
		functionalMainWindow.setBodyPanel( new JBodyPanelForMainWindow( airshipsDatabase, airshipsFound ));
		functionalMainWindow.getBodyPanel().setWorldMapWithAirships(airshipsFound);
		functionalMainWindow.revalidate();
		functionalMainWindow.repaint();
		functionalMainWindow.getBodyPanel().getWorldMapWithAirships().repaint();
	}
	
	
	
	
	private void funtionalShowAllAirshipsButtons() {
	
		// TODO Auto-generated method stub
	}
	
	// Public Get Methods
	
	public MainWindow getFunctionalMainWindow() {
	
		return functionalMainWindow;
	}
	
	
	
}