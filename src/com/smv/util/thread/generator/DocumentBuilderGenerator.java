package com.smv.util.thread.generator;


/**
 * @author Minh Do
 * 03/2010
 */

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import com.smv.util.thread.AbstractBaseGenerator;

public class DocumentBuilderGenerator extends AbstractBaseGenerator {

	private static DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();

	public DocumentBuilderGenerator() {
		super("javax.xml.parsers.DocumentBuilder");
	}

	@Override
	public Object generateInstance() throws Exception {
		//DocumentBuilder parser = DocumentBuilderFactory.newInstance().newDocumentBuilder();	
		DocumentBuilder parser = null;
		synchronized(docBuilderFactory) {
			parser = docBuilderFactory.newDocumentBuilder();
		}
		return parser;
	}

}
