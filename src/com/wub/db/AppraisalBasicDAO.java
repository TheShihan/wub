package com.wub.db;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * AppraisalBasic entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.wub.db.AppraisalBasic
 * @author MyEclipse Persistence Tools
 */

public class AppraisalBasicDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory.getLog(AppraisalBasicDAO.class);
	// property constants
	public static final String NAME = "name";
	public static final String VOTE_STYLE = "voteStyle";
	public static final String LOCKED = "locked";

	public void save(AppraisalBasic transientInstance) {
		log.debug("saving AppraisalBasic instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(AppraisalBasic persistentInstance) {
		log.debug("deleting AppraisalBasic instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public AppraisalBasic findById(java.lang.Integer id) {
		log.debug("getting AppraisalBasic instance with id: " + id);
		try {
			AppraisalBasic instance = (AppraisalBasic) getSession().get(
					"com.wub.db.AppraisalBasic", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(AppraisalBasic instance) {
		log.debug("finding AppraisalBasic instance by example");
		try {
			List results = getSession().createCriteria(
					"com.wub.db.AppraisalBasic").add(Example.create(instance))
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
		log.debug("finding AppraisalBasic instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from AppraisalBasic as model where model."
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

	public List findByVoteStyle(Object voteStyle) {
		return findByProperty(VOTE_STYLE, voteStyle);
	}

	public List findByLocked(Object locked) {
		return findByProperty(LOCKED, locked);
	}

	public List findAll() {
		log.debug("finding all AppraisalBasic instances");
		try {
			String queryString = "from AppraisalBasic";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public AppraisalBasic merge(AppraisalBasic detachedInstance) {
		log.debug("merging AppraisalBasic instance");
		try {
			AppraisalBasic result = (AppraisalBasic) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(AppraisalBasic instance) {
		log.debug("attaching dirty AppraisalBasic instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(AppraisalBasic instance) {
		log.debug("attaching clean AppraisalBasic instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}