package com.RestAssuredUdemyRS;

import org.testng.Assert;

import files.Payload;
import io.restassured.path.json.JsonPath;

public class ComplexJsonParse {

	public static void main(String[] args) {
		JsonPath js=new JsonPath(Payload.CoursePrice());//expected response
		// print the total courses available
		int count= js.getInt("courses.size()"); //since course is an array and to get the size of array we use size() method
		System.out.println("Total Course: "+count);
		
		//print the purchase amount
		int totalAmount= js.getInt("dashboard.purchaseAmount");
		System.out.println("Total Amount: "+totalAmount);
		
		//print title of the first course
		String course1_Title=js.get("courses[0].title");
		System.out.println("First Course title: "+course1_Title);

		//print title of the 3rd course
		String course3_Title=js.get("courses[2].title");
		System.out.println("Third Course title: "+course3_Title);
		//print all courses title along with their price
		for(int i=0;i<count;i++) {
			String courseTitle= js.get("courses["+i+"].title");
			int CoursePrice=js.getInt("courses["+i+"].price");
			System.out.print("Course Title: "+courseTitle+", ");
			System.out.println("Course Price: "+CoursePrice);
		}
		
		System.out.println("--------print the number of copiese sold by RPA--------");
		
		for(int i=0;i<count;i++) {
			String courseTitle= js.get("courses["+i+"].title");
			if(courseTitle.equalsIgnoreCase("RPA")) {
				int copies=js.getInt("courses["+i+"].copies");
				System.out.println(courseTitle+" copies : "+copies);
				break;
			}
		}
		
		System.out.println("--- print that all the courses price and copies equals to purchase amoutnt");
		int purchaseAmount=js.getInt("dashboard.purchaseAmount");
		int sum=0;
		for(int i=0;i<count;i++) {
			int price=js.getInt("courses["+i+"].price");
			int copies =js.getInt("courses["+i+"].copies");
			sum=sum+(price*copies);
		}
		System.out.println("Total: "+sum);
		Assert.assertEquals(sum, purchaseAmount);
	}

}

