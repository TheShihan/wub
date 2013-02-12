package com.wub.db;

/**
 * Administrators entity.
 * 
 * @author MyEclipse Persistence Tools
 */

@SuppressWarnings("unchecked")
public class Administrators implements java.io.Serializable, Comparable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -2135070954201162144L;
	private Integer adminId;
	private String name;
	private String email;
	private String password;
	private Integer roleId;

	// Constructors

	/** default constructor */
	public Administrators() {
	}

	/** full constructor */
	public Administrators(String name, String email, String password,
			Integer roleId) {
		this.name = name;
		this.email = email;
		this.password = password;
		this.roleId = roleId;
	}

	// Property accessors

	public Integer getAdminId() {
		return this.adminId;
	}

	public void setAdminId(Integer adminId) {
		this.adminId = adminId;
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

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getRoleId() {
		return this.roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	
	/**
	 * Für interface "Comparable". Zum Sortieren dieses Objektes in Listen.
	 */
	public int compareTo(Object o) {
		return this.name.compareTo(((Administrators)o).name);
	}

}