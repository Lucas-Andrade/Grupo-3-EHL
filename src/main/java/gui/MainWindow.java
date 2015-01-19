package main.java.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.Collection;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import main.java.gui.JPanels.JPanelImage;

public class MainWindow extends JFrame {

	private static final long serialVersionUID = 1L;

	

	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new MainWindow();
								
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	public MainWindow() {
	
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(100, 100, 1350, 720);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setIconImage(Toolkit.getDefaultToolkit().getImage("src/main/resources/images/radar.png"));
		this.setTitle("Air Traffic Controll");
		
				
		JPanel contentPane = new JPanel();
		
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(new Color(65, 72, 78));
		setContentPane(contentPane);
		
		
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0};
		gbl_contentPane.columnWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		
		contentPane.setLayout(gbl_contentPane);
		
		
		JPanelImage worldMap = new JPanelImage("src/main/resources/images/map2.jpg");
		GridBagConstraints Box = new GridBagConstraints();
		
		Box.insets = new Insets(0, 0, 0, 0);
		Box.gridx = 0;
		Box.gridy = 0;
		
		getContentPane().add(worldMap, Box);
		

				
		JLabel militaryAirship = new JLabel();
		militaryAirship.setIcon(new ImageIcon("src/main/resources/images/militaryAirship.png"));
		militaryAirship.setLocation(50, 50);
		
		worldMap.add(militaryAirship);
		
		 JList<JLabel> test = new JList<JLabel>();
      
		 JScrollPane scrollBar = new JScrollPane(test, JScrollPane.VERTICAL_SCROLLBAR_NEVER, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
     
        
		 scrollBar.setPreferredSize(new Dimension(200,200));
       
      

        
//        panel.add(new JLabel("Sporting Club Portugal"));
//        panel.add(new JLabel("Rivais da 2ª Circular"));
//        panel.add(	new JLabel("Rivais do Norte"));
//        panel.add(new JLabel("Rivais de Braga"));
//        panel.add(new JLabel("Rivais de Guimarães"));
//        panel.add(new JLabel("Rivais da Madeira"));
//        panel.setBackground(Color.WHITE);
        
        
		GridBagConstraints Box2 = new GridBagConstraints();
		
		Box2.insets = new Insets(0, 0, 0, 0);
		Box2.gridx = 1;
		Box2.gridy = 0;
		
		getContentPane().add(scrollBar, Box2);
	
	      
	      
		setVisible(true);
		
		
		
		
	}

}
