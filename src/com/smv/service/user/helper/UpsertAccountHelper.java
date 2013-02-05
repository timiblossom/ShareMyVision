
package com.zaptoe.user.helper;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zaptoe.common.bean.AccountDTO;
import com.zaptoe.common.exception.ZapToeErrorCode;
import com.zaptoe.common.exception.ZapToeServiceException;
import com.zaptoe.user.UserConstant;
import com.zaptoe.user.db.dao.AccountDAO;
import com.zaptoe.user.db.dao.AddressDAO;
import com.zaptoe.user.db.dao.CcInfoDAO;
import com.zaptoe.user.db.dao.ContactDAO;
import com.zaptoe.user.db.dbo.AccountDO;
import com.zaptoe.user.db.dbo.AddressDO;
import com.zaptoe.user.db.dbo.CcInfoDO;
import com.zaptoe.user.db.dbo.ContactDO;
import com.zaptoe.util.db.AbstractDO;


public class UpsertAccountHelper {

	private final static Logger LOGGER = LoggerFactory.getLogger(UpsertAccountHelper.class);

	public static AccountDTO upsertAccount(AccountDTO account) throws IllegalArgumentException, ZapToeServiceException, Exception {
		try {
			ParameterChecker.checkAccountNonNull(account);
			ParameterChecker.checkAccountId(account);
			return performUpsertAccountWork(account);
		} catch (IllegalArgumentException iae) {
			String errorMessage = "upsertAccount(AccountDTO account) failed. " +
									"\n " +
									"Account = " +
									account +
									"\n " +
									"Exception = " +
									iae;
			LOGGER.error(errorMessage);
			
			ZapToeServiceException swe = new ZapToeServiceException();
			swe.setErrorCode(ZapToeErrorCode.USER_SERVICE_ILLEGAL_ARGUMENT);
			swe.setErrorMessage(ZapToeErrorCode.USER_SERVICE_ILLEGAL_ARGUMENT_MSG + errorMessage);
			throw swe;
		} catch (ZapToeServiceException swe) {
			String errorMessage = "upsertAccount(AccountDTO account) failed. " +
									"\n " +
									"Account = " +
									account +
									"\n " +
									"Exception = " +
									swe;
			LOGGER.error(errorMessage);
			throw swe;
		} catch (Exception exception) {
			String errorMessage = "upsertAccount(AccountDTO account) failed. " +
									"\n " +
									"Account = " +
									account +
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

	protected static AccountDTO performUpsertAccountWork(AccountDTO account) throws Exception {
		try {
			// Check if Account exists in the system.
			AccountDO accountLookAheadDO = AccountDAO.getAccountById(account.getId().longValue());

			// Set operation of DO based on User Operation's action
			if (UserConstant.INSERT.equals(account.getAction())) {
				if (accountLookAheadDO != null) {
					String errorMessage = "Account  already exists. \nAccount  = " + account;
					LOGGER.error(errorMessage);
					throw new Exception(errorMessage);
				}
			} else if (UserConstant.UPDATE.equals(account.getAction())) {
				if (accountLookAheadDO == null) {
					String errorMessage = "Account  does not exist. \nAccount  = " + account;
					LOGGER.error(errorMessage);
					throw new Exception(errorMessage);
				}
			}
			
			AccountDO accountDO = new AccountDO();
			
			if (account.getContact() != null) {
				// Create Resident Address DO
				AddressDO residentAddressDO = new AddressDO();
				
				// Set attributes of DO
				residentAddressDO.setStreet(account.getContact().getContactResidentAddress().getStreet());
				residentAddressDO.setCity(account.getContact().getContactResidentAddress().getCity());
				residentAddressDO.setState(account.getContact().getContactResidentAddress().getState());
				residentAddressDO.setZipCode(account.getContact().getContactResidentAddress().getZipCode());
				residentAddressDO.setCountry(account.getContact().getContactResidentAddress().getCountry());
				residentAddressDO.setLatitude(account.getContact().getContactResidentAddress().getLatitude());
				residentAddressDO.setLongitude(account.getContact().getContactResidentAddress().getLongitude());

				// Set operation of DO based on User Operation's action
				if (UserConstant.INSERT.equals(account.getAction())) {
					// Set operation to "CREATE" -- i.e. insert new row into database
					residentAddressDO.setOperation(AbstractDO.CREATE);
				} else if (UserConstant.UPDATE.equals(account.getAction())) {
					// Set operation to "UPDATE" -- i.e. update row in database
					residentAddressDO.setOperation(AbstractDO.UPDATE);
				}

				// Link DAO-DO
				AddressDAO residentAddressDao = new AddressDAO(residentAddressDO);
				residentAddressDao.save(true, false);

				// Create Billing Address DO
				AddressDO billingAddressDO = new AddressDO();
				
				// Set attributes of DO
				billingAddressDO.setStreet(account.getContact().getContactBillingAddress().getStreet());
				billingAddressDO.setCity(account.getContact().getContactBillingAddress().getCity());
				billingAddressDO.setState(account.getContact().getContactBillingAddress().getState());
				billingAddressDO.setZipCode(account.getContact().getContactBillingAddress().getZipCode());
				billingAddressDO.setCountry(account.getContact().getContactBillingAddress().getCountry());
				billingAddressDO.setLatitude(account.getContact().getContactBillingAddress().getLatitude());
				billingAddressDO.setLongitude(account.getContact().getContactBillingAddress().getLongitude());

				// Set operation of DO based on User Operation's action
				if (UserConstant.INSERT.equals(account.getAction())) {
					// Set operation to "CREATE" -- i.e. insert new row into database
					billingAddressDO.setOperation(AbstractDO.CREATE);
				} else if (UserConstant.UPDATE.equals(account.getAction())) {
					// Set operation to "UPDATE" -- i.e. update row in database
					billingAddressDO.setOperation(AbstractDO.UPDATE);
				}

				// Link DAO-DO
				AddressDAO billingAddressDao = new AddressDAO(billingAddressDO);
				billingAddressDao.save(true, false);

				// Create Shipping Address DO
				AddressDO shippingAddressDO = new AddressDO();
				
				// Set attributes of DO
				shippingAddressDO.setStreet(account.getContact().getContactShippingAddress().getStreet());
				shippingAddressDO.setCity(account.getContact().getContactShippingAddress().getCity());
				shippingAddressDO.setState(account.getContact().getContactShippingAddress().getState());
				shippingAddressDO.setZipCode(account.getContact().getContactShippingAddress().getZipCode());
				shippingAddressDO.setCountry(account.getContact().getContactShippingAddress().getCountry());
				shippingAddressDO.setLatitude(account.getContact().getContactShippingAddress().getLatitude());
				shippingAddressDO.setLongitude(account.getContact().getContactShippingAddress().getLongitude());

				// Set operation of DO based on User Operation's action
				if (UserConstant.INSERT.equals(account.getAction())) {
					// Set operation to "CREATE" -- i.e. insert new row into database
					shippingAddressDO.setOperation(AbstractDO.CREATE);
				} else if (UserConstant.UPDATE.equals(account.getAction())) {
					// Set operation to "UPDATE" -- i.e. update row in database
					shippingAddressDO.setOperation(AbstractDO.UPDATE);
				}

				// Link DAO-DO
				AddressDAO shippingAddressDao = new AddressDAO(shippingAddressDO);
				shippingAddressDao.save(true, false);

				ContactDO contactDO = new ContactDO();
				
				// Set attributes of DO
				contactDO.setFirstName(account.getContact().getContactFirstName());
				contactDO.setLastName(account.getContact().getContactLastName());
				contactDO.setAdditionalEmail(account.getContact().getContactAdditionalEmail());
				contactDO.setResidentAddressId(residentAddressDO.getId());
				contactDO.setShippingAddressId(shippingAddressDO.getId());
				contactDO.setBillingAddressId(billingAddressDO.getId());
				contactDO.setWorkPhone(account.getContact().getContactWorkPhone());
				contactDO.setMobilePhone(account.getContact().getContactMobilePhone());
				contactDO.setHomePhone(account.getContact().getContactHomePhone());
				contactDO.setAim(account.getContact().getContactAim());
				contactDO.setYim(account.getContact().getContactYim());
				contactDO.setSkype(account.getContact().getContactSkype());
				contactDO.setFacebook(account.getContact().getContactFaceBook());
				contactDO.setTwitter(account.getContact().getContactTwitter());

				// Set operation of DO based on User Operation's action
				if (UserConstant.INSERT.equals(account.getAction())) {
					// Set operation to "CREATE" -- i.e. insert new row into database
					contactDO.setOperation(AbstractDO.CREATE);
				} else if (UserConstant.UPDATE.equals(account.getAction())) {
					// Set operation to "UPDATE" -- i.e. update row in database
					contactDO.setOperation(AbstractDO.UPDATE);
				}

				// Link DAO-DO
				ContactDAO contactDao = new ContactDAO(contactDO);
				contactDao.save(true, false);
			}
			
			// Create CcInfo DO
			CcInfoDO ccInfoDO = new CcInfoDO();
			
			// Set attributes of DO
			ccInfoDO.setLastFour(account.getCreditCardLastFour());
			ccInfoDO.setExpireMmyy(account.getCreditCardExpireMmyy());
			ccInfoDO.setType(account.getCreditCardType());
			ccInfoDO.setStatus(account.getCreditCardStatus());

			// Set operation of DO based on User Operation's action
			if (UserConstant.INSERT.equals(account.getAction())) {
				// Set operation to "CREATE" -- i.e. insert new row into database
				ccInfoDO.setOperation(AbstractDO.CREATE);
			} else if (UserConstant.UPDATE.equals(account.getAction())) {
				// Set operation to "UPDATE" -- i.e. update row in database
				ccInfoDO.setOperation(AbstractDO.UPDATE);
			}

			// Link DAO-DO
			CcInfoDAO CcInfoDao = new CcInfoDAO(ccInfoDO);
			CcInfoDao.save(true, false);

			// Set attributes of DO
			accountDO.setName(account.getAccountName());
			accountDO.setDescription(account.getAccountDescription());
			accountDO.setType(account.getAccountType());
			accountDO.setStatus(account.getAccountStatus());
			accountDO.setCcInfoId(ccInfoDO.getId());

			// Set operation of DO based on User Operation's action
			if (UserConstant.INSERT.equals(account.getAction())) {
				// Set operation to "CREATE" -- i.e. insert new row into database
				accountDO.setOperation(AbstractDO.CREATE);
			} else if (UserConstant.UPDATE.equals(account.getAction())) {
				// Set operation to "UPDATE" -- i.e. update row in database
				accountDO.setOperation(AbstractDO.UPDATE);
			}

			// Link DAO-DO
			AccountDAO accountDao = new AccountDAO(accountDO);
			accountDao.save(true, false);

			return account;
		} catch (Exception exception) {
			LOGGER.error("Error in performing performUpsertAccountWork(); \nAccount = " + account + "; \nException = " + exception);
			throw exception;
		}
	}
}
