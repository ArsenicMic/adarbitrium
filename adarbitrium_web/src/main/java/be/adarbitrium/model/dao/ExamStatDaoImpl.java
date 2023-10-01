package be.adarbitrium.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

import be.adarbitrium.model.latin_quizz.ExamStat;

public class ExamStatDaoImpl implements ExamStatDao {

	private DaoFactory daoFactory;
	String dbTable = "adarbitrium_exam_stats";
	
	ExamStatDaoImpl(DaoFactory daoFactory) {
		this.daoFactory = daoFactory;
	}


	@Override
	public boolean addStat(ExamStat stat) {
	       Connection connexion = null;
	        PreparedStatement preparedStatement = null;

	        try {
	            connexion = daoFactory.getConnection();
	            preparedStatement = connexion.prepareStatement
	            		("INSERT INTO " + dbTable
	            		+ "(userId, date, duree, resultat, categoriesBinary) "	
	            		+ "VALUES(?, ?, ?, ?, ?);");
	            preparedStatement.setString(1, String.valueOf(stat.getUserId()));
	            preparedStatement.setString(2, java.sql.Date.valueOf(stat.getDate()).toString());
	            preparedStatement.setString(3, String.valueOf(stat.getDuree()));
	            preparedStatement.setString(4, String.valueOf(stat.getResult()));
	            preparedStatement.setString(5, String.valueOf(stat.getCategoriesDeclBinary()));

	            preparedStatement.executeUpdate();
	        } catch (SQLException e) {
	            e.printStackTrace();
	            return false;
	        }
	        return true;
	}

	@Override
	public ArrayList<ExamStat> getUserStats(int userId) {
		ArrayList<ExamStat> stats = new ArrayList<ExamStat>();
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
            	int duree = resultat.getInt("duree");
            	int result = resultat.getInt("resultat");
            	int binary = resultat.getInt("categoriesBinary");
                ExamStat stat = new ExamStat(userId, date, duree, result, binary);
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
