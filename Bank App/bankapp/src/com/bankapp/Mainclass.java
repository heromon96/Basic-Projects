package com.bankapp;
import java.util.Scanner;
import java.lang.Math;

public class Mainclass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter your name");
		System.out.println("Name :"+sc.next());
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
//		char c=sc.next().charAt(0);
		System.out.println("----------------------------------------------------------------------");
		do
		{
//			Scanner sc=new Scanner(System.in);
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
