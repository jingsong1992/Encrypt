package ce_user_bean;

import java.util.ArrayList;
import java.util.List;

public class PasswordConfigEmail {
	private String emailSender;
	private String emailAddressFiledName;
	private List<String> initialList;
	
	public PasswordConfigEmail(){
		initialList = new ArrayList<String>();
	}

	public String getEmailSender() {
		return emailSender;
	}

	public void setEmailSender(String emailSender) {
		this.emailSender = emailSender;
	}

	public String getEmailAddressFiledName() {
		return emailAddressFiledName;
	}

	public void setEmailAddressFiledName(String emailAddressFiledName) {
		this.emailAddressFiledName = emailAddressFiledName;
	}

	public List<String> getInitialList() {
		return initialList;
	}

	public void setInitialList(List<String> initialList) {
		this.initialList = initialList;
	}
	
	
}
