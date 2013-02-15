package lv.kvd.lu.user;

import java.io.Serializable;

import lv.kvd.lu.skill.Skill;

/**
 * @author vitalik
 *
 */
public class SkillView extends Skill implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String experience;

	public String getExperience() {
		return experience;
	}

	public void setExperience(String experience) {
		this.experience = experience;
	}
	
}
