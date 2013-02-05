
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
import com.zaptoe.user.UserConstant;
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
import com.zaptoe.util.db.AbstractDO;
import com.zaptoe.util.text.StringUtils;


public class UpsertUserHelper {

	private final static Logger LOGGER = LoggerFactory.getLogger(UpsertUserHelper.class);


	public static UserDTO upsertUser(UserDTO user) throws IllegalArgumentException, ZapToeServiceException, Exception {
		try {
			ParameterChecker.checkUserNonNull(user);
			ParameterChecker.checkUserEmail(user);
			return performUpsertUserWork(user);
		} catch (IllegalArgumentException iae) {
			String errorMessage = "upsertUser(UserDTO user) failed. " +
									"\n " +
									"User = " +
									user +
									"\n " +
									"Exception = " +
									iae;
			LOGGER.error(errorMessage);
			
			ZapToeServiceException swe = new ZapToeServiceException();
			swe.setErrorCode(ZapToeErrorCode.USER_SERVICE_ILLEGAL_ARGUMENT);
			swe.setErrorMessage(ZapToeErrorCode.USER_SERVICE_ILLEGAL_ARGUMENT_MSG + errorMessage);
			throw swe;
		} catch (ZapToeServiceException swe) {
			String errorMessage = "upsertUser(UserDTO user) failed. " +
									"\n " +
									"User = " +
									user +
									"\n " +
									"Exception = " +
									swe;
			LOGGER.error(errorMessage);
			throw swe;
		} catch (Exception exception) {
			String errorMessage = "upsertUser(UserDTO user) failed. " +
									"\n " +
									"User = " +
									user +
									"\n " +
									"Exception = " +
									exception;
			LOGGER.error(errorMessage);
			
			ZapToeServiceException swe = new ZapToeServiceException();
			swe.setErrorCode(ZapToeErrorCode.USER_SERVICE_EXCEPTION);
			swe.setErrorMessage(ZapToeErrorCode.USER_SERVICE_EXCEPTION_MSG + errorMessage);
			throw swe;
		}
	}

	protected static void propagateUserStatus(UserDTO user, UserDO userDO) throws Exception {
		if (userDO.getStatusId() != null) {
			user.setUserStatus(UserStatusDO.getUserStatus(userDO.getStatusId()).getName());
		}
	}
	
	protected static void propagateUserType(UserDTO user, UserDO userDO) throws Exception {
		if (userDO.getTypeId() != null) {
			user.setUserType(UserTypeDO.getUserType(userDO.getTypeId()).getName());
		}
	}
	
	protected static void propagateUserId(UserDTO user, UserDO userDO) {
		if (userDO.getId() != null) {
			user.setUserId(userDO.getId());
		}
	}

	protected static void propagateUserActivationCode(UserDTO user, UserDO userDO) {
		user.setUserActivationCode(userDO.getActivationCode());
	}
	
	protected static void setUserStatus(UserDTO user, UserDO userDO) throws IllegalArgumentException {
		Long statusId = null;
		if (!StringUtils.nullOrEmptyOrBlankString(user.getUserStatus())) {
			statusId = UserStatusDO.getUserStatus(user.getUserStatus()).getId();
		} else {
			statusId = UserStatusDO.getUserStatus(UserStatusDO.USER_STATUS_NAME_ACTIVATION_PENDING).getId();
		}
		userDO.setStatusId(statusId);
	}
	
	protected static void setUserType(UserDTO user, UserDO userDO) throws IllegalArgumentException {
		Long typeId = null;
		if (!StringUtils.nullOrEmptyOrBlankString(user.getUserType())) {
			typeId = UserTypeDO.getUserType(user.getUserType()).getId();
		} else {
			typeId = UserTypeDO.getUserType(UserTypeDO.USER_TYPE_NAME_FREE).getId();
		}
		userDO.setTypeId(typeId);
	}
	
