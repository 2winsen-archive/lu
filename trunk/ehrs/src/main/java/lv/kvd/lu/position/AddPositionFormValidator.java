package lv.kvd.lu.position;

import java.util.List;

import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class AddPositionFormValidator implements Validator {

	private static final String DEFAULT_VALUE = "...no such property...";
	
	private PositionDaoImpl positionDao;
	
	public PositionDaoImpl getPositionDao() {
		return positionDao;
	}

	public void setPositionDao(PositionDaoImpl positionDao) {
		this.positionDao = positionDao;
	}

	@SuppressWarnings("unchecked")
	public boolean supports(Class clazz) {
		return Position.class.isAssignableFrom(clazz);
	}
	
	public void validate(Object target, Errors errors) {
		Position form = (Position)target;
		if(!StringUtils.hasText(form.getName())) {
			errors.reject("ER0001", DEFAULT_VALUE);
		}
		if(!isNameUnique(form.getName())) {
			errors.reject("ER0016", DEFAULT_VALUE);
		}
		if(!isLengthMatch(form)) {
			errors.reject("ER0009", DEFAULT_VALUE);
		}
	}
	
	/**
	 * Checks if group name is unique
	 * @param name
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public boolean isNameUnique(String name) {
		List list = positionDao.getRecords("name", name);
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
	public boolean isLengthMatch(Position form) {
		if (form.getName().length() > 30 || form.getComments().length() > 100) {
			return false;
		}
		return true;
	}
	
}
