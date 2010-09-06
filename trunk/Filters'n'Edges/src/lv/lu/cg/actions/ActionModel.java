package lv.lu.cg.actions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author vitalik
 * 
 */
public class ActionModel {

	private static Map<String, ActionModelListener> listeners = new HashMap<String, ActionModelListener>();

	// .................................
	// .........Model data..............
	// .................................
	private List<Action> actions;

	// .................................
	// .........Singleton data..........
	// .................................
	private static ActionModel instance;

	public static ActionModel getInstance() {
		if (instance == null) {
			instance = new ActionModel();
		}
		return instance;
	}

	private ActionModel() {
		actions = new ArrayList<Action>();
	}

	public void addAction(String action) {
		Action a = new Action(action);
		actions.add(a);
		dispatchUpdateEvent(new ActionModelEvent(a));
	}

	public void clearActions() {
		actions.clear();
		dispatchClearEvent();
	}

	public List<Action> getActions() {
		return actions;
	}

	public static void addActionModelListener(String key, ActionModelListener listener) {
		listeners.put(key, listener);
	}
	
	public static void removeActionModelListener(String key) {
		listeners.remove(key);
	}

	private void dispatchUpdateEvent(ActionModelEvent event) {
		for (ActionModelListener listener : listeners.values()) {
			listener.dataUpdated(event);
		}
	}
	
	private void dispatchClearEvent() {
		for (ActionModelListener listener : listeners.values()) {
			listener.dataCleared();
		}
	}

}
