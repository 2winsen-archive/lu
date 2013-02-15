package lv.kvd.lu.user;

import java.util.List;

import lv.kvd.lu.utils.AbstractDao;

/**
 * 
 * @author vitalik
 *
 */
public interface UserDao extends AbstractDao {
	
	/**
	 * Wise get records 
	 * @param equals flag if fieldname is equals with value or not
	 * @param fieldName
	 * @param value
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List getRecords(boolean equals, String fieldName, String value);
	
}
