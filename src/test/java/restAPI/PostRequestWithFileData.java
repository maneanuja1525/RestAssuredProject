package restAPI;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class PostRequestWithFileData {

	@Test
	public void PostMethod() throws IOException{
		
		RestAssured.baseURI="http://localhost:3000/employees";
		byte[] databytes=Files.readAllBytes(Paths.get("Data.json"));
		
		RequestSpecification request=RestAssured.given();
		Response response=request.contentType(ContentType.JSON)
								 .accept(ContentType.JSON)
								 .body(databytes)
								 .post("/create");
		String reponseBody=response.getBody().asString();
		System.out.println(reponseBody);
		int responseCode=response.getStatusCode();
		Assert.assertEquals(responseCode, 201);
		
		JsonPath jpath=response.jsonPath();
		System.out.println("id : "+jpath.get("id"));
	}
	
}
