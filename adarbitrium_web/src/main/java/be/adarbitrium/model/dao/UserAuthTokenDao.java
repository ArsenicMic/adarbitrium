package be.adarbitrium.model.dao;

import be.adarbitrium.model.UserAuthToken;

public interface UserAuthTokenDao {
		
	public boolean add(UserAuthToken userAuthToken);
	public int fetchUserId(UserAuthToken userAuthToken);
	public boolean delete(UserAuthToken userAuthToken);

}
