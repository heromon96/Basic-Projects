package com.atm.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Transcation_History extends ATM {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		try {
		
			
			String query=" select * from transcation_details where customerid="+customer_no+" ";
			 STMT=CONN.createStatement();
			
			PrintWriter out=resp.getWriter();
			 RES=STMT.executeQuery(query);
			
			String html="<html>"
					+ "<head>"
					+ "<title>"
					+ "Transcation details"
					+ "</title>"
					+ "<body align=\"center\">"
					+ "<div width=\"100%\">"
					+ "<table border=2px solid balck align=\"center\" bgcolor=\"green\"  padding=1px>"
					+ "<tr>"
					+ "<th>Date</th>"
					+ "<th>Type</th>"
					+ "<th>Amount</th>"
					+ "</tr>";
			while(RES.next())
			{
				html+="<tr>"
						+ "<td>"+RES.getDate("transcationdate")+"</td>"
								+ "<td>"+RES.getString("transcation")+"</td>"
										+ "<td>"+RES.getFloat("amount")+"</td>"
												+ "</tr>";
			}
			html+="</table>"
					+ "</div>"
					+ "</body>"
					+ "</html>";
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