	protected static AddressDTO performUpsertAddressWork(AddressDTO address, String action) throws Exception {
		try {
			if (address != null) {
				// Create Address DO
				AddressDO addressDO = null;
				if (address.getId() != null) {
					addressDO = AddressDAO.getAddressById(address.getId().longValue());
				}
				
				// Check if address exists in database
				if (UserConstant.INSERT.equals(action)) {
					if (addressDO != null) {
						String errorMessage = "Address already exists." + 
							"\n" +
							"Address = " + 
							address;
						LOGGER.error(errorMessage);
						throw new Exception(errorMessage);
					}
				} else if (UserConstant.UPDATE.equals(action)) {
					if (addressDO == null) {
						String errorMessage = "Address does not exist." + 
							"\n" +
							"Address = " + 
							address;
						LOGGER.error(errorMessage);
						throw new Exception(errorMessage);
					}
				}
				
				// Create Address DO if null (INSERT case)
				if (addressDO == null) {
					addressDO = new AddressDO();
				}
				
				// Set attributes of DO
				addressDO.setStreet(address.getStreet());
				addressDO.setCity(address.getCity());
				addressDO.setState(address.getState());
				addressDO.setZipCode(address.getZipCode());
				addressDO.setCountry(address.getCountry());
				addressDO.setLatitude(address.getLatitude());
				addressDO.setLongitude(address.getLongitude());

				// Set operation of DO based on User Operation's action
				if (UserConstant.INSERT.equals(action)) {
					// Set operation to "CREATE" -- i.e. insert new row into database
					addressDO.setOperation(AbstractDO.CREATE);
				} else if (UserConstant.UPDATE.equals(action)) {
					// Set operation to "UPDATE" -- i.e. update row in database
					addressDO.setOperation(AbstractDO.UPDATE);
					
					// Get id of address for updating
					if (address.getId() != null) {
						addressDO.setId(address.getId().longValue());
					}
				}

				// Link DAO-DO
				AddressDAO addressDao = new AddressDAO(addressDO);

				// Persist address
				addressDao.save(true, false);
				
				// Propagate back if operation is INSERT/creation
				if (UserConstant.INSERT.equals(action)) {
					address.setId(addressDO.getId());
				}

			}

			return address;

		} catch (Exception exception) {
			String errorMessage = "performUpsertAddressWork(address, action) failed." + 
				"\n" +
				"Address = " + 
				address + 
				"\n" + 
				"Action = " + 
				action + 
				"; \n" + 
				"Exception = " + 
				exception.toString();
			LOGGER.error(errorMessage);
			exception.printStackTrace();
			throw new Exception(errorMessage);
		}
	}

	protected static ContactDTO performUpsertContactWork(ContactDTO contact, String action) throws Exception {
		try {
			if (contact != null) {
				// Contact
				ContactDO contactDO = null;
				if (contact.getId() != null) {
					contactDO = ContactDAO.getContactById(contact.getId().longValue());
				}

				// Check if contact exists in database
				if (UserConstant.INSERT.equals(action)) {
					if (contactDO != null) {
						String errorMessage = "Contact already exists." + 
							"\n" +
							"Contact = " + 
							contact;
						LOGGER.error(errorMessage);
						throw new Exception(errorMessage);
					}
				} else if (UserConstant.UPDATE.equals(action)) {
					if (contactDO == null) {
						String errorMessage = "Contact does not exist." + 
							"\n" +
							"Contact = " + 
							contact;
						LOGGER.error(errorMessage);
						throw new Exception(errorMessage);
					}
				}
				
				// Resident Address
				AddressDTO residentAddress = contact.getContactResidentAddress();
				if ((residentAddress != null) && (contactDO != null)) {
					residentAddress.setId(contactDO.getResidentAddressId());
				}
				residentAddress = performUpsertAddressWork(residentAddress, action);
				contact.setContactResidentAddress(residentAddress);

				// Shipping Address
				AddressDTO shippingAddress = contact.getContactShippingAddress();
				if ((shippingAddress != null) && (contactDO != null)) {
					shippingAddress.setId(contactDO.getShippingAddressId());
				}
				shippingAddress = performUpsertAddressWork(shippingAddress, action);
				contact.setContactShippingAddress(shippingAddress);

				// Billing Address
				AddressDTO billingAddress = contact.getContactBillingAddress();
				if ((billingAddress != null) && (contactDO != null)) {
					billingAddress.setId(contactDO.getBillingAddressId());
				}
				billingAddress = performUpsertAddressWork(billingAddress, action);
				contact.setContactBillingAddress(billingAddress);

				if (contactDO == null) {
					contactDO = new ContactDO();
				}
				
				// Set attributes of DO
				contactDO.setFirstName(contact.getContactFirstName());
				contactDO.setLastName(contact.getContactLastName());
				contactDO.setAdditionalEmail(contact.getContactAdditionalEmail());

				contactDO.setResidentAddressId(residentAddress != null ? residentAddress.getId() : null);
				contactDO.setBillingAddressId(billingAddress != null ? billingAddress.getId() : null);
				contactDO.setShippingAddressId(shippingAddress != null ? shippingAddress.getId() : null);

				contactDO.setWorkPhone(contact.getContactWorkPhone());
				contactDO.setMobilePhone(contact.getContactMobilePhone());
				contactDO.setHomePhone(contact.getContactHomePhone());
				contactDO.setAim(contact.getContactAim());
				contactDO.setYim(contact.getContactYim());
				contactDO.setSkype(contact.getContactSkype());
				contactDO.setFacebook(contact.getContactFaceBook());
				contactDO.setTwitter(contact.getContactTwitter());

				// Set operation of DO based on User Operation's action
				if (UserConstant.INSERT.equals(action)) {
					// Set operation to "CREATE" -- i.e. insert new row into database
					contactDO.setOperation(AbstractDO.CREATE);
				} else if (UserConstant.UPDATE.equals(action)) {
					// Set operation to "UPDATE" -- i.e. update row in database
					contactDO.setOperation(AbstractDO.UPDATE);
					if (contact.getId() != null) {
						contactDO.setId(contact.getId().longValue());
					}
				}

				// Link DAO-DO
				ContactDAO contactDao = new ContactDAO(contactDO);

				// Persist contact
				contactDao.save(true, false);
				
				// Propagate back id of Contact DO for INSERT/create operation
				if (UserConstant.INSERT.equals(action)) {
					contact.setId(contactDO.getId());
				}

			} // if (contact != null)
			
			return contact;

		} catch (Exception exception) {
			String errorMessage = "performUpsertContactWork(contact, action) failed. \n" + 
				"Contact = " + 
				contact + 
				"\n" + 
				"Action = " +
				action + 
				"; \n" + 
				"Exception = " + exception.toString();
			LOGGER.error(errorMessage);
			throw new Exception(errorMessage);
		}
	}

