package com.smv.service.user.db.dbobject;

import java.sql.Timestamp;
import java.util.Date;

import com.smv.util.db.AbstractVersionedDatedDO;

public class AddressDO extends AbstractVersionedDatedDO {

	private String street;
	private String city;
	private String state;
	private String zipCode;
	private String country;
	private double latitude;
	private double longitude;
	private long recordTs;

	public AddressDO() {}

	/**
	 * @param id
	 * @param street
	 * @param city
	 * @param state
	 * @param zipCode
	 * @param country
	 * @param latitude
	 * @param longitude
	 * @param versionId
	 * @param updatedOn
	 * @param createdOn
	 */
	public AddressDO(
			Long id,
			String street, 
			String city, 
			String state, 
			String zipCode,
			String country, 
			double latitude, 
			double longitude, 
			Long versionId,
			Date updatedOn, 
			Timestamp createdOn) {
		super(id, 
			versionId,
			updatedOn, 
			createdOn);
		setStreet(street);
		setCity(city);
		setState(state);
		setZipCode(zipCode);
		setCountry(country);
		setLatitude(latitude);
		setLongitude(longitude);
	}

	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
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
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	public long getRecordTs() {
		return recordTs;
	}
	public void setRecordTs(long recordTs) {
		this.recordTs = recordTs;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCity() {
		return city;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}

	public String toString() {
		StringBuffer result = super.getString();

		result.append("street: " + this.getStreet() + '\n');
		result.append("city: " + this.getCity() + '\n');
		result.append("state: " + this.getState() + '\n');
		result.append("zipCode: " + this.getZipCode() + '\n');
		result.append("country: " + this.getCountry() + '\n');
		result.append("latitude: " + this.getLatitude() + '\n');
		result.append("longitude: " + this.getLongitude() + '\n');
		result.append("recordTs: " + this.getRecordTs() + '\n');
		
		return result.toString();
	}

}
