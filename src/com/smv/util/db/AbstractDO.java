package com.smv.util.db;


/**
 * @author Minh Do
 * 03/2010
 */


public abstract class AbstractDO implements java.io.Serializable {
	
	private static final long serialVersionUID = -4856201834154662539L;

	public static int CREATE = 1;
	public static int UPDATE = 2;
	public static int DELETE = 3;
	public static int FIND = 4;
	public static int FETCH = 5;
	public static int NO_OP = -1;
	
	
	protected int operation = NO_OP;
	
	public void setOperation(int op) {
		operation = op;
	}
	
	public int getOperation() {
		return operation;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("AbstractDO [operation=");
		builder.append(operation);
		builder.append(", toString()=");
		builder.append(super.toString());
		builder.append("]");
		return builder.toString();
	}
}
