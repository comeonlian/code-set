package com.leolian.code.fragment.book.concurrence.chapter06;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class RankedTravelQuote {
	private ExecutorService exec;

	public RankedTravelQuote(ExecutorService exec) {
		this.exec = exec;
	}

	public List<TravelQuote> getRankedTravelQuote(TravelInfo travelInfo, Set<TravelCompany> companies,
			Comparator<TravelQuote> ranking, long time, TimeUnit unit) throws Exception {
		List<QuoteTask> tasks = new ArrayList<>();
		for (TravelCompany company : companies) {
			tasks.add(new QuoteTask(company, travelInfo));
		}
		List<Future<TravelQuote>> futures = exec.invokeAll(tasks, time, unit);
		List<TravelQuote> quotes = new ArrayList<>(tasks.size());
		Iterator<QuoteTask> taskIter = tasks.iterator();
		for (Future<TravelQuote> f : futures) {
			QuoteTask task = taskIter.next();
			try {
				quotes.add(f.get());
			} catch (ExecutionException e) {
				quotes.add(task.getFailureQuote(e.getCause()));
			} catch (CancellationException e) {
				quotes.add(task.getTimeoutQuote(e));
			}
		}
		Collections.sort(quotes, ranking);
		return quotes;
	}

}
class QuoteTask implements Callable<TravelQuote> {
	private final TravelCompany company;
	private final TravelInfo travelInfo;
	
	public QuoteTask(TravelCompany company, TravelInfo travelInfo) {
		this.company = company;
		this.travelInfo = travelInfo;
	}
	
	/**
	 * @param e
	 * @return
	 */
	public TravelQuote getTimeoutQuote(CancellationException e) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @param cause
	 * @return
	 */
	public TravelQuote getFailureQuote(Throwable cause) {
		// TODO Auto-generated method stub
		return null;
	}

	public TravelQuote call() {
		return company.solicitQuote(travelInfo);
	}
	
}
/**
 * 
 * Description: 公司
 * @author lianliang
 * @date 2018年1月17日 下午3:48:26
 */
class TravelCompany {

	/**
	 * @param travelInfo
	 * @return
	 */
	public TravelQuote solicitQuote(TravelInfo travelInfo) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
/**
 * 
 * Description: 旅行信息
 * @author lianliang
 * @date 2018年1月17日 下午3:48:23
 */
class TravelInfo {
	
}
/**
 * 
 * Description: 
 * @author lianliang
 * @date 2018年1月17日 下午3:48:20
 */
class TravelQuote {
	
}