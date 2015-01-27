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
import main.java.utils.exceptions.InternalErrorException;


/**
 * Class whose instances have the responsibility to add the
 * {@link ActionListener}s to the given {@link JHeaderPanelForMainWindow}
 * buttons, i.e., All {@link User}s commands: POST, GET, PATCH, DELETE
 * 
 *
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
public class FunctionalHeaderPanel
{

	// Field

	private JHeaderPanelForMainWindow headerPanel;
	private Database< User > usersDatabase;
	private User user;

	// Constructor

	/**
	 * Give an {@link ActionListener}s to the given
	 * {@link JHeaderPanelForMainWindow} buttons. To return the functional
	 * {@code FunctionalHeaderPanel} use the
	 * {@link FunctionalHeaderPanel#getHeaderPanel()} method.
	 * 
	 * @param headerPanel
	 * @param usersDatabase
	 * @param user
	 */
	public FunctionalHeaderPanel( JHeaderPanelForMainWindow headerPanel, Database< User > usersDatabase,
			User user )
	{
		this.headerPanel = headerPanel;
		this.usersDatabase = usersDatabase;
		this.user = user;

		addAddUserButtonAction();
		addChangePasswordButtonAction();
		addInfoAllUsersButtonAction();
		addRemoveUserButtonAction();
	}

	// Private Method

	/**
	 * POST
	 */
	private void addAddUserButtonAction()
	{

		headerPanel.getUserPanel().getAddUserButton().addActionListener( new ActionListener()
		{

			@Override
			public void actionPerformed( ActionEvent e )
			{
				new FunctionalPostUserWindow( new PostUserWindow(), usersDatabase, user );
			}
		} );
	}

	/**
	 * PATCH
	 */
	private void addChangePasswordButtonAction()
	{
		headerPanel.getUserPanel().getChangePasswordButton().addActionListener( new ActionListener()
		{
			@Override
			public void actionPerformed( ActionEvent e )
			{
				new FunctionalPatchUserWindow( new PatchUserWindow(), usersDatabase );
			}
		} );
	}



	/**
	 * DELETE
	 */
	private void addRemoveUserButtonAction()
	{
		headerPanel.getUserPanel().getRemoveUserButton().addActionListener( new ActionListener()
		{
			@Override
			public void actionPerformed( ActionEvent e )
			{
				new UnderConstrutionWindow();
			}
		} );
	}

	/**
	 * GET all
	 */
	private void addInfoAllUsersButtonAction()
	{
		headerPanel.getUserPanel().getInfoAllUsersButton().addActionListener( new ActionListener()
		{
			@Override
			public void actionPerformed( ActionEvent ae )
			{
				try
				{
					new GetUsersWindow( usersDatabase, new GetAllElementsInADatabaseCommand< User >(
							usersDatabase ).call().get() );
				}
				catch( Exception e )
				{
					throw new InternalErrorException(e);
				}
			}
		} );
	}

	// Public Get Methods

	/**
	 * @return the headerPanel
	 */
	public JHeaderPanelForMainWindow getHeaderPanel()
	{
		return headerPanel;
	}
}
