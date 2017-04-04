//package net.tutorial.controllers;
package net.tutorial.utilities;

import java.io.FileOutputStream;
import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

//import net.tutorial.utilities.Account;
import net.tutorial.utilities.DBService;

public class AccountManager {
	public AccountManager() {}
	
/*	public void addAccount(Account a){
		String sql = "INSERT INTO Account (email, password, role, loginAttempt, question)"
				+ "VALUES (?,?,?,?,?);";
		Connection conn = DBService.getConnection();
		
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, a.getEmail());
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
		Connection conn = DBService.getConnection();
		FileOutputStream logs;
		String timeStamp = new SimpleDateFormat("yyyy/MM/dd_HH:mm:ss").format(Calendar.getInstance().getTime());
		
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery(sql);
			logs = new FileOutputStream("/Users/francodavid/Documents/workspace/Foobar Bookshop/Logs.text", true);
			
			while(rs.next()){
				if(a.getEmail().equals(rs.getString("email"))){
					String sqlAttempt = "UPDATE Account SET password = ? WHERE email = ?;";
					PreparedStatement pstmtAttempt = conn.prepareStatement(sqlAttempt);
					pstmtAttempt.setString(2, a.getEmail());
					
					logs.write("\n".getBytes());
					logs.write(timeStamp.getBytes());
					logs.write(" - ".getBytes());
					logs.write(rs.getString("role").getBytes());
					logs.write(" - ".getBytes());
					logs.write(rs.getString("email").getBytes());
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
	*/
	public static boolean checkAccount(Account a){
		String sql = "SELECT * FROM users;";
		Connection conn = DBService.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	//	int count = 0;
		// FileOutputStream logs;
	//	String timeStamp = new SimpleDateFormat("yyyy/MM/dd_HH:mm:ss").format(Calendar.getInstance().getTime());
		
		try {
			pstmt = conn.prepareStatement(sql);
		    ResultSet rs = pstmt.executeQuery(sql);
			while(rs.next()){
				if(a.getEmail().equals(rs.getString("email"))){
					
						if(a.getPassword().equals(rs.getString("password"))){
							
						return true;
						} 
						else return false;
						
					}
				}
				
		    rs.close();
			pstmt.close();
			conn.close();
			}
			catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	       
		return false;
			
					
		
	}
	
/*
	public boolean forgotPassword(String email, String question){
		String sql = "SELECT * FROM Account;";
		boolean valid = false;
		Connection conn = DBService.getConnection();
		
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery(sql);
			
			while(rs.next()){
				if(email.equals(rs.getString("email")) && question.equals(rs.getString("question"))){
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