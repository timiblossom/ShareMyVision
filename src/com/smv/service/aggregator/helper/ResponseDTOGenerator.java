package com.smv.service.aggregator.helper;



import com.smv.common.bean.ResponseDTO;
import com.smv.util.thread.AbstractBaseGenerator;



public class ResponseDTOGenerator extends AbstractBaseGenerator {

	public ResponseDTOGenerator() {
		super("com.smv.common.bean.ResponseDTO");	
	}

	@Override
	public Object generateInstance() throws Exception {
        return new ResponseDTO();
	}


}
