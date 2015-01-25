package main.java.gui.designWindows.windows;

import java.awt.FlowLayout;

import main.java.gui.designWindows.JPanels.ForAll.JPanelImage;

@SuppressWarnings("serial")
public class JUnderConstrutionPanel  extends WindowBase{

	
	public JUnderConstrutionPanel() {
		super(350, 300);
		initial();
	}

	private void initial(){

		this.setLayout(new FlowLayout());		
		JPanelImage construction = new JPanelImage("src/main/resources/images/UnderConstruction.png");
		this.add(construction);
		this.setVisible(true);
	}
 }


