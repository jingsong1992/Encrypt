package ce_user_encrypt;

import ce_user_tools.EncryptHelper;

public class MD5Encrypt implements EncryptPolicy{

	public String encrypt(String str) throws Exception {
		return EncryptHelper.md5Encrypt(str);
	}

}
