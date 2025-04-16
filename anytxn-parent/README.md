### 信用卡系统-父工程

### 目标
- 提供统一的spring boot版本
- 其他项目依赖的jar进行统一版本管理

### 版本说明
* 3.0.0.SNAPSHOT 基础版本

主要项目版本

| 项目       | 版本           |
|:---------  |:---------------|
|spring boot |2.2.0.RELEASE|
|spring cloud|Hoxton.SR1|
  
### 使用
    <dependency>
      <groupId>com.jrx.anytxn</groupId>
      <artifactId>anytxn-parent</artifactId>
      <version>3.0.0-SNAPSHOT</version>
      <type>pom</type>
    </dependency>
 
### 更新记录
 
| 日期       | 更新人   | 说明 |
|:---------  |:---------|:-----------------------------  |
|2020-02-03  |yxy       |spring boot 2.1.5 -> 2.2.0,Greenwich.SR1->Hoxton.SR1,mybatis starter 更新2.1.1，guava 23.0->28.2-jre|
|2019-06-03  |zwg       |spring boot 2.0.5 -> 2.1.5|
|2019-06-03  |zwg       |spring cloud Finchley.SR1 -> Greenwich.SR1|
|2019-06-03  |zwg       |注释掉spring-beans 5.0.5版本的依赖管理|
|2018-10-31  |zwg       |swagger.bootstrap.ui 版本1.8.5 -> 1.9.2|
|2018-10-31  |zwg       |swagger.bootstrap.ui 版本1.7.7 -> 1.8.5|
|2018-09-20  |zwg       |添加ojdbc8 版本18.3.0           |
|2018-09-19  |zwg       |spring boot版本更新2.0