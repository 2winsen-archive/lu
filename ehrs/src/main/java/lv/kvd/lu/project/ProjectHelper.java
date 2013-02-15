package lv.kvd.lu.project;

import java.util.List;

import lv.kvd.lu.utils.FunctionUtils;

/**
 * Helper class contains methods for project funcionality
 * 
 * @author vitalik
 * 
 */
public class ProjectHelper {

	private ProjectDaoImpl projectDao;

	public ProjectDaoImpl getProjectDao() {
		return projectDao;
	}

	public void setProjectDao(ProjectDaoImpl projectDao) {
		this.projectDao = projectDao;
	}

	/**
	 * Creates in DB new project entry
	 * 
	 * @param form
	 * @return 1 if saved successfully
	 * 
	 */
	public int addNewProject(Project project) {
		projectDao.saveRecord(project);
		return 1;
	}

	/**
	 * Gets DB project entries by specified for criteria
	 * 
	 * @param form
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List viewProjects(Project form) {
		String[] fieldNames = { "name", "manager_surname" };
		String[] values = { FunctionUtils.nullSafeGet(form.getName()) + "%", FunctionUtils.nullSafeGet(form.getManagerSurname()) + "%" };
		return projectDao.getRecords(fieldNames, values);
	}

	/**
	 * Updates project information
	 * 
	 * @return 1 if saved successfully
	 */
	public int updateProject(Project form, Project projectDefault) {
		Project project = new Project();
		project.setId(projectDefault.getId());
		project.setName(form.getName());
		project.setManagerName(form.getManagerName());
		project.setManagerSurname(form.getManagerSurname());
		project.setComments(form.getComments());
		projectDao.saveRecord(project);
		return 1;
	}

}
