package com.wub.db;

/**
 * AppraisalBasic entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class AppraisalBasic implements java.io.Serializable, Comparable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1633659323206554953L;
	private Integer appraisalId;
	private String name;
	private Integer voteStyle;
	private Byte locked;

	// Constructors

	/** default constructor */
	public AppraisalBasic() {
	}

	/** full constructor */
	public AppraisalBasic(String name, Integer voteStyle, Byte locked) {
		this.name = name;
		this.voteStyle = voteStyle;
		this.locked = locked;
	}

	// Property accessors

	public Integer getAppraisalId() {
		return this.appraisalId;
	}

	public void setAppraisalId(Integer appraisalId) {
		this.appraisalId = appraisalId;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getVoteStyle() {
		return this.voteStyle;
	}

	public void setVoteStyle(Integer voteStyle) {
		this.voteStyle = voteStyle;
	}

	public Byte getLocked() {
		return this.locked;
	}

	public void setLocked(Byte locked) {
		this.locked = locked;
	}

	
	/**
	 * Für interface "Comparable". Zum Sortieren dieses Objektes in Listen.
	 */
	public int compareTo(Object o) {
		return this.name.compareTo(((AppraisalBasic)o).name);
	}
	
}