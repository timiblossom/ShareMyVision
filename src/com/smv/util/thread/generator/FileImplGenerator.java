package com.smv.util.thread.generator;


/**
 * @author Minh Do
 * 05/2010
 */

import com.smv.service.file.FileImpl;
import com.smv.util.thread.AbstractBaseGenerator;

public class FileImplGenerator extends AbstractBaseGenerator {

	public FileImplGenerator() {
		super("com.smv.service.file.IFile");
	}

	@Override
	public Object generateInstance() throws Exception {
		return new FileImpl();
	}

}
