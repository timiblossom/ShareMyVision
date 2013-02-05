package com.smv.util.db;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Restrictions;
import org.hibernate.type.ManyToOneType;
import org.hibernate.type.SetType;
import org.hibernate.type.Type;


/**
 * @author Minh Do
 * 03/2010
 */

public abstract class AbstractDAO {
	
	private final static Logger log = Logger.getLogger(AbstractDAO.class);
	
	protected AbstractDO iServerDO = null;	
	protected AbstractDO iClientDO = null;
	protected int iOperation = AbstractDO.NO_OP;
	protected SmvHibernateSession iSession;
	protected Serializable id = null;

	public AbstractDAO() {		
	}
	
	public AbstractDAO(AbstractDO clientDO) {
		this.iOperation= clientDO.getOperation();	
		setClientDO(clientDO);
		initialize();
	}
	
	public AbstractDAO(AbstractDO clientDO, SmvHibernateSession ysiSession) {
		iSession = ysiSession;
		this.iOperation= clientDO.getOperation();	
		setClientDO(clientDO);
		initialize();		
	}
	

	protected void initialize() {
		if (iOperation != AbstractDO.CREATE) {
			this.id = getHibernateSessionFactory().getIdentifier(iClientDO);
		}
				
		loadServerVO();
		reconcile();		
				
		if (iOperation == AbstractDO.UPDATE) {
			collate();
		}
		
		initializeSubDAOs();		
		setClientDO(iServerDO);
	}
	
	protected void loadServerVO() {		

		if (iOperation != AbstractDO.CREATE && 
			iOperation != AbstractDO.NO_OP) {
			
			SmvHibernateSession readSession = getHibernateSessionFactory().getSession();
			try {									                          
		        readSession.beginTransaction();
				List<String> setPropertiesList = getAssociatedProperties();			
			
				if (setPropertiesList.size() > 0) {							
				   Criteria crit = readSession.createCriteria(iClientDO.getClass());
											
				   for(String field : setPropertiesList) {
				      crit.setFetchMode(field, FetchMode.JOIN);					
				   }
				   iServerDO = (AbstractDO) crit.add(Restrictions.idEq(id)).uniqueResult();				
			    } else {
				   iServerDO = (AbstractDO) readSession.get(iClientDO.getClass(), id);
			    }

			    readSession.smvCommitTransaction();
			    
			} catch (Exception e) {
			    readSession.smvRollback();	
			}
			
			if (iServerDO == null) {
		    	throw new HibernateException("Record non-existed - can't update nor delete");
		    }
			
		} else {
			iServerDO = iClientDO;
		}

		
		if (iOperation == AbstractDO.CREATE || 
			iOperation == AbstractDO.UPDATE || 
		    iOperation == AbstractDO.DELETE) {
			if (iSession == null)
				iSession = getHibernateSessionFactory().getSession();
		} 
	}
					
	protected SmvHibernateSession getSession() {
		return iSession;
	}

	public AbstractDO getClientDO() {		
		return iClientDO;	
	}

	public AbstractDO getServerDO() {		
		return iServerDO;		
	}
	
	public int getOperation() {
		return iOperation;
	}
	
	public boolean save(boolean isTopLevel, boolean isInverse) {
	
		if (iOperation == AbstractDO.NO_OP || 
			iOperation == AbstractDO.FETCH)	
			return false;		
        
		try {
			if (isTopLevel) {	
				beginTransaction();
			}

			if (isInverse){
				if (iOperation == AbstractDO.DELETE){				
					persist();			
				}		
			} else  {			
				if (iOperation == AbstractDO.CREATE) {				
					persist();			
				}		
			}				
		
			saveSubBOs();				

			if (isInverse){			
				if (iOperation != AbstractDO.DELETE && iOperation != AbstractDO.NO_OP){						
					persist();			
				}		
			} else {		  
				if (iOperation != AbstractDO.CREATE && iOperation != AbstractDO.NO_OP){
					persist();		   
				}		
			}				

			if (isTopLevel){			
				commitTransaction();		
			}
		} catch (Exception e) {
			log.error(e.getMessage());
			e.printStackTrace();
			try {
				rollbackTransaction();
			} catch (Exception ex) {
				log.error(ex.getMessage());
				e.printStackTrace();
			}
			return false;
		}

		iServerDO = iClientDO;
		setClientDO(iClientDO);
		
		return true;

	}

	protected void persist() {
		if (iOperation == AbstractDO.CREATE) {			
			create();					
		} else if (iOperation == AbstractDO.UPDATE) {				
			update();		
		} else if (iOperation == AbstractDO.DELETE) {				
			delete();		
		}	
			
		iClientDO.setOperation(AbstractDO.NO_OP);//reset
	}


	protected void create() {
		try {			
			if (iSession == null) {								
				throw new HibernateException("session is null");
			} 
			id = iSession.save(iClientDO); 
		}
		catch (HibernateException re) {
			throw re;
		}
	}

