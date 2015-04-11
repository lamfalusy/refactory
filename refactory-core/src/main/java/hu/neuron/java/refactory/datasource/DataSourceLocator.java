package hu.neuron.java.refactory.datasource;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class DataSourceLocator {

	private static final String DS = "fake";
	private static final String JAVA_COMP_ENV = "fake";
	private static DataSource ds;

	static {
		try {
			Context initCtx = new InitialContext();
			Context envCtx = (Context) initCtx.lookup(JAVA_COMP_ENV);

			ds = (DataSource) envCtx.lookup(DS);
		} catch (Throwable t) {
			System.err.println(t.getMessage());
		}
	}

	private DataSourceLocator() {

	}

	public static Connection getConnection() throws SQLException {
		return ds.getConnection();
	}

}
