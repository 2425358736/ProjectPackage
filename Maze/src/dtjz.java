
import java.awt.GridLayout;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

import javax.swing.*;





public class dtjz {
	static int x;
	static int y;
	public static int q;
	public static int l;
	private static final JFrame asd=new JFrame();
    static String [] str;
	static int i=0;
	static String[][] cha;
	static zj zj=new zj();
	public static void main(String[] agrs){
			       try {   
			           String encoding = "GBK"; // 字符编码(可解决中文乱码问题 )   
			          File file = new File("migong.txt");   
			           if (file.isFile() && file.exists()) {   
			              InputStreamReader read = new InputStreamReader(   
			                   new FileInputStream(file), encoding);   
			              BufferedReader bufferedReader = new BufferedReader(read);   
			               String lineTXT = null;   
			                while ((lineTXT = bufferedReader.readLine()) != null) {
			                	if(i==0){
			                		System.out.println(file.length());
			                		System.out.println(lineTXT.toString().trim().length());
			                	str=new String[(int) ((file.length()+2)/(lineTXT.toString().trim().length()+2))];
			                	}
			                	str[i]=lineTXT.toString().trim();	
			                	System.out.println(str[i]);
		                     i++;
			              }   
			               read.close();   
			          }else{   
			               System.out.println("找不到指定的文件！");   
			           }   
			       } catch (Exception e) {   
			           System.out.println("读取文件内容操作出错");   
			           e.printStackTrace();   
			        }  
			       //for(int i=0;i<str.length;i++){ 
			    	//   if(str[i]==null){
			    		//   break;
			    //	   }   
			    	//   System.out.println(str[i]);	   
			     //  }
			       

		
		asd.setBounds(0, 0, 50*str[0].length(), 50*str.length+20);
		asd.setLayout(new GridLayout(str.length,str[0].length()+1));
		asd.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		asd.setLocationRelativeTo(null); 
	    		   for(int i=0;i<str.length;i++){
	    			   System.out.print(i);
	    				String [] str1=str[i].split("");
	    				for(int j=0;j<str[i].length();j++){
	    					
	    					if(str1[j].equals("0")){
	    				
	    						asd.add(new qiang());
	    					}
	    					
	    					else if(str1[j].equals("1")){
	    						asd.add(new lu());
	    					}
	    					
	    					else if(str1[j].equals("*")){
	    						asd.add(zj);
	    					}
	    					
	    					else if(str1[j].equals("#")){
	    						asd.add(new lu());
	    					}
	    				}
	    			}
	    		   //zj.setText("猪脚");
	    		   asd.addKeyListener(new KeyAdapter(){
	    				@Override
						public void keyPressed(KeyEvent e){
	    					x=zj.getX();
	    					y=zj.getY();
	    					int a=x/zj.getWidth();
							int b=y/zj.getHeight();
								
	    						if(e.getKeyCode()==KeyEvent.VK_A){
	    							if(a>0){
	    							String[] cha=str[b].split("");
	    							String str2=cha[a - 1];
	    							if(!str2.equals("0")){
	    							zj.setBounds(x-zj.getWidth(), y, zj.getWidth(), zj.getHeight());
	    							if(str2.equals("#")){
	    								
	    								new shibai();
	    								//asd.setVisible(false);
	    							}
	    							System.out.println(b);
	    							System.out.println(str[b]);
	    							System.out.println(str2);
	    							}
	    							}
	    							}
	    						else if(e.getKeyCode()==KeyEvent.VK_W){
	    							if(b>0){
	    							String[] cha=str[b-1].split("");
	    							String str2=cha[a];
	    							if(!str2.equals("0")){
	    							zj.setBounds(x, y-zj.getHeight(), zj.getWidth(), zj.getHeight());
	    							if(str2.equals("#")){
	    								new shibai();
	    								//asd.setVisible(false);
	    							}
	    							System.out.println(b);
	    							System.out.println(str[b]);
	    							System.out.println(str2);
	    							}
	    							}
	    						}
	    						else if(e.getKeyCode()==KeyEvent.VK_D){
	    							if(a<str[b].length()-2){
	    							String[] cha=str[b].split("");
	    							String str2=cha[a+1];
	    							if(!str2.equals("0")){
	    								
	    							zj.setBounds(x+zj.getWidth(), y, zj.getWidth(), zj.getHeight());
	    							if(str2.equals("#")){
	    								new shibai();
	    								//asd.setVisible(false);
	    							}
	    							System.out.println(b);
	    							System.out.println(str[b]);
	    							System.out.println(str2);
	    							}
	    							}
	    						}
	    						else if(e.getKeyCode()==KeyEvent.VK_S){
	    							if(b<str.length-1){
	    							String[] cha=str[b+1].split("");
	    							String str2=cha[a];
	    							if(!str2.equals("0")){
	    							zj.setBounds(x, y+zj.getHeight(), zj.getWidth(), zj.getHeight());
	    							if(str2.equals("#")){
	    								new shibai();
	    								//asd.setVisible(false);
	    							}
	    							System.out.println(b);
	    							System.out.println(str[b]);
	    							System.out.println(str2);
	    							}
	    						}
	    						}
	    				}
	    			});
		asd.setVisible(true);
		
		
	}
}
