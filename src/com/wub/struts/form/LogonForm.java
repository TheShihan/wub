/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package com.wub.struts.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

/** 
 * MyEclipse Struts
 * Creation date: 11-29-2007
 * 
 * XDoclet definition:
 * @struts.form name="logonForm"
 */
public class LogonForm extends ActionForm {
	/*
	 * Generated fields
	 */

	/**
	 * 
	 */
	private static final long serialVersionUID = 7647612953370187141L;

	/** password property */
	private String password;

	/** username property */
	private String username;

	/*
	 * Generated Methods
	 */

	/** 
	 * Method validate
	 * @param mapping
	 * @param request
	 * @return ActionErrors
	 */
	public ActionErrors validate(ActionMapping mp, HttpServletRequest rq) {
		ActionErrors errors = null;
		
		// pr�fen ob alle felder ausgef�llt, um Fehler anzuzeigen
		
		if (username == null || username.length() < 1) {
			if (errors == null) {
				errors = new ActionErrors();
			}
			errors.add("username", new ActionMessage("error.admin.logon.usernamemissing"));
		}
		if (password == null || password.length() < 1) {
			if (errors == null) {
				errors = new ActionErrors();
			}
			errors.add("password", new ActionMessage("error.admin.logon.passwordmissing"));
		}
		
		return errors;
	}

	/**
	 * Method reset
	 * 
	 * @param mapping
	 * @param request
	 */
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		// nicht implementiert
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
	 * Returns the username.
	 * @return String
	 */
	public String getUsername() {
		return username;
	}

	/** 
	 * Set the username.
	 * @param username The username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}
}