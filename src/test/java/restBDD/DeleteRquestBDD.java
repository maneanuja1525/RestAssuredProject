package restBDD;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import io.restassured.RestAssured;

public class DeleteRquestBDD {

	@Test
	public void Test1() {
		
		RestAssured.given()
				   .baseUri("http://localhost:3000/employees")
				   .when()
				   .delete("/5")
				   .then()
				   .log()
				   .body()
				   .statusCode(200)
				   .body(Matchers.equalTo("{}"));
		
	}
}
