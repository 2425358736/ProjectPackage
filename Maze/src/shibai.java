

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class shibai {
	private static ImageIcon icon=new ImageIcon("pause.png");;
	public shibai(){
	JFrame a=new JFrame();
	a.setSize(icon.getIconWidth(),icon.getIconHeight());
	a.setLayout(null);
	JLabel a1=new JLabel();
	a1.setIcon(icon);
	a1.setSize(icon.getIconWidth(),icon.getIconHeight());
	a.add(a1);
	a.setLocationRelativeTo(null); //���ô��ڳ�ʼλ��(����)
	a.setVisible(true);

	}
}