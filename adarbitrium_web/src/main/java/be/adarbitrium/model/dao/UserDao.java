package be.adarbitrium.model.dao;

import be.adarbitrium.model.User;

public interface UserDao {
	
	public boolean register(User user);
	public boolean update(User user);
	public User login(String username, String password);
	public User fetchFromId(int userId);
	public int[] getStudents(User user);
	public boolean delete(User user);

}
