package lv.lu.mpt.pd2.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import lv.lu.mpt.pd2.interfaces.PersistentEntity;

@Entity(name="changePlayers")
public class Change implements PersistentEntity {

	@Id
	@GeneratedValue
	private Long id;
	
	@OneToOne
	private Player playerFrom;
	
	@OneToOne
	private Player playerTo;

	@ManyToOne
	@JoinColumn(name="game_id")
	private Game game;
	
	private Integer minutes;
	
	private Integer seconds;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Player getPlayerFrom() {
		return playerFrom;
	}

	public void setPlayerFrom(Player playerFrom) {
		this.playerFrom = playerFrom;
	}

	public Player getPlayerTo() {
		return playerTo;
	}

	public void setPlayerTo(Player playerTo) {
		this.playerTo = playerTo;
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
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
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Change)
		{
			Change changeObj = (Change)obj;
			if (changeObj.minutes == minutes &&
					changeObj.seconds == seconds &&
					changeObj.playerFrom.equals(playerFrom) &&
					changeObj.playerTo.equals(playerTo))
			{
				return true;
			}
		}
		return false;
	}

}
