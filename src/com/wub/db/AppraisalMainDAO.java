package com.wub.db;

import java.util.Date;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * AppraisalMain entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.wub.db.AppraisalMain
 * @author MyEclipse Persistence Tools
 */

public class AppraisalMainDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory.getLog(AppraisalMainDAO.class);
	// property constants
	public static final String NAME = "name";
	public static final String ADMIN_ID = "adminId";
	public static final String SUBJECT_ID = "subjectId";
	public static final String APPRAISAL_ID = "appraisalId";
	public static final String SENT = "sent";

	public void save(AppraisalMain transientInstance) {
		log.debug("saving AppraisalMain instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(AppraisalMain persistentInstance) {
		log.debug("deleting AppraisalMain instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public AppraisalMain findById(java.lang.Integer id) {
		log.debug("getting AppraisalMain instance with id: " + id);
		try {
			AppraisalMain instance = (AppraisalMain) getSession().get(
					"com.wub.db.AppraisalMain", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(AppraisalMain instance) {
		log.debug("finding AppraisalMain instance by example");
		try {
			List results = getSession().createCriteria(
					"com.wub.db.AppraisalMain").add(Example.create(instance))
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
		log.debug("finding AppraisalMain instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from AppraisalMain as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByName(Object name) {
		return findByProperty(NAME, name);
	}

	public List findByAdminId(Object adminId) {
		return findByProperty(ADMIN_ID, adminId);
	}

	public List findBySubjectId(Object subjectId) {
		return findByProperty(SUBJECT_ID, subjectId);
	}

	public List findByAppraisalId(Object appraisalId) {
		return findByProperty(APPRAISAL_ID, appraisalId);
	}

	public List findBySent(Object sent) {
		return findByProperty(SENT, sent);
	}
	

	public List findAll() {
		log.debug("finding all AppraisalMain instances");
		try {
			String queryString = "from AppraisalMain";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public AppraisalMain merge(AppraisalMain detachedInstance) {
		log.debug("merging AppraisalMain instance");
		try {
			AppraisalMain result = (AppraisalMain) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(AppraisalMain instance) {
		log.debug("attaching dirty AppraisalMain instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(AppraisalMain instance) {
		log.debug("attaching clean AppraisalMain instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}