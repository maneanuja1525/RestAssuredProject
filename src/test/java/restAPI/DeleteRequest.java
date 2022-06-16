package restAPI;

import org.testng.Assert;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class DeleteRequest {

	@Test
	public void DeleteMethod() {
		RestAssured.baseURI="http://localhost:3000/employees";
		RequestSpecification request=RestAssured.given();
		Response response= request.delete("/14");
		String responseBody=response.getBody().asString();
		System.out.println(responseBody);
		int responseCode=response.getStatusCode();
		Assert.assertEquals(responseCode, 200);
		
		
	}
}
