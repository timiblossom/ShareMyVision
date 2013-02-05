package com.smv.util.db;

import java.sql.Timestamp;
import java.util.Date;

import org.apache.log4j.Logger;

/**
 * @author Tri Nguyen
 * 12/2010
 */

public abstract class AbstractDatedDAO extends AbstractDAO {

	private static final Logger log = Logger.getLogger(AbstractDatedDAO.class);

	public AbstractDatedDAO(AbstractDatedDO clientDO) {
		super(clientDO);
	}

	protected AbstractDatedDO getAbstractVersionedDatedVO() {
		return (AbstractDatedDO) iClientDO;
	}

	@Override
	protected void initializeSubDAOs() {
	}

	@Override
	protected boolean reconcileDerivedValues() {
		return false;
	}
	

	@Override
	protected boolean reconcileDefaultValues() {

		iServerDO = merge(getClientDO(), getServerDO());

		return true;
	}

	@Override
	protected boolean saveSubBOs() {		
		return false;
	}

	@Override
	public AbstractDO merge(AbstractDO obj1, AbstractDO obj2) {
		AbstractDatedDO clientVO = (AbstractDatedDO) obj1;
		AbstractDatedDO serverVO = (AbstractDatedDO) obj2;

		if (obj1 == null) {
			log.debug("obj1 or clientVO is null - failed to continue merging");
			return obj1;
		}

		if (obj2 == null) {
			log.debug("obj2 or serverVO is null - failed to continue merging");
			return obj2;
		}

		
		if (obj1 == obj2) {
			return obj1;
		}

		if ((clientVO.getId() != null) && (clientVO.getId() != 0)) {
			serverVO.setId(clientVO.getId());
		}
		
		if (clientVO.getOperation() == AbstractDO.DELETE ||
			clientVO.getOperation() == AbstractDO.UPDATE) {
			serverVO.setUpdatedOn(new Date());
		}
		
		if (clientVO.getOperation() == AbstractDO.CREATE) {			
			serverVO.setCreatedOn( new Timestamp(System.currentTimeMillis()) );
			serverVO.setUpdatedOn(new Date());
		}
		
		return serverVO;
	}

}
