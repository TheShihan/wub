package com.wub.handlers;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.struts.util.MessageResources;

import com.wub.db.Administrators;
import com.wub.db.AppraisalBasic;
import com.wub.db.AppraisalBasicDAO;
import com.wub.db.AppraisalConf;
import com.wub.db.AppraisalConfDAO;
import com.wub.db.AppraisalMain;
import com.wub.db.AppraisalMainDAO;
import com.wub.db.AppraisalResults;
import com.wub.db.AppraisalResultsDAO;
import com.wub.db.AppraisalStatus;
import com.wub.db.AppraisalStatusDAO;
import com.wub.db.Subjects;
import com.wub.db.SubjectsDAO;
import com.wub.db.TextElements;
import com.wub.db.TextElementsDAO;
import com.wub.db.Users;
import com.wub.db.UsersDAO;
import com.wub.db.VoteStyles;
import com.wub.misc.CommentBean;
import com.wub.misc.Helper;
import com.wub.struts.form.AppraisalConfigEditForm;
import com.wub.struts.form.AppraisalStatisicVoteElementForm;
import com.wub.struts.form.AppraisalStatisticForm;
import com.wub.struts.form.ClassSendForm;
import com.wub.struts.form.ClassSendListForm;

/**
 * Klasse um Beurteilungen zu "handeln". Beurteilungen auflisten, erstellen,
 * löschen, editieren etc.
 * 
 * @author Patric Schielke
 * 
 */
@SuppressWarnings("unchecked")
public class AppraisalHandler {

	public static final String APPRAISALEDITFORM = "appraisalEditForm";
	public static final String APPRAISALLIST = "appraisalListForm";
	public static final String TEXTELEMENTEDITFORM = "textElementEditForm";
	public static final String TEXTELEMENTLIST = "textElementListForm";
	public static final String SUBJECTEDITFORM = "subjectEditForm";
	public static final String SUBJECTLIST = "subjectListForm";
	public static final String USEREDITFORM = "userEditForm";
	public static final String USERLIST = "userListForm";
	public static final String APPRAISALBASICEDITFORM = "appraisalCoreBasicEditForm";
	public static final String APPRAISALBASICLIST = "appraisalCoreBasicListForm";
	public static final String APPRAISALCLASSFORM = "appClassForm";
	public static final String APPRAISALCLASSLIST = "appraisalClassListForm";
	public static final String APPRAISALSENDFORM = "appraisalSendForm";
	public static final String APPRAISALFORM = "appraisalForm";
	public static final String APPRAISALSTATFORM = "appraisalStatForm";
	private static AppraisalHandler instance = null;

	/**
	 * Constructor
	 */
	private AppraisalHandler() {
	}

	/**
	 * Singleton Pattern
	 * 
	 * @return
	 */
	public static AppraisalHandler getInstance() {
		if (instance == null) {
			instance = new AppraisalHandler();
		}
		return instance;
	}

	// ------- TEXT ELEMENTS

	/**
	 * Liefert eine Liste mit allen Text-Elementen zurück.
	 * 
	 * @return
	 */
	public List getTextElementList() {
		TextElementsDAO textElementsDAO = new TextElementsDAO();
		return textElementsDAO.findAll();
	}

	/**
	 * Findet ein Text-Element anhand der ID
	 * 
	 * @param id
	 * @return
	 */
	public TextElements getTextElementById(int id) {
		TextElementsDAO textElementsDAO = new TextElementsDAO();
		return textElementsDAO.findById(id);
	}

	/**
	 * Text-Element anhand seiner ID löschen
	 * 
	 * @param id
	 * @return
	 */
	public boolean deleteTextElementById(int id) {
		// nur löschen, wenn Text Element nirgends zugeordnet ist
		AppraisalConfDAO appConfDAO = new AppraisalConfDAO();
		if (appConfDAO.findByTextId(id).size() <= 0) {
			if (true) {
				TextElementsDAO textElementsDAO = new TextElementsDAO();
				textElementsDAO.delete(getTextElementById(id));
				return true;
			}
		}

		return false;
	}

	/**
	 * Editiert einen bestehenden Text-Element-Eintrag.
	 * 
	 * @param oldTextElement
	 */
	public void editTextElement(TextElements oldTextElement) {
		TextElementsDAO textElementsDAO = new TextElementsDAO();
		textElementsDAO.attachClean(oldTextElement);
	}

	/**
	 * Speichert ein neues Text-Element in der Datenbank.
	 * 
	 * @param newTextElement
	 */
	public void createTextElement(TextElements newTextElement) {
		TextElementsDAO textElementsDAO = new TextElementsDAO();
		textElementsDAO.save(newTextElement);
	}

	// ------- SUBJECTS --------------

	/**
	 * Liefert eine Liste mit allen Subjects zurück.
	 * 
	 * @return
	 */
	public List getSubjectList() {
		SubjectsDAO subjectDAO = new SubjectsDAO();
		return subjectDAO.findAll();
	}

