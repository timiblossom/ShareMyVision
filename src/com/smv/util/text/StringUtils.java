/**
 * 
 */
package com.smv.util.text;

import org.apache.commons.lang.RandomStringUtils;

/**
 * @author TriNguyen
 *
 */
public class StringUtils {


    /**
    Determine if a String is null or empty.

    @param str a String object to check
    @return <b>true</b> if <B>string</B> is null or empty,
    <b>false</b> otherwise
     */
	public static boolean nullOrEmptyString (final String str)
	{
	    return (str == null) || (str.length() == 0);
	}

	/**
	    Determine if a string is null or empty or entirely spaces.
	
	    @param str a String object to check
	    @return <b>true</b> if <B>string</B> is null, empty, or all
	    spaces; false otherwise
	*/
	public static boolean nullOrEmptyOrBlankString (final String str)
	{
	    final int length = (str != null) ? str.length() : 0;
	    if (length > 0) {
	        for (int i = 0; i < length; i++) {
	            if (!Character.isWhitespace(str.charAt(i))) {
	                return false;
	            }
	        }
	    }
	    return true;
	}

	public static String generateRandomString(int length) {
		return RandomStringUtils.randomAlphanumeric(length);
	}
	
}
