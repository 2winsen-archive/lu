package lv.lu.masters.main;

import java.util.concurrent.BlockingQueue;

import lv.lu.masters.businessobjects.CashEquityTrade;
import lv.lu.masters.businessobjects.Trade;
import lv.lu.masters.exchangefacade.ExchangeFacade;
import lv.lu.masters.integration.gateway.TradeCollector;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

public class TradeQueueConsumer extends Thread {

	private static final Logger log = Logger.getLogger(TradeQueueConsumer.class);

	private final BlockingQueue<Trade> queue;
	
	private ExchangeFacade exchangeFacade;

	@Autowired
	TradeCollector tradeCollector;

	public TradeQueueConsumer(BlockingQueue<Trade> queue, ExchangeFacade exchangeFacade) {
		this.queue = queue;
		this.exchangeFacade = exchangeFacade;
	}

	public void run() {
		try {
			while (true) {
				Trade trade = queue.take();
				if (trade != null) {
					// Spring Integration gateway
					tradeCollector.collectTrade(trade);
					
					// Cash Equities are reported to Stock Exchange
					if (trade instanceof CashEquityTrade) {
						exchangeFacade.processCashEquityToExchange((CashEquityTrade)trade);
					}
				}
			}
		} catch (InterruptedException ie) {
			log.error(ie.getMessage());
		}
	}
}
