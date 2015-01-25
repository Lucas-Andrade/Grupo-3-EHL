package main.java.gui.designWindows.JPanels.ForAll;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import main.java.gui.designWindows.GridBagUtils;



@SuppressWarnings("serial")
public class JLablePlusJTextField extends JPanel {


	private JLabel label;
	private JTextField textField;
	private GridBagConstraints constraints = GridBagUtils.createGridBagConstraints();
	
	public JLablePlusJTextField(String JLabelText, int JTextFieldSize, Color JLabelcolor, boolean isJPasswordField, String iconLabelPath) {

		this.setLayout(new GridBagLayout());
		this.setBackground(new Color(0, 0, 0, 0));
		
		createJlabel(JLabelText, JLabelcolor,iconLabelPath);
		createTextField(JTextFieldSize,isJPasswordField);
		
	}
	
	public JLablePlusJTextField(String JLabelText, int JTextFieldSize, Color JLabelcolor, boolean isJPasswordField) {
		
		this(JLabelText, JTextFieldSize, JLabelcolor, isJPasswordField, null);

	}
	
	public JLablePlusJTextField(String JLabelText, int JTextFieldSize, Color JLabelcolor) {

		this(JLabelText, JTextFieldSize, JLabelcolor, false, null);

	}

	private void createJlabel(String JLabelText, Color JLabelcolor,String iconPath) {
		 
		this.label = new JLabel(JLabelText, new ImageIcon(iconPath), JLabel.CENTER);
		label.setForeground(JLabelcolor);
		this.add(label,GridBagUtils.updateGridBagConstraints(constraints,0,1, new Insets(10, 0, 0, 0)));
	}

	
	private void createTextField(int JTextFieldSize,boolean isJPasswordField) {
		
		textField = (isJPasswordField)  ? new JPasswordField(JTextFieldSize) : new JTextField(JTextFieldSize);
	
		this.add(textField,GridBagUtils.updateGridBagConstraints(constraints,0,2, new Insets(10, 0, 0, 0)));

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
