/**
 * 
 */
package com.smv.service.outlet.helper;

import java.util.List;

import org.apache.log4j.Logger;

import com.smv.common.bean.UserOutletDTO;
import com.smv.common.exception.SmvServiceException;
import com.smv.service.outlet.db.dao.UserOutletDAO;
import com.smv.service.outlet.db.dbobject.UserOutletDO;
import com.smv.service.user.helper.IsUserExistedHelper;

/**
 * @author TriNguyen
 *
 */
public class UserOutletHelper {

	private static final Logger LOGGER = Logger.getLogger(UserOutletHelper.class);

    protected UserOutletHelper() {
    }

	public static boolean doesUserOutletExist(Long userId, Long outletId) {
		List<UserOutletDO> userOutletList = UserOutletDAO.getUserOutletByUserIdAndOutletId(userId, outletId);
		if ((userOutletList == null) || (userOutletList.isEmpty()) || (userOutletList.size() < 1)) {
			return false;
		}
		return true;
	}
	
	public static void checkUserOutlet(UserOutletDTO userOutlet) throws IllegalArgumentException, SmvServiceException {
    	// Basic non-null parameter checking
    	ParameterChecker.checkUserOutlet(userOutlet);
    	
    	// Verify Outlet exists
    	OutletHelper.checkOutletTypeExists(userOutlet.getOutlet().getId());
	}

}
