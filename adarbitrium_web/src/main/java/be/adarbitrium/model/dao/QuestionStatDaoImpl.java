package be.adarbitrium.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

import be.adarbitrium.model.latin_quizz.QuestionStat;
import be.adarbitrium.model.latin_toolbox.Mot;

public class QuestionStatDaoImpl implements QuestionStatDao {
	private DaoFactory daoFactory;
	String dbTable = "adarbitrium_question_stats";
	
	QuestionStatDaoImpl(DaoFactory daoFactory) {
		this.daoFactory = daoFactory;
	}

	@Override
	public boolean addStat(QuestionStat stat) {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;

        try {
            connexion = daoFactory.getConnection();
            preparedStatement = connexion.prepareStatement
            		("INSERT INTO " + dbTable
            		+ "(userId, date, motType, motCategorie, timeUsed, terminaison, failedAttempts) "	
            		+ "VALUES(?, ?, ?, ?, ?, ?, ?);");
            preparedStatement.setString(1, String.valueOf(stat.getUserId()));
            preparedStatement.setString(2, java.sql.Date.valueOf(stat.getDate()).toString());
            preparedStatement.setString(3, stat.getMotType().toString());
            preparedStatement.setString(4, String.valueOf(stat.getCategorie()));
            preparedStatement.setString(5, String.valueOf(stat.getTimeUsed()));
            preparedStatement.setString(6, stat.getTerminaison());
            preparedStatement.setString(7, String.valueOf(stat.getFailedAttempts()));

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
	}

	@Override
	public ArrayList<QuestionStat> getUserStats(int userId) {
		ArrayList<QuestionStat> stats = new ArrayList<QuestionStat>();
        Connection connexion = null;
        Statement statement = null;
        ResultSet resultat = null;

        try {
            connexion = daoFactory.getConnection();
            statement = connexion.createStatement();
            resultat = statement.executeQuery(
            		"SELECT * FROM " + dbTable + " WHERE userId = '"+ userId +"';");

            while (resultat.next()) {
            	LocalDate date = resultat.getDate("date").toLocalDate();
            	Mot.Mot_type type = Mot.Mot_type.valueOf(resultat.getString("motType"));
            	int cat = resultat.getInt("motCategorie");
            	int timeUsed = resultat.getInt("timeUsed");
            	int failedAttempts = resultat.getInt("failedAttempts");
                String terminaison = resultat.getString("terminaison");
                QuestionStat stat = new QuestionStat(date, type, cat, timeUsed, terminaison, failedAttempts, userId);
                stats.add(stat);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return stats;
	}

	@Override
	public boolean delete(LocalDate date) {
        Connection connexion = null;
        Statement statement = null;

        try {
        	connexion = daoFactory.getConnection();
            statement = connexion.createStatement();
            statement.executeQuery("DELETE FROM " + dbTable + " WHERE date=" + java.sql.Date.valueOf(date).toString());
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
	}

}
