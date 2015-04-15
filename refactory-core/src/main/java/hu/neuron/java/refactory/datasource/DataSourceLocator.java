package hu.neuron.java.refactory.datasource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataSourceLocator {

	private static Connection conn;

	static {
		try {
			 conn=DriverManager.getConnection("jdbc:hsqldb:mydatabase","SA","");
			 HSQLUtil.initHSQLDB(conn);
		} catch (Throwable t) {
			System.err.println(t.getMessage());
		}
	}

	private DataSourceLocator() {

	}

	public static Connection getConnection() throws SQLException {
		return conn;
	}

}
