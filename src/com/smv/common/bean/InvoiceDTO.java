/**
 * 
 */
package com.smv.common.bean;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * @author Tri Nguyen
 */

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "InvoiceDTO")
public class InvoiceDTO implements Serializable {

	private String action;
	private String invoiceNumberId;
	private Double totalInvoicedAmount;
	private Double totalInvoicedQuantity;
	private Long orderId;
	private String invoiceCode;
	private String invoiceDescription;
	private Long userId;
	private Long accountId;
	private Long id;
	/**
	 * @return the action
	 */
	public String getAction() {
		return action;
	}
	/**
	 * @param action the action to set
	 */
	public void setAction(String action) {
		this.action = action;
	}
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
	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("InvoiceDTO [accountId=");
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
