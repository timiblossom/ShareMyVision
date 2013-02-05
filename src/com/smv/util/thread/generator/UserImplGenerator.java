package com.smv.util.thread.generator;



/**
 * @author Minh Do
 * 05/2010
 */

import com.smv.service.user.UserImpl;
import com.smv.util.thread.AbstractBaseGenerator;

public class UserImplGenerator extends AbstractBaseGenerator {

	public UserImplGenerator() {
		super("com.smv.service.user.IUser");
	}

	@Override
	public Object generateInstance() throws Exception {
		return new UserImpl();
	}

}
