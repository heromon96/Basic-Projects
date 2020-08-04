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

public class ATM extends HttpServlet{
	static String customer_no;
	Driver driverref;
	Connection CONN;
	Statement STMT;
	ResultSet RES;
	public ATM() {
		Driver driverref;
		try {
			driverref = new Driver();
			DriverManager.registerDriver(driverref);
			
			String dburl="jdbc:mysql://localhost:3306/bank_application?user=root&password=root";
			 CONN=DriverManager.getConnection(dburl);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
         customer_no=req.getParameter("customerid");
        String password=req.getParameter("Password");
        resp.setContentType("text/html");
        try {

			
			String query=" select cpassword from bank_application  where customerid="+Integer.parseInt(customer_no)+" ";
			 STMT=CONN.createStatement();
			
			 RES=STMT.executeQuery(query);
			
			PrintWriter out=resp.getWriter();
			int count=0;
			
			String html="";
			while(RES.next()) {
				  
				if(password.equals(RES.getString("cpassword")))
				{
					 html+="<html>"
							+ "<head>"
							+ "<title>"
							+ "choose your option"
							+ "</title>"
							+ "</head>"
							+ "<body align=\"center\">"
							+ "<a href=\"http://localhost:8080/Atm/balance\" target=\"_blank\">A)Check Balance</a>"
							+ "<a href=\"Deposit.html\" target=\"_blank\">B)Deposit</a><br>"
							+"<a href=\"withdraw.html\" target=\"_blank\">C)Withdraw</a>"
							+ "<a href=\"http://localhost:8080/Atm/transaction\" target=\"_blank\">D)Transcation</a><br>"
							+ "<a href=\"#\" target=\"_blank\">E)Exit</a>"
							+"</body>"
							+ "</html>";
					 count++;
				}
				
				
			}
	
			if(count==0)
			{
				
				 html+="<html>"
							+ "<head>"
							+ "<title>"
							+ "choose your option"
							+ "</title>"
							+ "</head>"
							+ "<body align=\"center\">"
							+ "you have entered wrong ID number or wrong password"
							+ "</body>"
							+ "</html>";
				
				
			}
			out.print(html);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        finally {
        	try {
        		if(CONN!=null)
        		{
        			CONN.close();
        		}
        		if(STMT!=null)
        		{
        			STMT.close();
        		}
        		if(RES!=null)
        		{
        			RES.close();
        		}
        	}catch (Exception e2) {
				// TODO: handle exception
        		e2.printStackTrace();
			}
        }
			
	}
}
