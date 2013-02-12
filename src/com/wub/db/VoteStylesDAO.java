package com.wub.db;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * VoteStyles entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.wub.db.VoteStyles
 * @author MyEclipse Persistence Tools
 */

public class VoteStylesDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory.getLog(VoteStylesDAO.class);
	// property constants
	public static final String VOTE_COUNT = "voteCount";

	public void save(VoteStyles transientInstance) {
		log.debug("saving VoteStyles instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(VoteStyles persistentInstance) {
		log.debug("deleting VoteStyles instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public VoteStyles findById(java.lang.Integer id) {
		log.debug("getting VoteStyles instance with id: " + id);
		try {
			VoteStyles instance = (VoteStyles) getSession().get(
					"com.wub.db.VoteStyles", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(VoteStyles instance) {
		log.debug("finding VoteStyles instance by example");
		try {
			List results = getSession().createCriteria("com.wub.db.VoteStyles")
					.add(Example.create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding VoteStyles instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from VoteStyles as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByVoteCount(Object voteCount) {
		return findByProperty(VOTE_COUNT, voteCount);
	}

	public List findAll() {
		log.debug("finding all VoteStyles instances");
		try {
			String queryString = "from VoteStyles";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public VoteStyles merge(VoteStyles detachedInstance) {
		log.debug("merging VoteStyles instance");
		try {
			VoteStyles result = (VoteStyles) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(VoteStyles instance) {
		log.debug("attaching dirty VoteStyles instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(VoteStyles instance) {
		log.debug("attaching clean VoteStyles instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}