	protected void update() {		
		try {			
			if (iSession == null)				
				throw new HibernateException("session is null");			
			iSession.update(iClientDO); 
		}
		catch (HibernateException re) {
			throw re;
		}
	}

	protected void delete() {
		try {			
			if (iSession == null)				
				throw new HibernateException("session is null");			
            
			if (id != null) {
				iClientDO = (AbstractDO) iSession.get(iClientDO.getClass(), id);
				iSession.delete(iClientDO);
			} else {
				throw new HibernateException("Id is null");
			}
		}
		catch (HibernateException re) {
			throw re;
		}
	}

	
	protected List<String> getAssociatedProperties() {
		List<String> setPropertiesList = new ArrayList<String>();
		
		String[] ss = getHibernateSessionFactory().getPropertyNames(iClientDO);

		for(String s : ss) {
			Type o = getHibernateSessionFactory().getPropertyType(s, iClientDO);
			if (o instanceof SetType) {						
				String propertyName = ((SetType) o).getRole();
				int index = propertyName.lastIndexOf('.');
				setPropertiesList.add(propertyName.substring(index+1)); 
			} else if (o instanceof ManyToOneType) {
				String name = s;
				int index = name.lastIndexOf('.');
				setPropertiesList.add(name.substring(index+1));				
			}
		}
		
		return setPropertiesList;
	}


	protected void beginTransaction() {
		iSession.smvbeginTransaction();
	}

	protected void commitTransaction() {
		iSession.smvCommitTransaction();
	}

	protected void rollbackTransaction() {
		iSession.smvRollback();
	}


	protected void setClientDO(AbstractDO vo) {
		iClientDO = vo;
	}
	
	
	protected boolean reconcile() {				

		boolean result = true; 		
		
		if (iOperation != AbstractDO.NO_OP) {						
			result = result & reconcileDerivedValues();

            if (iOperation == AbstractDO.UPDATE) {
            	result = result & reconcileDefaultValues();
            }
		}		

		return result;	

	}
	
	protected void collate() {		
	}
	
	protected void collate(Set clientDoSet, Set serverDoSet, Comparator comparator) {
		Object[] mergedResult = collateHelper((AbstractDO[]) clientDoSet.toArray(new AbstractDO[0]), 
                                              (AbstractDO[]) serverDoSet.toArray(new AbstractDO[0]), 
                                              comparator);

		serverDoSet.clear();

		for(Object obj : mergedResult) {
			serverDoSet.add(obj);
		}
	}
	
	
	private Object[] collateHelper(AbstractDO[] clientSubDoList, AbstractDO[] serverSubDoList, Comparator comparator) {

		List<AbstractDO> serverChildrenArray = new ArrayList<AbstractDO>();
							
		//TODO: it would help to cut down 1 loop if we have equals() and hashCode() methods in the VOs
		for(AbstractDO clientSubVO : clientSubDoList) {
			boolean isEqualToOne = false;
			
			for(AbstractDO serverSubVO : serverSubDoList) {			
				if (comparator.compare(clientSubVO, serverSubVO) == 0) {
					isEqualToOne = true;
					if (clientSubVO.getOperation() == AbstractDO.DELETE) {						
						serverChildrenArray.add(clientSubVO);
					} else {
						//TODO: work on merging here so that we don't need to merge later at SubBO level
						//AbstractBaseVO modifiedClientVO = helper.merge(clientSubVO, serverSubVoSet);						
						serverChildrenArray.add(clientSubVO);
					}
				}				
			}	
			
			if (!isEqualToOne) {//Case: OPERATION_CREATE - new object				
				serverChildrenArray.add(clientSubVO);
			}
			
		}		
				
		//Take all the rest in the server list and reserve them
		//Again, we don't need these 2 loops complication if we have equals() and hashCode() in VOs
		List<AbstractDO> extraChildrenFromServer = new ArrayList<AbstractDO>();
		for(AbstractDO serverSubVO : serverSubDoList) {	
			boolean isEqualToOne = false;						
			for(AbstractDO vo : serverChildrenArray) {				
				if (comparator.compare(vo, serverSubVO) == 0) {
					isEqualToOne = true;
				}
			}
			if (!isEqualToOne) {				
				extraChildrenFromServer.add(serverSubVO);
			}
		}
		
        //Now add all extra server children to the resulted list	
		serverChildrenArray.addAll(extraChildrenFromServer);
		
		return serverChildrenArray.toArray();
	}
	
	protected abstract void initializeSubDAOs();	
	protected abstract boolean saveSubBOs();
	protected abstract boolean reconcileDefaultValues();
	protected abstract boolean reconcileDerivedValues();
	
	
	public abstract AbstractDO merge(AbstractDO clientDO, AbstractDO serverDO);	
	public abstract HibernateSessionFactory getHibernateSessionFactory();
	
	
	
}
