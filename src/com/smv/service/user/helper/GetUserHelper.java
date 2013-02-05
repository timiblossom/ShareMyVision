
package com.zaptoe.user.helper;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zaptoe.common.bean.AccountDTO;
import com.zaptoe.common.bean.AddressDTO;
import com.zaptoe.common.bean.ContactDTO;
import com.zaptoe.common.bean.UserDTO;
import com.zaptoe.common.bean.UserExtraInfoDTO;
import com.zaptoe.common.exception.ZapToeErrorCode;
import com.zaptoe.common.exception.ZapToeServiceException;
import com.zaptoe.user.db.dao.AccountDAO;
import com.zaptoe.user.db.dao.AccountExtraInfoDAO;
import com.zaptoe.user.db.dao.AddressDAO;
import com.zaptoe.user.db.dao.CcInfoDAO;
import com.zaptoe.user.db.dao.ContactDAO;
import com.zaptoe.user.db.dao.RoleDAO;
import com.zaptoe.user.db.dao.TimeZoneDAO;
import com.zaptoe.user.db.dao.UserDAO;
import com.zaptoe.user.db.dao.UserExtraInfoDAO;
import com.zaptoe.user.db.dbo.AccountDO;
import com.zaptoe.user.db.dbo.AccountExtraInfoDO;
import com.zaptoe.user.db.dbo.AddressDO;
import com.zaptoe.user.db.dbo.CcInfoDO;
import com.zaptoe.user.db.dbo.ContactDO;
import com.zaptoe.user.db.dbo.RoleDO;
import com.zaptoe.user.db.dbo.TimeZoneDO;
import com.zaptoe.user.db.dbo.UserDO;
import com.zaptoe.user.db.dbo.UserExtraInfoDO;
import com.zaptoe.user.db.dbo.UserStatusDO;
import com.zaptoe.user.db.dbo.UserTypeDO;



public class GetUserHelper {

	private final static Logger LOGGER = LoggerFactory.getLogger(GetUserHelper.class);


	public static UserDTO getUser(AccountDTO account) throws IllegalArgumentException, ZapToeServiceException, Exception {
		try {
			ParameterChecker.checkAccountNonNull(account);
			return performGetUserWork(account);
		} catch (IllegalArgumentException iae) {
			String errorMessage = "getUser(AccountDTO account) failed. " +
									"\n " +
									"Account = " +
									account +
									"\n " +
									iae;
			LOGGER.error(errorMessage);
			
			ZapToeServiceException swe = new ZapToeServiceException();
			swe.setErrorCode(ZapToeErrorCode.USER_SERVICE_ILLEGAL_ARGUMENT);
			swe.setErrorMessage(ZapToeErrorCode.USER_SERVICE_ILLEGAL_ARGUMENT_MSG + errorMessage);
			throw swe;
		} catch (ZapToeServiceException swe) {
			String errorMessage = "getUser(AccountDTO account) failed. " +
									"\n " +
									"Account = " +
									account +
									"\n " +
									swe;
			LOGGER.error(errorMessage);
			throw swe;
		} catch (Exception exception) {
			String errorMessage = "getUser(AccountDTO account) failed. " +
									"\n " +
									"Account = " +
									account +
									"\n " +
									exception;
			LOGGER.error(errorMessage);

			ZapToeServiceException swe = new ZapToeServiceException();
			swe.setErrorCode(ZapToeErrorCode.USER_SERVICE_EXCEPTION);
			swe.setErrorMessage(ZapToeErrorCode.USER_SERVICE_EXCEPTION_MSG + errorMessage);
			throw swe;
		}
	}

