package com.smv.util.thread.generator;



import javax.xml.xpath.XPathFactory;

import com.smv.util.thread.AbstractBaseGenerator;

/**
 * @author Minh Do
 * 03/2010
 */

public class XPathGenerator extends AbstractBaseGenerator {

	private static XPathFactory xpathFactory = XPathFactory.newInstance();

	public XPathGenerator() {
		super("javax.xml.xpath.XPath");	
	}

	@Override
	public Object generateInstance() throws Exception {

		Object result = null;

		synchronized(xpathFactory) {
			result = xpathFactory.newXPath();	
		}

		return result;	
	}


}
