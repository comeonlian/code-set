package com.leolian.code.fragment.book.concurrence.chapter05;

import java.math.BigInteger;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import com.leolian.code.fragment.book.concurrence.common.ThreadSafe;
import com.leolian.code.fragment.book.concurrence.util.ServletUtils;

@ThreadSafe
public class Factorizer implements Servlet {
	private final Computable<BigInteger, BigInteger[]> c = new Computable<BigInteger, BigInteger[]>() {
		public BigInteger[] compute(BigInteger arg) {
			return ServletUtils.factor(arg);
		}
	};

	private final Computable<BigInteger, BigInteger[]> cache = new Memoizer<BigInteger, BigInteger[]>(c);

	public void service(ServletRequest req, ServletResponse resp) {
		try {
			BigInteger i = ServletUtils.extractFromRequest(req);
			ServletUtils.encodeIntoResponse(resp, cache.compute(i));
		} catch (InterruptedException e) {
			encodeError(resp, "factorization interrupted");
		}
	}

	/**
	 * @param resp
	 * @param string
	 */
	private void encodeError(ServletResponse resp, String string) {
		
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