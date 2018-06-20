// http://hsqldb.org/doc/2.0/guide/index.html
package jcat;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

public class DatabaseHandler {
	private static final String driver = "org.hsqldb.jdbc.JDBCDriver";
	private static final String url = "jdbc:hsqldb:file:db;shutdown-true";
	private static final String user = "sa";
	private static final String pass = "";
	private static final HashMap<String, String> sql = new HashMap<String, String>();
	static {
		sql.put("test", "select");
		sql.put("init", "drop table item;\r\n" + 
				"create table item(\r\n" + 
				"	-- basic identity\r\n" + 
				"	id integer,\r\n" + 
				"	name varchar(100),\r\n" + 
				"	-- slot, slot level\r\n" + 
				"	slot varchar(50),\r\n" + 
				"	slotlevel tinyint,\r\n" + 
				"	-- group, subgroup\r\n" + 
				"	group1 varchar(50),\r\n" + 
				"	group2 varchar(50),\r\n" + 
				"	-- misc data\r\n" + 
				"	brand varchar(50),\r\n" + 
				"	notes varchar(250),\r\n" + 
				"	created timestamp,\r\n" + 
				"	purchased timestamp,\r\n" + 
				"	price decimal,\r\n" + 
				"	formality varchar(25),\r\n" + 
				"	material varchar(50),\r\n" + 
				"	warmth varchar(25),\r\n" + 
				"	fit varchar(25),\r\n" + 
				"	size varchar(5),\r\n" + 
				"	sleevelength varchar(25),\r\n" + 
				"	pattern varchar(25),\r\n" + 
				"	-- colors\r\n" + 
				"	color1 varchar(50),\r\n" + 
				"	color2 varchar(50),\r\n" + 
				"	color3 varchar(50)\r\n" + 
				")");
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