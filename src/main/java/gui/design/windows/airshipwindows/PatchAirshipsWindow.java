package main.java.gui.design.windows.airshipwindows;


import java.awt.GridBagConstraints;
import main.java.gui.design.GridBagUtils;
import main.java.gui.design.panels.postairshipwindowpanels.JAirCorridorPanel;
import main.java.gui.design.panels.postairshipwindowpanels.JGeographicalCoordinatesPanel;
import main.java.gui.design.windows.WindowBase;


@SuppressWarnings( "serial" )
public class PatchAirshipsWindow extends WindowBase {
    
    
    private static final int WINDOWWIDTH = 400;
    private static final int WINDOWHEIGHT = 400;
    private static final int GRIDYFORGEOPANEL = 1;
    private static final int GRIDYFORCORRIDORPANEL = 2;
    
    
    private JGeographicalCoordinatesPanel geoPanel;
    private GridBagConstraints constraints = GridBagUtils.createGridBagConstraints();
    private JAirCorridorPanel corridorPanel;
    
    public PatchAirshipsWindow() {
        super( WINDOWWIDTH, WINDOWHEIGHT, "/images/edit.png", "src/main/resources/images/civil.png" );
        
        initial();
    }
    
    private void initial() {
        
        geoPanel = new JGeographicalCoordinatesPanel();
        this.getContentPane()
            .add( geoPanel, GridBagUtils.updateGridBagConstraints( constraints, GRIDYFORGEOPANEL ) );
        corridorPanel = new JAirCorridorPanel();
        this.getContentPane().add( corridorPanel,
                                   GridBagUtils.updateGridBagConstraints( constraints, GRIDYFORCORRIDORPANEL ) );
        this.setVisible( true );
        
    }
    
    public JGeographicalCoordinatesPanel getGeoPanel() {
        return geoPanel;
    }
    
}
