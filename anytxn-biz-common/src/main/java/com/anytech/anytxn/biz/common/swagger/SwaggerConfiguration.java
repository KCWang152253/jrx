package com.anytech.anytxn.biz.common.swagger;

import com.google.common.base.Function;
import com.google.common.base.Optional;
import com.google.common.base.Predicate;
import com.anytech.anytxn.biz.common.constant.PropsConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.documentation.RequestHandler;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


/**
 * @author yxy
 * @version 2.0
 * @date 2020/3/12
 */
@Configuration
@ConditionalOnProperty(name = PropsConstants.PROPS_PREFIX +".swagger.enable")
@EnableSwagger2
@EnableConfigurationProperties(SwaggerProperties.class)
public class SwaggerConfiguration {


    private static final Logger logger = LoggerFactory.getLogger(SwaggerConfiguration.class);

    private SwaggerProperties swaggerProperties;

    public SwaggerConfiguration(SwaggerProperties swaggerProperties){
        this.swaggerProperties = swaggerProperties;
    }

    /**
     * 通过 createRestApi函数来构建一个DocketBean
     * 函数名,可以随意命名,喜欢什么命名就什么命名
     */
    @Bean
    public Docket createReportApi() {
        logger.info("build swagger docket,{}",swaggerProperties);
        Set<String> contentTypes = new HashSet<>();
        contentTypes.add("application/json");

        List<ResponseMessage> messages = new ArrayList<>();
        ResponseMessage message1 = new ResponseMessageBuilder().code(200).message("操作成功")
                .responseModel(new ModelRef("操作成功")).build();
        ResponseMessage message2 = new ResponseMessageBuilder().code(400).message("非法请求")
                .responseModel(new ModelRef("非法请求")).build();
        ResponseMessage message3 = new ResponseMessageBuilder().code(501).message("如请求路径拼写不正确")
                .responseModel(new ModelRef("如请求路径拼写不正确")).build();
        ResponseMessage message4 = new ResponseMessageBuilder().code(502).message("服务器过载引起的错误")
                .responseModel(new ModelRef("服务器过载引起的错误")).build();
        messages.add(message1);
        messages.add(message2);
        messages.add(message3);
        messages.add(message4);

        return new Docket(DocumentationType.SWAGGER_2).groupName(swaggerProperties.getDocket().getGroupName())
                .useDefaultResponseMessages(true)
                .consumes(contentTypes)
                .produces(contentTypes)
                .globalResponseMessage(RequestMethod.POST,messages)
                //调用apiInfo方法,创建一个ApiInfo实例,里面是展示在文档页面信息内容
                .apiInfo(apiInfo())
                .select()
                //控制暴露出去的路径下的实例
                //如果某个接口不想暴露,可以使用以下注解
                //@ApiIgnore 这样,该接口就不会暴露在 swagger2 的页面下
                .apis(basePackage(swaggerProperties.getDocket().getBasePackage()))
//                .apis(RequestHandlerSelectors.basePackage(swaggerProperties.getDocket().getBasePackage()))
                .paths(PathSelectors.any())
                .build();
    }

    /**
     * 构建 api文档的详细信息函数
     */
    private ApiInfo apiInfo() {
        ApiInfoProperties apiInfoProperties = swaggerProperties.getApiInfo();

        return new ApiInfoBuilder()
                //页面标题
                .title(apiInfoProperties.getTitle())
                //创建人
                //版本号
                .version(apiInfoProperties.getVersion())
                //描述
                .description(apiInfoProperties.getDescription())
                .license(apiInfoProperties.getLicense())
                .licenseUrl(apiInfoProperties.getLicenseUrl())
                .termsOfServiceUrl(apiInfoProperties.getTermsOfServiceUrl())
                .build();
    }

    private static Predicate<RequestHandler> basePackage(final String basePackage) {
        return input -> declaringClass(input).transform(handlerPackage(basePackage)).or(true);
    }


    /**
     * 处理包路径配置规则,支持多路径扫描匹配以逗号隔开
     *
     * @param basePackage 扫描包路径
     * @return Function
     */
    private static Function<Class<?>, Boolean> handlerPackage(final String basePackage) {
        return input -> {
            for (String strPackage : basePackage.split(",")) {
                boolean isMatch = input.getPackage().getName().startsWith(strPackage);
                if (isMatch) {
                    return true;
                }
            }
            return false;
        };
    }

    /**
     * @param input RequestHandler
     * @return Optional
     */
    private static Optional<? extends Class<?>> declaringClass(RequestHandler input) {
        return Optional.fromNullable(input.declaringClass());
    }

}
