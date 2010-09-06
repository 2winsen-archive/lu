package lv.lu.cg.actions;

/**
 * 
 * @author vitalik
 *
 */
public class ActionModelEvent {
	
	private Action action;
	
	public ActionModelEvent(Action action) {
		this.action = action;
	}
	
	public Action getAction() {
		return action;
	}

}
