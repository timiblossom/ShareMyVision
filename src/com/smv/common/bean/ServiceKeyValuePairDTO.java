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
@XmlType(name = "ServiceKeyValuePairDTO")
public class ServiceKeyValuePairDTO extends KeyValuePairDTO {

	private ServiceDTO service;

	/**
	 * @return the service
	 */
	public ServiceDTO getService() {
		return service;
	}

	/**
	 * @param service the service to set
	 */
	public void setService(ServiceDTO service) {
		this.service = service;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ServiceKeyValuePairDTO [service=");
		builder.append(service);
		builder.append(", toString()=");
		builder.append(super.toString());
		builder.append("]");
		return builder.toString();
	}
	
}
