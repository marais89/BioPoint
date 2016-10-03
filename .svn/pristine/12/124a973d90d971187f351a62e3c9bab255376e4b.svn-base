package org.bio.service;

import it.eng.spagobi.security.AsymmetricProviderSingleton;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;



public class PasswordEncrypt {
	  private String encValue = "";
		 private String value = "meznih";
	public String testEncrypt(String password){
		 AsymmetricProviderSingleton bs;
		try {
			bs = AsymmetricProviderSingleton.getInstance();
		    encValue = "#SHA#" + bs.enCrypt(password);
		    
		 	return encValue;
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		encValue = "#SHA#";
		return encValue;
	
	}
	
																		
	
	public void encrypt(String password) throws NoSuchAlgorithmException, UnsupportedEncodingException  {

        MessageDigest crypt = MessageDigest.getInstance("SHA-1");
        crypt.reset();
        crypt.update(password.getBytes("UTF-8"));

       
    }

	private Object BigInteger(int i, byte[] digest) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
