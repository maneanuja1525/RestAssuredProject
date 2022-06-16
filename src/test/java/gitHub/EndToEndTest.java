package gitHub;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class EndToEndTest {
	
	Response response;
	@Test
	public void TestMethod() throws IOException {
		
		response=GetAllRepos();
		Assert.assertEquals(response.getStatusCode(), 200);
	
		response=PostMethod();
		Assert.assertEquals(response.getStatusCode(), 201);
	}
	
	public Response GetAllRepos() {
		
		RestAssured.baseURI="https://api.github.com/users/maneanuja1525/repos";
		RequestSpecification request=RestAssured.given();
		Response response=request.get();
		String responseBody=response.getBody().asString();
		System.out.println(responseBody);
		return response;
		
	}
	
	public Response PostMethod() throws IOException {
		RestAssured.baseURI="https://api.github.com/user/repos";
		RequestSpecification request=RestAssured.given();
		
		byte[] databytes=Files.readAllBytes(Paths.get("data1.json"));
		Response response=request.contentType(ContentType.JSON)
								 .accept(ContentType.JSON)
								 .auth()
								 .oauth2("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")
								 .body(databytes)
								 .post();
		String responseBody=response.getBody().asString();
		System.out.println(responseBody);
		
		return response;
	}
}
