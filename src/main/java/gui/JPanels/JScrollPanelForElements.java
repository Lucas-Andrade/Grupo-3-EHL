package main.java.gui.JPanels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.AbstractBorder;

import main.java.domain.model.Element;
import main.java.gui.Borders.TextRoundBorder;

@SuppressWarnings("serial")
public class JScrollPanelForElements<E extends Element > extends JPanel {

		
	public JScrollPanelForElements( Iterable <E> database) {
		
		
		this.setLayout(new BorderLayout());
		this.setPreferredSize(new Dimension(200,200));
		
		JPanel contactListPanel = new JPanel();
		
		
		int counter=0;
		
		Iterator<E> iterator=  database.iterator();
		
		while(iterator.hasNext()){
			
			JButton button = new JButton();
			JLabel label = new JLabel("Flight " +iterator.next().getIdentification());
			
			
			button.add(label);
			button.setOpaque(true);
			button.setContentAreaFilled(false);
			
			button.setBackground(new Color(65, 72, 78));
			contactListPanel.add(button);
			++counter;
		}
					
		contactListPanel.setLayout(new GridLayout(counter,1)); 
		
		JScrollPane scrollPane = new JScrollPane(contactListPanel);
		this.add(scrollPane, BorderLayout.CENTER);
		
		AbstractBorder brdr = new TextRoundBorder(Color.WHITE,6,12,0);
		
		this.setBorder(brdr);
		this.setBackground(new Color(65, 72, 78));

	}

}
