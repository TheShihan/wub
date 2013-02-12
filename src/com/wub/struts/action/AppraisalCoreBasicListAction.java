/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package com.wub.struts.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.wub.handlers.AdminUserHandler;
import com.wub.handlers.AppraisalHandler;
import com.wub.struts.form.AppraisalCoreBasicListForm;
import com.wub.struts.form.UserListForm;

/** 
 * MyEclipse Struts
 * Creation date: 01-16-2008
 * 
 * XDoclet definition:
 * @struts.action path="/appraisalCoreBasicList" name="appraisalCoreBasicListForm" scope="request" validate="true"
 */
public class AppraisalCoreBasicListAction extends Action {
	/*
	 * Generated Methods
	 */

	/** 
	 * Method execute
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		AppraisalCoreBasicListForm appraisalCoreBasicListForm = (AppraisalCoreBasicListForm) form;// TODO Auto-generated method stub

		
		if(request.getSession().getAttribute(AdminUserHandler.ADMINDATA) != null) {
			// Liste der User vorbereiten
			if (appraisalCoreBasicListForm == null) {
				appraisalCoreBasicListForm = new AppraisalCoreBasicListForm();
			}
			
			// abf�llen der List-Elemente
			AppraisalHandler appraisalHandler = AppraisalHandler.getInstance();
			List appraisalCoreBasicList = appraisalHandler.getAppraisalBasicList();
		
			appraisalCoreBasicListForm.setAppraisalBasicList(appraisalCoreBasicList);
			
			// wir m�ssen die Liste im Scope speichern, damit sie auf der Seite verf�gbar ist
			request.setAttribute(AppraisalHandler.APPRAISALBASICLIST, appraisalCoreBasicListForm);
			
			return mapping.findForward("showList");
		}
		
		// nicht eingeloggt -> zu startseite leiten
		return mapping.findForward("start");
		
		
	}
}