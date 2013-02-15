package lv.kvd.lu.user;

import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Validation of Change user additional form data
 * @author vitalik
 *
 */
public class ChangeAddUserValidator implements Validator {
	
	private static final String DEFAULT_VALUE = "...no such property...";

	@SuppressWarnings("unchecked")
	@Override
	public boolean supports(Class clazz) {
		return AddUserForm.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		AddUserForm form = (AddUserForm)target;
		if(!StringUtils.hasText(form.getSalary())) {
			errors.reject("ER0007", DEFAULT_VALUE);
		}
		if(!salaryMatch(form.getSalary())) {
			errors.reject("ER0008", DEFAULT_VALUE);
		}
		if(!isLengthMatch(form)) {
			errors.reject("ER0009", DEFAULT_VALUE);
		}
	}
	
	/**
	 * Checks if salary contains only numbers
	 * @return
	 */
	public boolean salaryMatch(String salary) {
		for(int i=0; i<salary.length(); i++) {		
			if(!Character.isDigit(salary.charAt(i))) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * Check if all field do not exceed maximum length provided by DB
	 * 
	 * @param form
	 * @return
	 */
	public boolean isLengthMatch(AddUserForm form) {
		if (form.getSalary().length() > 10) {
			return false;
		}
		return true;
	}

}
