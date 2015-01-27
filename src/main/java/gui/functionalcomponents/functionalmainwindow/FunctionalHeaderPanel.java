package main.java.gui.functionalcomponents.functionalmainwindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import main.java.domain.commands.getcommands.GetAllElementsInADatabaseCommand;
import main.java.domain.model.Database;
import main.java.domain.model.users.User;
import main.java.gui.design.panels.mainwindowpanels.JHeaderPanelForMainWindow;
import main.java.gui.design.windows.popupwindows.UnderConstrutionWindow;
import main.java.gui.design.windows.userwindows.GetUsersWindow;
import main.java.gui.design.windows.userwindows.PatchUserWindow;
import main.java.gui.design.windows.userwindows.PostUserWindow;
import main.java.gui.functionalcomponents.functionaluserwindows.FunctionalPatchUserWindow;
import main.java.gui.functionalcomponents.functionaluserwindows.FunctionalPostUserWindow;

public class FunctionalHeaderPanel {
	
	// Field
	
	private JHeaderPanelForMainWindow headerPanel;
	
	private Database<User> usersDatabase;
	
	private User user;
	
	// Constructor
	
	public FunctionalHeaderPanel(JHeaderPanelForMainWindow headerPanel,
		Database<User> usersDatabase, User user) {
	
		this.headerPanel = headerPanel;
		
		this.usersDatabase = usersDatabase;
		
		this.user = user;
		
		addChangePasswordButtonAction();
		addAddUserButtonAction();
		addRemoveUserButtonAction();
		addInfoAllUsersButtonAction();
	}
	
	// Private Auxiliar Method
	
	private void addChangePasswordButtonAction() {
	
		headerPanel.getUserPanel().getChangePasswordButton()
			.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
				
					new FunctionalPatchUserWindow(new PatchUserWindow(), usersDatabase);
					
				}
			});
	}
	
	private void addAddUserButtonAction() {
	
		headerPanel.getUserPanel().getAddUserButton().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
			
				new FunctionalPostUserWindow(new PostUserWindow(), usersDatabase, user);
				
			}
		});
	}
	
	private void addRemoveUserButtonAction() {
	
		headerPanel.getUserPanel().getRemoveUserButton().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
			
				new UnderConstrutionWindow();
			}
		});
	}
	
	private void addInfoAllUsersButtonAction() {
	
		headerPanel.getUserPanel().getInfoAllUsersButton().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent ae) {
			
				try {
					new GetUsersWindow(usersDatabase, new GetAllElementsInADatabaseCommand<User>(
						usersDatabase).call().get());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
	}
	
	// Public Get Methods
	
	/**
	 * @return the headerPanel
	 */
	public JHeaderPanelForMainWindow getHeaderPanel() {
	
		return headerPanel;
	}
}