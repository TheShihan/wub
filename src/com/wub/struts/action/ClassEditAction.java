/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package com.wub.struts.action;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.wub.db.AppraisalMain;
import com.wub.db.AppraisalStatus;
import com.wub.db.Users;
import com.wub.handlers.AdminUserHandler;
import com.wub.handlers.AppraisalHandler;
import com.wub.misc.Helper;
import com.wub.struts.form.AppraisalClassForm;
import com.wub.struts.form.StudentForm;

/** 
 * MyEclipse Struts
 * Creation date: 02-06-2008
 * 
 * XDoclet definition:
 * @struts.action parameter="do" validate="true"
 */
public class ClassEditAction extends DispatchAction {

	
	
	/**
	 * Methode um eine "Klasse" zu editieren
	 */
	@SuppressWarnings("unchecked")
	public ActionForward editClass(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {

		// das Formular vorbereiten
		AppraisalClassForm appClassForm = (AppraisalClassForm)form;
		if (appClassForm == null) {
			appClassForm = new AppraisalClassForm();
		}

		// id aus request lesen
		int id = Integer.parseInt(request.getParameter("classId"));

		// element info auslesen und in session speichern
		AppraisalMain appMainRow = AppraisalHandler.getInstance().getClassById(id);

		// die Daten werden in einer Form gespeichert, welche in der Session
		// abgespeichert wird
		appClassForm.setAdminId(appMainRow.getAdminId());
		appClassForm.setAppraisalId(appMainRow.getAppraisalId());
		appClassForm.setClassId(appMainRow.getClassId());
		appClassForm.setEndDate(Helper.getInstance().GetStandardDate(appMainRow.getEndDate()));
		appClassForm.setName(appMainRow.getName());
		appClassForm.setStartDate(Helper.getInstance().GetStandardDate(appMainRow.getStartDate()));
		appClassForm.setSubjectId(appMainRow.getSubjectId());
		appClassForm.setSubjectYear(Helper.getInstance().GetStandardDateYearOnly(appMainRow.getSubjectYear()));
		
		// Standardlisten f�llen
		AppraisalHandler appHandler = AppraisalHandler.getInstance();
		
		List basicList = appHandler.getAppraisalBasicList();
		Collections.sort(basicList);
		List subjectList = appHandler.getSubjectList();
		Collections.sort(subjectList);
		List teacherList = AdminUserHandler.getInstance().getAdministrators();
		Collections.sort(teacherList);		
		
		/* Listen f�r die Selects werden nicht im Formular gespeichert, sondern direkt
		 * im request
		 */
		request.getSession().setAttribute("appraisalBasicList", basicList);
		request.getSession().setAttribute("teacherList", teacherList);
		request.getSession().setAttribute("subjectList", subjectList);
		
		// Studentenliste f�llen
		List studentList = appHandler.getUserList();
		Collections.sort(studentList);
		List studentFormList = new ArrayList();
		for (Object object : studentList) {
			Users curUser = (Users)object;
			StudentForm studentForm = new StudentForm();
			if (appHandler.checkUserInStatus(appMainRow.getClassId(), curUser.getUserId())) {
				// Student ist bereits zugeordnet
				studentForm.setActive(true);
			} else {
				studentForm.setActive(false);
			}
			studentForm.setDescription(curUser.getMainClass() + " - " + curUser.getName());
			studentForm.setStudentId(curUser.getUserId());
			// form zur Liste hinzuf�gen
			studentFormList.add(studentForm);
		}
		
		// Studenten zum Hauptform hinzuf�gen
		appClassForm.setStudentList(studentFormList);

		// Form in request speichern
		request.getSession().setAttribute(AppraisalHandler.APPRAISALCLASSFORM,
				appClassForm);

		return mapping.findForward("showEdit");

	}

	/**
	 * Methode um "Klasse" zu l�schen
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	public ActionForward deleteClass(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {

		// id aus request lesen
		int id = Integer.parseInt(request.getParameter("classId"));

		// l�schen der Klasse und der dazugeh�rigen Stati
		// die Stati m�ssen wegen des Check VOR der Klasse gel�scht werden 
		AppraisalHandler.getInstance().deleteClassStatusById(id);
		AppraisalHandler.getInstance().deleteClassById(id);

		return mapping.findForward("showList");
	}

	/**
	 * Methode addClass leitet weiter auf eine Seite um eine neue Klasse
	 * hinzuzuf�gen.
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	@SuppressWarnings("unchecked")
	public ActionForward addClass(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
		// das Formular vorbereiten
		AppraisalClassForm appClassForm = (AppraisalClassForm)form;
		if (appClassForm == null) {
			appClassForm = new AppraisalClassForm();
		}
		
		appClassForm.setReuse(true);
		
		// standardlisten f�llen
		AppraisalHandler appHandler = AppraisalHandler.getInstance();
		
		List basicList = appHandler.getAppraisalBasicList();
		Collections.sort(basicList);
		List subjectList = appHandler.getSubjectList();
		Collections.sort(subjectList);
		List teacherList = AdminUserHandler.getInstance().getAdministrators();
		Collections.sort(teacherList);		
		
		request.getSession().setAttribute("appraisalBasicList", basicList);
		request.getSession().setAttribute("teacherList", teacherList);
		request.getSession().setAttribute("subjectList", subjectList);
		
		// die Studenten-Liste muss etwas spezieller aufgebaut werden
		List studentList = appHandler.getUserList();
		Collections.sort(studentList);
		List studentFormList = new ArrayList();
		for (Object object : studentList) {
			Users curUser = (Users)object;
			StudentForm studentForm = new StudentForm();
			studentForm.setActive(false);
			studentForm.setDescription(curUser.getMainClass() + " - " + curUser.getName());
			studentForm.setStudentId(curUser.getUserId());
			// form zur Liste hinzuf�gen
			studentFormList.add(studentForm);
		}
		
		appClassForm.setStudentList(studentFormList);

		request.getSession().setAttribute(AppraisalHandler.APPRAISALCLASSFORM,
				appClassForm);	

		return mapping.findForward("showAdd");

	}

	/**
	 * Methode speichert eine neue "Klasse" (=versandbereite Beurteilung, welche mit Sch�lern angereichert ist)
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	public ActionForward saveClass(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {

		AppraisalClassForm classForm = (AppraisalClassForm) form;

		boolean formOk = false;
		
		// Form validieren
		if (classForm.getName() != null &&
			classForm.getName().length() > 0) {
			// auch die Daten validieren
			Helper helper = Helper.getInstance();
			if (helper.ValidateDate(classForm.getStartDate()) &&
				helper.ValidateDate(classForm.getEndDate()) &&
				helper.ValidateYear(classForm.getSubjectYear())) {
				// pr�fen ob end datum nach startdatum
				Date startDate = helper.ConvertDate(classForm.getStartDate());
				Date endDate = helper.ConvertDate(classForm.getEndDate());
				if (startDate.getTime() <= endDate.getTime()) {
					// check if there is at least one student assigned
					List studentFormList = classForm.getStudentList();
					boolean hasSelectedStudents = false;
					for (Object object : studentFormList) {
						StudentForm stdForm = (StudentForm)object;
						if (stdForm.getActive() != null && stdForm.getActive()) {
							hasSelectedStudents = true;
							break;
						}
					}
					if (hasSelectedStudents) {
						formOk = true;
						// Daten speichern
						// ---------------
						AppraisalHandler appHandler = AppraisalHandler.getInstance();
						int adminId = classForm.getAdminId();
						int subjectId = classForm.getSubjectId();
						int appraisalBasicId = classForm.getAppraisalId();
						String name = classForm.getName();
						Date subjectYear = helper.ConvertYear(classForm.getSubjectYear());
						
						int lastInsertedId;
						AppraisalMain appMain;
						if (classForm.getClassId() != null) {
							// bestehende "Schulklasse/Beurteilung"
							appMain = appHandler.getClassById(classForm.getClassId());
							
							/* alle bestehenden Stati l�schen, da noch nichts versendet wurde,
							 * ist dies kein Problem. Danach neue Stati generieren
							 */
							appHandler.deleteClassStatusById(classForm.getClassId());
							lastInsertedId = classForm.getClassId();
						}
						else {
							// neue "Schulklasse/Beurteilung"
							appMain = new AppraisalMain();
						}
						
