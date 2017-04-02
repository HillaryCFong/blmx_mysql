package net.tutorial.utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class UDBService {

	public static final int INSERT_RECORD = 1;
	public static final int UPDATE_RECORD = 2;

	private static UDBService instance = new UDBService();
	Connection dbConnection = null;
	private PreparedStatement ps = null;

	private UDBService() {
		createTable();
	}

	public static UDBService getInstance() {
		return instance;
	}

	public ArrayList<Map<String, Object>> allRecords() {

		this.dbConnection = getConnection();

		ArrayList<Map<String, Object>> records = new ArrayList<Map<String, Object>>();
		Map<String, Object> record = null;
		String sSQL = "SELECT _id, name, email, password, number, gender " + "FROM `users`";

		ResultSet rs = null;

		try {
			ps = this.dbConnection.prepareStatement(sSQL);
			rs = ps.executeQuery(sSQL);

			while (rs.next()) {
				record = new HashMap<String, Object>();
				record.put("_id", rs.getInt("_id"));
				record.put("name", rs.getString("name"));
				record.put("email", rs.getString("email"));
				record.put("password", rs.getString("password"));
				record.put("number", rs.getInt("number"));
				record.put("gender", rs.getString("gender"));
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

		String createTableSQL = "CREATE TABLE IF NOT EXISTS `users` (" + "`_id` int(11) NOT NULL AUTO_INCREMENT,"
				+ "`name` varchar(90) DEFAULT NULL," + "`email` varchar(90) DEFAULT NULL,"
				+ "`password` varchar(90) DEFAULT NULL," + "`number` int(30) DEFAULT NULL," + "`gender` varchar(90) DEFAULT NULL," + "PRIMARY KEY (`_id`)"
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
			String sSQL = "DELETE FROM `users` WHERE _id=?";
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
			String sSQL = "SELECT * FROM `users` WHERE _id=?";
			ps = this.dbConnection.prepareStatement(sSQL);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			if (rs.next()) {
				record.put("_id", rs.getInt("_id"));
				record.put("name", rs.getString("name"));
				record.put("email", rs.getString("email"));
				record.put("password", rs.getString("password"));
				record.put("number", rs.getInt("number"));
				record.put("gender", rs.getString("gender"));
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
			sSQL = "UPDATE `users` " + "SET name = ? , email = ? , password = ? , number = ? , gender = ? " + "WHERE _id = ?";
		} else {
			sSQL = "INSERT INTO `users`" + "(`name`, `email`, `password`, `number`, `gender` ) VALUES" + "(?,?,?,?,?)";
		}

		try {
	
			ps = this.dbConnection.prepareStatement(sSQL);
			ps.setString(1, (String) record.get("name"));
			ps.setString(2, (String) record.get("email"));
			ps.setString(3, (String) record.get("password"));
			ps.setInt(4, (int) record.get("number"));
			ps.setString(5, (String) record.get("gender"));
			if (transaction == UPDATE_RECORD) {
				ps.setInt(7, (int) record.get("_id"));
			}
			ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			cleanUp();
		}
	}

}
