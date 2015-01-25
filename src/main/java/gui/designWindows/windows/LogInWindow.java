package main.java.gui.designWindows.windows;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import main.java.gui.designWindows.GridBagUtils;
import main.java.gui.designWindows.JPanels.ForAll.JLablePlusJTextField;


@SuppressWarnings("serial")
public class LogInWindow extends WindowBase{

	
	private GridBagConstraints constraints = GridBagUtils.createGridBagConstraints();

	public LogInWindow() {
		
		super(380, 380,"src/main/resources/images/radar.png","src/main/resources/images/logo.png");
		
		initialize();

	}

	private void initialize()  {		


		this.getContentPane().add( new JLablePlusJTextField("Username", 20, Color.WHITE), 
									GridBagUtils.updateGridBagConstraints(constraints ,0,1, new Insets(5, 0, 0, 0)));
		
		this.getContentPane().add( new JLablePlusJTextField("Password", 20, Color.WHITE,true,"src/main/resources/images/locker.png"), 
									GridBagUtils.updateGridBagConstraints(constraints,0,2, new Insets(5, 0, 15, 0)));
		

		this.setVisible(true);
		
		
		
	}
}
