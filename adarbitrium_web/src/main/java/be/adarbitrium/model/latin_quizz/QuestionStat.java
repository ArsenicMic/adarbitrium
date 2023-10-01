package be.adarbitrium.model.latin_quizz;

import java.time.LocalDate;
import java.time.LocalDateTime;

import be.adarbitrium.model.dao.DaoFactory;
import be.adarbitrium.model.dao.QuestionStatDao;
import be.adarbitrium.model.latin_toolbox.Mot;
import be.adarbitrium.model.latin_toolbox.Nom;

public class QuestionStat {
	private Mot.Mot_type mMotType;
	private int mCategorie;
	private int mTimeUsed;
	private int mFailedAttempts;
	private LocalDate mDate;
	private int mUserId;
	private String mTerminaison;
	
	/**
	 * @return the terminaison
	 */
	public String getTerminaison() {
		return mTerminaison;
	}

	/**
	 * @param terminaison the terminaison to set
	 */
	public void setTerminaison(String terminaison) {
		mTerminaison = terminaison;
	}

	public QuestionStat() {
		mDate = LocalDate.now();
	}
	
	public QuestionStat(Mot.Mot_type type, int categorie, int timeUsed, String terminaison, int failedAttempts, int userId) {
		mMotType = type;
		mCategorie = categorie;
		mTimeUsed = timeUsed;
		mFailedAttempts = failedAttempts;
		mDate = LocalDate.now();
		mUserId = userId;
		mTerminaison = terminaison;
	}

	public QuestionStat(LocalDate date, Mot.Mot_type type, int categorie, int timeUsed, String terminaison, int failedAttempts, int userId) {
		mMotType = type;
		mCategorie = categorie;
		mTimeUsed = timeUsed;
		mFailedAttempts = failedAttempts;
		mDate = date;
		mUserId = userId;
		mTerminaison = terminaison;
	}
	
	public boolean writeStatToDb() {
		DaoFactory factory = DaoFactory.getInstance();
		QuestionStatDao dao= factory.getQuestionStatDao();
		return dao.addStat(this);
	}

	/**
	 * @return the failedAttempts
	 */
	public int getFailedAttempts() {
		return mFailedAttempts;
	}

	/**
	 * @param failedAttempts the failedAttempts to set
	 */
	public void setFailedAttempts(int failedAttempts) {
		mFailedAttempts = failedAttempts;
	}

	/**
	 * @return the dateTime
	 */
	public LocalDate getDate() {
		return mDate;
	}

	/**
	 * @param dateTime the dateTime to set
	 */
	public void setDateTime(LocalDate date) {
		this.mDate = date;
	}

	/**
	 * @return the motType
	 */
	public Mot.Mot_type getMotType() {
		return mMotType;
	}

	/**
	 * @param motType the motType to set
	 */
	public void setMotType(Mot.Mot_type motType) {
		mMotType = motType;
	}

	/**
	 * @return the categorie
	 */
	public int getCategorie() {
		return mCategorie;
	}

	/**
	 * @param categorie the categorie to set
	 */
	public void setCategorie(int categorie) {
		mCategorie = categorie;
	}

	/**
	 * @return the timeUsed
	 */
	public int getTimeUsed() {
		return mTimeUsed;
	}

	/**
	 * @param timeUsed the timeUsed to set
	 */
	public void setTimeUsed(int timeUsed) {
		mTimeUsed = timeUsed;
	}

	@Override
	public String toString() {
		return "" + mMotType + " (" + mCategorie + "), used " + mTimeUsed + "s, failed: " + mFailedAttempts + ", terminaison: " + mTerminaison + " (on " + mDate + ").";
	}

	public int getUserId() {
		return mUserId;
	}

	public void setUserId(int userId) {
		mUserId = userId;
	}
}
