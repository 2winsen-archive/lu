package lv.kvd.lu.user;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.context.SecurityContextHolder;

import lv.kvd.lu.country.CountryDaoImpl;
import lv.kvd.lu.office.OfficeDaoImpl;
import lv.kvd.lu.position.PositionDaoImpl;
import lv.kvd.lu.project.ProjectDaoImpl;
import lv.kvd.lu.utils.FunctionUtils;

/**
 * Helper class contains methods for user funcionality
 * 
 * @author vitalik
 * 
 */
public class UserHelper {

	private static final String NOT_AVAILABLE = "n/a";
	private static final Long START_ID = 1L;

	private UserDaoImpl userDao;
	private CountryDaoImpl countryDao;
	private PositionDaoImpl positionDao;
	private OfficeDaoImpl officeDao;
	private ProjectDaoImpl projectDao;
	private UserSkillsDaoImpl userSkillsDao;

	public UserDaoImpl getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDaoImpl userDao) {
		this.userDao = userDao;
	}

	public CountryDaoImpl getCountryDao() {
		return countryDao;
	}

	public void setCountryDao(CountryDaoImpl countryDao) {
		this.countryDao = countryDao;
	}

	public PositionDaoImpl getPositionDao() {
		return positionDao;
	}

	public void setPositionDao(PositionDaoImpl positionDao) {
		this.positionDao = positionDao;
	}
	
	public OfficeDaoImpl getOfficeDao() {
		return officeDao;
	}

	public void setOfficeDao(OfficeDaoImpl officeDao) {
		this.officeDao = officeDao;
	}

	public ProjectDaoImpl getProjectDao() {
		return projectDao;
	}

	public void setProjectDao(ProjectDaoImpl projectDao) {
		this.projectDao = projectDao;
	}

	public UserSkillsDaoImpl getUserSkillsDao() {
		return userSkillsDao;
	}

	public void setUserSkillsDao(UserSkillsDaoImpl userSkillsDao) {
		this.userSkillsDao = userSkillsDao;
	}

	/**
	 * Populates userType select with values
	 * 
	 * @return list
	 */
	@SuppressWarnings("unchecked")
	public List populateUserType() {
		List<String> list = new ArrayList<String>();
		list.add("ROLE_ADMIN");
		list.add("ROLE_POWER");
		list.add("ROLE_USER");
		return list;
	}

	/**
	 * Populates country select with values
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List populateCountry() {
		return countryDao.getRecords();
	}
	
	/**
	 * Populates enabled select with values (true , false)
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List populateEnabled() {
		List<String> list = new ArrayList<String>();
		list.add("true");
		list.add("false");
		return list;
	}
	
	/**
	 * Populates position select with values
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List populatePosition() {
		return positionDao.getRecords();
	}
	
	/**
	 * Populates office select with values
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List populateOffice() {
		return officeDao.getRecords();
	}
	
	/**
	 * Populates project select with values
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List populateProject() {
		return projectDao.getRecords();
	}

	/**
	 * Creates in DB new user entry
	 * 
	 * @param form
	 * @return 1 if saved successfully
	 * 
	 */
	public int addNewUser(AddUserForm form) {
		User user = new User();
		user.mergeFieldsWith(form);
		user.setSalary(NOT_AVAILABLE);
		user.setPositionId(START_ID);
		user.setOfficeId(START_ID);
		user.setProjectId(START_ID);
		userDao.saveRecord(user);
		return 1;
	}

	/**
	 * Gets DB User entries by specified for criteria
	 * 
	 * @param form
	 * @return 
	 */
	@SuppressWarnings("unchecked")
	public List viewUsers(User form) {
		String[] fieldNames = { "name", "surname", "username", "email" };
		String[] values = { FunctionUtils.nullSafeGet(form.getName()) + "%",
				FunctionUtils.nullSafeGet(form.getSurname()) + "%",
				FunctionUtils.nullSafeGet(form.getUsername()) + "%", FunctionUtils.nullSafeGet(form.getEmail()) + "%" };
		return userDao.getRecords(fieldNames, values);
	}
	
	/**
	 * Updates user information
	 * @return 1 if saved successfully
	 */
	public int updateUser(AddUserForm form, User userDefault) {
		User user = new User();
		user.setId(userDefault.getId());
		user.mergeFieldsWith(form);
		user.setSalary(userDefault.getSalary());
		user.setPositionId(userDefault.getPositionId());
		user.setOfficeId(userDefault.getOfficeId());
		user.setProjectId(userDefault.getProjectId());
		userDao.saveRecord(user);
		return 1;
	}
	
	/**
	 * Updates user additional information
	 * @return 1 if saved successfully
	 */
	public int updateUserAdd(AddUserForm form, User userDefault) {
		User user = new User();
		user.setId(userDefault.getId());
		user.setEnabled(userDefault.getEnabled());
		user.setAuthority(userDefault.getAuthority());
		user.setName(userDefault.getName());
		user.setSurname(userDefault.getSurname());
		user.setUsername(userDefault.getUsername());
		user.setEmail(userDefault.getEmail());
		user.setPassword(userDefault.getPassword());
		user.setCountryId(userDefault.getCountryId());
		user.setCity(userDefault.getCity());
		user.setZip(userDefault.getZip());
		user.setAddress(userDefault.getAddress());
		user.setSalary(form.getSalary());
		user.setPositionId(form.getPositionId());
		user.setOfficeId(form.getOfficeId());
		user.setProjectId(form.getProjectId());
		userDao.saveRecord(user);
		return 1;
	}
	
	/**
	 * Creates in DB new user_skills entry
	 * 
	 * @param form
	 * @return 1 if saved successfully
	 * 
	 */
	public int addUserSkill(UserSkills form, User user) {
		//	Id to null, object always is being saved not modified
		form.setId(null);
		form.setUserId(user.getId());
		userSkillsDao.saveRecord(form);
		return 1;
	}
	
	/**
	 * Gets all particular user skills
	 * @param userId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List viewUserSkills(Long userId) {
		return userSkillsDao.getRecords("user_id", userId.toString());
	}
	
	/**
	 * Gets currently logged user entry from DB
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public User getCurrentUser() {
		// Getting current logged user username
		String currentUser = ((org.springframework.security.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal())
				.getUsername();
		// Getting current user Object
		List users = userDao.getRecords("username", currentUser);
		return (User)users.get(0);
	}

}
