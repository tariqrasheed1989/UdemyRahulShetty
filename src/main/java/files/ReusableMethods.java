package files;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class ReusableMethods {
	
	public static JsonPath rawToJson(String response) {
		JsonPath js=new JsonPath(response);
				return js;
	}
	public static JsonPath rawToJson(Response response) {
		JsonPath js=new JsonPath(response.asString());	
				return js;
	}

}
