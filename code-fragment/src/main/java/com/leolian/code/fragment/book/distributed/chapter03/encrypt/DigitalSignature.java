package com.leolian.code.fragment.book.distributed.chapter03.encrypt;

import java.security.MessageDigest;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;

import javax.crypto.Cipher;

/**
 * 数字签名算法
 * @Description: 
 * @author lianliang
 * @date 2017年11月10日 下午4:46:38
 */
public class DigitalSignature {
	
	/**
	 * 生成签名
	 * @param content
	 * @param privateKey
	 * @return
	 * @throws Exception 
	 */
	public static byte[] signMD5(byte[] content, PrivateKey privateKey) throws Exception{
		MessageDigest md = MessageDigest.getInstance("MD5");
		byte[] bytes = md.digest(content);
		Cipher cipher = Cipher.getInstance("RSA");
		cipher.init(Cipher.ENCRYPT_MODE, privateKey);
		byte[] encryptBytes = cipher.doFinal(bytes);
		return encryptBytes;
	}
	
	/**
	 * 校验签名
	 * @param content
	 * @param sign
	 * @param publicKey
	 * @return
	 * @throws Exception 
	 */
	public static boolean verifyMD5(byte[] content, byte[] sign, PublicKey publicKey) throws Exception{
		MessageDigest md = MessageDigest.getInstance("MD5");
		byte[] bytes = md.digest(content);
		Cipher cipher = Cipher.getInstance("RSA");
		cipher.init(Cipher.DECRYPT_MODE, publicKey);
		byte[] decryptBytes = cipher.doFinal(sign);
		if(DigitalDigest.byte2Base64(decryptBytes).equals(DigitalDigest.byte2Base64(bytes))){
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * 基于JavaAPI的数字签名
	 * @param content
	 * @param privateKey
	 * @return
	 * @throws Exception
	 */
	public static byte[] signMD5Api(byte[] content, PrivateKey privateKey) throws Exception{
		Signature signature = Signature.getInstance("MD5withRSA");
		signature.initSign(privateKey);
		signature.update(content);
		return signature.sign();
	}
	
	public static boolean verifMD5Api(byte[] content, byte[] sign, PublicKey publicKey) throws Exception{
		Signature signature = Signature.getInstance("MD5withRSA");
		signature.initVerify(publicKey);
		signature.update(content);
		return signature.verify(sign);
	}
	
	
	/**
	 * 生成签名
	 * @param content
	 * @param privateKey
	 * @return
	 * @throws Exception 
	 */
	public static byte[] signSHA1(byte[] content, PrivateKey privateKey) throws Exception{
		MessageDigest md = MessageDigest.getInstance("SHA1");
		byte[] bytes = md.digest(content);
		Cipher cipher = Cipher.getInstance("RSA");
		cipher.init(Cipher.ENCRYPT_MODE, privateKey);
		byte[] encryptBytes = cipher.doFinal(bytes);
		return encryptBytes;
	}
	
	/**
	 * 校验签名
	 * @param content
	 * @param sign
	 * @param publicKey
	 * @return
	 * @throws Exception 
	 */
	public static boolean verifySHA1(byte[] content, byte[] sign, PublicKey publicKey) throws Exception{
		MessageDigest md = MessageDigest.getInstance("SHA1");
		byte[] bytes = md.digest(content);
		Cipher cipher = Cipher.getInstance("RSA");
		cipher.init(Cipher.DECRYPT_MODE, publicKey);
		byte[] decryptBytes = cipher.doFinal(sign);
		if(DigitalDigest.byte2Base64(decryptBytes).equals(DigitalDigest.byte2Base64(bytes))){
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * 基于JavaAPI的数字签名
	 * @param content
	 * @param privateKey
	 * @return
	 * @throws Exception
	 */
	public static byte[] signSHA1Api(byte[] content, PrivateKey privateKey) throws Exception{
		Signature signature = Signature.getInstance("SHA1withRSA");
		signature.initSign(privateKey);
		signature.update(content);
		return signature.sign();
	}
	
	public static boolean verifSHA1Api(byte[] content, byte[] sign, PublicKey publicKey) throws Exception{
		Signature signature = Signature.getInstance("SHA1withRSA");
		signature.initVerify(publicKey);
		signature.update(content);
		return signature.verify(sign);
	}
}
