/**
 * 
 */
package com.smv.util.thread.generator;

import com.smv.service.mailer.MailerImpl;
import com.smv.util.thread.AbstractBaseGenerator;

/**
 * @author TriNguyen
 *
 */
public class MailerImplGenerator extends AbstractBaseGenerator {

	public MailerImplGenerator() {
		super("com.smv.service.mailer.IMailer");
	}

	/* (non-Javadoc)
	 * @see com.smv.util.thread.AbstractBaseGenerator#generateInstance()
	 */
	@Override
	public Object generateInstance() throws Exception {
		return new MailerImpl();
	}

}
