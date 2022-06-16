package restAPI;

import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class PUTRequest {

	@Test
	public void PutMethod() {
		
		RestAssured.baseURI="http://localhost:3000/employees";
		RequestSpecification request=RestAssured.given();
		Map<String, Object> mapObject= new HashMap<String, Object>();
		mapObject.put("name", "Tina");
		mapObject.put("salary", "10000");
		Response response=request.contentType(ContentType.JSON)
								 .accept(ContentType.JSON)
								 .body(mapObject)
								 .put("/5");
		String responseBody=response.getBody().asString();
		System.out.println(responseBody);
		int reponseCode=response.getStatusCode();
		Assert.assertEquals(reponseCode, 200);
		
	}
}
