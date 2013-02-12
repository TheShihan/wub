package com.wub.db;

/**
 * AppraisalConf entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class AppraisalConf implements java.io.Serializable, Comparable  {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 2788630377596529455L;
	private Integer confId;
	private Integer appraisalId;
	private Integer textId;
	private Short itemOrder;

	// Constructors

	/** default constructor */
	public AppraisalConf() {
	}

	/** full constructor */
	public AppraisalConf(Integer appraisalId, Integer textId, Short itemOrder) {
		this.appraisalId = appraisalId;
		this.textId = textId;
		this.itemOrder = itemOrder;
	}

	// Property accessors

	public Integer getConfId() {
		return this.confId;
	}

	public void setConfId(Integer confId) {
		this.confId = confId;
	}

	public Integer getAppraisalId() {
		return this.appraisalId;
	}

	public void setAppraisalId(Integer appraisalId) {
		this.appraisalId = appraisalId;
	}

	public Integer getTextId() {
		return this.textId;
	}

	public void setTextId(Integer textId) {
		this.textId = textId;
	}

	public Short getItemOrder() {
		return this.itemOrder;
	}

	public void setItemOrder(Short itemOrder) {
		this.itemOrder = itemOrder;
	}

	
	/**
	 * Für interface "Comparable". Zum Sortieren dieses Objektes in Listen.
	 */
	public int compareTo(Object o) {
		AppraisalConf appConf = (AppraisalConf)o;
		if (this.itemOrder < appConf.itemOrder) {
			return -1;
		} else if (this.itemOrder == appConf.itemOrder){
			return 0;
		} else {
			return 1;
		}
	}
	
}