	/**
	 * Liefert eine Liste mit Subjects zurück, die in einer bereits versendeten
	 * Beurteilung verwendet werden.
	 * 
	 * @return Liste mit verwendeten Subjects
	 */
	public List getUsedSubjects() {
		List<Subjects> allSubjects = getSubjectList();
		List<Subjects> usedSubjects = new ArrayList<Subjects>();
		for (Subjects subjects : allSubjects) {
			if (checkSubjectInUse(subjects.getSubjectId())) {
				// zur Liste hinzufügen
				usedSubjects.add(subjects);
			}
		}
		return usedSubjects;
	}

	/**
	 * Findet ein Subject anhand seiner ID
	 * 
	 * @param id
	 * @return
	 */
	public Subjects getSubjectById(int id) {
		SubjectsDAO subjectsDAO = new SubjectsDAO();
		return subjectsDAO.findById(id);
	}

	/**
	 * Löscht ein Subject anhand seiner ID
	 * 
	 * @param id
	 * @return
	 */
	public boolean deleteSubjectById(int id) {
		// Zugeorndete Subjects dürfen nicht gelöscht werden
		AppraisalMainDAO appMainDAO = new AppraisalMainDAO();
		List appMainList = appMainDAO.findBySubjectId(id);
		if (!(appMainList != null && appMainList.size() > 0)) {
			// hat keine Zurodnungen, kann gelöscht werden
			SubjectsDAO subjectsDAO = new SubjectsDAO();
			subjectsDAO.delete(getSubjectById(id));
			return true;
		}

		return false;
	}

	/**
	 * Editiert ein bestehendes Subject
	 * 
	 * @param oldSubject
	 */
	public void editSubject(Subjects oldSubject) {
		SubjectsDAO subjectsDAO = new SubjectsDAO();
		subjectsDAO.attachClean(oldSubject);
	}

	/**
	 * Speichert ein neues Subject in der Datenbank.
	 * 
	 * @param newSubject
	 */
	public void createSubject(Subjects newSubject) {
		SubjectsDAO subjectsDAO = new SubjectsDAO();
		subjectsDAO.save(newSubject);
	}

	// --------- Users

	/**
	 * Liefert eine Liste mit allen Usern zurück.
	 * 
	 * @return
	 */
	public List getUserList() {
		UsersDAO userDAO = new UsersDAO();
		return userDAO.findAll();
	}

	/**
	 * Findet einen User anhand seiner ID
	 * 
	 * @param id
	 * @return
	 */
	public Users getUserById(int id) {
		UsersDAO usersDAO = new UsersDAO();
		return usersDAO.findById(id);
	}

	/**
	 * Löscht einen User anhand seiner ID
	 * 
	 * @param id
	 * @return
	 */
	public boolean deleteUserById(int id) {
		// user auch löschen wenn aktive Beurteilungen zugeordnet,
		// diese Zuordnung muss aber gelöscht werden
		List appList = UserHandler.getInstance().getStatusListByUserId(id);
		if (appList != null && appList.size() > 0) {
			// der User hat Beurteilungen zugeordnet
			for (Object statusObj : appList) {
				AppraisalStatus appStatus = (AppraisalStatus) statusObj;
				deleteClassStatus(appStatus);
			}
		}

		UsersDAO usersDAO = new UsersDAO();
		usersDAO.delete(getUserById(id));
		return true;
	}

	/**
	 * Editiert ein bestehendes Subject
	 * 
	 * @param oldUser
	 */
	public void editUser(Users oldUser) {

		UsersDAO usersDAO = new UsersDAO();
		usersDAO.attachClean(oldUser);
	}

	/**
	 * Speichert ein neues Subject in der Datenbank.
	 * 
	 * @param newUser
	 */
	public void createUser(Users newUser) {
		UsersDAO usersDAO = new UsersDAO();
		usersDAO.save(newUser);
	}

	/**
	 * es kann ja möglich sein, das es zwei Personen mit gleichem Namen gibt,
	 * aber mit gleicher Email sollte unmöglich sein. Für den UserLogon wird
	 * deshalb die Emailadresse verwendet, zur Sicherheit, dass keine zwei User
	 * trotzdem die gleiche Mail- adresse haben, wird diese beim editieren und
	 * speichern von Benutzern mit Hilfe dieser Methode überprüft.
	 * 
	 * @param userEmail
	 *            String, welcher die Emailadresse, welche geprüft werden muss
	 *            enthält
	 * @return null wenn kein User gefunden wurde sonst die ID des Benutzers
	 */
	public Integer getUserIdByEmail(String userEmail) {
		UsersDAO usersDAO = new UsersDAO();
		// sollte maximal ein Element haben (der "alte" User selbst)
		List userList = usersDAO.findByEmail(userEmail);
		for (Object userObj : userList) {
			Users user = (Users) userObj;
			return user.getUserId();
		}
		return null;
	}

