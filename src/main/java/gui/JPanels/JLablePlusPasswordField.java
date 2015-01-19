package main.java.gui.JPanels;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

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

		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		
		JLabel label = new JLabel(JLabelText);
		label.setForeground(JLabelColor);
		label.setBackground(new Color(0,0,0,0));
		
		if(path!=null) label.setIcon(new ImageIcon(path));
		
		GridBagConstraints labeltextBox = new GridBagConstraints();
		labeltextBox.insets = new Insets(0, 0, 5, 0);
		labeltextBox.gridx = 0;
		labeltextBox.gridy = 0;
		
		
		this.add(label, labeltextBox);
		
		
		JPasswordField textField = new JPasswordField();
		textField.setColumns(JTextFieldSize);
		
		
		GridBagConstraints textFieldBox = new GridBagConstraints();
		textFieldBox.insets = new Insets(0, 0, 5, 0);
		textFieldBox.gridx = 0;
		textFieldBox.gridy = 1;
		this.add(textField, textFieldBox);
		this.setBackground(new Color(0,0,0,0));;
	}

}
