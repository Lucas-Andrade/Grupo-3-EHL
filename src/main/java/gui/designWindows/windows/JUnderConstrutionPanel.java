package main.java.gui.designWindows.windows;

import java.awt.Color;
import java.awt.FlowLayout;

import main.java.gui.designWindows.JPanels.JDialogWithBackground;
import main.java.gui.designWindows.JPanels.JPanelImage;

@SuppressWarnings("serial")
public class JUnderConstrutionPanel  extends JDialogWithBackground{

	public static void main(String[] args){
	
	new JUnderConstrutionPanel(new Color (65,72,78),370, 240);
	
}
	
	
	public JUnderConstrutionPanel(Color color, int Width, int Height) {
		super(color,Width, Height);
		initial();
	}

	private void initial(){

		this.setLayout(new FlowLayout());
		
		JPanelImage construction = new JPanelImage("src/main/resources/images/UnderConstruction.png");
		this.add(construction);
		this.setVisible(true);
	}
 }





//	@SuppressWarnings("serial")
//	public class JUnderConstrutionPanel  {
//
//		
//		public static void main(String[] args){
//			
//			new JUnderConstrutionPanel();
//			
//		}
//		
//		public JUnderConstrutionPanel(){
//		
//			
//		JFrame frame =new JFrame();			
//					
//		JOptionPane.showInternalMessageDialog(null  , " ", " ",
//				JOptionPane.INFORMATION_MESSAGE, new ImageIcon( "src/main/resources/images/UnderConstruction.png" ));
//				
//					
//						
//			
//			
//		}
		
		

//	}

