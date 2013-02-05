package com.smv.util.io;

import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;

public class FileUtil {
	
    public static boolean saveImage(byte[] in, File outFile) {
    	return saveImage(new ByteArrayInputStream(in), outFile);
    }
	
    public static boolean saveImage(InputStream is, File outFile) {
    	FileOutputStream fos = null;
    	BufferedOutputStream bos = null;;
    	
    	try {
			fos = new FileOutputStream(outFile);		
			bos = new BufferedOutputStream(fos);
			final ReadableByteChannel inputChannel = Channels.newChannel(is);
	    	final WritableByteChannel outputChannel = Channels.newChannel(bos);
	    	fastChannelCopy(inputChannel, outputChannel);
	    	// closing the channels
	    	inputChannel.close();
	    	outputChannel.close();
	    	
			bos.close();
			fos.close();
			is.close();			
    	} catch (Exception e) {
			e.printStackTrace();
		}
    	
    	return true;
    }
    
    public static void fastChannelCopy(final ReadableByteChannel src, final WritableByteChannel dest) throws IOException {
    	final ByteBuffer buffer = ByteBuffer.allocateDirect(16 * 1024);
    	while (src.read(buffer) != -1) {
    		// prepare the buffer to be drained
    		buffer.flip();
    		// write to the channel, may block
    		dest.write(buffer);
    		// If partial transfer, shift remainder down
    		// If buffer is empty, same as doing clear()
    		buffer.compact();
    	}
    	// EOF will leave buffer in fill state
    	buffer.flip();
    	// make sure the buffer is fully drained.
    	while (buffer.hasRemaining()) {
    		dest.write(buffer);
    	}
    }
}
