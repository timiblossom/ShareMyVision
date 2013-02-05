/**
 * 
 */
package com.smv.service.mailer;

/**
 * @author TriNguyen
 *
 */
public class MailTemplateCouplet {
	
	protected String subject;
	protected String body;
	
	/**
	 * 
	 */
	protected MailTemplateCouplet() {
		super();
	}

	/**
	 * 
	 */
	public MailTemplateCouplet(String subject, String body) {
		super();
		setSubject(subject);
		setBody(body);
	}

	/**
	 * @return the subject
	 */
	public String getSubject() {
		return subject;
	}

	/**
	 * @param subject the subject to set
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}

	/**
	 * @return the body
	 */
	public String getBody() {
		return body;
	}

	/**
	 * @param body the body to set
	 */
	public void setBody(String body) {
		this.body = body;
	}

}
