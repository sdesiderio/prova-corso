package aaa;

import static org.junit.Assert.assertEquals;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import org.junit.Before;
import org.junit.jupiter.api.Test;



public class TestApplication {
	
	private Connection con;

	@Before
	public Connection getConnection() throws ClassNotFoundException, SQLException {

		final String dbDriver = "com.mysql.cj.jdbc.Driver";
		final String dbUrl = "jdbc:mysql://localhost:3306/prova";
		final String dbUser = "root";
		final String dbPass = "";
		
		Class.forName(dbDriver);
		
	    con = DriverManager.getConnection(dbUrl,dbUser,dbPass);
	    
	    return con;

	}

	@Test
	public void addEmployee() throws SQLException, ClassNotFoundException {
		
		int nRows = 0;
		
		String sqlInsert = "insert into employee (passport_number,first_name,last_name,job,age) values (?,?,?,?,?)";
		PreparedStatement ps = getConnection().prepareStatement(sqlInsert);
		ps.setString(1, "10RT6");
		ps.setString(2, "Loio");
		ps.setString(3, "Stilo");
		ps.setString(4, "manager");
		ps.setInt(5, 56);
		
		nRows = ps.executeUpdate();
		
		assertEquals(nRows,1);
		

	}
	
	@Test
	public void updateEmployee() throws SQLException, ClassNotFoundException {
		
		int nRows = 0;
		
		String sqlInsert = "update employee set age=? where passport_number=?";
		PreparedStatement ps = getConnection().prepareStatement(sqlInsert);
		ps.setInt(1, 60);
		ps.setString(2, "1067A");
		
		nRows = ps.executeUpdate();
		
		assertEquals(nRows,1);
		

	}
	
	@Test
	public void removeEmployee() throws SQLException, ClassNotFoundException {
		
		int nRows = 0;
		
		String sqlInsert = "delete from employee where passport_number=?";
		PreparedStatement ps = getConnection().prepareStatement(sqlInsert);
		ps.setString(1, "1030O");
		
		nRows = ps.executeUpdate();
		
		assertEquals(nRows,1);
		

	}
	
	@Test
	public void findEmployeeByPassportNumber() throws SQLException, ClassNotFoundException {
		
		int count = 0;
		
		String sqlSelect = "select count(first_name) from employee where first_name=?";
		
		PreparedStatement ps = getConnection().prepareStatement(sqlSelect);
		ps.setString(1, "Claudio");
		ResultSet rs = ps.executeQuery();
		
		while (rs.next()) {
			
			count = rs.getInt(1);
			
		}
		
		assertEquals(count,6);
		

	}

}
