package ce_user_encrypt;

import java.io.IOException;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class Base64Encrypt {

	
	public static String getEncrypt(String str){
		if(str != null&&str != ""){
			return encryptBase64(str);
		}
		return null;	
	}
	
	
	public static String getDecrypt(String str){
		if(str != null&&str != ""){
			return decryptBase64(str);
		}
		return null;		
	}
	
	//使用BASE64对字符串进行加密
	public static String encryptBase64(String str){
		BASE64Encoder encoder = new BASE64Encoder();
		String encryptStr = encoder.encode(str.getBytes());
		return encryptStr;		
	}
	
	//使用BASE64对字符串进行解密
	public static String decryptBase64(String str){
		BASE64Decoder decoder = new BASE64Decoder();
		String decryptStr = null;
		try {
			byte[] decoderStr = decoder.decodeBuffer(str);
			decryptStr = new String(decoderStr);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return decryptStr;	
	}
		
	public static void main(String[] args) {
		String hello = "bgfsbgfsbgsrtw";
		hello = Base64Encrypt.encryptBase64(hello);
		System.out.println("加密 "+hello);
		System.out.println("解密 "+Base64Encrypt.decryptBase64(hello));
	}
}
