package lv.kvd.lu.message;

import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Validation of Change group form data
 * @author vitalik
 *
 */
public class ComposeMessageValidator implements Validator {
	
	private static final String DEFAULT_VALUE = "...no such property...";

	@SuppressWarnings("unchecked")
	@Override
	public boolean supports(Class clazz) {
		return Message.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Message form = (Message)target;
		if(!StringUtils.hasText(form.getTitle()) || form.getUser().getId() == null || 
				!StringUtils.hasText(form.getEntry())) {
			errors.reject("ER0001", DEFAULT_VALUE);
		}
		if(!isLengthMatch(form)) {
			errors.reject("ER0009", DEFAULT_VALUE);
		}
	}
	
	/**
	 * Check if all field do not exceed maximum length provided by DB
	 * 
	 * @param form
	 * @return
	 */
	public boolean isLengthMatch(Message form) {
		if (form.getTitle().length() > 30 || form.getEntry().length() > 500) {
			return false;
		}
		return true;
	}
	
}
