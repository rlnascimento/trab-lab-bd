package labbd.series.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Oracle {

	private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	private static final String DRIVER = "oracle.jdbc.driver.OracleDriver";
	private static final String USER = "fatec";
	private static final String PASS = "1234";

	private static Connection connection = null;

	private Oracle() {
	}

	public static Connection getConnection() {
		if (connection == null) {
			try {
				Class.forName(DRIVER);
				connection = DriverManager.getConnection(URL, USER, PASS);
			} catch (SQLException | ClassNotFoundException e) {
				e.printStackTrace();
			}
		}

		return connection;
	}
}
