package lv.lu.mpt.pd2.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import lv.lu.mpt.pd2.interfaces.PersistentEntity;

@Entity
public class Penalty implements PersistentEntity {

	@Id
	@GeneratedValue
	private Long id;
	
	private Integer minutes;
	
	private Integer seconds;

	@ManyToOne
	@JoinColumn(name="game_id")
	private Game game;

	@OneToOne
	private Player player;

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

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Penalty)
		{
			Penalty penaltyObj = (Penalty)obj;
			if (penaltyObj.minutes == minutes &&
					penaltyObj.seconds == seconds &&
					penaltyObj.player.equals(player))
			{
				return true;
			}
		}
		return false;
	}

}
