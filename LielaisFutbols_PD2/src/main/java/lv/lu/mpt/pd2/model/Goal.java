package lv.lu.mpt.pd2.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import lv.lu.mpt.pd2.interfaces.PersistentEntity;
import lv.lu.mpt.pd2.model.enums.GoalTypeEnum;

@Entity
public class Goal implements PersistentEntity {

	@Id
	@GeneratedValue
	private Long id;

	private Integer minutes;
	
	private Integer seconds;

	private GoalTypeEnum goalType;

	@ManyToOne
	@JoinColumn(name="game_id")
	private Game game;

	@OneToOne
	private Player player;
	
	@OneToOne
	private Player goalkeeperLost;
	
	@OneToOne
	private Team teamScored;
	
	@OneToOne
	private Team teamLost;

	@ManyToMany
	@JoinTable(
		name = "Goal_Assistant",
		joinColumns = {
			@JoinColumn(name="goal_id") 
		}, 
		inverseJoinColumns = {
			@JoinColumn(name="player_id")
		}
	)
	private Set<Player> assistants;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getMinutes() {
		return minutes;
	}

	public void setMinutes(Integer minutes) {
		this.minutes = minutes;
	}

	public Integer getSeconds() {
		return seconds;
	}

	public void setSeconds(Integer seconds) {
		this.seconds = seconds;
	}

	public GoalTypeEnum getGoalType() {
		return goalType;
	}

	public void setGoalType(GoalTypeEnum goalType) {
		this.goalType = goalType;
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public Player getGoalkeeperLost() {
		return goalkeeperLost;
	}

	public void setGoalkeeperLost(Player goalkeeperLost) {
		this.goalkeeperLost = goalkeeperLost;
	}

	public Team getTeamScored() {
		return teamScored;
	}

	public void setTeamScored(Team teamScored) {
		this.teamScored = teamScored;
	}

	public Team getTeamLost() {
		return teamLost;
	}

	public void setTeamLost(Team teamLost) {
		this.teamLost = teamLost;
	}

	public Set<Player> getAssistants() {
		return assistants;
	}

	public void setAssistants(Set<Player> assistants) {
		this.assistants = assistants;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Goal)
		{
			Goal goalObj = (Goal)obj;
			if (goalObj.getMinutes() == minutes &&
					goalObj.getSeconds() == seconds &&
					goalObj.getGoalType().equals(goalType) &&
					goalObj.getPlayer().equals(player))
			{
				return true;
			}
		}
		return false;
	}

}
