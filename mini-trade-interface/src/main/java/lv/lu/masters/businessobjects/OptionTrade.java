package lv.lu.masters.businessobjects;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@NamedQueries({ @NamedQuery(name = "getUnprocessedOptions", query = "SELECT o FROM OptionTrade o WHERE o.sentToMiddleware = null ORDER BY o.id asc") })
@Entity
public class OptionTrade extends Trade {
}
