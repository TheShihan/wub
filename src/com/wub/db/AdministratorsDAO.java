package com.wub.db;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * Administrators entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.wub.db.Administrators
 * @author MyEclipse Persistence Tools
 */

public class AdministratorsDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory.getLog(AdministratorsDAO.class);
	// property constants
	public static final String NAME = "name";
	public static final String EMAIL = "email";
	public static final String PASSWORD = "password";
	public static final String ROLE_ID = "roleId";

	public void save(Administrators transientInstance) {
		log.debug("saving Administrators instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Administrators persistentInstance) {
		log.debug("deleting Administrators instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Administrators findById(java.lang.Integer id) {
		log.debug("getting Administrators instance with id: " + id);
		try {
			Administrators instance = (Administrators) getSession().get(
					"com.wub.db.Administrators", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Administrators instance) {
		log.debug("finding Administrators instance by example");
		try {
			List results = getSession().createCriteria(
					"com.wub.db.Administrators").add(Example.create(instance))
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
		log.debug("finding Administrators instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Administrators as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Administrators> checkLogin(String username, String password) {
		log.debug("checklogin for user: "
				+ username);
		try {
			String queryString = "from Administrators as model where model.name=? and model.password=?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, username);
			queryObject.setParameter(1, password);
			return (List<Administrators>)queryObject.list();
		} catch (RuntimeException re) {
			log.error("checklogin failed", re);
			throw re;
		}
	}

	public List findByName(Object name) {
		return findByProperty(NAME, name);
	}

	public List findByEmail(Object email) {
		return findByProperty(EMAIL, email);
	}

	public List findByPassword(Object password) {
		return findByProperty(PASSWORD, password);
	}

	public List findByRoleId(Object roleId) {
		return findByProperty(ROLE_ID, roleId);
	}

	public List findAll() {
		log.debug("finding all Administrators instances");
		try {
			String queryString = "from Administrators";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Administrators merge(Administrators detachedInstance) {
		log.debug("merging Administrators instance");
		try {
			Administrators result = (Administrators) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Administrators instance) {
		log.debug("attaching dirty Administrators instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Administrators instance) {
		log.debug("attaching clean Administrators instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}