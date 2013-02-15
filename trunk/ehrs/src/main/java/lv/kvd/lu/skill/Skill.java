package lv.kvd.lu.skill;

import java.io.Serializable;
import java.util.Date;

/**
 * Hibernate persistence class for table skills, also as java bean add skill
 * @author vitalik
 *
 */
public class Skill implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String name;
	private Long groupId;
	private String difficulty;
	private String comments;
	private Date timestamp;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getGroupId() {
		return groupId;
	}
	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}
	public String getDifficulty() {
		return difficulty;
	}
	public void setDifficulty(String difficulty) {
		this.difficulty = difficulty;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public Date getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
	
}
