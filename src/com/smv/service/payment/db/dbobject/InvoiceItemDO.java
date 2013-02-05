/**
 * 
 */
package com.smv.service.payment.db.dbobject;

import com.smv.util.db.AbstractVersionedDatedDO;

/**
 * @author TriNguyen
 *
 */
public class InvoiceItemDO extends AbstractVersionedDatedDO {

	private Long invoiceId;
	private Double itemAmount;
	private Double itemQuantity;
	private Long itemCode;
	private String itemDescription;
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
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuffer builder = super.getString();
		builder.append("InvoiceItemDO [invoiceId=");
		builder.append(invoiceId);
		builder.append(", itemAmount=");
		builder.append(itemAmount);
		builder.append(", itemCode=");
		builder.append(itemCode);
		builder.append(", itemDescription=");
		builder.append(itemDescription);
		builder.append(", itemQuantity=");
		builder.append(itemQuantity);
		builder.append("]");
		return builder.toString();
	}

}
