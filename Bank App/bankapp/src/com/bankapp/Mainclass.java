package com.projects.bankapp;


	import java.util.Scanner;

import com.mysql.jdbc.Driver;

import java.lang.Math;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

	public class Mainclass {

		public static void main(String[] args) {
			Scanner sc=new Scanner(System.in);
			System.out.println("Enter your name");
			String name=sc.next();
			System.out.println("Name :"+name);
			System.out.println("enter your mobile number");
			long mob=sc.nextLong();
			System.out.println("enter your email id");
			String email=sc.next();
			System.out.println("choose c to create account or choose  t to do or check transcation");
			// TODO Auto-generated method stub
			int custid=101;
			char choose=sc.next().charAt(0);
			if(choose=='c'||choose=='C')
			{
			Driver driverref=null;
			Connection CONN=null;
			Statement STMT=null;
			try {
				driverref=new Driver();
				DriverManager.registerDriver(driverref);
				
				String dburl="jdbc:mysql://localhost:3306/Bank_Application?user=root&password=root";
				CONN=DriverManager.getConnection(dburl);
				
				String query=" insert into Customer_Details values(\'"+name+"\',"+mob+",\'"+email+"\',"+custid+" )";
				STMT=CONN.createStatement();
				
				int res=STMT.executeUpdate(query);
				if(res!=0)
				{
					System.out.println("Account created");
					System.out.println("Name :"+name);
					System.out.println("moblie number :"+mob);
					System.out.println("Account number"+custid);
				}
			
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}
			
			
		
			System.out.println("Enter your ID");
			System.out.println("customer id :"+sc.next());
			new Bank().show();
			
		}

	}
	class Bank
	{
		Scanner sc=new Scanner(System.in);
		char c;
		final int  minbal=1000;
		int bal;
		int prevoiusTranst;
		void depositAmt(int amt)
		{
			if(amt!=0)
			{
				bal=amt+minbal;
				prevoiusTrans(amt);
			}
			else
			{
				System.out.println("wrong input");
			}
		}
		 void prevoiusTrans(int amt) {
			// TODO Auto-generated method stub
				if(amt>0)
				{
					prevoiusTranst=amt;
					System.out.println("your amount has been deposited");
				}
				else
				{
					prevoiusTranst=Math.abs(amt);
					System.out.println("your amount has been withdrawn");
				}
		}
		void withdrawAmt(int amt)
		{
			if(amt!=0)
			{
				bal=(minbal+bal)-amt;
				prevoiusTrans(-amt);
			}
			else
			{
				System.out.println("wrong input");
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
				System.out.println("Balance :"+bal);
					break;
					
				case 'B':System.out.println("*********************");
				System.out.println("Enter the amount to deposit");
				depositAmt(sc.nextInt());
				break;
				
				case 'C':System.out.println("*********************");
				System.out.println("Enter the amount to credit");
				withdrawAmt(sc.nextInt());
				break;
				
				case 'D':System.out.println("*********************");
				System.out.println("Prevoius Transcation ="+prevoiusTranst);
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
		}


