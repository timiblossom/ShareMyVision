package com.smv.service.mailer.helper;

import java.util.HashMap;
import java.util.Map;

import org.joda.time.DateTime;

import com.smv.util.config.ApplicationProperties;



/**
 * @author Minh Do
 * 07/22/2010
 */
public class SmvMailerData {
	private final String baseUrl;
	

	public SmvMailerData(ApplicationProperties settings) {
		this.baseUrl = settings.getSetting("smtpBaseUrl", "http://localhost:8080/mailer");
	}
	
	public Map<String, Object> apply(Map<String, Object> otherValues) {
		Map<String, Object> map = new HashMap<String, Object>(otherValues);
		
		map.put("baseUrl", this.baseUrl);
		map.put("date",    new DateTime());
		map.put("service", new CompanyInfo());
		
		return map;
	}
	
	//TODO: pull this from a config / DB setting, it will be part of partner branding later
	public static class CompanyInfo {
		private String companyName          = "ShareMyVision";
		private String supportEmail         = "support@sharmyvision.com";
		private String copyRightCompanyName = "ShareMyVision, Inc.";
		
		public String getCompanyName() {
			return companyName;
		}
		public String getSupportEmail() {
			return supportEmail;
		}
		public String getCopyRightCompanyName() {
			return copyRightCompanyName;
		}
	}
}
