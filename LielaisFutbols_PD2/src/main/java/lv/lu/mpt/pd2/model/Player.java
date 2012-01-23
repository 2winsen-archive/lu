package lv.lu.mpt.pd2.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Transient;

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
	
	private Integer gamesPlayed = 0;
	
	private Integer gamesPlayedInMainLineUp = 0;
	
	private Integer minutesPlayed = 0;
	
	private Integer yellowCardsCount = 0;
	
	private Integer redCardsCount = 0;
	
	private Integer goalsCount = 0;
	
	private Integer goalsLostCount = 0;
	
	private Integer assistsCount = 0;
	
	@Transient
	public int startedToPlay = 0;
	@Transient
	public boolean changed = false;
	@Transient
	public int yellowCardsInCurrentGame = 0;
	
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

	public Integer getGamesPlayedInMainLineUp() {
		return gamesPlayedInMainLineUp;
	}

	public void setGamesPlayedInMainLineUp(Integer gamesPlayedInMainLineUp) {
		this.gamesPlayedInMainLineUp = gamesPlayedInMainLineUp;
	}

	public Integer getMinutesPlayed() {
		return minutesPlayed;
	}

	public void setMinutesPlayed(Integer minutesPlayed) {
		this.minutesPlayed = minutesPlayed;
	}

	public Integer getYellowCardsCount() {
		return yellowCardsCount;
	}

	public void setYellowCardsCount(Integer yellowCardsCount) {
		this.yellowCardsCount = yellowCardsCount;
	}

	public Integer getRedCardsCount() {
		return redCardsCount;
	}

	public void setRedCardsCount(Integer redCardsCount) {
		this.redCardsCount = redCardsCount;
	}

	public Integer getGoalsCount() {
		return goalsCount;
	}

	public void setGoalsCount(Integer goalsCount) {
		this.goalsCount = goalsCount;
	}

	public Integer getGoalsLostCount() {
		return goalsLostCount;
	}

	public void setGoalsLostCount(Integer goalsLostCount) {
		this.goalsLostCount = goalsLostCount;
	}

	public Integer getAssistsCount() {
		return assistsCount;
	}

	public void setAssistsCount(Integer assistsCount) {
		this.assistsCount = assistsCount;
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
