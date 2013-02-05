package com.smv.util.number;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.CharacterIterator;
import java.text.StringCharacterIterator;
import java.util.Arrays;

import org.apache.log4j.Logger;

public class Conversion {
	private static final Logger LOGGER = Logger.getLogger(Conversion.class);

	/**
	 * This method converts a long value to a byte array
	 * @param long
	 * @return
	 */
	public static byte[] longToByteArray(final long l) {
		byte [] b = new byte[8];

		for(int i= 0; i < 8; i++){  
			b[7 - i] = (byte)(l >>> (i * 8));  
		}  

		return b;
	}


	/**
	 * This method converts a byte array to a long value 
	 * @param byte[8]
	 * @return
	 */
	public static long byteArrayToLong(final byte[] b) {
		long l = 1;

		for(int i =0; i < 8; i++){      
			l <<= 8;  
			l ^= (long)b[i] & 0xFF;      
		}
		return l;
	}


	/**
	 * This method converts a int to a byte array
	 * @param int
	 * @return
	 */
	public static byte[] intToByteArray(final int l) {
		byte [] b = new byte[4];

		for(int i= 0; i < 4; i++){  
			b[3-i] = (byte)(l >>> (i * 8));
		}  

		return b;
	}

	/**
	 * This method converts a byte array to a int value 
	 * @param byte[4]
	 * @return
	 */
	public static int byteArrayToInt(final byte[] b) {
		int l = 1;

		for(int i =0; i < 4; i++){
			l <<= 8;  
			l ^= (long)b[i] & 0xFF;    
		}
		return l;
	}


	/**
	 * This method converts a byte array to IP string representation 
	 * @param byte[] byte[] = {192, 168, 10, 1}
	 * @return String = "192.168.10.1"
	 */
	public static String getIpFromByteArray(final byte[] addr) {
		InetAddress inetAddr = null;

		try {
			inetAddr = InetAddress.getByAddress(addr);
			return inetAddr.getHostAddress();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}

		return null;				
	}
	
	/**
	 * This method converts an IP string representation to a byte array
	 * @param String = "192.168.10.1"
	 * @return byte[] byte[] = {1, 10, 168, 192}
	 */
	public static byte[] getByteArrayFromIp(String addr) {
		final String[] aa = addr.split("\\.");

		int ip = 0;
		for(int i=0; i<aa.length; i++) {
			int a = Integer.valueOf(aa[aa.length - i - 1]);
			ip |= (a & 0xFF) << (i*8) ;    
		}

		return intToByteArray(ip);				
	}

	
	
	
	public static long hexToLong(byte[] bytes) {

		if (bytes.length > 16) {
			throw new IllegalArgumentException("Byte array too long (max 16 elements)");
		}
		long v = 0;
		for (int i = 0; i < bytes.length; i += 2) {
			byte b1 = (byte) (bytes[i] & 0xFF);

			b1 -= 48;
			if (b1 > 9) b1 -= 39;

			if (b1 < 0 || b1 > 15) {
				throw new IllegalArgumentException("Illegal hex value: " + bytes[i]);
			}

			b1 <<=4;

			byte b2 = (byte) (bytes[i + 1] & 0xFF);
			b2 -= 48;
			if (b2 > 9) b2 -= 39;

			if (b2 < 0 || b2 > 15) {
				throw new IllegalArgumentException("Illegal hex value: " + bytes[i + 1]);
			}

			v |= (((b1 & 0xF0) | (b2 & 0x0F))) & 0x00000000000000FFL ;

			if (i + 2 < bytes.length) v <<= 8;
		}

		return v;
	}

	public static byte[] longToHex(final long l) {
		long v = l & 0xFFFFFFFFFFFFFFFFL;

		byte[] result = new byte[16];
		Arrays.fill(result, 0, result.length, (byte)0);

		for (int i = 0; i < result.length; i += 2) {
			byte b = (byte) ((v & 0xFF00000000000000L) >> 56);

			byte b2 = (byte) (b & 0x0F);
			byte b1 = (byte) ((b >> 4) & 0x0F);

			if (b1 > 9) b1 += 39;
			b1 += 48;

			if (b2 > 9) b2 += 39;
			b2 += 48;

			result[i] = (byte) (b1 & 0xFF);
			result[i + 1] = (byte) (b2 & 0xFF);

			v <<= 8;
		}

		return result;
	}
	
	
	/**
	 * Hexadecimal characters corresponding to each half byte value.
	 */
	public static final char[] HexChars = {
		'0', '1', '2', '3', '4', '5', '6', '7',
		'8', '9', 'a', 'b', 'c', 'd', 'e', 'f'
	};

