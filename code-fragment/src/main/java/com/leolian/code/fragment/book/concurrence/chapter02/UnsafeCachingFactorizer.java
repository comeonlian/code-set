package com.leolian.code.fragment.book.concurrence.chapter02;

import java.math.BigInteger;
import java.util.concurrent.atomic.AtomicReference;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import com.leolian.code.fragment.book.concurrence.common.NotThreadSafe;
import com.leolian.code.fragment.book.concurrence.util.ServletUtils;

@NotThreadSafe
public class UnsafeCachingFactorizer implements Servlet {
	private final AtomicReference<BigInteger> lastNumber = new AtomicReference<BigInteger>();
	private final AtomicReference<BigInteger[]> lastFactors = new AtomicReference<BigInteger[]>();

	public void service(ServletRequest req, ServletResponse resp) {
		BigInteger i = ServletUtils.extractFromRequest(req);
		if (i.equals(lastNumber.get()))
			ServletUtils.encodeIntoResponse(resp, lastFactors.get());
		else {
			BigInteger[] factors = ServletUtils.factor(i);
			lastNumber.set(i);
			lastFactors.set(factors);
			ServletUtils.encodeIntoResponse(resp, factors);
		}
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