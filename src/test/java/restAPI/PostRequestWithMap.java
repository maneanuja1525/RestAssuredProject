package restAPI;


import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class PostRequestWithMap {

	@Test
	public void PostMethod(){
		
		RestAssured.baseURI="http://localhost:3000/employees";
		Map<String, Object> MapObject=new HashMap<String, Object>();
		MapObject.put("name", "Roy");
		MapObject.put("salary", "5000");
		
		RequestSpecification request=RestAssured.given();
		Response response=request.contentType(ContentType.JSON)
								 .accept(ContentType.JSON)
								 .body(MapObject)
								 .post("/create");
		String reponseBody=response.getBody().asString();
		System.out.println(reponseBody);
		int responseCode=response.getStatusCode();
		Assert.assertEquals(responseCode, 201);
		
		JsonPath jpath=response.jsonPath();
		System.out.println("id : "+jpath.get("id"));
	}
	
}
