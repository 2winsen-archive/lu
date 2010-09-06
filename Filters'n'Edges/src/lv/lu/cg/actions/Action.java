package lv.lu.cg.actions;

import java.util.Date;

/**
 * 
 * @author vitalik
 *
 */
public class Action {

	private Long time;
	private String action;
	
	public Action(String action) {
		this.time = System.currentTimeMillis();
		this.action = action;
	}

	public Long getTime() {
		return time;
	}

	public void setTime(Long time) {
		this.time = time;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	@Override
	public String toString() {
		return new Date(this.time)+ ": " + this.action;
	}

}
