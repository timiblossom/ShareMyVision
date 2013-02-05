package com.smv.service.outlet;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import com.smv.common.bean.OutletDTO;
import com.smv.common.bean.UserOutletContentDTO;
import com.smv.common.bean.UserOutletDTO;
import com.smv.service.outlet.helper.DisableOutletForUserHelper;
import com.smv.service.outlet.helper.EnableOutletForUserHelper;
import com.smv.service.outlet.helper.GetOutletForUserHelper;
import com.smv.service.outlet.helper.GetOutletHelper;
import com.smv.service.outlet.helper.PublishHelper;

public class OutletImpl implements IOutlet {

	protected static Logger LOGGER = Logger.getLogger(OutletImpl.class);

	public OutletImpl() {
	    BasicConfigurator.configure();
	}

	/* (non-Javadoc)
	 * @see com.smv.service.outlet.IOutlet#getOutlet()
	 */
	@Override
	public OutletDTO[] getOutlet() throws Exception {
		return GetOutletHelper.getOutlet();
	}

	/* (non-Javadoc)
	 * @see com.smv.service.outlet.IOutlet#getOutletForUser(java.lang.Long)
	 */
	@Override
	public OutletDTO[] getOutletForUser(Long userId) throws Exception {
		return GetOutletForUserHelper.getOutletForUser(userId);
	}

	/* (non-Javadoc)
	 * @see com.smv.service.outlet.IOutlet#enableOutletForUser(com.smv.common.bean.UserOutletDTO)
	 */
	@Override
	public UserOutletDTO enableOutletForUser(UserOutletDTO userOutlet) throws Exception {
		return EnableOutletForUserHelper.enableOutletForUser(userOutlet);
	}

	/* (non-Javadoc)
	 * @see com.smv.service.outlet.IOutlet#disableOutletForUser(com.smv.common.bean.UserOutletDTO)
	 */
	@Override
	public UserOutletDTO disableOutletForUser(UserOutletDTO userOutlet) throws Exception {
		return DisableOutletForUserHelper.disableOutletForUser(userOutlet);
	}

	/* (non-Javadoc)
	 * @see com.smv.service.outlet.IOutlet#publishToOutlets(java.lang.Long, com.smv.common.bean.UserOutletContentDTO, com.smv.common.bean.OutletDTO[])
	 */
	@Override
	public Boolean publishToOutlets(Long userId, 
									UserOutletContentDTO content,
									OutletDTO[] outlets) 
	throws Exception {
		return PublishHelper.publishToOutlets(userId, content, outlets);
	}

}
