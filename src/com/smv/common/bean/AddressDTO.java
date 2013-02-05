package com.smv.common.bean;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * @author Minh Do
 * 03/2010
 */

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AddressDTO")
public class AddressDTO {
	private Long id;
	private String street;
	private String city;
	private String state;
	private String zipCode;
	private String country;
	private Double latitude;
	private Double longitude;

	public AddressDTO() {};
	
	public AddressDTO(
			Long id, 
			String street,
			String city,
			String state,
			String zipCode,
			String country,
			double latitude,
			double longitude
			) {
		setId(id);
		setStreet(street);
		setCity(city);
		setState(state);
		setZipCode(zipCode);
		setCountry(country);
		setLatitude(latitude);
		setLongitude(longitude);
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public Double getLatitude() {
		return latitude;
	}
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}
	public Double getLongitude() {
		return longitude;
	}
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

    /* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("AddressDTO [city=");
		builder.append(city);
		builder.append(", country=");
		builder.append(country);
		builder.append(", id=");
		builder.append(id);
		builder.append(", latitude=");
		builder.append(latitude);
		builder.append(", longitude=");
		builder.append(longitude);
		builder.append(", state=");
		builder.append(state);
		builder.append(", street=");
		builder.append(street);
		builder.append(", zipCode=");
		builder.append(zipCode);
		builder.append("]");
		return builder.toString();
	}
}
