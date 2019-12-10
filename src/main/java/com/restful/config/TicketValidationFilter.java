/**
 * 版 权: Copyright © Huawei Technologies Co., Ltd. 1998-2019. All rights reserved.
       描 述: <此Demo代码版权属于华为技术有限公司，仅用于Demo演示及定制开发参考，不能用于商业目的，
       华为技术有限公司拥有此代码的使用权和解释权。>
 */
package com.restful.config;


import com.alibaba.fastjson.JSON;
import com.restful.model.UserDetailInfo;
import com.restful.utils.HttpsURLConnectionFactory2;
import lombok.extern.slf4j.Slf4j;
import org.jasig.cas.client.ssl.HttpURLConnectionFactory;
import org.jasig.cas.client.ssl.HttpsURLConnectionFactory;
import org.jasig.cas.client.validation.Assertion;
import org.jasig.cas.client.validation.Cas20ProxyReceivingTicketValidationFilter;

import javax.servlet.FilterConfig;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Slf4j
public class TicketValidationFilter extends
        Cas20ProxyReceivingTicketValidationFilter {
	
	protected void onSuccessfulValidation(final HttpServletRequest request,
                                          final HttpServletResponse response, final Assertion assertion) {
		log.info("INTO TicketValidationFilter");
		String userName = assertion.getPrincipal().getName();
		if (userName != null){
			setRedirectAfterValidation(true);
			Map map = assertion.getPrincipal().getAttributes();

			UserDetailInfo userDetailInfo = JSON.parseObject(JSON.toJSONString(map),UserDetailInfo.class);
			System.out.println("userInfo="+userDetailInfo.toString());
			System.out.println("Ticket Validation success");
		}
		
	}
	
	protected void onFailedValidation(HttpServletRequest request,
                                      HttpServletResponse response) {
		System.out.println("Ticket Validation failed");
	}
	
	
	public static HttpURLConnectionFactory factory = null;
	protected void initInternal(final FilterConfig filterConfig) throws ServletException {
		super.initInternal(filterConfig);
		//针对华为fusionstage平台的cas ssl认证重写
//		factory = new HttpsURLConnectionFactory(getHostnameVerifier(filterConfig),getSSLConfig(filterConfig));
		factory = new HttpsURLConnectionFactory(getHostnameVerifier(),getSSLConfig());
	}



}
