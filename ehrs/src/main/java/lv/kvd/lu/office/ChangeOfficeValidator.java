package lv.kvd.lu.office;

import java.util.List;

import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;

/**
 * Validation of change office form data
 * 
 * @author vitalik
 * 
 */
public class ChangeOfficeValidator extends AddOfficeFormValidator {
	
	private static final String DEFAULT_VALUE = "...no such property...";
	
	public Long id;
	
	@Override
	public void validate(Object target, Errors errors) {
		Office form = (Office) target;
		id = form.getId();
		if (!StringUtils.hasText(form.getName()) || !StringUtils.hasText(form.getAddress())
				|| !StringUtils.hasText(form.getCity()) || form.getCountryId() == null
				|| !StringUtils.hasText(form.getZip())) {
			errors.reject("ER0001", DEFAULT_VALUE);
		}
		if(!isNameUnique(form.getName())) {
			errors.reject("ER0010", DEFAULT_VALUE);
		}
		if(!isLengthMatch(form)) {
			errors.reject("ER0009", DEFAULT_VALUE);
		}
	}
	
	/**
	 * Checks if name is unique or it is current office name
	 */
	@SuppressWarnings("unchecked")
	@Override
	public boolean isNameUnique(String name) {
		OfficeDaoImpl officeDao = getOfficeDao();
		List<Office> list = officeDao.getRecords("name", name);
		if(list.isEmpty()) {
			return true;
		} else if(list.size() == 1 && 
				list.get(0).getId().equals(id)) {
			return true;
		}
		return false;
	}
}
