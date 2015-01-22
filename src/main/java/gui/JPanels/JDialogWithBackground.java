package main.java.gui.JPanels;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JPanel;

public abstract class JDialogWithBackground extends JDialog {

	private static final long serialVersionUID = 1L;
	Image bImage;


	public JDialogWithBackground(String path) {
	
		bImage = new ImageIcon(path).getImage();
		this.setSize(bImage.getWidth(this), bImage.getHeight(this));
		this.setContentPane(new NewContentPane());
		this.initComponents();
	}
	
	public JDialogWithBackground(Color color,int Width, int Height ) {
		
		this.getContentPane().setBackground( color);
		this.setSize(Width,Height);
		
		this.initComponents();

	}
		
	
	public void initComponents() {
		
		this.setIconImage(Toolkit.getDefaultToolkit().getImage("src/main/resources/images/Air-icon.png"));
		this.setTitle("Air Traffic Control");
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);	
		
	}

	private class NewContentPane extends JPanel {

		private static final long serialVersionUID = 1L;
		
		protected void paintComponent(final Graphics g) {
			
			
			super.paintComponent(g);
			g.drawImage(bImage, 0, 0, this);
		}

	}
}