	// --------- appraisal (core) basic --------

	/**
	 * Liefert eine Liste mit allen Basis-Beurteilungen zurück.
	 * 
	 * @return
	 */
	public List getAppraisalBasicList() {
		AppraisalBasicDAO appraisalBasicDAO = new AppraisalBasicDAO();
		return appraisalBasicDAO.findAll();
	}

	/**
	 * Findet einen Basis-Beurteilung anhand ihrer ID
	 * 
	 * @param id
	 * @return
	 */
	public AppraisalBasic getAppraisalBasicById(int id) {
		AppraisalBasicDAO appraisalBasicDAO = new AppraisalBasicDAO();
		return appraisalBasicDAO.findById(id);
	}

	/**
	 * Holt alle Basis Konfigurationen zu einer bestimmten Basis-Beurteilungs-ID
	 * aus der Datenbank.
	 * 
	 * @param id
	 * @return
	 */
	public List<AppraisalConf> getAppraisalBasicConfListById(int id) {
		AppraisalConfDAO appConfDAO = new AppraisalConfDAO();
		return (List<AppraisalConf>) appConfDAO.findByAppraisalId(id);
	}

	/**
	 * Löscht eine Basis-Beurteilung anhand ihrer ID
	 * 
	 * @param id
	 * @return
	 */
	public boolean deleteAppraisalBasicById(int id) {
		AppraisalConfDAO appConfDAO = new AppraisalConfDAO();
		List appConfList = appConfDAO.findByAppraisalId(id);
		// nur löschen wenn keine konfigurationen vorhanden
		if (!(appConfList != null && appConfList.size() > 0)) {
			// auch prüfen ob "Klassen" zugeordnet, wenn ja, nicht löschen
			AppraisalMainDAO appMainDAO = new AppraisalMainDAO();
			List appMainList = appMainDAO.findByAppraisalId(id);
			if (appMainList == null || appMainList.size() == 0) {
				AppraisalBasicDAO appraisalBasicDAO = new AppraisalBasicDAO();
				appraisalBasicDAO.delete(getAppraisalBasicById(id));

				// auch Konfigurationen löschen
				deleteAppraisalConfBasic(id);
				return true;
			}
		}

		return false;
	}

	/**
	 * Löscht Beurteilungs-Konfigurationen zu einer bestimmten Beurteilungs ID
	 * 
	 * @param id
	 */
	private void deleteAppraisalConfBasic(int id) {
		AppraisalConfDAO appConfDAO = new AppraisalConfDAO();
		/*
		 * finde alle Elemente/Konfiguration zu dieser Beurteilung und lösche
		 * sie der Reihe nach
		 */
		List appConfDelList = appConfDAO.findByAppraisalId(id);
		for (Object appConfToDel : appConfDelList) {
			AppraisalConf curConf = (AppraisalConf) appConfToDel;
			appConfDAO.delete(curConf);
			appConfDAO.getSession().flush();
		}
	}

	/**
	 * Editiert eine bestehende Basis-Beurteilung
	 * 
	 * @param oldBasic
	 */
	public void editAppraisalBasic(AppraisalBasic oldBasic) {
		AppraisalBasicDAO appraisalBasicDAO = new AppraisalBasicDAO();
		appraisalBasicDAO.attachClean(oldBasic);
	}

	/**
	 * Speichert eine neue Basis-Beurteilung in der Datenbank.
	 * 
	 * @param newBasic
	 * @param appraisalConfList
	 */
	public int saveAppraisalBasic(AppraisalBasic newBasic) {
		int returnVal = 0;
		AppraisalBasicDAO appraisalBasicDAO = new AppraisalBasicDAO();
		appraisalBasicDAO.save(newBasic);

		// get the ID of the saved item
		int lastInsertedId = 0;
		List basicList = appraisalBasicDAO.findByName(newBasic.getName());
		if (basicList.size() > 0) {
			/*
			 * as we only allow one elment with a specific name there can only
			 * be one elment in the list, so it is save to take the first one
			 */
			lastInsertedId = ((AppraisalBasic) basicList.get(0))
					.getAppraisalId();
		}

		if (lastInsertedId != 0) {
			returnVal = lastInsertedId;
		}

		return returnVal;
	}

	/**
	 * Editiert Basis-Konfigurationen
	 * 
	 * @param appraisalId
	 * @param appraisalConfList
	 */
	public void editAppraisalBasicConfig(int appraisalId,
			List<AppraisalConf> appraisalConfList) {
		// am einfachsten: einfach alle alten Löschen und die neuen Speichern
		deleteAppraisalConfBasic(appraisalId);
		saveAppraisalBasicConfigurations(appraisalConfList);
	}

	/**
	 * Speichert eine Liste mit Basic-Konfigurationen ab
	 * 
	 * @param configList
	 */
	public void saveAppraisalBasicConfigurations(List<AppraisalConf> configList) {
		AppraisalConfDAO appConfDAO = new AppraisalConfDAO();
		if (configList != null && configList.size() > 0) {
			for (AppraisalConf appraisalConf : configList) {
				appConfDAO.save(appraisalConf);
				appConfDAO.getSession().flush();
			}
		}
	}

