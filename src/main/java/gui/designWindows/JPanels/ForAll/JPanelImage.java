package main.java.gui.designWindows.JPanels.ForAll;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class JPanelImage extends JPanel {

	private static final long serialVersionUID = 1L;

	public JPanelImage(String... path) {

		this.setBackground(new Color(65,72,78));
		for(String imagePath : path){
			
			this.add(new createImage(imagePath));
			
		}
		
	}

	@SuppressWarnings("serial")
	public static class createImage extends JPanel {

		private Image image;

		public createImage(String path) { 
			
			

			image = new ImageIcon(path).getImage();
			setOpaque(false);
			setPreferredSize(new Dimension(image.getWidth(this),
					image.getHeight(this)));
		}

		@Override
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.drawImage(image, 0, 0, this);

		}

	}


	
}