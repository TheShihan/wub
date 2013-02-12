/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package com.wub.struts.action;

import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.wub.handlers.AdminUserHandler;
import com.wub.handlers.AppraisalHandler;
import com.wub.struts.form.UserListForm;

/** 
 * MyEclipse Struts
 * Creation date: 01-15-2008
 * 
 * XDoclet definition:
 * @struts.action path="/userList" name="userListForm" input="/form/userList.jsp" scope="request" validate="true"
 * @struts.action-forward name="showEdit" path="page.appraisalEditUser"
 * @struts.action-forward name="showAdd" path="page.appraisalAddUser"
 */
public class UserListAction extends Action {
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
		UserListForm userListForm = (UserListForm) form;

		
		if(request.getSession().getAttribute(AdminUserHandler.ADMINDATA) != null) {
			// Liste der User vorbereiten
			if (userListForm == null) {
				userListForm = new UserListForm();
			}
			
			// abf�llen der List-Elemente
			AppraisalHandler appraisalHandler = AppraisalHandler.getInstance();
			List userList = appraisalHandler.getUserList();
		
			// Benutzerliste sortieren
			Collections.sort(userList);
			// .. zum Formular hinzuf�gen
			userListForm.setUserList(userList);
			
			// wir m�ssen die Liste im Scope speichern, damit sie auf der Seite verf�gbar ist
			request.setAttribute(AppraisalHandler.USERLIST, userListForm);
			
			return mapping.findForward("showList");
		}
		
		// nicht eingeloggt -> zu startseite leiten
		return mapping.findForward("start");
		
		
	}
}