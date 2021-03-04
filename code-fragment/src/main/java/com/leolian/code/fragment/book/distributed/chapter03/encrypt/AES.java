package com.leolian.code.fragment.book.distributed.chapter03.encrypt;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 * 对称加密AES算法
 * @Description: 
 * @author lianliang
 * @date 2017年10月24日 上午11:21:25
 */
public class AES {
	
	public static String genKeyAES() throws Exception {
		KeyGenerator keyGen = KeyGenerator.getInstance("AES");
		keyGen.init(128);
		SecretKey key = keyGen.generateKey();
		String base64Str = DigitalDigest.byte2Base64(key.getEncoded());
		return base64Str;
	}

	public static SecretKey loadKeyAES(String base64Key) throws Exception {
		byte[] bytes = DigitalDigest.base642byte(base64Key);
		SecretKey key = new SecretKeySpec(bytes, "AES");
		return key;
	}

	public static byte[] encryptAES(byte[] source, SecretKey key) throws Exception {
		Cipher cipher = Cipher.getInstance("AES");
		cipher.init(Cipher.ENCRYPT_MODE, key);
		byte[] bytes = cipher.doFinal(source);
		return bytes;
	}

	public static byte[] decryptAES(byte[] source, SecretKey key) throws Exception {
		Cipher cipher = Cipher.getInstance("AES");
		cipher.init(Cipher.DECRYPT_MODE, key);
		byte[] bytes = cipher.doFinal(source);
		return bytes;
	}
	
	
}
