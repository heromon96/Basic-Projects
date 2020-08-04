package com.atm.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.Driver;

public class DepositMoneyToDB extends ATM {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		String deposit=req.getParameter("deposit");
		float balance=0;
		
		Driver driverref;
		try {
			driverref = new Driver();
			DriverManager.registerDriver(driverref);
			
			String dburl="jdbc:mysql://localhost:3306/bank_application?user=root&password=root";
			Connection CONN=DriverManager.getConnection(dburl);
			
			String query=" select Balance from bank_application where customerid="+customer_no+" ";
			Statement STMT=CONN.createStatement();
			
			ResultSet RES=STMT.executeQuery(query);
			while(RES.next())
			{
				balance=RES.getFloat("Balance")+Integer.parseInt(deposit);
				
			}
			query=" update bank_application set balance="+balance+" where customerid="+customer_no+" ";
			
			int res=STMT.executeUpdate(query);
			
			if(res!=0) {
				PrintWriter out=resp.getWriter();
				out.print("your Amount has been deposited");
				LocalDate dt=LocalDate.now();
				
				query=" insert into  transcation_details values("+Integer.parseInt(customer_no)+","+Float.parseFloat(deposit)+",\'deposit\',\'"+dt+"\') ";
				STMT.executeUpdate(query);
			}
//		
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		
	}
	
}
