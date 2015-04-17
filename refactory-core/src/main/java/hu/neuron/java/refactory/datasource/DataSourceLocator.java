package hu.neuron.java.refactory.datasource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataSourceLocator {

	//private static DataSource dataSource;

	/*static {
		try {
			Class.forName("org.hsqldb.jdbcDriver");
			
			Properties prop = new Properties();
			prop.setProperty("url", "jdbc:hsqldb:mydatabase");
			prop.setProperty("user", "SA");
			prop.setProperty("password", "");
			dataSource = JDBCDataSourceFactory.createDataSource(prop);
			try {
				Class.forName("org.sqlite.JDBC");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			Connection con = DriverManager.getConnection("jdbc:sqlite:test.db");
			HSQLUtil.initHSQLDB(con);
		} catch (Throwable t) {
			t.printStackTrace();
			System.err.println(t.getMessage());
		}
	}*/

	private DataSourceLocator() {

	}

	public static Connection getConnection() throws SQLException {
		try {
			Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return DriverManager.getConnection("jdbc:sqlite:test.db");
	}

}
