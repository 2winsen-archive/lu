package lv.lu.mpt.pd2.xml;

/**
 * Constants related to XML files
 */
public final class XmlConsts {

	public interface Blocks {
		String SENIOR_REFEREE = "VT";
		String REFEREE = "T";
		String TEAM = "Komanda";
	}

	public interface Game {
		String PLACE = "Vieta";
		String AUDIENCE = "Skatitaji";
		String DATE = "Laiks";
		String DATE_FORMAT = "yy/M/dd";
	}

	public interface Team {
		String ATTR_NAME = "Nosaukums";
		String PLAYERS = "Speletaji";
		String GOALS = "Varti";
		String PENALTIES = "Sodi";
		String CHANGES = "Mainas";
		String LINE_UP = "Pamatsastavs";
	}

	public interface Player {
		String ATTR_NUMBER = "Nr";
		String ATTR_FIRST_NAME = "Vards";
		String ATTR_LAST_NAME = "Uzvards";
		String ATTR_ROLE = "Loma";
		String ROLE_FORWARD = "U";
		String ROLE_DEFENDER = "A";
		String ROLE_GOALKEEPER = "V";
		String PLAYER = "Speletajs";
	}

	public interface Goal {
		String SCORER = "VG";
		String ASSISTANT = "P";
		String ATTR_TIME = "Laiks";
		String TIME_DELIM = ":";
		String ATTR_SCORER_NUMBER = "Nr";
		String ATTR_GOAL_TYPE = "Sitiens";
		String TYPE_J = "J";
		String TYPE_N = "N";
	}

	public interface Penalty {
		String PENALTY = "Sods";
		String ATTR_TIME = "Laiks";
		String TIME_DELIM = ":";
		String ATTR_NUMBER = "Nr";
	}

	public interface Change {
		String CHANGE = "Maina";
		String ATTR_TIME = "Laiks";
		String TIME_DELIM = ":";
		String ATTR_NUMBER_FROM = "Nr1";
		String ATTR_NUMBER_TO = "Nr2";
	}
	
	public interface Referee {
		String ATTR_FIRST_NAME = "Vards";
		String ATTR_LAST_NAME = "Uzvards";
	}

}
