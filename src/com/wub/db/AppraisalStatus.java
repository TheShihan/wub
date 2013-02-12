package com.wub.db;

/**
 * AppraisalStatus entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class AppraisalStatus implements java.io.Serializable {

	// Fields

	private Integer statusId;
	private Integer classId;
	private Integer userId;
	private String token;

	// Constructors

	/** default constructor */
	public AppraisalStatus() {
	}

	/** full constructor */
	public AppraisalStatus(Integer classId, Integer userId, String token) {
		this.classId = classId;
		this.userId = userId;
		this.token = token;
	}

	// Property accessors

	public Integer getStatusId() {
		return this.statusId;
	}

	public void setStatusId(Integer statusId) {
		this.statusId = statusId;
	}

	public Integer getClassId() {
		return this.classId;
	}

	public void setClassId(Integer classId) {
		this.classId = classId;
	}

	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getToken() {
		return this.token;
	}

	public void setToken(String token) {
		this.token = token;
	}

}