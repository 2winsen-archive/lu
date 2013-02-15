package lv.kvd.lu.office;

import java.util.List;

import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Validation of add group form data
 * 
 * @author vitalik
 * 
 */
public class AddOfficeFormValidator implements Validator {

	private static final String DEFAULT_VALUE = "...no such property...";

	private OfficeDaoImpl officeDao;

	public OfficeDaoImpl getOfficeDao() {
		return officeDao;
	}

	public void setOfficeDao(OfficeDaoImpl officeDao) {
		this.officeDao = officeDao;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean supports(Class clazz) {
		return Office.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Office form = (Office) target;
		if (!StringUtils.hasText(form.getName()) || !StringUtils.hasText(form.getAddress())
				|| !StringUtils.hasText(form.getCity()) || form.getCountryId() == null
				|| !StringUtils.hasText(form.getZip())) {
			errors.reject("ER0001", DEFAULT_VALUE);
		}
		if (!isNameUnique(form.getName())) {
			errors.reject("ER0015", DEFAULT_VALUE);
		}
		if (!isLengthMatch(form)) {
			errors.reject("ER0009", DEFAULT_VALUE);
		}
	}

	/**
	 * Checks if office name is unique
	 * 
	 * @param name
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public boolean isNameUnique(String name) {
		List list = officeDao.getRecords("name", name);
		if (list.isEmpty()) {
			return true;
		}
		return false;
	}

	/**
	 * Check if all field do not exceed maximum length provided by DB
	 * 
	 * @param form
	 * @return
	 */
	public boolean isLengthMatch(Office form) {
		if (form.getName().length() > 30 || form.getComments().length() > 100 || form.getCity().length() > 30
				|| form.getZip().length() > 10 || form.getAddress().length() > 50) {
			return false;
		}
		return true;
	}

}
