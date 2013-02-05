package com.smv.common.bean;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * @author Tri Nguyen
 * @author Minh Do
 */

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ContactDTO")
public class ContactDTO implements Serializable {
	
	private String action;
	private String contactFirstName;
	private String contactLastName;
	private String contactAdditionalEmail;
	private String contactWorkPhone;
	private String contactMobilePhone;
    private String contactHomePhone;
    private String contactAim;
    private String contactYim;
    private String contactSkype;
    private String contactFaceBook;
    private String contactTwitter;
    private AddressDTO contactResidentAddress;
    private AddressDTO contactShippingAddress;
    private AddressDTO contactBillingAddress;
	private Long id;

	public String getAction() {
		return action;
	}	
	
	public void setAction(String action) {
		this.action = action;
	}

	public String getContactFirstName() {
		return contactFirstName;
	}

	public void setContactFirstName(String contactFirstName) {
		this.contactFirstName = contactFirstName;
	}

	public String getContactLastName() {
		return contactLastName;
	}

	public void setContactLastName(String contactLastName) {
		this.contactLastName = contactLastName;
	}

	public String getContactAdditionalEmail() {
		return contactAdditionalEmail;
	}

	public void setContactAdditionalEmail(String contactAdditionalEmail) {
		this.contactAdditionalEmail = contactAdditionalEmail;
	}

	public String getContactWorkPhone() {
		return contactWorkPhone;
	}

	public void setContactWorkPhone(String contactWorkPhone) {
		this.contactWorkPhone = contactWorkPhone;
	}

	public String getContactMobilePhone() {
		return contactMobilePhone;
	}

	public void setContactMobilePhone(String contactMobilePhone) {
		this.contactMobilePhone = contactMobilePhone;
	}

	public String getContactHomePhone() {
		return contactHomePhone;
	}

	public void setContactHomePhone(String contactHomePhone) {
		this.contactHomePhone = contactHomePhone;
	}

	public String getContactAim() {
		return contactAim;
	}

	public void setContactAim(String contactAim) {
		this.contactAim = contactAim;
	}

	public String getContactYim() {
		return contactYim;
	}

	public void setContactYim(String contactYim) {
		this.contactYim = contactYim;
	}

	public String getContactSkype() {
		return contactSkype;
	}

	public void setContactSkype(String contactSkype) {
		this.contactSkype = contactSkype;
	}

	public String getContactFaceBook() {
		return contactFaceBook;
	}

	public void setContactFaceBook(String contactFaceBook) {
		this.contactFaceBook = contactFaceBook;
	}

	public String getContactTwitter() {
		return contactTwitter;
	}

	public void setContactTwitter(String contactTwitter) {
		this.contactTwitter = contactTwitter;
	}

	public AddressDTO getContactResidentAddress() {
		return contactResidentAddress;
	}

	public void setContactResidentAddress(AddressDTO contactResidentAddress) {
		this.contactResidentAddress = contactResidentAddress;
	}

	public AddressDTO getContactShippingAddress() {
		return contactShippingAddress;
	}

	public void setContactShippingAddress(AddressDTO contactShippingAddress) {
		this.contactShippingAddress = contactShippingAddress;
	}

	public AddressDTO getContactBillingAddress() {
		return contactBillingAddress;
	}

	public void setContactBillingAddress(AddressDTO contactBillingAddress) {
		this.contactBillingAddress = contactBillingAddress;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ContactDTO [action=");
		builder.append(action);
		builder.append(", contactAdditionalEmail=");
		builder.append(contactAdditionalEmail);
		builder.append(", contactAim=");
		builder.append(contactAim);
		builder.append(", contactBillingAddress=");
		builder.append(contactBillingAddress);
		builder.append(", contactFaceBook=");
		builder.append(contactFaceBook);
		builder.append(", contactFirstName=");
		builder.append(contactFirstName);
		builder.append(", contactHomePhone=");
		builder.append(contactHomePhone);
		builder.append(", contactLastName=");
		builder.append(contactLastName);
		builder.append(", contactMobilePhone=");
		builder.append(contactMobilePhone);
		builder.append(", contactResidentAddress=");
		builder.append(contactResidentAddress);
		builder.append(", contactShippingAddress=");
		builder.append(contactShippingAddress);
		builder.append(", contactSkype=");
		builder.append(contactSkype);
		builder.append(", contactTwitter=");
		builder.append(contactTwitter);
		builder.append(", contactWorkPhone=");
		builder.append(contactWorkPhone);
		builder.append(", contactYim=");
		builder.append(contactYim);
		builder.append(", id=");
		builder.append(id);
		builder.append("]");
		return builder.toString();
	}
    
}
