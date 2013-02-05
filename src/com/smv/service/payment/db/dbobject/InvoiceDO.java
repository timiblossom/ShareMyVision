/**
 * 
 */
package com.smv.service.payment.db.dbobject;

import com.smv.util.db.AbstractVersionedDatedDO;

/**
 * @author TriNguyen
 *
 */
public class InvoiceDO extends AbstractVersionedDatedDO {

	private String invoiceNumberId;
	private Double totalInvoicedAmount;
	private Double totalInvoicedQuantity;
	private Long orderId;
	private String invoiceCode;
	private String invoiceDescription;
	private Long userId;
	private Long accountId;
	/**
	 * @return the invoiceNumberId
	 */
	public String getInvoiceNumberId() {
		return invoiceNumberId;
	}
	/**
	 * @param invoiceNumberId the invoiceNumberId to set
	 */
	public void setInvoiceNumberId(String invoiceNumberId) {
		this.invoiceNumberId = invoiceNumberId;
	}
	/**
	 * @return the totalInvoicedAmount
	 */
	public Double getTotalInvoicedAmount() {
		return totalInvoicedAmount;
	}
	/**
	 * @param totalInvoicedAmount the totalInvoicedAmount to set
	 */
	public void setTotalInvoicedAmount(Double totalInvoicedAmount) {
		this.totalInvoicedAmount = totalInvoicedAmount;
	}
	/**
	 * @return the totalInvoicedQuantity
	 */
	public Double getTotalInvoicedQuantity() {
		return totalInvoicedQuantity;
	}
	/**
	 * @param totalInvoicedQuantity the totalInvoicedQuantity to set
	 */
	public void setTotalInvoicedQuantity(Double totalInvoicedQuantity) {
		this.totalInvoicedQuantity = totalInvoicedQuantity;
	}
	/**
	 * @return the orderId
	 */
	public Long getOrderId() {
		return orderId;
	}
	/**
	 * @param orderId the orderId to set
	 */
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}
	/**
	 * @return the invoiceCode
	 */
	public String getInvoiceCode() {
		return invoiceCode;
	}
	/**
	 * @param invoiceCode the invoiceCode to set
	 */
	public void setInvoiceCode(String invoiceCode) {
		this.invoiceCode = invoiceCode;
	}
	/**
	 * @return the invoiceDescription
	 */
	public String getInvoiceDescription() {
		return invoiceDescription;
	}
	/**
	 * @param invoiceDescription the invoiceDescription to set
	 */
	public void setInvoiceDescription(String invoiceDescription) {
		this.invoiceDescription = invoiceDescription;
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
		builder.append("InvoiceDO [accountId=");
		builder.append(accountId);
		builder.append(", invoiceCode=");
		builder.append(invoiceCode);
		builder.append(", invoiceDescription=");
		builder.append(invoiceDescription);
		builder.append(", invoiceNumberId=");
		builder.append(invoiceNumberId);
		builder.append(", orderId=");
		builder.append(orderId);
		builder.append(", totalInvoicedAmount=");
		builder.append(totalInvoicedAmount);
		builder.append(", totalInvoicedQuantity=");
		builder.append(totalInvoicedQuantity);
		builder.append(", userId=");
		builder.append(userId);
		builder.append("]");
		return builder.toString();
	}

}
