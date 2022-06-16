package restBDD;

import java.util.List;

import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class GetRequestBDD {

	@Test
	public void Test1() {
		RestAssured.given()
					.baseUri("http://localhost:3000/employees")
					.when()
					.get("/1")
					.then()
					.log()
					.all() //it shows logs including header and body and .body only shows body
					.statusCode(200);
	}
	
	@Test
	public void Test2() {
		RestAssured.given()
					.baseUri("http://localhost:3000/employees")
					.queryParam("id", "1")
					.when()
					.get()
					.then()
					.log()
					.body() //it shows logs including header and body and .body only shows body
					.statusCode(200)
					.body("[0].name", Matchers.equalTo("Pankaj"));				
	}
	
	@Test
	public void Test3() {
		Response response=RestAssured.given()
					.baseUri("http://localhost:3000/employees")
					.queryParam("id", "1")
					.when()
					.get();
		
		System.out.println(response.getBody().asString());
		
		JsonPath jpath = response.jsonPath();
		List<String> names = jpath.get("name");
		
		System.out.println(names.get(0));
		Assert.assertEquals(names.get(0), "Pankaj");
					
		
	}
}
