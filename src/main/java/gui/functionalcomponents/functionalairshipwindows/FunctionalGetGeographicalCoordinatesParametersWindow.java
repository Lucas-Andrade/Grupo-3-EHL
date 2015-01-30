package main.java.gui.functionalcomponents.functionalairshipwindows;

import javax.swing.JLabel;

import main.java.domain.commands.getcommands.GetTheNearestAirshipsToGeographicPositionCommand;
import main.java.domain.model.Database;
import main.java.domain.model.airships.Airship;
import main.java.domain.model.airships.InMemoryAirshipsDatabase;
import main.java.gui.design.panels.mainwindowpanels.JBodyPanelForMainWindow;
import main.java.gui.design.windows.airshipwindows.GetGeographicalCoordinatesParametersWindow;
import main.java.gui.functionalcomponents.FunctionalGetWindowSwingWorker;
import main.java.gui.functionalcomponents.FunctionalWindow;
import main.java.gui.functionalcomponents.FunctionalWindowSwingWorker;
import main.java.utils.StringUtils;

public class FunctionalGetGeographicalCoordinatesParametersWindow
	extends FunctionalWindow< Iterable< Airship > >
{
	private GetGeographicalCoordinatesParametersWindow functionalWindow;
	private Database< Airship > airshipsDatabase;
	private JBodyPanelForMainWindow bodyPanel;

	public FunctionalGetGeographicalCoordinatesParametersWindow(
			GetGeographicalCoordinatesParametersWindow nonFunctionalWindow, Database< Airship > airshipsDatabase,
			JBodyPanelForMainWindow bodyPanel, JLabel errorLabel )
	{
		super( nonFunctionalWindow );

		this.functionalWindow = nonFunctionalWindow;
		this.airshipsDatabase = airshipsDatabase;
		this.bodyPanel = bodyPanel;
	}

	@Override
	protected FunctionalWindowSwingWorker< Iterable< Airship >> getSwingWorker()
	{
		return new FunctionalGetWindowSwingWorker( airshipsDatabase, bodyPanel, functionalWindow.getErrorLabel() )
		{
			String latitude = functionalWindow.getLatitude().getJTextField().getText();
			String longitude = functionalWindow.getLongitude().getJTextField().getText();
			String airshipsNumber = functionalWindow.getAirshipsNumber().getJTextField().getText();

			@Override
			protected Iterable< Airship > doInBackground() throws Exception
			{
				return new GetTheNearestAirshipsToGeographicPositionCommand(  (InMemoryAirshipsDatabase)airshipsDatabase,
						StringUtils.parameterToInteger( "parameterName", airshipsNumber ), StringUtils.parameterToDouble(
                                     								"Maximum Number of Passengers", latitude ),
                                     								StringUtils.parameterToDouble(
                                             								"Maximum Number of Passengers", longitude )).call().get();
			}

			@Override
			public final void functionalDone( Iterable< Airship > resultOfDoInBackGround ) throws Exception
			{
				super.functionalDone( resultOfDoInBackGround );
				functionalWindow.dispose();
			}
		};

	};
}
