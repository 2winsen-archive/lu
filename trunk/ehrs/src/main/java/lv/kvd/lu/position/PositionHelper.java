package lv.kvd.lu.position;

import java.util.List;

import lv.kvd.lu.utils.FunctionUtils;

/**
 * Helper class contains methods for position funcionality
 * 
 * @author vitalik
 * 
 */
public class PositionHelper {
	
	private PositionDaoImpl positionDao;
	
	public PositionDaoImpl getPositionDao() {
		return positionDao;
	}

	public void setPositionDao(PositionDaoImpl positionDao) {
		this.positionDao = positionDao;
	}

	/**
	 * Creates in DB new position entry
	 * 
	 * @param form
	 * @return 1 if saved successfully
	 * 
	 */
	public int addNewposition(Position position) {
		positionDao.saveRecord(position);
		return 1;
	}
	
	/**
	 * Gets DB position entries by specified for criteria
	 * 
	 * @param form
	 * @return 
	 */
	@SuppressWarnings("unchecked")
	public List viewPositions(Position form) {
		String[] fieldNames = {"name"};
		String[] values = {FunctionUtils.nullSafeGet(form.getName()) + "%"};
		return positionDao.getRecords(fieldNames, values);
	}
	
	/**
	 * Updates position information
	 * @return 1 if saved successfully
	 */
	public int updatePosition(Position form) {
		positionDao.saveRecord(form);
		return 1;
	}
}
