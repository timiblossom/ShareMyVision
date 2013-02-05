
package com.zaptoe.user.helper;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zaptoe.common.Constant;
import com.zaptoe.util.encryption.TriDesCipher;
import com.zaptoe.util.thread.LocalThreadObjectsHolder;


public class EncryptionDecryptionHelper {

	private final static Logger LOGGER = LoggerFactory.getLogger(EncryptionDecryptionHelper.class);
	
	public static TriDesCipher getCipher() {
		TriDesCipher cipher = (TriDesCipher) LocalThreadObjectsHolder.getLocalThreadObject("TRIPLE_DES_CIPHER");
		if (cipher == null) {
			try {
				cipher = new TriDesCipher(Constant.TRIPLE_DES_KEY);
				LocalThreadObjectsHolder.setLocalThreadObject("TRIPLE_DES_CIPHER", cipher);
			} catch (NoSuchAlgorithmException e) {
				LOGGER.error(e.getMessage());
			} catch (InvalidKeySpecException e) {
				LOGGER.error(e.getMessage());
			} catch (IOException e) {
				LOGGER.error(e.getMessage());
			} catch (InvalidKeyException e) {
				LOGGER.error(e.getMessage());
			}
		}
		
		return (TriDesCipher) cipher;
	}
	
}
