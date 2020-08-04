package com.atm.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.Driver;

public class Balance extends ATM{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			Driver driverref=new Driver();
			DriverManager.registerDriver(driverref);
			
			String dburl="jdbc:mysql://localhost:3306/bank_application?user=root&password=root";
			Connection CONN=DriverManager.getConnection(dburl);
			
			String query=" select Balance from Bank_application where customerid="+customer_no+" ";
			PrintWriter out=resp.getWriter();
			out.println("customer number :"+customer_no);
			Statement STMT=CONN.createStatement();
			
			
			ResultSet RES=STMT.executeQuery(query);
			while(RES.next()) {
				
				out.print("Balance:"+RES.getInt("Balance"));
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
