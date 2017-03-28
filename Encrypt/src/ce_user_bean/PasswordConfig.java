package ce_user_bean;

import java.util.ArrayList;
import java.util.List;

public class PasswordConfig {
	private int saltLength;
	private String saltLocation;
	private List<String> algorithmList;
	private String generate;
	private String resetUrlPrefix;
	private String resetExpire;
	private boolean autoLogin;
	private PasswordConfigEmail passwordConfigEmail;

	
	public PasswordConfig(){
		algorithmList = new ArrayList<String>();
	}

	public int getSaltLength() {
		return saltLength;
	}

	public void setSaltLength(int saltLength) {
		this.saltLength = saltLength;
	}

	public String getSaltLocation() {
		return saltLocation;
	}

	public void setSaltLocation(String saltLocation) {
		this.saltLocation = saltLocation;
	}

	public List<String> getAlgorithmList() {
		return algorithmList;
	}

	public void setAlgorithmList(List<String> algorithmList) {
		this.algorithmList = algorithmList;
	}

	public String getGenerate() {
		return generate;
	}

	public void setGenerate(String generate) {
		this.generate = generate;
	}

	public String getResetUrlPrefix() {
		return resetUrlPrefix;
	}

	public void setResetUrlPrefix(String resetUrlPrefix) {
		this.resetUrlPrefix = resetUrlPrefix;
	}

	public String getResetExpire() {
		return resetExpire;
	}

	public void setResetExpire(String resetExpire) {
		this.resetExpire = resetExpire;
	}

	public boolean isAutoLogin() {
		return autoLogin;
	}

	public void setAutoLogin(boolean autoLogin) {
		this.autoLogin = autoLogin;
	}
	
	public PasswordConfigEmail getPasswordConfigEmail() {
		return passwordConfigEmail;
	}

	public void setPasswordConfigEmail(PasswordConfigEmail passwordConfigEmail) {
		this.passwordConfigEmail = passwordConfigEmail;
	}
	
	public String getPasswordEmailSender(){
		return this.passwordConfigEmail.getEmailSender();
	}
	
	public String getPasswordEmailAddressName(){
		return this.passwordConfigEmail.getEmailAddressFiledName();
	}
	
	public List<String> getPasswordInitialList(){
		return this.passwordConfigEmail.getInitialList();
	}
	
}
