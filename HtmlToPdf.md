# html 转 pdf
## 第一步 引用需求包Itext和core-renderer
    <dependency>
        <groupId>com.lowagie</groupId>
        <artifactId>itext</artifactId>
        <version>2.0.8</version>
    </dependency>
    <dependency>
        <groupId>org.xhtmlrenderer</groupId>
        <artifactId>core-renderer</artifactId>
        <version>R8</version>
    </dependency>
## 第二步 下载字体文件 simsun.ttc
## 第三步 准备html文件
```html
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
 <head> 
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" /> 
  <title>刘志强</title> 
  <style type="text/css" mce_bogus="1">
	    body {font-family:SimSun}
	    @page {
          size: 310mm 200mm;
        }
		h2{ font-size: 26px; font-weight: bold; height: 60px; line-height: 60px; margin:0px; padding:10px 0 0 0; text-align: center; }
		table td{ text-align: center; }
		.wrap>table{ border-left: 1px #333 solid; border-top: 1px #333 solid; font-size: 16px; }
		.wrap>table>tbody>tr>td{ height: 25px; padding: 5px; border-right: 1px #333 solid; border-bottom: 1px #333 solid; }
		.wrap{ width: 1024px; font-size: 16px;}
	</style> 
 </head> 
 <body style="height:900px"> 
  <div class="wrap"> 
   <h2>A统计表</h2> 
   <table width="100%" cellpadding="0" cellspacing="0"> 
    <tbody>
     <tr> 
      <td>房间编号</td> 
      <td>本次抄表数</td> 
      <td>上次抄表数</td> 
      <td>电量（Kwh）</td> 
      <td>变比</td> 
      <td>总电量</td> 
      <td>单价</td> 
      <td>金额</td> 
      <td>备注</td> 
     </tr> 
     <tr> 
      <td>A</td> 
      <td>A</td> 
      <td>A</td> 
      <td>A</td> 
      <td>A</td> 
      <td>A</td> 
      <td>A</td> 
      <td>A</td> 
      <td>A</td> 
     </tr> 
     <tr> 
      <td>A</td> 
      <td>A</td> 
      <td>A</td> 
      <td>A</td> 
      <td>A</td> 
      <td>A</td> 
      <td>A</td> 
      <td>A</td> 
      <td>A</td> 
     </tr> 
     <tr> 
      <td>A</td> 
      <td>A</td> 
      <td>A</td> 
      <td>A</td> 
      <td>A</td> 
      <td>A</td> 
      <td>A</td> 
      <td>A</td> 
      <td>A</td> 
     </tr> 
    </tbody>
   </table> 
   <h2>B用水统计表</h2> 
   <table width="100%" cellpadding="0" cellspacing="0"> 
    <tbody>
     <tr> 
      <td>房间编号</td> 
      <td>本次抄表读数m&sup3;</td> 
      <td>上次抄表读数</td> 
      <td>本次用水量m&sup3;</td> 
      <td>损耗</td> 
      <td>总用水量m&sup3;</td> 
      <td>单价</td> 
      <td>金额</td> 
     </tr> 
     <tr> 
      <td align="center">B</td> 
      <td align="center">B</td> 
      <td align="center">B</td> 
      <td align="center">B</td> 
      <td align="center">B</td> 
      <td align="center">B</td> 
      <td align="center">B</td> 
      <td align="center">B</td> 
     </tr> 
     <tr> 
      <td align="center">B</td> 
      <td align="center">B</td> 
      <td align="center">B</td> 
      <td align="center">B</td> 
      <td align="center">B</td> 
      <td align="center">B</td> 
      <td align="center">B</td> 
      <td align="center">B</td> 
     </tr> 
     <tr> 
      <td align="center">B</td> 
      <td align="center">B</td> 
      <td align="center">B</td> 
      <td align="center">B</td> 
      <td align="center">B</td> 
      <td align="center">B</td> 
      <td align="center">B</td> 
      <td align="center">B</td> 
     </tr> 
    </tbody>
   </table> 
   <p style="text-align: right;">制表：刘志强</p> 
  </div>  
 </body>
</html>
```
@page {size: 310mm 200mm;}
@page设置打印页面大小 310毫米宽，200毫米高 
body {font-family:SimSun}
设置字体
## 准备编写工具类 HtMLToPdf
``` java
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import org.xhtmlrenderer.pdf.ITextFontResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;
import com.lowagie.text.pdf.BaseFont;
/**
 * Created by SCKJ on 2017/12/25.
 */
public class HtmltoPDF  {
    public static void main(String[] ages) throws Exception {
        String html = UrlReqUtil.get("file:///D:/%E6%88%91%E7%9A%84%E6%96%87%E6%A1%A3/%E6%A1%8C%E9%9D%A2/html/%E6%96%B0%E5%BB%BA%E6%96%87%E6%9C%AC%E6%96%87%E6%A1%A3.html");
        htmlToPdfString(html,"D:/pdfString.pdf");
        htmlToPdfFile("D:/我的文档/桌面/html/curriculumVitae.html","D:/pdf.pdf");
    }
    // 支持中文
    public static void htmlToPdfString(String html,String pdfPath) throws Exception {
        OutputStream os = new FileOutputStream(pdfPath);
        ITextRenderer renderer = new ITextRenderer();
        //支持中文
        ITextFontResolver fontResolver = renderer.getFontResolver();
        fontResolver.addFont("C:/Windows/fonts/simsun.ttc", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
        renderer.setDocumentFromString(html);
        // 解决图片的相对路径问题
        // renderer.getSharedContext().setBaseURL("file:/F:/teste/html/");
        renderer.layout();
        renderer.createPDF(os);
        System.out.println("======转换成功!");
        os.close();
    }

    public static void htmlToPdfFile(String htmlPath,String pdfPath) throws Exception{
        OutputStream os = new FileOutputStream(pdfPath);
        ITextRenderer renderer = new ITextRenderer();
        //支持中文
        ITextFontResolver fontResolver = renderer.getFontResolver();
        fontResolver.addFont("C:/Windows/fonts/simsun.ttc", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
        String url = new File( htmlPath ).toURI().toURL().toString();
        System.out.println("=============url: "+url);
        renderer.setDocument(url);
        renderer.layout();
        renderer.createPDF(os);
        System.out.println("======转换成功!");
        os.close();
    }
}
```


