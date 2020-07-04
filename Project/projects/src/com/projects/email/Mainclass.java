package com.projects.email;
import java.util.Scanner;

public class Mainclass {
	public static void main(String[]args) {
		Scanner sc=new Scanner(System.in);
		System.out.println("please Enter your fristname and lastname");
		Email e=new Email(sc.next(),sc.next());
		System.out.println("To share your alternate Email if you wish type yes else no");
		String opinoin=sc.next().toUpperCase();
		switch (opinoin)
		{
			case "YES":
				System.out.println("please Enter your alternate Emailid");
				e.alternateMail(sc.next());
				break;
			default:
				break;
		}
		e.showDetails();
		
	}
}
class Email
{
	private String fristName;
	private String lastName;
	final String password="AbCdefGK$io%^&*34TJq9G@sdiK6=_dj1234567,.<>~`+890";
	private String passwordSet="";
	private String Department;
	private String Emailid;
	private String companyname="Civica";
	private String alternatEmailid;
	Scanner sc=new Scanner(System.in);
	
	public Email(String fristName, String lastName) {
		// TODO Auto-generated constructor stub
		this.fristName=fristName;
		this.lastName=lastName;
		passwordGenerate();
		this.Department=department();
//		System.out.println(Department);
		int rand=(int)(Math.random()*(fristName.length()*lastName.length()));
		Emailid=fristName+lastName+rand+'@'+Department+'.'+companyname+'.'+"com";
		
	}
	
	public void alternateMail(String alternatEmailid) {
		// TODO Auto-generated method stub
		this.alternatEmailid=alternatEmailid;
	}

	public void showDetails() {
		// TODO Auto-generated method stub
		System.out.println("                                        Your Details\n");
		System.out.println("-----------------------------------------------------------------\n");
		System.out.println("Name : "+fristName+" "+lastName);
		System.out.println("Department : "+Department);
		System.out.println("Email id: "+Emailid);
		System.out.println("password :"+passwordSet);
		System.out.println("Alternate EmailID: "+alternatEmailid+"\n");
		System.out.println("-----------------------------------------------------------------");
	}

	private String department() {
		// TODO Auto-generated method stub
	System.out.println("Choose your department");
	System.out.println("1)Sales Department(sales)\n2)Information Technology Department(IT)\n3)Testing Department(QA)\n4)Production Department(Pd)\n5)none(NA)");
	String choice=sc.next();
	switch (choice)
	{
	case "1":
		return "sale";
	case "2":
		return "IT";
	case "3":
		return "QA";
	case "4":
		return "Pd";
	default:
		return null;
	}
	
	
	}
	private void passwordGenerate()
	{
//		System.out.println(password.length());
		for(int i=0;i<=8;i++)
		{
			int num=(int)(Math.random()*password.length());
//			System.out.println("num= "+num);
			
			passwordSet=passwordSet+password.charAt(num);
		}
	}
	
	
}