	protected static CcInfoDO performUpsertCcInfoWork(AccountDTO account, String action) throws Exception {
		try {
			CcInfoDO ccInfoDO = null;
			if (account != null) {
				if (!StringUtils.nullOrEmptyOrBlankString(account.getCreditCardLastFour())) {
					// Create CcInfo DO
					ccInfoDO = new CcInfoDO();
					
					// Set attributes of DO
					ccInfoDO.setLastFour(account.getCreditCardLastFour());
					ccInfoDO.setExpireMmyy(account.getCreditCardExpireMmyy());
					ccInfoDO.setType(account.getCreditCardType());
					ccInfoDO.setStatus(account.getCreditCardStatus());

					// Set operation of DO based on User Operation's action
					if (UserConstant.INSERT.equals(action)) {
						// Set operation to "CREATE" -- i.e. insert new row into database
						ccInfoDO.setOperation(AbstractDO.CREATE);
					} else if (UserConstant.UPDATE.equals(action)) {
						// Set operation to "UPDATE" -- i.e. update row in database
						ccInfoDO.setOperation(AbstractDO.UPDATE);
					}

					// Set address id from Account via Billing Address of Contact (if it exists)
					if ((account.getContact() != null) && 
							(account.getContact().getContactBillingAddress() != null) &&
							(account.getContact().getContactBillingAddress().getId() != null)) {
						ccInfoDO.setAddressId(account.getContact().getContactBillingAddress().getId().longValue());
					}
					
					// Link DAO-DO
					CcInfoDAO ccInfoDao = new CcInfoDAO(ccInfoDO);

					// Persist CcInfo
					ccInfoDao.save(true, false);
				}
			}
			
			return ccInfoDO;
			
		} catch (Exception exception) {
			String errorMessage = "performUpsertCcInfoWork(account, action) failed. \n" + 
				"Account = " + 
				account + 
				"\n" + 
				"Action = " +
				action + 
				"; \n" + 
				"Exception = " + exception.toString();
			LOGGER.error(errorMessage);
			throw new Exception(errorMessage);
		}
		
	}