## 利用html2canvas 和 jspdf 实现html转pdf
```
let dom = document.getElementById("pdf");
		html2canvas(dom).then(canvas => {
		let url = canvas.toDataURL("image/png",1);
		// let imgHtml = new Image();
		// imgHtml.src = url;
		// dom.innerHTML = '';
		// dom.appendChild(imgHtml)
		let pdf = new jsPDF('', 'pt', 'a4');
		pdf.addImage(url, 'png', 10, 60)
		pdf.save('www.pdf');
		});
```
注释的部分是转图片
## 完整代码
```
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
 <head> 
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8" /> 
  <title>个人简历</title> 
  <style>
#title{
color:#F00;
font-size:28px
}
.secondTitle{


font-size:18px;
background-color:#09F;
text-align:center;
}


body{ 
text-align:center;
font-size:16px;
font-family:SimSun;

}
ul{
text-align:left;}
li{
text-align:left;}
.info{
text-align:left;
font-size:16px;
}
td{
border-color:#09F;
}
tr{
border-color:#09F;}

</style> 
  <script type="text/javascript" src="http://html2canvas.hertzen.com/dist/html2canvas.js"></script> 
  <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jspdf/1.3.5/jspdf.debug.js"></script> 
  <script type="text/javascript">
     function HtmlToPdf(){
		let dom = document.getElementById("pdf");
		html2canvas(dom).then(canvas => {
		let url = canvas.toDataURL("image/png",1);
		// let imgHtml = new Image();
		// imgHtml.src = url;
		// dom.innerHTML = '';
		// dom.appendChild(imgHtml)
		let pdf = new jsPDF('', 'pt', 'a4');
		pdf.addImage(url, 'png', 10, 60)
		pdf.save('www.pdf');
		});
	 }
</script> 
 </head> 
 <body> 
  <strong onclick="HtmlToPdf()"> 转储pdf </strong> 
  <table id="pdf" align="center" width="660" border="0" bordercolor="#00CCFF" frame="box"> 
   <caption id="title"> 
    <strong> 个人简历</strong> 
   </caption> 
   <tbody> 
    <tr> 
     <td colspan="5" class="secondTitle">基本资料</td> 
    </tr> 
    <tr> 
     <td width="116">姓&nbsp;名</td> 
     <td width="134">刘志强</td> 
     <td width="79">性&nbsp;别</td> 
     <td width="166">男</td> 
     <td width="129" rowspan="5"><img src="./25731425.jpg" width="105" height="150" alt="1" /></td> 
    </tr> 
    <tr> 
     <td>年&nbsp;龄</td> 
     <td>24</td> 
     <td>籍贯</td> 
     <td>汉族</td> 
    </tr> 
    <tr> 
     <td>民&nbsp;族</td> 
     <td>汉</td> 
     <td>政治面貌</td> 
     <td>团员</td> 
    </tr> 
    <tr> 
     <td>毕业学校</td> 
     <td colspan="3">临沂大学</td> 
    </tr> 
    <tr> 
     <td>专&nbsp;业</td> 
     <td>软件技术</td> 
     <td>学&nbsp;历</td> 
     <td>专科</td> 
    </tr> 
    <tr> 
     <td colspan="5" class="secondTitle">个人技能</td> 
    </tr> 
    <tr> 
     <td>个人技能</td> 
     <td colspan="4"> 
      <ul> 
       <li>熟练掌握Java</li> 
       <li>熟练掌握Linux系统和docker等技术</li> 
       <li>熟练掌握svn,git,maven等技术</li> 
       <li>熟练掌握idea,myeclipse等开发工具</li> 
       <li>熟练掌握spring struts hibernate，springMvc,springBoot,springcloud,Mybatis,Lavavel 等各种服务端框架</li> 
       <li>基础掌握asp php</li> 
       <li>熟练掌握SQl Server,mysql,redis,memcached等关系型非关系型数据库</li> 
       <li>熟练掌握react vue html js jquey css 等前端技术</li> 
       <li>熟练掌握jsp freemarker velocity等模板技术</li> 
      </ul> </td> 
    </tr> 
    <tr> 
     <td>个人专长</td> 
     <td colspan="4" class="info"> 逻辑能力学习能力强，热爱学习掌握新的技术 </td> 
    </tr> 
    <tr> 
     <td colspan="5" class="secondTitle">项目经验</td> 
    </tr> 
    <tr> 
     <td>年月</td> 
     <td>项目名称</td> 
     <td colspan="3">项目说明</td> 
    </tr> 
    <tr> 
     <td>2013年</td> 
     <td>学生管理系统</td> 
     <td colspan="3"> 实现管理员登录注册<br /> 实现学生的增删改查 </td> 
    </tr> 
    <tr> 
     <td>2014年</td> 
     <td>二手房交易系统</td> 
     <td colspan="3"> 实现用户的注册登录<br /> 实现管理员帐号<br /> 实现用户购买寄售房屋 房屋信息的显示（分页）<br /> </td> 
    </tr> 
    <tr> 
     <td>2015年</td> 
     <td>捕鱼达人</td> 
     <td colspan="3"> 捕鱼达人基本功能的实现 </td> 
    </tr> 
    <tr> 
     <td colspan="5" class="secondTitle">实践与实习</td> 
    </tr> 
    <tr> 
     <td>2011.6-2012.9</td> 
     <td colspan="4"></td> 
    </tr> 
    <tr> 
     <td>2012.6-2013.9</td> 
     <td colspan="4"></td> 
    </tr> 
    <tr> 
     <td>2014.6-2015.9</td> 
     <td colspan="4"></td> 
    </tr> 
    <tr> 
     <td>2015.9-2015.1</td> 
     <td colspan="4"></td> 
    </tr> 
    <tr> 
     <td colspan="5" class="secondTitle">个人爱好</td> 
    </tr> 
    <tr> 
     <td>兴趣爱好</td> 
     <td colspan="4">游戏、篮球</td> 
    </tr> 
    <tr> 
     <td colspan="5" class="secondTitle">自我评价</td> 
    </tr> 
    <tr> 
     <td colspan="5"> </td> 
    </tr> 
    <tr> 
     <td colspan="5" class="secondTitle">联系方式</td> 
    </tr> 
    <tr> 
     <td>移动电话</td> 
     <td>157***</td> 
     <td>QQ</td> 
     <td colspan="2">450**</td> 
    </tr> 
    <tr> 
     <td>e-mail</td> 
     <td>xiaoshidebukuai@163.com</td> 
     <td>CSDN</td> 
     <td colspan="2">xiaoshidetaikuai20</td> 
    </tr> 
   </tbody> 
  </table>  
 </body>
</html>
```
