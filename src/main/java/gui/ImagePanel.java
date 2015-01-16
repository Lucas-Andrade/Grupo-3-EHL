package main.java.gui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.net.MalformedURLException;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class ImagePanel extends JPanel {
	 

	private static final long serialVersionUID = 1L;
	
    private Image image;
 
    
  
    public ImagePanel(String path) throws MalformedURLException {
 
       
        setImage(path);
 
       
        setOpaque(false);
 
        setPreferredSize(new Dimension(getWidth(), getHeight()));
    }
 
    public void setImage(String path) {
        image = new ImageIcon(path).getImage();
        
    }
 
    @Override
    public void paintComponent(Graphics g) {
       
        g.drawImage(image, 0, 0, this);
 
        
        super.paintComponent(g);
    }
 
    
    @Override
    public int getWidth() {
        return image.getWidth(this);
    }
 
    
    @Override
    public int getHeight() {
        return image.getHeight(this);
    }
}