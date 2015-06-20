package lv.lu.masters.businessobjects;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@NamedQueries({ @NamedQuery(name = "getUnprocessedCashEquities", query = "SELECT c FROM CashEquityTrade c WHERE c.sentToMiddleware = null ORDER BY c.id asc") })
@Entity
public class CashEquityTrade extends Trade {
	
	private String resultRisk;
	
	private String resultExchange;

	public String getResultRisk() {
		return resultRisk;
	}

	public void setResultRisk(String resultRisk) {
		this.resultRisk = resultRisk;
	}

	public String getResultExchange() {
		return resultExchange;
	}

	public void setResultExchange(String resultExchange) {
		this.resultExchange = resultExchange;
	}

}
