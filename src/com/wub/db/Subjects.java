package com.wub.db;

/**
 * Subjects entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Subjects implements java.io.Serializable, Comparable {

	// Fields

	private Integer subjectId;
	private String name;

	// Constructors

	/** default constructor */
	public Subjects() {
	}

	/** full constructor */
	public Subjects(String name) {
		this.name = name;
	}

	// Property accessors

	public Integer getSubjectId() {
		return this.subjectId;
	}

	public void setSubjectId(Integer subjectId) {
		this.subjectId = subjectId;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
	/**
	 * Für interface "Comparable". Zum Sortieren dieses Objektes in Listen.
	 */
	public int compareTo(Object o) {
		return this.name.compareTo(((Subjects)o).name);
	}
	
}