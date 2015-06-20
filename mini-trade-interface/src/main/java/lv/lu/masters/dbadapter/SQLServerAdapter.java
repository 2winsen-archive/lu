package lv.lu.masters.dbadapter;

import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import lv.lu.masters.businessobjects.CashEquityTrade;
import lv.lu.masters.businessobjects.FutureTrade;
import lv.lu.masters.businessobjects.OptionTrade;
import lv.lu.masters.businessobjects.SwapTrade;
import lv.lu.masters.businessobjects.Trade;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;

public class SQLServerAdapter {

	private static final int SCHEDULE_SECONDS = 1;
	private static final Logger log = Logger.getLogger(SQLServerAdapter.class);

	private DataRepository dataRepository;
	
	BlockingQueue<Trade> queue;
	
	ApplicationContext context;
	
	public SQLServerAdapter(ApplicationContext context, BlockingQueue<Trade> queue) {
		this.queue = queue;
		this.context = context;
	}
	
	private void setBeans() {
	  	dataRepository = (DataRepository)context.getBean("dataRepository");
	}

	public void start() {
		log.info("SQL Server Adapter started.");
	  	setBeans();
		startCashEquityExecutor();
		startFutreExecutor();
		startOptionExecutor();
		startSwapExecutor();
	}

	private void startCashEquityExecutor() {
		ScheduledExecutorService exec = Executors.newSingleThreadScheduledExecutor();
		exec.scheduleAtFixedRate(new Runnable() {
			public void run() {
				List<CashEquityTrade> cashEquities = dataRepository.getUnprocessedCashEquities();
				if (cashEquities != null) {
					for (CashEquityTrade cashEquity : cashEquities) {
						dataRepository.updateSentToMiddleware(cashEquity);
						try {
							queue.put(cashEquity);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
			}
		}, 0, SCHEDULE_SECONDS, TimeUnit.SECONDS);
	}

	private void startFutreExecutor() {
		ScheduledExecutorService exec = Executors.newSingleThreadScheduledExecutor();
		exec.scheduleAtFixedRate(new Runnable() {
			public void run() {
				List<FutureTrade> futures = dataRepository.getUnprocessedFutures();
				if (futures != null) {
					for (FutureTrade future : futures) {
						dataRepository.updateSentToMiddleware(future);
						try {
							queue.put(future);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
			}
		}, 0, SCHEDULE_SECONDS, TimeUnit.SECONDS);
	}

	private void startOptionExecutor() {
		ScheduledExecutorService exec = Executors.newSingleThreadScheduledExecutor();
		exec.scheduleAtFixedRate(new Runnable() {
			public void run() {
				List<OptionTrade> options = dataRepository.getUnprocessedOptions();
				if (options != null) {
					for (OptionTrade option : options) {
						dataRepository.updateSentToMiddleware(option);
						try {
							queue.put(option);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
				System.out.println("Running cash...");
			}
		}, 0, SCHEDULE_SECONDS, TimeUnit.SECONDS);
	}

	private void startSwapExecutor() {
		ScheduledExecutorService exec = Executors.newSingleThreadScheduledExecutor();
		exec.scheduleAtFixedRate(new Runnable() {
			public void run() {
				List<SwapTrade> swaps = dataRepository.getUnprocessedSwaps();
				if (swaps != null) {
					for (SwapTrade swap : swaps) {
						dataRepository.updateSentToMiddleware(swap);
						try {
							queue.put(swap);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
				System.out.println("Running cash...");
			}
		}, 0, SCHEDULE_SECONDS, TimeUnit.SECONDS);
	}

}
