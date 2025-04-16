package com.anytech.anytxn.biz.common.swagger;

import com.anytech.anytxn.biz.common.constant.PropsConstants;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

/**
 * swagger属性配置
 * @author yxy
 * @date 2018/6/4
 */
@ConfigurationProperties(prefix = PropsConstants.PROPS_PREFIX+".swagger")
public class SwaggerProperties {

    private Boolean enable;
    @NestedConfigurationProperty
    private ApiInfoProperties apiInfo;

    @NestedConfigurationProperty
    private DocketProperties docket;

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }

    public ApiInfoProperties getApiInfo() {
        return apiInfo;
    }

    public void setApiInfo(ApiInfoProperties apiInfo) {
        this.apiInfo = apiInfo;
    }

    public DocketProperties getDocket() {
        return docket;
    }

    public void setDocket(DocketProperties docket) {
        this.docket = docket;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("SwaggerProperties{");
        sb.append("enable=").append(enable);
        sb.append(", apiInfo=").append(apiInfo);
        sb.append(", docket=").append(docket);
        sb.append('}');
        return sb.toString();
    }
}
