package lv.kvd.lu.user;

import java.io.Serializable;

public class AddUserForm extends User implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String retypePassword;

	public String getRetypePassword() {
		return retypePassword;
	}

	public void setRetypePassword(String retypePassword) {
		this.retypePassword = retypePassword;
	}

}
