package main.java.gui.designWindows.windows;

import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JLabel;

@SuppressWarnings("serial") 
public class SuccessPostUserWindow extends WindowBase{

		
	public SuccessPostUserWindow() {
		super(370, 240,"src/main/resources/images/successIcon.png");
		initialize();
	}

	
	private void initialize() {

		this.getContentPane().setLayout(new FlowLayout());
		
		JLabel SucessText = new JLabel("THE USER WAS SUCCESSFULY ADDED!");
		SucessText.setForeground(Color.WHITE);

		this.getContentPane().add(SucessText);

		this.setVisible(true);
	
	}
	
}
