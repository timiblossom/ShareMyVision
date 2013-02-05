package com.smv.util.cache;



public interface ISerializer {
	
	public Object toObject(byte[] in);
	public byte[] fromObject(Object o);
	
	
}
