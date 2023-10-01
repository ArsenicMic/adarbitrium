package be.adarbitrium.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import be.adarbitrium.model.User;
import be.adarbitrium.model.UserAuthToken;

public class UserAuthTokenDaoImpl implements UserAuthTokenDao {
	private DaoFactory daoFactory;
	UserAuthTokenDaoImpl(DaoFactory daoFactory){
		this.daoFactory = daoFactory;
	}

	@Override
	public boolean add(UserAuthToken userAuthToken) {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;

        try {
            connexion = daoFactory.getConnection();
            preparedStatement = connexion.prepareStatement
            		("INSERT INTO adarbitrium_users_auth"
            		+ "(selector, validator, user_id) "		
            		+ "VALUES(?, ?, ?);");
            preparedStatement.setString(1, userAuthToken.getSelector());
            preparedStatement.setString(2, userAuthToken.getHashedValidator());
            preparedStatement.setInt(3, userAuthToken.getUserId());

            preparedStatement.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
	}

	@Override
	public int fetchUserId(UserAuthToken userAuthToken) {
	   	int registeredUserId = -1;
        Connection connexion = null;
        Statement statement = null;
        ResultSet resultat = null;

        try {
            connexion = daoFactory.getConnection();
            statement = connexion.createStatement();
            resultat = statement.executeQuery(
            		"SELECT * FROM adarbitrium_users_auth WHERE selector = '"+ userAuthToken.getSelector() +"';");

            while (resultat.next()) {
                int userId = resultat.getInt("user_id");
                String storedValidator = resultat.getString("validator");
                if (storedValidator.equals(userAuthToken.getHashedValidator())) {
                	registeredUserId = userId;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return registeredUserId;

	}

	@Override
	public boolean delete(UserAuthToken userAuthToken) {
        Connection connexion = null;
        Statement statement = null;
        int deletedRows = 0;

        try {
            connexion = daoFactory.getConnection();
            statement = connexion.createStatement();
            deletedRows = statement.executeUpdate(
            		"DELETE FROM adarbitrium_users_auth WHERE selector = '"+ userAuthToken.getSelector() +"';");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return (deletedRows > 0);
	}

}
