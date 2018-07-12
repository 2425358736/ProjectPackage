import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Calendar;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JMenuBar;
import javax.swing.JTextField;
import javax.swing.JPanel;
import javax.swing.event.MenuDragMouseListener;
public class ZJM extends JFrame implements Runnable{
    //初始界面
    static JFrame re=new JFrame();
    //扫雷界面
    private JPanel panel;
    //计时，计步界面
    private JPanel panel1;
    //雷按钮数组
    private JButton[][] button;
    //标签数组
    private JLabel[][] label;
    //雷的个数
    public static  int sl;
    //扫雷界面的行数
    public  int hang;
    //扫雷界面的列数
    public  int lie;
    //开始时间
    public static long b;
    //计步
    public long o=1;
    //计时
    public static int z;
    //计时口
    JTextField a4=new JTextField();
    //雷图片
    ImageIcon img = new ImageIcon("aa.jpg");
    //暂停状态
    int g=0;
    JButton a5=new JButton("暂停");
    static int k;
    public static void main(String[] args){
        //初始界面大小及定位
        re.setBounds(500, 200, 220, 100);
        //初始界面布局
        re.setLayout(null);
        JLabel e1=new JLabel("欢迎来到扫雷游戏");
        //e1标签大小
        e1.setSize(220,50);
        re.add(e1);
        JMenuBar jm=new JMenuBar();
        re.setJMenuBar(jm);
        JMenu n1=new JMenu("游戏难度");
        JMenu n2=new JMenu("自定义游戏");
        JMenuItem m5=new JMenuItem("设置");
        jm.add(n1);
        jm.add(n2);
        n2.add(m5);
        //设置鼠标触发器
        m5.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                new Sz();
                new ZJM();
                re.setVisible(false);
            }
        });
        JMenuItem m1=new JMenuItem("初级");
        JMenuItem m2=new JMenuItem("中级");
        JMenuItem m3=new JMenuItem("高级");
        n1.add(m1);
        n1.add(m2);
        n1.add(m3);
        //初级鼠标触发器
        m1.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                ZJM jm=new ZJM();
                jm.hang=10;
                jm.lie=10;
                jm.sl=5;
                jm.k=sl;
                jm.z=60;
                Thread a=new Thread((Runnable) jm);
                a.start();
                re.setVisible(false);
                //Calendar cal=Calendar.getInstance();
                //b=cal.getTimeInMillis();
            }
        });
        //中级鼠标触发器
        m2.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                ZJM jm=new ZJM();
                jm.hang=20;
                jm.lie=20;
                jm.sl=15;
                jm.k=sl;
                jm.z=100;
                Thread b1=new Thread((Runnable) jm);
                b1.start();
                re.setVisible(false);
                //Calendar cal=Calendar.getInstance();
                //b=cal.getTimeInMillis();
            }
        });
        //高级鼠标触发器
        m3.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                ZJM jm=new ZJM();

                jm.hang=30;
                jm.lie=30;
                jm.sl=25;
                jm.k=sl;
                jm.z=120;
                Thread c=new Thread((Runnable) jm);
                c.start();
                re.setVisible(false);
                //Calendar cal=Calendar.getInstance();
                //b=cal.getTimeInMillis();
            }
        });
        re.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        re.setVisible(true);
    }
    public void run(){
        final JFrame aaa=new JFrame();
        final int s=z;
        z=s;
        panel=new JPanel();
        panel1=new JPanel();
        aaa.setBounds(0, 0, 620, 680);
        aaa.setLayout(null);
        panel.setBounds(0,0, 600, 600);
        panel.setLayout(new GridLayout(hang,lie));
        panel1.setLayout(new GridLayout(1,5));
        panel1.setBounds(0, 600, 600, 40);
        aaa.add(panel1,BorderLayout.CENTER);
        button=new JButton[hang][lie];
        label=new JLabel[hang][lie];
        JLabel a1=new JLabel("计步");
        final JTextField a2=new JTextField();
        JLabel a3=new JLabel("计时");
        //分割label块
        for(int i=0;i<hang;i++){
            for(int j=0;j<lie;j++){
                label[i][j]=new JLabel(String.valueOf((int)(Math.random()*1)));
                //label[i][j].setBounds(i*20, j*20,20,20);
                aaa.add(label[i][j]);
                label[i][j].setVisible(false);
            }
        }
        //分割button块
        for(int i=0;i<hang;i++){
            for(int j=0;j<lie;j++){
                button[i][j]=new JButton();
                button[i][j].setMargin(new Insets(0,0,0,0));
                //button[i][j].setBounds(i*20, j*20,20,20);
                panel.add(button[i][j]);
            }
        }
        //随机分布雷的位置
        for(int i=0;i<sl;i++){
            int a=(int)(Math.random()*hang);
            int b=(int)(Math.random()*lie);
            if(!label[a][b].getText().equals("*")){
                label[a][b].setText("*");
            }else{i--;}
        }
        panel1.add(a1);
        panel1.add(a2);
        panel1.add(a3);
        panel1.add(a4);
        panel1.add(a5);
        a5.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                if(a5.getText().equals("暂停")){
                    a5.setText("开始");
                    g=1;
                }else if(a5.getText().equals("开始")){
                    a5.setText("暂停");
                    g=0;
                }
            }
        });
        //设置label的值
        for(int i=0;i<hang;i++){
            for(int j=0;j<lie;j++){
                int sum = 0;
                for(int l=0;l<1;l++){
                    switch(l){
                        case 0:
                            if(i+1>=0&&i+1<hang){
                                if(label[(i+1)][j].getText().equals("*")){
                                    sum=sum+1;
                                }
                            }
                        case 1:
                            if((i+1>=0&&i+1<hang)&&(j+1>=0&&j+1<lie)){
                                if(label[(i+1)][(j+1)].getText().equals("*")){
                                    sum=sum+1;
                                }
                            }
                        case 2:
                            if((i+1>=0&&i+1<hang)&&(j-1>=0&&j-1<lie)){
                                if(label[(i+1)][(j-1)].getText().equals("*")){
                                    sum=sum+1;
                                }
                            }
                        case 3:
                            if((j+1>=0&&j+1<lie)){
                                if(label[(i)][(j+1)].getText().equals("*")){
                                    sum=sum+1;
                                }
                            }
                        case 4:
                            if((j-1>=0&&j-1<lie)){
                                if(label[(i)][(j-1)].getText().equals("*")){
                                    sum=sum+1;
                                }
                            }
                        case 5:
                            if((i-1>=0&&i-1<hang)){
                                if(label[(i-1)][j].getText().equals("*")){
                                    sum=sum+1;
                                }
                            }
                        case 6:
                            if((i-1>=0&&i-1<hang)&&(j+1>=0&&j+1<lie)){
                                if(label[(i-1)][(j+1)].getText().equals("*")){
                                    sum=sum+1;
                                }
                            }
                        case 7:
                            if((i-1>=0&&i-1<hang)&&(j-1>=0&&j-1<lie)){
                                if(label[(i-1)][(j-1)].getText().equals("*")){
                                    sum=sum+1;
                                }
                            }
                    }
                    if(!label[i][j].getText().equals("*")){
                        label[i][j].setText(String.valueOf(sum));
                    }
                }
            }
        }
        //点击事件设置button的值
        for( int i=0;i<hang;i++){
            for(int j=0;j<lie;j++){
                final int a=i;
                final int b=j;
                button[i][j].addMouseListener(new MouseListener(){
                    //若果点击为零显示四周的button值
                    public void bl(int w,int y){
                        if(label[w][y].getText().equals("0")){
                            button[w][y].setBackground(Color.blue);
                            for(int i=w-1;i<=w+1;i++){
                                for(int j=y-1;j<=y+1;j++){
                                    if((i>=0&&i<hang)&&(j>=0&&j<lie)&&(i!=w||j!=y)){
                                        if(!button[i][j].getText().equals("雷")){
                                            if(!button[i][j].getText().equals("0")){
                                                button[i][j].setText(label[i][j].getText());
                                                int size=10;button[i][j].setFont(new Font("Serif",Font.PLAIN,size));
                                                if(button[i][j].getText().equals("0")){
                                                    button[i][j].setBackground(Color.blue);
                                                }else{
                                                    button[i][j].setBackground(Color.green);

                                                }
                                                bl(i,j);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    //右键触发器
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        if((e.getModifiers()&InputEvent.BUTTON1_MASK)!=0) {
                            //button[a][b].setVisible(false);
                            //label[b][a].setVisible(true);
                            a2.setText(String.valueOf(o++));
                            button[a][b].setText(label[a][b].getText());
                            int size=10;button[a][b].setFont(new Font("Serif",Font.PLAIN,size));
                            if(!button[a][b].getText().equals("0")&&!button[a][b].getText().equals("*")){
                                button[a][b].setBackground(Color.green);
                            }else if(button[a][b].getText().equals("0")){
                                button[a][b].setBackground(Color.blue);
                            }else{
                                button[a][b].setText("");
                                Image image=img.getImage().getScaledInstance(button[a][b].getWidth(),
                                        button[a][b].getHeight(), img.getImage().SCALE_DEFAULT);  ;
                                img.setImage(image);
                                button[a][b].setIcon(img);
                            }
                            this.bl(a,b);
                            //失败
                            if(z>0){
                                if(button[a][b].getText().equals("")){
                                    g=1;
                                    for(int i1=0;i1<hang;i1++){
                                        for(int j1=0;j1<lie;j1++){
                                            if(label[i1][j1].getText().equals("*")){
                                                button[i1][j1].setText("");
                                                button[i1][j1].setIcon(img);
                                            }
                                        }
                                    }
                                    final JFrame me=new JFrame();
                                    me.setBounds(0, 0, 600, 200);
                                    me.setLayout(null);
                                    //Calendar cal=Calendar.getInstance();
                                    //final long a=cal.getTimeInMillis();
                                    Sz c=new Sz();
                                    c.fr.setVisible(false);
                                    JLabel v=new JLabel("你完蛋了用时"+(s-z)+"秒"+"走了"+(o-1)+"步");
                                    Button t=new Button("再来一次");
                                    Button b=new Button("重新开始");
                                    v.setBounds(0, 0, 600, 70);
                                    t.setBounds(0,70, 300, 100);
                                    b.setBounds(300,70, 300, 100);
                                    //开始第二次游戏
                                    t.addActionListener(new ActionListener(){
                                                            @Override
                                                            public void actionPerformed(ActionEvent e) {
                                                                me.setVisible(false);
                                                                aaa.setVisible(false);
                                                                new ZJM();
                                                                re.setVisible(true);
                                                            }
                                                        }
                                    );
                                    //重新开始第一次游戏
                                    b.addActionListener(new ActionListener(){
                                                            public void actionPerformed(ActionEvent e){
                                                                g=0;
                                                                for( int i=0;i<hang;i++){
                                                                    for(int j=0;j<lie;j++){
                                                                        button[i][j].setText("");
                                                                        button[i][j].setIcon(null);
                                                                        button[i][j].setBackground(null);
                                                                    }
                                                                }
                                                                //Calendar cal=Calendar.getInstance();
                                                                //new ZJM().b=cal.getTimeInMillis();
                                                                me.setVisible(false);
                                                                z=s+1;
                                                                a2.setText("0");
                                                                o=1;
                                                            }
                                                        }
                                    );
                                    me.add(b);
                                    me.add(t);
                                    me.add(v);
                                    me.setVisible(true);
                                    me.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                                }
                                //成功
                                int sum=0;
                                for( int i=0;i<hang;i++){
                                    for(int j=0;j<lie;j++){
                                        if(button[i][j].getText().equals("")){
                                            sum++;
                                        }
                                    }
                                }
                                if(sum==sl){
                                    g=1;

                                    //Calendar cal=Calendar.getInstance();
                                    //long a=cal.getTimeInMillis();
                                    Sz c=new Sz();
                                    c.fr.setVisible(false);
                                    final JFrame me=new JFrame();
                                    me.setLayout(null);
                                    me.setBounds(0, 0, 400, 240);
                                    Button t=new Button("再来一次");
                                    t.setBounds(0, 100, 400, 100);
                                    t.addActionListener(new ActionListener(){
                                                            @Override
                                                            public void actionPerformed(ActionEvent e) {
                                                                me.setVisible(false);
                                                                aaa.setVisible(false);
                                                                new ZJM();
                                                                re.setVisible(true);
                                                            }
                                                        }
                                    );
                                    JLabel v=new JLabel("你成功了"+"用时"+(s-z)+"秒"+"走了"+(o-1)+"步");
                                    v.setBounds(0, 0, 400, 100);
                                    me.add(v);
                                    me.add(t);
                                    me.setVisible(true);
                                    me.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                                }
                            }
                        }if ((e.getModifiers()&InputEvent.BUTTON3_MASK)!=0) {
                            if(k>=0){

                                if(button[a][b].getText().equals("")){
                                    button[a][b].setText("雷");
                                    k--;
                                }
                                else if(button[a][b].getText().equals("雷")){
                                    button[a][b].setText("");
                                    k++;
                                }
                            }
                            if(k<0){
                                if(button[a][b].getText().equals("雷")){
                                    button[a][b].setText("");
                                    k++;
                                }
                            }
                            int s1=0;
                            for( int i=0;i<hang;i++){
                                for(int j=0;j<lie;j++){
                                    if(label[i][j].getText().equals("*")&&button[i][j].getText().equals("雷")){
                                        s1++;
                                    }
                                }
                            }
                            if(s1==sl){
                                g=1;
                                Sz c=new Sz();
                                c.fr.setVisible(false);
                                final JFrame me=new JFrame();
                                me.setLayout(null);
                                me.setBounds(0, 0, 400, 240);
                                Button t=new Button("再来一次");
                                t.setBounds(0, 100, 400, 100);
                                t.addActionListener(new ActionListener(){
                                                        public void actionPerformed(ActionEvent e) {
                                                            me.setVisible(false);
                                                            aaa.setVisible(false);
                                                            new ZJM();
                                                            re.setVisible(true);
                                                        }
                                                    }
                                );
                                JLabel v=new JLabel("你成功了"+"用时"+(s-z)+"秒"+"走了"+(o-1)+"步");
                                v.setBounds(0, 0, 400, 100);
                                me.add(v);
                                me.add(t);
                                me.setVisible(true);
                                me.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                            }
                        }

                    }
                    @Override
                    public void mouseEntered(MouseEvent e) {
                        // TODO Auto-generated method stub

                    }

                    @Override
                    public void mouseExited(MouseEvent e) {
                        // TODO Auto-generated method stub

                    }

                    @Override
                    public void mousePressed(MouseEvent e) {
                        // TODO Auto-generated method stub

                    }

                    @Override
                    public void mouseReleased(MouseEvent e) {
                        // TODO Auto-generated method stub
                    }
                });
            }
        }
        aaa.add(panel,BorderLayout.CENTER);
        aaa.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        aaa.setVisible(true);
        for(z=z;z>=0;z--){
            try {
                if(g==1){
                    z++;
                }
                a4.setText(String.valueOf(z));
                Thread.sleep(1000);
            } catch (InterruptedException e) {

                e.printStackTrace();
            }

        }
        if(aaa.isShowing()){
            if (z<0){
                for(int i1=0;i1<hang;i1++){
                    for(int j1=0;j1<lie;j1++){
                        if(label[i1][j1].getText().equals("*")){
                            Image image=img.getImage().getScaledInstance(button[i1][j1].getWidth(),
                                    button[i1][j1].getHeight(), img.getImage().SCALE_DEFAULT);  ;
                            img.setImage(image);
                            button[i1][j1].setIcon(img);
                        }
                    }
                }
                final JFrame me=new JFrame();
                me.setLayout(null);
                me.setBounds(0, 0, 400, 240);
                JLabel v=new JLabel("时间到雷出现");
                v.setBounds(0, 0, 400, 100);
                Button t=new Button("再来一次吧");
                t.setBounds(0, 100, 400, 100);
                t.addActionListener(new ActionListener(){
                                        public void actionPerformed(ActionEvent e) {
                                            me.setVisible(false);
                                            aaa.setVisible(false);
                                            new ZJM();
                                            re.setVisible(true);
                                        }
                                    }
                );
                me.add(v);
                me.add(t);
                me.setVisible(true);
                me.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            }
        }
    }
}