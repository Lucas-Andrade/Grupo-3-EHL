package main.java.gui.designWindows.windows;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import main.java.gui.designWindows.GridBagUtils;
import main.java.gui.designWindows.JPanels.ForAll.JLablePlusJTextField;


@SuppressWarnings("serial")
public class LogInWindow extends WindowBase{

	
	private GridBagConstraints constraints = GridBagUtils.createGridBagConstraints();
	private JLablePlusJTextField getUserPanel;
	private JLablePlusJTextField getPasswordPanel;

	public LogInWindow() {
		
		super(380, 380,"src/main/resources/images/radar.png","src/main/resources/images/logo.png");
		
		initialize();

	}

	private void initialize()  {		


		getUserPanel=  new JLablePlusJTextField("Username", 20, Color.WHITE);		
		this.getContentPane().add(getUserPanel,GridBagUtils.updateGridBagConstraints(constraints ,0,1, new Insets(5, 0, 0, 0)));
		getPasswordPanel = new JLablePlusJTextField("Password", 20, Color.WHITE,true,"src/main/resources/images/locker.png");
		this.getContentPane().add( getPasswordPanel , GridBagUtils.updateGridBagConstraints(constraints,0,2, new Insets(5, 0, 15, 0)));
		

		this.setVisible(true);
		
		
		
	}

	public JLablePlusJTextField getUserPanel() {
		return getUserPanel;
	}

	public JLablePlusJTextField getPasswordPanel() {
		return getPasswordPanel;
	}
}
