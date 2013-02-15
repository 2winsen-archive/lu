package lv.kvd.lu.country;

/**
 * Hibernate persistence class for table countries
 * @author vitalik
 *
 */
public class Country {
	
	private Long id;
	private String name;
	private String comments;
	
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
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	
}
