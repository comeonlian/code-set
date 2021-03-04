package com.leolian.code.fragment.book.distributed.chapter03.encrypt;

import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;

/**
 * 非对称加密算法-RSA
 * @Description: 
 * @author lianliang
 * @date 2017年11月10日 下午3:52:04
 */
public class RSA {
	
	public static KeyPair getKeyPair() throws Exception {
		KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
		keyPairGenerator.initialize(512);
		KeyPair keyPair = keyPairGenerator.generateKeyPair();
		return keyPair;
	}
	
	/**
	 * 生成公钥
	 * @param keyPair
	 * @return
	 */
	public static String getPublicKey(KeyPair keyPair) {
		PublicKey publicKey = keyPair.getPublic();
		byte[] bytes = publicKey.getEncoded();
		return DigitalDigest.byte2Base64(bytes);
	}
	
	/**
	 * 生成私钥
	 * @param keyPair
	 * @return
	 */
	public static String getPrivateKey(KeyPair keyPair) {
		PrivateKey privateKey = keyPair.getPrivate();
		byte[] bytes = privateKey.getEncoded();
		return DigitalDigest.byte2Base64(bytes);
	}
	
	/**
	 * 将String类型的密钥转换为PublicKey对象
	 * @param pubStr
	 * @return
	 * @throws Exception
	 */
	public static PublicKey string2PublicKey(String pubStr) throws Exception{
		byte[] keyBytes = DigitalDigest.base642byte(pubStr);
		X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		PublicKey publicKey = keyFactory.generatePublic(keySpec);
		return publicKey;
	}
	
	/**
	 * 将String类型的密钥转换为PrivateKey对象
	 * @param priStr
	 * @return
	 * @throws Exception
	 */
	public static PrivateKey string2PrivateKey(String priStr) throws Exception{
		byte[] keyBytes = DigitalDigest.base642byte(priStr);
		PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		PrivateKey privateKey = keyFactory.generatePrivate(keySpec);
		return privateKey;
	}
	
	/**
	 * 公钥加密
	 * @param content
	 * @param publicKey
	 * @return
	 * @throws Exception
	 */
	public static byte[] publicEncrypt(byte[] content, PublicKey publicKey) throws Exception{
		Cipher ciper = Cipher.getInstance("RSA");
		ciper.init(Cipher.ENCRYPT_MODE, publicKey);
		byte[] bytes = ciper.doFinal(content);
		return bytes;
	}
	
	/**
	 * 私钥解密
	 * @param content
	 * @param privateKey
	 * @return
	 * @throws Exception
	 * @throws NoSuchPaddingException
	 */
	public static byte[] privateDecrypt(byte[] content, PrivateKey privateKey) throws Exception, NoSuchPaddingException{
		Cipher ciper = Cipher.getInstance("RSA");
		ciper.init(Cipher.DECRYPT_MODE, privateKey);
		byte[] bytes = ciper.doFinal(content);
		return bytes;
	}
	
}
