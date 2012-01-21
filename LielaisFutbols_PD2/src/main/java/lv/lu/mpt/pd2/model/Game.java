package lv.lu.mpt.pd2.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import lv.lu.mpt.pd2.constants.JPQLConst;
import lv.lu.mpt.pd2.interfaces.PersistentEntity;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@NamedQueries({
	@NamedQuery(name = JPQLConst.GameJPQL.QUERY_GET_GAME,
			query = "SELECT g FROM Game g WHERE g.date = :date AND g.place = :place AND g.audience = :audience")
})
@Entity
public class Game implements PersistentEntity {

	@Id
	@GeneratedValue
	private Long id;
	
	@OneToOne
	@Cascade(CascadeType.ALL)
	private Team team1;

	@OneToOne
	@Cascade(CascadeType.ALL)
	private Team team2;
	
	private Date date;

	private String place;

	private Integer audience;
	
	@Column(columnDefinition = "tinyint")
	private Boolean extraTime;
	
	private Integer team1Points;
	
	private Integer team2Points;
	
	@OneToOne
	@Cascade(CascadeType.ALL)
	private Referee seniorReferee;
	
	@OneToOne
	@Cascade(CascadeType.ALL)
	private Referee referee1;
	
	@OneToOne
	@Cascade(CascadeType.ALL)
	private Referee referee2;

	@ManyToMany
	@JoinTable(
		name = "Game_Team1LineUp", 
		joinColumns = {
			@JoinColumn(name="game_id") 
		}, 
		inverseJoinColumns = {
			@JoinColumn(name="player_id")
		}
	)
	private Set<Player> team1LineUp;
	
	@ManyToMany
	@JoinTable(
		name = "Game_Team2LineUp", 
		joinColumns = {
			@JoinColumn(name="game_id") 
		}, 
		inverseJoinColumns = {
			@JoinColumn(name="player_id")
		}
	)
	private Set<Player> team2LineUp;
	
	@OneToMany(mappedBy="game")
	@Cascade(CascadeType.ALL)
	private Set<Goal> goals;
	
	@OneToMany(mappedBy="game")
	@Cascade(CascadeType.ALL)
	private Set<Penalty> penalties;
	
	@OneToMany(mappedBy="game")
	@Cascade(CascadeType.ALL)
	private Set<Change> changes;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Team getTeam1() {
		return team1;
	}

	public void setTeam1(Team team1) {
		this.team1 = team1;
	}

	public Team getTeam2() {
		return team2;
	}

	public void setTeam2(Team team2) {
		this.team2 = team2;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public Integer getAudience() {
		return audience;
	}

	public void setAudience(Integer audience) {
		this.audience = audience;
	}

	public Boolean getExtraTime() {
		return extraTime;
	}

	public void setExtraTime(Boolean extraTime) {
		this.extraTime = extraTime;
	}

	public Integer getTeam1Points() {
		return team1Points;
	}

	public void setTeam1Points(Integer team1Points) {
		this.team1Points = team1Points;
	}

	public Integer getTeam2Points() {
		return team2Points;
	}

	public void setTeam2Points(Integer team2Points) {
		this.team2Points = team2Points;
	}

	public Referee getSeniorReferee() {
		return seniorReferee;
	}

	public void setSeniorReferee(Referee seniorReferee) {
		this.seniorReferee = seniorReferee;
	}

	public Referee getReferee1() {
		return referee1;
	}

	public void setReferee1(Referee referee1) {
		this.referee1 = referee1;
	}

	public Referee getReferee2() {
		return referee2;
	}

	public void setReferee2(Referee referee2) {
		this.referee2 = referee2;
	}

	public Set<Player> getTeam1LineUp() {
		return team1LineUp;
	}

	public void setTeam1LineUp(Set<Player> team1LineUp) {
		this.team1LineUp = team1LineUp;
	}

	public Set<Player> getTeam2LineUp() {
		return team2LineUp;
	}

	public void setTeam2LineUp(Set<Player> team2LineUp) {
		this.team2LineUp = team2LineUp;
	}

	public Set<Goal> getGoals() {
		return goals;
	}

	public void setGoals(Set<Goal> goals) {
		this.goals = goals;
	}

	public Set<Penalty> getPenalties() {
		return penalties;
	}

	public void setPenalties(Set<Penalty> penalties) {
		this.penalties = penalties;
	}

	public Set<Change> getChanges() {
		return changes;
	}

	public void setChanges(Set<Change> changes) {
		this.changes = changes;
	}
	
}
