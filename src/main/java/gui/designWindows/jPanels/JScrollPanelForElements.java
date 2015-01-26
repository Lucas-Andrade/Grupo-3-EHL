package main.java.gui.designWindows.jPanels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import main.java.domain.model.Database;
import main.java.domain.model.Element;
import main.java.gui.GetElementButton;
import main.java.gui.designWindows.borders.TextRoundBorder;

@SuppressWarnings("serial")
public class JScrollPanelForElements<E extends Element > extends JPanel {

	protected Map<JButton,String> infoContainer = new HashMap<JButton,String>();
	private JScrollPane scrollPane;
	private JTextArea textArea;
	
	public JScrollPanelForElements( ) {
		
		this.setLayout(new FlowLayout());
		this.setBackground(new Color(65, 72, 78));
		scrollPane = new JScrollPane();
		textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setLineWrap(true);
		textArea.setOpaque(false);
		
		
	}

	
	public JPanel produceAJScrollPaneWithAllElements(Database<E> dataBase, Iterable<E> iterable) {
			
		JPanel listPanel = new JPanel();	
		int counter=0;
				
		try {
			for(E element:iterable){
				
				JButton button = new GetElementButton<E>( element.getIdentification(), textArea, dataBase );		
				
								
				button.add(new JLabel(element.getIdentification()));
				button.setOpaque(true);
				button.setContentAreaFilled(false);			
				button.setBackground(new Color(65, 72, 78));
				
				infoContainer.put(button, getString(element));
				
				listPanel.add(button);
				++counter;
				
			}
		} catch (Exception e) {
			
			e.printStackTrace();
		}
					
		listPanel.setLayout(new GridLayout(counter,1)); 
		listPanel.setPreferredSize(new Dimension(200,350));
		
		
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setPreferredSize(new Dimension(200,200));
		scrollPane.setBackground(new Color(65,72,78));
		scrollPane.setBorder(new TextRoundBorder(Color.WHITE,6,12,0));
		this.add(scrollPane, BorderLayout.CENTER);
		
				
		textArea.setBorder(new TextRoundBorder(Color.WHITE,6,12,0));
		textArea.setBackground(new Color(65,72,78));
		textArea.setPreferredSize(new Dimension(200, 200));
		textArea.setForeground( Color.WHITE );
		this.add(textArea, BorderLayout.CENTER);
		
		
		scrollPane.setViewportView( listPanel );
		

		return this;
	}
	
	protected String getString(E element){
		
		return element.toString();
		
	}

	
}
