package com.wub.db;

/**
 * Settings entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Settings implements java.io.Serializable {

	// Fields

	private Integer settingId;
	private String settingName;
	private String settingValue;

	// Constructors

	/** default constructor */
	public Settings() {
	}

	/** full constructor */
	public Settings(String settingName, String settingValue) {
		this.settingName = settingName;
		this.settingValue = settingValue;
	}

	// Property accessors

	public Integer getSettingId() {
		return this.settingId;
	}

	public void setSettingId(Integer settingId) {
		this.settingId = settingId;
	}

	public String getSettingName() {
		return this.settingName;
	}

	public void setSettingName(String settingName) {
		this.settingName = settingName;
	}

	public String getSettingValue() {
		return this.settingValue;
	}

	public void setSettingValue(String settingValue) {
		this.settingValue = settingValue;
	}

}