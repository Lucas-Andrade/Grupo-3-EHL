package main.java.gui.ByGD;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

@SuppressWarnings ("serial")
public class JLablePlusJTextField extends JPanel {
	
	// Fields
	
	private JLabel label;
	private JTextField textField;
	
	private GridBagConstraints constraints;
	
	// Constructor
	
	public JLablePlusJTextField(String labelText, Color labelColor, String path, int textFieldSize) {
	
		createLabel(labelText, labelColor, path);
		
		createTextField(labelText, textFieldSize);
		
		setPanelSpecifications();
	}
	
	// Private Auxiliar Methods
	
	private void createLabel(String labelText, Color labelColor, String path) {
	
		label = new JLabel(labelText);
		
		label.setForeground(labelColor);
		
		if (path != null)
			label.setIcon(new ImageIcon(path));
	}
	
	private void createTextField(String labelText, int textFieldSize) {
	
		if (labelText.equals("Password"))
			textField = new JPasswordField();
		else
			textField = new JTextField();
		
		textField.setColumns(textFieldSize);
	}
	
	private void setPanelSpecifications() {
	
		setLayout(new GridBagLayout());
		this.setBackground(new Color(0, 0, 0, 0));
		
		constraints = GridBagUtils.createGridBagConstraints();
		this.add(label, constraints);
		
		this.add(textField, GridBagUtils.updateGridYBagConstraints(constraints, 1));
	}
	
	// Public Get Methods
	
	public JLabel getLabel() {
	
		return label;
	}
	
	public JTextField getTextField() {
	
		return textField;
	}
}