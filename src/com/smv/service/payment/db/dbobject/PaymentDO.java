/**
 * 
 */
package com.smv.service.payment.db.dbobject;

import java.sql.Timestamp;

import com.smv.util.db.AbstractVersionedDatedDO;

/**
 * @author TriNguyen
 *
 */
public class PaymentDO extends AbstractVersionedDatedDO {

	private String paymentId;
	private Timestamp paymentDate;
	private Long paymentMethod;
	private String description;
	private Long invoiceId;
	private Long userId;
	private Long accountId;
	/**
	 * @return the paymentId
	 */
	public String getPaymentId() {
		return paymentId;
	}
	/**
	 * @param paymentId the paymentId to set
	 */
	public void setPaymentId(String paymentId) {
		this.paymentId = paymentId;
	}
	/**
	 * @return the paymentDate
	 */
	public Timestamp getPaymentDate() {
		return paymentDate;
	}
	/**
	 * @param paymentDate the paymentDate to set
	 */
	public void setPaymentDate(Timestamp paymentDate) {
		this.paymentDate = paymentDate;
	}
	/**
	 * @return the paymentMethod
	 */
	public Long getPaymentMethod() {
		return paymentMethod;
	}
	/**
	 * @param paymentMethod the paymentMethod to set
	 */
	public void setPaymentMethod(Long paymentMethod) {
		this.paymentMethod = paymentMethod;
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
	/**
	 * @return the invoiceId
	 */
	public Long getInvoiceId() {
		return invoiceId;
	}
	/**
	 * @param invoiceId the invoiceId to set
	 */
	public void setInvoiceId(Long invoiceId) {
		this.invoiceId = invoiceId;
	}
	/**
	 * @return the userId
	 */
	public Long getUserId() {
		return userId;
	}
	/**
	 * @param userId the userId to set
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	/**
	 * @return the accountId
	 */
	public Long getAccountId() {
		return accountId;
	}
	/**
	 * @param accountId the accountId to set
	 */
	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuffer builder = super.getString();
		builder.append("PaymentDO [accountId=");
		builder.append(accountId);
		builder.append(", description=");
		builder.append(description);
		builder.append(", invoiceId=");
		builder.append(invoiceId);
		builder.append(", paymentDate=");
		builder.append(paymentDate);
		builder.append(", paymentId=");
		builder.append(paymentId);
		builder.append(", paymentMethod=");
		builder.append(paymentMethod);
		builder.append(", userId=");
		builder.append(userId);
		builder.append("]");
		return builder.toString();
	}

}
