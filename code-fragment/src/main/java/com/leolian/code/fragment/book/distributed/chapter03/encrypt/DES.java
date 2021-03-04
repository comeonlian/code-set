package com.leolian.code.fragment.book.distributed.chapter03.encrypt;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 * 对称加密算法
 * @Description:
 * @author lianliang
 * @date 2017年10月24日 上午11:02:07
 */
public class DES {

	public static String genKeyDES() throws Exception {
		KeyGenerator keyGen = KeyGenerator.getInstance("DES");
		keyGen.init(56);
		SecretKey key = keyGen.generateKey();
		String base64Str = DigitalDigest.byte2Base64(key.getEncoded());
		return base64Str;
	}

	public static SecretKey loadKeyDES(String base64Key) throws Exception {
		byte[] bytes = DigitalDigest.base642byte(base64Key);
		SecretKey key = new SecretKeySpec(bytes, "DES");
		return key;
	}

	public static byte[] encryptDES(byte[] source, SecretKey key) throws Exception {
		Cipher cipher = Cipher.getInstance("DES");
		cipher.init(Cipher.ENCRYPT_MODE, key);
		byte[] bytes = cipher.doFinal(source);
		return bytes;
	}

	public static byte[] decryptDES(byte[] source, SecretKey key) throws Exception {
		Cipher cipher = Cipher.getInstance("DES");
		cipher.init(Cipher.DECRYPT_MODE, key);
		byte[] bytes = cipher.doFinal(source);
		return bytes;
	}

}
