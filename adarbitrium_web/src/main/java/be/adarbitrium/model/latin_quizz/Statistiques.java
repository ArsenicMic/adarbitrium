package be.adarbitrium.model.latin_quizz;

import java.time.LocalDate;
import java.util.ArrayList;

import be.adarbitrium.model.User;
import be.adarbitrium.model.dao.DaoFactory;
import be.adarbitrium.model.dao.ExamStatDao;
import be.adarbitrium.model.dao.QuestionStatDao;
import be.adarbitrium.model.latin_toolbox.Mot;

public class Statistiques {
	private final ArrayList<ExamStat> mExamStats;
	private final ArrayList<QuestionStat> mQuestionStats;
	
	public Statistiques(User user) {
		mExamStats = retrieveUserExamStats(user);
		mQuestionStats = retrieveUserQuestionStats(user);
	}
	
	/* Fetching stats from database */
	
	private ArrayList<ExamStat> retrieveUserExamStats(User user){
		DaoFactory daoFactory = DaoFactory.getInstance();
		ExamStatDao esd = daoFactory.getExamStatDao();
		return esd.getUserStats(user.getUserId());
	}
	
	private ArrayList<QuestionStat> retrieveUserQuestionStats(User user){
		DaoFactory daoFactory = DaoFactory.getInstance();
		QuestionStatDao esd = daoFactory.getQuestionStatDao();
		return esd.getUserStats(user.getUserId());
	}
	
	/* Getters for ExamStats and sublists */
	
	public ArrayList<ExamStat> getExamStats() {
		return mExamStats;
	}
	
	public ArrayList<ExamStat> getExamStats(boolean[] categoriesDecl){
		ArrayList<ExamStat> result = new ArrayList<ExamStat>();
		for (ExamStat stat : mExamStats) {
			if (stat.getCategoriesDecl() == categoriesDecl) result.add(stat);
		}
		return result;
	}

	public ArrayList<ExamStat> getExamStats(boolean[] categoriesDecl, LocalDate date){
		ArrayList<ExamStat> result = new ArrayList<ExamStat>();
		for (ExamStat stat : mExamStats) {
			if (stat.getCategoriesDecl() == categoriesDecl && stat.getDate() == date) result.add(stat);
		}
		return result;
	}
	
	/* getting the list of dates of an ExamStat ArrayList */

	public ArrayList<LocalDate> getExamDates(ArrayList<ExamStat> stats){
		ArrayList<LocalDate> dates = new ArrayList<LocalDate>();
		for (ExamStat stat : stats) {
			if (!dates.contains(stat.getDate())) dates.add(stat.getDate());
		}
		return dates;
	}
	
	/* getting the best score for given exam type */
	
	public int getBestResult(boolean[] categoriesDecl) {
		ArrayList<ExamStat> stats = getExamStats(categoriesDecl);
		int best = 0;
		for (ExamStat stat : stats) {
			if (stat.getResult() > best) best = stat.getResult();
		}
		return best;
	}
	
	/* Getters for QuestionStats and sublists */
	
	public ArrayList<QuestionStat> getQuestionStats() {
		return mQuestionStats;
	}

	public ArrayList<QuestionStat> getQuestionStats(Mot.Mot_type type){
		ArrayList<QuestionStat> result = new ArrayList<QuestionStat>();
		for (QuestionStat stat : mQuestionStats) {
			if (stat.getMotType() == type) result.add(stat);
		}
		return result;
	}

	public ArrayList<QuestionStat> getQuestionStats(Mot.Mot_type type, String terminaison){
		ArrayList<QuestionStat> result = new ArrayList<QuestionStat>();
		for (QuestionStat stat : mQuestionStats) {
			if (stat.getMotType() == type && stat.getTerminaison() == terminaison) result.add(stat);
		}
		return result;
	}
	
	public ArrayList<QuestionStat> getQuestionStats(String terminaison){
		ArrayList<QuestionStat> result = new ArrayList<QuestionStat>();
		for (QuestionStat stat : mQuestionStats) {
			if (stat.getTerminaison() == terminaison) result.add(stat);
		}
		return result;
	}

}
