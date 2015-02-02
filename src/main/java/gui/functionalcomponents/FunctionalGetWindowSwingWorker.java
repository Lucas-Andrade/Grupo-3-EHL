package main.java.gui.functionalcomponents;


import javax.swing.JLabel;
import main.java.domain.model.Database;
import main.java.domain.model.airships.Airship;
import main.java.gui.design.panels.mainwindowpanels.JBodyPanelForMainWindow;


/**
 * Abstract SwingWorker Class that {@code Override} the {@code done()} method, where the
 * {@link Exception}s thrown by the {@link SwingWorker#doInBackground()} method are caught and write
 * on the {@code errorLabel}.
 */


/**
 * Abstract SwingWorker Class that for all GET airships {@code Override} the {@code done()}
 * 
 *
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
public abstract class FunctionalGetWindowSwingWorker extends
        FunctionalWindowSwingWorker< Iterable< Airship >> {
    
    private Database< Airship > airshipsDatabase;
    private JBodyPanelForMainWindow bodyPanel;
    
    public FunctionalGetWindowSwingWorker( Database< Airship > airshipsDatabase,
                                           JBodyPanelForMainWindow bodyPanel, JLabel errorLabel ) {
        super( errorLabel );
        this.airshipsDatabase = airshipsDatabase;
        this.bodyPanel = bodyPanel;
    }
    
    @Override
    public void functionalDone( Iterable< Airship > resultOfDoInBackGround ) throws Exception {
        bodyPanel.updateBodyPanel( airshipsDatabase, resultOfDoInBackGround );
    }
}
