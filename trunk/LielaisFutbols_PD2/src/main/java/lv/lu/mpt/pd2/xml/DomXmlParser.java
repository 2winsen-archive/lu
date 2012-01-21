package lv.lu.mpt.pd2.xml;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import lv.lu.mpt.pd2.interfaces.XmlParser;
import lv.lu.mpt.pd2.interfaces.service.GameService;
import lv.lu.mpt.pd2.interfaces.service.PlayerService;
import lv.lu.mpt.pd2.interfaces.service.RefereeService;
import lv.lu.mpt.pd2.interfaces.service.TeamService;
import lv.lu.mpt.pd2.model.Change;
import lv.lu.mpt.pd2.model.Game;
import lv.lu.mpt.pd2.model.Goal;
import lv.lu.mpt.pd2.model.Penalty;
import lv.lu.mpt.pd2.model.Player;
import lv.lu.mpt.pd2.model.Referee;
import lv.lu.mpt.pd2.model.Team;
import lv.lu.mpt.pd2.model.enums.GoalTypeEnum;
import lv.lu.mpt.pd2.model.enums.RoleEnum;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class DomXmlParser implements XmlParser {
	
	private Game game;
	
	private Set<Goal> team1Goals;
	
	private Set<Goal> team2Goals;
	
	private boolean isExtraTime = false;
	
	private PlayerService playerService;
	
	private RefereeService refereeService;
	
	private TeamService teamService;
	
	private GameService gameService;
	
	public PlayerService getPlayerService() {
		return playerService;
	}

	public void setPlayerService(PlayerService playerService) {
		this.playerService = playerService;
	}

	public RefereeService getRefereeService() {
		return refereeService;
	}

	public void setRefereeService(RefereeService refereeService) {
		this.refereeService = refereeService;
	}

	public TeamService getTeamService() {
		return teamService;
	}

	public void setTeamService(TeamService teamService) {
		this.teamService = teamService;
	}

	public GameService getGameService() {
		return gameService;
	}

	public void setGameService(GameService gameService) {
		this.gameService = gameService;
	}

	@Override
	public Game parse(InputStream xml) {
		try {
			Document doc = getDocumentBuilder().parse(xml);
			extractGame(doc);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this.game;
	}
	
	private DocumentBuilder getDocumentBuilder() {
	try {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = factory.newDocumentBuilder();

		// define error handler class to report XML processing errors
		db.setErrorHandler(new XmlErrorHandler());
		return db;
	} catch (ParserConfigurationException e) {
		throw new RuntimeException(e);
	}
}
	
	private void extractGame(Document doc) {
		
		// Spele;
		game = new Game();
		Element root = doc.getDocumentElement();
		
		// laiks; vieta; skatitaaji
		NamedNodeMap attribs = root.getAttributes();
		for (int j = 0; j < attribs.getLength(); j++) {

			Node attrib = attribs.item(j);

			if (XmlConsts.Game.DATE.equals(attrib.getNodeName())) {
				Date date = null;
				try {
					date = new SimpleDateFormat(XmlConsts.Game.DATE_FORMAT).parse(attrib.getNodeValue().trim());
				} catch (Exception e) {
					e.printStackTrace();
				}
				game.setDate(date);
				continue;
			}
			if (XmlConsts.Game.PLACE.equals(attrib.getNodeName())) {
				game.setPlace(attrib.getNodeValue().trim());
				continue;
			}
			if (XmlConsts.Game.AUDIENCE.equals(attrib.getNodeName())) {
				game.setAudience(Integer.valueOf(attrib.getNodeValue().trim()));
				continue;
			}
		}
		
		Game tempGame = gameService.getGame(game);
		// If such game already exists set game to null and return
		if (tempGame != null) {
			game = null;
			return;
		}
		
		// VT; T; T; Komanda; Komanda
		NodeList nodes = root.getChildNodes();
		for (int i = 0; i < nodes.getLength(); i++) {

			Node child = nodes.item(i);
			
			// VT
			if (XmlConsts.Blocks.SENIOR_REFEREE.equals(child.getNodeName())) {
				game.setSeniorReferee(extractReferee(child));
				continue;
			}
			
			// T; T
			if (XmlConsts.Blocks.REFEREE.equals(child.getNodeName())) {
				Referee referee = extractReferee(child);
				if (game.getReferee1() == null) {
					game.setReferee1(referee);
				} else {
					game.setReferee2(referee);
				}
				continue;
			}
			
			// Komanda; Komanda
			if (XmlConsts.Blocks.TEAM.equals(child.getNodeName())) {
				Team team = null;
				if (game.getTeam1() == null) {
					team = extractTeam(child, true);
					game.setTeam1(team);
				} else {
					team = extractTeam(child, false);
					game.setTeam2(team);
				}
				continue;
			}
		}
		
		game.setExtraTime(isExtraTime);
		if (!isExtraTime) {
			if (team1Goals.size() > team2Goals.size()) {
				game.setTeam1Points(3);
				game.setTeam2Points(0);
			} else {
				game.setTeam1Points(0);
				game.setTeam2Points(3);				
			}
		}
		else
		{
			if (team1Goals.size() > team2Goals.size()) {
				game.setTeam1Points(2);
				game.setTeam2Points(1);
			} else {
				game.setTeam1Points(1);
				game.setTeam2Points(2);				
			}
		}
	}
	
	private Referee extractReferee(Node node) {
		Referee referee = new Referee();
		NamedNodeMap nodes = node.getAttributes();
		for (int i = 0; i < nodes.getLength(); i++) {
			Node child = nodes.item(i);
			if (XmlConsts.Referee.ATTR_FIRST_NAME.equals(child.getNodeName())) {
				referee.setFirstName(child.getNodeValue().trim());
				continue;
			}
			if (XmlConsts.Referee.ATTR_LAST_NAME.equals(child.getNodeName())) {
				referee.setLastName(child.getNodeValue().trim());
				continue;
			}
		}
		Referee tempReferee = refereeService.getReferee(referee);
		if (tempReferee != null) {
			return tempReferee;
		}
		return referee;
	}

	private Team extractTeam(Node node, Boolean team1) {

		Team team = new Team();

		// Nosaukums
		NamedNodeMap attribs = node.getAttributes();
		for (int i = 0; i < attribs.getLength(); i++) {

			Node attrib = attribs.item(i);

			if (XmlConsts.Team.ATTR_NAME.equals(attrib.getNodeName())) {
				team.setName(attrib.getNodeValue().trim());
				continue;
			}
		}
		Team tempTeam = teamService.getTeam(team);
		if (tempTeam != null) {
			team = tempTeam;
		}
		
		// Speletaji; Pamatsastavs; Varti; Sodi; Mainas	
		NodeList nodes = node.getChildNodes();
		for (int i = 0; i < nodes.getLength(); i++) {
			
			Node child = nodes.item(i);
			
			// Speletaji
			if (XmlConsts.Team.PLAYERS.equals(child.getNodeName())) {
				team.setPlayers(extractPlayers(child, team));
			}
			
			// Pamatsastavs
			if (XmlConsts.Team.LINE_UP.equals(child.getNodeName())) {
				if (team1) {
					game.setTeam1LineUp(extractLineUps(child, team));
				} else {
					game.setTeam2LineUp(extractLineUps(child, team));
				}
			}
			
			// Varti
			if (XmlConsts.Team.GOALS.equals(child.getNodeName())) {
				Set<Goal> goals = new HashSet<Goal>();
				if (game.getGoals() != null) {
					goals.addAll(game.getGoals());
				}
				goals.addAll(extractGoals(child, team, team1));
				game.setGoals(goals);
				continue;
			}
			
			// Sodi
			if (XmlConsts.Team.PENALTIES.equals(child.getNodeName())) {
				Set<Penalty> penalties = new HashSet<Penalty>();
				if (game.getPenalties() != null) {
					penalties.addAll(game.getPenalties());
				}
				penalties.addAll(extractPenalties(child, team));
				game.setPenalties(penalties);
				continue;
			}
			
			// Mainas
			if (XmlConsts.Team.CHANGES.equals(child.getNodeName())) {
				Set<Change> changes = new HashSet<Change>();
				if (game.getChanges() != null) {
					changes.addAll(game.getChanges());
				}
				changes.addAll(extractChanges(child, team));
				game.setChanges(changes);
				continue;
			}
		}
		return team;
	}
	
	private Set<Player> extractPlayers(Node node, Team team) {

		Set<Player> players = new HashSet<Player>();

		// Speltajs
		NodeList nodes = node.getChildNodes();
		for (int i = 0; i < nodes.getLength(); i++) {
			Node child = nodes.item(i);
			if (!XmlConsts.Player.PLAYER.equals(child.getNodeName())) {
				continue;
			}

			Player player = new Player();
			NamedNodeMap attribs = child.getAttributes();
			for (int j = 0; j < attribs.getLength(); j++) {

				Node attrib = attribs.item(j);

				// Nr
				if (XmlConsts.Player.ATTR_NUMBER.equals(attrib.getNodeName())) {
					player.setNumber(Integer.parseInt(attrib.getNodeValue().trim()));
					continue;
				}
				
				// Vards
				if (XmlConsts.Player.ATTR_FIRST_NAME.equals(attrib.getNodeName())) {
					player.setFirstName(attrib.getNodeValue().trim());
					continue;
				}
				
				// Uzvards
				if (XmlConsts.Player.ATTR_LAST_NAME.equals(attrib.getNodeName())) {
					player.setLastName(attrib.getNodeValue().trim());
					continue;
				}
				
				// Loma
				if (XmlConsts.Player.ATTR_ROLE.equals(attrib.getNodeName())) {
					player.setRole(getPlayerRole(attrib.getNodeValue()));
					continue;
				}
			}
			player.setTeam(team);
			Player tempPlayer = playerService.getPlayer(player);
			if (tempPlayer != null) {
				player = tempPlayer;
			}
			players.add(player);
		}
		return players;
	}	
	
	private Set<Player> extractLineUps(Node node, Team team) {
		Set<Player> lineUp = new HashSet<Player>();
		NodeList nodes = node.getChildNodes();
		
		// Speletajs
		for (int i = 0; i < nodes.getLength(); i++) {
			Node child = nodes.item(i);
			if (!XmlConsts.Player.PLAYER.equals(child.getNodeName())) {
				continue;
			}
			NamedNodeMap attribs = child.getAttributes();
			for (int j = 0; j < attribs.getLength(); j++) {
				Node attrib = attribs.item(j);
				
				// Nr
				if (XmlConsts.Player.ATTR_NUMBER.equals(attrib.getNodeName())) {
					lineUp.add(getPlayerByNumber(Integer.parseInt(attrib.getNodeValue().trim()), team.getPlayers()));
				}
			}
		}
		return lineUp;
	}	

	private Set<Goal> extractGoals(Node node, Team team, Boolean team1) {

		Set<Goal> goals = new HashSet<Goal>();

		NodeList nodes = node.getChildNodes();
		for (int i = 0; i < nodes.getLength(); i++) {

			Node child = nodes.item(i);

			if (!XmlConsts.Goal.SCORER.equals(child.getNodeName())) {
				continue;
			}
			
			Goal goal = new Goal();
			NamedNodeMap attribs = child.getAttributes();
			for (int j = 0; j < attribs.getLength(); j++) {

				Node attrib = attribs.item(j);

				// Laiks
				if (XmlConsts.Goal.ATTR_TIME.equals(attrib.getNodeName())) {
					Integer[] time = extractTime(attrib.getNodeValue().trim());
					goal.setMinutes(time[0]);
					goal.setSeconds(time[1]);
					continue;
				}

				// Nr
				if (XmlConsts.Goal.ATTR_SCORER_NUMBER.equals(attrib.getNodeName())) {
					goal.setPlayer(getPlayerByNumber(Integer.parseInt(attrib.getNodeValue().trim()), team.getPlayers()));
					continue;
				}

				// Sitiens
				if (XmlConsts.Goal.ATTR_GOAL_TYPE.equals(attrib.getNodeName())) {
					if (attrib.getNodeValue().equals(XmlConsts.Goal.TYPE_N)) {
						goal.setGoalType(GoalTypeEnum.REGULAR_GOAL);
					} else {
						goal.setGoalType(GoalTypeEnum.PENALTY_GOAL);
					}
					continue;
				}
			}

			// assistants
			if (goal != null) {
				Set<Player> assistants = new HashSet<Player>();
				NodeList children = child.getChildNodes();
				for (int j = 0; j < children.getLength(); j++) {

					Node assistantChild = children.item(j);
					if (!XmlConsts.Goal.ASSISTANT.equals(assistantChild.getNodeName())) {
						continue;
					}

					NamedNodeMap assistantAttribs = assistantChild.getAttributes();
					for (int k = 0; k < assistantAttribs.getLength(); k++) {

						Node assistantAttrib = assistantAttribs.item(k);

						if (XmlConsts.Goal.ATTR_SCORER_NUMBER.equals(assistantAttrib.getNodeName())) {

							assistants.add(getPlayerByNumber(Integer.parseInt(assistantAttrib.getNodeValue()), team.getPlayers()));
							continue;
						}
					}
				}
				goal.setAssistants(assistants);
			}

			// add goal to result list
			if (goal != null) {
				goal.setGame(game);
				goals.add(goal);
			}
		}

		if (team1) {
			team1Goals = goals;
		} else {
			team2Goals = goals;
		}
		
		return goals;
	}

	private Set<Penalty> extractPenalties(Node node, Team team) {

		Set<Penalty> penalties = new HashSet<Penalty>();

		NodeList nodes = node.getChildNodes();
		for (int i = 0; i < nodes.getLength(); i++) {
			Node child = nodes.item(i);

			if (!XmlConsts.Penalty.PENALTY.equals(child.getNodeName())) {
				continue;
			}

			Penalty penalty = new Penalty();
			NamedNodeMap attribs = child.getAttributes();

			for (int j = 0; j < attribs.getLength(); j++) {

				Node attrib = attribs.item(j);

				// Laiks
				if (XmlConsts.Penalty.ATTR_TIME.equals(attrib.getNodeName())) {
					Integer[] time = extractTime(attrib.getNodeValue().trim());
					penalty.setMinutes(time[0]);
					penalty.setSeconds(time[1]);
					continue;
				}
				
				// Nr
				if (XmlConsts.Penalty.ATTR_NUMBER.equals(attrib.getNodeName())) {
					penalty.setPlayer(getPlayerByNumber(Integer.parseInt(attrib.getNodeValue()), team.getPlayers()));
					continue;
				}
			}
			penalty.setGame(game);
			penalties.add(penalty);
		}
		return penalties;
	}
	
	
	private Set<Change> extractChanges(Node node, Team team) {

		Set<Change> changies = new HashSet<Change>();

		NodeList nodes = node.getChildNodes();
		for (int i = 0; i < nodes.getLength(); i++) {

			Node child = nodes.item(i);
			if (!XmlConsts.Change.CHANGE.equals(child.getNodeName())) {
				continue;
			}

			Change change = new Change();
			NamedNodeMap attribs = child.getAttributes();

			for (int j = 0; j < attribs.getLength(); j++) {

				Node attrib = attribs.item(j);

				// Laiks
				if (XmlConsts.Change.ATTR_TIME.equals(attrib.getNodeName())) {
					Integer[] time = extractTime(attrib.getNodeValue().trim());
					change.setMinutes(time[0]);
					change.setSeconds(time[1]);
					continue;
				}
				
				// Nr1
				if (XmlConsts.Change.ATTR_NUMBER_FROM.equals(attrib.getNodeName())) {
					change.setPlayerFrom(getPlayerByNumber(Integer.parseInt(attrib.getNodeValue()), team.getPlayers()));
					continue;
				}
				
				// Nr2
				if (XmlConsts.Change.ATTR_NUMBER_TO.equals(attrib.getNodeName())) {
					change.setPlayerTo(getPlayerByNumber(Integer.parseInt(attrib.getNodeValue()), team.getPlayers()));
					continue;
				}
			}
			change.setGame(game);
			changies.add(change);
		}
		return changies;
	}
	
	private RoleEnum getPlayerRole(String role) {
		if (XmlConsts.Player.ROLE_FORWARD.equals(role)) {
			return RoleEnum.FORWARD;
		} else if (XmlConsts.Player.ROLE_GOALKEEPER.equals(role)) {
			return RoleEnum.GOALKEEPER;
		}
		return RoleEnum.DEFENDER;
	}
	
	private Player getPlayerByNumber(Integer number, Set<Player> players) {
		if (number == null) {
			return null;
		}
		for (Player p : players) {
			if (number.equals(p.getNumber())) {
				return p;
			}
		}
		return null;
	}
	
	private Integer[] extractTime(String time) {
		String tokens[] = time.split(XmlConsts.Penalty.TIME_DELIM);
		Integer minutes = Integer.parseInt(tokens[0]);
		Integer seconds = Integer.parseInt(tokens[1]);
		if (minutes > 90 || 
				(minutes == 90 && seconds > 0)) {
			isExtraTime = true;
		}		
		return new Integer[]{minutes, seconds};
	}
	
}