	// ----------- CLASS (Eine versandfertige Beurteilung (Schulklasse->Class))
	// ----------------

	/**
	 * Liefert eine Liste mit allen Beurteilungen zurück.
	 * 
	 * @return
	 */
	public List getClassList() {
		AppraisalMainDAO appraisalMainDAO = new AppraisalMainDAO();
		return appraisalMainDAO.findAll();
	}

	/**
	 * Liefert eine Liste mit allen Beurteilungen zurück, welche bereits
	 * versendet wurden
	 * 
	 * @return Liste mit versendeten Beurteilungen
	 */
	public List getClassListSent() {
		AppraisalMainDAO appraisalMainDAO = new AppraisalMainDAO();
		List<AppraisalMain> sentClasses = new ArrayList<AppraisalMain>();
		List<AppraisalMain> appMainList = appraisalMainDAO.findAll();
		for (AppraisalMain appraisalMain : appMainList) {
			if (appraisalMain.getSent() != null
					&& (int) appraisalMain.getSent() == 1) {
				// zur Liste
				sentClasses.add(appraisalMain);
			}
		}
		return sentClasses;
	}

	/**
	 * Liefert alle Stati zu einer Klasse/Beurteilung zurück
	 * 
	 * @param classId
	 * @return Liste mit Stati
	 */
	public List getClassStatusList(int classId) {
		AppraisalStatusDAO appraisalStatusDAO = new AppraisalStatusDAO();
		return appraisalStatusDAO.findByClassId(classId);
	}

	/**
	 * Findet eine Klasse anhand ihrer ID
	 * 
	 * @param id
	 * @return
	 */
	public AppraisalMain getClassById(int id) {
		AppraisalMainDAO appraisalMainDAO = new AppraisalMainDAO();
		return appraisalMainDAO.findById(id);
	}

	/**
	 * Sucht "Klassen" (AppraisalMain) anhand einer adminId (Lehrer ID) und gibt
	 * diese als Liste zurück
	 * 
	 * @param adminId
	 * @return Liste mit AppraisalMain Objekten
	 */
	public List getClassListByAdminId(int adminId) {
		AppraisalMainDAO appraisalMainDAO = new AppraisalMainDAO();
		return appraisalMainDAO.findByAdminId(adminId);
	}

	/**
	 * Findet einen Beurteilungs-Status anhand seiner ID
	 * 
	 * @param statusId
	 * @return
	 */
	public AppraisalStatus getStatusById(int statusId) {
		AppraisalStatusDAO appraisalStatusDAO = new AppraisalStatusDAO();
		return appraisalStatusDAO.findById(statusId);
	}

	/**
	 * Löscht eine Klasse anhand ihrer ID
	 * 
	 * @param id
	 * @return
	 */
	public boolean deleteClassById(int id) {
		AppraisalMainDAO appraisalMainDAO = new AppraisalMainDAO();
		AppraisalMain appClass = getClassById(id);
		if (appClass.getSent() == null || appClass.getSent() == 0) {
			appraisalMainDAO.delete(appClass);
			return true;
		}
		return false;
	}

	/**
	 * Löscht alle Stati (aus Appraisal_Status) zu einer ClassId
	 * 
	 * @param classId
	 */
	@SuppressWarnings("unchecked")
	public void deleteClassStatusById(int classId) {
		// nur löschen wenn beurteilung noch nicht versendet
		AppraisalMain appClass = getClassById(classId);
		if (appClass.getSent() == null || appClass.getSent() == 0) {
			AppraisalStatusDAO appraisalStatusDAO = new AppraisalStatusDAO();
			// alle zugehörigen Stati ermitteln
			List delList = appraisalStatusDAO.findByClassId(classId);
			for (Object object : delList) {
				AppraisalStatus curDelStatus = (AppraisalStatus) object;
				// löschen
				appraisalStatusDAO.delete(curDelStatus);
				appraisalStatusDAO.getSession().flush();
			}
		}
	}

	/**
	 * Editiert eine bestehende Klasse
	 * 
	 * @param oldClass
	 */
	public void editClass(AppraisalMain oldClass) {
		AppraisalMainDAO appraisalMainDAO = new AppraisalMainDAO();
		appraisalMainDAO.attachClean(oldClass);
		appraisalMainDAO.getSession().flush();
	}

	/**
	 * Updated eine "Klasse" in den Status "gesendet"
	 * 
	 * @param classId
	 */
	private void updateClassToSent(int classId) {
		AppraisalMainDAO appraisalMainDAO = new AppraisalMainDAO();
		AppraisalMain appMain = appraisalMainDAO.findById(classId);
		appMain.setSent(new Integer(1));
		// Änderung speichern
		editClass(appMain);
	}
	
