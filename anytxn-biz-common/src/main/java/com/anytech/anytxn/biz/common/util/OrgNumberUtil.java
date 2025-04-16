package com.anytech.anytxn.biz.common.util;

import com.anytech.anytxn.biz.common.constant.AnyTxnCommonRespCode;
import com.anytech.anytxn.biz.common.constant.CommonRepDetail;
import com.anytech.anytxn.biz.common.exception.AnyTxnCommonException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * 全局机构号初始化、获取工具类
 * 该工具类适用于所有http接口联机服务、批次服务
 * @author peidong.meng
 * @date 2020/12/31
 */
@Slf4j
@Configuration
public class OrgNumberUtil implements EnvironmentAware, BeanPostProcessor {

    /** 默认根机构号 */
    public final static String ROOT_ORG_NUM = "0000";
    /** 默认机构key */
    public final static String ORG = "organizationNumber";
    /** 批次机构入参key */
    public final static String BATCH_OTG_KEY = "job.organizationNumber";
    /** 批次任务key, 批次必传且其他服务不需要传 */
    public final static String BATCH_JOB_KEY = "t.job";
    /** 批次任务key, 预留，与BATCH_JOB_KEY作用相同，可替代 */
    public final static String BATCH_JOB_KEY2 = "isBatch";
    private Environment environment;
    /** 批次机构号 */
    private String batchOrg = null;
    public static OrgNumberUtil orgNumberUtil;

    public OrgNumberUtil(){
        orgNumberUtil = this;
    }

    /**
     * 批次配置初始化
     * @param args
     * @return
     */
    public static String[] batchInit(String[] args){
        if (args == null) {
            return new String[]{BATCH_JOB_KEY + "=true"};
        } else {
            String[] result = Arrays.copyOf(args, args.length + 1);
            result[result.length - 1] = BATCH_JOB_KEY + "=true";
            return result;
        }
    }

    /**
     * 通用获取机构方法
     * 1. 首先获取批次机构号，存在直接返回
     * 2. 获取当前请求http上下文
     * 3. 从http的session中获取机构号，存在直接返回
     * 4. 从http的header中获取机构号，存在直接返回
     * @return
     */
    public static String getOrg() {
        // 批次模式
        if (StringUtils.isNotEmpty(orgNumberUtil.getBatchOrg())){
            return orgNumberUtil.getBatchOrg();
        // 联机模式，仅支持http类请求
        } else {
            RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
            if (requestAttributes != null) {
                ServletRequestAttributes attr = (ServletRequestAttributes) requestAttributes;
                HttpServletRequest request = attr.getRequest();
                Object attribute = request.getSession().getAttribute(ORG);
                String org = LogForgingVaildUtil.vaildLog(request.getHeader(ORG));
                return attribute == null ? org : LogForgingVaildUtil.vaildLog(String.valueOf(attribute));
            }
        }

        return null;
    }

    public static String getOrg(String organizationNumber) {
        return StringUtils.isEmpty(organizationNumber) ? getOrg() : organizationNumber;
    }

    /**
     * 获取当前请求中非根机构号，如果是根机构号异常
     */
    public static String getNoRootOrg(){
        return getNoRootOrg(null);
    }

    /**
     * 判断是否参数传入机构号，如果没有从当前请求中获取机构号
     * 判断如果获取机构号为根机构则抛异常
     */
    public static String getNoRootOrg(String organizationNumber){
        String org = getOrg(organizationNumber);
        if (StringUtils.isNotEmpty(org) && org.equals(ROOT_ORG_NUM)) {
            throw new AnyTxnCommonException(AnyTxnCommonRespCode.P_ERR, CommonRepDetail.ORG_NUMBER_ERROR);
        }

        return org;
    }


    /**
     * spring构建对象时触发，感知配置加载时处理
     * 1. 判断是否含t.job配置判定为批次服务，否则无处理
     * 2. 从job.organizationNumber配置读取批次处理机构号，如果不含该参数或该参数为0000则跑出异常中断批次
     * 3. 将读取的机构号放入batchOrg缓存，批次全局使用该机构号
     * @param environment
     */
    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;

        if (isBatch(environment)) {
            if (StringUtils.isEmpty(batchOrg = environment.getProperty(BATCH_OTG_KEY))){
                log.error("当前服务判定为批次机构（含isBatch参数），但未传入机构号!");
                throw new AnyTxnCommonException(AnyTxnCommonRespCode.P_ERR, CommonRepDetail.BATCH_ORG_NUMBER_EXIST);
            }

            if (batchOrg.equals(ROOT_ORG_NUM)) {
                log.error("当前服务判定为批次机构（含isBatch参数），禁止传入根机构号!");
                throw new AnyTxnCommonException(AnyTxnCommonRespCode.P_ERR, CommonRepDetail.BATCH_ORG_NUMBER_ERROR);
            }

            log.info("批量模式服务，机构号：{}", batchOrg);
        } else {
            log.info("联机模式服务，机构号未指定");
        }
    }

    public Environment getEnvironment(Environment environment){
        return environment;
    }

    public String getBatchOrg(){
        return batchOrg;
    }

    /**
     * 是否批次服务
     */
    private boolean isBatch(Environment environment){
        // 暂时默认批次都传
        return StringUtils.isNotEmpty(environment.getProperty(BATCH_OTG_KEY));
//        return StringUtils.isNotEmpty(environment.getProperty(BATCH_JOB_KEY)) || StringUtils.isNotEmpty(environment.getProperty(BATCH_JOB_KEY2));
    }
}
