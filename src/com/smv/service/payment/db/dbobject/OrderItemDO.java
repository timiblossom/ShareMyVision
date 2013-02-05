package com.smv.service.payment.db.dbobject;

import com.smv.util.db.AbstractVersionedDatedDO;

/**
 * @author TriNguyen
 *
 */
public class OrderItemDO extends AbstractVersionedDatedDO {

	private Long orderId;
	private Double itemAmount;
	private Double itemQuantity;
	private Long itemCode;
	private String itemDescription;
	private Boolean isService;
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
	 * @return the itemAmount
	 */
	public Double getItemAmount() {
		return itemAmount;
	}
	/**
	 * @param itemAmount the itemAmount to set
	 */
	public void setItemAmount(Double itemAmount) {
		this.itemAmount = itemAmount;
	}
	/**
	 * @return the itemQuantity
	 */
	public Double getItemQuantity() {
		return itemQuantity;
	}
	/**
	 * @param itemQuantity the itemQuantity to set
	 */
	public void setItemQuantity(Double itemQuantity) {
		this.itemQuantity = itemQuantity;
	}
	/**
	 * @return the itemCode
	 */
	public Long getItemCode() {
		return itemCode;
	}
	/**
	 * @param itemCode the itemCode to set
	 */
	public void setItemCode(Long itemCode) {
		this.itemCode = itemCode;
	}
	/**
	 * @return the itemDescription
	 */
	public String getItemDescription() {
		return itemDescription;
	}
	/**
	 * @param itemDescription the itemDescription to set
	 */
	public void setItemDescription(String itemDescription) {
		this.itemDescription = itemDescription;
	}
	/**
	 * @return the isService
	 */
	public Boolean getIsService() {
		return isService;
	}
	/**
	 * @param isService the isService to set
	 */
	public void setIsService(Boolean isService) {
		this.isService = isService;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuffer builder = super.getString();
		builder.append("OrderItemDO [isService=");
		builder.append(isService);
		builder.append(", itemAmount=");
		builder.append(itemAmount);
		builder.append(", itemCode=");
		builder.append(itemCode);
		builder.append(", itemDescription=");
		builder.append(itemDescription);
		builder.append(", itemQuantity=");
		builder.append(itemQuantity);
		builder.append(", orderId=");
		builder.append(orderId);
		builder.append("]");
		return builder.toString();
	}

}
