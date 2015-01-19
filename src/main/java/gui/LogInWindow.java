package main.java.gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.net.MalformedURLException;

import main.java.gui.JPanels.JDialogWithBackground;
import main.java.gui.JPanels.JLablePlusJTextField;
import main.java.gui.JPanels.JLablePlusPasswordField;
import main.java.gui.JPanels.JOkCancelPanel;
import main.java.gui.JPanels.JPanelImage;


public class LogInWindow extends JDialogWithBackground{


	private static final long serialVersionUID = 1L;

	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {

			@Override
			public void run() {
				try {
					
					new LogInWindow();
				
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
	}

	
	public LogInWindow() throws MalformedURLException {
		super(new Color (65,72,78),370, 360);
		initialize();

	}

	private void initialize() throws MalformedURLException {
		
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{285};
		gridBagLayout.rowHeights = new int[]{125, 35, 45, 30, 40, 0};
		gridBagLayout.columnWeights = new double[]{0.0};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		
		this.getContentPane().setLayout(gridBagLayout);
		{
			JPanelImage radarIcon = new JPanelImage("src/main/resources/images/radar.png");
			
			GridBagConstraints radarTextBox = new GridBagConstraints();
			radarTextBox.insets = new Insets(0, 0, 5, 0);
			radarTextBox.anchor = GridBagConstraints.CENTER;
			radarTextBox.gridx = 0;
			radarTextBox.gridy = 0;
			
			this.getContentPane().add(radarIcon, radarTextBox);

		}		
		{
				
			GridBagConstraints usernameBox = new GridBagConstraints();
			usernameBox.anchor = GridBagConstraints.NORTH;
			usernameBox.insets = new Insets(0, 0, 5, 0);
			usernameBox.gridx = 0;
			usernameBox.gridy = 1;
			this.getContentPane().add(new JLablePlusJTextField("Username", 20, Color.WHITE), usernameBox);

		}
		{					
			GridBagConstraints passwordBox = new GridBagConstraints();
			passwordBox.anchor = GridBagConstraints.NORTH;
			passwordBox.insets = new Insets(0, 0, 5, 0);
			passwordBox.gridx = 0;
			passwordBox.gridy = 2;
			this.getContentPane().add(new JLablePlusPasswordField("Password", 20, Color.WHITE,
							"src/main/resources/images/locker.png"), passwordBox);

		}
		{
			GridBagConstraints okCancelBox = new GridBagConstraints();
			okCancelBox.anchor = GridBagConstraints.NORTH;
			okCancelBox.insets = new Insets(0, 0, 5, 0);
			okCancelBox.gridx = 0;
			okCancelBox.gridy = 4;
			this.getContentPane().add(new JOkCancelPanel("Ok", "Close"), okCancelBox);
					
		}
		
		this.setVisible(true);		
	}
}
