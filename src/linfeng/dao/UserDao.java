package linfeng.dao;

import java.util.List;

import linfeng.beans.BeanException;
import linfeng.beans.User;

public interface UserDao {
	List<User> getUsers() throws BeanException, DaoException;
	void addUser(User user) throws DaoException;
}
