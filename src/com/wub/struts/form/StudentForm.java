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
 * Creation date: 02-10-2008
 * 
 * XDoclet definition:
 * @struts.form name="studentForm"
 */
@SuppressWarnings("serial")
public class StudentForm extends ActionForm {
	/*
	 * Generated fields
	 */

	/** studentId property */
	private Integer studentId;

	/** description property */
	private String description;

	/** isActive property */
	private Boolean active;

	/*
	 * Generated Methods
	 */

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

	/** 
	 * Returns the studentId.
	 * @return Integer
	 */
	public Integer getStudentId() {
		return studentId;
	}

	/** 
	 * Set the studentId.
	 * @param studentId The studentId to set
	 */
	public void setStudentId(Integer studentId) {
		this.studentId = studentId;
	}

	/** 
	 * Returns the description.
	 * @return String
	 */
	public String getDescription() {
		return description;
	}

	/** 
	 * Set the description.
	 * @param description The description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/** 
	 * Returns the Active.
	 * @return Boolean
	 */
	public Boolean getActive() {
		return this.active;
	}

	/** 
	 * Set the Active.
	 * @param active The active to set
	 */
	public void setActive(Boolean active) {
		this.active = active;
	}
}