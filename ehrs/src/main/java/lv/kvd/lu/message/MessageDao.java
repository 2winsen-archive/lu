package lv.kvd.lu.message;

import java.util.List;

import lv.kvd.lu.utils.AbstractDao;

public interface MessageDao extends AbstractDao {
	
	/**
	 * Get DB data where fields are 'fieldNames' and values are 'values'
	 * @param sql symbol of compare = or like
	 * @param fieldNames
	 * @param values
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List getRecords(String symbol, String[] fieldNames, String[] values);

}
