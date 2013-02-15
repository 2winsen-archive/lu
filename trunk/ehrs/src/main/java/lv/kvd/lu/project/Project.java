package lv.kvd.lu.project;

import java.io.Serializable;
import java.util.Date;


/**
 * Hibernate persistence class for table projects
 * @author vitalik
 *
 */
public class Project implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String name;
	private String managerName;
	private String managerSurname;
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
	public String getManagerName() {
		return managerName;
	}
	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}
	public String getManagerSurname() {
		return managerSurname;
	}
	public void setManagerSurname(String managerSurname) {
		this.managerSurname = managerSurname;
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
