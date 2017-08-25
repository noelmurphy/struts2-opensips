package net.codejava.framework.dao;

import java.util.List;

import org.hibernate.Session;

import net.codejava.framework.model.User;

public interface UserDAO 
{

	List<User> listUser();
	User getUserByEmail(String email);
	void generatePassword(User user, String plainTextPassword);
	void create(String email, String plainTextPassword);
	void register(String email, String password, String roleName);
	void registrate(Session session, String email, String plainTextPassword, String roleName);
	void login(User user);
	void deleteUser(Long userId);
}
