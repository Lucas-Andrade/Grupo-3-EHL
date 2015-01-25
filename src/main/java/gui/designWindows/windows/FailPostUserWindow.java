package main.java.gui.designWindows.windows;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.net.MalformedURLException;

import javax.swing.JButton;
import javax.swing.JLabel;

import main.java.gui.designWindows.JPanels.JDialogWithBackground;
import main.java.gui.designWindows.JPanels.JPanelImage;

public class FailPostUserWindow extends JDialogWithBackground {

	private static final long serialVersionUID = 1L;


	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
				
					
					new FailPostUserWindow();
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public FailPostUserWindow() throws MalformedURLException {
		super(new Color (65,72,78),370, 240);
		
		initialize();
	}

	
	private void initialize() throws MalformedURLException {
		
			
		
		  
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{100, 100};
		gridBagLayout.rowHeights = new int[]{30, 85, 68};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0};
		this.getContentPane().setLayout(gridBagLayout);
		
		{
		JLabel SucessText = new JLabel("THE USER WASN'T SUCCESSFULY ADDED!");
		SucessText.setForeground(Color.WHITE);
		
		GridBagConstraints SucessBox = new GridBagConstraints();
		SucessBox.insets = new Insets(50, 0, 0, 0);
		SucessBox.gridx = 0;
		SucessBox.gridy = 1;

		this.getContentPane().add(SucessText, SucessBox);
		}
		
		{
			
			JPanelImage failIcon = new JPanelImage("src/main/resources/images/failIcon.png");
			
			GridBagConstraints FailIconBox = new GridBagConstraints();
			FailIconBox.insets = new Insets(0, 0, 0, 20);
			FailIconBox.gridx = 1;
			FailIconBox.gridy = 1;
			
			this.getContentPane().add(failIcon, FailIconBox);
			
		}
		
		
		{
			JButton okButton = new JButton("Ok");
			okButton.setActionCommand("Ok");
			
			GridBagConstraints OkBox = new GridBagConstraints();
			OkBox.insets = new Insets(0, 0, 0, 0);
			OkBox.gridx = 0;
			OkBox.gridy = 2;
			this.getContentPane().add(okButton, OkBox);
			
		}
		this.setVisible(true);
	
	}

	
}
