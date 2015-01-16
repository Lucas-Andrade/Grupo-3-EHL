package main.java.gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.net.MalformedURLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;


public class LogInWindow {

	
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
		initialize();

	}

	private void initialize() throws MalformedURLException {
		
		JDialogWithBackground jDialogLogin = new JDialogWithBackground("src/main/resources/images/ImageGray370x350.jpg");
		jDialogLogin.setIconImage(Toolkit.getDefaultToolkit().getImage("src/main/resources/images/Air-icon.png"));
		

		
		jDialogLogin.setTitle("Air Traffic Controll");
		jDialogLogin.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		jDialogLogin.setVisible(true);

		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{20, 285, 20, 0};
		gridBagLayout.rowHeights = new int[]{125, 35, 45, 30, 40, 40, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		
		jDialogLogin.getContentPane().setLayout(gridBagLayout);
		
		jDialogLogin.setResizable(false);   

		{
			ImagePanel radarIcon = new ImagePanel("src/main/resources/images/radar.png");
			
			GridBagConstraints radarTextBox = new GridBagConstraints();
			radarTextBox.insets = new Insets(0, 0, 5, 5);
			radarTextBox.anchor = GridBagConstraints.CENTER;
			radarTextBox.gridx = 1;
			radarTextBox.gridy = 0;
			
			jDialogLogin.getContentPane().add(radarIcon, radarTextBox);

		}	
		
			
		
		{
			
			JLabel usernameTextLabel = new JLabel("Username");	
			usernameTextLabel.setForeground(Color.WHITE);

			GridBagConstraints usernameTextBox = new GridBagConstraints();
			usernameTextBox.insets = new Insets(0, 0, 5, 5);
			usernameTextBox.anchor = GridBagConstraints.CENTER;
			usernameTextBox.gridx = 1;
			usernameTextBox.gridy = 1;
			jDialogLogin.getContentPane().add(usernameTextLabel, usernameTextBox);
			
		}	
		
		{
			
			JTextField usernameTextField = new JTextField();
			usernameTextField.setColumns(20);
			
			GridBagConstraints usernameBox = new GridBagConstraints();
			usernameBox.anchor = GridBagConstraints.NORTH;
			usernameBox.insets = new Insets(0, 0, 5, 5);
			usernameBox.gridx = 1;
			usernameBox.gridy = 2;
			jDialogLogin.getContentPane().add(usernameTextField, usernameBox);

		}
		
		
		{
			ImageIcon lockerIcon = new ImageIcon("src/main/resources/images/locker.png");			
			JLabel PasswordTextLabel = new JLabel("Password");	
			
			PasswordTextLabel.setIcon(lockerIcon);
			PasswordTextLabel.setForeground(Color.WHITE);

			GridBagConstraints PasswordTextBox = new GridBagConstraints();
			
			PasswordTextBox.insets = new Insets(0, 0, 5, 5);
			PasswordTextBox.anchor = GridBagConstraints.CENTER;
			PasswordTextBox.gridx = 1;
			PasswordTextBox.gridy = 3;
			jDialogLogin.getContentPane().add(PasswordTextLabel, PasswordTextBox);
			
		}	

		
		{
			
			JPasswordField fullnameTextField = new JPasswordField();
			fullnameTextField.setColumns(20);
			
			GridBagConstraints fullnameBox = new GridBagConstraints();
			fullnameBox.anchor = GridBagConstraints.NORTH;
			fullnameBox.insets = new Insets(0, 0, 5, 5);
			fullnameBox.gridx = 1;
			fullnameBox.gridy = 4;
			jDialogLogin.getContentPane().add(fullnameTextField, fullnameBox);

		}
		
		
		{

			JPanel okCancelPanel = new JPanel();

			okCancelPanel.setOpaque(false);
			
			GridBagConstraints okCancelPanelBox = new GridBagConstraints();
			okCancelPanelBox.insets = new Insets(0, 0, 0, 5);
			okCancelPanelBox.anchor = GridBagConstraints.NORTH;
			okCancelPanelBox.gridx = 1;
			okCancelPanelBox.gridy = 5;
			jDialogLogin.getContentPane().add(okCancelPanel, okCancelPanelBox);

			JButton okButton = new JButton("Ok");
			okButton.setActionCommand("Ok");
			okCancelPanel.add(okButton);

			JButton cancelButton = new JButton("Close");
			cancelButton.setActionCommand("Close");
			okCancelPanel.add(cancelButton);
		}
		
		
		
		
	}

}
