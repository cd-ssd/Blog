package com.ischoolbar.programmer.util;

import org.apache.shiro.crypto.hash.Md5Hash;

/**
 * ���ܹ���
 *
 */
public class CryptographyUtil {

	
	/**
	 * Md5����
	 */
	public static String md5(String str,String salt){
		return new Md5Hash(str,salt).toString();
	}
	
	public static void main(String[] args) {
		String password="admin";
		
		System.out.println("Md5���ܣ�"+CryptographyUtil.md5(password, "programmer.ischoolbar.com"));
	}
}
