/**
 * 
 */
package com.smv.service.payment.db.dbobject;

import com.smv.util.db.AbstractVersionedDatedDO;

/**
 * @author TriNguyen
 *
 */
public class PaymentMethodDO extends AbstractVersionedDatedDO {

	private String methodName;
	private String description;
	/**
	 * @return the methodName
	 */
	public String getMethodName() {
		return methodName;
	}
	/**
	 * @param methodName the methodName to set
	 */
	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuffer builder = super.getString();
		builder.append("PaymentMethodDO [description=");
		builder.append(description);
		builder.append(", methodName=");
		builder.append(methodName);
		builder.append("]");
		return builder.toString();
	}

}
