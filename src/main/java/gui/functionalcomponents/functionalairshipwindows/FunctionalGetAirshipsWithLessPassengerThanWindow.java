package main.java.gui.functionalcomponents.functionalairshipwindows;


import javax.swing.JLabel;
import main.java.domain.commands.getcommands.GetAirshipsWithLessPassengersThanCommand;
import main.java.domain.model.Database;
import main.java.domain.model.airships.Airship;
import main.java.domain.model.airships.InMemoryAirshipsDatabase;
import main.java.gui.design.panels.mainwindowpanels.JBodyPanelForMainWindow;
import main.java.gui.design.windows.airshipwindows.GetAirshipsWithLessPassengerThanWindow;
import main.java.gui.functionalcomponents.FunctionalWindow;
import main.java.gui.functionalcomponents.FunctionalWindowSwingWorker;
import main.java.gui.functionalcomponents.functionalmainwindow.FunctionalGetWindowSwingWorker;
import main.java.utils.StringUtils;


public class FunctionalGetAirshipsWithLessPassengerThanWindow extends
        FunctionalWindow< Iterable< Airship > > {
    
    private GetAirshipsWithLessPassengerThanWindow functionalWindow;
    
    private Database< Airship > airshipsDatabase;
    
    private JBodyPanelForMainWindow bodyPanel;
    
    public FunctionalGetAirshipsWithLessPassengerThanWindow( GetAirshipsWithLessPassengerThanWindow nonFunctionalWindow,
                                                             Database< Airship > airshipsDatabase,
                                                             JBodyPanelForMainWindow bodyPanel,
                                                             JLabel errorLabel ) {
        super( nonFunctionalWindow );
        
        this.functionalWindow = nonFunctionalWindow;
        this.airshipsDatabase = airshipsDatabase;
        this.bodyPanel = bodyPanel;
    }
    
    @Override
    protected FunctionalWindowSwingWorker< Iterable< Airship >> getSwingWorker() {
        return new FunctionalGetWindowSwingWorker( airshipsDatabase, bodyPanel,
                                                   functionalWindow.getErrorLabel() ) {
            
            String a = functionalWindow.getNumberOfPassengers().getJTextField().getText();
            
            @Override
            protected Iterable< Airship > doInBackground() throws Exception {
                return new GetAirshipsWithLessPassengersThanCommand(
                                                                     (InMemoryAirshipsDatabase)airshipsDatabase,
                                                                     StringUtils.parameterToInteger( "Maximum Number of Passengers",
                                                                                                     a ) ).call()
                                                                                                          .get();
            }
            
            @Override
            public final void functionalDone( Iterable< Airship > resultOfDoInBackGround )
                throws Exception {
                super.functionalDone( resultOfDoInBackGround );
                functionalWindow.dispose();
            }
        };
        
    };
}
