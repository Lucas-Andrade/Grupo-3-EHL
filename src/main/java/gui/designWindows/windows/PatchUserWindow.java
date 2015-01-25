package main.java.gui.designWindows.windows;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import main.java.gui.designWindows.GridBagUtils;
import main.java.gui.designWindows.JPanels.ForAll.JLablePlusJTextField;

@SuppressWarnings("serial")
public class PatchUserWindow extends WindowBase{

	
	
	private JLablePlusJTextField userPanel;
	private JLablePlusJTextField oldPasswordPanel;
	private JLablePlusJTextField newPasswordPanel;
	private JLablePlusJTextField getNewPasswordConfirmationPanel;
	private GridBagConstraints constraints = GridBagUtils.createGridBagConstraints();

	public PatchUserWindow() {
		super(400, 600,"src/main/resources/images/pacthUser.png");
		initialize();
	}

	private void initialize() {
		
		userPanel =new JLablePlusJTextField("Username",20, Color.WHITE);
		this.getContentPane().add(userPanel, GridBagUtils.updateGridBagConstraints(constraints ,0,1, new Insets(0, 0, 10, 0)));

		oldPasswordPanel = new JLablePlusJTextField("Password",20, Color.WHITE,true);			
		this.getContentPane().add(oldPasswordPanel, GridBagUtils.updateGridBagConstraints(constraints,0,2, new Insets(0, 0, 10, 0)));

		newPasswordPanel = new JLablePlusJTextField("New Password",20, Color.WHITE,true);
		this.getContentPane().add(newPasswordPanel,GridBagUtils.updateGridBagConstraints(constraints,0,3, new Insets(0, 0, 10, 0)));
		
		getNewPasswordConfirmationPanel = new JLablePlusJTextField("Confirm New Password",20, Color.WHITE,true);
		this.getContentPane().add(getNewPasswordConfirmationPanel,GridBagUtils.updateGridBagConstraints(constraints,0,4, new Insets(0, 0, 10, 0)));
		this.setVisible(true);

	}

	public JLablePlusJTextField getUserPanel() {
		return userPanel;
	}

	public JLablePlusJTextField getOldPasswordPanel() {
		return oldPasswordPanel;
	}

	public JLablePlusJTextField getNewPasswordPanel() {
		return newPasswordPanel;
	}

	public JLablePlusJTextField getGetNewPasswordConfirmationPanel() {
		return getNewPasswordConfirmationPanel;
	}

	
	
	
	
}
