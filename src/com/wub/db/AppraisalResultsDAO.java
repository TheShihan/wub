package com.wub.db;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * AppraisalResults entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.wub.db.AppraisalResults
 * @author MyEclipse Persistence Tools
 */

public class AppraisalResultsDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory.getLog(AppraisalResultsDAO.class);
	// property constants
	public static final String TEXT_ID = "textId";
	public static final String STATUS_ID = "statusId";
	public static final String VOTE = "vote";
	public static final String COMMENT = "comment";

	public void save(AppraisalResults transientInstance) {
		log.debug("saving AppraisalResults instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(AppraisalResults persistentInstance) {
		log.debug("deleting AppraisalResults instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public AppraisalResults findById(java.lang.Integer id) {
		log.debug("getting AppraisalResults instance with id: " + id);
		try {
			AppraisalResults instance = (AppraisalResults) getSession().get(
					"com.wub.db.AppraisalResults", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(AppraisalResults instance) {
		log.debug("finding AppraisalResults instance by example");
		try {
			List results = getSession().createCriteria(
					"com.wub.db.AppraisalResults")
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
		log.debug("finding AppraisalResults instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from AppraisalResults as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}
	
	public List findByTextIdAndStatusId(Object statusId, Object textId) {
		log.debug("finding AppraisalResults instance");
		try {
			String queryString = "from AppraisalResults as model where model.statusId= ? and model.textId = ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, statusId);
			queryObject.setParameter(1, textId);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByTextId(Object textId) {
		return findByProperty(TEXT_ID, textId);
	}

	public List findByStatusId(Object statusId) {
		return findByProperty(STATUS_ID, statusId);
	}

	public List findByVote(Object vote) {
		return findByProperty(VOTE, vote);
	}

	public List findByComment(Object comment) {
		return findByProperty(COMMENT, comment);
	}

	public List findAll() {
		log.debug("finding all AppraisalResults instances");
		try {
			String queryString = "from AppraisalResults";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public AppraisalResults merge(AppraisalResults detachedInstance) {
		log.debug("merging AppraisalResults instance");
		try {
			AppraisalResults result = (AppraisalResults) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(AppraisalResults instance) {
		log.debug("attaching dirty AppraisalResults instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(AppraisalResults instance) {
		log.debug("attaching clean AppraisalResults instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}