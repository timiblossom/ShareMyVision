package com.smv.service.aggregator.helper;


import com.smv.common.bean.MailerDataDTO;
import com.smv.common.bean.ResponseDTO;
import com.smv.util.thread.LocalThreadObjectsHolder;

/**
 * @author Minh Do
 * 011/06/2010
 */
public class InstanceGenerator {

	static {
		synchronized(LocalThreadObjectsHolder.class) {
			LocalThreadObjectsHolder.addInstanceGenerator(new ResponseDTOGenerator());

		}
	}



	public static ResponseDTO getResponseDTOInstance() {
		ResponseDTO bean = LocalThreadObjectsHolder.getThreadInstance(ResponseDTO.class);
		bean.setMessage(null);
		bean.setStatusCode(-1);
		bean.setValue(null);
		return bean;
	}



	public  MailerDataDTO getMailerDataDTOInstance() {
		return LocalThreadObjectsHolder.getThreadInstance(MailerDataDTO.class);		
	}


}