	protected static AccountExtraInfoDO performUpsertAccountExtraInfoWork(AccountDTO account, String action) throws Exception {
		try {
			AccountExtraInfoDO accountExtraInfoDO = null;

			if (account != null) {
				// Account
				AccountDO accountDO = null;
				if (account.getId() != null) {
					accountDO = AccountDAO.getAccountById(account.getId().longValue());
				}

				// Check if account exists in database
				if (UserConstant.INSERT.equals(action)) {
					if (accountDO != null) {
						String errorMessage = "Account already exists." + 
							"\n" +
							"Account = " + 
							account;
						LOGGER.error(errorMessage);
						throw new Exception(errorMessage);
					}
				} else if (UserConstant.UPDATE.equals(action)) {
					if (accountDO == null) {
						String errorMessage = "Account does not exist." + 
							"\n" +
							"Account = " + 
							account;
						LOGGER.error(errorMessage);
						throw new Exception(errorMessage);
					}
				}

				if ((accountDO != null) && (accountDO.getExtraInfo() != null)) {
					accountExtraInfoDO = AccountExtraInfoDAO.getAccountExtraInfoById(accountDO.getExtraInfo());

					// Check if account extra info already exists in database
					if (UserConstant.INSERT.equals(action)) {
						if (accountExtraInfoDO != null) {
							String errorMessage = "Account Extra Info already exists." + 
								"\n" +
								"Account Extra Info = " + 
								accountExtraInfoDO;
							LOGGER.error(errorMessage);
							throw new Exception(errorMessage);
						}
					} else if (UserConstant.UPDATE.equals(action)) {
						if (accountExtraInfoDO == null) {
							String errorMessage = "Account Extra Info does not exist." + 
								"\n" +
								"Account Extra Info = " + 
								accountExtraInfoDO;
							LOGGER.error(errorMessage);
							throw new Exception(errorMessage);
						}
					}
				}
				
				if ((account.getAccountExtraInfoKeyPair() != null) || (account.getAccountExtraInfoValuePair() != null)) {
					if (accountExtraInfoDO == null) {
						accountExtraInfoDO = new AccountExtraInfoDO();
					}
					accountExtraInfoDO.setKeyPair(account.getAccountExtraInfoKeyPair());
					accountExtraInfoDO.setValuePair(account.getAccountExtraInfoValuePair());
				}

				if (accountExtraInfoDO != null) {
					// Set operation of DO based on User Operation's action
					if (UserConstant.INSERT.equals(action)) {
						// Set operation to "CREATE" -- i.e. insert new row into database
						accountExtraInfoDO.setOperation(AbstractDO.CREATE);
					} else if (UserConstant.UPDATE.equals(action)) {
						// Set operation to "UPDATE" -- i.e. update row in database
						accountExtraInfoDO.setOperation(AbstractDO.UPDATE);
					}

					// Link DAO-DO
					AccountExtraInfoDAO accountExtraInfoDAO = new AccountExtraInfoDAO(accountExtraInfoDO);

					// Persist DO
					accountExtraInfoDAO.save(true, false);
				}
			
			}
			
			return accountExtraInfoDO;
			
		} catch (Exception exception) {
			String errorMessage = "performUpsertAccountExtraInfoWork(account, action) failed. \n" + 
				"Account = " + 
				account + 
				"\n" + 
				"Action = " +
				action + 
				"; \n" + 
				"Exception = " + exception.toString();
			LOGGER.error(errorMessage);
			throw new Exception(errorMessage);
		}
		
	}
	
