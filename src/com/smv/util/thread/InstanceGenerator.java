package com.smv.util.thread;


import javax.xml.parsers.DocumentBuilder;
import javax.xml.transform.Transformer;
import javax.xml.xpath.XPath;

import com.smv.service.catalog.ICatalog;
import com.smv.service.file.IFile;
import com.smv.service.mailer.IMailer;
import com.smv.service.outlet.IOutlet;
import com.smv.service.payment.IPayment;
import com.smv.service.user.IUser;
import com.smv.util.thread.generator.CatalogImplGenerator;
import com.smv.util.thread.generator.DocumentBuilderGenerator;
import com.smv.util.thread.generator.FileImplGenerator;
import com.smv.util.thread.generator.MailerImplGenerator;
import com.smv.util.thread.generator.OutletImplGenerator;
import com.smv.util.thread.generator.TransformerGenerator;
import com.smv.util.thread.generator.UserImplGenerator;
import com.smv.util.thread.generator.XPathGenerator;

/**
 * @author Minh Do
 * 03/01/2010
 */
public class InstanceGenerator {

	static {
		synchronized(LocalThreadObjectsHolder.class) {
			LocalThreadObjectsHolder.addInstanceGenerator(new XPathGenerator());
			LocalThreadObjectsHolder.addInstanceGenerator(new TransformerGenerator());
			LocalThreadObjectsHolder.addInstanceGenerator(new DocumentBuilderGenerator());
			LocalThreadObjectsHolder.addInstanceGenerator(new UserImplGenerator());
			LocalThreadObjectsHolder.addInstanceGenerator(new FileImplGenerator());
			LocalThreadObjectsHolder.addInstanceGenerator(new CatalogImplGenerator());
			LocalThreadObjectsHolder.addInstanceGenerator(new MailerImplGenerator());
			LocalThreadObjectsHolder.addInstanceGenerator(new OutletImplGenerator());
		}
	}

	public static DocumentBuilder getDocumentBuilderInstance() {
		return LocalThreadObjectsHolder.getThreadInstance(javax.xml.parsers.DocumentBuilder.class);
	}

	public static XPath getXPathInstance() {
		return LocalThreadObjectsHolder.getThreadInstance(javax.xml.xpath.XPath.class);
	}

	public static Transformer getTransformerInstance() {
		return LocalThreadObjectsHolder.getThreadInstance(javax.xml.transform.Transformer.class);
	}
	
	public static IUser getUserImplInstance() {
		return LocalThreadObjectsHolder.getThreadInstance(com.smv.service.user.IUser.class);
	}

	public static IPayment getPaymentImplInstance() {
		return LocalThreadObjectsHolder.getThreadInstance(com.smv.service.payment.IPayment.class);
	}

	public static ICatalog getCatalogImplInstance() {
		return LocalThreadObjectsHolder.getThreadInstance(com.smv.service.catalog.ICatalog.class);
	}

	public static IFile getFileImplInstance() {
		return LocalThreadObjectsHolder.getThreadInstance(com.smv.service.file.IFile.class);
	}

	public static IMailer getMailerImplInstance() {
		return LocalThreadObjectsHolder.getThreadInstance(com.smv.service.mailer.IMailer.class);
	}

	public static IOutlet getOutletImplInstance() {
		return LocalThreadObjectsHolder.getThreadInstance(com.smv.service.outlet.IOutlet.class);
	}

}
