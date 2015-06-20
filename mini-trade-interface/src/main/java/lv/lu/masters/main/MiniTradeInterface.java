package lv.lu.masters.main;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;

import lv.lu.masters.businessobjects.Trade;
import lv.lu.masters.dbadapter.SQLServerAdapter;
import lv.lu.masters.exchangefacade.ExchangeFacade;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MiniTradeInterface {
	
	public static void main(String[] args) {
		final ApplicationContext context = new ClassPathXmlApplicationContext("/applicationContext.xml");		
		
		BlockingQueue<Trade> queue = new SynchronousQueue<Trade>();
		TradeQueueConsumer worker = new TradeQueueConsumer(queue, new ExchangeFacade());
        worker.start();
		
		SQLServerAdapter adapter = new SQLServerAdapter(context, queue);
		adapter.start();
	}
	
}
