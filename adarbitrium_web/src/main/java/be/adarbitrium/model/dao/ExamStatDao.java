package be.adarbitrium.model.dao;

import java.time.LocalDate;
import java.util.ArrayList;

import be.adarbitrium.model.latin_quizz.ExamStat;


public interface ExamStatDao {
	public boolean addStat(ExamStat stat);
	public ArrayList<ExamStat> getUserStats(int userId);
	public boolean delete(LocalDate date);
}
