package com.smv.util.cache.helper;



import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

/**
 * @author Ray Krueger
 * @author Minh Do
 */
public class KeyUtils {

    private static final char[] DIGITS = {
            '0', '1', '2', '3', '4', '5', '6', '7',
            '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'
    };

    public static String getKey(String serviceMethod, String param) {
    	String md5Value = md5Hex(param);
    	StringBuilder buffer = new StringBuilder();
    	
    	
    	buffer.append(serviceMethod);
    	buffer.append("|");
    	buffer.append(md5Value);
    	
    	return buffer.toString();
    }
    
    public static String getKey(String serviceMethod, List<String> params) {
    	return join(serviceMethod, params);
    }
    
    public static String join(String serviceMethod, List<String> params) {
    	StringBuilder buffer = new StringBuilder();    	    	
    	
    	for(String param : params) {
    		buffer.append(param);
    	}
    	
    	String md5Value = md5Hex(buffer.toString());
    	
    	buffer = new StringBuilder();
    	
    	buffer.append(serviceMethod);    	
    	buffer.append("|");
    	buffer.append(md5Value);
    	
    	return buffer.toString();
    }
    
    public static String join(Object[] array, String separator) {
        if (array == null) {
            return null;
        }
        int arraySize = array.length;
        StringBuilder buffer = new StringBuilder();

        for (int i = 0; i < arraySize; i++) {
            if (i > 0) {
                buffer.append(separator);
            }
            if (array[i] != null) {
                buffer.append(array[i]);
            }
        }
        return buffer.toString();
    }

    public static String md5Hex(String data) {
        if (data == null) {
            throw new IllegalArgumentException("data must not be null");
        }

        byte[] bytes = digest("MD5", data);

        return toHexString(bytes);
    }

    public static String sha1Hex(String data) {
        if (data == null) {
            throw new IllegalArgumentException("data must not be null");
        }

        byte[] bytes = digest("SHA1", data);

        return toHexString(bytes);
    }

    private static String toHexString(byte[] bytes) {
        int l = bytes.length;

        char[] out = new char[l << 1];

        for (int i = 0, j = 0; i < l; i++) {
            out[j++] = DIGITS[(0xF0 & bytes[i]) >>> 4];
            out[j++] = DIGITS[0x0F & bytes[i]];
        }

        return new String(out);
    }

    private static byte[] digest(String algorithm, String data) {
        MessageDigest digest;
        try {
            digest = MessageDigest.getInstance(algorithm);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

        return digest.digest(data.getBytes());
    }
}