	protected static AccountDTO performUpsertAccountWork(AccountDTO account, String action) throws Exception {
		try {
			if (account != null) {
				// Account
				AccountDO accountDO = null;
				if (account.getId() != null) {
					accountDO = AccountDAO.getAccountById(account.getId().longValue());
				}

				// Check if account exists in database
				if (UserConstant.INSERT.equals(action)) {
					if (accountDO != null) {
						String errorMessage = "Account already exists." + 
							"\n" +
							"Account = " + 
							account;
						LOGGER.error(errorMessage);
						throw new Exception(errorMessage);
					}
				} else if (UserConstant.UPDATE.equals(action)) {
					if (accountDO == null) {
						String errorMessage = "Account does not exist." + 
							"\n" +
							"Account = " + 
							account;
						LOGGER.error(errorMessage);
						throw new Exception(errorMessage);
					}
				}

				CcInfoDO ccInfoDO = performUpsertCcInfoWork(account, action);
				
				AccountExtraInfoDO accountExtraInfoDO = performUpsertAccountExtraInfoWork(account, action);
				
				// Create Account DO if necessary/null
				if (accountDO == null) {
					accountDO = new AccountDO();
				}
				
				// Set attributes of DO
				accountDO.setName(account.getAccountName());
				accountDO.setDescription(account.getAccountDescription());
				accountDO.setType(account.getAccountType());
				accountDO.setStatus(account.getAccountStatus());
				if (ccInfoDO != null) {
					accountDO.setCcInfoId(ccInfoDO.getId());
				}
				if ((account.getContact() != null) && (account.getContact().getId() != null)) {
					accountDO.setContactId(account.getContact().getId().longValue());
				}
				if (accountExtraInfoDO != null) {
					accountDO.setExtraInfo(accountExtraInfoDO.getId());
				}

				// Set operation of DO based on User Operation's action
				if (UserConstant.INSERT.equals(action)) {
					// Set operation to "CREATE" -- i.e. insert new row into database
					accountDO.setOperation(AbstractDO.CREATE);
				} else if (UserConstant.UPDATE.equals(action)) {
					// Set operation to "UPDATE" -- i.e. update row in database
					accountDO.setOperation(AbstractDO.UPDATE);
					
					// Set id of Account DO from Account DTO
					if (account.getId() != null) {
						accountDO.setId(account.getId().longValue());
					}
				}

				// Link DAO-DO
				AccountDAO accountDao = new AccountDAO(accountDO);

				// Persist DO
				accountDao.save(true, false);
				
				// Propagate back id of Account DO for INSERT/create operation
				if (UserConstant.INSERT.equals(action)) {
					account.setId(accountDO.getId());
				}
				
			} // if (account != null)
		
			return account;

		} catch (Exception exception) {
			String errorMessage = "performUpsertAccountWork(account, action) failed. \n" + 
				"Account = " + 
				account + 
				"\n" + 
				"Action = " +
				action + 
				"; \n" + 
				"Exception = " + exception.toString();
			LOGGER.error(errorMessage);
			throw new Exception(errorMessage);
		}
		
	}

