package main.java.gui;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JPanel;

public class JDialogWithBackground extends JDialog {

	private static final long serialVersionUID = 1L;
	Image bImage;
	ImageIcon bImageIcon;

	public JDialogWithBackground(String path) {

		this.bImage = this.createImage(path);
		this.bImageIcon = new ImageIcon(path);

		this.initComponents();

	}

	public void initComponents() {

		super.setContentPane(new NewContentPane());
		super.setSize(bImageIcon.getIconWidth(), bImageIcon.getIconHeight());
		super.setLocationRelativeTo(null);
		
		
	}

	private Image createImage(String path) {

		return Toolkit.getDefaultToolkit().createImage(path);

	}

	private class NewContentPane extends JPanel {

		private static final long serialVersionUID = 1L;

		protected void paintComponent(final Graphics g) {

			super.paintComponent(g);
			g.drawImage(bImage, 0, 0, this);

		}

	}

}
