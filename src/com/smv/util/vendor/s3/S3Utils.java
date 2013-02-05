package com.smv.util.vendor.s3;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;
import org.jets3t.service.Constants;
import org.jets3t.service.S3ServiceException;


/**
 * @author Minh Do
 * 03/01/2010
 */
public class S3Utils {
	
	private static final Logger log = Logger.getLogger(S3Utils.class);
	
	/**
     * Calculate the HMAC/SHA1 on a string.
     * 
     * @param awsSecretKey
     * AWS secret key.
     * @param canonicalString
     * canonical string representing the request to sign.
     * @return Signature
     * @throws S3ServiceException
     */
    public static String signWithHmacSha1(String awsSecretKey, String canonicalString)
        throws S3ServiceException
    {
        if (awsSecretKey == null) {
        	if (log.isDebugEnabled()) {
        		log.debug("Canonical string will not be signed, as no AWS Secret Key was provided");
        	}
            return null;
        }
        
        // The following HMAC/SHA1 code for the signature is taken from the
        // AWS Platform's implementation of RFC2104 (amazon.webservices.common.Signature)
        //
        // Acquire an HMAC/SHA1 from the raw key bytes.
        SecretKeySpec signingKey = null;
        try {
            signingKey = new SecretKeySpec(awsSecretKey.getBytes(Constants.DEFAULT_ENCODING),
                Constants.HMAC_SHA1_ALGORITHM);
        } catch (UnsupportedEncodingException e) {
            throw new S3ServiceException("Unable to get bytes from secret string", e);
        }

        // Acquire the MAC instance and initialize with the signing key.
        Mac mac = null;
        try {
            mac = Mac.getInstance(Constants.HMAC_SHA1_ALGORITHM);
        } catch (NoSuchAlgorithmException e) {
            // should not happen
            throw new RuntimeException("Could not find sha1 algorithm", e);
        }
        try {
            mac.init(signingKey);
        } catch (InvalidKeyException e) {
            // also should not happen
            throw new RuntimeException("Could not initialize the MAC algorithm", e);
        }

        // Compute the HMAC on the digest, and set it.
        try {
            byte[] b64 = Base64.encodeBase64(mac.doFinal(
                canonicalString.getBytes(Constants.DEFAULT_ENCODING))); 
            return new String(b64);
        } catch (UnsupportedEncodingException e) {
            throw new S3ServiceException("Unable to get bytes from canonical string", e);
        }
    }
}