	/**
	 * Updated eine Basis-Beurteilung in den Status "gesperrt"
	 * 
	 * @param appraisalId Die ID der Basis-Beurteilung
	 */
	private void updateAppraisalToLocked(int appraisalId) {
		AppraisalBasicDAO appBasicDAO = new AppraisalBasicDAO();
		AppraisalBasic appBasic = appBasicDAO.findById(appraisalId);
		appBasic.setLocked((new Integer(1)).byteValue());
		// Änderung speichern
		editAppraisalBasic(appBasic);
	}
	

	/**
	 * Speichert eine neue Klasse in der Datenbank.
	 * 
	 * @param newClass Das Objekt das die neue Klasse/Beurteilung repräsentiert
	 */
	public int createClass(AppraisalMain newClass) {
		int returnVal = 0;
		AppraisalMainDAO appraisalMainDAO = new AppraisalMainDAO();

		appraisalMainDAO.save(newClass);
		appraisalMainDAO.getSession().flush();

		// get the ID of the saved item
		int lastInsertedId = 0;
		List basicList = appraisalMainDAO.findByName(newClass.getName());
		if (basicList.size() > 0) {
			AppraisalMain savedAppMain = (AppraisalMain) basicList
					.get(basicList.size() - 1);
			lastInsertedId = savedAppMain.getClassId();
		}

		if (lastInsertedId != 0) {
			returnVal = lastInsertedId;
		}

		return lastInsertedId;
	}

	/**
	 * Speichert ein Beurteilungs-Status ab
	 * 
	 * @param newStatus Das Objekt, das den neuen Status repräsentiert.
	 */
	public void createClassStatus(AppraisalStatus newStatus) {
		AppraisalStatusDAO appraisalStatusDAO = new AppraisalStatusDAO();
		appraisalStatusDAO.save(newStatus);
		appraisalStatusDAO.getSession().flush();
	}

	/**
	 * Löscht einen "Klassen"-Status
	 * 
	 * @param delStatus
	 *            der Status der gelöscht werden soll
	 */
	public void deleteClassStatus(AppraisalStatus delStatus) {
		AppraisalStatusDAO appraisalStatusDAO = new AppraisalStatusDAO();
		appraisalStatusDAO.delete(delStatus);
		appraisalStatusDAO.getSession().flush();
	}

	/**
	 * Editiert einen bestehenden AppraisalStatus-Eintrag
	 * 
	 * @param editStatus
	 *            der Status-Eintrag der geändert wird
	 */
	public void editClassStatus(AppraisalStatus editStatus) {
		AppraisalStatusDAO appraisalStatusDAO = new AppraisalStatusDAO();
		appraisalStatusDAO.attachClean(editStatus);
		appraisalStatusDAO.getSession().flush();
	}

