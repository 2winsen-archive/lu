package lv.kvd.lu.message;

import java.io.Serializable;
import java.util.Date;

import lv.kvd.lu.user.User;

/**
 * Hibernate persistence class for table messages
 * @author vitalik
 *
 */
public class Message implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private Boolean readFlag;
	private String title;
	private String entry;
	private String shortEntry;
	private User user = new User();
	private String senderUsername;
	private Date timestamp;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Boolean getReadFlag() {
		return readFlag;
	}
	public void setReadFlag(Boolean readFlag) {
		this.readFlag = readFlag;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getEntry() {
		return entry;
	}
	public void setEntry(String entry) {
		this.entry = entry;
	}
	public String getShortEntry() {
		return shortEntry;
	}
	public void setShortEntry(String shortEntry) {
		String entry = this.entry;
		if(entry.length() > 17) {
			this.shortEntry = entry.substring(0, 17) + "...";
		} else {
			this.shortEntry = entry.substring(0, entry.length());
		}
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getSenderUsername() {
		return senderUsername;
	}
	public void setSenderUsername(String senderUsername) {
		this.senderUsername = senderUsername;
	}
	public Date getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
	
}
