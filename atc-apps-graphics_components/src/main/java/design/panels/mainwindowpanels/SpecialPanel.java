package design.panels.mainwindowpanels;


import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;
import design.panels.JGIFPanel;
import design.windows.popupwindows.SpecialPopUpWindow;


@SuppressWarnings( "serial" )
public class SpecialPanel extends JPanel {

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
     * {@code HORIZONTALIMIT} int value that represents horizontal limit for {@link JGIFPanel} path.
     */
    private static final int HORIZONTALIMIT = 600;
    /**
     * {@code VERTICALLIMIT} int value that represents horizontal limit for {@link JGIFPanel} path.
     */
    private static final int VERTICALLIMIT = 0;
    /**
     * {@code BLUECOMPONENT} int value that represents the component width.
     */
    private static final int GIFWIDTH =100;
    /**
     * {@code BLUECOMPONENT} int value that represents the component width.
     */
    private static final int GIFHEIGHT =100;
    /**
     * {@code BLUECOMPONENT} int value that represents the component width.
     */
    
    public long myLong= 10;
    private int deltaHorizontal = 0;
    private JGIFPanel gif;
    private JButton button; 
    
    public SpecialPanel(){
  
    this.setBackground( new Color( REDCOMPONENT, GREENCOMPONENT, BLUECOMPONENT ) );
    this.setLayout( null );
    
        gif = new JGIFPanel("/images/jet.gif");
        button = new JButton();
        button.setBackground( new Color(0,0,0,0) );
        button.setOpaque( true );

        this.add( gif );
        this.add( button );
        gif.setBounds( deltaHorizontal, 20, GIFWIDTH, GIFHEIGHT );
        button.setBounds( deltaHorizontal, 20, GIFWIDTH, GIFHEIGHT );
        
        button.addActionListener( new ActionListener() {
            
            @Override
            public void actionPerformed( ActionEvent e ) {
            
                    new SpecialPopUpWindow();
            }
        } );
        
        
    }   
          
    public void doStuff() {
      
          if( deltaHorizontal > HORIZONTALIMIT )
              deltaHorizontal = 0;
          deltaHorizontal++;
          gif.setBounds(deltaHorizontal, VERTICALLIMIT, GIFWIDTH, GIFHEIGHT );
          button.setBounds( deltaHorizontal, VERTICALLIMIT, GIFWIDTH, GIFHEIGHT );
          
      } 
  

}

