/**
 * 
 */
package com.smv.common.bean;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * @author TriNguyen
 *
 */

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ProductKeyValuePairDTO")
public class ProductKeyValuePairDTO extends KeyValuePairDTO {

	private ProductDTO product;
	
	/**
	 * @return the product
	 */
	public ProductDTO getProduct() {
		return product;
	}

	/**
	 * @param product the product to set
	 */
	public void setProduct(ProductDTO product) {
		this.product = product;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ProductKeyValuePairDTO [product=");
		builder.append(product);
		builder.append(", toString()=");
		builder.append(super.toString());
		builder.append("]");
		return builder.toString();
	}

}
