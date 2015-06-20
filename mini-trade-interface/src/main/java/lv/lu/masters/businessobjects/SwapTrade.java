package lv.lu.masters.businessobjects;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@NamedQueries({ @NamedQuery(name = "getUnprocessedSwaps", query = "SELECT s FROM SwapTrade s WHERE s.sentToMiddleware = null ORDER BY s.id asc") })
@Entity
public class SwapTrade extends Trade {

}
