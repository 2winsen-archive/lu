package lv.kvd.lu.group;

import java.util.List;

import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Validation of Change group form data
 * @author vitalik
 *
 */
public class ChangeGroupValidator extends AddGroupFormValidator implements Validator {
	
	private static final String DEFAULT_VALUE = "...no such property...";
	
	public Long id;
	
	@Override
	public void validate(Object target, Errors errors) {
		Group form = (Group)target;
		id = form.getId();
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
	 * Checks if name is unique or it is current group name
	 */
	@SuppressWarnings("unchecked")
	@Override
	public boolean isNameUnique(String name) {
		GroupDaoImpl groupDao = getGroupDao();
		List<Group> list = groupDao.getRecords("name", name);
		if(list.isEmpty()) {
			return true;
		} else if(list.size() == 1 && 
				list.get(0).getId().equals(id)) {
			return true;
		}
		return false;
	}
	
}
