package cn.pacificy.service;

import cn.pacificy.dao.UserDao;
import cn.pacificy.domain.User;

public class UserService {
	UserDao dao = new UserDao();
	public boolean checkUsername(String username) {

		User user = dao.findUserByUsername(username);

		if(user == null) {
			return true;
		}
		
		return false;
		
		
	}

	public boolean addUser(String username, String password) {
		return dao.addUser(username, password);
	}

	public boolean login(String username, String password) {
		return dao.login(username,password);
	}
}
