package com.wub.db;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * Subjects entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.wub.db.Subjects
 * @author MyEclipse Persistence Tools
 */

public class SubjectsDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory.getLog(SubjectsDAO.class);
	// property constants
	public static final String NAME = "name";

	public void save(Subjects transientInstance) {
		log.debug("saving Subjects instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Subjects persistentInstance) {
		log.debug("deleting Subjects instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Subjects findById(java.lang.Integer id) {
		log.debug("getting Subjects instance with id: " + id);
		try {
			Subjects instance = (Subjects) getSession().get(
					"com.wub.db.Subjects", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Subjects instance) {
		log.debug("finding Subjects instance by example");
		try {
			List results = getSession().createCriteria("com.wub.db.Subjects")
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
		log.debug("finding Subjects instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Subjects as model where model."
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

	public List findAll() {
		log.debug("finding all Subjects instances");
		try {
			String queryString = "from Subjects";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Subjects merge(Subjects detachedInstance) {
		log.debug("merging Subjects instance");
		try {
			Subjects result = (Subjects) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Subjects instance) {
		log.debug("attaching dirty Subjects instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Subjects instance) {
		log.debug("attaching clean Subjects instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}