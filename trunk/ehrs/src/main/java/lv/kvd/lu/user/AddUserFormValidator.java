package lv.kvd.lu.user;

import java.util.List;

import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Validation of Add new user form data
 * 
 * @author vitalik
 * 
 */
public class AddUserFormValidator implements Validator {

	private static final String DEFAULT_VALUE = "...no such property...";

	private UserDaoImpl userDao;

	public UserDaoImpl getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDaoImpl userDao) {
		this.userDao = userDao;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean supports(Class clazz) {
		return AddUserForm.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		AddUserForm form = (AddUserForm) target;
		if (!StringUtils.hasText(form.getName()) || !StringUtils.hasText(form.getSurname())
				|| !StringUtils.hasText(form.getUsername()) || !StringUtils.hasText(form.getPassword())
				|| !StringUtils.hasText(form.getRetypePassword()) || !StringUtils.hasText(form.getEmail())
				|| !StringUtils.hasText(form.getAddress()) || !StringUtils.hasText(form.getCity())
				|| form.getCountryId() == null || !StringUtils.hasText(form.getZip())) {
			errors.reject("ER0001", DEFAULT_VALUE);
		}
		if (!form.getPassword().equals(form.getRetypePassword())) {
			errors.reject("ER0002", DEFAULT_VALUE);
		}
		if (form.getPassword().length() < 8) {
			errors.reject("ER0003", DEFAULT_VALUE);
		}
		if (!isStrongPassword(form.getPassword())) {
			errors.reject("ER0004", DEFAULT_VALUE);
		}
		if (!isUsernameUnique(form.getUsername())) {
			errors.reject("ER0005", DEFAULT_VALUE);
		}
		if (!isEmailUnique(form.getEmail())) {
			errors.reject("ER0006", DEFAULT_VALUE);
		}
		if(!isLengthMatch(form)) {
			errors.reject("ER0009", DEFAULT_VALUE);
		}
	}

	/**
	 * Chacks if password contains at least one uppercase letter one lowercase letter and one digit
	 * 
	 * @param password
	 * @return
	 */
	public Boolean isStrongPassword(String password) {
		boolean hasUpper = false;
		boolean hasLower = false;
		boolean hasDigit = false;
		for (int i = 0; i < password.length(); i++) {
			if (Character.isUpperCase(password.charAt(i))) {
				hasUpper = true;
			}
			if (Character.isLowerCase(password.charAt(i))) {
				hasLower = true;
			}
			if (Character.isDigit(password.charAt(i))) {
				hasDigit = true;
			}
		}
		if (hasUpper && hasLower && hasDigit)
			return true;
		return false;
	}

	/**
	 * Checks if in DB exists user with this username; useraname is unique
	 * 
	 * @param username
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Boolean isUsernameUnique(String username) {
		List list = userDao.getRecords("username", username);
		if (list.isEmpty()) {
			return true;
		}
		return false;
	}

	/**
	 * Checks if in DB exists user with this email; email is unique
	 * 
	 * @param email
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Boolean isEmailUnique(String email) {
		List list = userDao.getRecords("email", email);
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
	public boolean isLengthMatch(AddUserForm form) {
		if (form.getName().length() > 30 || form.getSurname().length() > 30 || form.getUsername().length() > 30
				|| form.getEmail().length() > 50 || form.getCity().length() > 30 || form.getZip().length() > 10
				|| form.getAddress().length() > 50) {
			return false;
		}
		return true;
	}

}
