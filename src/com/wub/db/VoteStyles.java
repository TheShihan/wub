package com.wub.db;

/**
 * VoteStyles entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class VoteStyles implements java.io.Serializable {

	// Fields

	private Integer voteStyleId;
	private Short voteCount;

	// Constructors

	/** default constructor */
	public VoteStyles() {
	}

	/** full constructor */
	public VoteStyles(Short voteCount) {
		this.voteCount = voteCount;
	}

	// Property accessors

	public Integer getVoteStyleId() {
		return this.voteStyleId;
	}

	public void setVoteStyleId(Integer voteStyleId) {
		this.voteStyleId = voteStyleId;
	}

	public Short getVoteCount() {
		return this.voteCount;
	}

	public void setVoteCount(Short voteCount) {
		this.voteCount = voteCount;
	}

}