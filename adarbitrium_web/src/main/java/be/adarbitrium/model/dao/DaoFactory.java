package be.adarbitrium.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DaoFactory {
    private String url;
    private String username;
    private String password;

    DaoFactory(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    public static DaoFactory getInstance() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {

        }

        //DaoFactory instance = new DaoFactory("jdbc:mysql://localhost:3306/intem1517491", "arsenic", "micmic42");
        //DaoFactory instance = new DaoFactory("jdbc:mysql://localhost:3306/exercice_db", "exercice_website", "vg9+t#=0%YrY");
        DaoFactory instance = new DaoFactory("jdbc:mysql://exercices-latin.be:3306/exercice_db", "exercice_website", "vg9+t#=0%YrY");
        return instance;
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }

    // Récupération du Dao
    public UserDao getUserDao() {
        return new UserDaoImpl(this);
    }
    
    public UserAuthTokenDao getUserAuthTokenDao() {
        return new UserAuthTokenDaoImpl(this);
    }
    
    public VocDao getVocDao() {
    	return new VocDaoImpl(this);
    }

    public QuestionStatDao getQuestionStatDao() {
    	return new QuestionStatDaoImpl(this);
    }
    
    public ExamStatDao getExamStatDao() {
    	return new ExamStatDaoImpl(this);
    }
}