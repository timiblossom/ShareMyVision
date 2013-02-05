package com.smv.service.outlet.adaptee.smv;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.smv.common.bean.EmailNameEntryDTO;
import com.smv.common.bean.KeyValueEntryDTO;
import com.smv.common.bean.MailerDataDTO;
import com.smv.common.bean.EmailNameListDTO;
import com.smv.common.bean.KeyValueMapDTO;
import com.smv.common.bean.ResponseDTO;
import com.smv.common.bean.UserDTO;
import com.smv.common.bean.UserOutletContentDTO;
import com.smv.service.aggregator.AggregationFactory;
import com.smv.service.aggregator.IAggregation;
import com.smv.service.outlet.adaptee.IPublish;
import com.smv.service.outlet.helper.validator.ValidatorResourceManager;
import com.smv.util.config.ApplicationProperties;

/**
 * @author TriNguyen
 *
 */
public class ShareMyVisionPublisher implements IPublish {

    private static final ShareMyVisionPublisher INSTANCE = new ShareMyVisionPublisher();

    protected static final Logger LOGGER = Logger.getLogger(ShareMyVisionPublisher.class);
    
    protected static final String TO_EMAIL_DTO_KEY_VALUE_MAP_KEY = "toEmail"; 
    protected static final String TO_NAME_DTO_KEY_VALUE_MAP_KEY = "toName"; 
    protected static final String FOLDER_NAME_NOTIFICATION_LINK_DTO_KEY_VALUE_MAP_KEY = "folderNameLink"; 
    protected static final String RECIPIENT_NAME_LINK_DTO_KEY_VALUE_MAP_KEY = "name"; 
    
    //
    // Application Properties
    //
    // From Email
    protected static final String DEFAULT_FROM_EMAIL = "dev@sharemyvision.com";
    protected static final String FROM_EMAIL_KEY = "smvpublisher.mailer.from.email";
    // From Name
    protected static final String DEFAULT_FROM_NAME = "ShareMyVision";
    protected static final String FROM_NAME_KEY = "smvpublisher.mailer.from.name";
    // Mailer Event Code
    protected static final String DEFAULT_MAILER_EVENT_CODE = "FolderNameNotification";
    protected static final String MAILER_EVENT_CODE_KEY = "smvpublisher.mailer.event.code";

    // Private constructor prevents instantiation from other classes
    private ShareMyVisionPublisher() {
    }
 
    public static ShareMyVisionPublisher getInstance() {
        return INSTANCE;
    }

	/* (non-Javadoc)
	 * @see com.smv.service.outlet.adaptee.IPublish#publish(java.lang.Long, com.smv.common.bean.UserOutletContentDTO)
	 */
	@Override
	public Boolean publish(Long userId, UserOutletContentDTO content)
			throws Exception {
		String message = "Publishing to ShareMyVision. " +
					    	"\n " +
					    	"userId = " +
					    	userId +
					    	"\n " +
					    	"content = " +
					    	content
					    	;
		LOGGER.info(message);
		
		IAggregation aggregator = AggregationFactory.getAggregationInstance();
		
		try {
			MailerDataDTO mailer = constructMailerDataDTO(userId, content);
			Map<String, String> httpEnv = new HashMap<String, String>();
			aggregator.sendMail(mailer, httpEnv);
		} catch (Exception exception) {
			String errorMessage = "Error in publishing to ShareMyVision. " +
						    	"\n " +
						    	"userId = " +
						    	userId +
						    	"\n " +
						    	"content = " +
						    	content
						    	;
			LOGGER.error(errorMessage);

			Exception rethrownException = new Exception(errorMessage, exception);
			throw rethrownException;
		}
		
		return Boolean.TRUE;
	}

