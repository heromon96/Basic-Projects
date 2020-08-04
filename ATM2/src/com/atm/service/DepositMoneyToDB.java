package com.atm.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DepositMoneyToDB extends ATM {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		String deposit=req.getParameter("deposit");
		float balance=0;
		
		
		try {
		
			
			String query=" select Balance from bank_application where customerid="+customer_no+" ";
			 STMT=CONN.createStatement();
			
			 RES=STMT.executeQuery(query);
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
