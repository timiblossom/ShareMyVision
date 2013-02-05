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
@XmlType(name = "PurchaseOrderDTO")
public class PurchaseOrderDTO implements Serializable {

	private String action;
	private String orderNumberId;
	private Double totalOrderAmount;
	private Double totalOrderQuantity;
	private String orderCode;
	private String orderDescription;
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
	 * @return the orderNumberId
	 */
	public String getOrderNumberId() {
		return orderNumberId;
	}
	/**
	 * @param orderNumberId the orderNumberId to set
	 */
	public void setOrderNumberId(String orderNumberId) {
		this.orderNumberId = orderNumberId;
	}
	/**
	 * @return the totalOrderAmount
	 */
	public Double getTotalOrderAmount() {
		return totalOrderAmount;
	}
	/**
	 * @param totalOrderAmount the totalOrderAmount to set
	 */
	public void setTotalOrderAmount(Double totalOrderAmount) {
		this.totalOrderAmount = totalOrderAmount;
	}
	/**
	 * @return the totalOrderQuantity
	 */
	public Double getTotalOrderQuantity() {
		return totalOrderQuantity;
	}
	/**
	 * @param totalOrderQuantity the totalOrderQuantity to set
	 */
	public void setTotalOrderQuantity(Double totalOrderQuantity) {
		this.totalOrderQuantity = totalOrderQuantity;
	}
	/**
	 * @return the orderCode
	 */
	public String getOrderCode() {
		return orderCode;
	}
	/**
	 * @param orderCode the orderCode to set
	 */
	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}
	/**
	 * @return the orderDescription
	 */
	public String getOrderDescription() {
		return orderDescription;
	}
	/**
	 * @param orderDescription the orderDescription to set
	 */
	public void setOrderDescription(String orderDescription) {
		this.orderDescription = orderDescription;
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
		builder.append("PurchaseOrderDTO [accountId=");
		builder.append(accountId);
		builder.append(", orderCode=");
		builder.append(orderCode);
		builder.append(", orderDescription=");
		builder.append(orderDescription);
		builder.append(", orderNumberId=");
		builder.append(orderNumberId);
		builder.append(", totalOrderAmount=");
		builder.append(totalOrderAmount);
		builder.append(", totalOrderQuantity=");
		builder.append(totalOrderQuantity);
		builder.append(", userId=");
		builder.append(userId);
		builder.append("]");
		return builder.toString();
	}
}
