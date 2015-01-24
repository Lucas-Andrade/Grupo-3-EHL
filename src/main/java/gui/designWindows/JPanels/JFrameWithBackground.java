package main.java.gui.designWindows.JPanels;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class JFrameWithBackground extends JFrame {

	private static final long serialVersionUID = 1L;
	Image bImage;

	public JFrameWithBackground(String path) {

		this.bImage = this.createImage(path);

		this.initComponents();

	}

	public void initComponents() {

		super.setContentPane(new NewContentPane());

		super.setExtendedState(JFrame.MAXIMIZED_BOTH);

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