	/**
	 * The BASE64 encoding standard's 6-bit alphabet, from RFC 1521,
	 * plus the padding character at the end.
	 */
	public static final char[] Base64Chars = {
		'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H',
		'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P',
		'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X',
		'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f',
		'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n',
		'o', 'p', 'q', 'r', 's', 't', 'u', 'v',
		'w', 'x', 'y', 'z', '0', '1', '2', '3',
		'4', '5', '6', '7', '8', '9', '+', '/',
		'='
	};

	/**
	 * Converts a long integer to an unsigned hexadecimal String.  Treats
	 * the integer as an unsigned 64 bit value and left-pads with the
	 * pad character of the caller's choice.
	 * 
	 * @param value The long integer to convert to a hexadecimal string.
	 * @param len   The total padded length of the string.  If the number
	 * 		    is larger than the padded length, then this length
	 * 		    of the string will be the length of the number.
	 * @param pad   The character to use for padding.
	 * @return	    Unsigned hexadecimal numeric string representing 
	 * 		    the specified value.
	 */
	public static final String toHexString(long value, int len, char pad) {
		StringBuffer sb = new StringBuffer(Long.toHexString(value));
		int npad = len - sb.length();
		while (npad-- > 0) {
			sb.insert(0, pad);
		}
		return new String(sb);
	}

	/**
	 * Converts an arbitrary array of bytes to ASCII hexadecimal string
	 * form, with two hex characters corresponding to each byte.  The
	 * length of the resultant string in characters will be twice the
	 * length of the specified array of bytes.
	 * 
	 * @param bytes The array of bytes to convert to ASCII hex form.
	 * @return	    An ASCII hexadecimal numeric string representing the
	 * 		    specified array of bytes.
	 */
	public static final String toHexString(byte[] bytes) {
		StringBuffer sb = new StringBuffer();
		int i;
		for (i=0; i < bytes.length; i++) {
			sb.append(HexChars[(bytes[i] >> 4) & 0xf]);
			sb.append(HexChars[bytes[i] & 0xf]);
		}
		return new String(sb);
	}

	/**
	 * Performs RFC1521 style Base64 encoding of arbitrary binary data.
	 * The output is a java String containing the Base64 characters representing
	 * the binary data.  Be aware that this string is in Unicode form, and
	 * should be converted to UTF8 with the usual java conversion routines
	 * before it is sent over a network.  The output string is guaranteed
	 * to only contain characters that are a single byte in UTF8 format.
	 * Also be aware that this routine leaves it to the caller to break
	 * the string into 70 byte lines as per RFC1521.
	 * 
	 * @param bytes The array of bytes to convert to Base64 encoding.
	 * @return	    An string containing the specified bytes in Base64
	 * 		    encoded form.
	 */
	public static final String toBase64String(byte[] bytes) {
		return toBase64String(bytes, Base64Chars);
	}


	/**
	 * Performs encoding of arbitrary binary data based on a 6 bit
	 * alphabet.  The output is a java String containing the encoded
	 * characters representing the binary data.  Be aware that this
	 * string is in Unicode form, and should be converted to UTF8 with
	 * the usual java conversion routines before it is sent over a
	 * network. The alphabet passed in via <code>chars</code> is used
	 * without further checks, it's the callers responsibility to set
	 * it to something meaningful.
	 * 
	 * @param bytes The array of bytes to convert to Base64 encoding.
	 * @param chars The alphabet used in encoding. Must contain
	 *              exactly 65 characters: A 6 bit alphabet plus one
	 *              padding char at position 65.
	 * @return	    An string containing the specified bytes in Base64
	 * 		    encoded form.
	 */
	public static final String toBase64String(byte[] bytes, char[] chars) {
		StringBuffer sb = new StringBuffer();
		int len = bytes.length, i=0, ival;
		while (len >= 3) {
			ival = ((int)bytes[i++] + 256) & 0xff;
			ival <<= 8;
			ival += ((int)bytes[i++] + 256) & 0xff;
			ival <<= 8;
			ival += ((int)bytes[i++] + 256) & 0xff;
			len -= 3;
			sb.append(chars[(ival >> 18) & 63]);
			sb.append(chars[(ival >> 12) & 63]);
			sb.append(chars[(ival >> 6) & 63]);
			sb.append(chars[ival & 63]);
		}
		switch (len) {
		case 0:	// No pads needed.
			break;
		case 1: // Two more output bytes and two pads.
			ival = ((int)bytes[i++] + 256) & 0xff;
			ival <<= 16;
			sb.append(chars[(ival >> 18) & 63]);
			sb.append(chars[(ival >> 12) & 63]);
			sb.append(chars[64]);
			sb.append(chars[64]);
			break;
		case 2:	// Three more output bytes and one pad.
			ival = ((int)bytes[i++] + 256) & 0xff;
			ival <<= 8;
			ival += ((int)bytes[i] + 256) & 0xff;
			ival <<= 8;
			sb.append(chars[(ival >> 18) & 63]);
			sb.append(chars[(ival >> 12) & 63]);
			sb.append(chars[(ival >> 6) & 63]);
			sb.append(chars[64]);
			break;
		default:
			break;
		}
		return new String(sb);
	}

