package com.wub.db;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * Settings entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.wub.db.Settings
 * @author MyEclipse Persistence Tools
 */

public class SettingsDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory.getLog(SettingsDAO.class);
	// property constants
	public static final String SETTING_NAME = "settingName";
	public static final String SETTING_VALUE = "settingValue";

	public void save(Settings transientInstance) {
		log.debug("saving Settings instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Settings persistentInstance) {
		log.debug("deleting Settings instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Settings findById(java.lang.Integer id) {
		log.debug("getting Settings instance with id: " + id);
		try {
			Settings instance = (Settings) getSession().get(
					"com.wub.db.Settings", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Settings instance) {
		log.debug("finding Settings instance by example");
		try {
			List results = getSession().createCriteria("com.wub.db.Settings")
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
		log.debug("finding Settings instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Settings as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findBySettingName(Object settingName) {
		return findByProperty(SETTING_NAME, settingName);
	}

	public List findBySettingValue(Object settingValue) {
		return findByProperty(SETTING_VALUE, settingValue);
	}

	public List findAll() {
		log.debug("finding all Settings instances");
		try {
			String queryString = "from Settings";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Settings merge(Settings detachedInstance) {
		log.debug("merging Settings instance");
		try {
			Settings result = (Settings) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Settings instance) {
		log.debug("attaching dirty Settings instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Settings instance) {
		log.debug("attaching clean Settings instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}