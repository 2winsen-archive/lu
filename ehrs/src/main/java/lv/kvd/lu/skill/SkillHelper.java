package lv.kvd.lu.skill;

import java.util.List;

import lv.kvd.lu.group.GroupDaoImpl;
import lv.kvd.lu.utils.FunctionUtils;

/**
 * Helper class contains methods for skill funcionality
 * 
 * @author vitalik
 * 
 */
public class SkillHelper {
	
	private SkillDaoImpl skillDao;
	private GroupDaoImpl groupDao;

	public SkillDaoImpl getSkillDao() {
		return skillDao;
	}

	public void setSkillDao(SkillDaoImpl skillDao) {
		this.skillDao = skillDao;
	}
	
	public GroupDaoImpl getGroupDao() {
		return groupDao;
	}

	public void setGroupDao(GroupDaoImpl groupDao) {
		this.groupDao = groupDao;
	}

	/**
	 * Populates group select with values
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List populateGroup() {
		return groupDao.getRecords();
	}
	
	/**
	 * Creates in DB new skill entry
	 * 
	 * @param form
	 * @return 1 if saved successfully
	 * 
	 */
	public int addNewSkill(Skill skill) {
		skillDao.saveRecord(skill);
		return 1;	
	}
	
	/**
	 * Gets DB Skill entries by specified for criteria
	 * 
	 * @param form
	 * @return 
	 */
	@SuppressWarnings("unchecked")
	public List viewSkills(Skill form) {
		String[] fieldNames = {"name", "sgroup_id"};
		String[] values = {FunctionUtils.nullSafeGet(form.getName()) + "%", FunctionUtils.nullSafeGet(form.getGroupId()) + "%"};
		return skillDao.getRecords(fieldNames, values);
	}
	
	/**
	 * Updates skill information
	 * @return 1 if saved successfully
	 */
	public int updateSkill(Skill form, Skill groupDefault) {
		Skill skill = new Skill();
		skill.setId(groupDefault.getId());
		skill.setName(form.getName());
		skill.setGroupId(form.getGroupId());
		skill.setDifficulty(form.getDifficulty());
		skill.setComments(form.getComments());
		skillDao.saveRecord(skill);
		return 1;
	}
	
	/**
	 * Populates skills select depending on group id
	 */
	@SuppressWarnings("unchecked")
	public List populateSkills(Long groupId) {
		if(groupId == null ) {
			return skillDao.getRecords();
		} else {
			return skillDao.getRecords("sgroup_id", groupId.toString());
		}
	}

	
}
