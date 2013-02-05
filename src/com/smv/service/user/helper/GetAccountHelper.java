
package com.zaptoe.user.helper;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zaptoe.common.bean.AccountDTO;
import com.zaptoe.common.bean.AddressDTO;
import com.zaptoe.common.bean.ContactDTO;
import com.zaptoe.common.bean.UserDTO;
import com.zaptoe.common.exception.ZapToeErrorCode;
import com.zaptoe.common.exception.ZapToeServiceException;
import com.zaptoe.user.db.dao.AccountDAO;
import com.zaptoe.user.db.dao.AddressDAO;
import com.zaptoe.user.db.dao.CcInfoDAO;
import com.zaptoe.user.db.dao.ContactDAO;
import com.zaptoe.user.db.dao.UserDAO;
import com.zaptoe.user.db.dbo.AccountDO;
import com.zaptoe.user.db.dbo.AddressDO;
import com.zaptoe.user.db.dbo.CcInfoDO;
import com.zaptoe.user.db.dbo.ContactDO;
import com.zaptoe.user.db.dbo.UserDO;


public class GetAccountHelper {

	private final static Logger LOGGER = LoggerFactory.getLogger(GetAccountHelper.class);
	

	public static AccountDTO getAccount(UserDTO user) throws IllegalArgumentException, ZapToeServiceException, Exception {
		try {
			ParameterChecker.checkUserNonNull(user);
			ParameterChecker.checkUserEmail(user);
			ParameterChecker.checkUserPassword(user);
			return performGetAccountWork(user);
		} catch (IllegalArgumentException iae) {
			String errorMessage = "getAccount(user) failed. " +
									"\n " +
									"User = " +
									user +
									"\n " +
									iae;
			LOGGER.error(errorMessage);
			ZapToeServiceException swe = new ZapToeServiceException();
			swe.setErrorCode(ZapToeErrorCode.USER_SERVICE_ILLEGAL_ARGUMENT);
			swe.setErrorMessage(ZapToeErrorCode.USER_SERVICE_ILLEGAL_ARGUMENT_MSG + errorMessage);
			throw swe;
		} catch (ZapToeServiceException swe) {
			String errorMessage = "getAccount(user) failed. " +
									"\n " +
									"User = " +
									user +
									"\n " +
									swe;
			LOGGER.error(errorMessage);
			throw swe;
		} catch (Exception exception) {
			String errorMessage = "getAccount(user) failed. " +
									"\n " +
									"User = " +
									user +
									"\n " +
									exception;
			LOGGER.error(errorMessage);

			ZapToeServiceException swe = new ZapToeServiceException();
			swe.setErrorCode(ZapToeErrorCode.USER_SERVICE_EXCEPTION);
			swe.setErrorMessage(ZapToeErrorCode.USER_SERVICE_EXCEPTION_MSG + errorMessage);
			throw swe;
		}
	}

