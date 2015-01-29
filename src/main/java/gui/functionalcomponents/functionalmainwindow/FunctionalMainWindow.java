package main.java.gui.functionalcomponents.functionalmainwindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import main.java.domain.model.Database;
import main.java.domain.model.airships.Airship;
import main.java.domain.model.users.User;
import main.java.gui.design.panels.mainwindowpanels.JFooterPanelForMainWindow;
import main.java.gui.design.panels.mainwindowpanels.JHeaderPanelForMainWindow;
import main.java.gui.design.windows.MainWindow;
import main.java.gui.design.windows.userwindows.LogInWindow;
import main.java.gui.functionalcomponents.functionaluserwindows.FunctionalLoginWindow;

/**
 * Class whose instances have the responsibility to add the
 * {@link ActionListener}s to the given {@link MainWindow} buttons.
 *
 *@author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
public class FunctionalMainWindow {
	
	// Fields
	
	private MainWindow functionalMainWindow;
	
	private Database<User> usersDatabase;
	private Database<Airship> airshipsDatabase;
	
	private User user;
	
	
	/**
	 * Give an {@link ActionListener}s to the given {@link MainWindow}
	 * buttons. To return the functional {@code MainWindow} use the
	 * {@link FunctionalMainWindow#getFunctionalMainWindow()} method.
	 * 
	 * @param nonFunctionalMainWindow
	 * @param usersDatabase
	 * @param airshipsDatabase
	 * @param user
	 */
	public FunctionalMainWindow(MainWindow nonFunctionalMainWindow, Database<User> usersDatabase,
		Database<Airship> airshipsDatabase, User user) {
	
		this.functionalMainWindow = nonFunctionalMainWindow;
		
		this.usersDatabase = usersDatabase;
		this.airshipsDatabase = airshipsDatabase;
		this.user = user;
		
		functionalHeaderPanel();
		functionalFooterPanel();
		functionalLogOutButton();
		functionalTurnOffButton();
	}
	
	// Private  Methods
	
	/**
	 * Add the {@link JHeaderPanelForMainWindow} functionality.
	 */
	private void functionalHeaderPanel() {
	
		functionalMainWindow.setHeaderPanel((new FunctionalHeaderPanel(functionalMainWindow
			.getHeaderPanel(), usersDatabase, user)).getHeaderPanel());
	}

	/**
	 * Add the {@link JFooterPanelForMainWindow} functionality.
	 */
	private void functionalFooterPanel() {
	
		functionalMainWindow.setFooterPanel((new FunctionalFooterPanel(functionalMainWindow
			.getFooterPanel(), functionalMainWindow.getBodyPanel(), airshipsDatabase, user, functionalMainWindow.getErrorLabel() ))
			.getFooterPanel());
	}
	
	/**
	 * Log out
	 */
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
	
	/**
	 * Turn off
	 */
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

	/**
	 * @return MainWindow
	 */
	public MainWindow getFunctionalMainWindow() {
	
		return functionalMainWindow;
	}
}