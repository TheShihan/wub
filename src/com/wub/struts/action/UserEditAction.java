/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package com.wub.struts.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.apache.struts.util.MessageResources;

import com.wub.db.Subjects;
import com.wub.db.Users;
import com.wub.handlers.AppraisalHandler;
import com.wub.struts.form.SubjectEditForm;
import com.wub.struts.form.UserEditForm;

/** 
 * MyEclipse Struts
 * Creation date: 01-15-2008
 * 
 * XDoclet definition:
 * @struts.action path="/userEdit" name="userEditForm" input="/form/userEdit.jsp" parameter="do" scope="request" validate="true"
 */
public class UserEditAction extends DispatchAction {

	
	/**
	 * Methode um User zu editieren
	 */
	public ActionForward editUser(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {

		UserEditForm userEditForm = (UserEditForm) form;
		if (userEditForm == null) {
			userEditForm = new UserEditForm();
		}

		// id aus request lesen
		int id = Integer.parseInt(request.getParameter("userId"));

		// element info auslesen und in session speichern
		Users userRow = AppraisalHandler.getInstance().getUserById(id);

		// die daten werden in einer form gespeichert, welche in der session
		// abgespeichert wird
		userEditForm.setUserId(userRow.getUserId());
		userEditForm.setName(userRow.getName());
		userEditForm.setEmail(userRow.getEmail());
		userEditForm.setMainClass(userRow.getMainClass());

		request.getSession().setAttribute(AppraisalHandler.USEREDITFORM,
				userEditForm);

		return mapping.findForward("showEdit");

	}

	/**
	 * Methode um User zu l�schen
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	public ActionForward deleteUser(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {

		// id aus request lesen
		int id = Integer.parseInt(request.getParameter("userId"));

		// Element Anhand der ID l�schen
		AppraisalHandler.getInstance().deleteUserById(id);

		return mapping.findForward("showList");
	}

	/**
	 * Methode addUser leitet weiter auf eine Seite um ein neues Element
	 * hinzuzuf�gen.
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	public ActionForward addUser(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {

		return mapping.findForward("showAdd");

	}

	/**
	 * Methode speichert einen neuen User
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	public ActionForward saveUser(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {

		UserEditForm userEditForm = (UserEditForm) form;
		boolean showFormAgain = false;
		boolean emailError = false;
		
		// Form validieren 
		// Wichtig: Username muss eindeutig sein, sonst gibt es Probleme beim Logon
		if (userEditForm.getName() != null &&
				userEditForm.getName().length() > 0 &&
				userEditForm.getEmail() != null &&
				userEditForm.getEmail().length() > 0 &&
				userEditForm.getMainClass() != null &&
				userEditForm.getMainClass().length() > 0) {

			
			Integer idEmail = AppraisalHandler.getInstance().getUserIdByEmail(userEditForm.getEmail());
			Integer id = userEditForm.getUserId();
			
			if (idEmail != null) {
				// Email bereits verwendet, nur ok wenn zugeh�rig zu diesem User
				if (!(id != null && id.equals(idEmail))) {
					// geh�rt anderem user
					emailError = true;
					showFormAgain = true;
				}
			}
			
			if (!emailError) {
				// wenn eine ID vorhanden ist, wird diese wieder verwendet, ansonsten
				// wird ein neuer Datensatz angelegt werden
				if (id != null && id > 0) {
					// bestehender Eintrag
					Users oldUser = AppraisalHandler.getInstance().getUserById(id);
					oldUser.setName(userEditForm.getName());
					oldUser.setEmail(userEditForm.getEmail());
					oldUser.setMainClass(userEditForm.getMainClass());
	
					AppraisalHandler.getInstance().editUser(oldUser);
				} else {
					// neuer Datensatz
					Users newUserRow = new Users();
					newUserRow.setName(userEditForm.getName());
					newUserRow.setEmail(userEditForm.getEmail());
					newUserRow.setMainClass(userEditForm.getMainClass());
	
					AppraisalHandler.getInstance().createUser(newUserRow);
				}
			}
		} else {
			showFormAgain = true;
		}
		
		// Logik um Formular (mit ung�ltigen Daten) nochmals anzuzeigen
		if (showFormAgain)
		{
			// Form hat Fehler, Seite nochmals anzeigen
			request.getSession().setAttribute(AppraisalHandler.USEREDITFORM,
					userEditForm);
			
			// wir speichern einen Meldungstext (wenn Emailadresse besetzt)
			if (emailError) {
				MessageResources msg = MessageResources.getMessageResources("com.wub.struts.ApplicationResources");
				userEditForm.setMessageText(msg.getMessage("error.admin.user.emailtaken"));
				userEditForm.setEmail("");
			}
			
			if (userEditForm.getUserId() != null) {
				// existing user
				return mapping.findForward("showEdit");
			} else {
				// new user
				return mapping.findForward("showAdd");
			}
		}

		// default
		return mapping.findForward("showList");

	}
	
	
}