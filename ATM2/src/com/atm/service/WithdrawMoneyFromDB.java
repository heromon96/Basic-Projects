package com.atm.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class WithdrawMoneyFromDB extends ATM {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		String withdraw=req.getParameter("withdraw");
		float balance=0;
		
		
		try {
	
			String query=" select Balance from bank_application where customerid="+customer_no+" ";
			 STMT=CONN.createStatement();
			
			 RES=STMT.executeQuery(query);
			while(RES.next())
			{
				if((RES.getFloat("Balance")>1000) && (Integer.parseInt(withdraw)>=500) ) {
				balance=RES.getFloat("Balance")-Integer.parseInt(withdraw);
				}
				else {
					PrintWriter out=resp.getWriter();
					out.print("insuffcient Balance");
					throw new  ArithmeticException("insuffcient Balance");
				}
				
			}
			query=" update bank_application set balance="+balance+" where customerid="+customer_no+" ";
			
			int res=STMT.executeUpdate(query);
			
			if(res!=0) {
				PrintWriter out=resp.getWriter();
				out.print("collect your cash");
				LocalDate dt=LocalDate.now();
				
				query=" insert into  transcation_details values("+Integer.parseInt(customer_no)+","+Float.parseFloat(withdraw)+",\'withdraw\',\'"+dt+"\') ";
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
