package com.atm.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.Driver;

public class Transcation_History extends ATM {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Driver driverref;
		try {
			driverref = new Driver();
			DriverManager.registerDriver(driverref);
			
			String dburl="jdbc:mysql://localhost:3306/bank_application?user=root&password=root";
			Connection CONN=DriverManager.getConnection(dburl);
			
			String query=" select * from transcation_details where customerid="+customer_no+" ";
			Statement STMT=CONN.createStatement();
			
			PrintWriter out=resp.getWriter();
			ResultSet RES=STMT.executeQuery(query);
			
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
	
	}
	
			
	
}
