package miniStore.store.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DataBaseConnections {
	protected static Connection con = null;
	protected ResultSet result;
	protected PreparedStatement statment;
	public  static final String URL="src/main/resources/database/database.db";
	
	public DataBaseConnections() {
		 
	}
	
	public PreparedStatement getStatment() {
		return statment;
	}
	public void setStatment(PreparedStatement statment) {
		this.statment = statment;
	}
	public Connection getCon() {
		return con;
	}
	public ResultSet getResult() {
		return result;
	}

	public void Connect() {
		try {
			
				
			 con = DriverManager.getConnection("jdbc:sqlite:"+URL);
			
			} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		
		}
	}

}
