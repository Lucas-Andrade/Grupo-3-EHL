package main.java.gui.design.windows;


import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import main.java.domain.model.Database;
import main.java.domain.model.airships.Airship;
import main.java.gui.design.GridBagUtils;
import main.java.gui.design.panels.mainwindowpanels.JBodyPanelForMainWindow;
import main.java.gui.design.panels.mainwindowpanels.JFooterPanelForMainWindow;
import main.java.gui.design.panels.mainwindowpanels.JHeaderPanelForMainWindow;


@SuppressWarnings( "serial" )
public class MainWindow extends JFrame {
    
    private static final int REDCOMPONENT = 65;
    private static final int GREENCOMPONENT = 72;
    private static final int BLUECOMPONENT = 78;
    private static final int GRIDYFORBODYPANEL = 1;
    private static final int GRIDYFORFOOTERPANEL = 2;
    private static final int GRIDYFORCONTENTPANE = 3;
    
    // Fields
    
    private GridBagConstraints constraints = GridBagUtils.createGridBagConstraints();
    
    private JHeaderPanelForMainWindow headerPanel;
    private JBodyPanelForMainWindow bodyPanel;
    private JFooterPanelForMainWindow footerPanel;
    private JTextArea errorJTextArea;
    
    // Constructor
    
    public MainWindow( Database< Airship > airshipsDatabase, Iterable< Airship > airshipsFound ) {
        
        this.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        this.setIconImage( Toolkit.getDefaultToolkit().getImage( "/images/radar.png" ) );
        this.setTitle( "Air Traffic Control" );
        
        JPanel contentPane = new JPanel();
        
        contentPane.setBackground( new Color( REDCOMPONENT, GREENCOMPONENT, BLUECOMPONENT ) );
        getContentPane().add( contentPane );
        
        contentPane.setLayout( new GridBagLayout() );
        
        headerPanel = new JHeaderPanelForMainWindow();
        
        contentPane.add( headerPanel, constraints );
        
        contentPane.add( headerPanel, constraints );
        
        
        bodyPanel = new JBodyPanelForMainWindow( airshipsDatabase, airshipsFound );
        contentPane.add( bodyPanel, GridBagUtils.updateGridBagConstraints( constraints, GRIDYFORBODYPANEL ) );
        
        footerPanel = new JFooterPanelForMainWindow();
        contentPane.add( footerPanel, GridBagUtils.updateGridBagConstraints( constraints, GRIDYFORFOOTERPANEL ) );
        
        errorJTextArea = new JTextArea( " " );
        errorJTextArea.setForeground( Color.RED );
        errorJTextArea.setBackground(new Color( REDCOMPONENT, GREENCOMPONENT, BLUECOMPONENT ) );
        errorJTextArea.setEditable( false );
        
        contentPane.add( errorJTextArea, GridBagUtils.updateGridBagConstraints( constraints, GRIDYFORCONTENTPANE ) );
        
        pack();
        setLocationRelativeTo( null );
        setResizable( false );
        setVisible( true );
    }
    
    // Public Set Methods
    
    public void setHeaderPanel( JHeaderPanelForMainWindow headerPanel ) {
        
        this.headerPanel = headerPanel;
    }
    
    public void setFooterPanel( JFooterPanelForMainWindow footerPanel ) {
        
        this.footerPanel = footerPanel;
    }
    
    public void setBodyPanel( JBodyPanelForMainWindow bodyPanel ) {
        
        this.bodyPanel = bodyPanel;
    }
    
    
    // Public Get Methods
    
    public JHeaderPanelForMainWindow getHeaderPanel() {
        
        return headerPanel;
    }
    
    public JBodyPanelForMainWindow getBodyPanel() {
        
        return bodyPanel;
    }
    
    public JFooterPanelForMainWindow getFooterPanel() {
        
        return footerPanel;
    }
    
    /**
     * @return the errorJTextArea
     */
    public JTextArea getErrorJTextArea() {
        return errorJTextArea;
    }
}
