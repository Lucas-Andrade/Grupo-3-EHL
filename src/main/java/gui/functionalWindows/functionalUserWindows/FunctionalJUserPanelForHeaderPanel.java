package main.java.gui.functionalWindows.functionalUserWindows;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import main.java.domain.commands.getcommands.GetAllElementsInADatabaseCommand;
import main.java.domain.model.Database;
import main.java.domain.model.users.User;
import main.java.gui.designWindows.jPanels.forMainWindow.JUserPanelForHeaderPanel;
import main.java.gui.designWindows.windows.popupWindows.UnderConstrutionWindow;
import main.java.gui.designWindows.windows.userWindows.GetUsersWindow;
import main.java.gui.designWindows.windows.userWindows.PatchUserWindow;
import main.java.gui.designWindows.windows.userWindows.PostUserWindow;

public class FunctionalJUserPanelForHeaderPanel
{
	private JUserPanelForHeaderPanel headerPanel;
	

	private Database< User > usersDatabase;


	private User user;

	public FunctionalJUserPanelForHeaderPanel(JUserPanelForHeaderPanel j, Database< User > usersDatabase, User user )
	{
		this.headerPanel = j;
		this.usersDatabase = usersDatabase;
		this.user = user;
		addChangePasswordButtonAction();
		addAddUserButtonAction();
		addRemoveUserButtonAction();
		addInfoAllUsersButtonAction();
	}
	
	private void addChangePasswordButtonAction()
	{
		headerPanel.getChangePasswordButton().addActionListener( new ActionListener()
		{
			@Override
			public void actionPerformed( ActionEvent e )
			{
				new FunctionalPatchUserWindow( new PatchUserWindow(), usersDatabase );
				
			}
		} );
	}
	
	

	private void addAddUserButtonAction()
	{
		headerPanel.getAddUserButton().addActionListener( new ActionListener()
		{
			@Override
			public void actionPerformed( ActionEvent e )
			{
				new FunctionalPostUserWindow( new PostUserWindow(), usersDatabase, user );
				
			}
		} );;
	}


	private void addRemoveUserButtonAction()
	{
		headerPanel.getRemoveUserButton().addActionListener( new ActionListener()
		{
			@Override
			public void actionPerformed( ActionEvent e )
			{
				new UnderConstrutionWindow();
			}
		} );;
	}
	
	private void addInfoAllUsersButtonAction()
	{
		headerPanel.getInfoAllUsersButton().addActionListener( new ActionListener()
		{
			@Override
			public void actionPerformed( ActionEvent ae )
			{
				try
				{
					new GetUsersWindow(usersDatabase, new GetAllElementsInADatabaseCommand< User >( usersDatabase ).call().get());
				}
				catch( Exception e )
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} );;
	}
	
	/**
	 * @return the headerPanel
	 */
	public JUserPanelForHeaderPanel getHeaderPanel()
	{
		return headerPanel;
	}
	

}
