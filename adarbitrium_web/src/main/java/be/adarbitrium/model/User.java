package be.adarbitrium.model;

import be.adarbitrium.model.dao.DaoFactory;
import be.adarbitrium.model.dao.UserAuthTokenDao;
import be.adarbitrium.model.dao.UserDao;

public class User {
    private int mUserId;
    private String mUsername;
    private String mPassword;
    private String mName;
    private String mFirstName;
    private boolean mIsTeacher;
    private int mTeacherId;
    private int mClasseId;
    private int[] mHiScores; //nom, pron, adj, verbes
    private boolean mIsRegistered;
    
    public User() {};
    
    public User(int userId) {
        DaoFactory daoFactory = DaoFactory.getInstance();
        UserDao ud = daoFactory.getUserDao();
        User registeredUser = ud.fetchFromId(userId);
        if (registeredUser == null) mIsRegistered = false;
        else {
        	mIsRegistered = true;
        	mUserId = registeredUser.getUserId();
        	mUsername = registeredUser.getUsername();
            mPassword = registeredUser.getPassword();
            mName = registeredUser.getName();
            mFirstName = registeredUser.getFirstName();
            mIsTeacher = registeredUser.isTeacher();
            mHiScores = registeredUser.getHiScores();
    	    mClasseId = registeredUser.getClasseId();
    	    mTeacherId = registeredUser.getTeacherId();
        }
    }
    
    public User(UserAuthToken uat) {
    	DaoFactory daoFactory = DaoFactory.getInstance();
    	
    	//retrieve user id from auth tokens db
    	UserAuthTokenDao uatd = daoFactory.getUserAuthTokenDao();
    	int registeredUserId = uatd.fetchUserId(uat);
    	
    	if (registeredUserId == -1)	mIsRegistered = false;
    	
    	else {
	    	//retrieve registered user info
	        UserDao ud = daoFactory.getUserDao();
	        User registeredUser = ud.fetchFromId(registeredUserId);
	        if (registeredUser == null) mIsRegistered = false;
	        else {
	        	mIsRegistered = true;
	        	mUserId = registeredUser.getUserId();
	        	mUsername = registeredUser.getUsername();
	            mPassword = registeredUser.getPassword();
	            mName = registeredUser.getName();
	            mFirstName = registeredUser.getFirstName();
	            mIsTeacher = registeredUser.isTeacher();
	            mHiScores = registeredUser.getHiScores();
	    	    mClasseId = registeredUser.getClasseId();
	    	    mTeacherId = registeredUser.getTeacherId();
	        }
    	}    	
    }
    
    public User(String username, String password) {
        mUsername = username;
        mPassword = password;

        DaoFactory daoFactory = DaoFactory.getInstance();
        UserDao ud = daoFactory.getUserDao();
        User registeredUser = ud.login(username, password);
        if (registeredUser == null) mIsRegistered = false;
        else {
        	mIsRegistered = true;
        	mUserId = registeredUser.getUserId();
        	mUsername = registeredUser.getUsername();
            mPassword = registeredUser.getPassword();
            mName = registeredUser.getName();
            mFirstName = registeredUser.getFirstName();
            mIsTeacher = registeredUser.isTeacher();
            mHiScores = registeredUser.getHiScores();
    	    mClasseId = registeredUser.getClasseId();
    	    mTeacherId = registeredUser.getTeacherId();
        }
    }
    
    public User(String username, String password, String name, String firstName, boolean isTeacher, int teacherId, int classeId) {
    	mUsername = username;
        mPassword = password;
        mName = name;
        mFirstName = firstName;
        mIsTeacher = isTeacher;
        mHiScores = new int[]{0, 0, 0, 0};
    	if (!isTeacher) {
	        mClasseId = classeId;
	        mTeacherId = teacherId;
    	}
    	else {
            mClasseId = -1;
            mTeacherId = -1;
    	}
    }
    
    public User(int id, String username, String password, String name, String firstName, boolean isTeacher, int teacherId, int classeId, int[] hiScores) {
    	mUserId = id;
    	mUsername = username;
        mPassword = password;
        mName = name;
        mFirstName = firstName;
        mIsTeacher = isTeacher;
        mHiScores = hiScores;
	    mClasseId = classeId;
	    mTeacherId = teacherId;
    }

    
    //public methods
    
    public boolean isRegistered() {
    	return mIsRegistered;
    }
    
    //Getters and setters


    public String getUsername() {
        return mUsername;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getFirstName() {
        return mFirstName;
    }

    public int getClasseId() {
        return mClasseId;
    }

    public int[] getHiScores() {
        return mHiScores;
    }

    public void setHiScores(int index, int hiScore) {
        mHiScores[index] = hiScore;
    }

    public int getUserId() {
        return mUserId;
    }

    public void setUserID(int userId) {
        this.mUserId = userId;
    }

    public String getPassword() {
        return mPassword;
    }

	public boolean isTeacher() {
		return mIsTeacher;
	}

	public void setIsTeacher(boolean mIsTeacher) {
		this.mIsTeacher = mIsTeacher;
	}

	public int getTeacherId() {
		return mTeacherId;
	}

	public void setTeacherId(int mTeacherId) {
		this.mTeacherId = mTeacherId;
	}
	
    @Override
    public String toString(){
        return mUsername + " (" + mFirstName + " " + mName + ")" + mIsRegistered;
    }
}