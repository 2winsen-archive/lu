package lv.kvd.lu.user;

import java.util.List;

import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;

/**
 * Validation of Change user form data
 * @author vitalik
 *
 */
public class ChangeUserValidator extends AddUserFormValidator {
	
	private static final String DEFAULT_VALUE = "...no such property...";
	
	public Long id;
	
	@SuppressWarnings("unchecked")
	@Override
	public boolean supports(Class clazz) {
		return super.supports(clazz);
	}
	
	@Override
	public void validate(Object target, Errors errors) {
		AddUserForm form = (AddUserForm)target;
		id = form.getId();
		if(!StringUtils.hasText(form.getName()) || !StringUtils.hasText(form.getSurname())
				|| !StringUtils.hasText(form.getUsername()) || !StringUtils.hasText(form.getPassword())
				|| !StringUtils.hasText(form.getRetypePassword()) || !StringUtils.hasText(form.getEmail())
				|| !StringUtils.hasText(form.getAddress()) || !StringUtils.hasText(form.getCity()) 
				|| form.getCountryId() == null || !StringUtils.hasText(form.getZip())) {
			errors.reject("ER0001", DEFAULT_VALUE);
		}
		if(!form.getPassword().equals(form.getRetypePassword())) {
			errors.reject("ER0002", DEFAULT_VALUE);
		}
		if(form.getPassword().length() < 8) {
			errors.reject("ER0003", DEFAULT_VALUE);
		}
		if(!isStrongPassword(form.getPassword()) ) {
			errors.reject("ER0004", DEFAULT_VALUE);
		}
		if(!isUsernameUnique(form.getUsername())) {
			errors.reject("ER0005", DEFAULT_VALUE);
		}
		if(!isEmailUnique(form.getEmail())) {
			errors.reject("ER0006", DEFAULT_VALUE);
		}
		if(!isLengthMatch(form)) {
			errors.reject("ER0009", DEFAULT_VALUE);
		}
	}
	
	/**
	 * Checks if email is unique or it is current user email
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Boolean isEmailUnique(String email) {
		UserDaoImpl userDao = getUserDao();
		List<User> list = userDao.getRecords("email", email);
		if(list.isEmpty()) {
			return true;
		} else if(list.size() == 1 && 
				list.get(0).getId().equals(id)) {
			return true;
		}
		return false;
	}
	
	/**
	 * Checks if username is unique or it is current user username
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Boolean isUsernameUnique(String username) {
		UserDaoImpl userDao = getUserDao();
		List<User> list = userDao.getRecords("username", username);
		if(list.isEmpty()) {
			return true;
		} else if(list.size() == 1 && 
				list.get(0).getId().equals(id)) {
			return true;
		}
		return false;
	}

}
