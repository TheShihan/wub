/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package com.wub.struts.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.wub.db.Administrators;
import com.wub.handlers.AdminUserHandler;
import com.wub.struts.form.ChangePasswordForm;

/** 
 * MyEclipse Struts
 * Creation date: 12-02-2007
 * 
 * XDoclet definition:
 * @struts.action path="/changePassword" name="changePasswordForm" input="/WebRoot/changePassword.jsp" scope="request" validate="true"
 */
public class ChangePasswordAction extends Action {
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
		ChangePasswordForm changePasswordForm = (ChangePasswordForm) form;// TODO Auto-generated method stub
		
		// pr�fen ob alles ausgef�llt
		if (!changePasswordForm.getOldPassword().equals("") &&
				!changePasswordForm.getNewPassword().equals("") &&
				!changePasswordForm.getNewPasswordCheck().equals("")) {
			// pr�fen ob die beiden alten Kennw�rter identisch sind
			if (changePasswordForm.getNewPassword().equals(changePasswordForm.getNewPasswordCheck())) {
				
				// speichern des neuen Kennwortes
				AdminUserHandler adminHandler = AdminUserHandler.getInstance();
				
				// username Herausfinden
				HttpSession session = request.getSession();
				Administrators adminData = (Administrators)session.getAttribute(AdminUserHandler.ADMINDATA);
				
				String newPassword = changePasswordForm.getNewPassword();
				
				boolean passwordChanged = adminHandler.changePassword(adminData.getName(), changePasswordForm.getOldPassword(), newPassword);
				
				if (passwordChanged){
					// neue Session informationen speichern
					adminData.setPassword(newPassword);
					session.setAttribute(AdminUserHandler.ADMINDATA, adminData);
					
					return mapping.findForward("success");
				}
			}
				
		}
		
		return mapping.findForward("changePasswordFailed");
	}
}