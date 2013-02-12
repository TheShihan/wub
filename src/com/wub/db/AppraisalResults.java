package com.wub.db;

/**
 * AppraisalResults entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class AppraisalResults implements java.io.Serializable {

	// Fields

	private Integer resultId;
	private Integer textId;
	private Integer statusId;
	private Short vote;
	private String comment;

	// Constructors

	/** default constructor */
	public AppraisalResults() {
	}

	/** full constructor */
	public AppraisalResults(Integer textId, Integer statusId, Short vote,
			String comment) {
		this.textId = textId;
		this.statusId = statusId;
		this.vote = vote;
		this.comment = comment;
	}

	// Property accessors

	public Integer getResultId() {
		return this.resultId;
	}

	public void setResultId(Integer resultId) {
		this.resultId = resultId;
	}

	public Integer getTextId() {
		return this.textId;
	}

	public void setTextId(Integer textId) {
		this.textId = textId;
	}

	public Integer getStatusId() {
		return this.statusId;
	}

	public void setStatusId(Integer statusId) {
		this.statusId = statusId;
	}

	public Short getVote() {
		return this.vote;
	}

	public void setVote(Short vote) {
		this.vote = vote;
	}

	public String getComment() {
		return this.comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

}