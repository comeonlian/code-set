package com.leolian.code.fragment.book.concurrence.chapter02;

import java.math.BigInteger;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import com.leolian.code.fragment.book.concurrence.common.ThreadSafe;
import com.leolian.code.fragment.book.concurrence.util.ServletUtils;

/**
 * Description: 一个无状态的Servlet
 * @author lianliang
 * @date 2018年1月5日 上午10:49:39
 */
@ThreadSafe
public class StatelessFactorizer implements Servlet {
	
	public void service(ServletRequest req, ServletResponse resp) {
		BigInteger i = ServletUtils.extractFromRequest(req);
		BigInteger[] factors = ServletUtils.factor(i);
		ServletUtils.encodeIntoResponse(resp, factors);
	}

	/* (non-Javadoc)
	 * @see javax.servlet.Servlet#destroy()
	 */
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see javax.servlet.Servlet#getServletConfig()
	 */
	@Override
	public ServletConfig getServletConfig() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see javax.servlet.Servlet#getServletInfo()
	 */
	@Override
	public String getServletInfo() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see javax.servlet.Servlet#init(javax.servlet.ServletConfig)
	 */
	@Override
	public void init(ServletConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}
}