package lv.kvd.lu.user;

import java.io.Serializable;
import java.util.Date;

import org.springframework.security.providers.encoding.ShaPasswordEncoder;

/**
 * Hibernate persistence class for table users, and Add user form bean
 * @author vitalik
 *
 */
public class User implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private Boolean enabled;
	private String authority;
	private String name;
	private String surname;
	private String username;
	private String email;
	private String password;
	private Long countryId;
	private String city;
	private String zip;
	private String address;
	private String salary;
	private Long positionId;
	private Long officeId;
	private Long projectId;	
	private Date timestamp;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Long getCountryId() {
		return countryId;
	}

	public void setCountryId(Long countryId) {
		this.countryId = countryId;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getSalary() {
		return salary;
	}

	public void setSalary(String salary) {
		this.salary = salary;
	}

	public Long getPositionId() {
		return positionId;
	}

	public void setPositionId(Long positionId) {
		this.positionId = positionId;
	}

	public Long getOfficeId() {
		return officeId;
	}

	public void setOfficeId(Long officeId) {
		this.officeId = officeId;
	}

	public Long getProjectId() {
		return projectId;
	}

	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	/**
	 * Merger AddUserForm object with User object
	 * @param form
	 */
	public void mergeFieldsWith(AddUserForm form) {
		this.enabled = form.getEnabled();
		this.name = form.getName();
		this.surname = form.getSurname();
		this.username = form.getUsername();
		this.email = form.getEmail();
		ShaPasswordEncoder encoder = new ShaPasswordEncoder(256);
		this.password = encoder.encodePassword(form.getPassword(), null);
		this.authority = form.getAuthority();
		this.address = form.getAddress();
		this.countryId = form.getCountryId();
		this.city = form.getCity();
		this.zip = form.getZip();
	}
	
}
