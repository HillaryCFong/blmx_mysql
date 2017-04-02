package foobar.services;

import java.io.FileOutputStream;
import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import foobar.classes.Account;
import foobar.database.DBConnection;

public class AccountManager {
	public AccountManager() {}
	
	public void addAccount(Account a){
		String sql = "INSERT INTO Account (username, password, role, loginAttempt, question)"
				+ "VALUES (?,?,?,?,?);";
		Connection conn = DBConnection.getConnection();
		
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, a.getUsername());
			pstmt.setString(2, a.getPassword());
			pstmt.setString(3, a.getRole());
			pstmt.setInt(4, a.getLoginAttempt());
			pstmt.setString(5, a.getQuestion());
			
			pstmt.executeUpdate();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	public void changePassword(Account a, String password){
		String sql = "SELECT * FROM Account;";
		Connection conn = DBConnection.getConnection();
		FileOutputStream logs;
		String timeStamp = new SimpleDateFormat("yyyy/MM/dd_HH:mm:ss").format(Calendar.getInstance().getTime());
		
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery(sql);
			logs = new FileOutputStream("/Users/francodavid/Documents/workspace/Foobar Bookshop/Logs.text", true);
			
			while(rs.next()){
				if(a.getUsername().equals(rs.getString("username"))){
					String sqlAttempt = "UPDATE Account SET password = ? WHERE username = ?;";
					PreparedStatement pstmtAttempt = conn.prepareStatement(sqlAttempt);
					pstmtAttempt.setString(2, a.getUsername());
					
					logs.write("\n".getBytes());
					logs.write(timeStamp.getBytes());
					logs.write(" - ".getBytes());
					logs.write(rs.getString("role").getBytes());
					logs.write(" - ".getBytes());
					logs.write(rs.getString("username").getBytes());
					logs.write(" : ".getBytes());
					
					if(password.equals("Forgot")){
						pstmtAttempt.setString(1, a.getPassword());
						pstmtAttempt.executeUpdate();
						logs.write("Forgot Changed Password.".getBytes());
					} else if(a.getPassword().equals(rs.getString("password"))){
						pstmtAttempt.setString(1, password);
						pstmtAttempt.executeUpdate();
						logs.write("Changed Password.".getBytes());
					} else {
						logs.write("Attempted to Change Password.".getBytes());
					}
					pstmtAttempt.close();
				}
			}
			
			logs.close();
			rs.close();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	public String checkAccount(Account a){
		String sql = "SELECT * FROM Account;";
		String role = null;
		Connection conn = DBConnection.getConnection();
		FileOutputStream logs;
		String timeStamp = new SimpleDateFormat("yyyy/MM/dd_HH:mm:ss").format(Calendar.getInstance().getTime());
		
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery(sql);
			logs = new FileOutputStream("/Users/francodavid/Documents/workspace/Foobar Bookshop/Logs.text", true);
			
			while(rs.next()){
				if(a.getUsername().equals(rs.getString("username"))){
					
				/*	logs.write("\n".getBytes());
					logs.write(timeStamp.getBytes());
					logs.write(" - ".getBytes());
					logs.write(rs.getString("role").getBytes());
					logs.write(" - ".getBytes());
					logs.write(rs.getString("username").getBytes());
					logs.write(" : ".getBytes());
					
					if(rs.getInt("loginAttempt") % 10 == 0 || rs.getInt("loginAttempt") % 5 != 0){
						String sqlAttempt = "UPDATE Account SET loginAttempt = ? WHERE username = ?;";
						PreparedStatement pstmtAttempt = conn.prepareStatement(sqlAttempt);
						pstmtAttempt.setInt(1, 0);
						pstmtAttempt.setString(2, a.getUsername());
					*/	
						if(a.getPassword().equals(rs.getString("password"))){
						//	if(rs.getInt("loginAttempt") >= 10){
							//	role = "Change";
						//	} else {
							//	role = rs.getString("role");
					//		}
						//	logs.write("Login Successful.".getBytes());
						
						return true;
						} 
						
						pstmtAttempt.executeUpdate();
						pstmtAttempt.close();
					} else{
					// logs.write("Locked Account.".getBytes());
					return false;
					}
			//	} 
			
			}
			
			
			logs.close();
			rs.close();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		// return role;
	}
	
/*
	public boolean forgotPassword(String username, String question){
		String sql = "SELECT * FROM Account;";
		boolean valid = false;
		Connection conn = DBConnection.getConnection();
		
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery(sql);
			
			while(rs.next()){
				if(username.equals(rs.getString("username")) && question.equals(rs.getString("question"))){
					valid = true;
				}
			}
			
			rs.close();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return valid;
	} */
	
	public String hashPassword(String password){
		String passwordToHash = password;
        String generatedPassword = null;
        try {
            // Create MessageDigest instance for MD5
            MessageDigest md = MessageDigest.getInstance("MD5");
            //Add password bytes to digest
            md.update(passwordToHash.getBytes());
            //Get the hash's bytes 
            byte[] bytes = md.digest();
            //This bytes[] has bytes in decimal format;
            //Convert it to hexadecimal format
            StringBuilder sb = new StringBuilder();
            for(int i=0; i< bytes.length ;i++)
            {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            //Get complete hashed password in hex format
            generatedPassword = sb.toString();
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }
        return generatedPassword;
	}
}