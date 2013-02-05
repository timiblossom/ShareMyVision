/**
 * 
 */
package com.smv.service.payment.db.dbobject;

import com.smv.util.db.AbstractVersionedDatedDO;

/**
 * @author TriNguyen
 *
 */
public class PurchaseOrderDO extends AbstractVersionedDatedDO {

	private String orderNumberId;
	private Double totalOrderAmount;
	private Double totalOrderQuantity;
	private String orderCode;
	private String orderDescription;
	private Long userId;
	private Long accountId;
	public PurchaseOrderDO() {
		super();
	}
	/**
	 * @param orderNumberId
	 * @param totalOrderAmount
	 * @param totalOrderQuantity
	 * @param orderCode
	 * @param orderDescription
	 * @param userId
	 * @param accountId
	 */
	public PurchaseOrderDO(Long id, 
			String orderNumberId, 
			Double totalOrderAmount,
			Double totalOrderQuantity, 
			String orderCode,
			String orderDescription, 
			Long userId, 
			Long accountId) {
		super(id);
		this.orderNumberId = orderNumberId;
		this.totalOrderAmount = totalOrderAmount;
		this.totalOrderQuantity = totalOrderQuantity;
		this.orderCode = orderCode;
		this.orderDescription = orderDescription;
		this.userId = userId;
		this.accountId = accountId;
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
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuffer builder = super.getString();
		builder.append("PurchaseOrderDO [accountId=");
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
