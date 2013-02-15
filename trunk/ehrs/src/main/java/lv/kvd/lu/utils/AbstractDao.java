package lv.kvd.lu.utils;

import java.sql.BatchUpdateException;
import java.util.List;

public interface AbstractDao {

	/**
	 * Gets <b>one</b> record from table
	 * 
	 * @param id
	 * @return User
	 */
	public Object getRecord(Long id);

	/**
	 * Gets <b>all</b> records from table
	 * 
	 * @return List
	 */
	@SuppressWarnings("unchecked")
	public List getRecords();

	/**
	 * Save a User record to table
	 * 
	 * @param user
	 * @throws Exception 
	 */
	public void saveRecord(Object obj);

	/**
	 * Removes one record from table
	 * 
	 * @param id
	 */
	public int removeRecord(Long id) throws BatchUpdateException;
	
	/**
	 * Get DB data where field is fieldName = value
	 * @param fieldName
	 * @param value
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List getRecords(String fieldName, String value);
	
	/**
	 * Get DB data where fields are <b>like</b> 'fieldNames' and values are 'values'
	 * @param fieldNames
	 * @param values
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List getRecords(String[] fieldNames, String[] values);

}
