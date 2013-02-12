package com.wub.handlers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import com.wub.db.Administrators;
import com.wub.db.AppraisalBasic;
import com.wub.db.AppraisalConf;
import com.wub.db.AppraisalMain;
import com.wub.db.AppraisalResults;
import com.wub.db.AppraisalResultsDAO;
import com.wub.db.AppraisalStatus;
import com.wub.db.AppraisalStatusDAO;
import com.wub.db.Subjects;
import com.wub.db.TextElements;
import com.wub.struts.form.AppraisalForm;
import com.wub.struts.form.AppraisalVoteElementForm;
import com.wub.struts.form.UserAppraisalInfoForm;

/**
 * Dient der Verwaltung des User-Workflows (dem eigentlichen
 * Ausfüllen der Beurteilungen).
 * @author patric
 *
 */
@SuppressWarnings("unchecked")
public class UserHandler {

	private static UserHandler instance = null;
	public static final String USERDATA = "userData";
	
	
	/**
	 * Prüft die Logondaten eines Benutzers
	 * @param email die Emailadresse des Benutzers
	 * @param token das Token des Benutzers
	 * @return true = Daten korrekt, darf sich z.B. anmelden, false = Daten inkorrekt
	 */
	public boolean checkUserLogon(String email, String token) {
		// zuerst muss die UserId herausgefunden werden
		Integer userId =
			AppraisalHandler.getInstance().getUserIdByEmail(email);
		if (userId != null) {
			/* gültige Id wurde gefunden (pro Email kann es gemäss
			 * ablauf sowieso nur einen User/eine ID geben.
			 * Nun prüfen ob zu diesm User und diesem Token ein
			 * Status gefunden werden kann.
			 */
			AppraisalStatus appStatus = getAppraisalStatusByUserIdAndToken(userId, token);
			// ein Eintrag genügt schon, wir holen nun die classId
			if (appStatus != null && appStatus.getClassId() != null) {					
				/* wir interessieren uns für das gültigkeitsdatum, das heutige
				 * Datum muss innerhalb des Gültigkeitszeitraumes liegen, sonst
				 * schlägt der Login fehl.
				 */
				Integer classId = appStatus.getClassId();
				AppraisalMain appMain = AppraisalHandler.getInstance().getClassById(classId);
				// Gültigkeitszeitraum prüfen
				if (checkIfAppraisalIsValid(appMain)) {
					return true;
				}
			}
		}
		return false;
	}
	
	
	/**
	 * Hole das erste AppraisalStatusElement für eine bestimmte User-ID und ein Token.
	 * @param userId
	 * @param token
	 * @return
	 */
	public AppraisalStatus getAppraisalStatusByUserIdAndToken(Integer userId, String token) {
		AppraisalStatusDAO appStatDAO = new AppraisalStatusDAO();
		List statusList = appStatDAO.findByUserIdAndToken(userId, token);
		if (statusList != null && statusList.size() > 0) {
			return((AppraisalStatus)statusList.get(0));
		}
		return null;
	}
	
	
	/**
	 * Holt eine Liste mit Beurteilungs-Stati pro User
	 * @param userId
	 * @return
	 */
	public List getAppraisalStatusListByUserId(int userId) {
		AppraisalStatusDAO appStatusDAO = new AppraisalStatusDAO();
		return appStatusDAO.findByUserId(userId);
	}
	
	
	/**
	 * Prüft ob eine Beurteilung noch im Gültigkeitszeitraum ist
	 * @param appMain
	 * @return true = ja, noch gültig, false = nein, ungültig
	 */
	public boolean checkIfAppraisalIsValid(AppraisalMain appMain) {
		if (appMain != null && appMain.getStartDate() != null && appMain.getEndDate() != null) {
			Date currentDate = new Date();
			if (currentDate.getTime() >= appMain.getStartDate().getTime() &&
					currentDate.getTime() <= appMain.getEndDate().getTime()) {
				// Datumsbereich gültig
				return true;
			}
		}
		return false;
	}
	
	
	/**
	 * Holt eine Liste mit gültigen Beurteilungen für einen bestimmten Benutzer
	 * @param userId
	 * @return
	 */
	public List getValidAppraisals(int userId) {
		List statusList = getAppraisalStatusListByUserId(userId);
		List appraisalList = new ArrayList();
		if (statusList != null) {
			for (Object statusObj : statusList) {
				AppraisalStatus appStatus = (AppraisalStatus)statusObj;
				// Daten zu diesem Status herauslesen
				Integer classId = appStatus.getClassId();
				if (classId != null) {
					AppraisalMain appMain =
						AppraisalHandler.getInstance().getClassById(classId);
					// checken ob gültig (Zeitraum und Versendet)
					if (appMain.getSent() != null &&
						appMain.getSent() == 1 &&
						checkIfAppraisalIsValid(appMain)) {
						// gültig: für Liste vorbereiten
						String teacher =
							AdminUserHandler.getInstance().getAdminById(appMain.getAdminId()).getName();
						String info = appMain.getName() + " - " + teacher;
						UserAppraisalInfoForm infoForm = new UserAppraisalInfoForm();
						// Daten zu Form hinzufügen
						infoForm.setAppraisalInfo(info);
						infoForm.setClassId(appMain.getClassId());
						infoForm.setStatusId(appStatus.getStatusId());
						// Form zur Liste hinzufügen
						appraisalList.add(infoForm);
					}
				}
			}
		}
		return appraisalList;
	}
	
	
	/**
	 * Liefert eine Liste mit allen Stati zurück, welche dem User zugeordnet sind
	 * @param userId
	 * @return
	 */
	public List getStatusListByUserId(int userId) {
		AppraisalStatusDAO appStatusDAO = new AppraisalStatusDAO();
		return appStatusDAO.findByUserId(userId);
	}
	
	
	/**
	 * Liefert das Formular für eine Beurteilung zurück anhand einer StatusId
	 * @param statusId die Id die verwendet wird um die Beurteilungsdaten herauszufinden und aufzubauen
	 * @return ein Objekt vom Typ AppraisalForm, welches eine Beurteilung darstellt welche ausgefüllt werden kann
	 */
	public AppraisalForm getAppraisalFrom(int statusId) {
		AppraisalForm appraisalForm = new AppraisalForm();
		// Daten vorbereiten und abspeichern
		AppraisalHandler appHandler = AppraisalHandler.getInstance();
		
		// anhand von statusId die classId herausfinden
		AppraisalStatus appStatus = appHandler.getStatusById(statusId);
		
		AppraisalMain appMain =
			appHandler.getClassById(appStatus.getClassId());
		Subjects subject =
			appHandler.getSubjectById(appMain.getSubjectId());
		Administrators teacher =
			AdminUserHandler.getInstance().getAdminById(appMain.getAdminId());
		AppraisalBasic appBasic =
			appHandler.getAppraisalBasicById(appMain.getAppraisalId());
		// Wurde anders gelöst:
//		VoteStyles voteStyle =
//			VoteStyleHandler.getInstance().getVoteStyleById(appBasic.getVoteStyle());
		
		// Formulardaten abfüllen
		appraisalForm.setStatusId(statusId);
		appraisalForm.setDescription(appMain.getName());
		appraisalForm.setStartDate(appMain.getStartDateString());
		appraisalForm.setEndDate(appMain.getEndDateString());
		appraisalForm.setSubject(subject.getName());
		appraisalForm.setSubjectYear(appMain.getSubjectYearString());
		appraisalForm.setTeacher(teacher.getName());
		appraisalForm.setVoteStyleId(appBasic.getVoteStyle());
		
		// Listen-Elemente
		List voteElementList = new ArrayList(); // das Ziel für die Elemente
		List<AppraisalConf> appConfList =
			appHandler.getAppraisalBasicConfListById(appMain.getAppraisalId());
		if (appConfList != null) {
			// Liste nach Reihenfolge sortieren
			Collections.sort(appConfList);
			for (AppraisalConf appraisalConf : appConfList) {
				// Element vorbereiten
				AppraisalVoteElementForm voteElement =
					new AppraisalVoteElementForm();
				
				TextElements text =
					appHandler.getTextElementById(appraisalConf.getTextId());
				/* text und textId setzen (Kommentar und Vote
				 * werden dann vom User ausgefüllt.
				 */
				voteElement.setTextId(appraisalConf.getTextId());
				voteElement.setTitle(text.getTitle());
				voteElement.setText(text.getText());
				
				// Element in Liste speichern
				voteElementList.add(voteElement);
			}
		}
		
		appraisalForm.setVoteElementList(voteElementList);
		
		return appraisalForm;
	}
	
	
	/**
	 * Speichert ein einzelnes Resultat in der Datenbank
	 * @param result Das Resultat das gespeichert wird
	 */
	private void saveResult(AppraisalResults result) {
		AppraisalResultsDAO appResultDAO =
			new AppraisalResultsDAO();
		appResultDAO.save(result);
		appResultDAO.getSession().flush();
	}
	
	
	/**
	 * Anonymisiert einen Status-Eintrag, indem die User-ID durch einen Dummy-
	 * Eintrag ersetzt wird.
	 * @param statusId Die ID des Status
	 * @param userId Der UserID der im Status eingetragen sein muss (für check)
	 * @return true = wurde anonymisiert, false = nicht anonymisiert
	 */
	private boolean anonymizeStatus(int statusId, int userId) {
		AppraisalStatus appStatus =
			AppraisalHandler.getInstance().getStatusById(statusId);
		if (appStatus != null && (int)appStatus.getUserId() == userId) {
			appStatus.setUserId(new Integer(-1)); // anonymisieren
			appStatus.setToken(""); // token wird nicht mehr gebraucht
			// geänderter Status speichern
			AppraisalHandler.getInstance().editClassStatus(appStatus);
			// Erfolgreich
			return true;
		}
		return false;
	}
	
	
	
