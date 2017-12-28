import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;



public class zj extends JLabel{
	
	

	static ImageIcon img;
	public zj(){
		img=new ImageIcon("IUI2Y8EZAE2]KQ3_6IH[@Q4.png");
		 img.setImage(img.getImage().getScaledInstance(50,50,Image.SCALE_DEFAULT)); 
		this.setIcon(img); 
		
	}
}
