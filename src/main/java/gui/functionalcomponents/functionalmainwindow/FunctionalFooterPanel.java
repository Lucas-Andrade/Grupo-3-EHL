package main.java.gui.functionalcomponents.functionalmainwindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;

import main.java.domain.commands.getcommands.GetAllElementsInADatabaseCommand;
import main.java.domain.commands.getcommands.GetAllTransgressingAirshipsCommand;
import main.java.domain.model.Database;
import main.java.domain.model.airships.Airship;
import main.java.domain.model.airships.InMemoryAirshipsDatabase;
import main.java.domain.model.users.User;
import main.java.gui.design.panels.mainwindowpanels.JBodyPanelForMainWindow;
import main.java.gui.design.panels.mainwindowpanels.JFooterPanelForMainWindow;
import main.java.gui.design.windows.airshipwindows.GetAirshipsWithLessPassengerThanWindow;
import main.java.gui.design.windows.airshipwindows.GetGeographicalCoordinatesParametersWindow;
import main.java.gui.design.windows.airshipwindows.PostAirshipsWindow;
import main.java.gui.design.windows.popupwindows.UnderConstrutionWindow;
import main.java.gui.functionalcomponents.FunctionalGetSwingWorker;
import main.java.gui.functionalcomponents.functionalairshipwindows.FunctionalGetAirshipsWithLessPassengerThanWindow;
import main.java.gui.functionalcomponents.functionalairshipwindows.FunctionalGetGeographicalCoordinatesParametersWindow;
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
				new FunctionalGetSwingWorker( airshipsDatabase, bodyPanel, errorLabel )
				{
					@Override
					protected Iterable< Airship > doInBackground() throws Exception
					{
						return new GetAllElementsInADatabaseCommand< Airship >( airshipsDatabase ).call()
																									.get();
					}
				}.run();
			}

		} );
	}


	private void addGetNearestAirshipsButtonAction()
	{
		footerPanel.getNearestAirships().addActionListener( new ActionListener()
		{
			@Override
			public void actionPerformed( ActionEvent e )
			{
				new FunctionalGetGeographicalCoordinatesParametersWindow(
						new GetGeographicalCoordinatesParametersWindow(), airshipsDatabase, bodyPanel,
						errorLabel );
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
				new FunctionalGetSwingWorker( airshipsDatabase, bodyPanel, errorLabel )
				{
					@Override
					protected Iterable< Airship > doInBackground() throws Exception
					{
						return new GetAllTransgressingAirshipsCommand(
								( InMemoryAirshipsDatabase )airshipsDatabase ).call().get();
					}
				}.run();
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
				new FunctionalGetAirshipsWithLessPassengerThanWindow(
						new GetAirshipsWithLessPassengerThanWindow(), airshipsDatabase, bodyPanel, errorLabel );
			}
		} );
	}

	/**
	 * PATCH
	 */
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

	/**
	 * POST
	 */
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

	/**
	 * DELETE
	 */
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

	public JFooterPanelForMainWindow getFooterPanel()
	{
		return footerPanel;
	}
}
