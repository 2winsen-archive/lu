package lv.kvd.lu.group;

import java.util.List;

import lv.kvd.lu.utils.FunctionUtils;


/**
 * Helper class contains methods for group funcionality
 * 
 * @author vitalik
 * 
 */
public class GroupHelper {
	
	private GroupDaoImpl groupDao;

	public GroupDaoImpl getGroupDao() {
		return groupDao;
	}

	public void setGroupDao(GroupDaoImpl groupDao) {
		this.groupDao = groupDao;
	}
	
	/**
	 * Creates in DB new group entry
	 * 
	 * @param form
	 * @return 1 if saved successfully
	 * 
	 */
	public int addNewGroup(Group group) {
		groupDao.saveRecord(group);
		return 1;
	}
	
	/**
	 * Gets DB Group entries by specified for criteria
	 * 
	 * @param form
	 * @return 
	 */
	@SuppressWarnings("unchecked")
	public List viewGroups(Group form) {
		String[] fieldNames = {"name"};
		String[] values = {FunctionUtils.nullSafeGet(form.getName()) + "%"};
		return groupDao.getRecords(fieldNames, values);
	}
	
	/**
	 * Updates group information
	 * @return 1 if saved successfully
	 */
	public int updateGroup(Group form, Group groupDefault) {
		Group group = new Group();
		group.setId(groupDefault.getId());
		group.setName(form.getName());
		group.setComments(form.getComments());
		groupDao.saveRecord(group);
		return 1;
	}

}
