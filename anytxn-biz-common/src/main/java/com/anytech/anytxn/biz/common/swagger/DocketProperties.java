package com.anytech.anytxn.biz.common.swagger;

/**
 * swagger的docket属性
 * @author yxy
 * @date 2018/6/4
 */
public class DocketProperties {

    private String groupName;
    private String basePackage;


    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getBasePackage() {
        return basePackage;
    }

    public void setBasePackage(String basePackage) {
        this.basePackage = basePackage;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("DocketProperties{");
        sb.append("groupName='").append(groupName).append('\'');
        sb.append(", basePackage='").append(basePackage).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
