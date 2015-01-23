package main.java.gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.GridLayout;

import main.java.gui.JPanels.JDialogWithBackground;
import main.java.gui.JPanels.JLablePlusJTextField;
import main.java.gui.JPanels.JLablePlusPasswordField;
import main.java.gui.JPanels.JLogoPanel;
import main.java.gui.JPanels.JOkCancelPanel;


public class LogInWindow extends JDialogWithBackground{


	private static final long serialVersionUID = 1L;

	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {

			@Override
			public void run() {
			
					
					new LogInWindow();

			}
		});
	}

	
	public LogInWindow() {
		
		super(new Color (65,72,78),370, 380);
		initialize();

	}

	private void initialize()  {
		
		this.setLayout(new GridLayout(4,0,0,0));
			
		this.getContentPane().add(new JLogoPanel());			
		this.getContentPane().add(new JLablePlusJTextField("Username", 20, Color.WHITE));
		this.add(new JLablePlusPasswordField("Password", 20, Color.WHITE,"src/main/resources/images/locker.png"));
		this.add(new JOkCancelPanel("Ok", "Close"));
		
		this.setVisible(true);		
	}
	
	
	
}
