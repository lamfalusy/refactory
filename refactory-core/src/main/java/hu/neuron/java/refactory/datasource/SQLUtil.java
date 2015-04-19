package hu.neuron.java.refactory.datasource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class SQLUtil {

	public static void closeConnection(Connection connection, PreparedStatement preparedStatement, ResultSet resultSet) {
		try {
			resultSet.close();
		} catch (Throwable t) {
			System.out.println(t);
		}

		try {
			preparedStatement.close();
		} catch (Throwable t) {
			System.out.println(t);
		}
		try {
			connection.close();
		} catch (Throwable t) {
			System.out.println(t);
		}
	}
}
