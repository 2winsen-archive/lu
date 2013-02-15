package lv.kvd.lu.office;

import java.util.List;

import lv.kvd.lu.utils.FunctionUtils;

/**
 * Helper class contains methods for office funcionality
 * 
 * @author vitalik
 * 
 */
public class OfficeHelper {
	
	private OfficeDaoImpl officeDao;
	
	public OfficeDaoImpl getOfficeDao() {
		return officeDao;
	}

	public void setOfficeDao(OfficeDaoImpl officeDao) {
		this.officeDao = officeDao;
	}

	/**
	 * Creates in DB new office entry
	 * 
	 * @param form
	 * @return 1 if saved successfully
	 * 
	 */
	public int addNewOffice(Office office) {
		officeDao.saveRecord(office);
		return 1;
	}
	
	/**
	 * Gets DB Office entries by specified for criteria
	 * 
	 * @param form
	 * @return 
	 */
	@SuppressWarnings("unchecked")
	public List viewOffices(Office form) {
		String[] fieldNames = {"name"};
		String[] values = {FunctionUtils.nullSafeGet(form.getName()) + "%"};
		return officeDao.getRecords(fieldNames, values);
	}
	
	/**
	 * Updates office information
	 * @return 1 if saved successfully
	 */
	public int updateOffice(Office form) {
		officeDao.saveRecord(form);
		return 1;
	}
}
