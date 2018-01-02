## SpringBoot 搭建教程
### 1. SpringBoot简单搭建
#### 第一步： 新建maven项目
##### 引入spring boot开发依赖包
```
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>SpringBoot</groupId>
    <artifactId>SpringBoot</artifactId>
    <version>1.0-SNAPSHOT</version>
    
    
    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>1.4.0.RELEASE</version>
        <relativePath/>
    </parent>
    <dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
    </dependencies>

</project>
```
##### 新建spring boot启动类 SpringBootAppliction.java
```
package com.liuzhiqiang;

/**
 * Created by SCKJ on 2018/1/2.
 */
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by liuzhiqiang on 2017/9/7.
 */
@SpringBootApplication
@Controller
@EnableWebMvc
public class SpringBootAppliction extends WebMvcConfigurerAdapter implements CommandLineRunner {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootAppliction.class, args);
    }

    @RequestMapping("/test")
    @ResponseBody
    Map<String,Object> test() {
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("作者","刘志强");
        map.put("时间",new Date());
        return map;
    }


    @Override
    public void run(String... strings) throws Exception {
        System.out.println("服务器已起动");
    }
}

```
==注意：     SpringBootAppliction类不能直接放在Java目录下==
##### idea 配置启动项
![image](https://note.youdao.com/yws/api/personal/file/79593EDCAD7B40528A16B5DAEFEF3BE4?method=download&shareKey=31d3dd0182434b859d5498bf87a0f3d6)

点击edit configuration

![image](https://note.youdao.com/yws/api/personal/file/72E933D20D2741DF8ED6DAE94FED0D64?method=download&shareKey=951d926b8f591b7431f33f7e380dc139)

##### 启动后 浏览器输入 http://localhost:8080/test

![image](https://note.youdao.com/yws/api/personal/file/3AE8F67079E7448798623310EA0540AE?method=download&shareKey=951d926b8f591b7431f33f7e380dc139)


第一阶段Springboot完毕


### 2 模板引擎和application.yml配置

#### /src/main/resources/application.yml
```
spring:
  application:
    name: SpringBoot
  #freemarker 模板引擎配置
  freemarker:
    cache: false
    charset: UTF-8
    check-template-location: true
    content-type: text/html
    expose-request-attributes: true
    expose-session-attributes: true
    request-context-attribute: request
    template-loader-path: classpath:/templates
#端口号修改
server:
  port: 8484
```
#### 引用freemarker依赖包
```
        <!-- freemarker模板引擎-->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-freemarker</artifactId>
        </dependency>
        <!--freemarker模板引擎end-->
```
#### 创建控制器 IndexController
```
package com.liuzhiqiang.controller;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by liuzhiqiang on 2018/1/2.
 */
@RestController
public class IndexController {
    @RequestMapping("/index")
    ModelAndView index(ModelMap modelMap) {
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("userName","刘志强");
        map.put("时间",new Date());
        modelMap.put("map",map);
        return new ModelAndView("index", modelMap);
    }
}

```
#### 新建freemarker页面 index.ftl
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
 </head> 
 <body>
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
     <td width="134">${map.userName}</td>
     <td width="79">性&nbsp;别</td> 
     <td width="166">男</td> 
     <td width="129" rowspan="5"><img src="https://avatars2.githubusercontent.com/u/25731425?s=40&v=4" width="105" height="150" alt="1" /></td>
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
    </tbody>
  </table>  
 </body>
</html>
```
#### SpringBootAppliction类中增加
```
    // 跟目录重定向
    @RequestMapping("/")
    String home() {
        return "redirect:index";
    }
```
#### 在浏览器访问http://localhost:8484
![image](https://note.youdao.com/yws/api/personal/file/3E1D938C6DF243B99022DF6E028F62EB?method=download&shareKey=951d926b8f591b7431f33f7e380dc139)

### 至此 springboot实现spring mvc 的功能已全部实现

### 2 SpringBoot mybatis整合
#### 引入mybatis依赖开发包
```
        <!--mybatis-->
        <dependency>
            <groupId>org.mybatis.spring.boot</groupId>
            <artifactId>mybatis-spring-boot-starter</artifactId>
            <version>RELEASE</version>
        </dependency>
        <dependency>
            <groupId> mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <version>5.1.21</version>
        </dependency>
        <!--end-->
```
#### pom.xml 配置mybatis-generator插件，数据库表自动生成 dao xml model
```
<!--mybatis-generator数据表自动生成-->
            <plugin>
                <groupId>org.mybatis.generator</groupId>
                <artifactId>mybatis-generator-maven-plugin</artifactId>
                <version>1.3.5</version>
                <dependencies>
                    <dependency>
                        <groupId> mysql</groupId>
                        <artifactId>mysql-connector-java</artifactId>
                        <version>5.1.21</version>
                    </dependency>
                    <dependency>
                        <groupId>org.mybatis.generator</groupId>
                        <artifactId>mybatis-generator-core</artifactId>
                        <version>1.3.5</version>
                    </dependency>
                </dependencies>
                <configuration>
                    <!--允许移动生成的文件 -->
                    <verbose>true</verbose>
                    <!-- 是否覆盖 -->
                    <overwrite>true</overwrite>
                    <!-- 自动生成的配置 -->
                    <configurationFile>
                        src/main/resources/mybatis/generatorConfig.xml</configurationFile>
                </configuration>
            </plugin>
```
#### 创建mybatis-generator配置文件generatorConfig.xml
```
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE generatorConfiguration
        PUBLIC "-//mybatis.org//DTD MyBatis Generator Configuration 1.0//EN"
        "http://mybatis.org/dtd/mybatis-generator-config_1_0.dtd">
<generatorConfiguration>
    <context id="MysqlContext" targetRuntime="MyBatis3" defaultModelType="flat">
        <property name="beginningDelimiter" value="`"/>
        <property name="endingDelimiter" value="`"/>

        <commentGenerator>
            <property name="suppressDate" value="true"/>
        </commentGenerator>

        <!--配置数据源-->
        <jdbcConnection driverClass="com.mysql.jdbc.Driver"
                        connectionURL="jdbc:mysql://localhost:3306/test"
                        userId="root"
                        password="root">
        </jdbcConnection>
        <!--model所在目录-->
        <javaModelGenerator targetPackage="com.liuzhiqiang.model" targetProject="src\main\java">
            <property name="trimStrings" value="true" />
        </javaModelGenerator>
        <!--xml所在目录-->
        <sqlMapGenerator targetPackage="mappers"  targetProject="src\main\resources"/>
        <!--dao所在目录-->
        <javaClientGenerator type="XMLMAPPER" targetPackage="com.liuzhiqiang.dao"  targetProject="src\main\java"/>

        <table tableName="%">
            <generatedKey column="id" sqlStatement="Mysql"/>
        </table>
    </context>
</generatorConfiguration>
```
#### 点击下图所标注的
![image](https://note.youdao.com/yws/api/personal/file/39CC0476126A4EA29C8209C6B352E2E8?method=download&shareKey=951d926b8f591b7431f33f7e380dc139)
#### 会自动创建
![image](https://note.youdao.com/yws/api/personal/file/5815B3D14C3F4E6FA7E87E2B626E1B7B?method=download&shareKey=951d926b8f591b7431f33f7e380dc139)



#### application.yml 配置数据源 和 mybatis
```
spring:
  application:
    name: SpringBoot
  #freemarker 模板引擎配置
  freemarker:
    cache: false
    charset: UTF-8
    check-template-location: true
    content-type: text/html
    expose-request-attributes: true
    expose-session-attributes: true
    request-context-attribute: request
    template-loader-path: classpath:/templates
#druid数据源相关配置配置
  datasource:
    url: jdbc:mysql://127.0.0.1:3306/test?useUnicode=true&amp;characterEncoding=UTF-8&amp;tinyInt1isBit=false
    username: root
    password: root
    driver-class-name: com.mysql.jdbc.Driver
    type: org.apache.tomcat.jdbc.pool.DataSource

    #连接池的配置信息
    initialSize: 5
    minIdle: 5
    maxActive: 20
    maxWait: 60000
    timeBetweenEvictionRunsMillis: 60000
    minEvictableIdleTimeMillis: 300000
    validationQuery: SELECT 1 FROM DUAL
    testWhileIdle: true
    testOnBorrow: false
    testOnReturn: false
    poolPreparedStatements: true
    maxPoolPreparedStatementPerConnectionSize: 20
    filters: stat,wall,log4j
    connectionProperties: druid.stat.mergeSql=true;druid.stat.slowSqlMillis=5000
mybatis:
  type-aliases-package: com.liuzhiqiang.model
  mapperLocations: classpath:/mappers/**/*.xml
#端口号修改
server:
  port: 8484
```
#### 修改 IndexController
```
@RestController
public class IndexController {
    @Autowired
    public UserMapper userMapper;
    @RequestMapping("/index")
    ModelAndView index(ModelMap modelMap) {
        User user = userMapper.selectByPrimaryKey(Long.parseLong("1"));
        modelMap.put("map",user);
        return new ModelAndView("index", modelMap);
    }
}
```
#### 启动项目 浏览器访问 http://localhost:8484
#### 至此 springboot简单搭建结束
