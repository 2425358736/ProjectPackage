import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class shibai {
	private static ImageIcon icon=new ImageIcon("gameover.png");;
	public shibai(){
		JFrame a=new JFrame();
		a.setSize(icon.getIconWidth(),icon.getIconHeight());
		a.setLayout(null);
		JLabel a1=new JLabel();
		a1.setIcon(icon);
		a1.setSize(icon.getIconWidth(),icon.getIconHeight());
		a.add(a1);
		a.setLocationRelativeTo(null); //设置窗口初始位置(居中)
		a.setVisible(true);

	}
}