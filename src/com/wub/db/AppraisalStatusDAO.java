package com.wub.db;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * AppraisalStatus entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.wub.db.AppraisalStatus
 * @author MyEclipse Persistence Tools
 */

public class AppraisalStatusDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory.getLog(AppraisalStatusDAO.class);
	// property constants
	public static final String CLASS_ID = "classId";
	public static final String USER_ID = "userId";
	public static final String TOKEN = "token";

	public void save(AppraisalStatus transientInstance) {
		log.debug("saving AppraisalStatus instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(AppraisalStatus persistentInstance) {
		log.debug("deleting AppraisalStatus instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public AppraisalStatus findById(java.lang.Integer id) {
		log.debug("getting AppraisalStatus instance with id: " + id);
		try {
			AppraisalStatus instance = (AppraisalStatus) getSession().get(
					"com.wub.db.AppraisalStatus", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(AppraisalStatus instance) {
		log.debug("finding AppraisalStatus instance by example");
		try {
			List results = getSession().createCriteria(
					"com.wub.db.AppraisalStatus").add(Example.create(instance))
					.list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding AppraisalStatus instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from AppraisalStatus as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}
	
	public List findByClassIdAndUserId(int classId, int userId) {
		log.debug("finding AppraisalStatus instance by classId and userId");
		try {
			String queryString = "from AppraisalStatus as model where model.classId = ? and model.userId = ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, classId);
			queryObject.setParameter(1, userId);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property classId and userId failed", re);
			throw re;
		}
	}

	public List findByClassId(Object classId) {
		return findByProperty(CLASS_ID, classId);
	}

	public List findByUserId(Object userId) {
		return findByProperty(USER_ID, userId);
	}

	public List findByToken(Object token) {
		return findByProperty(TOKEN, token);
	}

	public List findAll() {
		log.debug("finding all AppraisalStatus instances");
		try {
			String queryString = "from AppraisalStatus";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	
	public List findByUserIdAndToken(Object userIdVal, Object tokenVal) {
		log.debug("finding AppraisalStatus instance with property UserId and Token.");
		try {
			String queryString = "from AppraisalStatus as model where model.userId= ? and model.token= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, userIdVal);
			queryObject.setParameter(1, tokenVal);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property userId and token failed", re);
			throw re;
		}
	}

	public AppraisalStatus merge(AppraisalStatus detachedInstance) {
		log.debug("merging AppraisalStatus instance");
		try {
			AppraisalStatus result = (AppraisalStatus) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(AppraisalStatus instance) {
		log.debug("attaching dirty AppraisalStatus instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(AppraisalStatus instance) {
		log.debug("attaching clean AppraisalStatus instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}