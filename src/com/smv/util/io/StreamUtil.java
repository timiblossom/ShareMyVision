package com.smv.util.io;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.log4j.Logger;

public class StreamUtil {
	
	private static final Logger log = Logger.getLogger(StreamUtil.class);
	
    /**
     * Reads text data from an input stream and returns it as a String.
     * 
     * @param is
     * input stream from which text data is read.
     * @param encoding
     * the character encoding of the textual data in the input stream. If this
     * parameter is null, the default system encoding will be used.
     * 
     * @return
     * text data read from the input stream.
     * 
     * @throws IOException
     */
    public static String readInputStreamToString(InputStream is, String encoding) throws IOException {
        StringBuffer sb = new StringBuffer();
        BufferedReader br = null;
        if (encoding != null) {
            br = new BufferedReader(new InputStreamReader(is, encoding));
        } else {
            br = new BufferedReader(new InputStreamReader(is));
        }
        String line = null;
        try {
            while ((line = br.readLine()) != null) {
                sb.append(line + "\n");
            }
        } catch (Exception e) {        	
        	log.warn("Unable to read String from Input Stream", e);        	
        }
        return sb.toString();
    }
    
    /**
     * Reads from an input stream until a newline character or the end of the stream is reached.
     * 
     * @param is
     * @return
     * text data read from the input stream, not including the newline character.
     * @throws IOException
     */
    public static String readInputStreamLineToString(InputStream is, String encoding) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        int b = -1;        
        while ((b = is.read()) != -1) {
            if ('\n' == (char) b) {
                break;
            } else {
                baos.write(b);
            }
        }
        return new String(baos.toByteArray(), encoding);
    }  
    
    
    /**
     * Convert InputStream to a byte[]
     * @param in
     * @return byte[]
     * @throws IOException
     */
	public static byte[] inputStreamToByteArray(InputStream in) throws IOException {	
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		int next = in.read();
		while (next > -1) {
			bos.write(next);
			next = in.read();
		}
		bos.flush();
		return bos.toByteArray();
	}
}
