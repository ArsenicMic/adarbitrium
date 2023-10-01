package be.adarbitrium.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import be.adarbitrium.model.User;

public class UserDaoImpl implements UserDao {
	private DaoFactory daoFactory;
	UserDaoImpl(DaoFactory daoFactory) {
		this.daoFactory = daoFactory;
	}

    @Override
    public boolean register(User user) {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;

        try {
            connexion = daoFactory.getConnection();
            preparedStatement = connexion.prepareStatement
            		("INSERT INTO adarbitrium_users"
            		+ "(username, password, name, fistname, classeId, isTeacher, teacherId, "
            		+ "hiscore_noms, hiscore_pronoms, hiscore_adjectifs) "		
            		+ "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);");
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getName());
            preparedStatement.setString(4, user.getFirstName());
            preparedStatement.setString(5, String.valueOf(user.getClasseId()));
            preparedStatement.setString(6, String.valueOf(user.isTeacher()));
            preparedStatement.setString(7, String.valueOf(user.getTeacherId()));           
            preparedStatement.setString(8, String.valueOf(user.getHiScores()[0]));
            preparedStatement.setString(9, String.valueOf(user.getHiScores()[1]));
            preparedStatement.setString(10, String.valueOf(user.getHiScores()[2]));
            preparedStatement.setString(11, String.valueOf(user.getHiScores()[3]));

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;

    }

    @Override
    public User login(String username, String password) {
    	User loggedUser = null;
        Connection connexion = null;
        Statement statement = null;
        ResultSet resultat = null;

        try {
            connexion = daoFactory.getConnection();
            statement = connexion.createStatement();
            resultat = statement.executeQuery(
            		"SELECT * FROM adarbitrium_users WHERE username = '"+ username +"';");

            while (resultat.next()) {
                String registeredPassword = resultat.getString("password");

                if (password.equals(registeredPassword)) {
	                int userId = resultat.getInt("id");
	                String name = resultat.getString("name");
	                String firstname = resultat.getString("firstname");
	                int classeId = resultat.getInt("classeId");
	                boolean isTeacher = resultat.getBoolean("isTeacher");
	                int teacherId = resultat.getInt("teacherId");
	                int[] hiScores = new int[4];
	                hiScores[0] = resultat.getInt("hiscore_noms");
	                hiScores[1] = resultat.getInt("hiscore_pronoms");
	                hiScores[2] = resultat.getInt("hiscore_adjectifs");
	                hiScores[3] = resultat.getInt("hiscore_verbes");
	                
	                loggedUser = new User(userId, username, password, name, firstname, isTeacher, teacherId, classeId, hiScores);            
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return loggedUser;

    } 
    
    @Override
    public User fetchFromId(int userId) {
    	User loggedUser = null;
        Connection connexion = null;
        Statement statement = null;
        ResultSet resultat = null;

        try {
            connexion = daoFactory.getConnection();
            statement = connexion.createStatement();
            resultat = statement.executeQuery(
            		"SELECT * FROM adarbitrium_users WHERE id = '"+ userId +"';");

            while (resultat.next()) {
                String username = resultat.getString("username");
                String password = resultat.getString("password");
                String name = resultat.getString("name");
                String firstname = resultat.getString("firstname");
                int classeId = resultat.getInt("classeId");
                boolean isTeacher = resultat.getBoolean("isTeacher");
                int teacherId = resultat.getInt("teacherId");
                int[] hiScores = new int[4];
                hiScores[0] = resultat.getInt("hiscore_noms");
                hiScores[1] = resultat.getInt("hiscore_pronoms");
                hiScores[2] = resultat.getInt("hiscore_adjectifs");
                hiScores[3] = resultat.getInt("hiscore_verbes");
                loggedUser = new User(userId, username, password, name, firstname, isTeacher, teacherId, classeId, hiScores);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return loggedUser;

    }    

    
    @Override
    public boolean update(User user) {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;

        try {
            connexion = daoFactory.getConnection();
            preparedStatement = connexion.prepareStatement
            		("INSERT INTO adarbitrium_users"
            		+ "(username, password, name, fistname, classeId, isTeacher, teacherId, "
            		+ "hiscore_noms, hiscore_pronoms, hiscore_adjectifs) "		
            		+ "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?);");
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getName());
            preparedStatement.setString(4, user.getFirstName());
            preparedStatement.setString(5, String.valueOf(user.getClasseId()));
            preparedStatement.setString(6, String.valueOf(user.isTeacher()));
            preparedStatement.setString(7, String.valueOf(user.getTeacherId()));           
            preparedStatement.setString(8, String.valueOf(user.getHiScores()[0]));
            preparedStatement.setString(9, String.valueOf(user.getHiScores()[1]));
            preparedStatement.setString(10, String.valueOf(user.getHiScores()[2]));

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;

    }
    
    @Override
    public boolean delete(User user) {
        Connection connexion = null;
        Statement statement = null;

        try {
        	connexion = daoFactory.getConnection();
            statement = connexion.createStatement();
            statement.executeQuery("DELETE FROM adarbitrium_users WHERE username=" + user.getUsername());
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;

    }
    
    @Override
    public int[] getStudents(User user) {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;

        try {
            connexion = daoFactory.getConnection();
            preparedStatement = connexion.prepareStatement
            		("INSERT INTO adarbitrium_users"
            		+ "(username, password, name, fistname, classeId, isTeacher, teacherId, "
            		+ "hiscore_noms, hiscore_pronoms, hiscore_adjectifs) "		
            		+ "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?);");
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getName());
            preparedStatement.setString(4, user.getFirstName());
            preparedStatement.setString(5, String.valueOf(user.getClasseId()));
            preparedStatement.setString(6, String.valueOf(user.isTeacher()));
            preparedStatement.setString(7, String.valueOf(user.getTeacherId()));           
            preparedStatement.setString(8, String.valueOf(user.getHiScores()[0]));
            preparedStatement.setString(9, String.valueOf(user.getHiScores()[1]));
            preparedStatement.setString(10, String.valueOf(user.getHiScores()[2]));

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return new int[0];
        }
        return new int[0];

    }
}
