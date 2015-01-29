package main.java.gui.functionalcomponents;

import javax.swing.JLabel;

import main.java.domain.model.Database;
import main.java.domain.model.airships.Airship;
import main.java.gui.design.panels.mainwindowpanels.JBodyPanelForMainWindow;

public abstract class FunctionalGetSwingWorker
	extends FunctionalWindowSwingWorker <Iterable< Airship >>
{
	private Database< Airship > airshipsDatabase;
	private JBodyPanelForMainWindow bodyPanel;

	public FunctionalGetSwingWorker( Database< Airship > airshipsDatabase, JBodyPanelForMainWindow bodyPanel, JLabel errorLabel )
	{
		super( errorLabel );
		this.airshipsDatabase = airshipsDatabase;
		this.bodyPanel = bodyPanel;
	}

	@Override
	public void functionalDone( Iterable< Airship > resultOfDoInBackGround ) throws Exception
	{
		bodyPanel.updateBodyPanel( airshipsDatabase, resultOfDoInBackGround );
	}
}
