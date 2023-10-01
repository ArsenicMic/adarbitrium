package be.adarbitrium.model;


import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import be.adarbitrium.model.dao.DaoFactory;
import be.adarbitrium.model.dao.UserAuthTokenDao;
 
public class UserAuthToken implements java.io.Serializable {

	
	private static final long serialVersionUID = 1L;
	private int userId;
    private String selector;
    private String validator;
    private String hashedValidator;

	public UserAuthToken(User user) {
    	RandomString randomString = new RandomString();
    	this.selector = randomString.nextString();
    	this.validator = randomString.nextString();
    	this.userId = user.getUserId();
 
    	hashValidator();
    	
        DaoFactory daoFactory = DaoFactory.getInstance();
        UserAuthTokenDao uatd = daoFactory.getUserAuthTokenDao();
        boolean result = uatd.add(this);
    }
    
    public UserAuthToken(String selector, String validator) {
    	this.selector = selector;
    	this.validator = validator;
    	
    	hashValidator();	
    }
    
    public boolean destroy() {
        DaoFactory daoFactory = DaoFactory.getInstance();
        UserAuthTokenDao uatd = daoFactory.getUserAuthTokenDao();
    	return uatd.delete(this);
    }
    
    private void hashValidator() {
    	MessageDigest digest;
		try {
			digest = MessageDigest.getInstance("SHA3-256");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			hashedValidator = null;
			return;
		}
	    final byte[] hashbytes = digest.digest(validator.getBytes(StandardCharsets.UTF_8));
	    String sha3Hex = bytesToHex(hashbytes);	    	
	    hashedValidator = sha3Hex;
    }
    
    private static String bytesToHex(byte[] hash) {
        StringBuilder hexString = new StringBuilder(2 * hash.length);
        for (int i = 0; i < hash.length; i++) {
            String hex = Integer.toHexString(0xff & hash[i]);
            if(hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }
    

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getSelector() {
		return selector;
	}

	public String getValidator() {
		return validator;
	}
	
	public String getHashedValidator() {
		return hashedValidator;
	}
 
}