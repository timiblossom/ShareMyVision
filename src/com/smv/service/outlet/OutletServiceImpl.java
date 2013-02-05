package com.smv.service.outlet;

import javax.jws.WebService;

import com.smv.common.bean.OutletDTO;
import com.smv.common.bean.UserOutletContentDTO;
import com.smv.common.bean.UserOutletDTO;
import com.smv.common.exception.SmvErrorCode;
import com.smv.common.exception.SmvServiceException;
import com.smv.util.thread.InstanceGenerator;


@WebService(endpointInterface = "com.smv.service.outlet.IOutletService", serviceName = "OutletService", targetNamespace = "http://smv") 
public class OutletServiceImpl implements IOutletService {

	/* (non-Javadoc)
	 * @see com.smv.service.outlet.IOutletService#getOutlet()
	 */
	@Override
	public OutletDTO[] getOutlet() throws SmvServiceException {
		try {
			return InstanceGenerator.getOutletImplInstance().getOutlet();
		} catch (SmvServiceException wse) {
			throw wse;
		} catch (Exception exception) {
			throw new SmvServiceException(SmvErrorCode.OUTLET_SERVICE_EXCEPTION, SmvErrorCode.OUTLET_SERVICE_EXCEPTION_MSG + exception);
		}
	}

	/* (non-Javadoc)
	 * @see com.smv.service.outlet.IOutletService#getOutletForUser(java.lang.Long)
	 */
	@Override
	public OutletDTO[] getOutletForUser(Long userId) throws SmvServiceException {
		try {
			return InstanceGenerator.getOutletImplInstance().getOutletForUser(userId);
		} catch (SmvServiceException wse) {
			throw wse;
		} catch (Exception exception) {
			throw new SmvServiceException(SmvErrorCode.OUTLET_SERVICE_EXCEPTION, SmvErrorCode.OUTLET_SERVICE_EXCEPTION_MSG + exception);
		}
	}

	/* (non-Javadoc)
	 * @see com.smv.service.outlet.IOutletService#enableOutletForUser(com.smv.common.bean.UserOutletDTO)
	 */
	@Override
	public UserOutletDTO enableOutletForUser(UserOutletDTO userOutlet)
			throws SmvServiceException {
		try {
			return InstanceGenerator.getOutletImplInstance().enableOutletForUser(userOutlet);
		} catch (SmvServiceException wse) {
			throw wse;
		} catch (Exception exception) {
			throw new SmvServiceException(SmvErrorCode.OUTLET_SERVICE_EXCEPTION, SmvErrorCode.OUTLET_SERVICE_EXCEPTION_MSG + exception);
		}
	}

	/* (non-Javadoc)
	 * @see com.smv.service.outlet.IOutletService#disableOutletForUser(com.smv.common.bean.UserOutletDTO)
	 */
	@Override
	public UserOutletDTO disableOutletForUser(UserOutletDTO userOutlet)
			throws SmvServiceException {
		try {
			return InstanceGenerator.getOutletImplInstance().disableOutletForUser(userOutlet);
		} catch (SmvServiceException wse) {
			throw wse;
		} catch (Exception exception) {
			throw new SmvServiceException(SmvErrorCode.OUTLET_SERVICE_EXCEPTION, SmvErrorCode.OUTLET_SERVICE_EXCEPTION_MSG + exception);
		}
	}

	/* (non-Javadoc)
	 * @see com.smv.service.outlet.IOutletService#publishToOutlets(java.lang.Long, com.smv.common.bean.UserOutletContentDTO, com.smv.common.bean.OutletDTO[])
	 */
	@Override
	public Boolean publishToOutlets(Long userId, 
									UserOutletContentDTO content,
									OutletDTO[] outlets) 
	throws SmvServiceException{
		try {
			return InstanceGenerator.getOutletImplInstance().publishToOutlets(  userId, 
																				content,
																				outlets);
		} catch (SmvServiceException wse) {
			throw wse;
		} catch (Exception exception) {
			throw new SmvServiceException(SmvErrorCode.OUTLET_SERVICE_EXCEPTION, SmvErrorCode.OUTLET_SERVICE_EXCEPTION_MSG + exception);
		}
	}

}
