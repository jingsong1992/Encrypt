package ce_user_encrypt;

import ce_user_tools.EncryptHelper;

public class SHA256Encrypt implements EncryptPolicy{

	public String encrypt(String str) throws Exception {
		return EncryptHelper.sha256Encrypt(str);
	}
}
