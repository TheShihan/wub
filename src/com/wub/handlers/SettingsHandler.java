package com.wub.handlers;

import java.util.List;

import com.wub.db.Settings;
import com.wub.db.SettingsDAO;


public class SettingsHandler {

	public static final String SETTINGSLISTFORM = "settingsListForm";
	public static final String SETTINGFORM = "settingForm";
	private static SettingsHandler instance = null;
	
	
	/**
	 * Gibt alle Settings zurück, welche in der DB gespeichert sind 
	 * @return Liste mit allen Settings
	 */
	public List getAllSettings() {
		SettingsDAO settingsDAO = new SettingsDAO();
		return settingsDAO.findAll();
	}
	
	
	/**
	 * Sucht ein Setting anhand seiner ID 
	 * @param settingId
	 * @return
	 */
	public Settings getSettingById(int settingId) {
		SettingsDAO settingsDAO = new SettingsDAO();
		return settingsDAO.findById(settingId);
	}
	
	
	/**
	 * Findet ein Setting anhand des Namens
	 * @param settingName
	 * @return
	 */
	public String getSettingByName(String settingName) {
		SettingsDAO settingsDAO = new SettingsDAO();
		List settingList = settingsDAO.findBySettingName(settingName);
		if (settingList.size() > 0) {
			return ((Settings)settingList.get(0)).getSettingValue();
		} else {
			return null;
		}
	}
	
	
	/**
	 * Speichert ein Setting in der DB
	 * @param setting
	 * @return
	 */
	public void saveSetting(Settings setting) {
		SettingsDAO settingsDAO = new SettingsDAO();
		settingsDAO.save(setting);
		settingsDAO.getSession().flush();
	}
	
	
	/**
	 * Singleton Pattern
	 * @return
	 */
	public static SettingsHandler getInstance() {
		if (instance == null) {
			instance = new SettingsHandler();
		}
		return instance;
	}
	
	
}
