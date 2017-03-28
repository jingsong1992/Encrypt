package ce_user_encrypt;

import java.util.List;

import ce_user_bean.PasswordConfig;

public class PasswordHelper {
	
	/*
	 * if password_config.xml no have encryptType, get encryptPassword by base64
	 * */
	public static String encryptPasswordBySha256AndMD5(String password,PasswordConfig config){
		if(config != null){
			if( config.getAlgorithmList().size()!=0){
				List<String> list = config.getAlgorithmList();
				for(int i=0;i<list.size();i++){
					try {
						EncryptPolicy encryptPolicy = (EncryptPolicy)Class.forName(list.get(i)).newInstance();
						password = encryptPolicy.encrypt(password);
					} catch (Exception e) {
						e.printStackTrace();
					} 
				}
			}else{
				password = Base64Encrypt.encryptBase64(password); 
			}
		}
		return password;
	}
}
