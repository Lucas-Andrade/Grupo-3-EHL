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
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class PostUserWindow {

	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {

			@Override
			public void run() {
				try {
					
					new PostUserWindow();
				
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
	}

	public PostUserWindow() throws MalformedURLException  {
		initialize();
		
	}
	private void initialize() throws MalformedURLException {
	
		
		JDialogWithBackground Jdialog = new JDialogWithBackground(
				"src/main/resources/images/ImageGray.jpg");
		Jdialog.setIconImage(Toolkit.getDefaultToolkit().getImage("src/main/resources/images/Air-icon.png"));
		Jdialog.setTitle("Air Traffic Controll");

		Jdialog.setVisible(true);

		Jdialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 350 };
		gridBagLayout.rowHeights = new int[] { 165, 0, 40, 0, 42, 0, 40, 0, 0, 73,
				42, 0 };
		
		gridBagLayout.columnWeights = new double[] { 0.0 };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		
		Jdialog.getContentPane().setLayout(gridBagLayout);
		Jdialog.setResizable(false);   
		{

			ImagePanel UserImage = new ImagePanel(
					"src/main/resources/images/user.jpg");

			GridBagConstraints UserBox = new GridBagConstraints();
			UserBox.insets = new Insets(0, 0, 5, 0);
			UserBox.gridx = 0;
			UserBox.gridy = 0;

			Jdialog.getContentPane().add(UserImage, UserBox);
			
		}

		{

			JLabel UsernameText = new JLabel("Username");
			UsernameText.setForeground(Color.WHITE);
			GridBagConstraints userNameBox = new GridBagConstraints();
			userNameBox.insets = new Insets(0, 0, 5, 0);
			userNameBox.anchor = GridBagConstraints.CENTER;
			userNameBox.gridx = 0;
			userNameBox.gridy = 1;

			Jdialog.getContentPane().add(UsernameText, userNameBox);
		}

		{
			JTextField userNameTextField = new JTextField();

			userNameTextField.setColumns(20);

			//userNameTextField.getText();
			
			GridBagConstraints userNameTextFieldBox = new GridBagConstraints();
			userNameTextFieldBox.insets = new Insets(0, 0, 5, 0);
			userNameTextFieldBox.anchor = GridBagConstraints.CENTER;
			userNameTextFieldBox.gridx = 0;
			userNameTextFieldBox.gridy = 2;

			Jdialog.getContentPane().add(userNameTextField,
					userNameTextFieldBox);
		}

		{

			JLabel PasswordText = new JLabel("Password");
			PasswordText.setForeground(Color.WHITE);
			GridBagConstraints PasswordBox = new GridBagConstraints();
			PasswordBox.insets = new Insets(0, 0, 5, 0);
			PasswordBox.anchor = GridBagConstraints.CENTER;
			PasswordBox.gridx = 0;
			PasswordBox.gridy = 3;

			Jdialog.getContentPane().add(PasswordText, PasswordBox);
		}

		{

			JPasswordField PasswordTextField = new JPasswordField();

			PasswordTextField.setColumns(20);

			GridBagConstraints PasswordTextFieldBox = new GridBagConstraints();
			PasswordTextFieldBox.insets = new Insets(0, 0, 5, 0);
			PasswordTextFieldBox.anchor = GridBagConstraints.CENTER;
			PasswordTextFieldBox.gridx = 0;
			PasswordTextFieldBox.gridy = 4;

			Jdialog.getContentPane().add(PasswordTextField,
					PasswordTextFieldBox);

		}

		{

			JLabel emailText = new JLabel("email");
			emailText.setForeground(Color.WHITE);

			GridBagConstraints emailBox = new GridBagConstraints();
			emailBox.insets = new Insets(0, 0, 5, 0);
			emailBox.anchor = GridBagConstraints.CENTER;
			emailBox.gridx = 0;
			emailBox.gridy = 5;

			Jdialog.getContentPane().add(emailText, emailBox);
		}

		{

			JTextField emailTextField = new JTextField();

			emailTextField.setColumns(20);

			GridBagConstraints emailTextFieldBox = new GridBagConstraints();
			emailTextFieldBox.insets = new Insets(0, 0, 5, 0);
			emailTextFieldBox.anchor = GridBagConstraints.CENTER;
			emailTextFieldBox.gridx = 0;
			emailTextFieldBox.gridy = 6;

			Jdialog.getContentPane().add(emailTextField, emailTextFieldBox);
		}

		{
			JLabel fullnameText = new JLabel("fullname");
			fullnameText.setForeground(Color.WHITE);
			GridBagConstraints fullnameBox = new GridBagConstraints();
			fullnameBox.insets = new Insets(0, 0, 5, 0);
			fullnameBox.anchor = GridBagConstraints.CENTER;
			fullnameBox.gridx = 0;
			fullnameBox.gridy = 7;

			Jdialog.getContentPane().add(fullnameText, fullnameBox);
		}

		{

			JTextField fullnameTextField = new JTextField();

			fullnameTextField.setColumns(20);

			GridBagConstraints fullnameTextFieldBox = new GridBagConstraints();
			fullnameTextFieldBox.insets = new Insets(0, 0, 5, 0);
			fullnameTextFieldBox.anchor = GridBagConstraints.CENTER;
			fullnameTextFieldBox.gridx = 0;
			fullnameTextFieldBox.gridy = 8;

			Jdialog.getContentPane().add(fullnameTextField, fullnameTextFieldBox);
		}

		{

			JPanel okcancelpanel = new JPanel();

			okcancelpanel.setOpaque(false);
			GridBagConstraints gbc_okcancelpanel = new GridBagConstraints();
			gbc_okcancelpanel.anchor = GridBagConstraints.CENTER;
			gbc_okcancelpanel.gridx = 0;
			gbc_okcancelpanel.gridy = 10;
			Jdialog.getContentPane().add(okcancelpanel, gbc_okcancelpanel);

			JButton okButton = new JButton("Ok");
			okButton.setActionCommand("Ok");
			okcancelpanel.add(okButton);

			JButton cancelButton = new JButton("Cancel");
			cancelButton.setActionCommand("Cancel");
			okcancelpanel.add(cancelButton);
		}

	}

}
