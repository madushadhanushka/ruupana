package com.tutorialspoint;


import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

public class MYSQLDBCP {	
	private static MysqlDataSource m_MysqlDataSource = null;
	
	public MYSQLDBCP() {
		super();
		if(m_MysqlDataSource == null) {
			m_MysqlDataSource = getMySQLDataSource();
		}
	}

    private MysqlDataSource getMySQLDataSource() {
        Properties properties = new Properties();
        try {
        	properties.load(MYSQLDBCP.class.getClassLoader().getResourceAsStream("/db.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
        MysqlDataSource mysqlDataSource = new MysqlDataSource();
        mysqlDataSource.setURL("jdbc:mysql://localhost:3306/ruupana");
        mysqlDataSource.setUser("root");
        mysqlDataSource.setPassword("");
        return mysqlDataSource;
    }

    public Connection getConnection() {
    	Connection connection = null;
		try {
			connection = m_MysqlDataSource.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connection;    	
    }
}
