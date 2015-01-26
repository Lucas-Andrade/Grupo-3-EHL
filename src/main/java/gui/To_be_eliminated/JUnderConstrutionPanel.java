package main.java.gui.To_be_eliminated;

import java.awt.FlowLayout;

import main.java.gui.designWindows.jPanels.forAll.JPanelImage;
import main.java.gui.designWindows.windows.WindowBase;

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


