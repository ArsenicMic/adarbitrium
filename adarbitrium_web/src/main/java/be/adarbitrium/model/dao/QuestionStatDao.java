package be.adarbitrium.model.dao;

import java.time.LocalDate;
import java.util.ArrayList;

import be.adarbitrium.model.latin_quizz.QuestionStat;

public interface QuestionStatDao {
	public boolean addStat(QuestionStat stat);
	public ArrayList<QuestionStat> getUserStats(int userId);
	public boolean delete(LocalDate date);
}
