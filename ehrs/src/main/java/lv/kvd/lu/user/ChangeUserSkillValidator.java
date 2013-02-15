package lv.kvd.lu.user;

import java.util.List;

import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Validate data on user change skill form
 * @author vitalik
 *
 */
public class ChangeUserSkillValidator implements Validator {
	
	private static final String DEFAULT_VALUE = "...no such property...";
	
	private UserSkillsDaoImpl userSkillsDao;
	
	public UserSkillsDaoImpl getUserSkillsDao() {
		return userSkillsDao;
	}

	public void setUserSkillsDao(UserSkillsDaoImpl userSkillsDao) {
		this.userSkillsDao = userSkillsDao;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean supports(Class clazz) {
		return UserSkills.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		UserSkills form = (UserSkills)target;
		if(!StringUtils.hasText(form.getExperience()) || form.getSkill().getId() == null) {
			errors.reject("ER0001", DEFAULT_VALUE);
		}
		if(!isLengthMatch(form)) {
			errors.reject("ER0009", DEFAULT_VALUE);
		}
		if(!experienceMatch(form.getExperience())) {
			errors.reject("ER0012", DEFAULT_VALUE);
		}
		if(hasThisSkill(form.getUserId(), form.getSkill().getId())) {
			errors.reject("ER0013", DEFAULT_VALUE);
		}
	}
	
	/**
	 * Check if all field do not exceed maximum length provided by DB
	 * 
	 * @param form
	 * @return
	 */
	public boolean isLengthMatch(UserSkills form) {
		if (form.getExperience().length() > 10) {
			return false;
		}
		return true;
	}
	
	/**
	 * Checks if ecperience contains only numbers
	 * @return
	 */
	public boolean experienceMatch(String experience) {
		for(int i=0; i<experience.length(); i++) {		
			if(!Character.isDigit(experience.charAt(i))) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * Check if user has this skill in his skills list
	 * @param id
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public boolean hasThisSkill(Long userId, Long skillId) {
		if(skillId != null) {
			String[] fieldNames = {"user_id", "skill_id"};
			String[] values = {userId.toString(), skillId.toString()};
			List list = userSkillsDao.getRecords(fieldNames, values);
			if(list.isEmpty()) {
				return false;
			}
		}
		return true;
	}

}
