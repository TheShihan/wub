package com.wub.db;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * TextElements entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.wub.db.TextElements
 * @author MyEclipse Persistence Tools
 */

public class TextElementsDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory.getLog(TextElementsDAO.class);
	// property constants
	public static final String TEXT = "text";

	public void save(TextElements transientInstance) {
		log.debug("saving TextElements instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(TextElements persistentInstance) {
		log.debug("deleting TextElements instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public TextElements findById(java.lang.Integer id) {
		log.debug("getting TextElements instance with id: " + id);
		try {
			TextElements instance = (TextElements) getSession().get(
					"com.wub.db.TextElements", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(TextElements instance) {
		log.debug("finding TextElements instance by example");
		try {
			List results = getSession().createCriteria(
					"com.wub.db.TextElements").add(Example.create(instance))
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
		log.debug("finding TextElements instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from TextElements as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByText(Object text) {
		return findByProperty(TEXT, text);
	}

	public List findAll() {
		log.debug("finding all TextElements instances");
		try {
			String queryString = "from TextElements";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public TextElements merge(TextElements detachedInstance) {
		log.debug("merging TextElements instance");
		try {
			TextElements result = (TextElements) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(TextElements instance) {
		log.debug("attaching dirty TextElements instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(TextElements instance) {
		log.debug("attaching clean TextElements instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}