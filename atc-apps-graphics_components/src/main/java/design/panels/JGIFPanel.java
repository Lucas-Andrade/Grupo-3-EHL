package design.panels;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;


@SuppressWarnings( "serial" )
public class JGIFPanel extends JPanel {

    

    private ImageIcon image; 
    
    
    public JGIFPanel(String path) {

        this.setBackground( new Color(65,72,78) );
        image = new ImageIcon(getClass().getResource(path));
        create();
        
    }
    private static void  create(){
    
      EventQueue.invokeLater(new Runnable() {
           
            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (ClassNotFoundException | InstantiationException 
                        | IllegalAccessException | UnsupportedLookAndFeelException ex) {
          
                }

            }
        });
    }
        @Override
        public Dimension getPreferredSize() {
            return  new Dimension(image.getIconWidth(), image.getIconHeight());
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g); 
            image.paintIcon(this, g,
                            (getWidth() - image.getIconWidth()) / 2 ,
                            (getHeight() - image.getIconHeight()) / 2);
        }

    }




