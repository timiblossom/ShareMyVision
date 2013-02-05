package com.smv.util.thread.generator;

import com.smv.service.outlet.OutletImpl;
import com.smv.util.thread.AbstractBaseGenerator;

/**
 * @author TriNguyen
 *
 */
public class OutletImplGenerator extends AbstractBaseGenerator {

	public OutletImplGenerator() {
		super("com.smv.service.outlet.IOutlet");
	}

	@Override
	public Object generateInstance() throws Exception {
		return new OutletImpl();
	}

}
