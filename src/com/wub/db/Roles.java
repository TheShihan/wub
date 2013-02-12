package com.wub.db;

/**
 * Roles entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Roles implements java.io.Serializable {

	// Fields

	private Integer roleId;
	private String name;
	private Byte usermanager;
	private Byte reportmanager;
	private Byte appraisalmanager;

	// Constructors

	/** default constructor */
	public Roles() {
	}

	/** full constructor */
	public Roles(String name, Byte usermanager, Byte reportmanager,
			Byte appraisalmanager) {
		this.name = name;
		this.usermanager = usermanager;
		this.reportmanager = reportmanager;
		this.appraisalmanager = appraisalmanager;
	}

	// Property accessors

	public Integer getRoleId() {
		return this.roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Byte getUsermanager() {
		return this.usermanager;
	}

	public void setUsermanager(Byte usermanager) {
		this.usermanager = usermanager;
	}

	public Byte getReportmanager() {
		return this.reportmanager;
	}

	public void setReportmanager(Byte reportmanager) {
		this.reportmanager = reportmanager;
	}

	public Byte getAppraisalmanager() {
		return this.appraisalmanager;
	}

	public void setAppraisalmanager(Byte appraisalmanager) {
		this.appraisalmanager = appraisalmanager;
	}

}