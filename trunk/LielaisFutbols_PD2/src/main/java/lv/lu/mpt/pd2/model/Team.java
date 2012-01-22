package lv.lu.mpt.pd2.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

import lv.lu.mpt.pd2.constants.JPQLConst;
import lv.lu.mpt.pd2.interfaces.PersistentEntity;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@NamedQueries({
	@NamedQuery(name = JPQLConst.TeamJPQL.QUERY_GET_TEAM,
			query = "SELECT t FROM Team t WHERE name = :name"),
	@NamedQuery(name = JPQLConst.TeamJPQL.QUERY_GET_ALL_TEAM_NAMES,
			query = "SELECT t.name FROM Team t")
})
@Entity
public class Team implements PersistentEntity {

	@Id
	@GeneratedValue
	private Long id;

	private String name;

	@OneToMany(mappedBy = "team")
	@Cascade(CascadeType.ALL)
	private Set<Player> players;
	
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

	public Set<Player> getPlayers() {
		return players;
	}

	public void setPlayers(Set<Player> players) {
		this.players = players;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Team)
		{
			Team teamObj = (Team)obj;
			if (teamObj.name.equals(name))
			{
				return true;
			}
		}
		return false;
	}
}