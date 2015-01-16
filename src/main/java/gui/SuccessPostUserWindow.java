package main.java.gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.net.MalformedURLException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;

public class SuccessPostUserWindow {

	

	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
				
					
					new SuccessPostUserWindow();
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public SuccessPostUserWindow() throws MalformedURLException {
		initialize();
	}

	
	private void initialize() throws MalformedURLException {
		
		JDialogWithBackground jDialog = new JDialogWithBackground("src/main/resources/images/ImageGray_2.jpg");
		jDialog.setIconImage(Toolkit.getDefaultToolkit().getImage("src/main/resources/images/Air-icon.png"));
		jDialog.setTitle("Air Traffic Controll");

		jDialog.setVisible(true);
		jDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		
		
		
		jDialog.setResizable(false);   
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{100, 100};
		gridBagLayout.rowHeights = new int[]{30, 85, 68};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0};
		jDialog.getContentPane().setLayout(gridBagLayout);
		
		{
		JLabel SucessText = new JLabel("THE USER WAS SUCCESSFULY ADDED!");
		SucessText.setForeground(Color.WHITE);
		
		GridBagConstraints SucessBox = new GridBagConstraints();
		SucessBox.insets = new Insets(50, 0, 0, 0);
		SucessBox.gridx = 0;
		SucessBox.gridy = 1;

		jDialog.getContentPane().add(SucessText, SucessBox);
		}
		
		{
			
			ImagePanel sucessIcon = new ImagePanel("src/main/resources/images/successIcon.png");
			
			GridBagConstraints SucessIconBox = new GridBagConstraints();
			SucessIconBox.insets = new Insets(0, 0, 0, 20);
			SucessIconBox.gridx = 1;
			SucessIconBox.gridy = 1;
			
			jDialog.getContentPane().add(sucessIcon, SucessIconBox);
			
		}
		
		
		{
			JButton okButton = new JButton("Ok");
			okButton.setActionCommand("Ok");
			
			GridBagConstraints OkBox = new GridBagConstraints();
			OkBox.insets = new Insets(0, 0, 0, 0);
			OkBox.gridx = 0;
			OkBox.gridy = 2;
			jDialog.getContentPane().add(okButton, OkBox);
			
		}
		
	
	}

	
}
