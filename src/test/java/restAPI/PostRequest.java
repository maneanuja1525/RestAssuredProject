package restAPI;


import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class PostRequest {

	@Test
	public void PostMethod() {
		
		RestAssured.baseURI="http://localhost:3000/employees";
		RequestSpecification request=RestAssured.given();
		Response response=request.contentType(ContentType.JSON)
								 .accept(ContentType.JSON)
								 .body("{\r\n"
								 		+ "        \"name\": \"Rj\",\r\n"
								 		+ "        \"salary\": \"1000\"\r\n"
								 		+ "}")
								 .post("/create");
		String responseBody=response.getBody().asString();
		System.out.println(responseBody);
		int responseCode=response.getStatusCode();
		Assert.assertEquals(responseCode, 201);
		
		JsonPath jpath=response.jsonPath();
		//int empId=jpath.get("id");
		System.out.println("id: "+jpath.get("id"));
	}
}

