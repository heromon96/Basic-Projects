package com.projects.studentmanagement;


import java.util.Scanner;
import java.util.Arrays;

public class Mainclass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		System.out.println("please enter the no of students ");
		int size=sc.nextInt();
		Student st[] =new Student[size+1];
		for(int i=0;i<size;i++)
		{
			System.out.println("please enter the name");
			st[i]=new Student(sc.next());
		}
		
	
		System.out.println(st[0]);
		sc.close();
	}

}
class Student
{
	private static String name;
	private String courses="";
	final int costPerCourse=600;
	private int id=101;
	private int due;
	private int payment;
	private String choice="";
	private String listOfCourses="";
	private int totalPayment;
	Scanner sc=new Scanner(System.in);
	public Student(String name) {
		super();
		this.name = name;
		
		id++;
		enroll();
	}
	private void enroll() {
		// TODO Auto-generated method stub
		System.out.println("select the courses");
		System.out.println("1)History\n2)Science\n3)Scoial\n4)Math\n5)Civics\n6)Quit");
	
		do {
			System.out.println("please Enter your chioce of course");
			choice=sc.nextLine();
			if(!choice.equalsIgnoreCase("quit"))
			{
			courses= courses+choice+","; 
			due=due+costPerCourse;
			}
			else
			{
				for(int i=0;i<courses.length()-1;i++)
				{
					listOfCourses=listOfCourses+courses.charAt(i);
				}
				listOfCourses=listOfCourses+'.';
				break;
			}
			
			}while(true);
			makePayment();

	}
	public void makePayment() {
		// TODO Auto-generated method stub
		System.out.println("please enter the amount");
		payment=sc.nextInt();
		totalPayment=due;
		due=due-payment;
	}
	public String toString() {
		return "Name :"+name+"\n"+"Student ID :"+id+"\n"+"List of Courses you have enrolled\n"+
	listOfCourses+"\n"+"your total cost :"+totalPayment+"\n"+"your recent payment :"+payment+
				"\n"+"your due :"+due;
	}
}