	protected static TimeZoneDO performUpsertTimeZoneWork(UserExtraInfoDTO userExtraInfo, String action) throws Exception {
		try {
			TimeZoneDO timeZoneDO = null;
			if (userExtraInfo != null) {
				UserExtraInfoDO userExtraInfoDO = null;

				if (userExtraInfo.getId() != null) {
					userExtraInfoDO = UserExtraInfoDAO.getUserExtraInfoById(userExtraInfo.getId());
				}

				// Check if userExtraInfo exists in database
				if (UserConstant.INSERT.equals(action)) {
					if (userExtraInfoDO != null) {
						String errorMessage = "UserExtraInfo already exists." + 
							"\n" +
							"UserExtraInfo = " + 
							userExtraInfo;
						LOGGER.error(errorMessage);
						throw new Exception(errorMessage);
					}
				} else if (UserConstant.UPDATE.equals(action)) {
					if (userExtraInfoDO == null) {
						String errorMessage = "UserExtraInfo does not exist." + 
							"\n" +
							"UserExtraInfo = " + 
							userExtraInfo;
						LOGGER.error(errorMessage);
						throw new Exception(errorMessage);
					}
				}
				
				// Check if userExtraInfo exists in database
				if ((userExtraInfoDO != null) && (userExtraInfoDO.getTimeZoneId() != null)) {
					timeZoneDO = TimeZoneDAO.getTimeZoneById(userExtraInfoDO.getTimeZoneId());
				}
				
				// Insert or Update time zone only if time zone information in user extra info is not null
				if ((userExtraInfo.getTimeZone() != null) || (userExtraInfo.getTimeZoneLabel() != null)) {
					if (timeZoneDO == null) {
						timeZoneDO = new TimeZoneDO();
					}

					// Set attributes of DO
					timeZoneDO.setLabel(userExtraInfo.getTimeZoneLabel());
					timeZoneDO.setTimeZone(userExtraInfo.getTimeZone());

					// Set operation of DO based on User Operation's action
					if (UserConstant.INSERT.equals(action)) {
						// Set operation to "CREATE" -- i.e. insert new row into database
						timeZoneDO.setOperation(AbstractDO.CREATE);
					} else if (UserConstant.UPDATE.equals(action)) {
						// Set operation to "UPDATE" -- i.e. update row in database
						timeZoneDO.setOperation(AbstractDO.UPDATE);
					}

					// Link DAO-DO
					TimeZoneDAO timeZoneDao = new TimeZoneDAO(timeZoneDO);

					// Persist DO
					timeZoneDao.save(true, false);

				}

			}

			return timeZoneDO;
		} catch (Exception exception) {
			String errorMessage = "performUpsertTimeZoneWork(userExtraInfo, action) failed. \n" + 
				"UserExtraInfo = " + 
				userExtraInfo + 
				"\n" + 
				"Action = " +
				action + 
				"; \n" + 
				"Exception = " + exception.toString();
			LOGGER.error(errorMessage);
			throw new Exception(errorMessage);
		}
		
	}
	
	
	protected static UserExtraInfoDTO performUpsertUserExtraInfoWork(UserExtraInfoDTO userExtraInfo, String action) throws Exception {
		try {
			if (userExtraInfo != null) {
				UserExtraInfoDO userExtraInfoDO = null;

				if (userExtraInfo.getId() != null) {
					userExtraInfoDO = UserExtraInfoDAO.getUserExtraInfoById(userExtraInfo.getId());
				}

				// Check if userExtraInfo exists in database
				if (UserConstant.INSERT.equals(action)) {
					if (userExtraInfoDO != null) {
						String errorMessage = "UserExtraInfo already exists." + 
							"\n" +
							"UserExtraInfo = " + 
							userExtraInfo;
						LOGGER.error(errorMessage);
						throw new Exception(errorMessage);
					}
				} else if (UserConstant.UPDATE.equals(action)) {
					if (userExtraInfoDO == null) {
						String errorMessage = "UserExtraInfo does not exist." + 
							"\n" +
							"UserExtraInfo = " + 
							userExtraInfo;
						LOGGER.error(errorMessage);
						throw new Exception(errorMessage);
					}
				}

				// Create DO if necessary/null
				if (userExtraInfoDO == null) {
					userExtraInfoDO = new UserExtraInfoDO();
				}

				// Set attributes of DO
				userExtraInfoDO.setIndustry(userExtraInfo.getUserExtraInfoIndustry());
				userExtraInfoDO.setCompany(userExtraInfo.getUserExtraInfoCompany());
				userExtraInfoDO.setCompanySize(userExtraInfo.getUserExtraInfoCompanySize());
				userExtraInfoDO.setZipCode(userExtraInfo.getUserExtraInfoZipCode());
				userExtraInfoDO.setTitle(userExtraInfo.getUserExtraInfoTitle());
				userExtraInfoDO.setJobTitle(userExtraInfo.getUserExtraInfoJobTitle());
				userExtraInfoDO.setMobileDeviceModel(userExtraInfo.getUserExtraInfoMobileDeviceModel());
				userExtraInfoDO.setMobileManufacturer(userExtraInfo.getUserExtraInfoMobileManufacturer());

				TimeZoneDO timeZoneDO = performUpsertTimeZoneWork(userExtraInfo, action);
				userExtraInfoDO.setTimeZoneId(timeZoneDO == null ? null : timeZoneDO.getId());

				// Set operation of DO based on User Operation's action
				if (UserConstant.INSERT.equals(action)) {
					// Set operation to "CREATE" -- i.e. insert new row into database
					userExtraInfoDO.setOperation(AbstractDO.CREATE);
				} else if (UserConstant.UPDATE.equals(action)) {
					// Set operation to "UPDATE" -- i.e. update row in database
					userExtraInfoDO.setOperation(AbstractDO.UPDATE);
				}

				// Link DAO-DO
				UserExtraInfoDAO userExtraInfoDao = new UserExtraInfoDAO(userExtraInfoDO);

				// Persist DO
				userExtraInfoDao.save(true, false);

				// Propagate back id of Account DO for INSERT/create operation
				if (UserConstant.INSERT.equals(action)) {
					userExtraInfo.setId(userExtraInfoDO.getId());
				}
				
			}

			return userExtraInfo;
		} catch (Exception exception) {
			String errorMessage = "performUpsertUserExtraInfoWork(userExtraInfo, action) failed. \n" + 
				"UserExtraInfo = " + 
				userExtraInfo + 
				"\n" + 
				"Action = " +
				action + 
				"; \n" + 
				"Exception = " + exception.toString();
			LOGGER.error(errorMessage);
			throw new Exception(errorMessage);
		}
	}
	
