package lv.kvd.lu.group;

import java.util.List;

import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Validation of add group form data
 * @author vitalik
 *
 */
public class AddGroupFormValidator implements Validator {
	
	private static final String DEFAULT_VALUE = "...no such property...";

	private GroupDaoImpl groupDao;
	
	public GroupDaoImpl getGroupDao() {
		return groupDao;
	}

	public void setGroupDao(GroupDaoImpl groupDao) {
		this.groupDao = groupDao;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean supports(Class clazz) {
		return Group.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Group form = (Group)target;
		if(!StringUtils.hasText(form.getName())) {
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
	 * Checks if group name is unique
	 * @param name
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public boolean isNameUnique(String name) {
		List list = groupDao.getRecords("name", name);
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
	public boolean isLengthMatch(Group form) {
		if (form.getName().length() > 30 || form.getComments().length() > 100) {
			return false;
		}
		return true;
	}

}
