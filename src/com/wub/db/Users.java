package com.wub.db;

/**
 * Users entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Users implements java.io.Serializable, Comparable {

	// Fields

	private Integer userId;
	private String name;
	private String email;
	private String mainClass;

	// Constructors

	/** default constructor */
	public Users() {
	}

	/** full constructor */
	public Users(String name, String email, String mainClass) {
		this.name = name;
		this.email = email;
		this.mainClass = mainClass;
	}

	// Property accessors

	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getMainClass() {
		return this.mainClass;
	}

	public void setMainClass(String mainClass) {
		this.mainClass = mainClass;
	}
	
	
	/**
	 * Für interface "Comparable". Zum Sortieren dieses Objektes in Listen.
	 */
	public int compareTo(Object o) {
		if (this.mainClass.compareTo(((Users)o).mainClass) != 0) {
			return this.mainClass.compareTo(((Users)o).mainClass);
		} else {
			return this.name.compareTo(((Users)o).name);
		}
	}
	

}