package main.java.gui.designWindows.windows;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import main.java.gui.designWindows.GridBagUtils;
import main.java.gui.designWindows.JPanels.ForAll.JLablePlusJTextField;

@SuppressWarnings("serial")
public class PostUserWindow extends WindowBase{


	private JLablePlusJTextField username;
	private JLablePlusJTextField password;
	private JLablePlusJTextField confirmPassword;
	private JLablePlusJTextField email;
	private JLablePlusJTextField fullname;
	private GridBagConstraints constraints = GridBagUtils.createGridBagConstraints();
	
	public PostUserWindow()   {
		
		super(400, 600,"src/main/resources/images/User.png");
		initialize();
		
	}
	private void initialize()  {

		
			username =new JLablePlusJTextField("Username",20, Color.WHITE);
			this.getContentPane().add(username, GridBagUtils.updateGridBagConstraints(constraints ,0,1, new Insets(0, 0, 10, 0)));
	
			password = new JLablePlusJTextField("Password",20, Color.WHITE,true);			
			this.getContentPane().add(password, GridBagUtils.updateGridBagConstraints(constraints,0,2, new Insets(0, 0, 10, 0)));
	
			confirmPassword = new JLablePlusJTextField("Confirm Password",20, Color.WHITE,true);
			this.getContentPane().add(confirmPassword,GridBagUtils.updateGridBagConstraints(constraints,0,3, new Insets(0, 0, 10, 0)));
	
			email = new JLablePlusJTextField("Email",20,Color.WHITE);
			this.getContentPane().add(email, GridBagUtils.updateGridBagConstraints(constraints,0,4, new Insets(0, 0, 10, 0)));
	
			fullname = new JLablePlusJTextField("Fullname",20,Color.WHITE);
			this.getContentPane().add(fullname,GridBagUtils.updateGridBagConstraints(constraints,0,5, new Insets(0, 0, 20, 0)));
	
		this.setVisible(true);

	}
	public JLablePlusJTextField getUsername() {
		return username;
	}
	public JLablePlusJTextField getPassword() {
		return password;
	}
	public JLablePlusJTextField getConfirmPassword() {
		return confirmPassword;
	}
	public JLablePlusJTextField getEmail() {
		return email;
	}
	public JLablePlusJTextField getFullname() {
		return fullname;
	}

}
