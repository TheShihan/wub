/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package com.wub.struts.form;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/** 
 * MyEclipse Struts
 * Creation date: 01-15-2008
 * 
 * XDoclet definition:
 * @struts.form name="userListForm"
 */
public class UserListForm extends ActionForm {

	
	private List userList;
	
	
	public void setUserList(List userList) {
		this.userList = userList;
	}
	
	
	public List getUserList() {
		return userList;
	}
	
	
	/**
	 * Konstruktor
	 */
	public UserListForm() {
		userList = new ArrayList();
	}
	
	
	/** 
	 * Method validate
	 * @param mapping
	 * @param request
	 * @return ActionErrors
	 */
	public ActionErrors validate(ActionMapping mapping,
			HttpServletRequest request) {
		// TODO Auto-generated method stub
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
}