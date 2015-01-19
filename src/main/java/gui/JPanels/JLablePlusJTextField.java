package main.java.gui.JPanels;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class JLablePlusJTextField extends JPanel {

	private static final long serialVersionUID = 1L;

	private String JLabelText;
	private int JTextFieldSize;
	
	private Color JLabelColor = Color.BLACK ;
	
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

		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		
		JLabel label = new JLabel(JLabelText);
		label.setForeground(JLabelColor);
		label.setBackground(new Color(0,0,0,0));
		
		GridBagConstraints labeltextBox = new GridBagConstraints();
		labeltextBox.insets = new Insets(0, 0, 5, 0);
		labeltextBox.gridx = 0;
		labeltextBox.gridy = 0;
		
		this.add(label, labeltextBox);
		
		
		JTextField textField = new JTextField();
		textField.setColumns(JTextFieldSize);

		
		GridBagConstraints textFieldBox = new GridBagConstraints();
		textFieldBox.insets = new Insets(0, 0, 5, 0);
		textFieldBox.gridx = 0;
		textFieldBox.gridy = 1;
		this.add(textField, textFieldBox);
		this.setBackground(new Color(0,0,0,0));;
	}

}
