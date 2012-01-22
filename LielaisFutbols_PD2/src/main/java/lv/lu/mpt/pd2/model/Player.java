package lv.lu.mpt.pd2.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import lv.lu.mpt.pd2.constants.JPQLConst;
import lv.lu.mpt.pd2.interfaces.PersistentEntity;
import lv.lu.mpt.pd2.model.enums.RoleEnum;

@NamedQueries({
	@NamedQuery(name = JPQLConst.PlayerJPQL.QUERY_GET_PLAYER,
			query = "SELECT p FROM Player p WHERE p.number = :number AND p.team.id = :teamId")
})
@Entity
public class Player implements PersistentEntity {
	
	@Id
	@GeneratedValue
	private Long id;

	private Integer number;
	
	private String firstName;
	
	private String lastName;

	private RoleEnum role;

	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name = "team_id")
	private Team team;
	
	private Integer gamesPlayed;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}
              
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public RoleEnum getRole() {
		return role;                  
	}

	public void setRole(RoleEnum role) {
		this.role = role;
	}
	
	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}

	public Integer getGamesPlayed() {
		return gamesPlayed;
	}

	public void setGamesPlayed(Integer gamesPlayed) {
		this.gamesPlayed = gamesPlayed;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Player) {
			Player playerObj = (Player)obj;
			if (playerObj.number == number &&
					playerObj.firstName.equals(firstName) &&
					playerObj.lastName.equals(lastName) &&
					playerObj.role.equals(role)) {
				return true;
			}
		}
		return false;  
	}

}
