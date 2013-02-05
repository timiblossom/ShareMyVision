package com.smv.util.cache.helper;


import com.thoughtworks.xstream.XStream;


/**
 * @author Minh Do
 * 07/22/2010
 */
public class XmlSerializer {

	private final static XStream xstream = new XStream();
	private final static XmlSerializer instance = new XmlSerializer();
	
	private XmlSerializer() {}
	
	
	public static XmlSerializer getInstance() {
		return instance;
	}
	
	public String toXml(Object o) {
		return xstream.toXML(o);
	}
	
	public Object fromXml(String s) {
		return xstream.fromXML(s);
	}
	
}
