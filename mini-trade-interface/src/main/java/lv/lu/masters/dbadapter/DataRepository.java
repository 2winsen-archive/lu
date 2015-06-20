package lv.lu.masters.dbadapter;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import lv.lu.masters.businessobjects.CashEquityTrade;
import lv.lu.masters.businessobjects.FutureTrade;
import lv.lu.masters.businessobjects.OptionTrade;
import lv.lu.masters.businessobjects.SwapTrade;
import lv.lu.masters.businessobjects.Trade;

import org.springframework.transaction.annotation.Transactional;

@Transactional
public class DataRepository {

	private EntityManager em;

	@PersistenceContext
	public void setEntityManager(EntityManager em) {
		this.em = em;
	}

	public EntityManager getEntityManager() {
		return em;
	}

	public void save(Trade trade) {
		if (trade != null) {
			if (trade.getId() == null) {
				getEntityManager().persist(trade);
			} else {
				getEntityManager().merge(trade);
			}
		}
	}
	
	public void updateSentToMiddleware(Trade trade) {
		if (trade != null) {
			trade = getById(trade.getClass(), trade.getId());
			trade.setSentToMiddleware(new Date());
			getEntityManager().merge(trade);
		}
	}
	
	public <T extends Trade> T getById(Class<T> clazz, Long id) {
		return getEntityManager().find(clazz, id);
	}
	
	@SuppressWarnings("unchecked")
	public List<CashEquityTrade> getUnprocessedCashEquities() {
		List<CashEquityTrade> result = null;
		Query query = null;
		query = getEntityManager().createNamedQuery("getUnprocessedCashEquities");
		result = (List<CashEquityTrade>)query.getResultList();
		return result;
	}
	
	@SuppressWarnings("unchecked")
	public List<FutureTrade> getUnprocessedFutures() {
		List<FutureTrade> result = null;
		Query query = null;
		query = getEntityManager().createNamedQuery("getUnprocessedFutures");
		result = (List<FutureTrade>)query.getResultList();
		if (result.isEmpty()) {
			return null;
		}
		return result;
	}
	
	@SuppressWarnings("unchecked")
	public List<SwapTrade> getUnprocessedSwaps() {
		List<SwapTrade> result = null;
		Query query = null;
		query = getEntityManager().createNamedQuery("getUnprocessedSwaps");
		result = (List<SwapTrade>)query.getResultList();
		if (result.isEmpty()) {
			return null;
		}
		return result;
	}
	
	@SuppressWarnings("unchecked")
	public List<OptionTrade> getUnprocessedOptions() {
		List<OptionTrade> result = null;
		Query query = null;
		query = getEntityManager().createNamedQuery("getUnprocessedOptions");
		result = (List<OptionTrade>)query.getResultList();
		if (result.isEmpty()) {
			return null;
		}
		return result;
	}
	
}