	protected MailerDataDTO constructMailerDataDTO(Long userId, UserOutletContentDTO content) 
	throws IllegalArgumentException, 
			Exception
	{
		// Construct and gather attributes
		String mailerEventCode = getMailerEventCode();
		String fromEmail = getFromEmail();
		String fromName = getFromName();
		EmailNameListDTO toEmailNameList = constructToEmailNameList(userId, content);
		// Use default subject, which has no Velocity replacement key/value holders.
		KeyValueMapDTO subjectData = null;
		KeyValueMapDTO bodyData = constructEmailBodyData(userId, content);

		// Construct result to return to caller
		MailerDataDTO mailerData = new MailerDataDTO();

		// Set attributes of result
		mailerData.setMailerEventCode(mailerEventCode);
		mailerData.setFromEmail(fromEmail);
		mailerData.setFromName(fromName);
		mailerData.setTo(toEmailNameList);
		mailerData.setSubjectData(subjectData);
		mailerData.setBodyData(bodyData);

		// Return result to caller
		return mailerData;
	}

	protected String getMailerEventCode() {
    	ApplicationProperties applicationProperties = ValidatorResourceManager.getApplicationProperties();
		String mailerEventCode = applicationProperties.getSetting(ShareMyVisionPublisher.MAILER_EVENT_CODE_KEY, 
																	ShareMyVisionPublisher.DEFAULT_MAILER_EVENT_CODE);
		return mailerEventCode;
	}
	
	protected String getFromEmail() {
    	ApplicationProperties applicationProperties = ValidatorResourceManager.getApplicationProperties();
		String fromEmail = applicationProperties.getSetting(ShareMyVisionPublisher.FROM_EMAIL_KEY, 
															ShareMyVisionPublisher.DEFAULT_FROM_EMAIL);
		return fromEmail;
	}
	
	protected String getFromName() {
    	ApplicationProperties applicationProperties = ValidatorResourceManager.getApplicationProperties();
		String fromName = applicationProperties.getSetting(ShareMyVisionPublisher.FROM_NAME_KEY, 
															ShareMyVisionPublisher.DEFAULT_FROM_NAME);
		return fromName;
	}
	
	protected EmailNameListDTO constructToEmailNameList(Long userId, UserOutletContentDTO content) 
	throws IllegalArgumentException, 
		Exception
	{
		// Construct result to return to caller
		EmailNameListDTO emailNameList = new EmailNameListDTO();

		// Set attributes of result
		setToEmailNameEntries(userId, content, emailNameList);

		// Return result to caller
		return emailNameList;
	}
	
	protected void setToEmailNameEntries(Long userId, UserOutletContentDTO content, EmailNameListDTO emailNameList) 
	throws IllegalArgumentException, 
			Exception
	{
		// Construct and gather attributes for Mailer Data DTO
		KeyValueMapDTO contentKeyValueMap = content.getData();
		List<String> toEmailList = constructToEmailList(userId, contentKeyValueMap);
		List<String> toNameList = constructToNameList(userId, contentKeyValueMap);
		
		// Throw exception if both lists of to/recipient lists are not of the same size.
		if (((toEmailList == null) && (toNameList == null)) || 
			((toEmailList == null) && (toNameList != null)) || 
			((toEmailList != null) && (toNameList == null)) || 
			((toEmailList != null) && (toNameList != null) && (toEmailList.isEmpty()) && (toNameList.isEmpty())) ||
			((toEmailList != null) && (toNameList != null) && (!toEmailList.isEmpty()) && (toNameList.isEmpty())) ||
			((toEmailList != null) && (toNameList != null) && (toEmailList.isEmpty()) && (!toNameList.isEmpty())) ||
			((toEmailList != null) && (toNameList != null) && (toEmailList.size() != toNameList.size()))
		) {
			String errorMessage = "Error in publishing to ShareMyVision. " +
							    	"\n " +
							    	"userId = " +
							    	userId +
							    	"\n " +
							    	"content = " +
							    	content +
							    	"\n " +
							    	"toEmailList is not of the same size as toNameList. " +
							    	"\n " +
							    	"toEmailList = " +
							    	toEmailList +
							    	"\n " +
							    	"toNameList = " +
							    	toNameList
							    	;
			LOGGER.error(errorMessage);
			throw new IllegalArgumentException(errorMessage);
		}
		
		// Set attributes of result
		for (int index = 0; index < toEmailList.size(); index++) {
			EmailNameEntryDTO emailNameEntry = new EmailNameEntryDTO();
			emailNameEntry.setEmail(toEmailList.get(index));
			emailNameEntry.setName(toNameList.get(index));
			emailNameList.getEntries().add(emailNameEntry);
		}
	}
	
