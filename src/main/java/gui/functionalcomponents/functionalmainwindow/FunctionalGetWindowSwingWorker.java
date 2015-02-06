package main.java.gui.functionalcomponents.functionalmainwindow;


import javax.swing.JTextArea;
import javax.swing.SwingWorker;
import main.java.domain.model.Database;
import main.java.domain.model.airships.Airship;
import main.java.gui.design.panels.mainwindowpanels.JBodyPanelForMainWindow;
import main.java.gui.design.windows.MainWindow;
import main.java.gui.functionalcomponents.FunctionalWindowSwingWorker;


/**
 * Abstract class that implements the {@link FunctionalWindowSwingWorker#functionalDone(Object)
 * functionalDone(Object)} method to be used in the implementation of GET airships commands.
 * 
 * Extends {@link FunctionalWindowSwingWorker} of {@link Iterable} o {@link Airship}.
 *
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
public abstract class FunctionalGetWindowSwingWorker extends
        FunctionalWindowSwingWorker< Iterable< Airship >> {
    
    /**
     * {@code bodyPanel} - The {@link MainWindow} body panel that will be updated as part of the
     * actions performed by any of the buttons bellonging to the {@link #footerPanel}.
     */
    private JBodyPanelForMainWindow bodyPanel;
    
    /**
     * {@code airshipsDatabase} - The airships database.
     */
    private Database< Airship > airshipsDatabase;
    
    /**
     * @param airshipsDatabase
     *            - The airships database.
     * @param bodyPanel
     *            - The {@link MainWindow} body panel that will be updated as part of the actions
     *            performed by any of the buttons bellonging to the {@link MainWindow MainWindow's}
     *            footer panel.
     * @param errorTextArea
     *            - The error text area where the error messages from the thrown exceptions will be
     *            written.
     */
    public FunctionalGetWindowSwingWorker( Database< Airship > airshipsDatabase,
                                           JBodyPanelForMainWindow bodyPanel,
                                           JTextArea errorTextArea ) {
    
        super( errorTextArea );
        this.airshipsDatabase = airshipsDatabase;
        this.bodyPanel = bodyPanel;
    }
    
    /**
     * Implementation of the {@link FunctionalWindowSwingWorker#functionalDone(Object)
     * functionalDone(Object)} method. This method will receive the result of the
     * {@link SwingWorker#doInBackground() doInBackground()} method and use it to update the given
     * {link #bodyPanel}
     * 
     * Implementation decisions: The execeptions thrown by this method are caught and will be
     * treated in the {@link FunctionalWindowSwingWorker#done() done()} method.
     * 
     * @param resultOfDoInBackGround
     *            - The result of the {@link SwingWorker#doInBackground() doInBackground()} method.
     * 
     * @throws Exception
     *             Depending on the function the window its supposed to do.
     */
    @Override
    protected void functionalDone( Iterable< Airship > resultOfDoInBackGround ) throws Exception {
    
        bodyPanel.updateBodyPanel( airshipsDatabase, resultOfDoInBackGround );
    }
}
