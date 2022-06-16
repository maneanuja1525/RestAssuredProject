package restAPI;


import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class GETRequest {

	@Test
	public void GetMethod() {
		
		RestAssured.baseURI="http://localhost:3000/employees";
		RequestSpecification request=RestAssured.given();
		Response response= request.get();
		String responseBody=response.getBody().asString();
		System.out.println(responseBody);
		int responseCode=response.getStatusCode();
		Assert.assertEquals(responseCode, 200);
		
		JsonPath jpath=response.jsonPath();
		List<String> names= jpath.get("name");
		System.out.println(names);
		
	}
}
