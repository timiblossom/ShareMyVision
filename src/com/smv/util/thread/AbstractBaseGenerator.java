package com.smv.util.thread;


/**
 * @author Minh Do
 * 03/2010
 */
public abstract class  AbstractBaseGenerator {

	private String clazz;


	public AbstractBaseGenerator(String s) {
		clazz= s;
	}

	public abstract <T> T generateInstance() throws Exception;

	public void setClassName(String clazz) {
		this.clazz = clazz;
	}

	public String getClassName() {
		return clazz;
	}
}