						appMain.setAdminId(adminId);
						appMain.setAppraisalId(appraisalBasicId);
						appMain.setSubjectId(subjectId);
						appMain.setStartDate(startDate);
						appMain.setEndDate(endDate);
						appMain.setName(name);
						appMain.setSent(0);
						appMain.setSubjectYear(subjectYear);
						
						// speichern
						lastInsertedId =  appHandler.createClass(appMain);
						
						// speichern der Stati (wird f�r neue und alte "Klassen" verwendet
						if (lastInsertedId > 0) {
							// nun auch die Studenten-Zuordnung speichern
							for (Object obj : studentFormList) {
								StudentForm stdForm = (StudentForm)obj;
								String userToken = appHandler.generateUserToker();
								if (stdForm.getActive() != null && stdForm.getActive()) {
									AppraisalStatus appStatus = new AppraisalStatus();
									appStatus.setClassId(lastInsertedId);
									appStatus.setToken(userToken);
									appStatus.setUserId(stdForm.getStudentId());
									// speichern
									appHandler.createClassStatus(appStatus);
								}
							}
							
							// pr�fen ob Daten f�r n�chste Beurteilung verwendet
							// werden sollen
							if (classForm.getReuse() != null && classForm.getReuse()) {
								// Daten die sicher nicht nochmals ben�tigt werden l�schen
								classForm.setName("");
								classForm.setInfoMessage("admin.appraisalmanager.class.addsuccess");
								
								request.getSession().setAttribute(AppraisalHandler.APPRAISALCLASSFORM,
										classForm);
								
								/* Sch�lernamen m�ssen erneut geholt werden, da sie beim senden
								 * des Formulares verloren gingen */
								List<StudentForm> studentList = classForm.getStudentList();
								for (StudentForm studentForm : studentList) {
									if (studentForm.getDescription() == null ||
										studentForm.getDescription().length() == 0) {
										Users student = appHandler.getUserById(studentForm.getStudentId());
										studentForm.setDescription(student.getMainClass() + " - " +
											student.getName());
									}
								}
								
								// Formular nochmals anzeigen
								return mapping.findForward("showAdd");
							}
						}
					}
				}
			}
		}
		
		if (!formOk) {
			// Form hat fehler, wird nochmals angzeigt
			request.getSession().setAttribute(AppraisalHandler.APPRAISALCLASSFORM,
					classForm);	
			classForm.setInfoMessage("admin.appraisalmanager.class.addfailure");
			
			return mapping.findForward("showAdd");
		}
		

		return mapping.findForward("showList");

	}
	
	
	
}