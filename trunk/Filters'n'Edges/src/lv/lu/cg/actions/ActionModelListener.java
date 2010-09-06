package lv.lu.cg.actions;

/**
 * 
 * @author vitalik
 *
 */
public interface ActionModelListener {

	public void dataUpdated(ActionModelEvent event);
	
	public void dataCleared();
	
}
