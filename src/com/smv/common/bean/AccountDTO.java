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
@XmlType(name = "AccountDTO")
public class AccountDTO implements Serializable {

	private String action;
	private String accountName;
	private String accountDescription;
	private String accountType;
	private String accountStatus;
	private ContactDTO contact;
	private String creditCardLastFour;
	private String creditCardExpireMmyy;
	private String creditCardType;
	private String creditCardStatus;
	private AddressDTO creditCardAddress;
	private String accountExtraInfoKeyPair;
	private String accountExtraInfoValuePair;
	private Long id;
	
	public String getAction() {
		return action;
	}	
	
	public void setAction(String action) {
		this.action = action;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getAccountDescription() {
		return accountDescription;
	}

	public void setAccountDescription(String accountDescription) {
		this.accountDescription = accountDescription;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public String getAccountStatus() {
		return accountStatus;
	}

	public void setAccountStatus(String accountStatus) {
		this.accountStatus = accountStatus;
	}

	public ContactDTO getContact() {
		return contact;
	}

	public void setContact(ContactDTO contact) {
		this.contact = contact;
	}

	public String getCreditCardLastFour() {
		return creditCardLastFour;
	}

	public void setCreditCardLastFour(String creditCardLastFour) {
		this.creditCardLastFour = creditCardLastFour;
	}

	public String getCreditCardExpireMmyy() {
		return creditCardExpireMmyy;
	}

	public void setCreditCardExpireMmyy(String creditCardExpireMmyy) {
		this.creditCardExpireMmyy = creditCardExpireMmyy;
	}

	public String getCreditCardType() {
		return creditCardType;
	}

	public void setCreditCardType(String creditCardType) {
		this.creditCardType = creditCardType;
	}

	public String getCreditCardStatus() {
		return creditCardStatus;
	}

	public void setCreditCardStatus(String creditCardStatus) {
		this.creditCardStatus = creditCardStatus;
	}

	public AddressDTO getCreditCardAddress() {
		return creditCardAddress;
	}

	public void setCreditCardAddress(AddressDTO creditCardAddress) {
		this.creditCardAddress = creditCardAddress;
	}

	public String getAccountExtraInfoKeyPair() {
		return accountExtraInfoKeyPair;
	}

	public void setAccountExtraInfoKeyPair(String accountExtraInfoKeyPair) {
		this.accountExtraInfoKeyPair = accountExtraInfoKeyPair;
	}

	public String getAccountExtraInfoValuePair() {
		return accountExtraInfoValuePair;
	}

	public void setAccountExtraInfoValuePair(String accountExtraInfoValuePair) {
		this.accountExtraInfoValuePair = accountExtraInfoValuePair;
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
		builder.append("AccountDTO [accountDescription=");
		builder.append(accountDescription);
		builder.append(", accountExtraInfoKeyPair=");
		builder.append(accountExtraInfoKeyPair);
		builder.append(", accountExtraInfoValuePair=");
		builder.append(accountExtraInfoValuePair);
		builder.append(", accountName=");
		builder.append(accountName);
		builder.append(", accountStatus=");
		builder.append(accountStatus);
		builder.append(", accountType=");
		builder.append(accountType);
		builder.append(", action=");
		builder.append(action);
		builder.append(", contact=");
		builder.append(contact);
		builder.append(", creditCardAddress=");
		builder.append(creditCardAddress);
		builder.append(", creditCardExpireMmyy=");
		builder.append(creditCardExpireMmyy);
		builder.append(", creditCardLastFour=");
		builder.append(creditCardLastFour);
		builder.append(", creditCardStatus=");
		builder.append(creditCardStatus);
		builder.append(", creditCardType=");
		builder.append(creditCardType);
		builder.append(", id=");
		builder.append(id);
		builder.append("]");
		return builder.toString();
	}

}
