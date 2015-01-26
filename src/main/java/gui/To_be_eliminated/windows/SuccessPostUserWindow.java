package main.java.gui.To_be_eliminated.windows;

import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JLabel;

import main.java.gui.designWindows.windows.WindowBase;

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
