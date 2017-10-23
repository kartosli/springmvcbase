package com.n5x.common.system;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 支持SHA-256、MD5加密
 *
 */
public final class MD5 {

	/**
	 * 加密
	 * @param strSrc   源码
	 * @param encName	加密算法默认 SHA-256
	 * @return
	 */
	public static String Encrypt(String strSrc, String encName) {
		MessageDigest md = null;
		String strDes = null;

		byte[] bt = strSrc.getBytes();
		try {
			if ((encName == null) || (encName.equals(""))) {
				encName = "SHA-256";
			}
			md = MessageDigest.getInstance(encName);
			md.update(bt);
			strDes = bytes2Hex(md.digest());
		} catch (NoSuchAlgorithmException e) {
			return null;
		}
		return strDes;
	}

	public static String bytes2Hex(byte[] bts) {
		String des = "";
		String tmp = null;
		for (int i = 0; i < bts.length; ++i) {
			tmp = Integer.toHexString(bts[i] & 0xFF);
			if (tmp.length() == 1) {
				des = des + "0";
			}
			des = des + tmp;
		}
		return des;
	}
	
	public static String toMD5(String source){
		return Encrypt(source,"MD5");
	}
	
	public static String toSHA256(String source){
		return Encrypt(source,"SHA-256");
	}
	
	public static String toSHA1(String source){
		return Encrypt(source,"SHA-1");
	}

	public static void main(String[] args) throws CloneNotSupportedException {
		System.out.println(MD5.Encrypt("654321", "MD5"));
		System.out.println(MD5.Encrypt("654321", "SHA-256"));
		System.out.println(MD5.Encrypt("654321", "SHA-1"));
		
	}

}
