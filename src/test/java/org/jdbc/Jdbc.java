package org.jdbc;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.sql.*;

public class Jdbc {

	   private Connection connection;
	   private static Statement statement;
	   private static ResultSet rs;

	    @BeforeClass
	    public void setUp() {
	            String databaseURL = "jdbc:oracle:thin:@localhost:1521:xe";
	            String user = "hr";
	            String password = "1234";
	            connection = null;
	            try {
	            	Class.forName("oracle.jdbc.driver.OracleDriver");  
	                System.out.println("Connecting to Database...");
	                connection = DriverManager.getConnection(databaseURL, user, password);
	                if (connection != null) {
	                    System.out.println("Connected to the Database...");
	                }
	            } catch (SQLException ex) {
	               ex.printStackTrace();
	            }
	            catch (ClassNotFoundException ex) {
	               ex.printStackTrace();
	            }
	    }

	    @Test
	    public void getEmployeesFromDataBase() {
	        try {
	            String query = "select * from employees";
	            statement = connection.createStatement();
	            rs = statement.executeQuery(query);

	            while(rs.next()){
	                int EmpId= rs.getInt("EMPLOYEE_ID");
	                String EmpName= rs.getString("FIRST_NAME");
	                String EmpAddress=rs.getString("EMAIL");
	                String EmpDept=rs.getString("DEPARTMENT_ID");
	                Double EmpSal= rs.getDouble("SALARY");
	                System.out.println(EmpId+"\t"+EmpName+"\t"+EmpAddress+"\t"+EmpSal+"\t"+EmpDept);
	            }
	        } catch (SQLException ex) {
	           ex.printStackTrace();
	        }
	    }

	    @AfterClass
	    public void tearDown() {
	      if (connection != null) {
	                try {
	                    System.out.println("Closing Database Connection...");
	                    connection.close();
	                } catch (SQLException ex) {
	                    ex.printStackTrace();
	                }
	            }
	      }
	}
