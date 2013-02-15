package lv.kvd.lu.skill;

import java.util.List;

import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Validation of add skill form data
 * 
 * @author vitalik
 * 
 */
public class AddSkillFormValidator implements Validator {

	private static final String DEFAULT_VALUE = "...no such property...";

	private SkillDaoImpl skillDao;

	public SkillDaoImpl getSkillDao() {
		return skillDao;
	}

	public void setSkillDao(SkillDaoImpl skillDao) {
		this.skillDao = skillDao;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean supports(Class clazz) {
		return Skill.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Skill form = (Skill) target;
		if (!StringUtils.hasText(form.getName()) || form.getGroupId() == null
				|| !StringUtils.hasText(form.getDifficulty())) {
			errors.reject("ER0001", DEFAULT_VALUE);
		}
		if (!isNameUnique(form.getName())) {
			errors.reject("ER0011", DEFAULT_VALUE);
		}
		if (!isLengthMatch(form)) {
			errors.reject("ER0009", DEFAULT_VALUE);
		}
	}

	/**
	 * Checks if skill name is unique
	 * 
	 * @param name
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public boolean isNameUnique(String name) {
		List list = skillDao.getRecords("name", name);
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
	public boolean isLengthMatch(Skill form) {
		if (form.getName().length() > 30 || form.getDifficulty().length() > 30 || form.getComments().length() > 100) {
			return false;
		}
		return true;
	}

}
