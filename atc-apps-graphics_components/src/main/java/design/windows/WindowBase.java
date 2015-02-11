package design.windows;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.util.concurrent.Callable;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JTextArea;
import design.GridBagUtils;
import design.panels.JPanelImage;
import design.panels.JTwoButtonsPanel;



/**
 * Abstract swing window, with:
 * <ul>
 * <li>an Image
 * <li>two buttons, without {@link ActionListener}s
 * <li>error textarea, where the exception message will be printed.
 * <ul>
 * 
 * This window has this configuration:
 *  <pre>
 *     ____________________________________
 *    |                                    |
 *    |               Image                |
 *    |____________________________________|
 *    |                                    |
 *    |           Another Components       |
 *    |                  .                 |
 *    |                  .                 |
 *    |                  .                 |
 *    |____________________________________|
 *    |                                    |
 *    |             Two buttons            |
 *    |____________________________________|
 *    |                                    |
 *    |             error text area        |
 *    |____________________________________|
 *    
 * </pre>
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
@SuppressWarnings( "serial" )
public abstract class WindowBase extends JDialog {
    
    //////////////////////////////////////////////////// 
    // Graphical Fields used only for design purposes //
   ////////////////////////////////////////////////////

   /**
    * {@code REDCOMPONENT} int value that represents Red Component for panel color. 
    */
    private static final int REDCOMPONENT = 65;
    /**
     * {@code GREENCOMPONENT} int value that represents Green Component for panel color. 
     */
    private static final int GREENCOMPONENT = 72;
    /**
     * {@code BLUECOMPONENT} int value that represents Blue Component for panel color. 
     */
    private static final int BLUECOMPONENT = 78;
    /**
     * {@code TRANSLUCENTCOMPONENT} int value that represents panel Color (translucent Color).
     */
    private static final int TRANSLUCENTCOMPONENT = 0;
    /**
     * {@code ERRORJTEXTAREAWIDTH} int value that represents {@code errorJTextArea} width. 
     */
    private static final int ERRORJTEXTAREAWIDTH = 320;   
    /**
     * {@code ERRORJTEXTAREAHEIGHT} int value that represents {@code errorJTextArea} height. 
     */
    private static final int ERRORJTEXTAREAHEIGHT = 60; 
    /**
     * {@code IMAGESGRIDBAGLAYOUTPOSITION} int value that represents the row where is insert the
     * {@link  JPanelImage} into {@code GridBagConstraints}.
     */
    private static final int IMAGESGRIDBAGLAYOUTPOSITION = 0; 
    
    /**
     * Panel with two buttons, the first have the point to {@code call} a {@link Callable}, and the
     * second to dispose this {@code JDialog}.
     */
    private JTwoButtonsPanel buttonsPanel;
    /**
     * JTextArea where the exception message will be printed.
     */
    // private JTextArea errorJTextArea;
    private JTextArea errorJTextArea;
    
    private GridBagConstraints constraints;
    
    /**
     * Create a {@code JDialog} with two buttons and a error label
     * 
     * @param width
     * @param height
     */
    public WindowBase( int width, int height, String... imagePath ) {
        setDefaultWindow( width, height );
        
        constraints = GridBagUtils.createGridBagConstraints();
        
        addComponent( new JPanelImage( imagePath ), IMAGESGRIDBAGLAYOUTPOSITION );
        
        createButtonsAndErrorJTextArea();
        
        getRootPane().setDefaultButton( buttonsPanel.getLeftButton() );
    }
    
    
    
    // Private methods
    /** 
     *  Private Method responsible to change {@link WindowBase} configuration.
     * @param width - int value for {@link WindowBase} width.
     * @param height- int value for {@link WindowBase} height.
     */
    private void setDefaultWindow( int width, int height ) {
        getContentPane().setLayout( new GridBagLayout() );
        
        setSize( width, height );
        getContentPane().setBackground( new Color( REDCOMPONENT, GREENCOMPONENT, BLUECOMPONENT ) );
        
        setIconImage( Toolkit.getDefaultToolkit().getImage( "src/main/resources/images/radar.png" ) );
        
        setTitle( "Air Traffic Controll" );
        setLocationRelativeTo( null );
        setResizable( false );
        setDefaultCloseOperation( JDialog.DISPOSE_ON_CLOSE );
    }
    
   /**
    *  Private Method responsible two create the {@link JTwoButtonsPanel} and {@link JTextArea} with error information.
    */
    private void createButtonsAndErrorJTextArea() {
        buttonsPanel = new JTwoButtonsPanel();
        buttonsPanel.setBackground( new Color( TRANSLUCENTCOMPONENT, TRANSLUCENTCOMPONENT, TRANSLUCENTCOMPONENT, TRANSLUCENTCOMPONENT ) );
        addComponent( buttonsPanel, GridBagConstraints.BASELINE_TRAILING );
        
        
        errorJTextArea = new JTextArea();
        errorJTextArea.setText( " " );
        errorJTextArea.setForeground( Color.RED );
        errorJTextArea.setPreferredSize( new Dimension( ERRORJTEXTAREAWIDTH, ERRORJTEXTAREAHEIGHT ) );
        errorJTextArea.setBackground( new Color( REDCOMPONENT, GREENCOMPONENT, BLUECOMPONENT ) );
        
        errorJTextArea.setEditable( false );
        
        addComponent( errorJTextArea, GridBagConstraints.BELOW_BASELINE );
    }
    
    // Protected Methods
    /**
     * Add a JComponent to the line {@code y}
     * 
     * @param component
     * @param constraints
     */
    protected void addComponent( JComponent component, int y ) {
        constraints.gridy = y;
        getContentPane().add( component, constraints );
    }
    
    // Public Get Methods
    /**
     * @return the buttonsPanel
     */
    public JTwoButtonsPanel getButtonsPanel() {
        return buttonsPanel;
    }
    
    /**
     * @return errorJTextArea
     */
    public JTextArea getErrorJTextArea() {
        return errorJTextArea;
    }
}
