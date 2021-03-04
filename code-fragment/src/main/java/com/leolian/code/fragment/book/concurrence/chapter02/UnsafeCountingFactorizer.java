package com.leolian.code.fragment.book.concurrence.chapter02;

import java.math.BigInteger;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import com.leolian.code.fragment.book.concurrence.common.NotThreadSafe;
import com.leolian.code.fragment.book.concurrence.util.ServletUtils;

/**
 * Description: 
 * @author lianliang
 * @date 2018年1月5日 下午1:55:07
 */
@NotThreadSafe
public class UnsafeCountingFactorizer implements Servlet {
	private long count = 0;

	public long getCount() {
		return count;
	}

	public void service(ServletRequest req, ServletResponse resp) {
		BigInteger i = ServletUtils.extractFromRequest(req);
		BigInteger[] factors = ServletUtils.factor(i);
		++count;
		ServletUtils.encodeIntoResponse(resp, factors);
	}
	
	/*
	 * ++count
	 * 读取-修改-写入
	 * */
	
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