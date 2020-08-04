package com.atm.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Balance extends ATM{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			
			
			String query=" select Balance from Bank_application where customerid="+customer_no+" ";
			PrintWriter out=resp.getWriter();
			 STMT=CONN.createStatement();
			
			
			 RES=STMT.executeQuery(query);
			while(RES.next()) {
				
				out.print("Balance:"+RES.getInt("Balance"));
			}
			
			
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