	protected static UserDTO performUpsertUserWork(UserDTO user) throws Exception {
		// Check if User exists in the system.
		UserDO userDO = UserDAO.getUserByEmailAndPassword(user.getUserEmail(), user.getUserPassword());

		// Check if user exists in database
		if (UserConstant.INSERT.equals(user.getAction())) {
			if (userDO != null) {
				String errorMessage = "User already exists." + 
					"\n" +
					"User = " + 
					user;
				LOGGER.error(errorMessage);
				throw new Exception(errorMessage);
			}
		} else if (UserConstant.UPDATE.equals(user.getAction())) {
			if (userDO == null) {
				String errorMessage = "User does not exist." + 
					"\n" +
					"User = " + 
					user;
				LOGGER.error(errorMessage);
				throw new Exception(errorMessage);
			}
		}
		
		try {
			
			// Contact
			ContactDTO contact = user.getContact();

			if (userDO != null) {
				contact.setId(userDO.getContactId());
			}

			if (contact != null) {
				contact = performUpsertContactWork(contact, user.getAction());
			}
			
			// Account
			AccountDTO account = user.getAccount();
			if (account != null) {
				
				if (userDO != null) {
					account.setId(userDO.getAccountId());
				}

				if (UserConstant.INSERT.equals(user.getAction())) {
					account.setAccountStatus(UserStatusDO.USER_STATUS_NAME_ACTIVATION_PENDING);
				}
				
				account.setContact(contact);
				account = performUpsertAccountWork(account, user.getAction());
			}  // if (user.getAccount() != null)
		
			RoleDO roleDO = null;
			// Retrieve Permissions and related objects
			if ((!StringUtils.nullOrEmptyOrBlankString(user.getRoleName())) && 
					(!StringUtils.nullOrEmptyOrBlankString(user.getRoleDescription()))) {
				roleDO = RoleDAO.getRoleByNameAndDescription(user.getRoleName(), user.getRoleDescription());
			}

			if (userDO == null) {
				userDO = new UserDO();
			}

			// Set attributes of User DO
			userDO.setDisplayName(user.getUserDisplayName());
			userDO.setEmail(user.getUserEmail());
			userDO.setPassword(user.getUserPassword());
			userDO.setSalt(user.getUserSalt());
			setUserStatus(user, userDO);
			setUserType(user, userDO);
			String activationCode = ActivationHelper.generateActivationCode(user);
			userDO.setActivationCode(activationCode);
			userDO.setPasswordResetCode(user.getUserPasswordResetUserCode());
			userDO.setLanguage(user.getUserLanguage());

			if (account != null) {
				userDO.setAccountId(account.getId());
			}
			if (contact != null) {
				userDO.setContactId(contact.getId());
			}
			if (user.getUserExtraInfo() != null) {
				UserExtraInfoDTO userExtraInfo = performUpsertUserExtraInfoWork(user.getUserExtraInfo(), user.getAction());
				user.setUserExtraInfo(userExtraInfo);
				userDO.setUserExtraInfoId(user.getUserExtraInfo().getId());
			}
			if (roleDO != null) {
				userDO.setRoleId(roleDO.getId());
			}
			
			// Set operation of DO based on User Operation's action
			if (UserConstant.INSERT.equals(user.getAction())) {
				// Set operation to "CREATE" -- i.e. insert new row into database
				userDO.setOperation(AbstractDO.CREATE);
			} else if (UserConstant.UPDATE.equals(user.getAction())) {
				// Set operation to "UPDATE" -- i.e. update row in database
				userDO.setOperation(AbstractDO.UPDATE);
			}

			// Link DAO-DO
			UserDAO userDao = new UserDAO(userDO);

			if (userDao.save(true, false) == false) {
				String errorMessage = "Error in performing performUpsertWork() with saving DAO; User = " + user;
				LOGGER.error(errorMessage);
				throw new Exception(errorMessage);
			}
			
			// Propagate back results of operation back to DTO
			user.setAccount(account);
			user.setContact(contact);
			propagateUserStatus(user, userDO);
			propagateUserId(user, userDO);
			propagateUserActivationCode(user, userDO);

			return user;
		} catch (Exception exception) {
			String errorMessage = "Error in performing performUpsertWork(); User = " + user + "; Exception = " + exception;
			LOGGER.error(errorMessage);
			throw exception;
		}
	}

}
