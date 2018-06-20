// http://hsqldb.org/doc/2.0/guide/index.html
package jcat;

import java.sql.*;
import java.util.HashMap;

public class DatabaseHandler {
	private static final String driver = "org.sqlite.JDBC";
	private static final String url = "jdbc:sqlite:database.db";
	private static final String user = "sa";
	private static final String pass = "";
	private static final HashMap<String, String> sql = new HashMap<String, String>();
	static {
		sql.put("test", "select");
		sql.put("init", "drop table item;\r\n");
	}

	private Connection connection;

	public DatabaseHandler () {
		try {
			connection = open();
		} catch(ClassNotFoundException error) {
			error.printStackTrace();
			return;
		} catch(SQLException error) {
			error.printStackTrace();
			return;
		}
	}

	private static Connection open() throws ClassNotFoundException, SQLException {
		Class.forName(driver);
		Connection connection = DriverManager.getConnection(url, user, pass);
		return connection;
	}

	public void close() throws SQLException {
		connection.close();
	}	

	private static boolean hasData() {
		// See if there's data defined
		// Return true if data defined, otherwise false
		return false;
	}

	protected void finalize() throws Throwable {
		if (connection != null && !connection.isClosed()) {
			close();
		}
	}

}