package com.crypto.backend.helper;

import java.security.MessageDigest;

/**
 * @author Phat Ngo
 * [1.26112020]
 * 
 * SE 575 - Software Design
 * 
 * Uses SHA-256 to generate the hashes
 * 
 */

public class SHA256Utility 
{
	/* Function takes the string input
	 * Returns a hashed string as output
	 */
	public static String applySHA256(String input)
	{
		try 
		{
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			byte[] hash = digest.digest(input.getBytes("UTF-8"));
			StringBuffer hexString = new StringBuffer();
			for(int i = 0; i < hash.length; i++) {
				String hex = Integer.toHexString(0xff & hash[i]);
				if(hex.length() == 1) {
					hexString.append('0');
				}
				hexString.append(hex);
			}
			return hexString.toString();
		}
		catch(Exception e)
		{
			throw new RuntimeException(e);
		}
	}
	
}
