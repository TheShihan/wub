package com.wub.db;

import java.util.Date;

import com.wub.misc.Helper;

/**
 * AppraisalMain entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class AppraisalMain implements java.io.Serializable, Comparable {

	// Fields

	private static final long serialVersionUID = -5287674132014076258L;
	private Integer classId;
	private String name;
	private Integer adminId;
	private Integer subjectId;
	private Integer appraisalId;
	private Date startDate;
	private Date endDate;
	private Date subjectYear;
	private Integer sent;

	// Constructors

	/** default constructor */
	public AppraisalMain() {
	}

	/** full constructor */
	public AppraisalMain(String name, Integer adminId, Integer subjectId,
			Integer appraisalId, Date startDate, Date endDate,
			Date subjectYear, Integer sent) {
		this.name = name;
		this.adminId = adminId;
		this.subjectId = subjectId;
		this.appraisalId = appraisalId;
		this.startDate = startDate;
		this.endDate = endDate;
		this.subjectYear = subjectYear;
		this.sent = sent;
	}

	// Property accessors

	public Integer getClassId() {
		return this.classId;
	}

	public void setClassId(Integer classId) {
		this.classId = classId;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAdminId() {
		return this.adminId;
	}

	public void setAdminId(Integer adminId) {
		this.adminId = adminId;
	}

	public Integer getSubjectId() {
		return this.subjectId;
	}

	public void setSubjectId(Integer subjectId) {
		this.subjectId = subjectId;
	}

	public Integer getAppraisalId() {
		return this.appraisalId;
	}

	public void setAppraisalId(Integer appraisalId) {
		this.appraisalId = appraisalId;
	}

	public Date getStartDate() {
		return this.startDate;
	}
	
	public String getStartDateString() {
		return Helper.getInstance().GetStandardDate(this.startDate);
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return this.endDate;
	}
	
	public String getEndDateString() {
		return Helper.getInstance().GetStandardDate(this.endDate);
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Date getSubjectYear() {
		return this.subjectYear;
	}
	
	public String getSubjectYearString() {
		return Helper.getInstance().GetStandardDateYearOnly(this.subjectYear);
	}

	public void setSubjectYear(Date subjectYear) {
		this.subjectYear = subjectYear;
	}

	public Integer getSent() {
		return this.sent;
	}

	public void setSent(Integer sent) {
		this.sent = sent;
	}

	
	/**
	 * Für interface "Comparable". Zum Sortieren dieses Objektes in Listen.
	 */
	public int compareTo(Object o) {
		return this.name.compareTo(((AppraisalMain)o).name);
	}

}