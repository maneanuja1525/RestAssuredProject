package restAPI;


import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class PostRequestWithJsonObject {

	@Test
	public void PostMethod(){
		
		RestAssured.baseURI="http://localhost:3000/employees";
		RequestSpecification request=RestAssured.given();
		JSONObject jobj=new JSONObject();
		jobj.put("name", "Ria");
		jobj.putOnce("salary", "2000");
		Response response=request.contentType(ContentType.JSON)
								 .accept(ContentType.JSON)
								 .body(jobj.toString())
								 .post("/create");
		String reponseBody=response.getBody().asString();
		System.out.println(reponseBody);
		int responseCode=response.getStatusCode();
		Assert.assertEquals(responseCode, 201);
		
		JsonPath jpath=response.jsonPath();
		System.out.println("id : "+jpath.get("id"));
	}
}