	/**
	 * Performs RFC1521 style Base64 decoding of Base64 encoded data.
	 * The output is a byte array containing the decoded binary data.
	 * The input is expected to be a normal Unicode String object.
	 * 
	 * @param s The Base64 encoded string to decode into binary data.
	 * @return An array of bytes containing the decoded data.
	 */
	public static final byte[] fromBase64String(String s) {
		try {
			StringCharacterIterator iter = new StringCharacterIterator(s);
			ByteArrayOutputStream   bytestr = new ByteArrayOutputStream();
			DataOutputStream	    outstr = new DataOutputStream(bytestr);
			char		    c;
			int			    d, i, group;
			int[]		    bgroup = new int[4];
			decode: for (i=0, group=0, c = iter.first();
			c != CharacterIterator.DONE;
			c = iter.next())
			{
				switch (c) {
				case 'A': d =  0; break; case 'B': d =  1; break;
				case 'C': d =  2; break; case 'D': d =  3; break;
				case 'E': d =  4; break; case 'F': d =  5; break;
				case 'G': d =  6; break; case 'H': d =  7; break;
				case 'I': d =  8; break; case 'J': d =  9; break;
				case 'K': d = 10; break; case 'L': d = 11; break;
				case 'M': d = 12; break; case 'N': d = 13; break;
				case 'O': d = 14; break; case 'P': d = 15; break;
				case 'Q': d = 16; break; case 'R': d = 17; break;
				case 'S': d = 18; break; case 'T': d = 19; break;
				case 'U': d = 20; break; case 'V': d = 21; break;
				case 'W': d = 22; break; case 'X': d = 23; break;
				case 'Y': d = 24; break; case 'Z': d = 25; break;
				case 'a': d = 26; break; case 'b': d = 27; break;
				case 'c': d = 28; break; case 'd': d = 29; break;
				case 'e': d = 30; break; case 'f': d = 31; break;
				case 'g': d = 32; break; case 'h': d = 33; break;
				case 'i': d = 34; break; case 'j': d = 35; break;
				case 'k': d = 36; break; case 'l': d = 37; break;
				case 'm': d = 38; break; case 'n': d = 39; break;
				case 'o': d = 40; break; case 'p': d = 41; break;
				case 'q': d = 42; break; case 'r': d = 43; break;
				case 's': d = 44; break; case 't': d = 45; break;
				case 'u': d = 46; break; case 'v': d = 47; break;
				case 'w': d = 48; break; case 'x': d = 49; break;
				case 'y': d = 50; break; case 'z': d = 51; break;
				case '0': d = 52; break; case '1': d = 53; break;
				case '2': d = 54; break; case '3': d = 55; break;
				case '4': d = 56; break; case '5': d = 57; break;
				case '6': d = 58; break; case '7': d = 59; break;
				case '8': d = 60; break; case '9': d = 61; break;
				case '+': d = 62; break; case '/': d = 63; break;
				default:
					// Any character not in Base64 alphabet is treated
					// as end of data.  This includes the '=' (pad) char.
					break decode;   // Skip illegal characters.
				}
				bgroup[i++] = d;
				if (i >= 4) {
					i = 0;
					group = ((bgroup[0] & 63) << 18)+((bgroup[1] & 63) << 12)+
					((bgroup[2] & 63) << 6) + (bgroup[3] & 63);
					outstr.writeByte(((group >> 16) & 255));
					outstr.writeByte(((group >> 8) & 255));
					outstr.writeByte(group & 255);
				}
			}
			// Handle the case of remaining characters and
			// pad handling.  If input is not a multiple of 4
			// in length, then '=' pads are assumed.
			switch (i) {
			case 2:
				// One output byte from two input bytes.
				group = ((bgroup[0] & 63) << 18)+((bgroup[1] & 63) << 12);
				outstr.writeByte(((group >> 16) & 255));
				break;
			case 3:
				// Two output bytes from three input bytes.
				group = ((bgroup[0] & 63) << 18)+((bgroup[1] & 63) << 12)+
				((bgroup[2] & 63) << 6);
				outstr.writeByte(((group >> 16) & 255));
				outstr.writeByte(((group >> 8) & 255));
				break;
			default:
				// Any other case, including correct 0, is treated as
				// end of data.
				break;
			}
			outstr.flush();
			return bytestr.toByteArray();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		
		return new byte[0];
	}

}
