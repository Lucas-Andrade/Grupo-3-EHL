package main.java.gui.designWindows.windows;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import main.java.gui.designWindows.JPanels.JDialogWithBackground;
import main.java.gui.designWindows.JPanels.JLablePlusJTextField;
import main.java.gui.designWindows.JPanels.JLablePlusPasswordField;
import main.java.gui.designWindows.JPanels.JOkCancelPanel;
import main.java.gui.designWindows.JPanels.JPanelImage;

public class PostUserWindow extends JDialogWithBackground{


	private static final long serialVersionUID = 1L;

	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {

			@Override
			public void run() {
				
					
					new PostUserWindow();	
				
			}
		});
	}

	public PostUserWindow()   {
		
		super(new Color (65,72,78),400, 600);
		initialize();
		
	}
	private void initialize()  {
		
	
		GridBagLayout gridBagLayout = new GridBagLayout();


		this.getContentPane().setLayout(gridBagLayout);
		 
		
		{
			JPanelImage UserImage = new JPanelImage("src/main/resources/images/user.png");

			GridBagConstraints UserBox = new GridBagConstraints();
			UserBox.insets = new Insets(20, 0, 10, 0);
			UserBox.gridx = 0;
			UserBox.gridy = 0;

			this.getContentPane().add(UserImage, UserBox);	
		}
		
		{
			
			GridBagConstraints usernameBox = new GridBagConstraints();
			usernameBox.insets = new Insets(0, 0, 5, 0);
			usernameBox.anchor = GridBagConstraints.CENTER;
			usernameBox.gridx = 0;
			usernameBox.gridy = 1;

			this.getContentPane().add(new JLablePlusJTextField("Username",20, Color.WHITE),
					usernameBox);
		}

		{
			GridBagConstraints PasswordBox = new GridBagConstraints();
			PasswordBox.insets = new Insets(0, 0, 5, 0);
			PasswordBox.gridx = 0;
			PasswordBox.gridy =2;
			this.getContentPane().add(new JLablePlusPasswordField("Password",20, Color.WHITE),PasswordBox);
		}
		
		{
			GridBagConstraints PasswordBox = new GridBagConstraints();
			PasswordBox.insets = new Insets(0, 0, 5, 0);
			PasswordBox.gridx = 0;
			PasswordBox.gridy =3;
			this.getContentPane().add(new JLablePlusPasswordField("Confirm Password",20, Color.WHITE),PasswordBox);
		}

		{
		
			GridBagConstraints emailBox = new GridBagConstraints();
			emailBox.insets = new Insets(0, 0, 5, 0);
			emailBox.gridx = 0;
			emailBox.gridy = 4;

			this.getContentPane().add(new JLablePlusJTextField("Email",20,Color.WHITE), emailBox);
		}
		
		{	
			GridBagConstraints fullnameBox = new GridBagConstraints();
			fullnameBox.insets = new Insets(0, 0, 5, 0);
			fullnameBox.gridx = 0;
			fullnameBox.gridy = 5;

			this.getContentPane().add(new JLablePlusJTextField("Fullname",20,Color.WHITE), fullnameBox);
		}

		{
			
			GridBagConstraints okCancelBox = new GridBagConstraints();
			okCancelBox.insets = new Insets(0, 0, 5, 0);
			okCancelBox.gridx = 0;
			okCancelBox.gridy = 6;

			this.getContentPane().add(new JOkCancelPanel(), okCancelBox);
		}
	
		this.setVisible(true);

	}

}
