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


