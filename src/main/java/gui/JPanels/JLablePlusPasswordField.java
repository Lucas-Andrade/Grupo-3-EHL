package main.java.gui.JPanels;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

public class JLablePlusPasswordField extends JPanel {

	private static final long serialVersionUID = 1L;

	private String JLabelText;
	private int JTextFieldSize;
	
	private Color JLabelColor = Color.BLACK ;

	private String path=null;
	
	public JLablePlusPasswordField(String JLabelText, int JTextFieldSize )  {
		
		this.JLabelText = JLabelText;
		this.JTextFieldSize = JTextFieldSize;
		
		initial();
	}	
	
	public JLablePlusPasswordField(String JLabelText,int JTextFieldSize, Color JLabelcolor ) {
		

		this.JLabelText = JLabelText;
		this.JTextFieldSize = JTextFieldSize;
		this.JLabelColor=JLabelcolor;
		
		initial();
	}
	
	public JLablePlusPasswordField(String JLabelText,int JTextFieldSize, Color JLabelcolor, String path ) {
		

		this.JLabelText = JLabelText;
		this.JTextFieldSize = JTextFieldSize;
		this.JLabelColor=JLabelcolor;
		this.path=path;
		initial();
	}

	private void initial() {
		
		setLayout(new GridLayout(2, 1, 0, 0));
	
		JPanel panelLabel = new JPanel();
		panelLabel.setBackground(new Color(0,0,0,0));
		add(panelLabel);
		
		JLabel labelPassword = new JLabel(JLabelText);
		labelPassword.setForeground(JLabelColor);
		labelPassword.setBackground(new Color(0,0,0,0));
		
		if(path!=null) labelPassword.setIcon(new ImageIcon(path));
		
		panelLabel.add(labelPassword);
		
		JPanel panelPasswordField = new JPanel();
		panelPasswordField.setBackground(new Color(0,0,0,0));
		add(panelPasswordField);
		
		JPasswordField textField = new JPasswordField();
		textField.setColumns(JTextFieldSize);

		panelPasswordField.add(textField);
		
		
		
		this.setBackground(new Color(0,0,0,0));;
	}

}
