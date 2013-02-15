package lv.kvd.lu.user;

import java.io.Serializable;
import java.util.Date;

import lv.kvd.lu.skill.Skill;

public class UserSkills implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	private Long groupId;
	
	private Long userId;
	private Skill skill = new Skill();
	private String experience;
	private Date timestamp;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Skill getSkill() {
		return skill;
	}
	public void setSkill(Skill skill) {
		this.skill = skill;
	}
	public String getExperience() {
		return experience;
	}
	public void setExperience(String experience) {
		this.experience = experience;
	}
	public Long getGroupId() {
		return groupId;
	}
	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}
	public Date getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
	
}
