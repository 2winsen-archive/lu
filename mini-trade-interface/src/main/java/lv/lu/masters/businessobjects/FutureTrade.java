package lv.lu.masters.businessobjects;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@NamedQueries({ @NamedQuery(name = "getUnprocessedFutures", query = "SELECT f FROM FutureTrade f WHERE f.sentToMiddleware = null ORDER BY f.id asc") })
@Entity
public class FutureTrade extends Trade {

}