	protected List<String> constructToEmailList(Long userId, KeyValueMapDTO contentKeyValueMap) 
	throws IllegalArgumentException, 
			Exception
	{
		List<String> toEmailList = new ArrayList<String>();
		List<KeyValueEntryDTO> keyValueEntryList = contentKeyValueMap.getEntries();
		for (KeyValueEntryDTO keyValueEntry : keyValueEntryList) {
			if (ShareMyVisionPublisher.TO_EMAIL_DTO_KEY_VALUE_MAP_KEY.equals(keyValueEntry.getKey())) {
				toEmailList.add(keyValueEntry.getValue());
			}
		}
		
		if (toEmailList.isEmpty()) {
			String toEmail = getToEmailFromUser(userId);
			toEmailList.add(toEmail);
		}
		
		return toEmailList;
	}
	
	protected String getToEmailFromUser(Long userId) {
		IAggregation aggregator = AggregationFactory.getAggregationInstance();
		Map<String, String> httpEnv = new HashMap<String, String>();
		UserDTO user = new UserDTO();
		user.setUserId(userId);
		ResponseDTO response = aggregator.getUser(user, httpEnv);
		user = (UserDTO) response.getValue();
		String toEmail = user.getUserEmail();
		return toEmail;
	}

	protected List<String> constructToNameList(Long userId, KeyValueMapDTO contentKeyValueMap) 
	throws IllegalArgumentException, 
			Exception
	{
		List<String> toNameList = new ArrayList<String>();
		List<KeyValueEntryDTO> keyValueEntryList = contentKeyValueMap.getEntries();
		for (KeyValueEntryDTO keyValueEntry : keyValueEntryList) {
			if (ShareMyVisionPublisher.TO_NAME_DTO_KEY_VALUE_MAP_KEY.equals(keyValueEntry.getKey())) {
				toNameList.add(keyValueEntry.getValue());
			}
		}
		
		if (toNameList.isEmpty()) {
			String toName = getToNameFromUser(userId);
			toNameList.add(toName);
		}
		
		return toNameList;
	}

	protected String getToNameFromUser(Long userId) {
		IAggregation aggregator = AggregationFactory.getAggregationInstance();
		Map<String, String> httpEnv = new HashMap<String, String>();
		UserDTO user = new UserDTO();
		user.setUserId(userId);
		ResponseDTO response = aggregator.getUser(user, httpEnv);
		user = (UserDTO) response.getValue();
		String toName = user.getUserDisplayName();
		return toName;
	}

	protected KeyValueMapDTO constructEmailBodyData(Long userId, UserOutletContentDTO content) {
		KeyValueMapDTO emailBodyData = null;
		
		KeyValueMapDTO contentKeyValueMap = content.getData();
		List<KeyValueEntryDTO> keyValueEntryList = contentKeyValueMap.getEntries();
		for (KeyValueEntryDTO keyValueEntry : keyValueEntryList) {
			if (ShareMyVisionPublisher.FOLDER_NAME_NOTIFICATION_LINK_DTO_KEY_VALUE_MAP_KEY.equals(keyValueEntry.getKey())) {
				KeyValueEntryDTO bodyDataEntry = new KeyValueEntryDTO();
				bodyDataEntry.setKey(ShareMyVisionPublisher.FOLDER_NAME_NOTIFICATION_LINK_DTO_KEY_VALUE_MAP_KEY);
				bodyDataEntry.setValue(keyValueEntry.getValue());
				emailBodyData = new KeyValueMapDTO();
				emailBodyData.getEntries().add(bodyDataEntry);
			}
			if (ShareMyVisionPublisher.RECIPIENT_NAME_LINK_DTO_KEY_VALUE_MAP_KEY.equals(keyValueEntry.getKey())) {
				KeyValueEntryDTO bodyDataEntry = new KeyValueEntryDTO();
				bodyDataEntry.setKey(ShareMyVisionPublisher.RECIPIENT_NAME_LINK_DTO_KEY_VALUE_MAP_KEY);
				bodyDataEntry.setValue(keyValueEntry.getValue());
				emailBodyData = new KeyValueMapDTO();
				emailBodyData.getEntries().add(bodyDataEntry);
			}
		}

		
		return emailBodyData;
	}
}
