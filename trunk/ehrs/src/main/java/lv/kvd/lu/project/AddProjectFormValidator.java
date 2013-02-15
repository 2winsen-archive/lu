package lv.kvd.lu.project;

import java.util.List;

import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Validation of add project form data
 * 
 * @author vitalik
 * 
 */
public class AddProjectFormValidator implements Validator {

	private static final String DEFAULT_VALUE = "...no such property...";

	private ProjectDaoImpl projectDao;

	public ProjectDaoImpl getProjectDao() {
		return projectDao;
	}

	public void setProjectDao(ProjectDaoImpl projectDao) {
		this.projectDao = projectDao;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean supports(Class clazz) {
		return Project.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Project form = (Project) target;
		if (!StringUtils.hasText(form.getName()) || !StringUtils.hasText(form.getManagerName())
				|| !StringUtils.hasText(form.getManagerSurname())) {
			errors.reject("ER0001", DEFAULT_VALUE);
		}
		if (!isNameUnique(form.getName())) {
			errors.reject("ER0014", DEFAULT_VALUE);
		}
		if (!isLengthMatch(form)) {
			errors.reject("ER0009", DEFAULT_VALUE);
		}
	}

	/**
	 * Checks if project name is unique
	 * 
	 * @param name
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public boolean isNameUnique(String name) {
		List list = projectDao.getRecords("name", name);
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
	public boolean isLengthMatch(Project form) {
		if (form.getName().length() > 30 || form.getManagerName().length() > 30 || 
				form.getManagerSurname().length() > 30 || form.getComments().length() > 100) {
			return false;
		}
		return true;
	}
}
