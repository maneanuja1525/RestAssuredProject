package restAPI;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class GetRequestWithParam {
	
	@Test
	public void GetMethod(){
		 
		RestAssured.baseURI="http://localhost:3000/employees";
		RequestSpecification request= RestAssured.given();
		Response response= request.param("id", "1").get();
		String responseBody=response.getBody().asString();
		System.out.println(responseBody);
		int responseCode=response.getStatusCode();
		Assert.assertEquals(responseCode, 200);
		Assert.assertTrue(responseBody.contains("Pankaj"));
		
		JsonPath jpath=response.jsonPath();
		List<String> names=jpath.get("name");
		System.out.println(names.get(0));
		Assert.assertEquals(names.get(0), "Pankaj");
		String header=response.getHeader("Content-Type");
		System.out.println(header);
	}
}
