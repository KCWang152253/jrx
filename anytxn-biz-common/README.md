
# 工程名称
# 信用卡系统-公共工程

## 工程用途及介绍
## 交易服务工程
> 该工程主要包含个工程使用依赖，各工程所需常量、枚举、异常及工具类
- 提供各工程所需常量、枚举、异常及工具类
- 提供redis配置(可通过设置@ConditionalOnExpression不加载)
- 提供自定义异常,可统一处理异常
- 提供各种工具类
- 提供web controller基类及http返回数据结构等
## 使用用户群
- 开发
- 运维
- 测试

## 用户术语




## 工程结构说明
> 工程目录结构，包结构说明
- anytxn-biz-common(anytxn 基础包)
    + 包结构说明
    ```text
      - java
         - jrx.anytxn.biz.common      
            - config        系统配置类包,主要是redis配置
             - ErrorCode 错误码定义
             - RedisConfig redis配置
             - RedisService 外部直接使用此类进行
           - constant      系统常量定义
             - auth         授权相关常量定义
             - card         卡片相关常量定义
             - instalment   分期相关常量定义
             - limit        额度相关常量定义
           - dto           基础dto定义,如查询返回结果dto
             - OrganizationDTO
             - PageResultDTO
           - exception     自定义异常,包括统一异常
             - AnyTxnBeanMappingException
             - AnytxnBizException
             - AnyTXNBusRuntimeException
             - AnyTXNRuntimeException
           - handler       全局异常处理器
             - GlobalExceptionHandler
           - util          一些工具类
             - encryption  加解密相关
             - AmountUtil
             - ...
           - validator     取值校验器
             - Values
             - ValuesValidator
           - web           定义了controller基类/http返回数据结构/LocalDateTime序列化
             - BizBaseController
             - HttpApiResponse
             - LocalDateTimeSerializerConfig

    ```
               


## 配置文件说明

## 服务发布
- 上传至maven仓库  
  ```text
  1. 在工程根目录下执行如下命令上传至maven仓库
   mvn clean deploy 
  2. 使用方式
            <dependency>
                <groupId>jrx.anytxn</groupId>
                <artifactId>anytxn-biz-common</artifactId>
                <version>${anytxn-biz-common.version}</version>
            </dependency>
   ```
  
## 参考文档