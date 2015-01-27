package main.java.gui.designWindows.jPanels.forMainWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import main.java.domain.model.Database;
import main.java.domain.model.users.User;
import main.java.gui.designWindows.windows.popupWindows.UnderConstrutionWindow;
import main.java.gui.designWindows.windows.userWindows.PatchUserWindow;
import main.java.gui.designWindows.windows.userWindows.PostUserWindow;
import main.java.gui.functionalWindows.functionalUserWindows.FunctionalPatchUserWindow;
import main.java.gui.functionalWindows.functionalUserWindows.FunctionalPostUserWindow;

public class FunctionalHeaderPanel {
	
	// Field
	
	private JHeaderForMainWindowPanel headerPanel;
	
	private Database<User> usersDatabase;
	
	private User user;
	
	// Constructor
	
	public FunctionalHeaderPanel(JHeaderForMainWindowPanel headerPanel,
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
			public void actionPerformed(ActionEvent e) {
			
				new UnderConstrutionWindow();
			}
		});
	}
	
	// Public Get Methods
	
	/**
	 * @return the headerPanel
	 */
	public JHeaderForMainWindowPanel getHeaderPanel() {
	
		return headerPanel;
	}
}