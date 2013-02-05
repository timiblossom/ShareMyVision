/**
 * 
 */
package com.smv.util.thread.generator;

import com.smv.service.catalog.CatalogImpl;
import com.smv.util.thread.AbstractBaseGenerator;

/**
 * @author TriNguyen
 *
 */
public class CatalogImplGenerator extends AbstractBaseGenerator {


	public CatalogImplGenerator() {
		super("com.smv.service.catalog.ICatalog");
	}

	@Override
	public Object generateInstance() throws Exception {
		return new CatalogImpl();
	}

}
