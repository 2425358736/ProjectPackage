import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
public class Sz {
    static long b;
    JLabel e1;
    JLabel e2;
    JLabel e3;
    JLabel e4;
    JTextField l1;
    JTextField l2;
    JTextField l3;
    JTextField l4;
    JFrame fr =new JFrame();
    public Sz(){
        fr.setBounds(500, 200, 220, 300);
        fr.setLayout(new GridLayout(5,2));
        e1=new JLabel("设置雷数");
        e2=new JLabel("设置行数");
        e3=new JLabel("设置列数");
        e4=new JLabel("设置时间");
        l1=new JTextField();
        l2=new JTextField();
        l3=new JTextField();
        l4=new JTextField();
        JButton b1=new JButton("提交");
        fr.add(e1);
        fr.add(l1);
        fr.add(e2);
        fr.add(l2);
        fr.add(e3);
        fr.add(l3);
        fr.add(e4);
        fr.add(l4);
        fr.add(b1);
        b1.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                //Calendar cal=Calendar.getInstance();
                //b=cal.getTimeInMillis();
                ZJM z=new ZJM();
                //z.b=b;
                z.hang=Integer.valueOf(l2.getText());
                z.lie=Integer.valueOf(l3.getText());
                z.sl=Integer.valueOf(l1.getText());
                z.z=Integer.valueOf(l4.getText());
                z.k=z.sl;
                fr.setVisible(false);
                Thread a=new Thread((Runnable) z);
                a.start();
            }
        });
        fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fr.setVisible(true);
    }
}