package com.leolian.code.fragment.book.concurrence.chapter02;

import java.math.BigInteger;
import java.util.concurrent.atomic.AtomicLong;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import com.leolian.code.fragment.book.concurrence.common.ThreadSafe;
import com.leolian.code.fragment.book.concurrence.util.ServletUtils;

@ThreadSafe
public class CountingFactorizer implements Servlet {
	private final AtomicLong count = new AtomicLong(0);

	public long getCount() {
		return count.get();
	}

	public void service(ServletRequest req, ServletResponse resp) {
		BigInteger i = ServletUtils.extractFromRequest(req);
		BigInteger[] factors = ServletUtils.factor(i);
		count.incrementAndGet();
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