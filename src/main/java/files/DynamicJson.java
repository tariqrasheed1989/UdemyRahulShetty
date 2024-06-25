package files;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;


public class DynamicJson {

	@Test(dataProvider = "BooksData")
	public void addBook(String isbn,String aisle) {
		RestAssured.baseURI = "http://216.10.245.166";
		Response response= given().header("Content-Type","application/json").
		body(Payload.addBook(isbn,aisle)).
		when().post("Library/Addbook.php").
		then().assertThat().statusCode(200).
		extract().response();
		
		JsonPath js=ReusableMethods.rawToJson(response);
		String id = js.get("ID");
		System.out.println(id);
	}
	@DataProvider(name = "BooksData")
	public Object[][] getData() {
		return new Object[][] {{"ojfwty","9363"},{"cwetee","4253"},{"okmfet","533"}};	
	}
}