	/**
	 * Speichert eine ausgefüllte Beurteilung in der Datenbank ab. Veranlasst auch, das
	 * der Status-Eintrag anonymisiert wird und somit die Beurteilung nicht mehr einem User
	 * zugeordnet werden kann.
	 * @param statusId Die statusID, wird verwendet um die Verknüpfung mit der Beurteilung herzustellen etc.
	 * @param voteElementList eine Liste mit den Beurteilungen 
	 * @param userId die ID des Users, welche die Beurteilung ausgefüllt hat (wird für eine Sicherheitsprüfung benötigt)
	 * @return true = Urteilung gespeichert, false = Fehler beim speichern der Beurteilung ist aufgetreten.
	 */
	public boolean saveAppraisal(int statusId, List<AppraisalVoteElementForm> voteElementList, int userId) {
		for (AppraisalVoteElementForm voteElement : voteElementList) {
			AppraisalResults appResult = new AppraisalResults();
			// Werte übertragen
			appResult.setTextId(voteElement.getTextId());
			appResult.setStatusId(statusId);
			appResult.setVote((short)(int)(voteElement.getVote()));
			appResult.setComment(voteElement.getComment());
			// Resultat speichern
			saveResult(appResult);
		}
		
		// User anonymisieren (und verhindern, dass er nochmals abstimmt)
		if (anonymizeStatus(statusId, userId)){
			return true;
		}
		
		return false;
	}
	
	
	
	/**
	 * Singleton Pattern
	 * @return
	 */
	public static UserHandler getInstance() {
		if (instance == null) {
			instance = new UserHandler();
		}
		return instance;
	}
	
	
}
