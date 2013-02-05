
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


public class GetFullUserInformationHelper {

	private final static Logger LOGGER = LoggerFactory.getLogger(GetFullUserInformationHelper.class);


	public static UserDTO getFullUserInformation(String emailAddress) throws ZapToeServiceException {
		try {
			ParameterChecker.checkEmailAddress(emailAddress);
			return performGetFullUserInformationWork(emailAddress);
		} catch (IllegalArgumentException iae) {
			String errorMessage = "getFullUserInformation(String emailAddress) failed. " +
									"\n " +
									"EmailAddress = " +
									emailAddress +
									"\n " +
									iae;
			LOGGER.error(errorMessage);

			ZapToeServiceException swe = new ZapToeServiceException();
			swe.setErrorCode(ZapToeErrorCode.USER_SERVICE_ILLEGAL_ARGUMENT);
			swe.setErrorMessage(ZapToeErrorCode.USER_SERVICE_ILLEGAL_ARGUMENT_MSG + errorMessage);
			throw swe;
		} catch (ZapToeServiceException swe) {
			String errorMessage = "getFullUserInformation(String emailAddress) failed. " +
									"\n " +
									"EmailAddress = " +
									emailAddress +
									"\n " +
									swe;
			LOGGER.error(errorMessage);
			throw swe;
		} catch (Exception exception) {
			String errorMessage = "getFullUserInformation(String emailAddress) failed. " +
									"\n " +
									"EmailAddress = " +
									emailAddress +
									"\n " +
									exception;
			LOGGER.error(errorMessage);

			ZapToeServiceException swe = new ZapToeServiceException();
			swe.setErrorCode(ZapToeErrorCode.USER_SERVICE_EXCEPTION);
			swe.setErrorMessage(ZapToeErrorCode.USER_SERVICE_EXCEPTION_MSG + errorMessage);
			throw swe;
		}
	}

