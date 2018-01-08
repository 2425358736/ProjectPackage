### 引入开发包
```
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
  xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/maven-v4_0_0.xsd">
  <modelVersion>4.0.0</modelVersion>
  <groupId>SpringMvc</groupId>
  <artifactId>SpringMvc</artifactId>
  <packaging>war</packaging>
  <version>1.0-SNAPSHOT</version>
  <name>SpringMvc Maven Webapp</name>
  <url>http://maven.apache.org</url>

  <properties>
    <spring.version>4.3.6.RELEASE</spring.version>
  </properties>

  <dependencies>

    <!--Spring核心开发包-->

    <!--单元测试-->
    <dependency>
      <groupId>junit</groupId>
      <artifactId>junit</artifactId>
      <version>3.8.1</version>
      <scope>test</scope>
    </dependency>
    <!--freemarker模板引擎-->
    <dependency>
      <groupId>org.freemarker</groupId>
      <artifactId>freemarker</artifactId>
      <version>2.3.23</version>
    </dependency>
    <!--spring核心模块core主要的功能是实现了控制反转与依赖注入、Bean配置以及加载。Core模块中有Beans、BeanFactory、BeanDefinitions、ApplicationContext等概念-->
    <dependency>
      <groupId>org.springframework</groupId>
      <artifactId>spring-core</artifactId>
      <version>${spring.version}</version>
    </dependency>
    <!--Spring ApplicationContext特性时所需的全部类-->
    <dependency>
      <groupId>org.springframework</groupId>
      <artifactId>spring-context</artifactId>
      <version>${spring.version}</version>
    </dependency>
    <!--这个jar文件包含支持缓存Cache（ehcache）、JCA、JMX、邮件服务（Java Mail、COS Mail）、任务计划Scheduling（Timer、Quartz）方面的类-->
    <dependency>
      <groupId>org.springframework</groupId>
      <artifactId>spring-context-support</artifactId>
      <version>${spring.version}</version>
    </dependency>
    <!--springmvc -->
    <dependency>
      <groupId>org.springframework</groupId>
      <artifactId>spring-webmvc</artifactId>
      <version>${spring.version}</version>
    </dependency>
    <dependency>
      <groupId>org.springframework</groupId>
      <artifactId>spring-aop</artifactId>
      <version>${spring.version}</version>
    </dependency>
    <dependency>
      <groupId>org.springframework</groupId>
      <artifactId>spring-aspects</artifactId>
      <version>${spring.version}</version>
    </dependency>
    <dependency>
      <groupId>org.springframework</groupId>
      <artifactId>spring-beans</artifactId>
      <version>${spring.version}</version>
    </dependency>
    <dependency>
      <groupId>org.springframework</groupId>
      <artifactId>spring-jdbc</artifactId>
      <version>${spring.version}</version>
    </dependency>
    <!-- 核心开发包end-->

    <!-- log4j-->
    <dependency>
      <groupId>log4j</groupId>
      <artifactId>log4j</artifactId>
      <version>1.2.14</version>
    </dependency>
    <!-- mybatis包-->
    <dependency>
      <groupId>commons-dbcp</groupId>
      <artifactId>commons-dbcp</artifactId>
      <version>1.4</version>
    </dependency>
    <dependency>
      <groupId>org.mybatis</groupId>
      <artifactId>mybatis</artifactId>
      <version>3.3.1</version>
    </dependency>
    <!--mybatis spring插件-->
    <dependency>
      <groupId>org.mybatis</groupId>
      <artifactId>mybatis-spring</artifactId>
      <version>1.2.4</version>
    </dependency>
    <!-- mysql连接驱动包-->
    <dependency>
      <groupId>mysql</groupId>
      <artifactId>mysql-connector-java</artifactId>
      <version>5.1.26</version>
    </dependency>
    <!-- 连接池-->
    <dependency>
      <groupId>c3p0</groupId>
      <artifactId>c3p0</artifactId>
      <version>0.9.1.2</version>
    </dependency>
  </dependencies>
  <build>
    <finalName>SpringMvc</finalName>
    <plugins>
      <!--内置jetty服务器插件-->
      <plugin>
        <groupId>org.eclipse.jetty</groupId>
        <artifactId>jetty-maven-plugin</artifactId>
        <version>9.3.7.v20160115</version>
        <configuration>
          <!-- 扫描秒数，若非0则为热部署-->
          <scanIntervalSeconds>0</scanIntervalSeconds>
          <reload>automatic</reload>
          <stopPort>19966</stopPort>
          <stopKey>foo</stopKey>
          <stopWait>10</stopWait>
          <webApp>
            <!-- 项目根目录-->
            <contextPath>/</contextPath>
          </webApp>
          <httpConnector>
            <!-- 服务器端口号-->
            <port>19090</port>
            <idleTimeout>60000</idleTimeout>
          </httpConnector>
          <!-- 请求log-->
          <requestLog implementation="org.eclipse.jetty.server.NCSARequestLog">
            <!-- log输出目录-->
            <filename>target/yyyy_mm_dd.request.log</filename>
            <!--<filenameDateformat>yyyy_MM_dd</filenameDateformat>-->
            <!-- log保存天数-->
            <retainDays>90</retainDays>
            <!-- log是否附加在文件后-->
            <append>true</append>
            <extended>false</extended>
            <!-- 时区-->
            <logTimeZone>GMT+8:00</logTimeZone>
          </requestLog>
        </configuration>
      </plugin>

      <!--内置Tomcat7服务器插件-->
      <plugin>
        <groupId>org.apache.tomcat.maven</groupId>
        <artifactId>tomcat7-maven-plugin</artifactId>
        <version>2.2</version>
        <configuration>
          <url>http://localhost:8080/manager/text</url> <!-- tomcat管理路径 -->
          <server>Tomcat7</server> <!-- 与settings.xml文件中Server的id相同 -->
          <path>/</path> <!-- 应用的部署位置 -->
        </configuration>
      </plugin>


      <!--mybatis-generator数据表自动生成-->
      <plugin>
        <groupId>org.mybatis.generator</groupId>
        <artifactId>mybatis-generator-maven-plugin</artifactId>
        <version>1.3.5</version>
        <dependencies>
          <dependency>
            <groupId> mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <version>5.1.26</version>
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
    </plugins>
  </build>
</project>

```
引入了内置服务器 jetty 和 Tomcat7 当然也支持自己配置Tomcat
### 新建freemarker.properties
```
tag_syntax=auto_detect
template_update_delay=60  
#  //模板更新时间
default_encoding=UTF-8 
#  //默认编码
output_encoding=UTF-8 
#  //输出编码
locale=zh_CN 
#  //使用语言
date_format=yyyy-MM-dd
time_format=HH:mm:ss
datetime_format=yyyy-MM-dd HH:mm:ss
number_format=0.##########
classic_compatible=true
template_exception_handler=ignore
#   //模板类型处理
```
### 新建log4j.properties
```
##define output style
ConversionPattern=%d %-5p [%t] %c - %m%n
log4j.rootLogger=DEBUG,Console,D,E
log4j.logger.com.cnblogs.lzrabbit=ERROR
log4j.logger.org.springframework=ERROR
log4j.logger.org.mybatis=DEBUG
log4j.logger.org.apache.ibatis=ERROR
log4j.logger.org.quartz=ERROR
log4j.logger.org.apache.axis2=ERROR
log4j.logger.org.apache.axiom=ERROR
log4j.logger.org.apache=ERROR
log4j.logger.httpclient=ERROR
#log4j.additivity.org.springframework=false
#Console
log4j.appender.Console=org.apache.log4j.ConsoleAppender
log4j.appender.Console.Threshold=DEBUG 
log4j.appender.Console.Target=System.out 
log4j.appender.Console.layout=org.apache.log4j.PatternLayout 
log4j.appender.Console.layout.ConversionPattern=${ConversionPattern}
#log4j.appender.Console.encoding=UTF-8

#org.apache.log4j.DailyRollingFileAppender
log4j.appender.D=org.apache.log4j.DailyRollingFileAppender
log4j.appender.D.File = D://logs/log.log
log4j.appender.D.Append = true
log4j.appender.D.Threshold = DEBUG
log4j.appender.D.layout = org.apache.log4j.PatternLayout
log4j.appender.D.layout.ConversionPattern = %-d{yyyy-MM-dd HH:mm:ss}  [ %t:%r:%l ] - [ %p ]  %m%n

#    %c 输出日志信息所属的类的全名
#    %d 输出日志时间点的日期或时间，默认格式为ISO8601，也可以在其后指定格式，比如：%d{yyy-MM-dd HH:mm:ss}，
#      输出类似：2016-03-10 22:10:11
#    %f 输出日志信息所属的类的类名
#    %l 输出日志事件的发生位置，即输出日志信息的的语句处于它所在的类的第几行
#    %m 输出代码中指定的信息，如log(message)中的message
#    %n 输出一个回车换行符，Windows平台为"rn",Unix平台为"n"
#    %p 输出优先级，即DEBUG.INFO,WARN,ERROR,FATAL.如果是调用debug()输出的，则为DEBUG,以此类推
#    %r 输出自应用启动到输出该日志信息所耗费的毫秒数
#    %t 输出产生该日志的线程名
```
### 新建 jdbc.properties
```
#============================================================================
# MySQL
#============================================================================
jdbc.mysql.driver=com.mysql.jdbc.Driver
jdbc.mysql.url=jdbc:mysql://localhost:3306/test?useUnicode=true&characterEncoding=utf8
jdbc.mysql.username=liuzhiqiang
jdbc.mysql.password=lzq199528

#============================================================================
# MS SQL Server
#============================================================================
#jdbc.sqlserver.driver=com.microsoft.sqlserver.jdbc.SQLServerDriver
#jdbc.sqlserver.url=jdbc:sqlserver://127.0.0.1:1433;database=angular-demo;
#jdbc.sqlserver.username=root
#jdbc.sqlserver.password=root

#============================================================================
# ORACLE
#============================================================================
jdbc.orcl.driver=oracle.jdbc.OracleDriver
jdbc.orcl.url=jdbc:oracle:thin:@localhost:1526:angular-demo
jdbc.orcl.username=root
jdbc.orcl.password=root

#============================================================================
# common settings
#============================================================================
jdbc.initialSize=5
jdbc.minIdle=5
jdbc.maxIdle=20
jdbc.maxActive=100
jdbc.maxWait=100000
jdbc.defaultAutoCommit=false
jdbc.removeAbandoned=true
jdbc.removeAbandonedTimeout=600
jdbc.testWhileIdle=true
jdbc.timeBetweenEvictionRunsMillis=60000
jdbc.numTestsPerEvictionRun=20
jdbc.minEvictableIdleTimeMillis=300000
```
### 新建spring-mvc.xml
```
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:mvc="http://www.springframework.org/schema/mvc"
       xmlns:context="http://www.springframework.org/schema/context"
       xsi:schemaLocation="
       http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans-4.0.xsd
       http://www.springframework.org/schema/mvc http://www.springframework.org/schema/mvc/spring-mvc-4.0.xsd
       http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context-4.0.xsd">

    <!-- 启动Spring MVC的注解功能，完成请求和注解POJO的映射 -->
    <mvc:annotation-driven />
    <!--自动扫描-->
    <context:component-scan base-package="com.liuzhiqiang.controller" />
    <!-- 解决js,css,images访问不到的问题-->
    <mvc:resources mapping="/js/**" location="/js/" />
    <mvc:resources mapping="/css/**" location="/css/" />
    <mvc:resources mapping="/images/**" location="/images/" />

    <!-- 视图解析器 -->
    <bean id="freemarkerConfiguration"
          class="org.springframework.beans.factory.config.PropertiesFactoryBean">
        <property name="location" value="classpath:config/freemarker.properties" />
    </bean>

    <bean id="freemarkerConfig"
          class="org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer">
        <property name="freemarkerSettings" ref="freemarkerConfiguration" />
        <property name="templateLoaderPath">
            <value>/WEB-INF/templates</value>
        </property>
        <property name="freemarkerVariables">
            <map>
                <entry key="xml_escape" value-ref="fmXmlEscape" />
            </map>
        </property>
        <property name="defaultEncoding">
            <value>utf-8</value>
        </property>
    </bean>
    <bean id="fmXmlEscape" class="freemarker.template.utility.XmlEscape" />

    <!-- 配置freeMarker视图解析器 -->
    <bean
            class="org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver">
        <property name="viewClass"
                  value="org.springframework.web.servlet.view.freemarker.FreeMarkerView" />
        <property name="contentType" value="text/html; charset=utf-8" />
        <property name="suffix" value=".ftl" />
        <property name="order" value="0" />
    </bean>
</beans>
```
### 新建applicationContext.xml
```
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:aop="http://www.springframework.org/schema/aop"
       xmlns:tx="http://www.springframework.org/schema/tx" xmlns:context="http://www.springframework.org/schema/context"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans-4.0.xsd
       http://www.springframework.org/schema/aop http://www.springframework.org/schema/aop/spring-aop-4.0.xsd
       http://www.springframework.org/schema/tx http://www.springframework.org/schema/tx/spring-tx-4.0.xsd http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context.xsd">

    <!-- 引入文件属性 -->
    <bean id="propertyConfigurer" class="org.springframework.beans.factory.config.PropertyPlaceholderConfigurer">
        <property name="location" value="classpath:config/jdbc.properties"/>
    </bean>

    <!-- sqlServer数据源-->
    <!--<bean id="sqlServerDataSource" class="org.apache.commons.dbcp.BasicDataSource" destroy-method="close">-->
        <!--<property name="driverClassName" value="${jdbc.sqlserver.driver}"/>-->
        <!--<property name="url" value="${jdbc.sqlserver.url}"/>-->
        <!--<property name="username" value="${jdbc.sqlserver.username}"/>-->
        <!--<property name="password" value="${jdbc.sqlserver.password}"/>-->
        <!--<property name="initialSize" value="${jdbc.initialSize}"/>-->
        <!--<property name="minIdle" value="${jdbc.minIdle}"/>-->
        <!--<property name="maxIdle" value="${jdbc.maxIdle}"/>-->
        <!--<property name="maxActive" value="${jdbc.maxActive}"/>-->
        <!--<property name="maxWait" value="${jdbc.maxWait}"/>-->
        <!--<property name="defaultAutoCommit" value="${jdbc.defaultAutoCommit}"/>-->
        <!--<property name="removeAbandoned" value="${jdbc.removeAbandoned}"/>-->
        <!--<property name="removeAbandonedTimeout" value="${jdbc.removeAbandonedTimeout}"/>-->
        <!--<property name="testWhileIdle" value="${jdbc.testWhileIdle}"/>-->
        <!--<property name="timeBetweenEvictionRunsMillis" value="${jdbc.timeBetweenEvictionRunsMillis}"/>-->
        <!--<property name="numTestsPerEvictionRun" value="${jdbc.numTestsPerEvictionRun}"/>-->
        <!--<property name="minEvictableIdleTimeMillis" value="${jdbc.minEvictableIdleTimeMillis}"/>-->
    <!--</bean>-->

    <!-- mysql数据源-->
    <bean id="mySqlDataSource" class="org.apache.commons.dbcp.BasicDataSource"
          destroy-method="close">
        <property name="driverClassName" value="${jdbc.mysql.driver}"/>
        <property name="url" value="${jdbc.mysql.url}"/>
        <property name="username" value="${jdbc.mysql.username}"/>
        <property name="password" value="${jdbc.mysql.password}"/>
        <property name="initialSize" value="${jdbc.initialSize}"/>
        <property name="minIdle" value="${jdbc.minIdle}"/>
        <property name="maxIdle" value="${jdbc.maxIdle}"/>
        <property name="maxActive" value="${jdbc.maxActive}"/>
        <property name="maxWait" value="${jdbc.maxWait}"/>
        <property name="defaultAutoCommit" value="${jdbc.defaultAutoCommit}"/>
        <property name="removeAbandoned" value="${jdbc.removeAbandoned}"/>
        <property name="removeAbandonedTimeout" value="${jdbc.removeAbandonedTimeout}"/>
        <property name="testWhileIdle" value="${jdbc.testWhileIdle}"/>
        <property name="timeBetweenEvictionRunsMillis" value="${jdbc.timeBetweenEvictionRunsMillis}"/>
        <property name="numTestsPerEvictionRun" value="${jdbc.numTestsPerEvictionRun}"/>
        <property name="minEvictableIdleTimeMillis" value="${jdbc.minEvictableIdleTimeMillis}"/>
    </bean>

    <!-- 数据源选择，targetDataSources中选择数据源，若找不到，则使用defaultTargetDataSource-->
    <bean id="multipleDataSource" class="com.liuzhiqiang.config.MultipleDataSource">
        <property name="defaultTargetDataSource" ref="mySqlDataSource"/>
        <property name="targetDataSources">
            <map>
                <entry key="mySqlDataSource" value-ref="mySqlDataSource"/>
                <!--<entry key="sqlServerDataSource" value-ref="sqlServerDataSource"/>-->
            </map>
        </property>
    </bean>

    <!-- 配置mybatis的sqlSessionFactory -->
    <bean id="sqlSessionFactory" class="org.mybatis.spring.SqlSessionFactoryBean">
        <property name="dataSource" ref="multipleDataSource" />
        <!-- mybatis配置文件 -->
        <property name="configLocation" value="classpath:config/mybatis-config.xml"></property>
        <!-- 自动扫描mappers.xml文件 ，要加上classpath:com/...-->
        <property name="mapperLocations" value="classpath:mappers/*.xml"></property>
    </bean>

    <!-- DAO接口所在包名，Spring会自动查找其下的类 -->
    <bean class="org.mybatis.spring.mapper.MapperScannerConfigurer">
        <property name="basePackage" value="com.liuzhiqiang.dao" />
        <property name="sqlSessionFactoryBeanName" value="sqlSessionFactory"></property>
    </bean>

    <!-- (事务管理)transaction manager, use JtaTransactionManager for global tx -->
    <bean id="transactionManager"
          class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
        <property name="dataSource" ref="mySqlDataSource" />
    </bean>

    <!-- 配置事务通知属性 -->
    <tx:advice id="txAdvice" transaction-manager="transactionManager">
        <!-- 定义事务传播属性 -->
        <tx:attributes>
            <tx:method name="insert*" propagation="REQUIRED" />
            <tx:method name="update*" propagation="REQUIRED" />
            <tx:method name="edit*" propagation="REQUIRED" />
            <tx:method name="save*" propagation="REQUIRED" />
            <tx:method name="add*" propagation="REQUIRED" />
            <tx:method name="new*" propagation="REQUIRED" />
            <tx:method name="set*" propagation="REQUIRED" />
            <tx:method name="remove*" propagation="REQUIRED" />
            <tx:method name="delete*" propagation="REQUIRED" />
            <tx:method name="change*" propagation="REQUIRED" />
            <tx:method name="get*" propagation="REQUIRED" read-only="true" />
            <tx:method name="find*" propagation="REQUIRED" read-only="true" />
            <tx:method name="load*" propagation="REQUIRED" read-only="true" />
            <tx:method name="*" propagation="REQUIRED" read-only="true" />
        </tx:attributes>
    </tx:advice>

    <!-- 配置事务切面 -->
    <aop:config>
        <aop:pointcut id="serviceOperation"
                      expression="execution(* com.liuzhiqiang.controller.*.*(..))" />
        <aop:advisor advice-ref="txAdvice" pointcut-ref="serviceOperation" />
    </aop:config>
</beans>
```
### 新建mybatis-config.xml
```
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE configuration PUBLIC
        "-//mybatis.org//DTD Config 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-config.dtd">
<configuration>
    <settings>
        <!-- setting name="cacheEnable" value="true"/-->
        <setting name="useGeneratedKeys" value="false"/>
        <setting name="logImpl" value="LOG4J"/><!--增加mybatis日志-->
    </settings>
    <mappers>
    </mappers>
</configuration>
```
### 新建generatorConfig.xml 数据库映射出dao xml等
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

        <jdbcConnection driverClass="com.mysql.jdbc.Driver"
                        connectionURL="jdbc:mysql://localhost:3306/test"
                        userId="liuzhiqiang"
                        password="lzq199528">
        </jdbcConnection>

        <javaModelGenerator targetPackage="com.liuzhiqiang.model" targetProject="src\main\java">
            <property name="trimStrings" value="true" />
        </javaModelGenerator>

        <sqlMapGenerator targetPackage="mappers"  targetProject="src\main\resources"/>

        <javaClientGenerator type="XMLMAPPER" targetPackage="com.liuzhiqiang.dao"  targetProject="src\main\java"/>

        <table tableName="%">
            <generatedKey column="id" sqlStatement="Mysql"/>
        </table>
    </context>
</generatorConfiguration>
```
### 到此配置完毕
项目地址:
https://github.com/2425358736/ProjectPackage/tree/master/SpringMvc