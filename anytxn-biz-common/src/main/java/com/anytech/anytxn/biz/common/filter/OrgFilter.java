package com.anytech.anytxn.biz.common.filter;

import com.anytech.anytxn.biz.common.constant.AnyTxnCommonRespCode;
import com.anytech.anytxn.biz.common.constant.CommonRepDetail;
import com.anytech.anytxn.biz.common.exception.AnyTxnCommonException;
import com.anytech.anytxn.biz.common.util.OrgNumberUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author peidong.meng
 * @date 2021/1/13
 */
@Slf4j
public class OrgFilter implements Filter{

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;

        String org = request.getHeader(OrgNumberUtil.ORG);
        if (StringUtils.isEmpty(org) || org.equals(OrgNumberUtil.ROOT_ORG_NUM)){
            throw new AnyTxnCommonException(AnyTxnCommonRespCode.P_ERR, CommonRepDetail.UNKNOWN_ORG_NUMBER);
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }
}
