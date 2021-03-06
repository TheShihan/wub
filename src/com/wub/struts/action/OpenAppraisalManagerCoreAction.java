/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package com.wub.struts.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.wub.handlers.AdminUserHandler;

/** 
 * MyEclipse Struts
 * Creation date: 01-15-2008
 * 
 * XDoclet definition:
 * @struts.action validate="true"
 */
public class OpenAppraisalManagerCoreAction extends Action {
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

		
		if(request.getSession().getAttribute(AdminUserHandler.ADMINDATA) == null) {
			// nicht eingeloggt -> zu startseite leiten
			return mapping.findForward("start");
		}
		else {
			// zur Hauptseite des Beurteilungs-Managers
			return mapping.findForward("toAppraisalCore");
		}
		
		
	}
}