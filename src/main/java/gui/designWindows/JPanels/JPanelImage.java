package main.java.gui.designWindows.JPanels;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class JPanelImage extends JPanel {
	 
	private static final long serialVersionUID = 1L;
	
    private Image image;
  
    public JPanelImage(String path) {
        
    	image = new ImageIcon(path).getImage();
    	setOpaque(false);
        setPreferredSize(new Dimension(image.getWidth(this), image.getHeight(this)));
    }
    
      
    
    @Override
    public void paintComponent(Graphics g) {
    	 super.paintComponent(g);
        g.drawImage(image, 0, 0, this);
         
       
    }
  
}