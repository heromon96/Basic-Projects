package com.projects.bankapp;


import java.util.Date;
import java.util.Scanner;

import com.mysql.jdbc.Driver;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;


	public class Mainclass {

		public static void main(String[] args) {
			Scanner sc=new Scanner(System.in);
		
			System.out.println("Enter your name");
			String name=sc.next();
			
			System.out.println("enter your mobile number");
			long mob=sc.nextLong();
			System.out.println("enter your email id");
			String email=sc.next();
			System.out.println("choose c to create account or choose  t to do or check transcation");
			// TODO Auto-generated method stub
			Connection CONN=null;
			Statement STMT=null;
			ResultSet RES=null;
			char choose=sc.next().charAt(0);
			
			if(choose=='c'||choose=='C')
			{
			
		
			int custid=0;
			try {
				 Driver driverref=new Driver();
				DriverManager.registerDriver(driverref);
				
				String dburl="jdbc:mysql://localhost:3306/Bank_Application?user=root&password=root";
				CONN=DriverManager.getConnection(dburl);
				
				String query=" select * from bank_application where customerid=(select max(customerid) from bank_application) ";
				STMT=CONN.createStatement();
				
				RES=STMT.executeQuery(query);
				while(RES.next())
				{
				
					custid=RES.getInt("customerid")+1;
					
				}
				
				final int minBal=1000;
				
				String paswrd="";
				boolean flag=false;
				do {
					System.out.println("create your password");
					paswrd=sc.next();
					System.out.println("re-enter your password");
					String paswrd2=sc.next();
				
					
					if(paswrd.equals(paswrd2))
					{
						flag=true;
					}
					else {
						System.out.println("password not matching");
					}
					
				}while(flag==false);
				query=" insert into  Bank_Application values(\'"+name+"\',\'"+email+"\',"+custid+","+minBal+",\'"+paswrd+"\' )";
				
				
				int res=STMT.executeUpdate(query);
				if(res!=0)
				{
					System.out.println("Account created");
					System.out.println("Name :"+name);
					System.out.println("moblie number :"+mob);
					System.out.println("Account number :"+custid);
					System.out.println("Balance :"+minBal);
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
					if(STMT!=null )
					{
						STMT.close();
					}
					if(RES!=null)
					{
						RES.close();
					}
				}catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			}
			}
			
			else {
		
			System.out.println("Enter your ID");
			int custid=sc.nextInt();
			
			int c=0;
			boolean flag=false;
			do {
				c++;
				try {
				System.out.println("Enter your password");
				String passwrd=sc.next();
				 Driver driverref=new Driver();
				 DriverManager.registerDriver(driverref);
					
				String dburl="jdbc:mysql://localhost:3306/Bank_Application?user=root&password=root";
				CONN=DriverManager.getConnection(dburl);
				
				String query=" select cpassword from bank_application where customerid="+custid+" ";
				STMT=CONN.createStatement();
				
				RES=STMT.executeQuery(query);
				while(RES.next()) {
				
					if(RES.getString("cpassword").equals(passwrd))
					{
						new Bank(custid).show();
						flag=true;
					}
					
				}
				if(flag==false&&c<=3) {
					System.out.println("incorrect password or ID");
				}
				
				}catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
					break;
				}
			}while(flag==false&&c<=3);
			if(c>=3) {
				System.out.println("you can try after 24hrs");
			}
			}
			
		}

	}
	class Bank
	{
		int custid;
		
		public Bank(int custid) {
			super();
			this.custid = custid;
		}
		Scanner sc=new Scanner(System.in);
		char c;
		final int  minbal=1000;
		int bal;
		int prevoiusTranst;
		Connection CONN=null;
		Statement STMT=null;
		ResultSet res=null;
		public int balance()
		{
			try {
				Driver driverref=new Driver();
				DriverManager.registerDriver(driverref);
				
				String dburl="jdbc:mysql://localhost:3306/Bank_Application?user=root&password=root";
				CONN=DriverManager.getConnection(dburl);
				
				String query=" select Balance from Bank_Application where customerid="+this.custid+" ";
				STMT=CONN.createStatement();
				
				res=STMT.executeQuery(query);
				while(res.next()) {
				bal= res.getInt(("Balance"));
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return bal;
			
		}
		public void depositAmt(int amt)
		{
			try {
				Driver driverref=new Driver();
				DriverManager.registerDriver(driverref);
				
				String dburl="jdbc:mysql://localhost:3306/Bank_Application?user=root&password=root";
				CONN=DriverManager.getConnection(dburl);
				
				String query=" select Balance from Bank_Application where customerid="+this.custid+" ";
				STMT=CONN.createStatement();
				
				res=STMT.executeQuery(query);
				while(res.next()) {
				bal= res.getInt(("Balance"))+amt;
				
				}
				
				query=" update Bank_Application set Balance="+bal+" where customerid="+this.custid+" ";
				STMT=CONN.createStatement();
				
				int res=STMT.executeUpdate(query);
				if(res!=0) {
					System.out.println("your Balance:"+bal);
					LocalDate dt=LocalDate.now();
					
					query=" insert into  transcation_details values("+this.custid+","+amt+",\'deposit\',\'"+dt+"\') ";
					STMT=CONN.createStatement();
					
					STMT.executeUpdate(query);
					
					
				}
				
				
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		public void withdrawAmt(int amt) 
		{
			try {
				Driver driverref=new Driver();
				DriverManager.registerDriver(driverref);
				
				String dburl="jdbc:mysql://localhost:3306/Bank_Application?user=root&password=root";
				CONN=DriverManager.getConnection(dburl);
				
				String query=" select Balance from Bank_Application where customerid="+this.custid+" ";
				STMT=CONN.createStatement();
				
				res=STMT.executeQuery(query);
				while(res.next()) {
				bal= res.getInt(("Balance"));
				if(bal-1000>=amt) {
					bal=bal-amt;
				}
				else {
					throw new  ArithmeticException("insuffcient Balance");
				}
				
				}
				
				query=" update Bank_Application set Balance="+bal+" where customerid="+this.custid+" ";
				STMT=CONN.createStatement();
				
				int res=STMT.executeUpdate(query);
				if(res!=0) {
					System.out.println("your Balance:"+bal);
					LocalDate dt=LocalDate.now();
					
					query=" insert into  transcation_details values("+this.custid+","+amt+",\'withdraw\',\'"+dt+"\') ";
					STMT=CONN.createStatement();
					
					STMT.executeUpdate(query);
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		void show()
		{
			System.out.println("----------------------------------------------------------------------");
			System.out.println("                                       Choose your option\n");
			System.out.println("A)Check Balance\nB)Deposit amount\nC)Withdraw\nD)Prevoius transcation\nE)Exit\n");
//			char c=sc.next().charAt(0);
			System.out.println("----------------------------------------------------------------------");
			do
			{
//				Scanner sc=new Scanner(System.in);
				c=sc.next().charAt(0);
				switch (c) {
				case 'A':System.out.println("*********************");
				balance();
				System.out.println("Balance :"+bal);
					break;
					
				case 'B':System.out.println("*********************");
				System.out.println("Enter the amount to deposit");
				depositAmt(sc.nextInt());
				break;
//				
				case 'C':System.out.println("*********************");
				System.out.println("Enter the amount to withdraw");
				withdrawAmt(sc.nextInt());
				break;
				
				case 'D':System.out.println("*********************");
				transcationHistory();
				break;
				
				case 'E':System.out.println("*********************");
				System.out.println("Thank you for using service");
				break;
				
				default:
					System.out.println("you have choose wrong option");
					break;
				}
				}while(c!='E');
				
			}
		private void transcationHistory() {
			// TODO Auto-generated method stub
			try {
			Driver driverref=new Driver();
			DriverManager.registerDriver(driverref);
			
			String dburl="jdbc:mysql://localhost:3306/Bank_Application?user=root&password=root";
			Connection CONN=DriverManager.getConnection(dburl);
			
			String query=" select name,customerid from Bank_Application where customerid="+this.custid+" ";
			Statement STMT=CONN.createStatement();
			
			ResultSet RES=STMT.executeQuery(query);
			while(RES.next())
			{
				System.out.println("name :"+RES.getString("name"));
				System.out.println("customer :"+RES.getInt("customerid"));
			}
			
			 query=" select * from transcation_details where customerid="+this.custid+" ";
			 STMT=CONN.createStatement();
			
			 RES=STMT.executeQuery(query);
			
			while(RES.next())
			{
				int cstid=RES.getInt("customerid");
				float amt=RES.getFloat("amount");
				String trnsType=RES.getString("transcation");
				Date date=RES.getDate("transcationdate");
				
				
				
				System.out.println("Date :"+date);
				System.out.println("transcation type :"+trnsType);
				System.out.println("amount :"+amt);
				System.out.println();
			}
			
			query="  select Balance from Bank_Application where customerid="+this.custid+" ";
			STMT=CONN.createStatement();
			
			RES=STMT.executeQuery(query);
			if(RES.next())
			{
				System.out.println("Mininmum Balance :"+RES.getFloat("Balance"));
			}
			}catch (SQLException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
	

	}
	


