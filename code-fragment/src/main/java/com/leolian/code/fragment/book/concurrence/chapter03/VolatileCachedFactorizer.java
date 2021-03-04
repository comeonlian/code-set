package com.leolian.code.fragment.book.concurrence.chapter03;

import java.math.BigInteger;
import java.util.Arrays;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import com.leolian.code.fragment.book.concurrence.common.Immutable;
import com.leolian.code.fragment.book.concurrence.util.ServletUtils;

public class VolatileCachedFactorizer implements Servlet {
	private volatile OneValueCache cache = new OneValueCache(null, null);

	public void service(ServletRequest req, ServletResponse resp) {
		BigInteger i = ServletUtils.extractFromRequest(req);
		BigInteger[] factors = cache.getFactors(i);
		if (factors == null) {
			factors = ServletUtils.factor(i);
			cache = new OneValueCache(i, factors);
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

/**
 * Description: 不可变对象
 * @author lianliang
 * @date 2018年1月10日 上午10:47:29
 */
@Immutable
class OneValueCache {
	private final BigInteger lastNumber;
	private final BigInteger[] lastFactors;
	
	public OneValueCache(BigInteger i, BigInteger[] factors) {
		lastNumber = i;
		lastFactors = Arrays.copyOf(factors, factors.length);
	}
	
	public BigInteger[] getFactors(BigInteger i) {
		if(lastNumber == null || !lastNumber.equals(i)) {
			return null;
		} else {
			return Arrays.copyOf(lastFactors, lastFactors.length);
		}
	}
}