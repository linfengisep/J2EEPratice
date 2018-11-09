package linfeng.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import linfeng.beans.BeanException;
import linfeng.beans.User;

public class UserDaoImpl implements UserDao {
	private DaoFactory daoFactory;
	
	public UserDaoImpl(DaoFactory daoFactory) {
		this.daoFactory = daoFactory;
	}

	@Override
	public List<User> getUsers() throws DaoException,BeanException {
		//Potential bean exception;
		List<User> listUsers = new ArrayList<>();
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		try {
			connection = daoFactory.getInstance().getConnection();
			statement = connection.createStatement();
			resultSet = statement.executeQuery("SELECT first_name,last_name FROM name");
			while(resultSet.next()) {
				String firstName = resultSet.getString("first_name");
				String lastName = resultSet.getString("last_name");
				User lee = new User();
				//Potential bean exception;
				lee.setFirstName(firstName);
				lee.setLastName(lastName);
				listUsers.add(lee);
			}
			
		}catch(SQLException e) {	
			throw new DaoException("get user list exception");
		}catch(BeanException e2) {
			throw new BeanException("bean user exception");
		}
		finally {
			try {
				if(connection !=null) {
					connection.commit();
					connection.close();
				}
			}catch(SQLException e) {
				throw new DaoException("can not close the connection");
			}
		}
		return listUsers;
	}

	@Override
	public void addUser(User user) throws DaoException{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = daoFactory.getInstance().getConnection();
			preparedStatement = connection.prepareStatement("insert name(first_name,last_name) values(?,?)");
			preparedStatement.setString(1, user.getFirstName());
			preparedStatement.setString(2, user.getLastName());
			preparedStatement.executeUpdate();
			connection.commit();
		}catch(SQLException e) {
			try {
				if(connection !=null) {
					connection.rollback();
				}
			}catch(SQLException e2) {}
			throw new DaoException("dao exception in add user");
		}finally {
			try {
				if(connection !=null) {
					connection.commit();
					connection.close();
				}
			}catch(SQLException e) {
			}
		}
	}
}
