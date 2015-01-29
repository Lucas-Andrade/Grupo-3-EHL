package main.java.gui.functionalcomponents.functionalmainwindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;

import main.java.domain.commands.getcommands.GetAllElementsInADatabaseCommand;
import main.java.domain.model.Database;
import main.java.domain.model.airships.Airship;
import main.java.domain.model.users.User;
import main.java.gui.design.panels.mainwindowpanels.JBodyPanelForMainWindow;
import main.java.gui.design.panels.mainwindowpanels.JFooterPanelForMainWindow;
import main.java.gui.design.windows.airshipwindows.PostAirshipsWindow;
import main.java.gui.design.windows.popupwindows.UnderConstrutionWindow;
import main.java.gui.functionalcomponents.FunctionalWindowSwingWorker;
import main.java.gui.functionalcomponents.functionalairshipwindows.FunctionalPostAirshipWindow;

public class FunctionalFooterPanel
{
	// Fields

	private JFooterPanelForMainWindow footerPanel;
	private JBodyPanelForMainWindow bodyPanel;

	private Database< Airship > airshipsDatabase;

	private User user;
	private JLabel errorLabel;

	// Constructor

	public FunctionalFooterPanel( JFooterPanelForMainWindow footerPanel, JBodyPanelForMainWindow bodyPanel,
			Database< Airship > airshipsDatabase, User user, JLabel errorLabel )
	{

		this.footerPanel = footerPanel;
		this.bodyPanel = bodyPanel;

		this.airshipsDatabase = airshipsDatabase;
		this.user = user;
		this.errorLabel = errorLabel;

		addGetAllAirshipsButtonAction();
		addGetNearestAirshipsButtonAction();
		addGetTransgressingAirshipsButtonAction();
		addGetAirshipsWithLessPassengerThanButtonAction();
		addPatchAirshipButtonAction();
		addPostAirshipButtonAction();
		addDeleteAirshipButtonAction();
	}

	// Private Methods

	private void addGetAllAirshipsButtonAction()
	{
		footerPanel.getShowAllAirships().addActionListener( new ActionListener()
		{
			@Override
			public void actionPerformed( ActionEvent e )
			{
				new FunctionalWindowSwingWorker<  Iterable<Airship>  >(errorLabel)
				{
					@Override
					protected Iterable< Airship > doInBackground() throws Exception
					{
						return new GetAllElementsInADatabaseCommand< Airship >(
								airshipsDatabase ).call().get();
					}
					@Override
					public void functionalDone( Iterable< Airship > resultOfDoInBackGround )
					{
						updateBodyPanel( airshipsDatabase, resultOfDoInBackGround );
					}
				}.run();
			}
		});
	}


	private void addGetNearestAirshipsButtonAction()
	{
		footerPanel.getNearestAirships().addActionListener( new ActionListener()
		{
			@Override
			public void actionPerformed( ActionEvent e )
			{
				new UnderConstrutionWindow();
			}
		} );
	}

	private void addGetTransgressingAirshipsButtonAction()
	{
		footerPanel.getTransgressingAirships().addActionListener( new ActionListener()
		{
			@Override
			public void actionPerformed( ActionEvent e )
			{
				new UnderConstrutionWindow();
			}
		} );
	}

	private void addGetAirshipsWithLessPassengerThanButtonAction()
	{
		footerPanel.getAirshipsWithLessPassengerThan().addActionListener( new ActionListener()
		{
			@Override
			public void actionPerformed( ActionEvent e )
			{
				new UnderConstrutionWindow();
			}
		} );
	}

	private void addPatchAirshipButtonAction()
	{
		footerPanel.getPatchAirship().addActionListener( new ActionListener()
		{
			@Override
			public void actionPerformed( ActionEvent e )
			{
				new UnderConstrutionWindow();
			}
		} );
	}

	private void addPostAirshipButtonAction()
	{
		footerPanel.getPostAirship().addActionListener( new ActionListener()
		{
			@Override
			public void actionPerformed( ActionEvent e )
			{
				new FunctionalPostAirshipWindow( new PostAirshipsWindow(), airshipsDatabase, user );
			}
		} );
	}

	private void addDeleteAirshipButtonAction()
	{
		footerPanel.getDeleteAirship().addActionListener( new ActionListener()
		{
			@Override
			public void actionPerformed( ActionEvent e )
			{
				new UnderConstrutionWindow();
			}
		} );
	}

	private void updateBodyPanel( Database< Airship > airshipsDatabase, Iterable< Airship > airshipsFound )
	{
		bodyPanel.setBodyPanel( airshipsDatabase, airshipsFound );
	}

	// Public Get Methods

	public JFooterPanelForMainWindow getFooterPanel()
	{
		return footerPanel;
	}
}
