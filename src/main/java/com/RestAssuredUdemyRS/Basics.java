package com.RestAssuredUdemyRS;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.testng.Assert;

import files.Payload;
import files.ReusableMethods;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class Basics {
	
	public static void main(String[] args) {
		//validate if add place api is working as expected
		
		/*
		 rest assured works on 
		 * given - take input,  
		 * when - submit the API - resource and http method 
		 * then - validate the response*/
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		String response=given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json")
			.body(Payload.addPlace())
			.when().post("/maps/api/place/add/json")//resource
			.then().assertThat().statusCode(200).body("scope",equalTo("APP"))
			.header("Server","Apache/2.4.52 (Ubuntu)").extract().response().asString(); 	//Add place->update place with new address->Get place to validate if new address is present
		
		System.out.println(response);
		JsonPath js=new JsonPath(response);//for parsing JSON
		String place_id=js.getString("place_id");
		
		System.out.println("Place ID: "+place_id);
		//update
		String newAddress="Summar walk, Africa";
		
		given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json")
		 					.body(Payload.updatePlace(place_id,newAddress))
		.when().put("/maps/api/place/update/json")
		.then().assertThat().log().all().statusCode(200).body("msg", equalTo("Address successfully updated"));
		
		//get place
		
		String getPlaceResponse= given().log().all().queryParam("key", "qaclick123")
		.queryParam("place_id", place_id)
		.when().get("/maps/api/place/get/json")
		.then().assertThat().log().all().statusCode(200).extract().asString();
		
//		JsonPath js1= new JsonPath(getPlaceResponse);
		JsonPath js1= ReusableMethods.rawToJson(getPlaceResponse);
		String actualAddress = js1.getString("address");
		System.out.println(actualAddress);
		Assert.assertEquals(actualAddress, newAddress);
		}
}
