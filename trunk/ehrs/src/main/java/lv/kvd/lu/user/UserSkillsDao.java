package lv.kvd.lu.user;

import java.sql.BatchUpdateException;

import lv.kvd.lu.utils.AbstractDao;

public interface UserSkillsDao extends AbstractDao {
	
	/**
	 * Removes entry in db
	 * @param id
	 * @return
	 * @throws BatchUpdateException
	 */
	public int removeRecord(Object id) throws BatchUpdateException;

}
