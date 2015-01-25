package main.java.gui.designWindows.JPanels;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class JLablePlusJTextField extends JPanel {

	private static final long serialVersionUID = 1L;

	private String JLabelText;
	private int JTextFieldSize;
	private Color JLabelColor = Color.BLACK ;
	
	private JLabel label; 
	private JTextField textField;

	
	
	public JLablePlusJTextField(String JLabelText, int JTextFieldSize )  {
		
		this.JLabelText = JLabelText;
		this.JTextFieldSize = JTextFieldSize;
			
		initial();
	}
	
	
	
	public JLablePlusJTextField(String JLabelText,int JTextFieldSize, Color JLabelcolor ) {
		

		this.JLabelText = JLabelText;
		this.JTextFieldSize = JTextFieldSize;
		this.JLabelColor=JLabelcolor;
		
		initial();
	}


	private void initial() {

		setLayout(new GridLayout(2, 1, 0,0));
		
		JPanel panelLabel = new JPanel();
		panelLabel.setBackground(new Color(0,0,0,0));
		add(panelLabel);
		
		label = new JLabel(JLabelText);
		label.setForeground(JLabelColor);
		label.setBackground(new Color(0,0,0,0));
		panelLabel.add(label);
		
		JPanel paneltextField = new JPanel();
		paneltextField.setBackground(new Color(0,0,0,0));
		add(paneltextField);
		
		textField = new JTextField();
		textField.setColumns(JTextFieldSize);		
		paneltextField.add(textField);

		this.setBackground(new Color(0,0,0,0));
	}
	
	
	public JLabel getJLabel() {
		return label;
	}

	public JTextField getJTextField() {
		return textField;
	}


	public void setJTextField(JTextField newtextField) {
		textField = newtextField;
	}	
	
	public void setJLabelText(JLabel newLabel) {
		
		label = newLabel;
	}

		
	
}
