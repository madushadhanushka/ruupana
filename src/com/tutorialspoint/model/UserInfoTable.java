package com.tutorialspoint.model;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserInfoTable {
	private final boolean DEBUG_ALL = true;
	Connection mConnection;

	public UserInfoTable(Connection connection) {
		if (DEBUG_ALL)
			System.out.println("UserInfoTable +");
		mConnection = connection;
	}

	public boolean authenticate(String userName, String password) {
		if (DEBUG_ALL)
			System.out.println("authenticate +");
		boolean returnValue = false;
		if (userName.equalsIgnoreCase("") || password.equalsIgnoreCase("")) {
			return returnValue;
		}
		try {
			PreparedStatement preparedStatement = mConnection
					.prepareStatement("select ID from UserInfoTable where UserName = ? and Password = password(?)");
			preparedStatement.setString(1, userName);
			preparedStatement.setString(2, password);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				returnValue = true;
			}
			resultSet.close();
			preparedStatement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return returnValue;
	}

	public boolean isUserNameAvailable(String userName) {
		if (DEBUG_ALL)
			System.out.println("isUserNameAvailable +");
		boolean returnValue = false;
		if (userName == null) {
			return returnValue;
		}
		try {
			PreparedStatement preparedStatement = mConnection
					.prepareStatement("select ID from UserInfoTable where UserName = ?");
			preparedStatement.setString(1, userName);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (!resultSet.next()) {
				returnValue = true;
			}
			resultSet.close();
			preparedStatement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return returnValue;
	}

	public boolean insertUserCredentials(String userName, String password) {
		if (DEBUG_ALL)
			System.out.println("insertUserCredentials +");
		boolean returnValue = false;
		if (userName == null || password == null) {
			return returnValue;
		}
		try {
			PreparedStatement preparedStatement = mConnection
					.prepareStatement("insert into UserInfoTable (UserName, Password) values (?, ?)");
			preparedStatement.setString(1, userName);
			preparedStatement.setString(2, password);
			if (preparedStatement.executeUpdate() > 0) {
				returnValue = true;
			}
			preparedStatement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return returnValue;
	}

	public boolean isUserInfoTableAvailable() {
		if (DEBUG_ALL)
			System.out.println("isUserInfoTableAvailable +");
		boolean returnValue = false;
		try {
			PreparedStatement preparedStatement = mConnection
					.prepareStatement("show tables like ?");
			preparedStatement.setString(1, "UserInfoTable");
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				returnValue = true;
			}
			resultSet.close();
			preparedStatement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return returnValue;
	}

	public void create1DBarcodesTable() {
		if (DEBUG_ALL)
			System.out.println("create 1DBarcodesTable+");
		try {
			PreparedStatement preparedStatement = mConnection
					.prepareStatement("CREATE TABLE 1DBarcodesTable (`ID` int(4) NOT NULL AUTO_INCREMENT,`Name` varchar(128) NOT NULL,`DecoderType` varchar(32) NOT NULL,`Data` varchar(256) NOT NULL,`Height` varchar(8) NOT NULL,`Width` varchar(8) NOT NULL,`Image` mediumblob NOT NULL,PRIMARY KEY (`ID`))");
			preparedStatement.executeUpdate();
			preparedStatement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void create2DBarcodesTable() {
		if (DEBUG_ALL)
			System.out.println("create2DBarcodesTable +");
		try {
			PreparedStatement preparedStatement = mConnection
					.prepareStatement("CREATE TABLE 2DBarcodesTable (`ID` int(4) NOT NULL AUTO_INCREMENT,`Name` varchar(128) NOT NULL,`DecoderType` varchar(32) NOT NULL,`Data` varchar(4096) NOT NULL,`Height` varchar(8) NOT NULL,`Width` varchar(8) NOT NULL,`Image` mediumblob NOT NULL,PRIMARY KEY (`ID`))");
			preparedStatement.executeUpdate();
			preparedStatement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void createSimulScanImagesTable() {
		if (DEBUG_ALL)
			System.out.println("createSimulScanImagesTable +");
		try {
			PreparedStatement preparedStatement = mConnection
					.prepareStatement("CREATE TABLE SimulScanImagesTable (`ID` int(4) NOT NULL AUTO_INCREMENT,`Name` varchar(128) NOT NULL,`Height` varchar(8) NOT NULL,`Width` varchar(8) NOT NULL,`Image` mediumblob NOT NULL,`TemplateName` varchar(128) NOT NULL,`Template` blob NOT NULL,PRIMARY KEY (`ID`))");
			preparedStatement.executeUpdate();
			preparedStatement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void createUserInfoTable() {
		if (DEBUG_ALL)
			System.out.println("createUserInfoTable +");
		try {
			PreparedStatement preparedStatement = mConnection
					.prepareStatement("create table UserInfoTable (ID int not null primary key auto_increment, UserName varchar(32) not null, Password varchar(50) not null)");
			preparedStatement.executeUpdate();
			preparedStatement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void close() {
		if (DEBUG_ALL)
			System.out.println("close +");
		try {
			mConnection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}

