package com.leolian.code.fragment.book.concurrence.chapter02;

import java.math.BigInteger;

import javax.annotation.concurrent.GuardedBy;
import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import com.leolian.code.fragment.book.concurrence.common.ThreadSafe;
import com.leolian.code.fragment.book.concurrence.util.ServletUtils;

@ThreadSafe
public class CachedFactorizer implements Servlet {
	@GuardedBy("this")
	private BigInteger lastNumber;
	@GuardedBy("this")
	private BigInteger[] lastFactors;
	@GuardedBy("this")
	private long hits;
	@GuardedBy("this")
	private long cacheHits;

	public synchronized long getHits() {
		return hits;
	}

	public synchronized double getCacheHitRatio() {
		return (double) cacheHits / (double) hits;
	}

	public void service(ServletRequest req, ServletResponse resp) {
		BigInteger i = ServletUtils.extractFromRequest(req);
		BigInteger[] factors = null;
		synchronized (this) {
			++hits;
			if (i.equals(lastNumber)) {
				++cacheHits;
				factors = lastFactors.clone();
			}
		}
		if (factors == null) {
			factors = ServletUtils.factor(i);
			synchronized (this) {
				lastNumber = i;
				lastFactors = factors.clone();
			}
		}
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