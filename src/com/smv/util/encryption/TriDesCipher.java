package com.smv.util.encryption;


import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;


public class TriDesCipher {
	private static final Logger log = Logger.getLogger(TriDesCipher.class);
	public static String TRIDES = "DESede";	

	private Cipher ecipher;
	private Cipher dcipher;


	public TriDesCipher(String storedKey) throws InvalidKeyException,
	NoSuchAlgorithmException, InvalidKeySpecException, IOException {

		this(TriDesCipher.readKeyFromStringBase64(storedKey));
	}

	public TriDesCipher(SecretKey key) {
		try {
			ecipher = Cipher.getInstance(TRIDES);
			dcipher = Cipher.getInstance(TRIDES);
			ecipher.init(Cipher.ENCRYPT_MODE, key);
			dcipher.init(Cipher.DECRYPT_MODE, key);
		} catch (NoSuchPaddingException e) {
			log.error(e);
		} catch (NoSuchAlgorithmException e) {
			log.error(e);
		} catch (InvalidKeyException e) {
			log.error(e);
		}
	}

	public String encrypt(String str) {
		try {
			byte[] utf8 = str.getBytes("UTF8");
			byte[] enc = ecipher.doFinal(utf8);

			// Encode bytes to base64 to get a string
			byte[] encryptedData = Base64.encodeBase64(enc);
           
			String base64 = new String(encryptedData);
			base64 = base64.replaceAll("/", "_");
    		return base64;
		} catch (BadPaddingException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	
	public String encrypt(byte[] data) {
		try {
			byte[] enc = ecipher.doFinal(data);

			// Encode bytes to base64 to get a string
			byte[] encryptedData = Base64.encodeBase64(enc);
            	       
    		String base64 = new String(encryptedData);
    		base64 = base64.replaceAll("/", "_");
    		return base64;
            //return base64.replace("=", "");            
            
		} catch (BadPaddingException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} 
		
		return null;
	}
	
	
	public String decrypt(String s) {

		try {
			String str = s.replaceAll("_", "/");
			byte[] dec = Base64.decodeBase64(str.getBytes());
			byte[] utf8 = dcipher.doFinal(dec);

			return new String(utf8, "UTF8");
		} catch (BadPaddingException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	public byte[] decryptToByteArray(String s) {

		try {
			String str = s.replaceAll("_", "/");
			byte[] dec = Base64.decodeBase64(str.getBytes());
			byte[] utf8 = dcipher.doFinal(dec);

			return utf8;
		} catch (BadPaddingException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} 
		
		return null;
	}
	
	private static SecretKey generateKey() throws NoSuchAlgorithmException {
		KeyGenerator keygen = KeyGenerator.getInstance(TRIDES);
		return keygen.generateKey();
	}

	private static String keyToStringBase64(SecretKey key)
	throws NoSuchAlgorithmException, InvalidKeySpecException {
		SecretKeyFactory keyfactory = SecretKeyFactory.getInstance(TRIDES);
		DESedeKeySpec keyspec = (DESedeKeySpec) keyfactory.getKeySpec(key,
				DESedeKeySpec.class);

		byte[] rawkey = keyspec.getKey();
		return new String(Base64.encodeBase64(rawkey));
	}

	private static SecretKey readKeyFromStringBase64(String keyInBase64)
	throws NoSuchAlgorithmException, InvalidKeyException,
	InvalidKeySpecException, IOException {

		byte[] rawkey = Base64.decodeBase64(keyInBase64.getBytes());

		DESedeKeySpec keyspec = new DESedeKeySpec(rawkey);
		SecretKeyFactory keyfactory = SecretKeyFactory.getInstance(TRIDES);
		SecretKey key = keyfactory.generateSecret(keyspec);
		return key;
	}


	//This method is used to generate a key to store for the application server
	//keep this to regenerate key
	private static String generatedAKey() {
		String s = null;
		try {
			s = keyToStringBase64(generateKey());
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (InvalidKeySpecException e) {

			e.printStackTrace();
		}
		return s;
	}


	
	public static void main(String[] args) throws InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException, IOException {
		String key = generatedAKey();
		TriDesCipher cipher = new TriDesCipher(key);
		
		String ss = cipher.encrypt("minh");
		
		System.out.println("ss : " + ss);
		
		String dss = cipher.decrypt(ss);
		
		System.out.println("dss : " + dss);

		
	}
	
}

