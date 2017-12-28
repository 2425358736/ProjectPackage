
import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
public class jim{
	static final JFrame jm=new JFrame();

	static int cssd=0;
	static int cszd=100;
	static JLabel zidan=new JLabel("剩余子弹:"+String.valueOf(cszd));
	static JLabel shadi=new JLabel("杀敌数:"+String.valueOf(cssd));
	//英雄级
	private static int w=0;
	private Timer timer;
	private static JLabel a;
	private static ImageIcon icon;
	private static int x;
	private static int y;
	//敌机
	private static JLabel a2[]=new JLabel[4];
	private static int x1[]=new int[4];
	private static int y1[]=new int[4];
	private static ImageIcon icon1[]=new ImageIcon[4];
	//子弹
	private static JLabel a3[]=new JLabel[100];
	private static int x2[]=new int[100];
	private static int y2[]=new int[100];
	private static ImageIcon icon2[]=new ImageIcon[100];



	static{
		try{

			zidan.setBounds(0, 0, 100, 50);
			shadi.setBounds(0, 50, 100, 50);

			a=new JLabel();
			icon=new ImageIcon("hero1.png");
			a.setIcon(icon);
			x=(500-icon.getIconWidth())/2;
			y=980-icon.getIconHeight();
			a.setBounds(x, y, icon.getIconWidth(),icon.getIconHeight() );

			for(int i=0;i<4;i++){
				a2[i]=new JLabel();
				icon1[i]=new ImageIcon("airplane.png");
				a2[i].setIcon(icon1[i]);
				x1[i]=new Random().nextInt(500);
				y1[i]=-icon1[i].getIconHeight()-i*100;
				a2[i].setBounds(x1[i], y1[i], icon1[i].getIconWidth(),icon1[i].getIconHeight() );
			}


			for(int i=0;i<100;i++){
				a3[i]=new JLabel();
				icon2[i]=new ImageIcon("bullet.png");
				a3[i].setIcon(icon2[i]);
			}

		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public void run(){
		/*x2=x+icon.getIconWidth()/2;
    	y2=y;
    	a3.setBounds(x2, y2, icon2.getIconWidth(),icon2.getIconHeight() );*/
		timer = new Timer(); //创建定时器对象
		timer.schedule(new TimerTask(){
			public void run(){ //定时干的那个事-每10毫秒走一次
				for(int i=0;i<4;i++){
					x1[i]+=i-1;
					y1[i]+=2;
					a2[i].setBounds(x1[i], y1[i], icon1[i].getIconWidth(),icon1[i].getIconHeight() );
					if(x1[i]>=x&&x1[i]<=x+icon.getIconWidth()&&y1[i]>=y&&y1[i]<=y+icon.getIconHeight()){
						x1[i]=new Random().nextInt(500);
						y1[i]=-icon1[i].getIconHeight()-i*100;
						a2[i].setBounds(x1[i], y1[i], icon1[i].getIconWidth(),icon1[i].getIconHeight() );
						x=10000;
						y=10000;
						a.setBounds(x, y, icon.getIconWidth(),icon.getIconHeight());
						jm.setVisible(false);

						new shibai();


					}
					if(x1[i]>500||y1[i]>980){
						x1[i]=new Random().nextInt(500);
						y1[i]=-icon1[i].getIconHeight()-i*100;
						a2[i].setBounds(x1[i], y1[i], icon1[i].getIconWidth(),icon1[i].getIconHeight() );
					}
				}
				/*y2-=2;
				a3.setBounds(x2, y2, icon2.getIconWidth(),icon2.getIconHeight() );*/
				/*if(x2>=x1&&x2<=x1+icon1.getIconWidth()&&y2>=y1&&y2<y1+icon1.getIconHeight()){
					x2=x+icon.getIconWidth()/2;
			    	y2=y;
			    	a3.setBounds(x2, y2, icon2.getIconWidth(),icon2.getIconHeight() );
					x1=new Random().nextInt(500);
					y1=-icon1.getIconHeight();
					a2.setBounds(x1, y1, icon1.getIconWidth(),icon1.getIconHeight() );
				}*/
				/*if(y2<0){
					x2=x+icon.getIconWidth()/2;
			    	y2=y;
			    	a3.setBounds(x2, y2, icon2.getIconWidth(),icon2.getIconHeight() );
				}
				*/

			}
		},10,10);
	}
	public void run1(final int i){
		x2[i]=x+icon.getIconWidth()/2;
		y2[i]=y;
		a3[i].setBounds(x2[i], y2[i], icon2[i].getIconWidth(),icon2[i].getIconHeight() );
		timer = new Timer(); //创建定时器对象
		timer.schedule(new TimerTask(){
			public void run(){ //定时干的那个事-每10毫秒走一次
				y2[i]-=2;
				a3[i].setBounds(x2[i], y2[i], icon2[i].getIconWidth(),icon2[i].getIconHeight() );
				for(int j=0;j<4;j++){
					if(x2[i]>=x1[j]&&x2[i]<=x1[j]+icon1[j].getIconWidth()&&y2[i]>=y1[j]&&y2[i]<y1[j]+icon1[j].getIconHeight()){
						x2[i]=-100;
						y2[i]=-100;
						a3[i].setBounds(x2[i], y2[i], icon2[i].getIconWidth(),icon2[i].getIconHeight() );
						x1[j]=new Random().nextInt(500);
						y1[j]=-icon1[j].getIconHeight()-i*100;
						a2[j].setBounds(x1[j], y1[j], icon1[j].getIconWidth(),icon1[j].getIconHeight() );
						cssd=cssd-1;
						shadi.setText("杀敌数:"+String.valueOf(cssd));
					}
				}
			}
		},10,10);
	}

	public static void main(String[] agrs){

		jm.setBounds(0,0,500,1000);
		jm.setLayout(null);
		jm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //设置默认关闭操作(窗口关闭时退出程序)
		jm.setLocationRelativeTo(null); //设置窗口初始位置(居中)
		jm.setVisible(true);
		for(int i=0;i<4;i++){
			jm.add(a2[i]);
		}
		jm.add(a);
		jm.add(zidan);
		jm.add(shadi);

		new jim().run();
		jm.addKeyListener(new KeyAdapter(){
			public void keyPressed(KeyEvent e){
				{
					if(e.getKeyCode()==KeyEvent.VK_A){
						if(x>0){
							x-=10;
							a.setBounds(x, y, icon.getIconWidth(),icon.getIconHeight() );
							icon=new ImageIcon("hero0.png");
							a.setIcon(icon);
						}
					}

					else if(e.getKeyCode()==KeyEvent.VK_W){
						if(y>0){
							y-=10;
							a.setBounds(x, y, icon.getIconWidth(),icon.getIconHeight() );
							icon=new ImageIcon("hero0.png");
							a.setIcon(icon);
						}
					}
					else if(e.getKeyCode()==KeyEvent.VK_S){
						if(y<980-icon.getIconHeight()){
							y+=10;
							a.setBounds(x, y, icon.getIconWidth(),icon.getIconHeight() );
							icon=new ImageIcon("hero1.png");
							a.setIcon(icon);
						}
					}

					else if(e.getKeyCode()==KeyEvent.VK_D){
						if(x<485-icon.getIconWidth()){
							x+=10;
							a.setBounds(x, y, icon.getIconWidth(),icon.getIconHeight() );
							icon=new ImageIcon("hero0.png");
							a.setIcon(icon);
						}

					}else if(e.getKeyCode()==KeyEvent.VK_J){
						cszd=cszd-1;
						zidan.setText("剩余子弹:"+String.valueOf(cszd));
						if(w>=100){
							x=10000;
							y=10000;
							a.setBounds(x, y, icon.getIconWidth(),icon.getIconHeight());
							jm.setVisible(false);

							new shibai();

						}else{
							jm.add(a3[w]);
							new jim().run1(w);
							w=w+1;
						}
					}


				}
			}
			public void keyReleased(KeyEvent e){
				{
					if(e.getKeyCode()==KeyEvent.VK_A){

						icon=new ImageIcon("hero1.png");
						a.setIcon(icon);

					}

					else if(e.getKeyCode()==KeyEvent.VK_W){

						icon=new ImageIcon("hero1.png");
						a.setIcon(icon);

					}
					else if(e.getKeyCode()==KeyEvent.VK_S){

						icon=new ImageIcon("hero1.png");
						a.setIcon(icon);

					}

					else if(e.getKeyCode()==KeyEvent.VK_D){

						icon=new ImageIcon("hero1.png");
						a.setIcon(icon);


					}


				}
			}

		});
	}
}
