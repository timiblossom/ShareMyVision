package com.smv.test.dao.user;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.smv.service.user.db.dao.SessionDAO;
import com.smv.service.user.db.dbobject.SessionDO;
import com.smv.service.user.db.dbobject.UserDO;
import com.smv.test.dao.AbstractTestDAO;
import com.smv.util.db.AbstractDO;

public class TestSessionDAO extends AbstractTestDAO {

	private static final Logger logger = Logger.getLogger(TestSessionDAO.class);

	public TestSessionDAO() {
		super();
		super.setLogger(logger);
	}

	public void testInsertSession() {
		// Create
		SessionDO session = new SessionDO();

		TestUserDAO userTest = new TestUserDAO();
		UserDO user = userTest.createNewUserWithIdOne();

		// Set attributes of DO
		session.setId(getRandomNumberGenerator().nextLong());
		session.setGuid("some_random_guid" + getRandomNumberGenerator().nextGaussian());
		session.setUid(user.getId());
		try {
			session.setClientIp (InetAddress.getLocalHost().getHostAddress());
		} catch (UnknownHostException uhe) {
			String errorMessage = "Unable to get localhost address: " + uhe.toString();
			log(errorMessage);
		}
		session.setLastLogin (new Date());
		session.setUserAgent ("Mozilla");
		session.setLoggedIn(Boolean.valueOf(true));
		//session.setVersionId(1L);

		session.setOperation(AbstractDO.CREATE);

		SessionDAO sessionBo = new SessionDAO(session);
		sessionBo.save(true, false);

		System.out.println("session id : " + session.getId());

		SessionDO sessionTemVO = new SessionDO();
		sessionTemVO.setOperation(AbstractDO.FETCH);
		sessionTemVO.setId(session.getId());
		sessionBo = new SessionDAO(sessionTemVO);
		sessionBo.save(true, false);
		sessionTemVO = (SessionDO) sessionBo.getServerDO();
		System.out.println("UID : " + sessionTemVO.getUid());
	}

	public void testGetAllSessions() {
		List<SessionDO> allSessions = SessionDAO.getAllSessions();

		// Get and print out size of all sessions
		String allSessionsSize = "size : " + allSessions.size();
		System.out.println(allSessionsSize);
		log(allSessionsSize);
		
		// Print out all sessions
		for (SessionDO session : allSessions) {
			System.out.println(session);
			log(session);
		}
	}
	
	public void testGetSessionById() {
		SessionDO session = SessionDAO.getSessionById(1L);
		System.out.println(session);
		log(session);
	}
	
	public SessionDO createNewSessionWithIdOne() {
		// Create
		SessionDO session = SessionDAO.getSessionById(1L);
		if (session == null) {
			TestUserDAO userTest = new TestUserDAO();
			UserDO user = userTest.createNewUserWithIdOne();

			session = new SessionDO();

			// Set attributes of DO
			session.setId(1L);
			session.setGuid("some_random_guid" + getRandomNumberGenerator().nextGaussian());
			session.setUid(user.getId());
			try {
				session.setClientIp (InetAddress.getLocalHost().getHostAddress());
			} catch (UnknownHostException uhe) {
				String errorMessage = "Unable to get localhost address: " + uhe.toString();
				log(errorMessage);
			}
			session.setLastLogin (new Date());
			session.setUserAgent ("Mozilla");
			session.setLoggedIn(Boolean.valueOf(true));
			
			// Set operation to "CREATE" -- i.e. insert new row into database
			session.setOperation(AbstractDO.CREATE);
			
			// Link DAO-DO
			SessionDAO sessionDao = new SessionDAO(session);
			sessionDao.save(true, false);

			// Output/log results
			String resultLog = "Newly created object: " + session.toString(); 
			log(resultLog);
		} else {
			// Output/log results
			String resultLog = "Object already exists: " + session.toString(); 
			log(resultLog);
		}

		return session;
	}

	public void testUpdateSession() {
		SessionDO session = createNewSessionWithIdOne();

		session.setOperation(AbstractDO.UPDATE);
		session.setId(1L);

		String beforeMsg = "BEFORE update";
		System.out.println(beforeMsg);
		log(beforeMsg);
		
		System.out.println(session);
		log(session);

		session.setGuid("some_random_guid" + getRandomNumberGenerator().nextGaussian());
		session.setLoggedIn(Boolean.valueOf(false));

		SessionDAO entityDAO = null;

		try {
			entityDAO = new SessionDAO(session);

			entityDAO.save(true, false);
		} catch (Exception e) {
			e.printStackTrace();
		}

	    session = entityDAO.getSessionDO();

		String afterMsg = "AFTER update";
		log(afterMsg);
		
		log(session);
	}

	public static void main(String[] args)  {
		TestSessionDAO test = new TestSessionDAO();
		test.testInsertSession();
		test.testGetAllSessions();
		test.testGetSessionById();
		test.testUpdateSession();
	}
}