	public static UserDTO performGetFullUserInformationWork(String emailAddress) throws ZapToeServiceException {
		try {
			// Declare result
			UserDTO user = null;

			// Retrieve User and related objects
			UserDO userDO = UserDAO.getUserByEmail(emailAddress);

			// Return result immediately if cannot retrieve any user associated with this email address.
			if (userDO == null) {
				return user;
			}
			
			// Construct User DTO
			user = new UserDTO();

			// Propagate User DO properties to User DTO
			propagateUserDOPropertiesToUserDTO(userDO, user);

			// Propagate Account of User DO to User DTO
			propagateAccountToUserDTO(userDO, user);
			
			// Propagate Contact of User DO to User DTO
			propagateContactToUserDTO(userDO, user);
			
			// Propagate User Extra Info of User DO to User DTO
			propagateExtraInfoToUser(userDO, user);
			
			// Propagate Role of User DO to User DTO
			propagateRoleToUser(userDO, user);
			
			// Return result
			return user;

		} catch (Exception exception) {
			String errorMessage = "performGetFullUserInformationWork(String emailAddress) failed. " +
									"\n " +
									"EmailAddress = " +
									emailAddress +
									"\n " +
									exception;
			LOGGER.error(errorMessage);
			
			ZapToeServiceException swe = new ZapToeServiceException();
			swe.setErrorCode(ZapToeErrorCode.USER_SERVICE_EXCEPTION);
			swe.setErrorMessage(ZapToeErrorCode.USER_SERVICE_EXCEPTION_MSG + errorMessage);
			throw swe;
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
	
	protected static void propagateAccountToUserDTO(UserDO userDO, UserDTO user) {
		// Get Account of User
		if (userDO.getAccountId() != null) {
			// Retrieve Account if it exists
			AccountDO accountDO = AccountDAO.getAccountById(userDO.getAccountId().longValue());

			if (accountDO != null) {
				// Create Account DTO
				AccountDTO account = new AccountDTO();
				
				// Propagate Account DO Properties to Account DTO
				propagateAccountDOPropertiesToAccountDTO(accountDO, account);

				// Link Account to User
				user.setAccount(account);

				// Propagate Contact to Account
				propagateContactToAccountDTO(accountDO, account);
				
				// Propagate CC Info to Account
				propagateCcInfoToAccountDTO(accountDO, account);

				// Propagate Extra Info to Account
				propagateExtraInfoToAccount(accountDO, account);
			} // if (accountDO != null)
		} // Get Account of User
	} // propagateAccountToUserDTO()
	
	protected static void propagateAccountDOPropertiesToAccountDTO(AccountDO accountDO, AccountDTO account) {
		// Set attributes of Account DTO from Account DO
		account.setAccountName(accountDO.getName());
		account.setAccountDescription(accountDO.getDescription());
		account.setAccountType(accountDO.getType());
		account.setAccountStatus(accountDO.getStatus());
	}
	
	protected static void propagateContactToAccountDTO(AccountDO accountDO, AccountDTO account) {
		// Get Contact of Account
		if (accountDO.getContactId() != null) {
			// Retrieve Contact if it exists
			ContactDO contactOfAccountDO = ContactDAO.getContactById(accountDO.getContactId());

			// Contact of Account does exist
			if (contactOfAccountDO != null) {
				// Create Contact DTO
				ContactDTO contactOfAccount = new ContactDTO();

				// Propagate Contact DO Properties to Contact DTO
				propagateContactDOPropertiesToContactDTO(contactOfAccountDO, contactOfAccount);

				// Link Contact to Account
				account.setContact(contactOfAccount);
				
				// Propagate Resident Address to Contact of Account
				AddressDTO contactResidentAddress = getAddressById(contactOfAccountDO.getResidentAddressId());
				
				// Link Resident Address of Contact of Account to Contact
				contactOfAccount.setContactResidentAddress(contactResidentAddress);

				// Propagate Resident Address to Contact of Account
				AddressDTO contactShippingAddress = getAddressById(contactOfAccountDO.getShippingAddressId());
				
				// Link Shipping Address of Contact of Account to Contact
				contactOfAccount.setContactShippingAddress(contactShippingAddress);

				// Propagate Resident Address to Contact of Account
				AddressDTO contactBillingAddress = getAddressById(contactOfAccountDO.getBillingAddressId());
				
				// Link Billing Address of Contact of Account to Contact
				contactOfAccount.setContactBillingAddress(contactBillingAddress);
			} // Contact of Account does exist -- if (contactOfAccountDO != null)
		} // Get Contact of Account -- if (accountDO.getContactId() != null)

		// Propagate CC Info to Account DTO
		propagateCcInfoToAccountDTO(accountDO, account);

	} // propagateContactToAccountDTO()
	
	protected static void propagateContactDOPropertiesToContactDTO(ContactDO contactDO, ContactDTO contact) {
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
	}

	protected static void propagateCcInfoToAccountDTO(AccountDO accountDO, AccountDTO account) {
		// Get CC Info of Account
		if (accountDO.getCcInfoId() != null) {
			CcInfoDO ccInfoDO = CcInfoDAO.getCcInfoById(accountDO.getCcInfoId());
			if (ccInfoDO != null) {
				// Propagate CC Info DO Properties to Account DTO
				propagateCcInfoDOPropertiesToAccountDTO(ccInfoDO, account);

				// Propagate Address to CC Info
				propagateAddressOfCcInfoToAccountDTO(ccInfoDO, account);
			} // if (ccInfoDO != null)
		} // Get CC Info of Account
	} // propagateCcInfoToAccountDTO()
	
	protected static void propagateCcInfoDOPropertiesToAccountDTO(CcInfoDO ccInfoDO, AccountDTO account) {
		// Set Credit Card attributes of Account DTO from CC Info DO
		account.setCreditCardLastFour(ccInfoDO.getLastFour());
		account.setCreditCardExpireMmyy(ccInfoDO.getExpireMmyy());
		account.setCreditCardType(ccInfoDO.getType());
		account.setCreditCardStatus(ccInfoDO.getStatus());
	}

	protected static void propagateAddressOfCcInfoToAccountDTO(CcInfoDO ccInfoDO, AccountDTO account) {
		// Get Address of CC Info
		if (ccInfoDO.getAddressId() != null) {
			// Get Address DO of CC Info if it exists
			AddressDO addressOfCcInfoDO = AddressDAO.getAddressById(ccInfoDO.getAddressId());
			if (addressOfCcInfoDO != null) {
				
				// Create Address DTO of CC Info
				AddressDTO ccInfoAddress = new AddressDTO();
				
				// Propagate Address DO Properties to Address DTO
				propagateAddressDOPropertiesToAddressDTO(addressOfCcInfoDO, ccInfoAddress);
				
				// Link Address of CC Info to Account
				account.setCreditCardAddress(ccInfoAddress);
			} // Get Address DO of CC Info if it exists
		} // // Get Address of CC Info -- if (ccInfoDO.getAddressId() != null)
	} // propagateAddressOfCcInfoToAccountDTO()
	
	protected static void propagateAddressDOPropertiesToAddressDTO(AddressDO addressDO, AddressDTO address) {
		// Set attributes of Address DTO from Address DO
		address.setStreet(addressDO.getStreet());
		address.setCity(addressDO.getCity());
		address.setState(addressDO.getState());
		address.setZipCode(addressDO.getZipCode());
		address.setCountry(addressDO.getCountry());
		address.setLatitude(addressDO.getLatitude());
		address.setLongitude(addressDO.getLongitude());
	}
	
	protected static void propagateExtraInfoToAccount(AccountDO accountDO, AccountDTO account) {
		// Get Extra Info of Account
		if (accountDO.getExtraInfo() != null) {
			AccountExtraInfoDO accountExtraInfoDO = AccountExtraInfoDAO.getAccountExtraInfoById(accountDO.getExtraInfo());
			if (accountExtraInfoDO != null) {
				// Propagate Extra Info DO Properties to Account DTO
				propagateExtraInfoDOPropertiesToAccountDTO(accountExtraInfoDO, account);
			} // if (accountExtraInfoDO != null)
		} // Get Extra Info of Account -- if (accountDO.getExtraInfo() != null)
	}

	protected static void propagateExtraInfoDOPropertiesToAccountDTO(AccountExtraInfoDO accountExtraInfoDO, AccountDTO account) {
		// Set Extra Info attributes of Account DTO from Extra Info DO
		account.setAccountExtraInfoKeyPair(accountExtraInfoDO.getKeyPair());
		account.setAccountExtraInfoValuePair(accountExtraInfoDO.getValuePair());
	}

	protected static AddressDTO getAddressById(Long addressId) {
		AddressDTO address = null;
		// Get Address of Contact of Account
		if (addressId  != null) {
			// Retrieve Address of Contact of Account if it exists
			AddressDO addressOfContactOfAccountDO = AddressDAO.getAddressById(addressId);
			if (addressOfContactOfAccountDO != null) {
				// Create Resident Address DTO
				address = new AddressDTO();

				// Propagate Address DO Properties to Address DTO
				propagateAddressDOPropertiesToAddressDTO(addressOfContactOfAccountDO, address);
			}
		} // Get Resident Address of Contact of Account
	
		return address;
	} // propagateAddressToContactOfAccount()

	protected static void propagateExtraInfoToUser(UserDO userDO, UserDTO user) {
		// Get User Extra Info of User
		if (userDO.getUserExtraInfoId() != null) {
			UserExtraInfoDO userExtraInfoDO = UserExtraInfoDAO.getUserExtraInfoById(userDO.getUserExtraInfoId().longValue());
			if (userExtraInfoDO != null) {
				// Create User Extra Info DTO
				UserExtraInfoDTO userExtraInfo = new UserExtraInfoDTO();
				
				// Propagate User Extra Info DO Properties to User Extra Info DTO
				propagateUserExtraInfoDOPropertiesToUserExtraInfoDTO(userExtraInfoDO, userExtraInfo);

				// Propagate TimeZone to User Extra Info
				propagateTimeZoneToUserExtraInfo(userExtraInfoDO, userExtraInfo);

				// Link User Extra Info to User
				user.setUserExtraInfo(userExtraInfo);
			}
		} // Get Extra Info of User
	} // propagateExtraInfoToUser

	protected static void propagateUserExtraInfoDOPropertiesToUserExtraInfoDTO(
			UserExtraInfoDO userExtraInfoDO, UserExtraInfoDTO userExtraInfo) {
		// Set attributes of User Extra Info DTO from User Extra Info DO
		userExtraInfo.setUserExtraInfoIndustry(userExtraInfoDO.getIndustry());
		userExtraInfo.setUserExtraInfoCompany(userExtraInfoDO.getCompany());
		userExtraInfo.setUserExtraInfoCompanySize(userExtraInfoDO.getCompanySize());
		userExtraInfo.setUserExtraInfoZipCode(userExtraInfoDO.getZipCode());
		userExtraInfo.setUserExtraInfoJobTitle(userExtraInfoDO.getJobTitle());
		userExtraInfo.setUserExtraInfoMobileDeviceModel(userExtraInfoDO.getMobileDeviceModel());
		userExtraInfo.setUserExtraInfoMobileManufacturer(userExtraInfoDO.getMobileManufacturer());
	}

	protected static void propagateTimeZoneToUserExtraInfo(UserExtraInfoDO userExtraInfoDO, UserExtraInfoDTO userExtraInfo) {
		// Time Zone of User Extra Info
		if (userExtraInfoDO.getTimeZoneId() != null) {
			TimeZoneDO timeZoneDO = TimeZoneDAO.getTimeZoneById(userExtraInfoDO.getTimeZoneId());
			if (timeZoneDO != null) {
				// Propagate Time Zone DO Properties to User Extra Info DTO
				propagateTimeZoneDOPropertiesToUserExtraInfoDTO(timeZoneDO, userExtraInfo);
			} // if (timeZoneDO != null)
		} // Time Zone of User Extra Info -- if (userExtraInfoDO.getTimeZoneId() != null)
	} // propagateTimeZoneToUserExtraInfo()

	protected static void propagateTimeZoneDOPropertiesToUserExtraInfoDTO(
			TimeZoneDO timeZoneDO, UserExtraInfoDTO userExtraInfo) {
		userExtraInfo.setTimeZone(timeZoneDO.getTimeZone());
		userExtraInfo.setTimeZoneLabel(timeZoneDO.getLabel());
	}
	
	protected static void propagateContactToUserDTO(UserDO userDO, UserDTO user) {
		// Get Contact of User
		if (userDO.getContactId() != null) {
			// Retrieve Contact if it exists
			ContactDO contactDO = ContactDAO.getContactById(userDO.getContactId());
			if (contactDO != null) {
				// Create Contact DTO
				ContactDTO contact = new ContactDTO();
				
				// Propagate Contact DO Properties to Contact DTO
				propagateContactDOPropertiesToContactDTO(contactDO, contact);

				// Link Contact to User
				user.setContact(contact);
				
				// Propagate Resident Address to Contact of Account
				AddressDTO contactResidentAddress = getAddressById(contactDO.getResidentAddressId());
				
				// Link Resident Address of Contact of Account to Contact
				contact.setContactResidentAddress(contactResidentAddress);

				// Propagate Resident Address to Contact of Account
				AddressDTO contactShippingAddress = getAddressById(contactDO.getShippingAddressId());
				
				// Link Shipping Address of Contact of Account to Contact
				contact.setContactShippingAddress(contactShippingAddress);

				// Propagate Resident Address to Contact of Account
				AddressDTO contactBillingAddress = getAddressById(contactDO.getBillingAddressId());
				
				// Link Billing Address of Contact of Account to Contact
				contact.setContactBillingAddress(contactBillingAddress);

			} // Retrieve Contact if it exists -- if (contactDO != null)
		} // Get Contact of User -- if (userDO.getContactId() != null)
	} // propagateContactToUserDTO()

	protected static void propagateRoleToUser(UserDO userDO, UserDTO user) {
		// Get Role of User
		if (userDO.getRoleId() != null) {
			// Retrieve Role if it exists
			RoleDO roleDO = RoleDAO.getRoleById(userDO.getRoleId().longValue());
			if (roleDO != null) {
				// Propagate Role DO Properties to User DTO
				propagateRoleDOPropertiesToUserDTO(roleDO, user);
			} // Retrieve Role if it exists -- if (roleDO != null)
		} // Get Role of User
	} // propagateRoleToUser()

	// Propagate Role DO Properties to User DTO
	protected static void propagateRoleDOPropertiesToUserDTO(RoleDO roleDO, UserDTO user) {
		user.setRoleName(roleDO.getName());
		user.setRoleDescription(roleDO.getDescription());
	}
	
}
