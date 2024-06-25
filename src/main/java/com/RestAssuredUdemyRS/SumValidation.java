package com.RestAssuredUdemyRS;
//
//import org.testng.annotations.Test;
//
//import files.Payload;
//import io.restassured.path.json.JsonPath;
//
//public class SumValidation {
//	
//	@Test
//	public void sumOfCourses() {
//		JsonPath js=new JsonPath(Payload.CoursePrice());
//		int count=js.getInt("courses.size()");
//		
//		int sum=0;
//		for(int i=0;i<count;i++) {
//			int price=js.getInt("courses["+i+"].price");
//			int copies =js.getInt("courses["+i+"].copies");
//			sum=sum+(price*copies);
//		}
//		System.out.println("Total: "+sum);
//	}
//}


// RAHUL SHETTY

import org.testng.Assert;
import org.testng.annotations.Test;

import files.Payload;
import io.restassured.path.json.JsonPath;

public class SumValidation {

	@Test
	public void sumOfCourses()
	{
		int sum = 0;
		JsonPath js=new JsonPath(Payload.CoursePrice());
		int count=	js.getInt("courses.size()");
		for(int i=0;i<count;i++)
		{
			int price=js.getInt("courses["+i+"].price");
			int copies=js.getInt("courses["+i+"].copies");
			int amount = price * copies;
			System.out.println(amount);
			sum = sum + amount;
			
		}
		System.out.println(sum);
		int purchaseAmount =js.getInt("dashboard.purchaseAmount");
		Assert.assertEquals(sum, purchaseAmount);
		
	}
}