	public static AccountDTO performGetAccountWork(UserDTO user) throws Exception {
		try {
			// Create DTOs
			AccountDTO account = new AccountDTO();
			ContactDTO contact = new ContactDTO();
			AddressDTO residentAddress = new AddressDTO();
			AddressDTO billingAddress = new AddressDTO();
			AddressDTO shippingAddress = new AddressDTO();
			AddressDTO creditCardAddress = new AddressDTO();
			
			// Retrieve user and related objects
			UserDO userDO = UserDAO.getUserByEmail(user.getUserEmail());
			
			// Check if UserDO was retrieved
			// Throw exception if there is no UserDO
			if (userDO == null) {
				String errorMessage = "User does not exist. User = " + user;
				throw new IllegalArgumentException(errorMessage); 
			}
			
			// Get related Data Objects
			AccountDO accountDO = AccountDAO.getAccountById(userDO.getAccountId().longValue());
			if (accountDO != null) {
				ContactDO contactDO = ContactDAO.getContactById(accountDO.getContactId());
				if (contactDO != null) {
					AddressDO residentAddressDO = AddressDAO.getAddressById(contactDO.getResidentAddressId());
					AddressDO billingAddressDO = AddressDAO.getAddressById(contactDO.getBillingAddressId());
					AddressDO shippingAddressDO = AddressDAO.getAddressById(contactDO.getShippingAddressId());

					// Set attributes of residentAddress
					residentAddress.setCity(residentAddressDO.getCity());
					residentAddress.setLatitude(residentAddressDO.getLatitude());
					residentAddress.setLongitude(residentAddressDO.getLongitude());
					residentAddress.setState(residentAddressDO.getState());
					residentAddress.setZipCode(residentAddressDO.getZipCode());
					
					// Set attributes of billingAddress
					billingAddress.setCity(billingAddressDO.getCity());
					billingAddress.setLatitude(billingAddressDO.getLatitude());
					billingAddress.setLongitude(billingAddressDO.getLongitude());
					billingAddress.setState(billingAddressDO.getState());
					billingAddress.setZipCode(billingAddressDO.getZipCode());
					
					// Set attributes of shippingAddress
					shippingAddress.setCity(shippingAddressDO.getCity());
					shippingAddress.setLatitude(shippingAddressDO.getLatitude());
					shippingAddress.setLongitude(shippingAddressDO.getLongitude());
					shippingAddress.setState(shippingAddressDO.getState());
					shippingAddress.setZipCode(shippingAddressDO.getZipCode());
				}

				// Set attributes of ContactDTO
				contact.setContactAdditionalEmail(contactDO.getAdditionalEmail());
				contact.setContactAim(contactDO.getAim());
				contact.setContactBillingAddress(billingAddress);
				contact.setContactFaceBook(contactDO.getFacebook());
				contact.setContactFirstName(contactDO.getFirstName());
				contact.setContactHomePhone(contactDO.getHomePhone());
				contact.setContactLastName(contactDO.getLastName());
				contact.setContactMobilePhone(contactDO.getMobilePhone());
				contact.setContactResidentAddress(residentAddress);
				contact.setContactShippingAddress(shippingAddress);
				contact.setContactSkype(contactDO.getSkype());
				contact.setContactTwitter(contactDO.getTwitter());
				contact.setContactWorkPhone(contactDO.getWorkPhone());
				contact.setContactYim(contactDO.getYim());
				
				CcInfoDO ccInfoDO = CcInfoDAO.getCcInfoById(accountDO.getCcInfoId());
				if (ccInfoDO != null) {
					AddressDO creditCardAddressDO = AddressDAO.getAddressById(ccInfoDO.getAddressId());

					// Set attributes of creditCardAddress
					creditCardAddress.setCity(creditCardAddressDO.getCity());
					creditCardAddress.setLatitude(creditCardAddressDO.getLatitude());
					creditCardAddress.setLongitude(creditCardAddressDO.getLongitude());
					creditCardAddress.setState(creditCardAddressDO.getState());
					creditCardAddress.setStreet(creditCardAddressDO.getStreet());
					creditCardAddress.setZipCode(creditCardAddressDO.getZipCode());
				}

				// Set attributes of AccountDTO
				account.setAccountDescription(accountDO.getDescription());
				account.setAccountName(accountDO.getName());
				account.setAccountStatus(accountDO.getStatus());
				account.setAccountType(accountDO.getType());
				account.setContact(contact);
				account.setCreditCardAddress(creditCardAddress);
				account.setCreditCardExpireMmyy(ccInfoDO.getExpireMmyy());
				account.setCreditCardLastFour(ccInfoDO.getLastFour());
				account.setCreditCardStatus(ccInfoDO.getStatus());
				account.setCreditCardType(ccInfoDO.getType());
				account.setId(accountDO.getId());
			}
			
			return account;
		} catch (Exception exception) {
			LOGGER.error("Error in performing performGetAccountWork(); User = " + user + "; Exception = " + exception);
			throw exception;
		}
	}
}
