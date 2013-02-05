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
@XmlType(name = "CatalogKeyValuePairDTO")
public class CatalogKeyValuePairDTO extends KeyValuePairDTO {

	private CatalogDTO catalog;
	
	/**
	 * @return the catalog
	 */
	public CatalogDTO getCatalog() {
		return catalog;
	}

	/**
	 * @param catalog the catalog to set
	 */
	public void setCatalog(CatalogDTO catalog) {
		this.catalog = catalog;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("CatalogKeyValuePairDTO [catalog=");
		builder.append(catalog);
		builder.append(", toString()=");
		builder.append(super.toString());
		builder.append("]");
		return builder.toString();
	}

}
