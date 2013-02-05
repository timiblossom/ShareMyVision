package com.smv.util.cache.helper;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import org.jboss.serial.io.JBossObjectInputStream;
import org.jboss.serial.io.JBossObjectOutputStream;


/**
 * @author Minh Do
 * 07/22/2010
 */
public class BinarySerializer {
	
	private final static BinarySerializer instance = new BinarySerializer();
	
	private BinarySerializer() {}
	
	
	public static BinarySerializer getInstance() {
		return instance;
	}
	
	public Object toObject(byte[] in) {
		ByteArrayInputStream bais;
	    JBossObjectInputStream jois;	    
	    Object value = null;
	    if (in == null)
	    	return null;
	    
		try {
			bais = new ByteArrayInputStream(in);
			jois = new JBossObjectInputStream(bais);
			value = jois.readObject();		
			jois.close();	
		} catch (Exception e) {
			e.printStackTrace();
		}
	    return value;
	}
	
	
	public byte[] fromObject(Object o){
		ByteArrayOutputStream baos;
		JBossObjectOutputStream mvos;
		
		if (o == null)
			return null;
		
		try {
			baos = new ByteArrayOutputStream();
			mvos = new JBossObjectOutputStream(baos);
			mvos.writeObject(o);
			mvos.flush();
			mvos.close();
			baos.close();
			return baos.toByteArray();
		} catch (Exception e) {
			e.printStackTrace();
		}
	    
	    return null;
	}

}
