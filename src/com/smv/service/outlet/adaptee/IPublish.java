package com.smv.service.outlet.adaptee;

import com.smv.common.bean.UserOutletContentDTO;

/**
 * @author TriNguyen
 *
 */
public interface IPublish {

	public Boolean publish(Long userId, UserOutletContentDTO content) throws Exception;

}
