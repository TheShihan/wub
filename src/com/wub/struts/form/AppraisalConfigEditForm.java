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
 * Creation date: 01-23-2008
 * 
 * XDoclet definition:
 * @struts.form name="appraisalConfigEditForm"
 */
public class AppraisalConfigEditForm extends ActionForm {
	/*
	 * Generated fields
	 */

	/** active property */
	private Boolean active;

	/** confId property */
	private Integer confId;

	/** order property */
	private Integer itemOrder;

	/** textId property */
	private Integer textId;
	
	/** text property */
	private String text;

	/** appraisalid property */
	private Integer appraisalid;

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
	 * Returns the active.
	 * @return Boolean
	 */
	public Boolean getActive() {
		return active;
	}

	/** 
	 * Set the active.
	 * @param active The active to set
	 */
	public void setActive(Boolean active) {
		this.active = active;
	}

	/** 
	 * Returns the confId.
	 * @return Integer
	 */
	public Integer getConfId() {
		return confId;
	}

	/** 
	 * Set the confId.
	 * @param confId The confId to set
	 */
	public void setConfId(Integer confId) {
		this.confId = confId;
	}

	/** 
	 * Returns the order.
	 * @return Integer
	 */
	public Integer getItemOrder() {
		return this.itemOrder;
	}

	/** 
	 * Set the order.
	 * @param order The order to set
	 */
	public void setOrder(Integer itemOrder) {
		this.itemOrder = itemOrder;
	}

	/** 
	 * Returns the textId.
	 * @return Integer
	 */
	public Integer getTextId() {
		return textId;
	}

	/** 
	 * Set the textId.
	 * @param textId The textId to set
	 */
	public void setTextId(Integer textId) {
		this.textId = textId;
	}
	
	public String getText() {
		return text;
	}
	
	public void setText(String text) {
		this.text = text;
	}

	/** 
	 * Returns the appraisalid.
	 * @return Integer
	 */
	public Integer getAppraisalid() {
		return appraisalid;
	}

	/** 
	 * Set the appraisalid.
	 * @param appraisalid The appraisalid to set
	 */
	public void setAppraisalid(Integer appraisalid) {
		this.appraisalid = appraisalid;
	}
}