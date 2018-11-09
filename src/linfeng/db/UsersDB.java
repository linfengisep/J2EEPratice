package linfeng.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import linfeng.beans.BeanException;
import linfeng.beans.User;

public class UsersDB {
	//connecting
	private Connection conn= null;
	private Statement statement = null;
	private ResultSet resultSet = null;
	
	//charging drivers
	public void loadingDatabase() {
		//charging Driver;
				try {
					Class.forName("com.mysql.jdbc.Driver");
				}catch(ClassNotFoundException e) {
				}
				try {
					conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/javaee", "root", "wlf5165804");
				}catch(SQLException e) {}
	}
	
	//closing database resources;
	public void closingDatabase() {
		try {
			if(conn !=null) {
				conn.close();
			}
			if(statement !=null) {
				statement.close();
			}
			if(resultSet !=null) {
				resultSet.close();
			}
			
		}catch(SQLException e) {
			
		}
	}
	
	//get users;
	public List<User> getUsers () throws BeanException{
		List<User> users = new ArrayList<>();
		loadingDatabase();
		String usersListQuery = "SELECT first_name,last_name FROM name";
		try {
			statement = conn.createStatement();
			resultSet = statement.executeQuery(usersListQuery);
			while(resultSet.next()) {
				String firstName = resultSet.getString("first_name");
				String lastName = resultSet.getString("last_name");
				User lee = new User();
				lee.setFirstName(firstName);
				lee.setLastName(lastName);
				users.add(lee);
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		closingDatabase();
		return users;
	}
	//add user
	public void addUser(User user) {
		loadingDatabase();
		try {
			PreparedStatement prepareStatement = conn.prepareStatement("insert name(first_name,last_name) values(?,?)");
			prepareStatement.setString(1, user.getFirstName());
			prepareStatement.setString(2,user.getLastName());
			prepareStatement.executeUpdate();
			
		}catch(SQLException e) {
			
		}
	}
	
	//delete a user
	public void deleteUser(User user) {
		
	}
}
