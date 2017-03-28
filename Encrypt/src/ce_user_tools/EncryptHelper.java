package ce_user_tools;

import java.security.MessageDigest;


public class EncryptHelper {
	
	public static String getEncrypt(String str,String encryptType) throws Exception{
		MessageDigest digest = MessageDigest.getInstance(encryptType);
		byte[] hash = digest.digest(str.getBytes("UTF-8"));
		digest.update(hash);
		StringBuffer hexString = new StringBuffer();

		for (int i = 0; i < hash.length; i++) {
			String hex = Integer.toHexString(0xff & hash[i]);
			if (hex.length() == 1) {
				hexString.append('0');
			}
			hexString.append(hex);
		}
		return hexString.toString();	
	}
	
	public static String md5Encrypt(String str) throws Exception{
		return getEncrypt(str,"MD5");
	}
	
	public static String sha256Encrypt(String str) throws Exception{
		return getEncrypt(str,"SHA-256");
	}
}