	/**
	 * Prüft ob ein User in der Statusliste gefunden werden kann
	 * 
	 * @param classId
	 *            Die Klassen ID
	 * @param userId
	 *            Die User ID
	 * @return
	 */
	public boolean checkUserInStatus(int classId, int userId) {
		AppraisalStatusDAO appraisalStatusDAO = new AppraisalStatusDAO();
		List result = appraisalStatusDAO
				.findByClassIdAndUserId(classId, userId);
		if (result.size() > 0) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Prüft ob ein Subjekt in einer Beurteilung verwendet wird (die Beurteilung
	 * muss bereits versendet worden sein).
	 * 
	 * @param subjectId
	 *            die ID des Subjektes (Faches)
	 * @return true = wird verwendet, false = wird nicht verwendet
	 */
	public boolean checkSubjectInUse(int subjectId) {
		AppraisalMainDAO appMainDAO = new AppraisalMainDAO();
		List<AppraisalMain> appMainList = appMainDAO.findBySubjectId(subjectId);
		for (AppraisalMain appraisalMain : appMainList) {
			if (appraisalMain.getSent() != null
					&& (int) appraisalMain.getSent() == 1) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Prüft ob ein Admin/Lehrer in einer Beurteilung verwendet wird (die
	 * Beurteilung muss bereits versendet worden sein).
	 * 
	 * @param adminId
	 *            die ID des Admins (Lehrers)
	 * @return true = wird verwendet, false = wird nicht verwendet
	 */
	public boolean checkAdminInUse(int adminId) {
		AppraisalMainDAO appMainDAO = new AppraisalMainDAO();
		List<AppraisalMain> appMainList = appMainDAO.findByAdminId(adminId);
		for (AppraisalMain appraisalMain : appMainList) {
			if (appraisalMain.getSent() != null
					&& (int) appraisalMain.getSent() == 1) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Erstelle ein Formular mit Daten zum Versand von Beurteilungen
	 * 
	 * @param classId die Id der Klasse/Beurteilung
	 * @return eine Liste mit Formularen
	 */
	public ClassSendListForm getClassSendListForm(int classId) {
		ClassSendListForm form = new ClassSendListForm();
		// hole Informationen zur "Klasse"
		AppraisalMain main = getClassById(classId);
		if (main != null) {
			form.setName(main.getName());
			if (main.getSent() != null && main.getSent() == 1) {
				form.setSent(true);
			} else {
				form.setSent(false);
			}
			/*
			 * steuern ob man einzelne Beurteilungen schicken kann oder zuerst
			 * alle lossenden muss.
			 */
			if (form.getSent()) {
				form.setSendToAll(false);
			} else {
				form.setSendToAll(true);
			}
			form.setStart(main.getStartDateString());
			form.setEnd(main.getEndDateString());
			Administrators admin = AdminUserHandler.getInstance().getAdminById(
					main.getAdminId());
			form.setTeacher(admin.getName());
			String subject = getSubjectById(main.getSubjectId()).getName();
			form.setSubject(subject);
			/*
			 * Liste mit Benutzern, welche die Beurteilung noch NICHT ausgefüllt
			 * haben erstellen und abfüllen
			 */
			List statusList = getClassStatusList(classId);
			List sendFormList = new ArrayList();
			if (statusList != null) {
				for (Object statusObj : statusList) {
					AppraisalStatus appStatus = (AppraisalStatus) statusObj;
					// nur Stati hinzufügen, welche noch reellen Usern
					// zugeordnet sind
					// User id -1 bedeutet -> anonymisiert da bereits ausgefüllt
					if (appStatus.getUserId() != -1) {
						ClassSendForm sendForm = new ClassSendForm();
						sendForm.setSend(new Boolean(true));
						// Hole Benutzerinformation
						Users user = getUserById(appStatus.getUserId());
						sendForm.setUserEmail(user.getEmail());
						sendForm.setStatusId(appStatus.getStatusId());
						sendForm.setUserId(user.getUserId());
						sendForm.setUserName(user.getName());
						// Form zu Liste hinzufügen
						sendFormList.add(sendForm);
					}
				}
			}
			form.setUserList(sendFormList);

			// Datum prüfen
			Date date = new Date();
			if (date.getTime() > main.getEndDate().getTime()) {
				form.setError(true);
			} else {
				form.setError(false);
			}
		}

		return form;
	}

	/**
	 * Nimmt eine Liste mit Stati auf und versendet den ausgewählten Benutzern
	 * innerhalb dieser Liste ein Mail mit der Information, dass sie eine
	 * Beurteilung ausfüllen können.
	 * 
	 * @param sendFormList
	 * @return
	 */
	public boolean sendAppraisalMails(List sendFormList) {
		if (sendFormList != null) {
			int classId = -1;
			boolean allMailsSentOk = true;
			for (Object sendFormObj : sendFormList) {
				ClassSendForm sendForm = (ClassSendForm) sendFormObj;
				if (sendForm.getSend() != null && sendForm.getSend()) {
					// Mail muss gesendet werden
					if (classId == -1) {
						// richtige Klasse herausfinden
						AppraisalStatus appStatus = getStatusById(sendForm
								.getStatusId());
						if (appStatus.getClassId() != null) {
							classId = appStatus.getClassId();
						}
					}
					if (!sendAppraisalMail(sendForm.getStatusId())) {
						/*
						 * wenn auch nur ein Mail nicht korrekt gesendet wurde
						 * wird dies festgehalten
						 */
						allMailsSentOk = false;
					}
				}
			}

			// prüfen ob senden Flag für Klasse gsetzt, wenn nicht, dann setzen
			if (classId != -1 && allMailsSentOk) {
				AppraisalMain appMain = getClassById(classId);
				if (appMain.getSent() == null
						|| (appMain.getSent() != null && appMain.getSent() != 1)) {
					updateClassToSent(classId);
				}
				/* die Basisbeurteilung muss gesperrt werden, ansonsten könnten
				 * Ergebnisse nachträglich verfälscht werden
				 */
				int appId = appMain.getAppraisalId();
				AppraisalBasic appBasic = getAppraisalBasicById(appId);
				if (appBasic.getLocked() == null ||
						appBasic.getLocked() !=  (new Integer(1)).byteValue()) {
					updateAppraisalToLocked(appId);					
				}
				/*
				 * true egal ob flag gesetzt wurde, da es sich um Nachsendungen
				 * handeln kann
				 */
				return true;
			}
		}
		return false;
	}

	// --------- Statistiken ---------------

	/**
	 * Gibt die durchschnittliche Bewertung für eine angegebene Class- und Text-ID
	 */
	private Double getVotePerTextAndClassId(int classId, int textId) {
		List<AppraisalStatus> statusList = getClassStatusList(classId);
		AppraisalResultsDAO appResDAO = new AppraisalResultsDAO();
		
		int noVotes = 0;
		int voteSummary = 0;
		
		if (statusList != null && statusList.size() > 0) {
			for (AppraisalStatus appStatus : statusList) {
				int statusId = appStatus.getStatusId();
				List<AppraisalResults> appResList =
					appResDAO.findByStatusId(statusId);
				if (appResList != null &&
					appResList.size() > 0) {
					// wir sind nur am Resultat für die jeweilige TextId interessiert
					for (AppraisalResults appRes : appResList) {
						if ((int)appRes.getTextId() == textId) {
							voteSummary += appRes.getVote().intValue();
							noVotes++;
						}
					}
				}
			}
		}
		// Berechnung des Mittels
		if (noVotes != 0) {
			Double result = new Double(voteSummary) / new Double(noVotes);
			return result;
		} else {
			return null;
		}
	}
	
	
	/**
	 * Gibt alle Kommentare für eine Kriterium (Text-ID) zu einer Beurteilung (classId)
	 * zurück.
	 */
	private List<CommentBean> getCommentsPerTextAndClassId(int classId, int textId) {
		List<AppraisalStatus> statusList = getClassStatusList(classId);
		AppraisalResultsDAO appResDAO = new AppraisalResultsDAO();
		
		List<CommentBean> commentList = new ArrayList<CommentBean>();
		
		if (statusList != null && statusList.size() > 0) {
			for (AppraisalStatus appStatus : statusList) {
				int statusId = appStatus.getStatusId();
				List<AppraisalResults> appResList =
					appResDAO.findByStatusId(statusId);
				if (appResList != null &&
					appResList.size() > 0) {
					// wir sind nur am Resultat für die jeweilige TextId interessiert
					for (AppraisalResults appRes : appResList) {
						if ((int)appRes.getTextId() == textId) {
							// Bean wird für das Speichern verwendet
							String comment = appRes.getComment();
							if (comment != null && comment.length() > 0) {
								// Kommentar zur Liste hinzufügen
								CommentBean commentBean = new CommentBean();
								commentBean.setCommentText(comment);
								commentList.add(commentBean);
							}
						}
					}
				}
			}
		}
		return commentList;
	}
	
	
	/**
	 * Ermittelt die Anzahl effektiv abgegebenen Bewertungen pro Beurteilung.
	 * @param classId ID der Beurteilung
	 * @return Integer mit Anzahl
	 */
	private int getVotCountPerClass(int classId) {
		List<AppraisalStatus> statusList = getClassStatusList(classId);
		AppraisalResultsDAO appResDAO = new AppraisalResultsDAO();
		int voteCount = 0;
		for (AppraisalStatus status : statusList) {
			List resList = appResDAO.findByStatusId(status.getStatusId());
			if (resList != null && resList.size() > 0) {
				voteCount++;
			}
		}
		return voteCount;
	}
		

	/**
	 * Generiert statistische Daten für die Auswertung einer Beurteilung, pro
	 * "Klasse".
	 * 
	 * @param classId
	 * @return Bean mit statistischen Daten
	 */
	public AppraisalStatisticForm getStatsPerClass(int classId) {
		// Formular/Bean vorbereiten
		AppraisalStatisticForm appStatForm = new AppraisalStatisticForm();
		AppraisalMain appMain = getClassById(classId);
		int appraisalId = appMain.getAppraisalId();
		List<AppraisalConf> textElementList = getAppraisalBasicConfListById(appraisalId);
		if (textElementList != null) {
			// Informationen abfüllen
			appStatForm.setDescription(appMain.getName());
			appStatForm.setTeacher(AdminUserHandler.getInstance().getAdminById(appMain.getAdminId()).getName());
			appStatForm.setSubject(getSubjectById(appMain.getSubjectId()).getName());
			appStatForm.setSubjectYear(appMain.getSubjectYearString());
			appStatForm.setStartDate(appMain.getStartDateString());
			appStatForm.setEndDate(appMain.getEndDateString());
			appStatForm.setVoteCount(getVotCountPerClass(classId));
			
			// Maximal mögliche Bewertung ermitteln
			AppraisalBasic appBasic =
				getAppraisalBasicById(appMain.getAppraisalId());
			VoteStyles voteStyle =
				VoteStyleHandler.getInstance().getVoteStyleById(appBasic.getVoteStyle());
			appStatForm.setMaxVote(voteStyle.getVoteCount().intValue());
			
			// Bewertungen und Kommentare zu den Kritererien suchen
			List<AppraisalStatisicVoteElementForm> voteList =
				new ArrayList<AppraisalStatisicVoteElementForm>();
			
			for (AppraisalConf appConf : textElementList) {
				int textId = appConf.getTextId();
				Double vote = getVotePerTextAndClassId(classId, textId);
				List<CommentBean> comments = getCommentsPerTextAndClassId(classId, textId);
				// Runden 
				if (vote == null) {
					vote = 0D;
				}
				vote = Math.round(vote * 100D) / 100D;
				
				AppraisalStatisicVoteElementForm voteElementForm =
					new AppraisalStatisicVoteElementForm();
				voteElementForm.setText(getTextElementById(textId).getTitle());
				voteElementForm.setVote(vote);
				voteElementForm.setCommentList(comments);
				voteList.add(voteElementForm);
			}
			
			// Liste zum Form hinzufügen
			appStatForm.setVoteElementList(voteList);
			
			return appStatForm;			
		}
		return null;
	}
	

	// --------- Special ---------------

	/**
	 * Versendet ein Email an einen Benutzer, und informiert ihn, dass er eine
	 * Beurteilung ausfüllen kann.
	 */
	public boolean sendAppraisalMail(int statusId) {
		// hole Statusdaten
		AppraisalStatus appStatus = getStatusById(statusId);
		int userId = appStatus.getUserId();
		// hole userdaten
		Users user = getUserById(userId);
		String emailTo = user.getEmail();
		MessageResources messages = MessageResources
				.getMessageResources("com.wub.struts.ApplicationResources");
		String emailSubject = messages
				.getMessage("admin.appraisalmanager.email.appraisaldelivery.subject");
		String emailBody = messages
				.getMessage("admin.appraisalmanager.email.appraisaldelivery");
		// hole settings;
		String smtpServer = SettingsHandler.getInstance().getSettingByName(
				"SmtpServer");
		String smtpFrom = SettingsHandler.getInstance().getSettingByName(
				"SmtpFrom");

		// Mit Anmeldung?
		String smtpUseLogin = SettingsHandler.getInstance().getSettingByName(
				"SmtpUseAuth");
		String smtpUser = null, smtpPassword = null;
		boolean useLogin = false;
		if (smtpUseLogin.equals("1")) {
			smtpUser = SettingsHandler.getInstance().getSettingByName(
					"SmtpUsername");
			smtpPassword = SettingsHandler.getInstance().getSettingByName(
					"SmtpPassword");
			if (smtpUser != null && smtpPassword != null) {
				useLogin = true;
			}
		}

		// Bilde pfad zur Beurteilung
		String url = SettingsHandler.getInstance().getSettingByName("WubUri");
		if (url == null) {
			url = "http://localhost/wub/";
		} else if (url.charAt(url.length() - 1) != '/') {
			url = url + "/";
		}
		String urlClickable = url + "userLogon.do?useremail=" + user.getEmail()
				+ "&token=" + appStatus.getToken();

		// Pfad zum manuellen Login
		String urlManual = url + "openUserLogon.do";

		// Emailbody Parameter auffüllen
		if (emailBody == null) {
			emailBody = "";
		}
		emailBody = MessageFormat.format(emailBody, urlClickable, urlManual,
				user.getEmail(), appStatus.getToken());
		try {
			if (useLogin) {
				// mit Anmeldung am SMTP Server
				Helper.getInstance().sendMail(smtpServer, emailTo, smtpFrom,
						emailSubject, emailBody, smtpUser, smtpPassword);
			} else {
				// ohne Anmeldung
				Helper.getInstance().sendMail(smtpServer, emailTo, smtpFrom,
						emailSubject, emailBody);
			}
			return true;
		} catch (Exception ex) {// eg SMTPSendFailedException
			return false;
		}
	}

	/**
	 * Liefert eine Liste mit einer gewünschten Anzahl von appraisal config edit
	 * FORM Elementen Forgefüllt mit Text Ids
	 */
	public boolean appraisalBasicNameExists(String name) {
		AppraisalBasicDAO basicDAO = new AppraisalBasicDAO();
		List basicList = basicDAO.findByName(name);
		if (basicList.size() > 0) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Holt eine standard Basis-Konfigurationsliste ab.
	 * 
	 * @return
	 */
	public List getDefaultAppraisalConfEditList() {
		List appraisalConfFormList = new ArrayList();

		List textElements = getTextElementList();

		for (Object textElement : textElements) {
			String title = ((TextElements) textElement).getTitle();
			String text = ((TextElements) textElement).getText();
			Integer textId = ((TextElements) textElement).getTextId();
			// Werte im Form setzen
			AppraisalConfigEditForm appConfEditForm = new AppraisalConfigEditForm();
			appConfEditForm.setText(title + "\n\r" + text);
			appConfEditForm.setTextId(textId);

			// Form zur Liste hinzufügen
			appraisalConfFormList.add(appConfEditForm);
		}

		return appraisalConfFormList;
	}

	/**
	 * Generiert einen zufälligen Token
	 * 
	 * @return String mit Token
	 */
	public String generateUserToker() {
		int n = 8;
		char[] pw = new char[n];
		int c = 'A';
		int r1 = 0;
		for (int i = 0; i < n; i++) {
			r1 = (int) (Math.random() * 3);
			switch (r1) {
			case 0:
				c = '0' + (int) (Math.random() * 10);
				break;
			case 1:
				c = 'a' + (int) (Math.random() * 26);
				break;
			case 2:
				c = 'A' + (int) (Math.random() * 26);
				break;
			}
			pw[i] = (char) c;
		}
		return new String(pw);
	}

}
