/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package com.wub.struts.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/** 
 * MyEclipse Struts
 * Creation date: 12-10-2007
 * 
 * XDoclet definition:
 * @struts.form name="administratorEditForm"
 */

public class AdministratorEditForm extends ActionForm {
	/*
	 * Generated fields
	 */

	/**
	 * Serial ID
	 */
	private static final long serialVersionUID = 8659684081685732088L;

	/** password property */
	private String password;

	/** role property */
	private Integer role;

	/** email property */
	private String email;

	/** name property */
	private String name;

	/** id property */
	private Integer id;
	
	/** infoMessage property */
	private String infoMessage;
	
	

	/*
	 * Generated Methods
	 */

	public String getInfoMessage() {
		return infoMessage;
	}

	public void setInfoMessage(String infoMessage) {
		this.infoMessage = infoMessage;
	}

	/** 
	 * Method validate
	 * @param mapping
	 * @param request
	 * @return ActionErrors
	 */
	public ActionErrors validate(ActionMapping mapping,
			HttpServletRequest request) {
		return null;
	}

	/** 
	 * Method reset
	 * @param mapping
	 * @param request
	 */
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		// TODO Auto-generated method stub
	}

	/** 
	 * Returns the password.
	 * @return String
	 */
	public String getPassword() {
		return password;
	}

	/** 
	 * Set the password.
	 * @param password The password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/** 
	 * Returns the role.
	 * @return Integer
	 */
	public Integer getRole() {
		return role;
	}

	/** 
	 * Set the role.
	 * @param role The role to set
	 */
	public void setRole(Integer role) {
		this.role = role;
	}

	/** 
	 * Returns the email.
	 * @return String
	 */
	public String getEmail() {
		return email;
	}

	/** 
	 * Set the email.
	 * @param email The email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/** 
	 * Returns the name.
	 * @return String
	 */
	public String getName() {
		return name;
	}

	/** 
	 * Set the name.
	 * @param name The name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/** 
	 * Returns the id.
	 * @return Integer
	 */
	public Integer getId() {
		return id;
	}

	/** 
	 * Set the id.
	 * @param id The id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}
}