	public static UserDTO getUser(UserDTO user) throws IllegalArgumentException, ZapToeServiceException, Exception {
		try {
			ParameterChecker.checkUserNonNull(user);
			ParameterChecker.checkUserEmail(user);
			return performGetUserWork(user);
		} catch (IllegalArgumentException iae) {
			String errorMessage = "getUser(UserDTO user) failed. " +
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
			String errorMessage = "getUser(UserDTO user) failed. " +
									"\n " +
									"User = " +
									user +
									"\n " +
									swe;
			LOGGER.error(errorMessage);
			throw swe;
		} catch (Exception exception) {
			String errorMessage = "getUser(UserDTO user) failed. " +
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

	public static UserDTO performGetUserWork(UserDTO user) throws IllegalArgumentException, Exception {
		try {
			// Retrieve User and related objects
			UserDO userDO = UserDAO.getUserByEmail(user.getUserEmail());

			// Check if UserDO was retrieved
			// Throw exception if there is no UserDO
			if (userDO == null) {
				String errorMessage = "There does not exist any user associated with this email." +
										"\n" + 
										"User = " + user;
				LOGGER.error(errorMessage);
				throw new ZapToeServiceException(ZapToeErrorCode.USER_SERVICE_NONE_EXISTENT_USER,
						ZapToeErrorCode.USER_SERVICE_NONE_EXISTENT_USER_MSG); 
			}

			// Get Account of User
			if (userDO.getAccountId() != null) {
				// Retrieve Account if it exists
				AccountDO accountDO = AccountDAO.getAccountById(userDO.getAccountId().longValue());

				if (accountDO != null) {
					// Create Account DTO
					AccountDTO account = new AccountDTO();

					// Set attributes of Account DTO from Account DO
					account.setAccountName(accountDO.getName());
					account.setAccountDescription(accountDO.getDescription());
					account.setAccountType(accountDO.getType());
					account.setAccountStatus(accountDO.getStatus());

					// Link Account to User
					user.setAccount(account);

					// Get Contact of Account
					if (accountDO.getContactId() != null) {
						// Retrieve Contact if it exists
						ContactDO contactOfAccountDO = ContactDAO.getContactById(accountDO.getContactId());

						if (contactOfAccountDO != null) {
							// Create Contact DTO
							ContactDTO contactOfAccount = new ContactDTO();

							// Set attributes of Contact DTO from Contact DO
							contactOfAccount.setContactFirstName(contactOfAccountDO.getFirstName());
							contactOfAccount.setContactLastName(contactOfAccountDO.getLastName());
							contactOfAccount.setContactAdditionalEmail(contactOfAccountDO.getAdditionalEmail());
							contactOfAccount.setContactWorkPhone(contactOfAccountDO.getWorkPhone());
							contactOfAccount.setContactMobilePhone(contactOfAccountDO.getMobilePhone());
							contactOfAccount.setContactHomePhone(contactOfAccountDO.getHomePhone());
							contactOfAccount.setContactAim(contactOfAccountDO.getAim());
							contactOfAccount.setContactYim(contactOfAccountDO.getYim());
							contactOfAccount.setContactSkype(contactOfAccountDO.getSkype());
							contactOfAccount.setContactFaceBook(contactOfAccountDO.getFacebook());
							contactOfAccount.setContactTwitter(contactOfAccountDO.getTwitter());

							// Link Contact to Account
							account.setContact(contactOfAccount);
							
							// Get Resident Address of Contact of Account
							if (contactOfAccountDO.getResidentAddressId()  != null) {
								// Retrieve Resident Address of Contact of Account if it exists
								AddressDO addressOfContactOfAccountDO = AddressDAO.getAddressById(contactOfAccountDO.getResidentAddressId());
								if (addressOfContactOfAccountDO != null) {
									// Create Resident Address DTO
									AddressDTO contactResidentAddress = new AddressDTO();

									// Set attributes of Resident Address DTO from Resident Address DO
									contactResidentAddress.setStreet(addressOfContactOfAccountDO.getStreet());
									contactResidentAddress.setCity(addressOfContactOfAccountDO.getCity());
									contactResidentAddress.setState(addressOfContactOfAccountDO.getState());
									contactResidentAddress.setZipCode(addressOfContactOfAccountDO.getZipCode());
									contactResidentAddress.setCountry(addressOfContactOfAccountDO.getCountry());
									contactResidentAddress.setLatitude(addressOfContactOfAccountDO.getLatitude());
									contactResidentAddress.setLongitude(addressOfContactOfAccountDO.getLongitude());

									// Link Resident Address of Contact of Account to Contact
									contactOfAccount.setContactResidentAddress(contactResidentAddress);
								}
							} // Get Resident Address of Contact of Account
						
							// Get Shipping Address of Contact of Account
							if (contactOfAccountDO.getShippingAddressId()  != null) {
								// Retrieve Shipping Address of Contact of Account if it exists
								AddressDO addressOfContactOfAccountDO = AddressDAO.getAddressById(contactOfAccountDO.getShippingAddressId());
								if (addressOfContactOfAccountDO != null) {
									// Create Shipping Address DTO
									AddressDTO contactShippingAddress = new AddressDTO();

									// Set attributes of Shipping Address DTO from Shipping Address DO
									contactShippingAddress.setStreet(addressOfContactOfAccountDO.getStreet());
									contactShippingAddress.setCity(addressOfContactOfAccountDO.getCity());
									contactShippingAddress.setState(addressOfContactOfAccountDO.getState());
									contactShippingAddress.setZipCode(addressOfContactOfAccountDO.getZipCode());
									contactShippingAddress.setCountry(addressOfContactOfAccountDO.getCountry());
									contactShippingAddress.setLatitude(addressOfContactOfAccountDO.getLatitude());
									contactShippingAddress.setLongitude(addressOfContactOfAccountDO.getLongitude());

									// Link Shipping Address of Contact of Account to Contact
									contactOfAccount.setContactShippingAddress(contactShippingAddress);
								}
							} // Get Shipping Address of Contact of Account
						
							// Get Billing Address of Contact of Account
							if (contactOfAccountDO.getBillingAddressId()  != null) {
								// Retrieve Billing Address of Contact of Account if it exists
								AddressDO addressOfContactOfAccountDO = AddressDAO.getAddressById(contactOfAccountDO.getBillingAddressId());
								if (addressOfContactOfAccountDO != null) {
									// Create Billing Address DTO
									AddressDTO contactBillingAddress = new AddressDTO();

									// Set attributes of Billing Address DTO from Billing Address DO
									contactBillingAddress.setStreet(addressOfContactOfAccountDO.getStreet());
									contactBillingAddress.setCity(addressOfContactOfAccountDO.getCity());
									contactBillingAddress.setState(addressOfContactOfAccountDO.getState());
									contactBillingAddress.setZipCode(addressOfContactOfAccountDO.getZipCode());
									contactBillingAddress.setCountry(addressOfContactOfAccountDO.getCountry());
									contactBillingAddress.setLatitude(addressOfContactOfAccountDO.getLatitude());
									contactBillingAddress.setLongitude(addressOfContactOfAccountDO.getLongitude());

									// Link Billing Address of Contact of Account to Contact
									contactOfAccount.setContactBillingAddress(contactBillingAddress);
								}
							} // Get Billing Address of Contact of Account
						
						}
					} // Get Contact of Account
					
					// Get CC Info of Account
					if (accountDO.getCcInfoId() != null) {
						CcInfoDO ccInfoDO = CcInfoDAO.getCcInfoById(accountDO.getCcInfoId());
						if (ccInfoDO != null) {
							account.setCreditCardLastFour(ccInfoDO.getLastFour());
							account.setCreditCardExpireMmyy(ccInfoDO.getExpireMmyy());
							account.setCreditCardType(ccInfoDO.getType());
							account.setCreditCardStatus(ccInfoDO.getStatus());

							/* Account
							 * 
							private AddressDTO creditCardAddress;
							 */
							// Get Address of CC Info
							if (ccInfoDO.getAddressId() != null) {
								// Retrieve Address of CC Info if it exists
								AddressDO addressOfCcInfoDO = AddressDAO.getAddressById(ccInfoDO.getAddressId());
								if (addressOfCcInfoDO != null) {
									// Create Address DTO of CC Info
									AddressDTO ccInfoAddress = new AddressDTO();

									// Set attributes of Address DTO of CC Info from Address DO of CC Info
									ccInfoAddress.setStreet(addressOfCcInfoDO.getStreet());
									ccInfoAddress.setCity(addressOfCcInfoDO.getCity());
									ccInfoAddress.setState(addressOfCcInfoDO.getState());
									ccInfoAddress.setZipCode(addressOfCcInfoDO.getZipCode());
									ccInfoAddress.setCountry(addressOfCcInfoDO.getCountry());
									ccInfoAddress.setLatitude(addressOfCcInfoDO.getLatitude());
									ccInfoAddress.setLongitude(addressOfCcInfoDO.getLongitude());

									// Link Address of CC Info to Account
									account.setCreditCardAddress(ccInfoAddress);
								}
							} // Get Address of CC Info
						}
					} // Get CC Info of Account

					// Get Extra Info of Account
					if (accountDO.getExtraInfo() != null) {
						AccountExtraInfoDO accountExtraInfoDO = AccountExtraInfoDAO.getAccountExtraInfoById(accountDO.getExtraInfo());
						if (accountExtraInfoDO != null) {
							account.setAccountExtraInfoKeyPair(accountExtraInfoDO.getKeyPair());
							account.setAccountExtraInfoValuePair(accountExtraInfoDO.getValuePair());
						}
					} // Get Extra Info of Account
				} // if (accountDO != null)
			} // Get Account of User
			
			// Get Contact of User
			if (userDO.getContactId() != null) {
				// Retrieve Contact if it exists
				ContactDO contactDO = ContactDAO.getContactById(userDO.getContactId().longValue());
				if (contactDO != null) {
					// Create Contact DTO
					ContactDTO contact = new ContactDTO();

					// Set attributes of Contact DTO from Contact DO
					contact.setContactFirstName(contactDO.getFirstName());
					contact.setContactLastName(contactDO.getLastName());
					contact.setContactAdditionalEmail(contactDO.getAdditionalEmail());
					contact.setContactWorkPhone(contactDO.getWorkPhone());
					contact.setContactMobilePhone(contactDO.getMobilePhone());
					contact.setContactHomePhone(contactDO.getHomePhone());
					contact.setContactAim(contactDO.getAim());
					contact.setContactYim(contactDO.getYim());
					contact.setContactSkype(contactDO.getSkype());
					contact.setContactFaceBook(contactDO.getFacebook());
					contact.setContactTwitter(contactDO.getTwitter());

					// Link Contact to User
					user.setContact(contact);
					
					// Get Resident Address User
					if (contactDO.getResidentAddressId() != null) {
						// Retrieve Resident Address of User if it exists
						AddressDO residentAddressDO = AddressDAO.getAddressById(contactDO.getResidentAddressId());
						if (residentAddressDO != null) {
							// Create Resident Address DTO
							AddressDTO contactResidentAddress = new AddressDTO();

							// Set attributes of Resident Address DTO from Resident Address DO
							contactResidentAddress.setStreet(residentAddressDO.getStreet());
							contactResidentAddress.setCity(residentAddressDO.getCity());
							contactResidentAddress.setState(residentAddressDO.getState());
							contactResidentAddress.setZipCode(residentAddressDO.getZipCode());
							contactResidentAddress.setCountry(residentAddressDO.getCountry());
							contactResidentAddress.setLatitude(residentAddressDO.getLatitude());
							contactResidentAddress.setLongitude(residentAddressDO.getLongitude());

							// Link Resident Address to User
							contact.setContactResidentAddress(contactResidentAddress);
						}
					} // Get Resident Address of User
				
					// Get Shipping Address of User
					if (contactDO.getShippingAddressId() != null) {
						// Retrieve Shipping Address of Contact of Account if it exists
						AddressDO shippingAddressDO = AddressDAO.getAddressById(contactDO.getShippingAddressId());
						if (shippingAddressDO != null) {
							// Create Shipping Address DTO
							AddressDTO contactShippingAddress = new AddressDTO();

							// Set attributes of Shipping Address DTO from Shipping Address DO
							contactShippingAddress.setStreet(shippingAddressDO.getStreet());
							contactShippingAddress.setCity(shippingAddressDO.getCity());
							contactShippingAddress.setState(shippingAddressDO.getState());
							contactShippingAddress.setZipCode(shippingAddressDO.getZipCode());
							contactShippingAddress.setCountry(shippingAddressDO.getCountry());
							contactShippingAddress.setLatitude(shippingAddressDO.getLatitude());
							contactShippingAddress.setLongitude(shippingAddressDO.getLongitude());

							// Link Shipping Address to User
							contact.setContactShippingAddress(contactShippingAddress);
						}
					} // Get Shipping Address of User
				
					// Get Billing Address of Contact of Account
					if (contactDO.getBillingAddressId() != null) {
						// Retrieve Billing Address of Contact of Account if it exists
						AddressDO billingAddressDO = AddressDAO.getAddressById(contactDO.getBillingAddressId());
						if (billingAddressDO != null) {
							// Create Billing Address DTO
							AddressDTO contactBillingAddress = new AddressDTO();

							// Set attributes of Billing Address DTO from Billing Address DO
							contactBillingAddress.setStreet(billingAddressDO.getStreet());
							contactBillingAddress.setCity(billingAddressDO.getCity());
							contactBillingAddress.setState(billingAddressDO.getState());
							contactBillingAddress.setZipCode(billingAddressDO.getZipCode());
							contactBillingAddress.setCountry(billingAddressDO.getCountry());
							contactBillingAddress.setLatitude(billingAddressDO.getLatitude());
							contactBillingAddress.setLongitude(billingAddressDO.getLongitude());

							// Link Billing Address to User
							contact.setContactBillingAddress(contactBillingAddress);
						}
					} // Get Billing Address of User
				}
			} // Get Contact of User
			
			// Get User Extra Info of User
			if (userDO.getUserExtraInfoId() != null) {
				UserExtraInfoDO userExtraInfoDO = UserExtraInfoDAO.getUserExtraInfoById(userDO.getUserExtraInfoId().longValue());
				if (userExtraInfoDO != null) {
					// Create User Extra Info DTO
					UserExtraInfoDTO userExtraInfo = new UserExtraInfoDTO();

					// Set attributes of User Extra Info DTO from User Extra Info DO
					userExtraInfo.setUserExtraInfoIndustry(userExtraInfoDO.getIndustry());
					userExtraInfo.setUserExtraInfoCompany(userExtraInfoDO.getCompany());
					userExtraInfo.setUserExtraInfoCompanySize(userExtraInfoDO.getCompanySize());
					userExtraInfo.setUserExtraInfoZipCode(userExtraInfoDO.getZipCode());
					userExtraInfo.setUserExtraInfoJobTitle(userExtraInfoDO.getJobTitle());
					userExtraInfo.setUserExtraInfoMobileDeviceModel(userExtraInfoDO.getMobileDeviceModel());
					userExtraInfo.setUserExtraInfoMobileManufacturer(userExtraInfoDO.getMobileManufacturer());
					
					// Time Zone of User Extra Info
					if (userExtraInfoDO.getTimeZoneId() != null) {
						TimeZoneDO timeZoneDO = TimeZoneDAO.getTimeZoneById(userExtraInfoDO.getTimeZoneId().longValue());
						if (timeZoneDO != null) {
							userExtraInfo.setTimeZone(timeZoneDO.getTimeZone());
							userExtraInfo.setTimeZoneLabel(timeZoneDO.getLabel());
						}
					} // Time Zone of User Extra Info

					
					// Link User Extra Info to User
					user.setUserExtraInfo(userExtraInfo);
				}
			} // Get Extra Info of Account
			
			// Get Role of User
			if (userDO.getRoleId() != null) {
				RoleDO roleDO = RoleDAO.getRoleById(userDO.getRoleId().longValue());
				if (roleDO != null) {
					user.setRoleName(roleDO.getName());
					user.setRoleDescription(roleDO.getDescription());
				}
			} // Get Role of User
			
			propagateUserDOPropertiesToUserDTO(userDO, user);

			return user;

		} catch (Exception exception) {
			LOGGER.error("Error in performing performGetUserWork(UserDTO user)" +
					";\n" +
					"User = " + 
					user +
					";\n" + 
					"Exception = " + 
					exception);
			throw exception;
		}
	}

	protected static void propagateUserDOPropertiesToUserDTO(UserDO userDO, UserDTO user) {
		// Set attributes of UserDTO
		user.setUserActivationCode(userDO.getActivationCode());
		user.setUserDisplayName(userDO.getDisplayName());
		user.setUserEmail(userDO.getEmail());
		user.setUserLanguage(userDO.getLanguage());
		user.setUserPassword(userDO.getPassword());
		user.setUserPasswordResetUserCode(userDO.getPasswordResetCode());
		user.setUserSalt(userDO.getSalt());
		if (userDO.getStatusId() != null) {
			user.setUserStatus(UserStatusDO.getUserStatus(userDO.getStatusId()).getName());
		}
		if (userDO.getTypeId() != null) {
			user.setUserType(UserTypeDO.getUserType(userDO.getTypeId()).getName());
		}
		user.setUserId(userDO.getId());
	}
	
	public static UserDTO performGetUserWork(AccountDTO account) throws IllegalArgumentException, Exception {
		try {
			// Retrieve user and related objects
			UserDO userDO = UserDAO.getUserByAccountId(account.getId());
			
			// Check if UserDO was retrieved
			// Throw exception if there is no UserDO
			if (userDO == null) {
				String errorMessage = "There does not exist any user associated with this account. Account = " + account;
				throw new IllegalArgumentException(errorMessage); 
			}

			// Create DTOs
			UserDTO user = new UserDTO();
			ContactDTO contact = new ContactDTO();
			AddressDTO residentAddress = new AddressDTO();
			AddressDTO billingAddress = new AddressDTO();
			AddressDTO shippingAddress = new AddressDTO();
			UserExtraInfoDTO userExtraInfo = new UserExtraInfoDTO();
			
			ContactDO contactDO = ContactDAO.getContactById(userDO.getContactId().longValue());
			if (contactDO != null) {
				AddressDO residentAddressDO = AddressDAO.getAddressById(contactDO.getResidentAddressId());
				if (residentAddressDO != null) {
					// Set attributes of residentAddress
					residentAddress.setCity(residentAddressDO.getCity());
					residentAddress.setLatitude(residentAddressDO.getLatitude());
					residentAddress.setLongitude(residentAddressDO.getLongitude());
					residentAddress.setState(residentAddressDO.getState());
					residentAddress.setZipCode(residentAddressDO.getZipCode());
				}
				
				AddressDO billingAddressDO = AddressDAO.getAddressById(contactDO.getBillingAddressId());
				if (billingAddressDO != null) {
					// Set attributes of billingAddress
					billingAddress.setCity(billingAddressDO.getCity());
					billingAddress.setLatitude(billingAddressDO.getLatitude());
					billingAddress.setLongitude(billingAddressDO.getLongitude());
					billingAddress.setState(billingAddressDO.getState());
					billingAddress.setZipCode(billingAddressDO.getZipCode());
				}
				
				AddressDO shippingAddressDO = AddressDAO.getAddressById(contactDO.getShippingAddressId());
				if (shippingAddressDO != null) {
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
			}
			
			if (userDO.getUserExtraInfoId() != null) {
				UserExtraInfoDO userExtraInfoDO = UserExtraInfoDAO.getUserExtraInfoById(userDO.getUserExtraInfoId().longValue());

				// Set attributes of UserExtraInfoDTO
				userExtraInfo.setUserExtraInfoCompany(userExtraInfoDO.getCompany());
				userExtraInfo.setUserExtraInfoCompanySize(userExtraInfoDO.getCompanySize());
				userExtraInfo.setUserExtraInfoIndustry(userExtraInfoDO.getIndustry());
				userExtraInfo.setUserExtraInfoJobTitle(userExtraInfoDO.getJobTitle());
				userExtraInfo.setUserExtraInfoMobileDeviceModel(userExtraInfoDO.getMobileDeviceModel());
				userExtraInfo.setUserExtraInfoMobileManufacturer(userExtraInfoDO.getMobileManufacturer());
				userExtraInfo.setUserExtraInfoTitle(userExtraInfoDO.getTitle());
				userExtraInfo.setUserExtraInfoZipCode(userExtraInfoDO.getZipCode());
			}

			RoleDO roleDO = null;
			if (userDO.getRoleId() != null) {
				roleDO = RoleDAO.getRoleById(userDO.getRoleId().longValue());
			}


			// Set attributes of UserDTO
			propagateUserDOPropertiesToUserDTO(userDO, user);
			user.setContact(contact);
			if (roleDO != null) {
				user.setRoleDescription(roleDO.getDescription());
				user.setRoleName(roleDO.getName());
			}
			user.setUserExtraInfo(userExtraInfo);
			
			return user;
		} catch (Exception exception) {
			LOGGER.error("Error in performing performGetUserWork(AccountDTO account); Account = " + account + "; Exception = " + exception);
			throw exception;
		}
	}

}
