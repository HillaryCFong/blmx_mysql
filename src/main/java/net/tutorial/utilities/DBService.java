package net.tutorial.utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DBService {

	public static final int INSERT_RECORD = 1;
	public static final int UPDATE_RECORD = 2;

	private static DBService instance = new DBService();
	Connection dbConnection = null;
	private PreparedStatement ps = null;

	private DBService() {
		createTable();
	}

	public static DBService getInstance() {
		return instance;
	}

	public ArrayList<Map<String, Object>> allRecords() {

		this.dbConnection = getConnection();

		ArrayList<Map<String, Object>> records = new ArrayList<Map<String, Object>>();
		Map<String, Object> record = null;
		String sSQL = "SELECT _id, language, old, newt, thumbsup, thumbsdown " + "FROM `translations`";

		ResultSet rs = null;

		try {
			ps = this.dbConnection.prepareStatement(sSQL);
			rs = ps.executeQuery(sSQL);

			while (rs.next()) {
				record = new HashMap<String, Object>();
				record.put("_id", rs.getInt("_id"));
				record.put("language", rs.getString("language"));
				record.put("old", rs.getString("old"));
				record.put("newt", rs.getString("newt"));
				record.put("thumbsup", rs.getInt("thumbsup"));
				record.put("thumbsdown", rs.getInt("thumbsdown"));
				records.add(record);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			cleanUp();
		}

		return records;
	}

	private void cleanUp() {
		try {
			if (ps != null) {
				ps.close();
			}
			if (this.dbConnection != null) {
				this.dbConnection.close();
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	private Connection getConnection() {
		Connection dbConnection = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("MySQL JDBC Driver not found.");
			System.out.println(e.getMessage());
			return null;
		}

		try {
			
			EnvVariables envVar = new EnvVariables();
			Map<String, String> creds = envVar.getCredentials("cleardb");
			dbConnection = DriverManager.getConnection(creds.get("jdbcUrl"));
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return null;
		}

		return dbConnection;
	}

	private void createTable() {
		this.dbConnection = getConnection();

		String createTableSQL = "CREATE TABLE IF NOT EXISTS `translations` (" + "`_id` int(11) NOT NULL AUTO_INCREMENT,"
				+ "`language` varchar(45) DEFAULT NULL," + "`old` varchar(90) DEFAULT NULL,"
				+ "`newt` varchar(90) DEFAULT NULL," + "`thumbsup` int(11) DEFAULT NULL," + "`thumbsdown` int(11) DEFAULT NULL," + "PRIMARY KEY (`_id`)"
				+ ") ENGINE=InnoDB DEFAULT CHARSET=utf8;";

		try {
			ps = this.dbConnection.prepareStatement(createTableSQL);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			cleanUp();
		}
	}

	public void deleteRecord(int id) {
		this.dbConnection = getConnection();

		try {
			String sSQL = "DELETE FROM `translations` WHERE _id=?";
			ps = this.dbConnection.prepareStatement(sSQL);
			ps.setInt(1, id);
			ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			cleanUp();
		}
	}

	public Map<String, Object> findRecord(int id) {
		this.dbConnection = getConnection();
		
		Map<String, Object> record = new HashMap<String, Object>();
		ResultSet rs = null;

		try {
			String sSQL = "SELECT * FROM `translations` WHERE _id=?";
			ps = this.dbConnection.prepareStatement(sSQL);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			if (rs.next()) {
				record.put("_id", rs.getInt("_id"));
				record.put("language", rs.getString("language"));
				record.put("old", rs.getString("old"));
				record.put("newt", rs.getString("newt"));
				record.put("thumbsup", rs.getInt("thumbsup"));
				record.put("thumbsdown", rs.getInt("thumbsdown"));
			}

			rs.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			cleanUp();
		}
		return record;
	}

	public void updateRecord(int transaction, Map<String, Object> record) {
		this.dbConnection = getConnection();

		String sSQL = null;

		if (transaction == UPDATE_RECORD) {
			sSQL = "UPDATE `translations` " + "SET language = ? , old = ? , newt = ? , thumbsup = ? , thumbsdown = ? " + "WHERE _id = ?";
		} else {
			sSQL = "INSERT INTO `translations`" + "(`language`, `old`, `newt`, `thumbsup`, `thumbsdown`) VALUES" + "(?,?,?,?,?)";
		}

		try {
	
			ps = this.dbConnection.prepareStatement(sSQL);
			ps.setString(1, (String) record.get("language"));
			ps.setString(2, (String) record.get("old"));
			ps.setString(3, (String) record.get("newt"));
			ps.setInt(4, (int) record.get("thumbsup"));
			ps.setInt(5, (int) record.get("thumbsdown"));
			if (transaction == UPDATE_RECORD) {
				ps.setInt(4, (int) record.get("_id"));
			}
			ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			cleanUp();
		}
	}

}
