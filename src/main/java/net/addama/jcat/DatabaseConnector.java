// http://hsqldb.org/doc/2.0/guide/index.html
// https://github.com/xerial/sqlite-jdbc
// https://www.sqlite.org/lang.html

package jcat;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

public class DatabaseConnector {
	private Connection connection;
	private static final String driver = "org.hsqldb.JDBC";
	private static final String url = "jdbc:hsqldb:file:database";
	private static final String user = "sa";
	private static final String pass = "";
	private static final HashMap<String, String> sql = new HashMap<String, String>();
	private static boolean ready = false;
	static {
		sql.put("test", "select exists (select 1 from item);");
		sql.put("init", "drop table item;\r\n");
	}

	public DatabaseConnector() throws ClassNotFoundException, SQLException {
		connection = open();
		if (hasData()) {
			ready = true;
		} else {
			String query = sql.get("init");
			ready = true;
		}
	}

	private static Connection open() throws ClassNotFoundException, SQLException {
		Class.forName(driver);
		Connection connection = DriverManager.getConnection(url, user, pass);
		return connection;
	}

	private boolean hasData() throws SQLException {
		String test = sql.get("test");
		ResultSet result = query(test);
		return !result.wasNull();
	}

	public ResultSet query(String query) throws SQLException {
		Statement sql = connection.createStatement();
		ResultSet result = sql.executeQuery(query);
		return result;
	}
	
	public ResultSet query(PreparedStatement query) throws SQLException {
		ResultSet result = query.executeQuery();
		return result;
	}
	
	public boolean isReady() {
		return ready;
	}
	
	protected void finalize() throws Throwable {
		if (connection != null && !connection.isClosed()) {
			connection.close();
		}
	}

}