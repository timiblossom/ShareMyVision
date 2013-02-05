
package com.zaptoe.user.helper;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zaptoe.util.encryption.TriDesCipher;
import com.zaptoe.util.text.StringUtils;


public class PasswordEncryptionHelper {

	private final static Logger LOGGER = LoggerFactory.getLogger(PasswordEncryptionHelper.class);
		
	private static final int SALT_LENGTH = 32;

	
	public static String generatePasswordSalt() {
		return StringUtils.generateRandomString(PasswordEncryptionHelper.SALT_LENGTH);
	}
	
	public static String encryptPassword(String password, String salt) {
		TriDesCipher cipher = null;
		try {
			cipher = new TriDesCipher(salt);
		} catch (NoSuchAlgorithmException e) {
			LOGGER.error(e.getMessage());
		} catch (InvalidKeySpecException e) {
			LOGGER.error(e.getMessage());
		} catch (IOException e) {
			LOGGER.error(e.getMessage());
		} catch (InvalidKeyException e) {
			LOGGER.error(e.getMessage());
		}		

		if (cipher != null) {
			return cipher.encrypt(password);
		} else {
			return null;
		}
	}

}
