/**
 * 版 权: Copyright © Huawei Technologies Co., Ltd. 1998-2019. All rights reserved.
       描 述: <此Demo代码版权属于华为技术有限公司，仅用于Demo演示及定制开发参考，不能用于商业目的，
       华为技术有限公司拥有此代码的使用权和解释权。>
 */
package com.restful.config;

import org.jasig.cas.client.util.AbstractCasFilter;

import javax.servlet.FilterConfig;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.URLEncoder;

public class SingleSignOutFilter1 implements Filter {
	
	String casServerLogoutUrl="https://auth.fmcc.cldc.test.com/authui/logout";
	String serverName ="http://10.47.181.202:8081/demo/ok";

	@Override
	public void destroy() {}

	@Override
	public void doFilter(final ServletRequest servletRequest, final ServletResponse servletResponse,
                         final FilterChain filterChain) throws IOException, ServletException {
		
		HttpServletResponse response = (HttpServletResponse)servletResponse;
		HttpServletRequest request = (HttpServletRequest)servletRequest;
		
		HttpSession session = request.getSession(false);
		if(session!=null){
			session.removeAttribute(AbstractCasFilter.CONST_CAS_ASSERTION);
			//session.invalidate();
		}

		
		// destroy BusiSession

		@SuppressWarnings("deprecation")
		String url = casServerLogoutUrl+"?service="+URLEncoder.encode(serverName+"/demo/ok");
		response.sendRedirect(url);
		
	}

	@Override
	public void init(FilterConfig fc) throws ServletException {
		casServerLogoutUrl = fc.getInitParameter("casServerLogoutUrl");
		serverName = fc.getInitParameter("serverName");
	}

}
